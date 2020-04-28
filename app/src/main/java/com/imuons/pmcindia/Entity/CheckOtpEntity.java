package com.imuons.pmcindia.Entity;

import java.io.Serializable;

public class CheckOtpEntity implements Serializable {
    String otp ,user_id;

    public CheckOtpEntity(String otp, String user_id) {
        this.otp = otp;
        this.user_id = user_id;
    }
}
