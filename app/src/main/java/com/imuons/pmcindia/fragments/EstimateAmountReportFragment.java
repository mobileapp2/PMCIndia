package com.imuons.pmcindia.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imuons.pmcindia.R;
import com.imuons.pmcindia.ResponseModel.EstimateRecord;
import com.imuons.pmcindia.ResponseModel.EstimateResponse;
import com.imuons.pmcindia.ResponseModel.ROIReportResponse;
import com.imuons.pmcindia.ResponseModel.RoiRecordModel;
import com.imuons.pmcindia.adapters.EstimateAmountReportAdapter;
import com.imuons.pmcindia.adapters.RoiAdapter;
import com.imuons.pmcindia.retrofit.AppService;
import com.imuons.pmcindia.retrofit.ServiceGenerator;
import com.imuons.pmcindia.utils.AppCommon;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EstimateAmountReportFragment extends Fragment {


    private View view;
    private RecyclerView recycle_view;
    private Spinner spinner_show_entry;
    private EditText searchbyid;

    public EstimateAmountReportFragment() {
        // Required empty public constructor
    }

    public static EstimateAmountReportFragment newInstance() {
        EstimateAmountReportFragment fragment = new EstimateAmountReportFragment();
        return fragment;
    }

    private void initUI() {
        recycle_view = view.findViewById(R.id.recycler_view);
        spinner_show_entry = view.findViewById(R.id.spinner_show);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.show_array, R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown);
        spinner_show_entry.setAdapter(adapter);
        spinner_show_entry.setSelection(0);
        searchbyid = view.findViewById(R.id.searchbyid);
        recycle_view.setHasFixedSize(true);
        recycle_view.setLayoutManager(new LinearLayoutManager(this.getContext(),
                LinearLayoutManager.VERTICAL, false));
        searchListener();
    }

    private void searchListener() {

        searchbyid.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    callRequestreport(
                            Integer.parseInt(spinner_show_entry.getSelectedItem().toString()), searchbyid.getText().toString());
                    return true;
                }
                return false;
            }
        });
        spinner_show_entry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                callRequestreport(
                        Integer.parseInt(spinner_show_entry.getSelectedItem().toString()), searchbyid.getText().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_estimate_amount_report, container, false);
        initUI();
        return view;
    }

    private void callRequestreport(int length, String search) {
        if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
            AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
            Map<String, Object> param = new HashMap<>();
            param.put("start", 0);
            param.put("length", length);
            param.put("search[value]", search);
            param.put("search[regex]", false);
            Call call = apiService.GetEstimateReport(param);
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(getContext()).clearNonTouchableFlags(getActivity());
                    // progressBar.setVisibility(View.GONE);
                    EstimateResponse body =
                            (EstimateResponse) response.body();
                    if (body != null) {
                        Log.i("requets fund::", new Gson().toJson(body));
                        if (body.getCode() == 200) {
                            setadpter(body.getData().getRecords());
                        } else {
                            Toast.makeText(getContext(), body.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(getContext()).showDialog(getActivity(),
                                getResources().getString(R.string.server_error));
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

    private void setadpter(List<EstimateRecord> records) {
        EstimateAmountReportAdapter adapter = new EstimateAmountReportAdapter(getActivity()
                , records);
        recycle_view.setAdapter(adapter);
    }
}
