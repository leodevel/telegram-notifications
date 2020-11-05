package br.com.marino.monitorar.utils;

public interface AddEditListener<M> {
    
    public void insert(M m);
    
    public void update(M to, M from);
    
}
