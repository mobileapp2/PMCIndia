package com.imuons.pmcindia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.imuons.pmcindia.DataModel.LevelData;
import com.imuons.pmcindia.DataModel.RecordData;
import com.imuons.pmcindia.R;
import com.imuons.pmcindia.fragments.MyTeamFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyTeamAdapter extends RecyclerView.Adapter<MyTeamAdapter.MyTeamHolder> {
    Context context;
    ArrayList<RecordData> records;
    Fragment fragment;
    int offset = 9;
    public MyTeamAdapter(Context context, ArrayList<RecordData> records , Fragment fragment) {
        this.context = context;
        this.records = records;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public MyTeamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_team_view, parent, false);
        return new MyTeamHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyTeamHolder holder, int position) {
        RecordData levelData = records.get(position);
        holder.srno.setText(String.valueOf(position+1));
        holder.date.setText(levelData.getEntryTime());
        holder.email.setText(levelData.getEm());
        holder.investment.setText(String.valueOf(levelData.getTotalInvestment()));
        holder.sponsorid.setText(levelData.getSponserId());
        holder.userid.setText(levelData.getDownUserId());
        holder.sponsorName.setText(levelData.getFn());
        holder.tv_sponsorname.setText(levelData.getFn());
        holder.level.setText(String.valueOf(levelData.getLevel()));
        if(levelData.isOpen())
            holder.hiddenlayout.setVisibility(View.VISIBLE);
        else
            holder.hiddenlayout.setVisibility(View.GONE);

        if(position == offset){
            ((MyTeamFragment)fragment).callapi(position);
        }


    }



    @Override
    public int getItemCount() {
        return records.size();
    }

    public void upDateList(ArrayList<RecordData> records , int offsetLevel) {
            offset = 9*(offsetLevel+1);
            this.records = records;

    notifyDataSetChanged();
    }

    public class MyTeamHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.srno)
        TextView srno;
        @BindView(R.id.userid)
        TextView userid;
        @BindView(R.id.sponsorName)
        TextView sponsorName;
        @BindView(R.id.hiddenlayout)
        LinearLayout hiddenlayout;
        @BindView(R.id.llmain)
        LinearLayout llmain;

        @BindView(R.id.sponsorid)
        TextView sponsorid;
        @BindView(R.id.tv_sponsorname)
        TextView tv_sponsorname;
        @BindView(R.id.email)
        TextView email;
        @BindView(R.id.level)
        TextView level;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.investment)
        TextView investment;
        public MyTeamHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this , itemView);
        }

        @OnClick({R.id.llmain ,R.id.userid ,R.id.sponsorName })
        void rowClick(){
            int pos = getAdapterPosition();
            if(records.get(pos).isOpen()){
                records.get(pos).setOpen(false);
            }else
                records.get(pos).setOpen(true);

            notifyItemChanged(pos);

        }
    }
}
