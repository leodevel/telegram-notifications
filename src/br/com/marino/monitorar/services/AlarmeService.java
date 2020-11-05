package br.com.marino.monitorar.services;

import br.com.marino.monitorar.enums.Tipo;
import br.com.marino.monitorar.enums.TipoConsulta;
import br.com.marino.monitorar.enums.TipoVariavel;
import br.com.marino.monitorar.models.Alarme;
import br.com.marino.monitorar.models.Column;
import br.com.marino.monitorar.utils.CrudService;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class AlarmeService extends CrudService<Alarme> {

    private static AlarmeService instance;

    public static AlarmeService getInstance() {
        if (instance == null) {
            instance = new AlarmeService();
        }
        return instance;
    }

    @Override
    public int getPosition(List<Alarme> list, Alarme obj) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == obj.getId()) {
                return i;
            }
        }
        return 0;
    }

    public Alarme getById(int id) {
        try {
            List<Alarme> list = getAll();
            for (Alarme u : list) {
                if (u.getId() == id) {
                    return u;
                }
            }
        } catch (Exception ex) {
        }
        return null;
    }

    @Override
    public void insert(Alarme obj) throws Exception {

        List<Alarme> list = getAll();
        obj.setId(getId(list));
        list.add(obj);

        save(list);

    }

    private int getId(List<Alarme> list) {
        int maior = 0;
        for (Alarme a : list) {
            if (maior < a.getId()) {
                maior = a.getId();
            }
        }
        return (maior + 1);
    }

    @Override
    public List<Column> getColumns() {
        List<Column> columns = Arrays.asList(
                new Column("Nome", "nome", String.class));
        return columns;
    }

    public String getSqlTable(Alarme alarme) {
        return "SELECT TOP 1 * FROM " + alarme.getTabela()
                + " ORDER BY " + alarme.getColunaOrdenar() + " " + alarme.getOrdem().toString();

    }

    public String getSql(Alarme alarme) {
        if (alarme.getTipoConsulta() == TipoConsulta.SCRIPT_SQL) {
            return alarme.getSql();
        }

        String table = getSqlTable(alarme);

        String sql = "SELECT * FROM (" + table + ") AS " + alarme.getTabela()
                + " " + getWhere(alarme);

        return sql;
    }

    public String getSql(Alarme alarme, TipoConsulta tipoConsulta) {
        if (tipoConsulta == TipoConsulta.SCRIPT_SQL) {
            return alarme.getSql();
        }

        String table = getSqlTable(alarme);

        String sql = "SELECT * FROM (" + table + ") AS " + alarme.getTabela()
                + " " + getWhere(alarme);

        return sql;
    }

    private String getWhere(Alarme alarme) {
        String where = "WHERE " + alarme.getColuna() + " " + getTipoWhere(alarme.getTipo()) + " "
                + getValorWhere(alarme.getValor(), alarme.getTipoVariavel());
        return where;
    }

    private String getTipoWhere(Tipo tipo) {
        if (tipo == Tipo.MENOR) {
            return "<";
        }
        if (tipo == Tipo.MENOR_OU_IGUAL) {
            return "<=";
        }
        if (tipo == Tipo.MAIOR) {
            return ">";
        }
        if (tipo == Tipo.MAIOR_OU_IGUAL) {
            return ">=";
        }
        return "";
    }

    private Object getValorWhere(String valor, TipoVariavel tipoVariavel) {
        if (tipoVariavel == TipoVariavel.TEXTO) {
            return "'" + valor + "'";
        }
        if (tipoVariavel == TipoVariavel.NUMERO) {
            return valor;
        }
        if (valor.startsWith("now-")) {
            int minutos = Integer.parseInt(valor.split("-")[1].trim()) * -1;
            return "DATEADD(minute, " + minutos + ", CURRENT_TIMESTAMP)";
        } else if (valor.startsWith("now+")) {
            int minutos = Integer.parseInt(valor.split("\\+")[1].trim());
            return "DATEADD(minute, " + minutos + ", CURRENT_TIMESTAMP)";
        }
        return "'" + LocalDateTime.parse(valor.trim()).format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + "'";
    }

}
