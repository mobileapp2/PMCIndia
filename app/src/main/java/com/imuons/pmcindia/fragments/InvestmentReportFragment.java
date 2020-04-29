package com.imuons.pmcindia.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imuons.pmcindia.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class InvestmentReportFragment extends Fragment {

    public InvestmentReportFragment() {
        // Required empty public constructor
    }

    public static InvestmentReportFragment newInstance() {
        InvestmentReportFragment fragment = new InvestmentReportFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_investment_report, container, false);
        return view;
    }
}
