package com.imuons.pmcindia.Entity;

import com.google.gson.annotations.SerializedName;

public class ChatEntity {
    @SerializedName("to_user")
    String to_user;

    public ChatEntity(String to_user) {
        this.to_user = to_user;
    }
}
