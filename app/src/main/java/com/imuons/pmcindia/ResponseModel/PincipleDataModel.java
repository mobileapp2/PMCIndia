package com.imuons.pmcindia.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PincipleDataModel {

    @SerializedName("principle_Wallet_balance")
    @Expose
    private Integer principleWalletBalance;

    public Integer getPrincipleWalletBalance() {
        return principleWalletBalance;
    }

    public void setPrincipleWalletBalance(Integer principleWalletBalance) {
        this.principleWalletBalance = principleWalletBalance;
    }

}
