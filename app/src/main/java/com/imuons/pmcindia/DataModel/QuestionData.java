package com.imuons.pmcindia.DataModel;

import java.io.Serializable;

public class QuestionData implements Serializable {
    int id;
    String question;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
