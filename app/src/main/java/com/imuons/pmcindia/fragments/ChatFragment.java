package com.imuons.pmcindia.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.gson.Gson;
import com.imuons.pmcindia.DataModel.ChatData;
import com.imuons.pmcindia.Entity.ChatEntity;
import com.imuons.pmcindia.Entity.SendMessage;
import com.imuons.pmcindia.R;
import com.imuons.pmcindia.ResponseModel.ChatResponse;
import com.imuons.pmcindia.ResponseModel.CommonResponse;
import com.imuons.pmcindia.ResponseModel.LevelResponse;
import com.imuons.pmcindia.ResponseModel.SendResponse;
import com.imuons.pmcindia.adapters.ChatRoomAdapter;
import com.imuons.pmcindia.adapters.MyTeamAdapter;
import com.imuons.pmcindia.retrofit.AppService;
import com.imuons.pmcindia.retrofit.ServiceGenerator;
import com.imuons.pmcindia.utils.AppCommon;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatFragment extends Fragment {

    @BindView(R.id.rvChat)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.etMessage)
    EditText etMessage;

    ChatRoomAdapter chatRoomAdapter;
    ArrayList<ChatData> chatDataArrayList;


    public ChatFragment() {
        // Required empty public constructor
    }

    public static ChatFragment newInstance() {
        ChatFragment fragment = new ChatFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chat, container, false);
        ButterKnife.bind(this, view);
        chatDataArrayList = new ArrayList<>();
        chatRoomAdapter = new ChatRoomAdapter(getContext(), chatDataArrayList, ChatFragment.this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(chatRoomAdapter);

        callChatApi();

        return view;
    }

    private void callChatApi() {
        if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
            AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
            progressBar.setVisibility(View.VISIBLE);
            //  Call call = apiService.token_CALL(new AuthEntitiyClass("vp235345@vp11.com", "123456"));
            Call call = apiService.getChatList(new ChatEntity("1"));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(ChatFragment.this.getContext()).clearNonTouchableFlags(ChatFragment.this.getActivity());
                    progressBar.setVisibility(View.GONE);
                    ChatResponse authResponse = (ChatResponse) response.body();
                    if (authResponse != null) {
                        Log.i("ChatResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setChatData(authResponse.getData());
                        } else {

                            Toast.makeText(ChatFragment.this.getContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(ChatFragment.this.getContext()).showDialog(ChatFragment.this.getActivity(), "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppCommon.getInstance(ChatFragment.this.getContext()).clearNonTouchableFlags(ChatFragment.this.getActivity());
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(ChatFragment.this.getContext(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            progressBar.setVisibility(View.GONE);
            Toast.makeText(ChatFragment.this.getContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void setChatData(ArrayList<ChatData> data) {
        chatDataArrayList = data;
        chatRoomAdapter.updateList(chatDataArrayList);
        recyclerView.smoothScrollToPosition(chatDataArrayList.size() - 1);
    }

    @OnClick(R.id.btSend)
    void send() {
        String msg = etMessage.getText().toString().trim();
        etMessage.setText("");
        if(msg.isEmpty()){
            Toast.makeText(getContext(), "Please enter message", Toast.LENGTH_SHORT).show();
        }else {
            callsentMsgApi(msg);
        }
    }

    private void callsentMsgApi(String msg) {
        AppCommon.getInstance(getContext()).onHideKeyBoard(getActivity());
        if (AppCommon.getInstance(getContext()).isConnectingToInternet(getContext())) {
            AppCommon.getInstance(getContext()).setNonTouchableFlags(getActivity());
            AppService apiService = ServiceGenerator.createService(AppService.class, AppCommon.getInstance(getContext()).getToken());
            progressBar.setVisibility(View.VISIBLE);
            //  Call call = apiService.token_CALL(new AuthEntitiyClass("vp235345@vp11.com", "123456"));
            Call call = apiService.sendMessage(new SendMessage("1" , msg , "text"));
            call.enqueue(new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    AppCommon.getInstance(ChatFragment.this.getContext()).clearNonTouchableFlags(ChatFragment.this.getActivity());
                    progressBar.setVisibility(View.GONE);
                    CommonResponse authResponse = (CommonResponse) response.body();
                    if (authResponse != null) {
                        Log.i("ChatResponse::", new Gson().toJson(authResponse));
                        if (authResponse.getCode() == 200) {
                            setData(msg);
                            Toast.makeText(ChatFragment.this.getContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(ChatFragment.this.getContext(), authResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        AppCommon.getInstance(ChatFragment.this.getContext()).showDialog(ChatFragment.this.getActivity(), "Server Error");
                    }
                }

                @Override
                public void onFailure(Call call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    AppCommon.getInstance(ChatFragment.this.getContext()).clearNonTouchableFlags(ChatFragment.this.getActivity());
                    // loaderView.setVisibility(View.GONE);
                    Toast.makeText(ChatFragment.this.getContext(), "Server Error", Toast.LENGTH_SHORT).show();
                }
            });


        } else {
            // no internet
            progressBar.setVisibility(View.GONE);
            Toast.makeText(ChatFragment.this.getContext(), "Please check your internet", Toast.LENGTH_SHORT).show();
        }
    }

    private void setData(String msg) {
        callChatApi();
    }
}
