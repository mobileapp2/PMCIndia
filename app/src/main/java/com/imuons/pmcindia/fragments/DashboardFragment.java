package com.imuons.pmcindia.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imuons.pmcindia.R;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        ButterKnife.bind(this, view);


        return view;
    }
}
