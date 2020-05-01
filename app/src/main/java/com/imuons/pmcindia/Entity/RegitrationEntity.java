package com.imuons.pmcindia.Entity;

import java.io.Serializable;

public class RegitrationEntity implements Serializable {
    String user_id ,fullname, email , mobile , ref_user_id,password,password_confirmation;

    public RegitrationEntity(String user_id, String fullname, String email, String mobile, String ref_user_id, String password, String password_confirmation) {
        this.user_id = user_id;
        this.fullname = fullname;
        this.email = email;
        this.mobile = mobile;
        this.ref_user_id = ref_user_id;
        this.password = password;
        this.password_confirmation = password_confirmation;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRef_user_id() {
        return ref_user_id;
    }

    public void setRef_user_id(String ref_user_id) {
        this.ref_user_id = ref_user_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_confirmation() {
        return password_confirmation;
    }

    public void setPassword_confirmation(String password_confirmation) {
        this.password_confirmation = password_confirmation;
    }
}
