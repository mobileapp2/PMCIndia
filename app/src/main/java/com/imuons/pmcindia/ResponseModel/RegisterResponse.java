package com.imuons.pmcindia.ResponseModel;

import com.imuons.pmcindia.DataModel.ResponseData;

import java.io.Serializable;

public class RegisterResponse implements Serializable {
    int code;
    String status , message ;
    ResponseData data;

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

    public ResponseData getResponseData() {
        return data;
    }

    public void setResponseData(ResponseData responseData) {
        this.data = responseData;
    }
}
