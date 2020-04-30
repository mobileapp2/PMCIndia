package com.imuons.pmcindia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.pmcindia.DataModel.ChatData;
import com.imuons.pmcindia.R;
import com.imuons.pmcindia.fragments.ChatFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatRoomAdapter extends RecyclerView.Adapter<ChatRoomAdapter.ChatRoomHolder> {

    Context context;
    ArrayList<ChatData> chatDataArrayList;
    Fragment faragment;

    public ChatRoomAdapter(Context context, ArrayList<ChatData> chatDataArrayList, Fragment faragment) {
        this.context = context;
        this.chatDataArrayList = chatDataArrayList;
        this.faragment = faragment;
    }

    @NonNull
    @Override
    public ChatRoomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_chet_view, parent, false);
        return new ChatRoomAdapter.ChatRoomHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatRoomHolder holder, int position) {
        ChatData chatData = chatDataArrayList.get(position);
        if(chatData.getPosition().equals("right")){
            holder.adminimage.setVisibility(View.GONE);
            holder.userimage.setVisibility(View.VISIBLE);
            holder.adminLayout.setVisibility(View.GONE);
            holder.userLayout.setVisibility(View.VISIBLE);
            holder.msgsend.setText(chatData.getMessage());
            holder.timeSend.setText(chatData.getTime());

        }else {
            holder.adminimage.setVisibility(View.VISIBLE);
            holder.userimage.setVisibility(View.GONE);
            holder.adminLayout.setVisibility(View.VISIBLE);
            holder.userLayout.setVisibility(View.GONE);
            holder.adminmsg.setText(chatData.getMessage());
            holder.adminTime.setText(chatData.getTime());
        }
    }

    @Override
    public int getItemCount() {
        return chatDataArrayList.size();
    }

    public void updateList(ArrayList<ChatData> chatDataArrayList) {
        this.chatDataArrayList = chatDataArrayList;
        notifyDataSetChanged();
    }

    public class ChatRoomHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.userimage)
        ImageView userimage;
        @BindView(R.id.adminimage)
        ImageView adminimage;
        @BindView(R.id.adminLayout)
        CardView adminLayout;
        @BindView(R.id.userLayout)
        CardView userLayout;
        @BindView(R.id.msg)
        TextView adminmsg;
        @BindView(R.id.time)
        TextView adminTime;
        @BindView(R.id.msgsend)
        TextView msgsend;
        @BindView(R.id.timeSend)
        TextView timeSend;

        public ChatRoomHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this , itemView);
        }
    }
}
