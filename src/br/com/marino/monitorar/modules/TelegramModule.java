package br.com.marino.monitorar.modules;

import br.com.marino.monitorar.enums.TipoNotificacao;
import br.com.marino.monitorar.models.Alarme;
import br.com.marino.monitorar.models.Grupo;
import br.com.marino.monitorar.models.SendTelegram;
import br.com.marino.monitorar.models.TipoNotificacaoAlarme;
import br.com.marino.monitorar.services.AlarmeService;
import br.com.marino.monitorar.services.TelegramService;
import br.com.marino.monitorar.utils.Logs;
import io.github.openunirest.http.HttpResponse;
import io.github.openunirest.http.JsonNode;
import io.github.openunirest.http.Unirest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.utils.URIBuilder;

public class TelegramModule extends Thread {

    public static final String API = "https://api.telegram.org/";
    private final List<SendTelegram> list;
    private boolean run;

    public TelegramModule() {
        list = new ArrayList<>();
        run = false;
    }

    private void clear() {
        list.clear();
    }

    public synchronized void addData(SendTelegram data) {
        try {
            list.add(data);
            notify();
        } catch (Exception ex) {
        }
    }

    public void encerrar() {
        this.run = false;
        clear();
    }

    @Override
    public void run() {

        clear();
        run = true;

        while (run) {

            if (list.isEmpty()) {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException ex) {
                    }
                }
            }

            if (list.isEmpty()) {
                continue;
            }

            SendTelegram obj = list.get(0);
            Alarme alarme = AlarmeService.getInstance().getById(obj.getAlarme().getId());
            TipoNotificacaoAlarme tipoNotificacaoAlarme = TipoNotificacaoAlarme.getTipoNotificacaoAlarme(alarme.getId());

            if (tipoNotificacaoAlarme.getTipoNotificacao() == null
                    && obj.getTipoNotificacao() == TipoNotificacao.SAIU_DA_REGRA) {
                list.remove(0);
                continue;
            }

            if (tipoNotificacaoAlarme.getTipoNotificacao() != null
                    && tipoNotificacaoAlarme.getTipoNotificacao() == obj.getTipoNotificacao()) {
                list.remove(0);
                continue;
            }

            String titulo;
            String mensagem;
            
            if (obj.getTipoNotificacao() == TipoNotificacao.CAIU_NA_REGRA) {
                mensagem = (obj.getAlarme().getMensagem());
                titulo = "✖ - " + obj.getAlarme().getNome();
            } else {
                mensagem = (obj.getAlarme().getMensagemSairDaRegra());
                titulo = "✔ - " + obj.getAlarme().getNome();
            }
            
            mensagem += "\n\nData/Hora: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

            boolean notificado = false;

            for (Grupo grupoAlarme : obj.getGrupos()) {

                try {

                    URIBuilder ub = new URIBuilder(API + "bot" + grupoAlarme.getToken() + "/sendMessage");
                    ub.addParameter("chat_id", grupoAlarme.getChatId());
                    ub.addParameter("text", titulo + "\n\n" + mensagem);
                    String url = ub.toString();

                    TelegramService.setConfigUnirest();

                    HttpResponse<JsonNode> response = Unirest.get(url).asJson();

                    if (!notificado) {
                        notificado = response.getStatus() == 200 && response.getBody().getObject().getBoolean("ok");
                    }

                } catch (Exception ex) {
                    Logs.addLog(alarme, "", LocalDateTime.now(), ex);
                }

            }

            try {

                if (notificado) {

                    tipoNotificacaoAlarme.setTipoNotificacao(obj.getTipoNotificacao());
                    TipoNotificacaoAlarme.update(tipoNotificacaoAlarme, obj.getAlarme().getId());

                    list.remove(0);

                    Logs.addLog(alarme, "Notificação enviada por email com sucesso. "
                            + "Título: " + titulo + ". "
                            + "Texto: " + mensagem, LocalDateTime.now(), null);

                } else {
                    Logs.addLog(alarme, "Não foi possível enviar a notificação.", LocalDateTime.now(), null);
                }

            } catch (Exception ex) {
            }

        }

    }

}
