package com.imuons.pmcindia.Entity;

import java.io.Serializable;

public class ChangePasswordEntity implements Serializable {

    String conf_pwd,current_pwd,new_pwd;

    public ChangePasswordEntity(String conf_pwd, String current_pwd, String new_pwd) {
        this.conf_pwd = conf_pwd;
        this.current_pwd = current_pwd;
        this.new_pwd = new_pwd;
    }
}
