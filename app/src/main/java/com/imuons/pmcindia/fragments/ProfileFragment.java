package com.imuons.pmcindia.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imuons.pmcindia.R;
import com.imuons.pmcindia.ResponseModel.RendomNumberResponse;
import com.imuons.pmcindia.ResponseModel.UserProfileDataModel;
import com.imuons.pmcindia.ResponseModel.UserProfileResponse;
import com.imuons.pmcindia.retrofit.AppService;
import com.imuons.pmcindia.retrofit.ServiceGenerator;
import com.imuons.pmcindia.utils.AppCommon;
import com.imuons.pmcindia.view.BankDetailsActivity;
import com.imuons.pmcindia.view.ChangePasswordActivity;
import com.imuons.pmcindia.view.EditProfileActivity;
import com.imuons.pmcindia.view.SignupActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.tv_Info)
    TextView tv_Info;
    @BindView(R.id.tv_editProfile)
    TextView tv_editProfile;
    @BindView(R.id.tv_bank_Details)
    TextView tv_bank_Details;
    @BindView(R.id.tv_change_Password)
    TextView tv_change_Password;


    @BindView(R.id.tv_userId)
    TextView tv_userId;
    @BindView(R.id.tv_sponsorId)
    TextView tv_sponsorId;
    @BindView(R.id.tv_emailId)
    TextView tv_emailId;
    @BindView(R.id.tv_MobileNo)
    TextView tv_MobileNo;
    @BindView(R.id.tv_btcAddress)
    TextView tv_btcAddress;
    @BindView(R.id.tv_withdrawType)
    TextView tv_withdrawType;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        registerListeners();
        getUserProfileInfo();
        return view;
    }

    private void getUserProfileInfo() {
        if (AppCommon.getInstance(getActivity()).isConnectingToInternet(getActivity())) {
            AppCommon.getInstance(getActivity()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class);
            progressBar.setVisibility(View.VISIBLE);
            Call call = apiService.UserInfo();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(ProfileFragment.this.getActivity()).clearNonTouchableFlags(ProfileFragment.this.getActivity());
                    progressBar.setVisibility(View.GONE);
                    UserProfileResponse authResponse = (UserProfileResponse) response.body();
                    if (authResponse != null) {
                        Log.i("UserDetails::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setData(authResponse.getData());
                        } else {
                            Toast.makeText(ProfileFragment.this.getActivity(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(ProfileFragment.this.getActivity()).showDialog(ProfileFragment.this.getActivity(), "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppCommon.getInstance(ProfileFragment.this.getActivity()).clearNonTouchableFlags(ProfileFragment.this.getActivity());
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(ProfileFragment.this.getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }

    private void setData(UserProfileDataModel data) {
        tv_userId.setText(data.getUserId());
        tv_sponsorId.setText(data.getSponser());
        tv_MobileNo.setText(data.getMobile());
        tv_emailId.setText(data.getEmail());
        tv_btcAddress.setText(data.getBtcAddress());
        tv_withdrawType.setText(data.getWithdrawType());
    }

    private void registerListeners() {
        tv_Info.setOnClickListener(this);
        tv_editProfile.setOnClickListener(this);
        tv_bank_Details.setOnClickListener(this);
        tv_change_Password.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        Gson gS = new Gson();

        switch (v.getId()) {

            case R.id.tv_editProfile:
                intent = new Intent(ProfileFragment.this.getContext(),
                        EditProfileActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_bank_Details:
                intent = new Intent(ProfileFragment.this.getContext(),
                        BankDetailsActivity.class);
                startActivity(intent);

                break;
            case R.id.tv_change_Password:
                intent = new Intent(ProfileFragment.this.getContext(),
                        ChangePasswordActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }
}
