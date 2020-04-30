package com.imuons.pmcindia.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EstimateRecord {

    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("house_name")
    @Expose
    private String houseName;

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

}
