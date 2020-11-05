package br.com.marino.monitorar.services;

import br.com.marino.monitorar.models.ConexaoBD;
import br.com.marino.monitorar.utils.CrudService;

public class ConexaoBDService extends CrudService<ConexaoBD> {

    private static ConexaoBDService instance;

    public static ConexaoBDService getInstance() {
        if (instance == null) {
            instance = new ConexaoBDService();
        }
        return instance;
    }   

}
