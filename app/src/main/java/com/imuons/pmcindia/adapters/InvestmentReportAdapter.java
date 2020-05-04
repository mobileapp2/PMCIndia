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

import java.util.List;

public class InvestmentReportAdapter extends RecyclerView.Adapter<InvestmentReportAdapter.ViewHolder> {

    Context context;
    List<TopupRecordModel> requestRecordModels_list;
    private int selected_postion;

    public InvestmentReportAdapter(FragmentActivity activity, List<TopupRecordModel> data) {
        context = activity;
        requestRecordModels_list = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_request_report, parent, false);
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
        setData(holder, requestRecordModels_list.get(position), position);
        holder.ll_main_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_postion = position;
                notifyDataSetChanged();

            }
        });
    }

    private void setData(ViewHolder holder, TopupRecordModel topupRecordModel, int position) {
        holder.srno.setText(String.valueOf(position + 1));
        holder.deposit.setText(topupRecordModel.getPin());
        holder.amount.setText("\u20B9"+String.valueOf(topupRecordModel.getAmount()));
        holder.tv_plan.setText(topupRecordModel.getName());
        holder.house.setText(topupRecordModel.getHouseName());
        holder.tv_slot_no.setText(String.valueOf(topupRecordModel.getSlotNo()));
        holder.tv_topu_up_by.setText(topupRecordModel.getTopUpBy());
        holder.tv_payment_mode.setText(topupRecordModel.getPaymentType());
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
        private final TextView house;
        private final TextView tv_slot_no;
        private final TextView tv_topu_up_by;
        private final TextView tv_payment_mode;

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
            house = itemView.findViewById(R.id.house);
            tv_slot_no = itemView.findViewById(R.id.tv_slot_no);
            tv_topu_up_by = itemView.findViewById(R.id.tv_topu_up_by);
            tv_payment_mode = itemView.findViewById(R.id.tv_payment_mode);


        }
    }
}
