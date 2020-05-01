package com.imuons.pmcindia.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imuons.pmcindia.DataModel.LevelData;
import com.imuons.pmcindia.DataModel.MyTeanData;
import com.imuons.pmcindia.DataModel.RecordData;
import com.imuons.pmcindia.Entity.MyTeamEntity;
import com.imuons.pmcindia.R;
import com.imuons.pmcindia.ResponseModel.LevelResponse;
import com.imuons.pmcindia.ResponseModel.MyTeamResponse;
import com.imuons.pmcindia.ResponseModel.RendomNumberResponse;
import com.imuons.pmcindia.adapters.MyTeamAdapter;
import com.imuons.pmcindia.retrofit.AppService;
import com.imuons.pmcindia.retrofit.ServiceGenerator;
import com.imuons.pmcindia.utils.AppCommon;
import com.imuons.pmcindia.view.SignupActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyTeamFragment extends Fragment {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.bottomProgressBar)
    ProgressBar bottomProgressBar;

    @BindView(R.id.spinnerLevel)
    Spinner spinnerLevel;
    @BindView(R.id.recycler_view_team_view)
    RecyclerView recyclerView;
    @BindView(R.id.searchbyid)
    EditText searchbyid;


    ArrayAdapter<LevelData> adapter;
    ArrayList<LevelData> levelDataArrayList;
    ArrayList<RecordData> records;
    MyTeamAdapter myteamAdapter;
    int level = 1;
    int offsetLevel = 0;


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
        ButterKnife.bind(this, view);
        levelDataArrayList = new ArrayList<>();
        records = new ArrayList<>();
        myteamAdapter = new MyTeamAdapter(getContext(), records, MyTeamFragment.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(myteamAdapter);

        searchbyid.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if (levelDataArrayList.size() != 0) {
                        progressBar.setVisibility(View.VISIBLE);
                        records.clear();
                        offsetLevel = 0;
                        callgetLevelView(0, 0 , s.toString());


                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        spinnerLevel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (levelDataArrayList.size() != 0) {
                    level = levelDataArrayList.get(position).getLevelId();
                    progressBar.setVisibility(View.VISIBLE);
                    records.clear();
                    offsetLevel = 0;
                    callgetLevelView(position, 0 ,"");


                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        callGetLevel();

        return view;
    }

    private void callgetLevelView(int position, int start , String searchTxt) {
        boolean isupdate = false;
        if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
            AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
            if (bottomProgressBar.getVisibility() != View.VISIBLE) {
                isupdate = true;
            }else {
                isupdate = false;
            }
            Map<String, String> roiMap = new HashMap<>();

            roiMap.put("start", String.valueOf(start));
            roiMap.put("length", "10");
            roiMap.put("level_id", String.valueOf(level));
            roiMap.put("search[value]", searchTxt);
           // Call call = apiService.getLevelView(new MyTeamEntity(start, 10, level));
            Call call = apiService.getLevelView(roiMap);
            boolean finalIsupdate = isupdate;
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(MyTeamFragment.this.getContext()).clearNonTouchableFlags(MyTeamFragment.this.getActivity());
                    if (bottomProgressBar.getVisibility() != View.VISIBLE)
                        progressBar.setVisibility(View.GONE);
                    else
                        bottomProgressBar.setVisibility(View.GONE);
                    MyTeamResponse authResponse = (MyTeamResponse) response.body();
                    if (authResponse != null) {
                        Log.i("MyTeamResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setMyTeamData(authResponse.getData());
                        } else {
                            if(records.size() == 0)
                                myteamAdapter.upDateList(records , 0);
                            /*if (finalIsupdate)
                            Toast.makeText(MyTeamFragment.this.getContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();*/
                        }
                    } else {
                        if (finalIsupdate)
                            AppCommon.getInstance(MyTeamFragment.this.getContext()).showDialog(MyTeamFragment.this.getActivity(), "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    if (bottomProgressBar.getVisibility() != View.VISIBLE)
                        progressBar.setVisibility(View.GONE);
                    else
                        bottomProgressBar.setVisibility(View.GONE);
                    AppCommon.getInstance(MyTeamFragment.this.getContext()).clearNonTouchableFlags(MyTeamFragment.this.getActivity());
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(MyTeamFragment.this.getContext(), "Data Not Found", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            if (bottomProgressBar.getVisibility() == View.VISIBLE)
                progressBar.setVisibility(View.GONE);
            else
                bottomProgressBar.setVisibility(View.GONE);
            Toast.makeText(MyTeamFragment.this.getContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void setMyTeamData(MyTeanData data) {
        if (records.size()!=0)
            records.addAll(data.getRecords());
        else
            records = data.getRecords();
        myteamAdapter.upDateList(records , offsetLevel);
    }

    private void callGetLevel() {
        if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
            AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
            progressBar.setVisibility(View.VISIBLE);
            //  Call call = apiService.token_CALL(new AuthEntitiyClass("vp235345@vp11.com", "123456"));
            Call call = apiService.getLevel();
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(MyTeamFragment.this.getContext()).clearNonTouchableFlags(MyTeamFragment.this.getActivity());
                    progressBar.setVisibility(View.GONE);
                    LevelResponse authResponse = (LevelResponse) response.body();
                    if (authResponse != null) {
                        Log.i("RendomResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setLevelData(authResponse.getData());
                        } else {

                            Toast.makeText(MyTeamFragment.this.getContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(MyTeamFragment.this.getContext()).showDialog(MyTeamFragment.this.getActivity(), "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppCommon.getInstance(MyTeamFragment.this.getContext()).clearNonTouchableFlags(MyTeamFragment.this.getActivity());
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(MyTeamFragment.this.getContext(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            progressBar.setVisibility(View.GONE);
            Toast.makeText(MyTeamFragment.this.getContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void setLevelData(ArrayList<LevelData> data) {
        levelDataArrayList = data;

        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, levelDataArrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerLevel.setAdapter(adapter);
        //spinnerLevel.setAdapter(adapter);

    }


    public void callapi(int position) {
        offsetLevel = offsetLevel+1;
        callgetLevelView(position - 1, records.size() , "");
        bottomProgressBar.setVisibility(View.VISIBLE);


    }
}
