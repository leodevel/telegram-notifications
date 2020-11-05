package br.com.marino.monitorar.services;

import br.com.marino.monitorar.models.Column;
import br.com.marino.monitorar.models.Usuario;
import br.com.marino.monitorar.utils.CrudService;
import br.com.marino.monitorar.utils.Utils;
import java.util.Arrays;
import java.util.List;

public class UserService extends CrudService<Usuario> {

    private static UserService instance;
    
    public static UserService getInstance(){
        if (instance == null){
            instance = new UserService();
        }
        return instance;
    }
    
    @Override
    public int getPosition(List<Usuario> list, Usuario obj) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getLogin().equals(obj.getLogin())) {
                return i;
            }
        }
        return 0;
    }

    public Usuario getUser(String login, String password) throws Exception {
        List<Usuario> list = getAll();
        for (Usuario u : list) {
            if (u.getLogin().equalsIgnoreCase(login) && u.getSenha()
                    .equals(password)) {
                return u;
            }
        }
        return null;
    }
    
    @Override
    public List<Column> getColumns(){
        List<Column> columns = Arrays.asList(
                new Column("Nome", "nome", String.class),
                new Column("Login", "login", String.class));
        return columns;
    }

}
