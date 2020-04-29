package com.imuons.pmcindia.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FundRequestRecordModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("invoice_id")
    @Expose
    private String invoiceId;
    @SerializedName("payment_mode")
    @Expose
    private String paymentMode;
    @SerializedName("trn_ref_no")
    @Expose
    private Object trnRefNo;
    @SerializedName("holder_name")
    @Expose
    private Object holderName;
    @SerializedName("bank_name")
    @Expose
    private Object bankName;
    @SerializedName("deposit_date")
    @Expose
    private Object depositDate;
    @SerializedName("pay_slip")
    @Expose
    private String paySlip;
    @SerializedName("admin_remark")
    @Expose
    private String adminRemark;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("approve_date")
    @Expose
    private String approveDate;
    @SerializedName("reject_date")
    @Expose
    private Object rejectDate;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("attachment")
    @Expose
    private String attachment;
    @SerializedName("product_name")
    @Expose
    private String productName;
    private final static long serialVersionUID = 2302506626062247804L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public Object getTrnRefNo() {
        return trnRefNo;
    }

    public void setTrnRefNo(Object trnRefNo) {
        this.trnRefNo = trnRefNo;
    }

    public Object getHolderName() {
        return holderName;
    }

    public void setHolderName(Object holderName) {
        this.holderName = holderName;
    }

    public Object getBankName() {
        return bankName;
    }

    public void setBankName(Object bankName) {
        this.bankName = bankName;
    }

    public Object getDepositDate() {
        return depositDate;
    }

    public void setDepositDate(Object depositDate) {
        this.depositDate = depositDate;
    }

    public String getPaySlip() {
        return paySlip;
    }

    public void setPaySlip(String paySlip) {
        this.paySlip = paySlip;
    }

    public String getAdminRemark() {
        return adminRemark;
    }

    public void setAdminRemark(String adminRemark) {
        this.adminRemark = adminRemark;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(String approveDate) {
        this.approveDate = approveDate;
    }

    public Object getRejectDate() {
        return rejectDate;
    }

    public void setRejectDate(Object rejectDate) {
        this.rejectDate = rejectDate;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;

    }
}