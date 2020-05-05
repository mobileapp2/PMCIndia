package com.imuons.pmcindia.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imuons.pmcindia.Entity.WithdrawAmountEntity;
import com.imuons.pmcindia.R;
import com.imuons.pmcindia.ResponseModel.MakeWithdrawDataModel;
import com.imuons.pmcindia.ResponseModel.MakeWithdrawResponseModel;
import com.imuons.pmcindia.ResponseModel.PincipleDataModel;
import com.imuons.pmcindia.ResponseModel.PrincipleWithdrawResponse;
import com.imuons.pmcindia.ResponseModel.WithdrawAmountResponse;
import com.imuons.pmcindia.retrofit.AppService;
import com.imuons.pmcindia.retrofit.ServiceGenerator;
import com.imuons.pmcindia.utils.AppCommon;
import com.imuons.pmcindia.utils.SharedPreferenceUtils;
import com.imuons.pmcindia.utils.Utils;
import com.imuons.pmcindia.view.DashboardActivity;
import com.imuons.pmcindia.view.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class PrincipleWithdrawFragment extends Fragment {
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.tv_Balance)
    TextView tv_Balance;
    @BindView(R.id.amount)
    EditText tv_amount;

    //button
    @BindView(R.id.withdraw)
    TextView withdraw;

    int amount = 0;

    public PrincipleWithdrawFragment() {
        // Required empty public constructor
    }

    public static PrincipleWithdrawFragment newInstance() {
        PrincipleWithdrawFragment fragment = new PrincipleWithdrawFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_principle_withdraw, container, false);
        ButterKnife.bind(this, view);
        return view;

    }

    @OnClick(R.id.withdraw)
    void withdraw() {

        if (amount > 0) {
            showAlertDialog();
        } else {
            Toast.makeText(PrincipleWithdrawFragment.this.getActivity(), "Amount is 0 you can't withdraw", Toast.LENGTH_SHORT).show();
        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder1 = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            builder1 = new AlertDialog.Builder(PrincipleWithdrawFragment.this.getActivity(), AlertDialog.THEME_HOLO_LIGHT);
        }
        builder1.setTitle("Alert");
        builder1.setMessage("Are you sure you want to Withdraw ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        //call withdraw Api
                        callWithdrawApi(new WithdrawAmountEntity("0", "withdraw", 0, 0, 0, 0, amount, 0, 0, 0));

                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


    private void callWithdrawApi(WithdrawAmountEntity withdrawAmountEntity) {
        if (AppCommon.getInstance(PrincipleWithdrawFragment.this.getActivity()).isConnectingToInternet(PrincipleWithdrawFragment.this.getActivity())) {
            AppCommon.getInstance(PrincipleWithdrawFragment.this.getActivity()).setNonTouchableFlags(PrincipleWithdrawFragment.this.getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class);
            progressBar.setVisibility(View.VISIBLE);
            Call call = apiService.WithdrawAmount(withdrawAmountEntity);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(PrincipleWithdrawFragment.this.getActivity()).clearNonTouchableFlags(PrincipleWithdrawFragment.this.getActivity());
                    progressBar.setVisibility(View.GONE);
                    WithdrawAmountResponse authResponse = (WithdrawAmountResponse) response.body();
                    if (authResponse != null) {
                        Log.i("withdraw::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            if (authResponse.getData() != null)
                                Toast.makeText(PrincipleWithdrawFragment.this.getActivity(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                            getBalanceInfo();

                        } else {
                            Toast.makeText(PrincipleWithdrawFragment.this.getActivity(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(PrincipleWithdrawFragment.this.getActivity()).showDialog(PrincipleWithdrawFragment.this.getActivity(), "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppCommon.getInstance(PrincipleWithdrawFragment.this.getActivity()).clearNonTouchableFlags(PrincipleWithdrawFragment.this.getActivity());
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(PrincipleWithdrawFragment.this.getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            progressBar.setVisibility(View.GONE);
            Toast.makeText(PrincipleWithdrawFragment.this.getActivity(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    public void onResume() {
        super.onResume();
        if (Utils.checkInternetConnection(PrincipleWithdrawFragment.this.getContext())) {

            getBalanceInfo();
        } else {

        }
    }

    private void getBalanceInfo() {
        if (AppCommon.getInstance(getActivity()).isConnectingToInternet(getActivity())) {
            AppCommon.getInstance(getActivity()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class);
            progressBar.setVisibility(View.VISIBLE);
            Call call = apiService.PrincipleWithdraw();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(PrincipleWithdrawFragment.this.getActivity()).clearNonTouchableFlags(PrincipleWithdrawFragment.this.getActivity());
                    progressBar.setVisibility(View.GONE);
                    PrincipleWithdrawResponse authResponse = (PrincipleWithdrawResponse) response.body();
                    if (authResponse != null) {
                        Log.i("Balance::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setBalance(authResponse.getData());
                        } else {
                            Toast.makeText(PrincipleWithdrawFragment.this.getActivity(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(PrincipleWithdrawFragment.this.getActivity()).showDialog(PrincipleWithdrawFragment.this.getActivity(), "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppCommon.getInstance(PrincipleWithdrawFragment.this.getActivity()).clearNonTouchableFlags(PrincipleWithdrawFragment.this.getActivity());
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(PrincipleWithdrawFragment.this.getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }

    private void setBalance(PincipleDataModel data) {
        tv_Balance.setText("\u20B9"+String.valueOf(data.getPrincipleWalletBalance()));
        tv_amount.setText(String.valueOf(data.getPrincipleWalletBalance()));
        amount = data.getPrincipleWalletBalance();
    }


}
