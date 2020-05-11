package com.imuons.pmcindia.ResponseModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetPackageHouseModel {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("applicable_for")
    @Expose
    private String applicableFor;
    @SerializedName("users")
    @Expose
    private List<GetPackageUserModel> users = null;
    private final static long serialVersionUID = 3211042810564408309L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApplicableFor() {
        return applicableFor;
    }

    public void setApplicableFor(String applicableFor) {
        this.applicableFor = applicableFor;
    }

    public List<GetPackageUserModel> getUsers() {
        return users;
    }

    public void setUsers(List<GetPackageUserModel> users) {
        this.users = users;
    }
}
