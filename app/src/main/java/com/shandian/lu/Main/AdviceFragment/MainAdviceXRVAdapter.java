package com.shandian.lu.Main.AdviceFragment;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shandian.lu.Main.AdviceFragment.AdviceDetail.AdviceDetailActivity;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.AdviceInfoListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/4/26.
 */

public class MainAdviceXRVAdapter extends RecyclerView.Adapter<MainAdviceXRVAdapter.ItemViewHolder>  {

    private List<AdviceInfoListBean.ContentBean> stringList;
    private Context context;
    private LayoutInflater inflater;
    public MainAdviceXRVAdapter(Context context1,List<AdviceInfoListBean.ContentBean> stringList1){
        stringList = stringList1;
        context = context1;
        inflater = LayoutInflater.from(context1);
    }
    public void setAdapter(List<AdviceInfoListBean.ContentBean> stringList1){
        stringList.clear();
        stringList.addAll(stringList1);
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflater.inflate(R.layout.main_advice_xrv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.tvMainAdviceXRVItem.setText(stringList.get(position).getTitle().toString());
        holder.pos = position;
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        int pos = 0;
        @BindView(R.id.tv_main_advice_xrv_item)
        TextView tvMainAdviceXRVItem;

        @BindView(R.id.lly_main_advice_xrv_item)
        LinearLayout llyMainAdviceXRVItem;
        @OnClick(R.id.lly_main_advice_xrv_item)
        public  void llyMainAdviceXRVItem(){
            Intent intent = new Intent(context, AdviceDetailActivity.class);
            intent.putExtra("id",stringList.get(pos).getId());
            context.startActivity(intent);
        }
        public ItemViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
