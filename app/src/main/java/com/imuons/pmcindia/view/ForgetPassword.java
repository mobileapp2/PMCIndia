package com.imuons.pmcindia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imuons.pmcindia.Entity.CheckOtpEntity;
import com.imuons.pmcindia.Entity.CheckuserEntity;
import com.imuons.pmcindia.R;
import com.imuons.pmcindia.ResponseModel.CheckOtpResponse;
import com.imuons.pmcindia.ResponseModel.CheckUserResponse;
import com.imuons.pmcindia.ResponseModel.LoginResponse;
import com.imuons.pmcindia.ResponseModel.SendResponse;
import com.imuons.pmcindia.retrofit.AppService;
import com.imuons.pmcindia.retrofit.ServiceGenerator;
import com.imuons.pmcindia.utils.AppCommon;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPassword extends AppCompatActivity {

    @BindView(R.id.mEditUserid)
    EditText mEditUserid;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.tvSignup)
    TextView tvSignup;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    boolean isValidId = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);

        DecimalFormat form = new DecimalFormat("#.##", new DecimalFormatSymbols(Locale.US));
        //EditText et = null;

        mEditUserid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("value::" , String.valueOf(count));
                if(count >= 3){
                    checkUserId(s);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent (ForgetPassword.this,SignupActivity.class);
                startActivity(login);

            }
        });
    }

    @OnClick(R.id.submit)
    void submit(){
        if(mEditUserid.getText().toString().trim().isEmpty()){
            mEditUserid.setError("Please enter userId");

        }else {
            if(isValidId){
                callResetuserId();
            }else {
                mEditUserid.setError("Please enter valid userId");
            }
        }
    }

    private void callResetuserId() {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            progressBar.setVisibility(View.VISIBLE);
            Call call = apiService.SendOtp(new CheckuserEntity(mEditUserid.getText().toString().trim()));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(ForgetPassword.this).clearNonTouchableFlags(ForgetPassword.this);
                    progressBar.setVisibility(View.GONE);
                    SendResponse authResponse = (SendResponse) response.body();
                    if (authResponse != null) {
                        Log.i("SendOtpResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            if(authResponse.getData() != null) {
                                Toast.makeText(ForgetPassword.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                openAlertBox();
                            }
                        } else {
                            Toast.makeText(ForgetPassword.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(ForgetPassword.this).showDialog(ForgetPassword.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppCommon.getInstance(ForgetPassword.this).clearNonTouchableFlags(ForgetPassword.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(ForgetPassword.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkUserId(CharSequence s) {
        String userId = s.toString();
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            AppService apiService = ServiceGenerator.createService(AppService.class);
            Call call = apiService.checkUser(new CheckuserEntity(userId) );
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                   // progressBar.setVisibility(View.GONE);
                    CheckUserResponse authResponse = (CheckUserResponse) response.body();
                    if (authResponse != null) {
                        Log.i("CheckUserResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            if(authResponse.getData() != null)
                                isValidId = true;


                        } else {
                            isValidId = false;
                           // Toast.makeText(ForgetPassword.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        isValidId = false;
                       // AppCommon.getInstance(ForgetPassword.this).showDialog(ForgetPassword.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                   // progressBar.setVisibility(View.GONE);
                    isValidId = false;
                    AppCommon.getInstance(ForgetPassword.this).clearNonTouchableFlags(ForgetPassword.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(ForgetPassword.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }



    private void openAlertBox() {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        final EditText edittext = new EditText(getApplication());
        edittext.setTextColor(Color.BLACK);
        alert.setMessage("Enter OTP here");
        alert.setTitle("OTP Send Successfully");
        alert.setView(edittext);


        alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

                String YouEditTextValue = edittext.getText().toString();

               verifyOTP(YouEditTextValue);
            }
        });

        alert.show();
    }

    private void verifyOTP(String youEditTextValue) {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            progressBar.setVisibility(View.VISIBLE);
            Call call = apiService.CheckOtp(new CheckOtpEntity(youEditTextValue , mEditUserid.getText().toString().trim()));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(ForgetPassword.this).clearNonTouchableFlags(ForgetPassword.this);
                    progressBar.setVisibility(View.GONE);
                    CheckOtpResponse authResponse = (CheckOtpResponse) response.body();
                    if (authResponse != null) {
                        Log.i("SendOtpResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            if(authResponse.getData() != null) {
                                String url = authResponse.getData();
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));
                                startActivity(i);
                                finish();
                            }
                        } else {
                            Toast.makeText(ForgetPassword.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(ForgetPassword.this).showDialog(ForgetPassword.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppCommon.getInstance(ForgetPassword.this).clearNonTouchableFlags(ForgetPassword.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(ForgetPassword.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

}
