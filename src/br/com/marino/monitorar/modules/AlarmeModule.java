package br.com.marino.monitorar.modules;

import br.com.marino.monitorar.enums.TipoConsulta;
import br.com.marino.monitorar.enums.TipoNotificacao;
import br.com.marino.monitorar.models.Alarme;
import br.com.marino.monitorar.models.ConexaoBD;
import br.com.marino.monitorar.models.Grupo;
import br.com.marino.monitorar.models.SendTelegram;
import br.com.marino.monitorar.services.AlarmeService;
import br.com.marino.monitorar.services.GrupoService;
import br.com.marino.monitorar.utils.Logs;
import br.com.marino.monitorar.utils.ReadListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AlarmeModule extends Thread {

    private TelegramModule telegramModule;
    private final Alarme alarme;
    private final ConexaoBD conexaoBD;
    private final List<Grupo> grupos;
    private boolean run;
    private ReadListener readListener;

    public AlarmeModule(Alarme alarme, ConexaoBD conexaoBD) {
        this.alarme = alarme;
        this.grupos = alarme.getGrupos()
                .stream()
                .filter(obj -> GrupoService.getInstance().getById(obj) != null)
                .map(obj -> GrupoService.getInstance().getById(obj)).collect(Collectors.toList());
        this.conexaoBD = conexaoBD;
        this.run = false;
    }

    public AlarmeModule(Alarme alarme, ConexaoBD conexaoBD, ReadListener readListener) {
        this.alarme = alarme;
        this.grupos = alarme.getGrupos()
                .stream()
                .filter(obj -> GrupoService.getInstance().getById(obj) != null)
                .map(obj -> GrupoService.getInstance().getById(obj)).collect(Collectors.toList());
        this.conexaoBD = conexaoBD;
        this.run = false;
        this.readListener = readListener;
    }

    public void encerrar() {
        this.run = false;

        try {
            telegramModule.encerrar();
            telegramModule.interrupt();
            telegramModule.join();
        } catch (InterruptedException ex) {
        }

        telegramModule = null;

    }

    /*private void notificarByDataHora(LocalDateTime valorLido) {

        LocalDateTime valor;

        if (alarme.getValor().startsWith("now-")) {

            valor = LocalDateTime.now();
            int minutos = Integer.parseInt(alarme.getValor().split("-")[1].trim());

            valor = valor.minus(minutos, ChronoUnit.MINUTES);

        } else if (alarme.getValor().startsWith("now+")) {

            valor = LocalDateTime.now();
            int minutos = Integer.parseInt(alarme.getValor().split("-")[1].trim());

            valor = valor.plus(minutos, ChronoUnit.MINUTES);

        } else {

            valor = LocalDateTime.parse(alarme.getValor().trim());

        }

        if (alarme.getTipo() == Tipo.MAIOR) {

            // vem depois
            if (valorLido.isAfter(valor)) {
                enviarNotificacao(TipoNotificacao.CAIU_NA_REGRA);
            } else {
                enviarNotificacao(TipoNotificacao.SAIU_DA_REGRA);
            }

        }

        if (alarme.getTipo() == Tipo.MAIOR_OU_IGUAL) {

            // vem depois ou é igual
            if (valorLido.isAfter(valor) || valorLido.isEqual(valor)) {
                enviarNotificacao(TipoNotificacao.CAIU_NA_REGRA);
            } else {
                enviarNotificacao(TipoNotificacao.SAIU_DA_REGRA);
            }

        }

        if (alarme.getTipo() == Tipo.MENOR) {

            // vem antes
            if (valorLido.isBefore(valor)) {
                enviarNotificacao(TipoNotificacao.CAIU_NA_REGRA);
            } else {
                enviarNotificacao(TipoNotificacao.SAIU_DA_REGRA);
            }

        }

        if (alarme.getTipo() == Tipo.MENOR_OU_IGUAL) {

            // vem antes ou é igual
            if (valorLido.isBefore(valor) || valorLido.isEqual(valor)) {
                enviarNotificacao(TipoNotificacao.CAIU_NA_REGRA);
            } else {
                enviarNotificacao(TipoNotificacao.SAIU_DA_REGRA);
            }

        }

        if (alarme.getTipo() == Tipo.IGUAL) {

            // é igual
            if (valorLido.isEqual(valor)) {
                enviarNotificacao(TipoNotificacao.CAIU_NA_REGRA);
            } else {
                enviarNotificacao(TipoNotificacao.SAIU_DA_REGRA);
            }

        }

    }*/
    private void enviarNotificacao(TipoNotificacao tipoNotificacao) {
        if (telegramModule == null) {
            return;
        }
        telegramModule.addData(new SendTelegram(alarme, tipoNotificacao, grupos));
    }

    @Override
    public void run() {

        telegramModule = new TelegramModule();
        telegramModule.start();

        this.run = true;

        while (this.run) {

            if (alarme.getTipoConsulta() == TipoConsulta.MANUAL && readListener != null) {
                try (Connection con = conexaoBD.getConnection();
                        PreparedStatement prep = con.prepareStatement(AlarmeService.getInstance()
                                .getSqlTable(alarme));
                        ResultSet res = prep.executeQuery()) {

                    if (res.next()) {
                        readListener.read(res.getObject(alarme.getColuna()));
                    } else {
                        readListener.read("");
                    }

                } catch (Exception ex) {
                    Logs.addLog(alarme, "", LocalDateTime.now(), ex);
                }
            }

            try (Connection con = conexaoBD.getConnection();
                    PreparedStatement prep = con.prepareStatement(AlarmeService.getInstance()
                            .getSql(alarme));
                    ResultSet res = prep.executeQuery()) {

                if (res.next()) {
                    enviarNotificacao(TipoNotificacao.CAIU_NA_REGRA);
                } else {
                    enviarNotificacao(TipoNotificacao.SAIU_DA_REGRA);
                }

            } catch (Exception ex) {
                Logs.addLog(alarme, "", LocalDateTime.now(), ex);
            }

            /*String sql;

            if (alarme.getTipoConsulta() == null
                    || alarme.getTipoConsulta() == TipoConsulta.MANUAL) {

                sql = "SELECT TOP 1 " + alarme.getColuna() + " FROM " + alarme.getTabela() + " "
                        + "ORDER BY " + alarme.getColunaOrdenar() + " " + alarme.getOrdem().toString();

            } else {

                sql = alarme.getSql();

            }

            try (Connection con = conexaoBD.getConnection();
                    PreparedStatement prep = con.prepareStatement(sql);
                    ResultSet res = prep.executeQuery()) {

                if (alarme.getTipoConsulta() == null
                        || alarme.getTipoConsulta() == TipoConsulta.MANUAL) {

                    if (res.next()) {
                        if (alarme.getTipoVariavel() == TipoVariavel.DATA_HORA) {
                            LocalDateTime valorLido = res.getTimestamp(alarme.getColuna()).toLocalDateTime();
                            notificarByDataHora(valorLido);
                        }
                    }

                } else {
                    
                    if (res.next()) {
                        enviarNotificacao(TipoNotificacao.CAIU_NA_REGRA);
                    } else{
                        enviarNotificacao(TipoNotificacao.SAIU_DA_REGRA);
                    }

                }

            } catch (Exception ex) {
                Logs.addLog(alarme, "", LocalDateTime.now(), ex);
            }*/
            try {
                Thread.sleep((alarme.getIntervalo() * 1000));
            } catch (InterruptedException ex) {
            }

        }

    }

}
