package com.passwordkeep.zeromq.passwordkeep.activites.model;

import com.passwordkeep.zeromq.passwordkeep.activites.utils.AesUtils;

import java.io.Serializable;

/**
 * Created by VMLDEV6 on 14/09/2017.
 */

public class PasswordKeepModel implements Serializable {

    private String id;
    private String account;
    private String password;
    private String remark;
    private String title;

    private PasswordTypeModel passwordType;


    public PasswordKeepModel()
    {

    }

    public PasswordKeepModel(String title,String account , String password, String remark, PasswordTypeModel passwordType)
    {
        this.id=java.util.UUID.randomUUID().toString();
        this.title=title;
        this.account= AesUtils.encrypt(id,account); ;
        this.password=AesUtils.encrypt(id,password);
        this.remark=remark;
        this.passwordType = passwordType;
    }

    public String getPassword() {

        return password;
    }

    public String getAccount() {

        return account;
    }

    public String getId() {
        return id;
    }

    public String getRemark() {
        return remark;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PasswordTypeModel getPasswordType() {
        return passwordType;
    }

    public void setPasswordType(PasswordTypeModel passwordType) {
        this.passwordType = passwordType;
    }
}
