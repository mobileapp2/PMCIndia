package com.imuons.pmcindia.retrofit;


import com.imuons.pmcindia.Entity.ChangePasswordEntity;
import com.imuons.pmcindia.Entity.CheckOtpEntity;
import com.imuons.pmcindia.Entity.CheckuserEntity;
import com.imuons.pmcindia.Entity.LoginEntity;
import com.imuons.pmcindia.Entity.RegitrationEntity;
import com.imuons.pmcindia.Entity.UserInfoEntity;
import com.imuons.pmcindia.Entity.WithdrawAmountEntity;
import com.imuons.pmcindia.ResponseModel.ChangePasswordResponseModel;
import com.imuons.pmcindia.ResponseModel.CheckOtpResponse;
import com.imuons.pmcindia.ResponseModel.CheckUserResponse;
import com.imuons.pmcindia.ResponseModel.DashboardResponseModel;
import com.imuons.pmcindia.ResponseModel.GetFundRequestReportResponseModel;
import com.imuons.pmcindia.ResponseModel.GetPackageResponseModel;
import com.imuons.pmcindia.ResponseModel.LevelResponse;
import com.imuons.pmcindia.ResponseModel.LoginResponse;
import com.imuons.pmcindia.ResponseModel.MakeWithdrawResponseModel;
import com.imuons.pmcindia.ResponseModel.MyTeamResponse;
import com.imuons.pmcindia.ResponseModel.RegisterResponse;
import com.imuons.pmcindia.ResponseModel.RendomNumberResponse;
import com.imuons.pmcindia.ResponseModel.SendResponse;
import com.imuons.pmcindia.ResponseModel.TopupReportResponseModel;
import com.imuons.pmcindia.ResponseModel.UpdateProfileResponse;
import com.imuons.pmcindia.ResponseModel.UserProfileResponse;
import com.imuons.pmcindia.ResponseModel.WithdrawAmountResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AppService {


    @POST("register")
    Call<RegisterResponse> RegisterApi(@Body RegitrationEntity registerEntity);

    @GET("generate-random-no")
    Call<RendomNumberResponse> GetRendomNumber();

    @POST("login")
    Call<LoginResponse> CallLogin(@Body LoginEntity loginEntity);

    @GET("get-user-dashboard")
    Call<DashboardResponseModel> GetDashBoard();

    @POST("checkuserexist")
    Call<CheckUserResponse> checkUser(@Body CheckuserEntity checkuserEntity);

    @POST("sendOtp-update-user-profile1")
    Call<SendResponse> SendOtp(@Body CheckuserEntity checkuserEntity);

    @POST("checkotp2")
    Call<CheckOtpResponse> CheckOtp(@Body CheckOtpEntity checkOtpentity);

    @GET("get-profile-info")
    Call<UserProfileResponse> UserInfo();

    @POST("change-profile")
    Call<UpdateProfileResponse> UpdateProfile(@Body UserInfoEntity userInfoEntity);

    @POST("change-password")
    Call<ChangePasswordResponseModel> ChangePassword(@Body ChangePasswordEntity changePasswordEntity);

    //tabish
    @GET("get-working-balance")
    Call<MakeWithdrawResponseModel> MakeWithdraw();

    //tabish
    @POST("withdraw-income")
    Call<WithdrawAmountResponse> WithdrawAmount(@Body WithdrawAmountEntity amountEntity);


    //Rahul
    @GET("get-packages")
    Call<GetPackageResponseModel> GetPackages();

    @FormUrlEncoded
    @POST("fund-request-report")
    Call<GetFundRequestReportResponseModel> GetFundRequestReport(@FieldMap Map<String, Object> param);

    @FormUrlEncoded
    @POST("topup-report")
    Call<TopupReportResponseModel> GetTopupReport(@FieldMap Map<String, Object> param);

    //azhar
    @GET("get-level")
    Call<LevelResponse> getLevel(
            //   LevelEntity levelEntity
    );

    //azhar
    @FormUrlEncoded
    @POST("level-view")
    Call<MyTeamResponse> getLevelView(
            //@Body MyTeamEntity myTeamEntity
            @FieldMap Map<String, String> loginMap);

//    @Multipart
//    @POST("getaddress")
//    Call<GetAddressResponseModule> GetAddress(@Body  RequestBody byte,);
}
