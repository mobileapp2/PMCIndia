package com.imuons.pmcindia.retrofit;


import com.imuons.pmcindia.Entity.LoginEntity;
import com.imuons.pmcindia.Entity.RegitrationEntity;
import com.imuons.pmcindia.ResponseModel.LoginResponse;
import com.imuons.pmcindia.ResponseModel.QuestionResponse;
import com.imuons.pmcindia.ResponseModel.RegisterResponse;
import com.imuons.pmcindia.ResponseModel.RendomNumberResponse;
import com.imuons.pmcindia.ResponseModel.DashboardResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AppService {


    @POST("register")
    Call<RegisterResponse> RegisterApi(
            @Body RegitrationEntity registerEntity
    );

    @GET("generate-random-no")
    Call<RendomNumberResponse> GetRendomNumber();

    @POST("login")
    Call<LoginResponse> CallLogin(
            @Body LoginEntity loginEntity
    );

    @GET("get-user-dashboard")
    Call<DashboardResponseModel> GetDashBoard();

}
