package com.imuons.pmcindia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.imuons.pmcindia.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ForgetPassword extends AppCompatActivity {

    @BindView(R.id.mEditUserid)
    EditText mEditUserid;
    @BindView(R.id.submit)
    TextView submit;
    @BindView(R.id.tvSignup)
    TextView tvSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        ButterKnife.bind(this);

        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent (ForgetPassword.this,SignupActivity.class);
                startActivity(login);

            }
        });
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

              //  verifyOTP(YouEditTextValue);
            }
        });

        alert.show();
    }

}
