package com.imuons.pmcindia.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecordDirectData {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("laps_amount")
    @Expose
    private Integer lapsAmount;
    @SerializedName("tax_amount")
    @Expose
    private String taxAmount;
    @SerializedName("amt_pin")
    @Expose
    private String amtPin;
    @SerializedName("toUserId")
    @Expose
    private String toUser;
    @SerializedName("fromUserId")
    @Expose
    private String fromUser;
    @SerializedName("rec_date")
    @Expose
    private String recDate;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("invoice_id")
    @Expose
    private String invoiceId;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("from_user_id")
    @Expose
    private String fromUserId;
    @SerializedName("to_user_id")
    @Expose
    private String toUserId;
    @SerializedName("from_fullname")
    @Expose
    private String fromFullname;
    @SerializedName("to_fullname")
    @Expose
    private String toFullname;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Integer getLapsAmount() {
        return lapsAmount;
    }

    public void setLapsAmount(Integer lapsAmount) {
        this.lapsAmount = lapsAmount;
    }

    public String getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(String taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getAmtPin() {
        return amtPin;
    }

    public void setAmtPin(String amtPin) {
        this.amtPin = amtPin;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getRecDate() {
        return recDate;
    }

    public void setRecDate(String recDate) {
        this.recDate = recDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getFromFullname() {
        return fromFullname;
    }

    public void setFromFullname(String fromFullname) {
        this.fromFullname = fromFullname;
    }

    public String getToFullname() {
        return toFullname;
    }

    public void setToFullname(String toFullname) {
        this.toFullname = toFullname;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
