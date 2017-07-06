package com.shandian.lu.Main.ReleaseFragment.FaBuCheYuan;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shandian.lu.Main.AdviceFragment.MainAdviceXRVAdapter;
import com.shandian.lu.R;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/6.
 */

public class NewFaBuBanJiaCarTypeRVAdapter extends RecyclerView.Adapter<NewFaBuBanJiaCarTypeRVAdapter.MyItemViewHolder> {


    private LayoutInflater inflater;
    private Activity activity;
    private List<Object> carTypeList;

    public NewFaBuBanJiaCarTypeRVAdapter(Activity activity1, List<Object> carTypeList1){
        activity = activity1;
        carTypeList = carTypeList1;
        inflater = LayoutInflater.from(activity1);
    }

    public void setAdapter(List<Object> carTypeList1){
        if(carTypeList1.size() == 0){
            return;
        }
        carTypeList.clear();
        carTypeList.addAll(carTypeList1);
        notifyDataSetChanged();
    }

    @Override
    public MyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyItemViewHolder(inflater.inflate(R.layout.activity_new_fabubanjia_content_rv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(MyItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return carTypeList.size();
    }

    class MyItemViewHolder extends RecyclerView.ViewHolder{

        public MyItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(itemView);
        }
    }
}
