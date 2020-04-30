package com.imuons.pmcindia.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WithdrawReportRecordModel {

    @SerializedName("sr_no")
    @Expose
    private Integer srNo;
    @SerializedName("transaction_hash")
    @Expose
    private String transactionHash;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("transaction_fee")
    @Expose
    private Integer transactionFee;
    @SerializedName("deduction")
    @Expose
    private Integer deduction;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("from_address")
    @Expose
    private String fromAddress;
    @SerializedName("to_address")
    @Expose
    private String toAddress;
    @SerializedName("remark")
    @Expose
    private Object remark;
    @SerializedName("status")
    @Expose
    private Integer status;
    @SerializedName("withdraw_type")
    @Expose
    private Integer withdrawType;
    @SerializedName("paid_date")
    @Expose
    private Object paidDate;
    @SerializedName("notification")
    @Expose
    private Integer notification;
    @SerializedName("network_type")
    @Expose
    private String networkType;
    @SerializedName("api_ref_id")
    @Expose
    private Object apiRefId;
    @SerializedName("user_id")
    @Expose
    private String userId;

    public Integer getSrNo() {
        return srNo;
    }

    public void setSrNo(Integer srNo) {
        this.srNo = srNo;
    }

    public String getTransactionHash() {
        return transactionHash;
    }

    public void setTransactionHash(String transactionHash) {
        this.transactionHash = transactionHash;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getTransactionFee() {
        return transactionFee;
    }

    public void setTransactionFee(Integer transactionFee) {
        this.transactionFee = transactionFee;
    }

    public Integer getDeduction() {
        return deduction;
    }

    public void setDeduction(Integer deduction) {
        this.deduction = deduction;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(String fromAddress) {
        this.fromAddress = fromAddress;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public Object getRemark() {
        return remark;
    }

    public void setRemark(Object remark) {
        this.remark = remark;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(Integer withdrawType) {
        this.withdrawType = withdrawType;
    }

    public Object getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Object paidDate) {
        this.paidDate = paidDate;
    }

    public Integer getNotification() {
        return notification;
    }

    public void setNotification(Integer notification) {
        this.notification = notification;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public Object getApiRefId() {
        return apiRefId;
    }

    public void setApiRefId(Object apiRefId) {
        this.apiRefId = apiRefId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
