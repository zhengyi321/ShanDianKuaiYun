package com.shandian.lu.Main.MineFragment.WoDeYaoQing.WoDeYaoQingDetail;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.NewYaoQingBean;
import com.shandian.lu.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/19.
 */

public class WoDeYaoQingDetailRVAdapter extends RecyclerView.Adapter<WoDeYaoQingDetailRVAdapter.MyItemViewHolder> {

    private Activity activity;
    private List<NewYaoQingBean.NrBean> dataList;
    private LayoutInflater inflater;
    public WoDeYaoQingDetailRVAdapter(Activity activity1, List<NewYaoQingBean.NrBean> dataList1){
        activity = activity1;
        dataList = dataList1;
        inflater = LayoutInflater.from(activity1);
    }

    public void setAdapter(List<NewYaoQingBean.NrBean> dataList1){
        if(dataList1 == null){
            return;
        }
        dataList.clear();
        dataList.addAll(dataList1);
        notifyDataSetChanged();
    }


    @Override
    public MyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyItemViewHolder(inflater.inflate(R.layout.activity_wodeyaoqingdetail_rv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(MyItemViewHolder holder, int position) {
        String name = dataList.get(position).getName();
        String jifen = dataList.get(position).getJifen();
        String date = dataList.get(position).getTime();
        holder.tvNewWoDeYaoQingDetailRVItemName.setText(name);
        holder.tvNewWoDeYaoQingDetailRVItemJiFen.setText(jifen);
        holder.tvNewWoDeYaoQingDetailRVItemDate.setText(date);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public class MyItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_new_wodeyaoqingdetail_rv_item_name)
        TextView tvNewWoDeYaoQingDetailRVItemName;
        @BindView(R.id.tv_new_wodeyaoqingdetail_rv_item_jifen)
        TextView tvNewWoDeYaoQingDetailRVItemJiFen;
        @BindView(R.id.tv_new_wodeyaoqingdetail_rv_item_date)
        TextView tvNewWoDeYaoQingDetailRVItemDate;
        public MyItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
