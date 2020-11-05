package br.com.marino.monitorar.services;

import br.com.marino.monitorar.models.Column;
import br.com.marino.monitorar.models.Grupo;
import br.com.marino.monitorar.utils.CrudService;
import java.util.Arrays;
import java.util.List;

public class GrupoService extends CrudService<Grupo> {

    private static GrupoService instance;

    public static GrupoService getInstance() {
        if (instance == null) {
            instance = new GrupoService();
        }
        return instance;
    }

    @Override
    public int getPosition(List<Grupo> list, Grupo obj) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == obj.getId()) {
                return i;
            }
        }
        return 0;
    }

    public Grupo getById(int id) {
        try {
            List<Grupo> list = getAll();
            for (Grupo u : list) {
                if (u.getId() == id) {
                    return u;
                }
            }
        } catch (Exception ex) {
        }
        return null;
    }

    @Override
    public void insert(Grupo obj) throws Exception {

        List<Grupo> list = getAll();
        obj.setId(getId(list));
        list.add(obj);

        save(list);

    }

    @Override
    public void delete(Grupo obj) throws Exception {

        boolean hasExists = AlarmeService.getInstance()
                .getAll()
                .stream()
                .anyMatch(a -> a.getGrupos() != null && a.getGrupos()
                          .stream().anyMatch(g -> g == obj.getId()));

        
        if (hasExists) {            
            throw new Exception("Este grupo está vinculado a um ou mais alarmes!");            
        }
        
        List<Grupo> list = getAll();
        list.remove(getPosition(list, obj));

        save(list);

    }

    private int getId(List<Grupo> list) {
        int maior = 0;
        for (Grupo a : list) {
            if (maior < a.getId()) {
                maior = a.getId();
            }
        }
        return (maior + 1);
    }

    @Override
    public List<Column> getColumns() {
        List<Column> columns = Arrays.asList(
                new Column("Nome", "nome", String.class),
                new Column("Token", "token", String.class),
                new Column("ChatID", "chatId", String.class));
        return columns;
    }

}
