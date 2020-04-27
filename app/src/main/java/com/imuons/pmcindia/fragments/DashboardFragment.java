package com.imuons.pmcindia.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imuons.pmcindia.DataModel.DashBoardData;
import com.imuons.pmcindia.R;
import com.imuons.pmcindia.ResponseModel.DashboardResponseModel;
import com.imuons.pmcindia.retrofit.AppService;
import com.imuons.pmcindia.retrofit.ServiceGenerator;
import com.imuons.pmcindia.utils.AppCommon;
import com.imuons.pmcindia.view.DashboardActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    @BindView(R.id.team)
    TextView team;
    @BindView(R.id.totalDirect)
    TextView totalDirect;
    @BindView(R.id.roiIncome)
    TextView roiIncome;
    @BindView(R.id.tv_directIncome)
    TextView tv_directIncome;
    @BindView(R.id.tv_investment)
    TextView tv_investment;
    @BindView(R.id.tv_winning)
    TextView tv_winning;
    @BindView(R.id.roitotalWithdrawal)
    TextView roitotalWithdrawal;


    public DashboardFragment() {
        // Required empty public constructor
    }

    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ButterKnife.bind(this ,view);
        callDashBoardApi();

        return view;
    }

    private void callDashBoardApi() {

        if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
            AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
            //  progressBar.setVisibility(View.VISIBLE);
            Call call = apiService.GetDashBoard();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(getContext()).clearNonTouchableFlags(getActivity());
                    // progressBar.setVisibility(View.GONE);
                    DashboardResponseModel authResponse = (DashboardResponseModel) response.body();
                    if (authResponse != null) {
                        Log.i("DashboardResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setData(authResponse.getData());
                        } else {
                            Toast.makeText(getContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(getContext()).showDialog(getActivity(), "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    // progressBar.setVisibility(View.GONE);
                    AppCommon.getInstance(getContext()).clearNonTouchableFlags(getActivity());
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(getContext(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            //  progressBar.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(DashBoardData data) {
        team.setText(String.valueOf(data.getTotalTeam()));
        totalDirect.setText(String.valueOf(data.getTotalDirects()));
        roiIncome.setText(String.valueOf(data.getRoiIncome()));
        tv_directIncome.setText(String.valueOf(data.getDirectRefIncome()));
        tv_investment.setText(String.valueOf(data.getTotalInvestment()));
        tv_winning.setText(String.valueOf(data.getWinningIncome()));
        roitotalWithdrawal.setText(String.valueOf(data.getTotalWithdraw()));
    }
}
