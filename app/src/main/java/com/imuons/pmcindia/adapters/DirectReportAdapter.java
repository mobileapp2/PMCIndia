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

import com.imuons.pmcindia.DataModel.RecordDirectData;
import com.imuons.pmcindia.R;
import com.imuons.pmcindia.ResponseModel.WithdrawReportRecordModel;

import java.util.ArrayList;
import java.util.List;

public class DirectReportAdapter extends RecyclerView.Adapter<DirectReportAdapter.ViewHolder> {

    Context context;
    ArrayList<RecordDirectData> recordModels;
    private int selected_postion;

    public DirectReportAdapter(FragmentActivity activity, ArrayList<RecordDirectData> data) {
        context = activity;
        recordModels = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_direct_report, parent, false);
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
        setData(holder, recordModels.get(position), position);
        holder.ll_main_layer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_postion = position;
                notifyDataSetChanged();

            }
        });
    }



    private void setData(ViewHolder holder, RecordDirectData topupRecordModel, int position) {
        holder.srno.setText(String.valueOf(position + 1));
        holder.deduction.setText(String.valueOf(topupRecordModel.getFromUserId()));
        holder.amount.setText(String.valueOf(topupRecordModel.getFromFullname()));
        holder.tv_status.setText(String.valueOf(topupRecordModel.getInvoiceId()));
        holder.tv_withdrawtype.setText("\u20B9"+topupRecordModel.getAmount());

        holder.tv_payment_mode.setText("\u20B9"+String.valueOf(topupRecordModel.getLapsAmount()));
        holder.statusDrict.setText(String.valueOf(topupRecordModel.getStatus()));
        holder.date.setText(topupRecordModel.getRecDate());

    }

    @Override
    public int getItemCount() {
        return recordModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout hidden_layer;
        LinearLayout ll_main_layer;
        ImageView expand_icon;
        private final TextView srno;
        private final TextView date;
        private final TextView deduction;
        private final TextView amount;
        private final TextView tv_status;
        private final TextView tv_withdrawtype;
        private final TextView tv_payment_mode;
        private final TextView statusDrict;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hidden_layer = itemView.findViewById(R.id.hiddenlayout);
            ll_main_layer = itemView.findViewById(R.id.llmain);
            expand_icon = itemView.findViewById(R.id.expand_icon);
            srno = itemView.findViewById(R.id.srno);
            date = itemView.findViewById(R.id.date);
            deduction = itemView.findViewById(R.id.deposit);
            amount = itemView.findViewById(R.id.amount);
            tv_status = itemView.findViewById(R.id.tv_status);
            tv_withdrawtype = itemView.findViewById(R.id.withdraw);
            tv_payment_mode = itemView.findViewById(R.id.payment);
            statusDrict = itemView.findViewById(R.id.statusDrict);


        }
    }
}
