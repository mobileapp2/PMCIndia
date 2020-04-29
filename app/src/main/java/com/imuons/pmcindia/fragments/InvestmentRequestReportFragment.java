package com.imuons.pmcindia.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imuons.pmcindia.R;

import butterknife.ButterKnife;


public class InvestmentRequestReportFragment extends Fragment {

    public InvestmentRequestReportFragment() {
        // Required empty public constructor
    }

    public static InvestmentRequestReportFragment newInstance() {
        InvestmentRequestReportFragment fragment = new InvestmentRequestReportFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_investment_request_report, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
