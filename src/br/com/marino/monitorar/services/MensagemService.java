package br.com.marino.monitorar.services;

import br.com.marino.monitorar.models.Mensagem;
import br.com.marino.monitorar.models.Column;
import br.com.marino.monitorar.utils.CrudService;
import java.util.Arrays;
import java.util.List;

public class MensagemService extends CrudService<Mensagem> {

    private static MensagemService instance;

    public static MensagemService getInstance() {
        if (instance == null) {
            instance = new MensagemService();
        }
        return instance;
    }

    @Override
    public int getPosition(List<Mensagem> list, Mensagem obj) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == obj.getId()) {
                return i;
            }
        }
        return 0;
    }

    public Mensagem getById(int id) {
        try {
            List<Mensagem> list = getAll();
            for (Mensagem u : list) {
                if (u.getId() == id) {
                    return u;
                }
            }
        } catch (Exception ex) {
        }
        return null;
    }

    @Override
    public void insert(Mensagem obj) throws Exception {

        List<Mensagem> list = getAll();
        obj.setId(getId(list));
        list.add(obj);

        save(list);

    }

    private int getId(List<Mensagem> list) {
        int maior = 0;
        for (Mensagem a : list) {
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

}
