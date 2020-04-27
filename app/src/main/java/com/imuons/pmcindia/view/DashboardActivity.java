package com.imuons.pmcindia.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imuons.pmcindia.R;
import com.imuons.pmcindia.ResponseModel.DashboardResponseModel;
import com.imuons.pmcindia.ResponseModel.RendomNumberResponse;
import com.imuons.pmcindia.retrofit.AppService;
import com.imuons.pmcindia.retrofit.ServiceGenerator;
import com.imuons.pmcindia.utils.AppCommon;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
       // callDashBoardApi();
    }

    private void callDashBoardApi() {

        if (AppCommon.getInstance(this).isConnectingToInternet(this)) {
            AppCommon.getInstance(this).setNonTouchableFlags(this);
            AppService apiService = ServiceGenerator.createService(AppService.class , AppCommon.getInstance(this).getToken());
          //  progressBar.setVisibility(View.VISIBLE);
            Call call = apiService.GetDashBoard();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(DashboardActivity.this).clearNonTouchableFlags(DashboardActivity.this);
                   // progressBar.setVisibility(View.GONE);
                    DashboardResponseModel authResponse = (DashboardResponseModel) response.body();
                    if (authResponse != null) {
                        Log.i("DashboardResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                        } else {
                            Toast.makeText(DashboardActivity.this, authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(DashboardActivity.this).showDialog(DashboardActivity.this, "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                   // progressBar.setVisibility(View.GONE);
                    AppCommon.getInstance(DashboardActivity.this).clearNonTouchableFlags(DashboardActivity.this);
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(DashboardActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
          //  progressBar.setVisibility(View.GONE);
            Toast.makeText(this, "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }
}
