package com.imuons.pmcindia.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imuons.pmcindia.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class EstimateAmountReportFragment extends Fragment {

    public EstimateAmountReportFragment() {
        // Required empty public constructor
    }
    public static EstimateAmountReportFragment newInstance() {
        EstimateAmountReportFragment fragment = new EstimateAmountReportFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_estimate_amount_report, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
