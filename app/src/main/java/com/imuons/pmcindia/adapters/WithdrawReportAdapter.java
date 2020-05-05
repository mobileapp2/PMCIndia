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
import com.imuons.pmcindia.ResponseModel.WithdrawReportRecordModel;

import java.util.List;

public class WithdrawReportAdapter extends RecyclerView.Adapter<WithdrawReportAdapter.ViewHolder> {

    Context context;
    List<WithdrawReportRecordModel> recordModels;
    private int selected_postion;

    public WithdrawReportAdapter(FragmentActivity activity, List<WithdrawReportRecordModel> data) {
        context = activity;
        recordModels = data;
    }

    @NonNull
    @Override
    public WithdrawReportAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_withdraw_report, parent, false);
        return new WithdrawReportAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WithdrawReportAdapter.ViewHolder holder, int position) {
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

    private void setData(WithdrawReportAdapter.ViewHolder holder, WithdrawReportRecordModel topupRecordModel, int position) {
        holder.srno.setText(String.valueOf(position + 1));
        holder.deduction.setText(String.valueOf(topupRecordModel.getNetworkType()));
        holder.amount.setText("\u20B9"+String.valueOf(topupRecordModel.getAmount()));
        if (topupRecordModel.getStatus() == 0 || topupRecordModel.getStatus().equals("0")){
            holder.tv_status.setText("Pending");
        }else {
            holder.tv_status.setText("Confirmed");
        }
        if (topupRecordModel.getWithdrawType()==2 ){
            holder.tv_withdrawtype.setText("Working");
        }
        if (topupRecordModel.getWithdrawType()==6){
            holder.tv_withdrawtype.setText("Principal Amount");
        }
      //  holder.tv_payment_mode.setText(String.valueOf(topupRecordModel.getNetworkType()));
        holder.date.setText(topupRecordModel.getEntryTime());

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


        }
    }
}
