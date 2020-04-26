package com.imuons.pmcindia.retrofit;

import com.imuons.pmcindia.models.LoginResponseModel;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PMCIndia {

    @FormUrlEncoded
    @POST("login")
    Call<LoginResponseModel> wsLogin(@FieldMap Map<String, String> map);
}
