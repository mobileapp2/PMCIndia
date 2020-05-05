package com.imuons.pmcindia.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;

import com.imuons.pmcindia.R;
import com.imuons.pmcindia.ResponseModel.GetPackageRecordModel;
import com.imuons.pmcindia.fragments.FragmentInvestment;

import java.util.List;

public class InvestmentGridAdapter extends BaseAdapter {

    List<GetPackageRecordModel> modelList;
    ClickEvent clickEvent;
    private Context mContext;
    private ViewHolder last_view;

    public InvestmentGridAdapter(FragmentActivity activity, List<GetPackageRecordModel> data, FragmentInvestment fragmentInvestment) {
        mContext = activity;
        modelList = data;
        clickEvent = fragmentInvestment;
    }

    @Override
    public int getCount() {
        return modelList.size();
    }


    @Override
    public GetPackageRecordModel getItem(int position) {
        return modelList.get(position);
    }


    @Override
    public long getItemId(int position) {
        return modelList.indexOf(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        final LayoutInflater layoutInflater = LayoutInflater.from(mContext);
       if(convertView==null){
           convertView = layoutInflater.inflate(R.layout.item_investment, null);
           viewHolder = new ViewHolder();
           viewHolder.iv_pkg_image = convertView.findViewById(R.id.pkg_image);
           viewHolder.tv_pkg_name = convertView.findViewById(R.id.pkg_name);
           viewHolder.tv_pkg_amount = convertView.findViewById(R.id.pkg_amount);
           viewHolder.int_btc = convertView.findViewById(R.id.int_BTC);
           viewHolder.int_inr = convertView.findViewById(R.id.int_inr);
           viewHolder.ll_file_choose = convertView.findViewById(R.id.file_choose_layer);
           viewHolder.tv_file_name = convertView.findViewById(R.id.file_name);
           viewHolder.et_amount = convertView.findViewById(R.id.et_amount);
           viewHolder.make_payment = convertView.findViewById(R.id.btn_payment);
           viewHolder.rb_groupe = convertView.findViewById(R.id.int_type);
           convertView.setTag(viewHolder);
       }
        ViewHolder viewHolder1 = (ViewHolder) convertView.getTag();
        setData(modelList.get(position), position, viewHolder1);

        viewHolder1.make_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type ;
                if(viewHolder1.int_btc.isChecked()){
                    type="BTC";
                }else{
                    type="INR";
                }
                if(modelList.get(position).getId()==5){
                    if(viewHolder1.et_amount.getText().toString().isEmpty()){
                        viewHolder1.et_amount.setError("Enter amount");
                        viewHolder1.et_amount.requestFocus();
                        return;
                    }
                   clickEvent.clickMakeyPayment(modelList.get(position),viewHolder1.et_amount.getText().toString(),type);
                }else{
                    clickEvent.clickMakeyPayment(modelList.get(position),String.valueOf( modelList.get(position).getMinHash()),type);
                }

            }
        });
       // View finalConvertView = convertView;
        viewHolder1.rb_groupe.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                RadioButton rb = finalConvertView.findViewById(checkedId);
//                if (rb.getText().toString().equals("BTC")) {
//                    Log.i("BTC", "1");
//                    viewHolder1.ll_file_choose.setVisibility(View.GONE);
//
//                } else {
//                    Log.i("INR", "1");
//
//                    viewHolder1.ll_file_choose.setVisibility(View.VISIBLE);
//                }
                if (viewHolder1.int_btc.isChecked()) {
                    Log.i("BTC", "1");
                    viewHolder1.ll_file_choose.setVisibility(View.GONE);

                } else {
                    Log.i("INR", "1");

                    viewHolder1.ll_file_choose.setVisibility(View.VISIBLE);
                }
            }
        });

        viewHolder1.ll_file_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickEvent.clickFileChooser(viewHolder1);
            }
        });
        return convertView;
    }

    private void setData(GetPackageRecordModel getPackageRecordModel, int position, ViewHolder viewHolder1) {
        viewHolder1.tv_pkg_name.setText(getPackageRecordModel.getName());
        viewHolder1.tv_pkg_amount.setText("\u20B9"+String.valueOf(getPackageRecordModel.getMinHash()));
        if (getPackageRecordModel.getId() == 1) {
            viewHolder1.iv_pkg_image.setImageResource(R.drawable.aakash);
        } else if (getPackageRecordModel.getId() == 2) {
            viewHolder1.iv_pkg_image.setImageResource(R.drawable.jal);
        } else if (getPackageRecordModel.getId() == 3) {
            viewHolder1.iv_pkg_image.setImageResource(R.drawable.vayu);
        } else if (getPackageRecordModel.getId() == 4) {
            viewHolder1.iv_pkg_image.setImageResource(R.drawable.agni);
        } else if (getPackageRecordModel.getId() == 5) {
            viewHolder1.iv_pkg_image.setVisibility(View.GONE);
            viewHolder1.et_amount.setVisibility(View.VISIBLE);
        }

    }

    public interface ClickEvent {
        void clickFileChooser(ViewHolder viewHolder);

        void clickMakeyPayment(GetPackageRecordModel packageRecordModel, String amount,String type);
    }

    public class ViewHolder {
        ImageView iv_pkg_image;
        TextView tv_pkg_name;
        TextView tv_pkg_amount;
        RadioButton int_btc;
        RadioButton int_inr;
        LinearLayout ll_file_choose;
        TextView tv_file_name;
        EditText et_amount;
        Button make_payment;
        RadioGroup rb_groupe;

    }
    public void setfileName(ViewHolder viewHolder ,String filename){
        if(last_view!=null){
            last_view.tv_file_name.setText("No file choosen");
            last_view=null;
            last_view=viewHolder;
        }else{
            last_view=viewHolder;
        }
        last_view=viewHolder;
        viewHolder.tv_file_name.setText(filename);
    }
}
