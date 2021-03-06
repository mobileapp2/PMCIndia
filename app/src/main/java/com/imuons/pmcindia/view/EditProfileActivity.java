package com.imuons.pmcindia.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.gson.Gson;
import com.imuons.pmcindia.Entity.UserInfoEntity;
import com.imuons.pmcindia.R;
import com.imuons.pmcindia.ResponseModel.BTCAddressResponseModel;
import com.imuons.pmcindia.ResponseModel.UpdateProfileResponse;
import com.imuons.pmcindia.ResponseModel.UserProfileDataModel;
import com.imuons.pmcindia.ResponseModel.UserProfileResponse;
import com.imuons.pmcindia.fragments.ProfileFragment;
import com.imuons.pmcindia.retrofit.AppService;
import com.imuons.pmcindia.retrofit.ServiceGenerator;
import com.imuons.pmcindia.utils.AppCommon;
import com.imuons.pmcindia.utils.Utils;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity implements View.OnClickListener {

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

    boolean sid = false;
    @BindView(R.id.tv_userId)
    EditText tv_userId;

    @BindView(R.id.tv_emailId)
    EditText tv_emailId;
    @BindView(R.id.tv_MobileNo)
    EditText tv_MobileNo;
    @BindView(R.id.tv_btcAddress)
    EditText tv_btcAddress;
    @BindView(R.id.tv_withdrawType)
    TextView tv_withdrawType;
    @BindView(R.id.radio_group)
    RadioGroup radio_group;
    String btc_validation;
    @BindView(R.id.update)
    TextView update;
    String account_no, bank_name, branch_name, btc, holder_name, ifsc_code, pan_no, status, withdraw_type, Type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        registerListeners();
        getUserProfileInfo();


        tv_Info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = ProfileFragment.newInstance();

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.container_layout, fragment).commit();
                transaction.disallowAddToBackStack();

            }
        });

        tv_btcAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (tv_btcAddress != null && tv_btcAddress.length() > 0) {
                    checkBtc();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void checkBtc() {

        Map<String, String> checkuserexistsMap = new HashMap<>();
        String btcAdd = tv_btcAddress.getText().toString().trim();

        checkuserexistsMap.put("network_type", "BTC");
        checkuserexistsMap.put("address", btcAdd);

        AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(EditProfileActivity.this).getToken());
        final Call<BTCAddressResponseModel> loginCall = apiService.wsCheckBTCAddress(checkuserexistsMap);
        loginCall.enqueue(new Callback<BTCAddressResponseModel>() {
            @SuppressLint("WrongConstant")
            @Override
            public void onResponse(Call<BTCAddressResponseModel> call,
                                   Response<BTCAddressResponseModel> response) {
                //  pd.hide();
                if (response.isSuccessful()) {
                    BTCAddressResponseModel checkUserExistResponseModel = response.body();

                    if(checkUserExistResponseModel.getCode()==200){
                        sid = true;
                        tv_btcAddress.setError(null);
                        btc_validation = checkUserExistResponseModel.getMessage();
                    }if (checkUserExistResponseModel.getCode()==404){
                        tv_btcAddress.setError("Bitcoin address is not valid");
                    }

                }
            }

            @Override
            public void onFailure(Call<BTCAddressResponseModel> call,
                                  Throwable t) {
                // pd.hide();
            //    Toast.makeText(EditProfileActivity.this, getString(R.string.something_went_wrong), Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void backBtn(View view) {
        onBackPressed();
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        // Check which radio button was clicked
        switch (view.getId()) {
            case R.id.radioBTC:
                if (checked)
                    withdraw_type = "BTC";
                break;
            case R.id.radioINR:
                if (checked)
                    withdraw_type = "INR";
                break;
        }
        tv_withdrawType.setText(withdraw_type);
    }

    @OnClick(R.id.update)
    void update() {
        String mobile = tv_MobileNo.getText().toString().trim();
        String email = tv_emailId.getText().toString().trim();
        btc = tv_btcAddress.getText().toString().trim();
        String w_Type = tv_withdrawType.getText().toString().trim();

        callUpdateApi(new UserInfoEntity(account_no, bank_name, branch_name, tv_btcAddress.getText().toString().trim(), email, holder_name, ifsc_code, mobile, pan_no, status, w_Type));

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
                    AppCommon.getInstance(EditProfileActivity.this).clearNonTouchableFlags(EditProfileActivity.this);
                    progressBar.setVisibility(View.GONE);
                    UpdateProfileResponse authResponse = (UpdateProfileResponse) response.body();
                    if (authResponse != null) {
                        Log.i("editprofile::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            if (authResponse.getData() != null)
                                Toast.makeText(EditProfileActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            getUserProfileInfo();

                        } else {
                            Toast.makeText(EditProfileActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(EditProfileActivity.this).showDialog(EditProfileActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppCommon.getInstance(EditProfileActivity.this).clearNonTouchableFlags(EditProfileActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(EditProfileActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
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
                    AppCommon.getInstance(EditProfileActivity.this).clearNonTouchableFlags(EditProfileActivity.this);
                    progressBar.setVisibility(View.GONE);
                    UserProfileResponse authResponse = (UserProfileResponse) response.body();
                    if (authResponse != null) {
                        Log.i("UserDetails::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setData(authResponse.getData());
                        } else {
                            Toast.makeText(EditProfileActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(EditProfileActivity.this).showDialog(EditProfileActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppCommon.getInstance(EditProfileActivity.this).clearNonTouchableFlags(EditProfileActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(EditProfileActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });

        } else {
            // no internet
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }

    private void setData(UserProfileDataModel data) {
        tv_userId.setText(data.getUserId());

        tv_MobileNo.setText(data.getMobile());
        tv_emailId.setText(data.getEmail());
        tv_btcAddress.setText(data.getBtcAddress());
        tv_withdrawType.setText(data.getWithdrawType());

        account_no = data.getAccountNo();
        bank_name = data.getBankName();
        holder_name = data.getHolderName();
        btc = data.getBtcAddress();
        ifsc_code = data.getIfscCode();
        pan_no = data.getPanNo();
        status = "1";
        Type = data.getWithdrawType();
        if (data.getWithdrawType().equals("BTC")) {
            radio_group.check(R.id.radioBTC);
        } else {
            radio_group.check(R.id.radioINR);
        }
        if (data.getBtcAddress() == null || data.getBtcAddress().isEmpty()) {
            tv_btcAddress.setFocusable(true);
            tv_btcAddress.setFocusableInTouchMode(true);
            tv_btcAddress.setClickable(true);
        } else {
            tv_btcAddress.setFocusable(false);
            tv_btcAddress.setFocusableInTouchMode(false);
            tv_btcAddress.setClickable(false);
        }

    }

    private void registerListeners() {
        tv_Info.setOnClickListener(this);
        tv_bank_Details.setOnClickListener(this);
        tv_change_Password.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;


        switch (v.getId()) {

            case R.id.tv_bank_Details:
                intent = new Intent(EditProfileActivity.this,
                        BankDetailsActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_change_Password:
                intent = new Intent(EditProfileActivity.this,
                        ChangePasswordActivity.class);
                startActivity(intent);
                finish();
                break;

            default:
                break;
        }
    }

    private boolean validateBTC() {
        String strreferalid = tv_btcAddress.getText().toString().trim();
        if (strreferalid.isEmpty() || sid == false) {
            Toast.makeText(getApplicationContext(), getString(R.string.empty_btc_address), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (Utils.checkInternetConnection(EditProfileActivity.this.getApplicationContext())) {

            getUserProfileInfo();
        } else {

        }
    }

}
