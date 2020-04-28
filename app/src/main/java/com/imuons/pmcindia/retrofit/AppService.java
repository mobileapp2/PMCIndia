package com.imuons.pmcindia.retrofit;


import com.imuons.pmcindia.Entity.CheckOtpEntity;
import com.imuons.pmcindia.Entity.CheckuserEntity;
import com.imuons.pmcindia.Entity.LoginEntity;
import com.imuons.pmcindia.Entity.RegitrationEntity;
import com.imuons.pmcindia.ResponseModel.CheckOtpResponse;
import com.imuons.pmcindia.ResponseModel.CheckUserResponse;
import com.imuons.pmcindia.ResponseModel.LoginResponse;
import com.imuons.pmcindia.ResponseModel.QuestionResponse;
import com.imuons.pmcindia.ResponseModel.RegisterResponse;
import com.imuons.pmcindia.ResponseModel.RendomNumberResponse;
import com.imuons.pmcindia.ResponseModel.DashboardResponseModel;
import com.imuons.pmcindia.ResponseModel.SendResponse;

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

    @POST("checkuserexist")
    Call<CheckUserResponse> checkUser(
            @Body CheckuserEntity checkuserEntity
    );

    @POST("sendOtp-update-user-profile1")
    Call<SendResponse> SendOtp(
            @Body CheckuserEntity checkuserEntity
    );

    @POST("checkotp2")
    Call<CheckOtpResponse> CheckOtp(
            @Body CheckOtpEntity checkOtpentity
    );
}
