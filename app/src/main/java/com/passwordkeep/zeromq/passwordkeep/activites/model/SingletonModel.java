package com.passwordkeep.zeromq.passwordkeep.activites.model;

import java.io.Serializable;

/**
 * Created by VMLDEV6 on 22/09/2017.
 */

public class SingletonModel implements Serializable {

    private UserModel userModel;

    private static class SingletonHolder {

        static final SingletonModel INSTANCE = new SingletonModel();
    }

    public static SingletonModel getInstance() {
        return SingletonHolder.INSTANCE;
    }


    private SingletonModel()
    {

    }

    public void clearUser()
    {
        this.userModel=null;
    }

    public void setUser(UserModel user)
    {
        this.userModel=user;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    private Object readResolve() {
        return getInstance();
    }
}
