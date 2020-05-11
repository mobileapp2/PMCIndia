package com.imuons.pmcindia.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPackageRecordModel {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("package_type")
    @Expose
    private Object packageType;
    @SerializedName("name_rupee")
    @Expose
    private String nameRupee;
    @SerializedName("package_name")
    @Expose
    private String packageName;
    @SerializedName("cost")
    @Expose
    private Integer cost;
    @SerializedName("winning_amount")
    @Expose
    private Integer winningAmount;
    @SerializedName("bvalue")
    @Expose
    private Integer bvalue;
    @SerializedName("direct_income")
    @Expose
    private Integer directIncome;
    @SerializedName("direct_referral")
    @Expose
    private Integer directReferral;
    @SerializedName("hash_rate")
    @Expose
    private Object hashRate;
    @SerializedName("min_hash")
    @Expose
    private Integer minHash;
    @SerializedName("max_hash")
    @Expose
    private Integer maxHash;
    @SerializedName("roi")
    @Expose
    private Integer roi;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("percentage")
    @Expose
    private String percentage;
    @SerializedName("duration_month")
    @Expose
    private Integer durationMonth;
    @SerializedName("capping")
    @Expose
    private Integer capping;
    @SerializedName("binary")
    @Expose
    private Integer binary;
    @SerializedName("date_diff")
    @Expose
    private Integer dateDiff;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("currency_code")
    @Expose
    private Object currencyCode;
    @SerializedName("management_fee")
    @Expose
    private Integer managementFee;
    @SerializedName("consumption_charge")
    @Expose
    private Integer consumptionCharge;
    @SerializedName("conversion_rate")
    @Expose
    private Integer conversionRate;
    @SerializedName("user_show_status")
    @Expose
    private String userShowStatus;
    @SerializedName("ref_product_id")
    @Expose
    private Integer refProductId;
    @SerializedName("total_roi_percentage")
    @Expose
    private Integer totalRoiPercentage;
    @SerializedName("entry_time")
    @Expose
    private String entryTime;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("convert")
    @Expose
    private String convert;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("countryCode")
    @Expose
    private Object countryCode;

    @SerializedName("houses")
    @Expose
    private List<GetPackageHouseModel> houses = null;



    private final static long serialVersionUID = -715073582588838945L;

    public List<GetPackageHouseModel> getHouses() {
        return houses;
    }

    public void setHouses(List<GetPackageHouseModel> houses) {
        this.houses = houses;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getNameRupee() {
        return nameRupee;
    }

    public void setNameRupee(String nameRupee) {
        this.nameRupee = nameRupee;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Integer getWinningAmount() {
        return winningAmount;
    }

    public void setWinningAmount(Integer winningAmount) {
        this.winningAmount = winningAmount;
    }

    public Integer getBvalue() {
        return bvalue;
    }

    public void setBvalue(Integer bvalue) {
        this.bvalue = bvalue;
    }

    public Integer getDirectIncome() {
        return directIncome;
    }

    public void setDirectIncome(Integer directIncome) {
        this.directIncome = directIncome;
    }

    public Integer getDirectReferral() {
        return directReferral;
    }

    public void setDirectReferral(Integer directReferral) {
        this.directReferral = directReferral;
    }

    public Object getHashRate() {
        return hashRate;
    }

    public void setHashRate(Object hashRate) {
        this.hashRate = hashRate;
    }

    public Integer getMinHash() {
        return minHash;
    }

    public void setMinHash(Integer minHash) {
        this.minHash = minHash;
    }

    public Integer getMaxHash() {
        return maxHash;
    }

    public void setMaxHash(Integer maxHash) {
        this.maxHash = maxHash;
    }

    public Integer getRoi() {
        return roi;
    }

    public void setRoi(Integer roi) {
        this.roi = roi;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public Integer getDurationMonth() {
        return durationMonth;
    }

    public void setDurationMonth(Integer durationMonth) {
        this.durationMonth = durationMonth;
    }

    public Integer getCapping() {
        return capping;
    }

    public void setCapping(Integer capping) {
        this.capping = capping;
    }

    public Integer getBinary() {
        return binary;
    }

    public void setBinary(Integer binary) {
        this.binary = binary;
    }

    public Integer getDateDiff() {
        return dateDiff;
    }

    public void setDateDiff(Integer dateDiff) {
        this.dateDiff = dateDiff;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(Object currencyCode) {
        this.currencyCode = currencyCode;
    }

    public Integer getManagementFee() {
        return managementFee;
    }

    public void setManagementFee(Integer managementFee) {
        this.managementFee = managementFee;
    }

    public Integer getConsumptionCharge() {
        return consumptionCharge;
    }

    public void setConsumptionCharge(Integer consumptionCharge) {
        this.consumptionCharge = consumptionCharge;
    }

    public Integer getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Integer conversionRate) {
        this.conversionRate = conversionRate;
    }

    public String getUserShowStatus() {
        return userShowStatus;
    }

    public void setUserShowStatus(String userShowStatus) {
        this.userShowStatus = userShowStatus;
    }

    public Integer getRefProductId() {
        return refProductId;
    }

    public void setRefProductId(Integer refProductId) {
        this.refProductId = refProductId;
    }

    public Integer getTotalRoiPercentage() {
        return totalRoiPercentage;
    }

    public void setTotalRoiPercentage(Integer totalRoiPercentage) {
        this.totalRoiPercentage = totalRoiPercentage;
    }

    public String getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(String entryTime) {
        this.entryTime = entryTime;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getConvert() {
        return convert;
    }

    public void setConvert(String convert) {
        this.convert = convert;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Object countryCode) {
        this.countryCode = countryCode;
    }

}
