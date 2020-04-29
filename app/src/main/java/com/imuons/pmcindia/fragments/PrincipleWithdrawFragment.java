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
public class PrincipleWithdrawFragment extends Fragment {

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
}
