package com.imuons.pmcindia.retrofit;


import com.imuons.pmcindia.Entity.ChangePasswordEntity;
import com.imuons.pmcindia.Entity.ChatEntity;
import com.imuons.pmcindia.Entity.CheckOtpEntity;
import com.imuons.pmcindia.Entity.CheckuserEntity;
import com.imuons.pmcindia.Entity.LoginEntity;
import com.imuons.pmcindia.Entity.RegitrationEntity;
import com.imuons.pmcindia.Entity.SendMessage;
import com.imuons.pmcindia.Entity.UserInfoEntity;
import com.imuons.pmcindia.Entity.WithdrawAmountEntity;
import com.imuons.pmcindia.ResponseModel.BTCAddressResponseModel;
import com.imuons.pmcindia.ResponseModel.ChangePasswordResponseModel;
import com.imuons.pmcindia.ResponseModel.ChatResponse;
import com.imuons.pmcindia.ResponseModel.CheckOtpResponse;
import com.imuons.pmcindia.ResponseModel.CheckUserResponse;
import com.imuons.pmcindia.ResponseModel.CommonResponse;
import com.imuons.pmcindia.ResponseModel.DashboardResponseModel;
import com.imuons.pmcindia.ResponseModel.DirectResponse;
import com.imuons.pmcindia.ResponseModel.EstimateResponse;
import com.imuons.pmcindia.ResponseModel.GetFundRequestReportResponseModel;
import com.imuons.pmcindia.ResponseModel.GetPackageResponseModel;
import com.imuons.pmcindia.ResponseModel.LevelResponse;
import com.imuons.pmcindia.ResponseModel.LoginResponse;
import com.imuons.pmcindia.ResponseModel.MakeWithdrawResponseModel;
import com.imuons.pmcindia.ResponseModel.MyTeamResponse;
import com.imuons.pmcindia.ResponseModel.PrincipleWithdrawResponse;
import com.imuons.pmcindia.ResponseModel.ROIReportResponse;
import com.imuons.pmcindia.ResponseModel.RegisterResponse;
import com.imuons.pmcindia.ResponseModel.RendomNumberResponse;
import com.imuons.pmcindia.ResponseModel.SendResponse;
import com.imuons.pmcindia.ResponseModel.TopupReportResponseModel;
import com.imuons.pmcindia.ResponseModel.UpdateProfileResponse;
import com.imuons.pmcindia.ResponseModel.UserProfileResponse;
import com.imuons.pmcindia.ResponseModel.WinnerReportResponse;
import com.imuons.pmcindia.ResponseModel.WithdrawAmountResponse;
import com.imuons.pmcindia.ResponseModel.WithdrawReportResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
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

    @GET("get-profile-info")
    Call<UserProfileResponse> UserInfo();

    @POST("change-profile")
    Call<UpdateProfileResponse> UpdateProfile(
            @Body UserInfoEntity userInfoEntity
    );

    @POST("change-password")
    Call<ChangePasswordResponseModel> ChangePassword(
            @Body ChangePasswordEntity changePasswordEntity
    );

    //tabish
    @GET("get-working-balance")
    Call<MakeWithdrawResponseModel> MakeWithdraw();

    //tabish
    @POST("withdraw-income")
    Call<WithdrawAmountResponse> WithdrawAmount(
            @Body WithdrawAmountEntity amountEntity
    );

    //tabish
    @GET("get-principal-balance")
    Call<PrincipleWithdrawResponse> PrincipleWithdraw();

    //tabish
    @FormUrlEncoded
    @POST("withdrwal-income")
    Call<WithdrawReportResponse> GetWithdrawReport(@FieldMap Map<String, Object> param);

    //tabish
    @FormUrlEncoded
    @POST("winner-report")
    Call<WinnerReportResponse> GetWinnerReport(@FieldMap Map<String, Object> param);

    //tabish
    @FormUrlEncoded
    @POST("roi-income")
    Call<ROIReportResponse> GetRoiReport(@FieldMap Map<String, Object> param);

    //tabish
    @FormUrlEncoded
    @POST("estimate-amount")
    Call<EstimateResponse> GetEstimateReport(@FieldMap Map<String, Object> param);


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
            @FieldMap Map<String, String> loginMap
    );

    //azhar
    @POST("fetch_message_mob")
    Call<ChatResponse> getChatList(
            @Body ChatEntity chatEntity
    );

    @POST("send-chat-message")
    Call<CommonResponse> sendMessage(
            @Body SendMessage sendMessage
    );

    @FormUrlEncoded
    @POST("direct-income-referral")
    Call<DirectResponse> GetDirectWithdrawReport(@FieldMap Map<String, Object> param);

    @FormUrlEncoded
    @POST("check_address1")
    Call<BTCAddressResponseModel> wsCheckBTCAddress(@FieldMap Map<String, String> loginMap);


}
