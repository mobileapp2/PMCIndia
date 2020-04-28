package com.imuons.pmcindia.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserProfileDataModel {

    @SerializedName("sponser")
    @Expose
    private String sponser;
    @SerializedName("sponser_fullname")
    @Expose
    private String sponserFullname;
    @SerializedName("sponser_country")
    @Expose
    private Object sponserCountry;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("elligible_for_principal")
    @Expose
    private Integer elligibleForPrincipal;
    @SerializedName("fullname")
    @Expose
    private String fullname;
    @SerializedName("entry_time")
    @Expose
    private Object entryTime;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("btc_address")
    @Expose
    private String btcAddress;
    @SerializedName("ethereum")
    @Expose
    private Object ethereum;
    @SerializedName("country")
    @Expose
    private Object country;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("ref_user_id")
    @Expose
    private String refUserId;
    @SerializedName("account_no")
    @Expose
    private String accountNo;
    @SerializedName("holder_name")
    @Expose
    private String holderName;
    @SerializedName("pan_no")
    @Expose
    private String panNo;
    @SerializedName("bank_name")
    @Expose
    private String bankName;
    @SerializedName("ifsc_code")
    @Expose
    private String ifscCode;
    @SerializedName("branch_name")
    @Expose
    private String branchName;
    @SerializedName("code")
    @Expose
    private Object code;
    @SerializedName("update_status")
    @Expose
    private String updateStatus;
    @SerializedName("withdraw_type")
    @Expose
    private String withdrawType;

    public String getSponser() {
        return sponser;
    }

    public void setSponser(String sponser) {
        this.sponser = sponser;
    }

    public String getSponserFullname() {
        return sponserFullname;
    }

    public void setSponserFullname(String sponserFullname) {
        this.sponserFullname = sponserFullname;
    }

    public Object getSponserCountry() {
        return sponserCountry;
    }

    public void setSponserCountry(Object sponserCountry) {
        this.sponserCountry = sponserCountry;
    }

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

    public Integer getElligibleForPrincipal() {
        return elligibleForPrincipal;
    }

    public void setElligibleForPrincipal(Integer elligibleForPrincipal) {
        this.elligibleForPrincipal = elligibleForPrincipal;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Object getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Object entryTime) {
        this.entryTime = entryTime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBtcAddress() {
        return btcAddress;
    }

    public void setBtcAddress(String btcAddress) {
        this.btcAddress = btcAddress;
    }

    public Object getEthereum() {
        return ethereum;
    }

    public void setEthereum(Object ethereum) {
        this.ethereum = ethereum;
    }

    public Object getCountry() {
        return country;
    }

    public void setCountry(Object country) {
        this.country = country;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRefUserId() {
        return refUserId;
    }

    public void setRefUserId(String refUserId) {
        this.refUserId = refUserId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Object getCode() {
        return code;
    }

    public void setCode(Object code) {
        this.code = code;
    }

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus;
    }

    public String getWithdrawType() {
        return withdrawType;
    }

    public void setWithdrawType(String withdrawType) {
        this.withdrawType = withdrawType;
    }

}
