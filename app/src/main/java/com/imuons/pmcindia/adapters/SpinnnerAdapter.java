package com.imuons.pmcindia.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.imuons.pmcindia.R;
import com.imuons.pmcindia.ResponseModel.GetPackageHouseModel;

import java.util.List;

public class SpinnnerAdapter extends BaseAdapter {
    List<GetPackageHouseModel> houseModelList;
    Context context;
    clickInterfce clickInterfce;
    public SpinnnerAdapter(Context mContext, List<GetPackageHouseModel> houses, InvestmentGridAdapter investmentGridAdapter) {
        context = mContext;
        houseModelList = houses;
        clickInterfce=
                (com.imuons.pmcindia.adapters.SpinnnerAdapter.clickInterfce)investmentGridAdapter;
    }

    @Override
    public int getCount() {
        return houseModelList.size();
    }


    @Override
    public Object getItem(int position) {
//        if(position==0){
//            return null;
//        }else{
//            return houseModelList.get(position-1);
//        }
        return houseModelList.get(position);
    }


    @Override
    public long getItemId(int position) {
//        if (position==0){
//            return 0;
//        }else{
//            return houseModelList.indexOf(position-1);
//        }
        return houseModelList.indexOf(position);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        viewHolder = new ViewHolder();
        if(convertView==null){
            convertView = inflater.inflate(R.layout.spinner_item, null);
            viewHolder.text1 = convertView.findViewById(R.id.text1);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.text1.setText(houseModelList.get(position).getName());
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickInterfce.clickOnText(houseModelList.get(position));
//            }
//        });
        return convertView;
    }

    class ViewHolder {
        TextView text1;
    }
  public  interface  clickInterfce{
        public void clickOnText(GetPackageHouseModel getPackageHouseModel);
    }
}
