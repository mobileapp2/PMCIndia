package com.imuons.pmcindia.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RoiRecordModel {

    @SerializedName("sr_no")
    @Expose
    private Integer srNo;
    @SerializedName("pin")
    @Expose
    private String pin;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("daily_percentage")
    @Expose
    private Integer dailyPercentage;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("name_rupee")
    @Expose
    private String nameRupee;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("on_amount")
    @Expose
    private Integer onAmount;

    public Integer getSrNo() {
        return srNo;
    }

    public void setSrNo(Integer srNo) {
        this.srNo = srNo;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getDailyPercentage() {
        return dailyPercentage;
    }

    public void setDailyPercentage(Integer dailyPercentage) {
        this.dailyPercentage = dailyPercentage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameRupee() {
        return nameRupee;
    }

    public void setNameRupee(String nameRupee) {
        this.nameRupee = nameRupee;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getOnAmount() {
        return onAmount;
    }

    public void setOnAmount(Integer onAmount) {
        this.onAmount = onAmount;
    }

}
