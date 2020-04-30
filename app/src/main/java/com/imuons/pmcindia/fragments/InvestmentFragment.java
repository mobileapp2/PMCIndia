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
public class InvestmentFragment extends Fragment {

    public InvestmentFragment() {
        // Required empty public constructor
    }

    public static InvestmentFragment newInstance() {
        InvestmentFragment fragment = new InvestmentFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_investment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }
}
