package com.imuons.pmcindia.Entity;

import java.io.Serializable;

public class UserInfoEntity implements Serializable {
    String account_no,bank_name,branch_name,btc,email,holder_name,ifsc_code,mobile,pan_no,status,withdraw_type;

    public UserInfoEntity(String account_no, String bank_name, String branch_name, String btc, String email, String holder_name, String ifsc_code, String mobile, String pan_no, String status, String withdraw_type) {
        this.account_no = account_no;
        this.bank_name = bank_name;
        this.branch_name = branch_name;
        this.btc = btc;
        this.email = email;
        this.holder_name = holder_name;
        this.ifsc_code = ifsc_code;
        this.mobile = mobile;
        this.pan_no = pan_no;
        this.status = status;
        this.withdraw_type = withdraw_type;
    }
}
