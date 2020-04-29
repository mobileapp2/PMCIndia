package com.imuons.pmcindia.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FundRequestDataModel {

    @SerializedName("totalRecord")
    @Expose
    private Integer totalRecord;
    @SerializedName("filterRecord")
    @Expose
    private Integer filterRecord;
    @SerializedName("record")
    @Expose
    private List<FundRequestRecordModel> record = null;
    private final static long serialVersionUID = -6300878224687917780L;

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }

    public Integer getFilterRecord() {
        return filterRecord;
    }

    public void setFilterRecord(Integer filterRecord) {
        this.filterRecord = filterRecord;
    }

    public List<FundRequestRecordModel> getRecord() {
        return record;
    }

    public void setRecord(List<FundRequestRecordModel> record) {
        this.record = record;
    }
}
