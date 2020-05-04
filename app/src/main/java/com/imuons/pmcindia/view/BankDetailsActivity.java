package com.imuons.pmcindia.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.imuons.pmcindia.Entity.UserInfoEntity;
import com.imuons.pmcindia.R;
import com.imuons.pmcindia.ResponseModel.UpdateProfileResponse;
import com.imuons.pmcindia.ResponseModel.UserProfileDataModel;
import com.imuons.pmcindia.ResponseModel.UserProfileResponse;
import com.imuons.pmcindia.fragments.ProfileFragment;
import com.imuons.pmcindia.retrofit.AppService;
import com.imuons.pmcindia.retrofit.ServiceGenerator;
import com.imuons.pmcindia.utils.AppCommon;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BankDetailsActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.tv_Info)
    TextView tv_Info;
    @BindView(R.id.tv_editProfile)
    TextView tv_editProfile;
    @BindView(R.id.tv_bank_Details)
    TextView tv_bank_Details;
    @BindView(R.id.tv_change_Password)
    TextView tv_change_Password;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;


    @BindView(R.id.mAccountNo)
    EditText mAccountNo;
    @BindView(R.id.mAccountHolderName)
    EditText mAccountHolderName;
    @BindView(R.id.mBankName)
    EditText mBankName;
    @BindView(R.id.mBranchName)
    EditText mBranchName;
    @BindView(R.id.mIFSCCode)
    EditText mIFSCCode;
    @BindView(R.id.mPan)
    EditText mPan;

    //button
    @BindView(R.id.update)
    TextView update;

    String account_no, bank_name, branch_name, btc, holder_name, ifsc_code, pan_no, status, withdraw_type, email, mobile, userid, withdrawtype;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_details);
        ButterKnife.bind(this);
        registerListeners();
        getUserProfileInfo();
        tv_Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = ProfileFragment.newInstance();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.addToBackStack(null);
                transaction.replace(R.id.container_layout, fragment).commit();


            }
        });
    }

    public void backBtn(View view) {
        onBackPressed();
    }


    @OnClick(R.id.update)
    void update() {
        account_no = mAccountNo.getText().toString().trim();
        holder_name = mAccountHolderName.getText().toString().trim();
        bank_name = mBankName.getText().toString().trim();
        branch_name = mBranchName.getText().toString().trim();
        ifsc_code = mIFSCCode.getText().toString().trim();
        pan_no = mPan.getText().toString().trim();


        callUpdateApi(new UserInfoEntity(account_no, bank_name, branch_name, btc, email, holder_name, ifsc_code, mobile, pan_no, status, withdraw_type));

    }

    private void callUpdateApi(UserInfoEntity userInfoEntity) {

        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            progressBar.setVisibility(View.VISIBLE);
            Call call = apiService.UpdateProfile(userInfoEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(BankDetailsActivity.this).clearNonTouchableFlags(BankDetailsActivity.this);
                    progressBar.setVisibility(View.GONE);
                    UpdateProfileResponse authResponse = (UpdateProfileResponse) response.body();
                    if (authResponse != null) {
                        Log.i("editprofile::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            if (authResponse.getData() != null)
                                Toast.makeText(BankDetailsActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            getUserProfileInfo();

                        } else {
                            Toast.makeText(BankDetailsActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(BankDetailsActivity.this).showDialog(BankDetailsActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppCommon.getInstance(BankDetailsActivity.this).clearNonTouchableFlags(BankDetailsActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(BankDetailsActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }

    private void getUserProfileInfo() {
        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            progressBar.setVisibility(View.VISIBLE);
            Call call = apiService.UserInfo();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(BankDetailsActivity.this).clearNonTouchableFlags(BankDetailsActivity.this);
                    progressBar.setVisibility(View.GONE);
                    UserProfileResponse authResponse = (UserProfileResponse) response.body();
                    if (authResponse != null) {
                        Log.i("UserDetails::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setData(authResponse.getData());
                        } else {
                            Toast.makeText(BankDetailsActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(BankDetailsActivity.this).showDialog(BankDetailsActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppCommon.getInstance(BankDetailsActivity.this).clearNonTouchableFlags(BankDetailsActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(BankDetailsActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }

    private void setData(UserProfileDataModel data) {
        mAccountNo.setText(data.getAccountNo());
        mBankName.setText(data.getBankName());
        mAccountHolderName.setText(data.getHolderName());
        mBranchName.setText(data.getBranchName());
        mIFSCCode.setText(data.getIfscCode());
        mPan.setText(data.getPanNo());
        userid = data.getUserId();
        mobile = data.getMobile();
        email = data.getEmail();
        btc = data.getBtcAddress();
        withdraw_type = data.getWithdrawType();
        status = "1";

    }

    private void registerListeners() {
        tv_Info.setOnClickListener(this);
        tv_editProfile.setOnClickListener(this);
        tv_change_Password.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;


        switch (v.getId()) {

            case R.id.tv_editProfile:
                intent = new Intent(BankDetailsActivity.this,
                        EditProfileActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.tv_change_Password:
                intent = new Intent(BankDetailsActivity.this,
                        ChangePasswordActivity.class);
                startActivity(intent);
                finish();
                break;

            default:
                break;
        }
    }
}
