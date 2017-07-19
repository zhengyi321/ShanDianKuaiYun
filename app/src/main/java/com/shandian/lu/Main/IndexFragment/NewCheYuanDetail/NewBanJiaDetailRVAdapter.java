package com.shandian.lu.Main.IndexFragment.NewCheYuanDetail;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.NewCheYuanDetailBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/12.
 */

public class NewBanJiaDetailRVAdapter extends RecyclerView.Adapter<NewBanJiaDetailRVAdapter.MyItemViewHolder> {

    private Activity activity;
    private List<NewCheYuanDetailBean.NrBean.BjcxixniBean> dataList;
    private LayoutInflater inflater;
    public NewBanJiaDetailRVAdapter(Activity activity1, List<NewCheYuanDetailBean.NrBean.BjcxixniBean> dataList1){
        activity = activity1;
        dataList = dataList1;
        inflater = LayoutInflater.from(activity1);
    }
    public void setAdapterDataList(List<NewCheYuanDetailBean.NrBean.BjcxixniBean> dataList1){
        if((dataList1!= null)&&(dataList1.size() > 0)){
            dataList.clear();
            dataList.addAll(dataList1);
        }
        notifyDataSetChanged();
    }




    @Override
    public MyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyItemViewHolder(inflater.inflate(R.layout.activity_new_banjiaxiangqing_rv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(MyItemViewHolder holder, int position) {
        ImageLoader.getInstance().displayImage(dataList.get(position).getImg(),holder.ivNewBanJiaXiangQingRVItemLogo, ImageLoaderUtils.options1);
        holder.tvNewBanJiaXiangQingRVItemName.setText(dataList.get(position).getName());
        holder.tvNewBanJiaXiangQingRVItemTJ.setText(dataList.get(position).getTj()+"m³");
        holder.tvNewBanJiaXiangQingRVItemZZ.setText(dataList.get(position).getZz()+"kg");
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }



    public class MyItemViewHolder extends RecyclerView.ViewHolder {
        int pos = 0;
        @BindView(R.id.tv_new_banjiaxiangqing_rv_item_name)
        TextView tvNewBanJiaXiangQingRVItemName;
        @BindView(R.id.tv_new_banjiaxiangqing_rv_item_tj)
        TextView tvNewBanJiaXiangQingRVItemTJ;
        @BindView(R.id.tv_new_banjiaxiangqing_rv_item_zz)
        TextView tvNewBanJiaXiangQingRVItemZZ;
        @BindView(R.id.iv_new_banjiaxiangqing_rv_item_logo)
        ImageView ivNewBanJiaXiangQingRVItemLogo;
        public MyItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
