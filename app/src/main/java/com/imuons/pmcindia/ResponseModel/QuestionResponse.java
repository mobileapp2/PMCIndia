package com.imuons.pmcindia.ResponseModel;

import com.imuons.pmcindia.DataModel.QuestionData;

import java.io.Serializable;
import java.util.ArrayList;

public class QuestionResponse implements Serializable {
    int code ;
    String status , message ;
    ArrayList<QuestionData> data;

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

    public ArrayList<QuestionData> getData() {
        return data;
    }

    public void setData(ArrayList<QuestionData> data) {
        this.data = data;
    }
}
