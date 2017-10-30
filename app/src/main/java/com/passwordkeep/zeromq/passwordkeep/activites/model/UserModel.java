package com.passwordkeep.zeromq.passwordkeep.activites.model;

import com.passwordkeep.zeromq.passwordkeep.activites.model.PasswordKeepModel;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Created by VMLDEV6 on 22/09/2017.
 */

public class UserModel implements Serializable {

    private    String InstallationId;
    public     String userName;
    public     int loginPassword;
    public LinkedList<PasswordKeepModel> PasswordKeepList;



    public UserModel(){

        this.userName="zero";
        this.loginPassword=1991;
    }


    public UserModel(String userName,int password,String InstallationId){
        this.InstallationId=InstallationId;
        this.userName=userName;
        this.loginPassword=password;
    }

    private  UserModel(String InstallationId,String userName,int loginPassword,LinkedList<PasswordKeepModel> PasswordKeepList){
        this.InstallationId=InstallationId;
        this.userName=userName;
        this.loginPassword=loginPassword;
        this.PasswordKeepList=PasswordKeepList;

    }


}
