package com.imuons.pmcindia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.imuons.pmcindia.R;
import com.imuons.pmcindia.utils.SharedPreferenceUtils;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(5 * 1000);
                    String token = SharedPreferenceUtils.getAccesstoken(SplashScreen.this);

                    if (token == null) {
                        startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                    } else {
                        startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                    }


                } catch (Exception e) {

                }
            }
        };
        thread.start();

    }
}
