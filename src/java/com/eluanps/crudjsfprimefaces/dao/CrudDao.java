package com.eluanps.crudjsfprimefaces.dao;

import com.eluanps.crudjsfprimefaces.util.Errors;
import java.util.List;

public interface CrudDao<E> {
    
    public void salvar(E entity) throws Error;
    
    public void deletar(E entity) throws Error;
    
    public List<E> read() throws Errors ;
    
}
