package com.imuons.pmcindia.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imuons.pmcindia.DataModel.LoginData;
import com.imuons.pmcindia.Entity.LoginEntity;
import com.imuons.pmcindia.Entity.RegitrationEntity;
import com.imuons.pmcindia.R;
import com.imuons.pmcindia.ResponseModel.LoginResponse;
import com.imuons.pmcindia.ResponseModel.RegisterResponse;
import com.imuons.pmcindia.ResponseModel.RendomNumberResponse;
import com.imuons.pmcindia.retrofit.AppService;
import com.imuons.pmcindia.retrofit.ServiceGenerator;
import com.imuons.pmcindia.utils.AppCommon;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends Activity {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.mEditUserId)
    EditText mEditUserId;
    @BindView(R.id.mEditUserName)
    EditText mEditUserName;
    @BindView(R.id.mEditEmail)
    EditText mEditEmail;
    @BindView(R.id.mEditConfirmEmail)
    EditText mEditConfirmEmail;
    @BindView(R.id.mEditPassword)
    EditText mEditPassword;
    @BindView(R.id.mEditConfirmPassword)
    EditText mEditConfirmPassword;
    @BindView(R.id.mEditSponsorName)
    EditText mEditSponsorName;
    @BindView(R.id.mEditMobile)
    EditText mEditMobile;
    @BindView(R.id.tv_login)
    TextView tv_login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);
        callRendomNumberAPI();
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }


    private void callRendomNumberAPI() {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            progressBar.setVisibility(View.VISIBLE);
            //  Call call = apiService.token_CALL(new AuthEntitiyClass("vp235345@vp11.com", "123456"));
            Call call = apiService.GetRendomNumber();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(SignupActivity.this).clearNonTouchableFlags(SignupActivity.this);
                    progressBar.setVisibility(View.GONE);
                    RendomNumberResponse authResponse = (RendomNumberResponse) response.body();
                    if (authResponse != null) {
                        Log.i("RendomResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {

                            mEditUserId.setText(String.valueOf(authResponse.getData()));
                        } else {
                            Toast.makeText(SignupActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(SignupActivity.this).showDialog(SignupActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppCommon.getInstance(SignupActivity.this).clearNonTouchableFlags(SignupActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(SignupActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.register)
    void register(){
        String userId = mEditUserId.getText().toString().trim();
        String userName = mEditUserName.getText().toString().trim();
        String emailId = mEditEmail.getText().toString().trim();
        String cmfEmail = mEditConfirmEmail.getText().toString().trim();
        String password = mEditPassword.getText().toString().trim();
        String cmfPassword = mEditConfirmPassword.getText().toString().trim();
        String sponserId = mEditSponsorName.getText().toString().trim();
        String mobileNumber = mEditMobile.getText().toString().trim();

        if(userId.isEmpty())
            mEditUserId.setError("please enter userId");
        else if(userName.isEmpty())
            mEditUserName.setError("please enter user name");
        else if(emailId.isEmpty())
            mEditEmail.setError("Please enter email");
        else if(cmfEmail.isEmpty())
            mEditConfirmEmail.setError("Please enter  confirm email");
        else if(!cmfEmail.matches(emailId))
            mEditConfirmEmail.setError("confirm email is not match");
        else if(password.isEmpty())
            mEditPassword.setError("please enter password");
        else if(cmfPassword.isEmpty())
            mEditConfirmPassword.setError("please enter confirm password");
         else if(sponserId.isEmpty())
            mEditSponsorName.setError("please enter Sponsor Id");
        else if(mobileNumber.isEmpty())
            mEditMobile.setError("please enter mobile number");
        else{
            callRegisterApi(new RegitrationEntity(userId , userName , emailId , mobileNumber , sponserId , password , cmfPassword ));
        }
    }

    private void callRegisterApi(RegitrationEntity regitrationEntity) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            progressBar.setVisibility(View.VISIBLE);
            //  Call call = apiService.token_CALL(new AuthEntitiyClass("vp235345@vp11.com", "123456"));
            Call call = apiService.RegisterApi(regitrationEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(SignupActivity.this).clearNonTouchableFlags(SignupActivity.this);
                    progressBar.setVisibility(View.GONE);
                    RegisterResponse authResponse = (RegisterResponse) response.body();
                    if (authResponse != null) {
                        Log.i("RendomResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                           /* Intent intent = new Intent();
                            intent.putExtra("userId" , authResponse.getResponseData().getUserid());
                            setResult(180 , intent);
                            finish();*/
                            AppCommon.getInstance(getApplicationContext()).setUserLogin(regitrationEntity.getUser_id(), regitrationEntity.getPassword());
                           callLoginApi(new LoginEntity(regitrationEntity.getUser_id() , regitrationEntity.getPassword()));
                        } else {
                            Toast.makeText(SignupActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(SignupActivity.this).showDialog(SignupActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppCommon.getInstance(SignupActivity.this).clearNonTouchableFlags(SignupActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(SignupActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void callLoginApi(LoginEntity loginEntity) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            progressBar.setVisibility(View.VISIBLE);
            //  Call call = apiService.token_CALL(new AuthEntitiyClass("vp235345@vp11.com", "123456"));
            Call call = apiService.CallLogin(loginEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(SignupActivity.this).clearNonTouchableFlags(SignupActivity.this);
                    progressBar.setVisibility(View.GONE);
                    LoginResponse authResponse = (LoginResponse) response.body();
                    if (authResponse != null) {
                        Log.i("loginResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            if(authResponse.getData() != null)

                                setData(authResponse.getData());

                        } else {
                            Toast.makeText(SignupActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(SignupActivity.this).showDialog(SignupActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppCommon.getInstance(SignupActivity.this).clearNonTouchableFlags(SignupActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(SignupActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(LoginData data) {
        AppCommon.getInstance(this).setUserObject(new Gson().toJson(data));
        AppCommon.getInstance(this).setToken(data.getAccess_token());
        startActivity(new Intent(this , DashboardActivity.class) );
        finish();
    }





}
