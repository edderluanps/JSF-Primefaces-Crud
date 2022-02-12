package com.eluanps.crudjsfprimefaces.bean;

import com.eluanps.crudjsfprimefaces.dao.UserDao;
import com.eluanps.crudjsfprimefaces.entity.User;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "ub")
public class UserBean extends CrudBean<User, UserDao>{

    private UserDao userDao;

    @Override
    public UserDao getDao() {
        if(userDao == null){
            userDao = new UserDao();
        }
        return userDao;
    }

    @Override
    public User getNewEntity() {
        return new User();
    }
    
}