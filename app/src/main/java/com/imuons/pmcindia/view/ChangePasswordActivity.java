package com.imuons.pmcindia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.imuons.pmcindia.R;
import com.imuons.pmcindia.fragments.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {


    @BindView(R.id.tv_Info)
    TextView tv_Info;
    @BindView(R.id.tv_editProfile)
    TextView tv_editProfile;
    @BindView(R.id.tv_bank_Details)
    TextView tv_bank_Details;
    @BindView(R.id.tv_change_Password)
    TextView tv_change_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        ButterKnife.bind(this);
        registerListeners();
    }
        private void registerListeners () {
            tv_Info.setOnClickListener(this);
            tv_editProfile.setOnClickListener(this);
            tv_bank_Details.setOnClickListener(this);

        }
    
        @Override
        public void onClick (View v){
            Intent intent = null;


            switch (v.getId()) {
                case R.id.tv_Info:
                    intent = new Intent(ChangePasswordActivity.this,
                            ProfileFragment.class);
                    startActivity(intent);
                    break;
                case R.id.tv_editProfile:
                    intent = new Intent(ChangePasswordActivity.this,
                            EditProfileActivity.class);
                    startActivity(intent);
                    break;
                case R.id.tv_bank_Details:
                    intent = new Intent(ChangePasswordActivity.this,
                            BankDetailsActivity.class);
                    startActivity(intent);
                    break;

                default:
                    break;
            }
        }
    }
