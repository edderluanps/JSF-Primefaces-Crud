package com.eluanps.crudjsfprimefaces.bean;


import com.eluanps.crudjsfprimefaces.dao.CrudDao;
import com.eluanps.crudjsfprimefaces.util.Errors;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class CrudBean<E, D extends CrudDao> {

    //métodos de controle de tela
    private String EstadoTela = "Search";

    private E entity;
    private List<E> entities;

    public void novo() {
        entity = getNewEntity();
        setInsertScreen();
    }
    public void salvar() {
        try {
            getDao().salvar(entity);
            entity = getNewEntity();
            addMsg("Salvo com sucesso!", FacesMessage.SEVERITY_INFO);
            setSearchScreen();
        } catch (Error e) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, e);
            addMsg("Ops, ocorreu um erro! " + e, FacesMessage.SEVERITY_ERROR);

        }
    }
    public void editar(E entity) {
        this.entity = entity;
        setEditScreen();
    }
    public void deletar(E entity) {
        try{
        getDao().deletar(entity);
        entities.remove(entity);
            addMsg("Deletado com sucesso!", FacesMessage.SEVERITY_INFO);
        }catch(Error e){
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, e);
            addMsg("Ops, ocorreu um erro! " + e, FacesMessage.SEVERITY_ERROR);            
        }
    }
    public void read() {
        if(isSearch()== false){
            setSearchScreen();
            return;
        }
        try {
            entities = getDao().read();
            if(entities == null || entities.size() < 1){
                addMsg("Nenhum dado cadastrado!", FacesMessage.SEVERITY_WARN);
            }
        } catch (Errors ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void addMsg(String mensagem, FacesMessage.Severity tipoErro) {
        FacesMessage facesMessage = new FacesMessage(tipoErro, mensagem, null);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }

    //métodos getters e setters
    public E getEntity(){
        return  entity;
    }
    public void setEntity(E entity){
        this.entity = entity;
    }
    public List<E> getEntities() {
        return entities;
    }
    public void setEntities(List<E> entities) {
        this.entities = entities;
    }
    
    //gerar novos beans
    public abstract D getDao();

    public abstract E getNewEntity();

    //setar os novos estados de tela
    public boolean isInsert() {
        return "Insert".equals(EstadoTela);
    }
    public boolean isEdit() {
        return "Edit".equals(EstadoTela);
    }
    public boolean isSearch() {
        return "Search".equals(EstadoTela);
    }
    public void setInsertScreen() {
        EstadoTela = "Insert";
    }
    public void setEditScreen() {
        EstadoTela = "Edit";
    }
    public void setSearchScreen() {
        EstadoTela = "Search";
    }

}