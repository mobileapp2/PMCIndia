package com.imuons.pmcindia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imuons.pmcindia.DataModel.LoginData;
import com.imuons.pmcindia.Entity.LoginEntity;
import com.imuons.pmcindia.R;
import com.imuons.pmcindia.ResponseModel.LoginResponse;
import com.imuons.pmcindia.ResponseModel.RendomNumberResponse;
import com.imuons.pmcindia.fragments.DashboardFragment;
import com.imuons.pmcindia.retrofit.AppService;
import com.imuons.pmcindia.retrofit.ServiceGenerator;
import com.imuons.pmcindia.utils.AppCommon;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.mEditUserName)
    EditText mEditUserName;
    @BindView(R.id.mEditPassword)
    EditText mEditPassword;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.singUp)
    void singUp(){
        startActivity(new Intent(this , SignupActivity.class));
    }
    @OnClick(R.id.login)
    void login(){
        String userId = mEditUserName.getText().toString().trim();
        String password = mEditPassword.getText().toString().trim();
        if(userId.isEmpty())
            mEditUserName.setError("Please enter userId");
        else if(password.isEmpty())
            mEditPassword.setError("Please enter password");
        else
            callLoginApi(new LoginEntity(userId , password));
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
                    AppCommon.getInstance(LoginActivity.this).clearNonTouchableFlags(LoginActivity.this);
                    progressBar.setVisibility(View.GONE);
                    LoginResponse authResponse = (LoginResponse) response.body();
                    if (authResponse != null) {
                        Log.i("loginResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                           if(authResponse.getData() != null)
                               setData(authResponse.getData());

                        } else {
                            Toast.makeText(LoginActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(LoginActivity.this).showDialog(LoginActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppCommon.getInstance(LoginActivity.this).clearNonTouchableFlags(LoginActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(LoginActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(this , DashboardActivity.class));
    }
}
