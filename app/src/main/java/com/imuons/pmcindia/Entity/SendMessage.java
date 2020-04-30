package com.imuons.pmcindia.Entity;

import java.io.Serializable;

public class SendMessage implements Serializable {
    String to_user , message ,type;

    public SendMessage(String to_user, String message, String type) {
        this.to_user = to_user;
        this.message = message;
        this.type = type;
    }
}
