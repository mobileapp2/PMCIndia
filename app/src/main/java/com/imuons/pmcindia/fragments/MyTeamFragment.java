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
public class MyTeamFragment extends Fragment {

    public MyTeamFragment() {
        // Required empty public constructor
    }

    public static MyTeamFragment newInstance() {
        MyTeamFragment fragment = new MyTeamFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_team, container, false);
        return view;
    }
}
