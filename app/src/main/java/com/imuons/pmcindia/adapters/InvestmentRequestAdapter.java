package com.imuons.pmcindia.adapters;

import android.content.Context;
import android.content.Intent;
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
import com.imuons.pmcindia.ResponseModel.FundRequestRecordModel;
import com.imuons.pmcindia.view.ViewAttachment;

import java.util.List;

import static com.imuons.pmcindia.application.PMCApplication.imageLoader;

public class InvestmentRequestAdapter extends RecyclerView.Adapter<InvestmentRequestAdapter.ViewHolder>{

Context context;
List<FundRequestRecordModel> requestRecordModels_list;
    private int selected_postion;

    public InvestmentRequestAdapter(FragmentActivity activity, List<FundRequestRecordModel> data) {
        context=activity;
        requestRecordModels_list=data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invest_request_report, parent, false);
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

        holder.attacment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, ViewAttachment.class);
                intent.putExtra("url", requestRecordModels_list.get(position).getAttachment());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });
    }

    private void setData(ViewHolder holder, FundRequestRecordModel fundRequestRecordModel, int position) {
        holder.srno.setText(String.valueOf(position + 1));
        holder.deposit.setText(fundRequestRecordModel.getInvoiceId());
        holder.amount.setText("\u20B9"+String.valueOf(fundRequestRecordModel.getAmount()));
        holder.tv_pkg_name.setText(fundRequestRecordModel.getProductName());
        holder.status.setText(fundRequestRecordModel.getStatus());
        holder.date.setText(fundRequestRecordModel.getEntryTime());
        imageLoader.displayImage(fundRequestRecordModel.getAttachment(), holder.attacment);
    }

    @Override
    public int getItemCount() {
        return requestRecordModels_list.size();
    }

    class   ViewHolder extends RecyclerView.ViewHolder{
        private final ImageView attacment;
        LinearLayout hidden_layer;
        LinearLayout ll_main_layer;
        ImageView expand_icon;
        private final TextView srno;
        private final TextView date;
        private final TextView deposit;
        private final TextView amount;
        private final TextView tv_pkg_name;
        private final TextView status;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            hidden_layer = itemView.findViewById(R.id.hiddenlayout);
            ll_main_layer = itemView.findViewById(R.id.llmain);
            expand_icon = itemView.findViewById(R.id.expand_icon);
            srno=itemView.findViewById(R.id.srno);
            date=itemView.findViewById(R.id.date);
            deposit=itemView.findViewById(R.id.deposit);
            amount=itemView.findViewById(R.id.amount);
            tv_pkg_name=itemView.findViewById(R.id.tv_pkg_name);
            status=itemView.findViewById(R.id.status);
            attacment=itemView.findViewById(R.id.attacment);


        }
    }
}
