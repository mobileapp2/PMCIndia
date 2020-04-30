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
import com.imuons.pmcindia.ResponseModel.RoiRecordModel;
import com.imuons.pmcindia.ResponseModel.WinnerRecordModel;

import java.util.List;

public class RoiAdapter extends RecyclerView.Adapter<RoiAdapter.ViewHolder> {

    Context context;
    List<RoiRecordModel> roiRecordModels;
    private int selected_postion;


    public RoiAdapter(FragmentActivity activity, List<RoiRecordModel> data) {
        context = activity;
        roiRecordModels = data;
    }

    @NonNull
    @Override
    public RoiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_roi_report, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
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
        setData(holder, roiRecordModels.get(position), position);
        holder.ll_main_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_postion = position;
                notifyDataSetChanged();

            }
        });
    }

    private void setData(RoiAdapter.ViewHolder holder, RoiRecordModel roiRecordModel, int position) {
        holder.srno.setText(String.valueOf(position + 1));
        holder.deposit.setText(roiRecordModel.getPin());
        holder.amount.setText(String.valueOf(roiRecordModel.getOnAmount()));
        holder.tv_profit.setText(String.valueOf(roiRecordModel.getAmount()));
        holder.date.setText(roiRecordModel.getEntryTime());
        holder.packageName.setText(roiRecordModel.getName());

    }

    @Override
    public int getItemCount() {
        return roiRecordModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout hidden_layer;
        LinearLayout ll_main_layer;
        ImageView expand_icon;
        private final TextView srno;
        private final TextView date;
        private final TextView deposit;
        private final TextView amount;
        private final TextView tv_profit;
        private final TextView packageName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hidden_layer = itemView.findViewById(R.id.hiddenlayout);
            ll_main_layer = itemView.findViewById(R.id.llmain);
            expand_icon = itemView.findViewById(R.id.expand_icon);
            srno = itemView.findViewById(R.id.srno);
            date = itemView.findViewById(R.id.date);
            deposit = itemView.findViewById(R.id.deposit);
            amount = itemView.findViewById(R.id.amount);
            tv_profit = itemView.findViewById(R.id.tv_profit);
            packageName = itemView.findViewById(R.id.packageName);
        }
    }
}
