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
import com.imuons.pmcindia.ResponseModel.EstimateRecord;


import java.util.List;

public class EstimateAmountReportAdapter extends RecyclerView.Adapter<EstimateAmountReportAdapter.ViewHolder> {


    Context context;
    List<EstimateRecord> estimateRecords;
    private int selected_position;

    public EstimateAmountReportAdapter(FragmentActivity activity, List<EstimateRecord> data) {
        context = activity;
        estimateRecords = data;
    }

    @NonNull
    @Override
    public EstimateAmountReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estimate_report, parent, false);
        return new EstimateAmountReportAdapter.ViewHolder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull EstimateAmountReportAdapter.ViewHolder holder, int position) {
        if (selected_position == position) {
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
        setData(holder, estimateRecords.get(position), position);
        holder.ll_main_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_position = position;
                notifyDataSetChanged();

            }
        });
    }

    private void setData(ViewHolder holder, EstimateRecord estimateRecord, int position) {

        holder.srno.setText(String.valueOf(position + 1));
        holder.deposit.setText("\u20B9"+String.valueOf(estimateRecord.getAmount()));
        holder.packageName.setText(String.valueOf(estimateRecord.getProductName()));
        holder.house.setText(String.valueOf(estimateRecord.getHouseName()));

        holder.date.setText(estimateRecord.getEntryTime());

    }

    @Override
    public int getItemCount() {
        return estimateRecords.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout hidden_layer;
        LinearLayout ll_main_layer;
        ImageView expand_icon;
        private final TextView srno;
        private final TextView date;
        private final TextView deposit;
        private final TextView house;

        private final TextView packageName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hidden_layer = itemView.findViewById(R.id.hiddenlayout);
            ll_main_layer = itemView.findViewById(R.id.llmain);
            expand_icon = itemView.findViewById(R.id.expand_icon);
            srno = itemView.findViewById(R.id.srno);
            date = itemView.findViewById(R.id.date);
            deposit = itemView.findViewById(R.id.deposit);
            house = itemView.findViewById(R.id.house);

            packageName = itemView.findViewById(R.id.packageName);
        }
    }
}
