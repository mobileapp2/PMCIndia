package com.imuons.pmcindia.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopupRecordModel {

    @SerializedName("srno")
    @Expose
    private Integer srno;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("pin")
    @Expose
    private String pin;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("amount_roi")
    @Expose
    private Integer amountRoi;
    @SerializedName("percentage")
    @Expose
    private String percentage;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("house_id")
    @Expose
    private Integer houseId;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("top_up_by")
    @Expose
    private String topUpBy;
    @SerializedName("roi_status")
    @Expose
    private String roiStatus;
    @SerializedName("top_up_type")
    @Expose
    private Integer topUpType;
    @SerializedName("payment_type")
    @Expose
    private String paymentType;
    @SerializedName("usd_rate")
    @Expose
    private Integer usdRate;
    @SerializedName("binary_pass_status")
    @Expose
    private Integer binaryPassStatus;
    @SerializedName("level_pass_status")
    @Expose
    private Integer levelPassStatus;
    @SerializedName("direct_pass_status")
    @Expose
    private Integer directPassStatus;
    @SerializedName("binary_pass_time")
    @Expose
    private Object binaryPassTime;
    @SerializedName("withdraw")
    @Expose
    private Integer withdraw;
    @SerializedName("old_status")
    @Expose
    private String oldStatus;
    @SerializedName("old_withdrawal")
    @Expose
    private Integer oldWithdrawal;
    @SerializedName("level_income_status")
    @Expose
    private Integer levelIncomeStatus;
    @SerializedName("roi_got")
    @Expose
    private Integer roiGot;
    @SerializedName("slot_no")
    @Expose
    private Integer slotNo;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("package_type")
    @Expose
    private Object packageType;
    @SerializedName("package_name")
    @Expose
    private String packageName;
    @SerializedName("house_name")
    @Expose
    private String houseName;
    private final static long serialVersionUID = -4070344467536168884L;

    public Integer getSrno() {
        return srno;
    }

    public void setSrno(Integer srno) {
        this.srno = srno;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getAmountRoi() {
        return amountRoi;
    }

    public void setAmountRoi(Integer amountRoi) {
        this.amountRoi = amountRoi;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getTopUpBy() {
        return topUpBy;
    }

    public void setTopUpBy(String topUpBy) {
        this.topUpBy = topUpBy;
    }

    public String getRoiStatus() {
        return roiStatus;
    }

    public void setRoiStatus(String roiStatus) {
        this.roiStatus = roiStatus;
    }

    public Integer getTopUpType() {
        return topUpType;
    }

    public void setTopUpType(Integer topUpType) {
        this.topUpType = topUpType;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getUsdRate() {
        return usdRate;
    }

    public void setUsdRate(Integer usdRate) {
        this.usdRate = usdRate;
    }

    public Integer getBinaryPassStatus() {
        return binaryPassStatus;
    }

    public void setBinaryPassStatus(Integer binaryPassStatus) {
        this.binaryPassStatus = binaryPassStatus;
    }

    public Integer getLevelPassStatus() {
        return levelPassStatus;
    }

    public void setLevelPassStatus(Integer levelPassStatus) {
        this.levelPassStatus = levelPassStatus;
    }

    public Integer getDirectPassStatus() {
        return directPassStatus;
    }

    public void setDirectPassStatus(Integer directPassStatus) {
        this.directPassStatus = directPassStatus;
    }

    public Object getBinaryPassTime() {
        return binaryPassTime;
    }

    public void setBinaryPassTime(Object binaryPassTime) {
        this.binaryPassTime = binaryPassTime;
    }

    public Integer getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(Integer withdraw) {
        this.withdraw = withdraw;
    }

    public String getOldStatus() {
        return oldStatus;
    }

    public void setOldStatus(String oldStatus) {
        this.oldStatus = oldStatus;
    }

    public Integer getOldWithdrawal() {
        return oldWithdrawal;
    }

    public void setOldWithdrawal(Integer oldWithdrawal) {
        this.oldWithdrawal = oldWithdrawal;
    }

    public Integer getLevelIncomeStatus() {
        return levelIncomeStatus;
    }

    public void setLevelIncomeStatus(Integer levelIncomeStatus) {
        this.levelIncomeStatus = levelIncomeStatus;
    }

    public Integer getRoiGot() {
        return roiGot;
    }

    public void setRoiGot(Integer roiGot) {
        this.roiGot = roiGot;
    }

    public Integer getSlotNo() {
        return slotNo;
    }

    public void setSlotNo(Integer slotNo) {
        this.slotNo = slotNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getPackageType() {
        return packageType;
    }

    public void setPackageType(Object packageType) {
        this.packageType = packageType;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }
}
