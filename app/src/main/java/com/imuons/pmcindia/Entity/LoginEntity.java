package com.imuons.pmcindia.Entity;

import java.io.Serializable;

public class LoginEntity implements Serializable {
    String user_id , password;

    public LoginEntity(String userId, String password) {
        this.user_id = userId;
        this.password = password;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
