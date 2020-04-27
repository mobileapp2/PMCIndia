package com.imuons.pmcindia.ResponseModel;

import com.imuons.pmcindia.DataModel.QuestionData;

import java.io.Serializable;
import java.util.ArrayList;

public class RendomNumberResponse   implements Serializable {
    int code ;
    String status , message ;
    int data;

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

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
