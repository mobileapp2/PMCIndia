package com.imuons.pmcindia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.pmcindia.R;
import com.imuons.pmcindia.ResponseModel.TopupRecordModel;
import com.imuons.pmcindia.ResponseModel.WinnerRecordModel;
import com.imuons.pmcindia.ResponseModel.WinnerReportDataModel;

import java.util.List;

public class WinnerReportAdapter extends RecyclerView.Adapter<WinnerReportAdapter.ViewHolder>{

    Context context;
    List<WinnerRecordModel> requestRecordModels_list;
    private int selected_postion;

    public WinnerReportAdapter(FragmentActivity activity, List<WinnerRecordModel> data) {
        context = activity;
        requestRecordModels_list = data;
    }

    @NonNull
    @Override
    public WinnerReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_winner_report, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WinnerReportAdapter.ViewHolder holder, int position) {
        if (selected_postion == position) {
            holder.expand_icon.setSelected(true);
            holder.expand_icon.setActivated(true);
            holder.ll_main_layer.setActivated(true);
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down);

            //toggling visibility
            holder.hidden_layer.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.hidden_layer.startAnimation(slideDown);
        } else {
            holder.ll_main_layer.setSelected(false);
            holder.expand_icon.setActivated(false);
            holder.ll_main_layer.setActivated(false);
            holder.hidden_layer.setVisibility(View.GONE);
        }
        setData(holder, requestRecordModels_list.get(position), position);
        holder.ll_main_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_postion = position;
                notifyDataSetChanged();

            }
        });
    }
    private void setData(WinnerReportAdapter.ViewHolder holder, WinnerRecordModel topupRecordModel, int position) {
        holder.srno.setText(String.valueOf(position + 1));
        holder.deposit.setText(topupRecordModel.getProductName());
        holder.amount.setText(String.valueOf(topupRecordModel.getHouseName()));
        holder.tv_plan.setText("\u20B9"+String.valueOf(topupRecordModel.getAmount()));
        holder.date.setText(topupRecordModel.getEntryTime());

    }
    @Override
    public int getItemCount() {
        return requestRecordModels_list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout hidden_layer;
        LinearLayout ll_main_layer;
        ImageView expand_icon;
        private final TextView srno;
        private final TextView date;
        private final TextView deposit;
        private final TextView amount;
        private final TextView tv_plan;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hidden_layer = itemView.findViewById(R.id.hiddenlayout);
            ll_main_layer = itemView.findViewById(R.id.llmain);
            expand_icon = itemView.findViewById(R.id.expand_icon);
            srno = itemView.findViewById(R.id.srno);
            date = itemView.findViewById(R.id.date);
            deposit = itemView.findViewById(R.id.deposit);
            amount = itemView.findViewById(R.id.amount);
            tv_plan = itemView.findViewById(R.id.tv_plan);
        }
    }
}
