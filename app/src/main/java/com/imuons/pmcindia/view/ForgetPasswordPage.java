package com.imuons.pmcindia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.imuons.pmcindia.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgetPasswordPage extends AppCompatActivity {

    @BindView(R.id.edit_password)
    EditText edit_password;
    @BindView(R.id.edit_cpassword)
    EditText edit_cpassword;
    @BindView(R.id.reset)
    TextView reset;
    @BindView(R.id.tvSignup)
    TextView tvSignup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_page);
        ButterKnife.bind(this);

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent (ForgetPasswordPage.this,SignupActivity.class);
                startActivity(login);

            }
        });
    }
}
