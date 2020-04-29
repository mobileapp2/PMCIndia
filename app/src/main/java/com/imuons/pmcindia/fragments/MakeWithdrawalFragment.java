package com.imuons.pmcindia.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imuons.pmcindia.R;
import com.imuons.pmcindia.ResponseModel.MakeWithdrawDataModel;
import com.imuons.pmcindia.ResponseModel.MakeWithdrawResponseModel;
import com.imuons.pmcindia.ResponseModel.UserProfileResponse;
import com.imuons.pmcindia.retrofit.AppService;
import com.imuons.pmcindia.retrofit.ServiceGenerator;
import com.imuons.pmcindia.utils.AppCommon;
import com.imuons.pmcindia.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MakeWithdrawalFragment extends Fragment {

    @BindView(R.id.tv_Balance)
    TextView tv_Balance;
    @BindView(R.id.amount)
    EditText tv_amount;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    //button
    @BindView(R.id.withdraw)
    TextView withdraw;

    public MakeWithdrawalFragment() {
        // Required empty public constructor
    }

    public static MakeWithdrawalFragment newInstance() {
        MakeWithdrawalFragment fragment = new MakeWithdrawalFragment();

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_make_withdrawal, container, false);
        ButterKnife.bind(this, view);
        getBalanceInfo();
        return view;
    }

    @OnClick(R.id.withdraw)
    void withdraw() {
        String amount = tv_amount.getText().toString().trim();



    }

    private void getBalanceInfo() {
        if (AppCommon.getInstance(getActivity()).isConnectingToInternet(getActivity())) {
            AppCommon.getInstance(getActivity()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class);
            progressBar.setVisibility(View.VISIBLE);
            Call call = apiService.MakeWithdraw();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(MakeWithdrawalFragment.this.getActivity()).clearNonTouchableFlags(MakeWithdrawalFragment.this.getActivity());
                    progressBar.setVisibility(View.GONE);
                    MakeWithdrawResponseModel authResponse = (MakeWithdrawResponseModel) response.body();
                    if (authResponse != null) {
                        Log.i("Balance::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setBalance(authResponse.getData());
                        } else {
                            Toast.makeText(MakeWithdrawalFragment.this.getActivity(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(MakeWithdrawalFragment.this.getActivity()).showDialog(MakeWithdrawalFragment.this.getActivity(), "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppCommon.getInstance(MakeWithdrawalFragment.this.getActivity()).clearNonTouchableFlags(MakeWithdrawalFragment.this.getActivity());
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(MakeWithdrawalFragment.this.getActivity(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            progressBar.setVisibility(View.GONE);
            Toast.makeText(getActivity(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }

    }

    private void setBalance(MakeWithdrawDataModel data) {

        tv_Balance.setText(String.valueOf(data.getBalance()));

    }

    @Override
    public void onResume() {
        super.onResume();
        if (Utils.checkInternetConnection(MakeWithdrawalFragment.this.getContext())) {

            getBalanceInfo();
        } else {
            Toast.makeText(MakeWithdrawalFragment.this.getContext(),
                    getString(R.string.no_internet_connection_message), Toast.LENGTH_SHORT).show();
        }
    }
}
