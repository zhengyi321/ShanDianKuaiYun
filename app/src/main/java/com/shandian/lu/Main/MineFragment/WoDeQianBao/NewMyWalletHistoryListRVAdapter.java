package com.shandian.lu.Main.MineFragment.WoDeQianBao;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.NewMyWalletHistoryListBean;
import com.shandian.lu.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/16.
 */

public class NewMyWalletHistoryListRVAdapter extends RecyclerView.Adapter<NewMyWalletHistoryListRVAdapter.MyItemViewHolder> {

    private List<NewMyWalletHistoryListBean.NrBean> dataList;
    private Activity activity;
    private LayoutInflater inflater;
    public NewMyWalletHistoryListRVAdapter(Activity activity1, List<NewMyWalletHistoryListBean.NrBean> dataList1){
        dataList = dataList1;
        activity = activity1;
        inflater = LayoutInflater.from(activity1);
    }



    public void setAdapterList(List<NewMyWalletHistoryListBean.NrBean> dataList1){
        dataList.clear();
        dataList.addAll(dataList1);

        notifyDataSetChanged();
    }

    @Override
    public MyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyItemViewHolder(inflater.inflate(R.layout.activity_new_mywallet_historylist_content_xrv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(MyItemViewHolder holder, int position) {
        holder.pos = position;
        String type = dataList.get(position).getLx();
        switch (type){
            case "1":
                holder.tvNewMyWalletHistoryListContentXRVItemType.setText("成功出车");
                holder.ivNewMyWalletHistoryListContentXRVItemLogo.setImageResource(R.mipmap.shouru_logo);
                break;
            case "2":
                holder.tvNewMyWalletHistoryListContentXRVItemType.setText("成功出货");
                holder.ivNewMyWalletHistoryListContentXRVItemLogo.setImageResource(R.mipmap.zhifu_logo);
                break;
            case "3":
                holder.tvNewMyWalletHistoryListContentXRVItemType.setText("金额提现");
                holder.ivNewMyWalletHistoryListContentXRVItemLogo.setImageResource(R.mipmap.tixian_logo);
                break;
            case "4":
                holder.tvNewMyWalletHistoryListContentXRVItemType.setText("红包领取");
                holder.ivNewMyWalletHistoryListContentXRVItemLogo.setImageResource(R.mipmap.hongbao_logo);
                break;
        }
        String time = dataList.get(position).getTime();
        holder.tvNewMyWalletHistoryListContentXRVItemTime.setText(time);
        String jiageDetail = dataList.get(position).getJiage();
        if(jiageDetail == null){
            jiageDetail = "";
        }
        int indeOfAdd = jiageDetail.indexOf("+");
        if(indeOfAdd >= 0){
            jiageDetail = jiageDetail.substring(indeOfAdd+1,jiageDetail.length());
            holder.tvNewMyWalletHistoryListContentXRVItemDetail.setText("+  ¥     "+jiageDetail);
            holder.tvNewMyWalletHistoryListContentXRVItemDetail.setTextColor(activity.getResources().getColor(R.color.mywallet_add_text_yellow_color));
        }
        int indexOfRediuce = jiageDetail.indexOf("-");
        if(indexOfRediuce >= 0){
            jiageDetail = jiageDetail.substring(indexOfRediuce+1,jiageDetail.length());
            holder.tvNewMyWalletHistoryListContentXRVItemDetail.setText("-  ¥     "+jiageDetail);
            holder.tvNewMyWalletHistoryListContentXRVItemDetail.setTextColor(activity.getResources().getColor(R.color.black));
        }

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public class MyItemViewHolder extends RecyclerView.ViewHolder{
        int pos = 0;
        @BindView(R.id.tv_new_mywallet_historylist_content_xrv_item_type)
        TextView tvNewMyWalletHistoryListContentXRVItemType;
        @BindView(R.id.tv_new_mywallet_historylist_content_xrv_item_time)
        TextView tvNewMyWalletHistoryListContentXRVItemTime;
        @BindView(R.id.tv_new_mywallet_historylist_content_xrv_item_detail)
        TextView tvNewMyWalletHistoryListContentXRVItemDetail;
        @BindView(R.id.iv_new_mywallet_historylist_content_xrv_item_logo)
        ImageView ivNewMyWalletHistoryListContentXRVItemLogo;
        public MyItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
