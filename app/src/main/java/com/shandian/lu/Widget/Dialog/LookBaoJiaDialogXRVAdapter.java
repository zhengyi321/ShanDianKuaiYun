package com.shandian.lu.Widget.Dialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.shandian.lu.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/11.
 */

public class LookBaoJiaDialogXRVAdapter extends RecyclerView.Adapter<LookBaoJiaDialogXRVAdapter.MyItemViewHolder> {


    private List<String> dataList;
    private Context context;
    private LayoutInflater inflater;
    public LookBaoJiaDialogXRVAdapter(Context context1, List<String> dataList1){
        context = context1;
        dataList = dataList1;
        inflater = LayoutInflater.from(context1);
    }


    @Override
    public MyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyItemViewHolder(inflater.inflate(R.layout.dialog_new_huoyuanxiangqing_self_baojia_xrv_item_rly,parent,false));
    }

    @Override
    public void onBindViewHolder(MyItemViewHolder holder, int position) {
        holder.pos = position;
        int data = position % 2;
        if(data == 0){
            holder.llyNewHYXQBJXRVItem.setBackgroundColor(context.getResources().getColor(R.color.white));

        }else{
            holder.llyNewHYXQBJXRVItem.setBackgroundResource(R.drawable.pink_half_radius_corner_shape);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public class MyItemViewHolder extends RecyclerView.ViewHolder{
        int pos = 0;
        @BindView(R.id.lly_new_hyxq_bj_xrv_item)
        LinearLayout llyNewHYXQBJXRVItem;
        @OnClick(R.id.lly_new_hyxq_bj_xrv_item)
        public void llyNewHYXQBJXRVItemOnclick(){

        }
        public MyItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
