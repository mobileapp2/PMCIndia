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
import com.imuons.pmcindia.Entity.ChangePasswordEntity;
import com.imuons.pmcindia.R;
import com.imuons.pmcindia.ResponseModel.ChangePasswordResponseModel;
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

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {


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

    @BindView(R.id.tv_OldPassword)
    EditText tv_OldPassword;
    @BindView(R.id.tv_NewPassword)
    EditText tv_NewPassword;
    @BindView(R.id.tv_reEnterPassword)
    EditText tv_reEnterPassword;

    //button
    @BindView(R.id.changePassword)
    TextView changePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        registerListeners();
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


    @OnClick(R.id.changePassword)
    void change() {
        String old_password = tv_OldPassword.getText().toString().trim();
        String new_password = tv_NewPassword.getText().toString().trim();
        String confirm_password = tv_reEnterPassword.getText().toString().trim();


        callChangePassApi(new ChangePasswordEntity(confirm_password, old_password, new_password));

    }

    private void callChangePassApi(ChangePasswordEntity changePasswordEntity) {

        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class);
            progressBar.setVisibility(View.VISIBLE);
            Call call = apiService.ChangePassword(changePasswordEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(ChangePasswordActivity.this).clearNonTouchableFlags(ChangePasswordActivity.this);
                    progressBar.setVisibility(View.GONE);
                    ChangePasswordResponseModel authResponse = (ChangePasswordResponseModel) response.body();
                    if (authResponse != null) {
                        Log.i("editprofile::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            if (authResponse.getData() != null)
                                Toast.makeText(ChangePasswordActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            tv_OldPassword.setText("");
                            tv_NewPassword.setText("");
                            tv_reEnterPassword.setText("");

                        } else {
                            Toast.makeText(ChangePasswordActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(ChangePasswordActivity.this).showDialog(ChangePasswordActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppCommon.getInstance(ChangePasswordActivity.this).clearNonTouchableFlags(ChangePasswordActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(ChangePasswordActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }

    private void registerListeners() {

        tv_editProfile.setOnClickListener(this);
        tv_bank_Details.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;


        switch (v.getId()) {

            case R.id.tv_editProfile:
                intent = new Intent(ChangePasswordActivity.this,
                        EditProfileActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tv_bank_Details:
                intent = new Intent(ChangePasswordActivity.this,
                        BankDetailsActivity.class);
                startActivity(intent);
                finish();
                break;

            default:
                break;
        }
    }
}
