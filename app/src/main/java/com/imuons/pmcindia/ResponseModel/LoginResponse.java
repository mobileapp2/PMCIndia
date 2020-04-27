package com.imuons.pmcindia.ResponseModel;

import com.imuons.pmcindia.DataModel.LoginData;
import com.imuons.pmcindia.DataModel.QuestionData;

import java.io.Serializable;
import java.util.ArrayList;

public class LoginResponse implements Serializable {
    int code ;
    String status , message ;
    LoginData data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
    }
}
