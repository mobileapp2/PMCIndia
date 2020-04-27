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
}
