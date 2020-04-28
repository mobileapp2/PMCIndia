package com.imuons.pmcindia.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.imuons.pmcindia.R;
import com.imuons.pmcindia.view.BankDetailsActivity;
import com.imuons.pmcindia.view.ChangePasswordActivity;
import com.imuons.pmcindia.view.EditProfileActivity;

import butterknife.BindView;
import butterknife.ButterKnife;


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
        return view;
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
