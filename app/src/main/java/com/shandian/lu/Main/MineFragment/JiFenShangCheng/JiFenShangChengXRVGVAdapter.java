package com.shandian.lu.Main.MineFragment.JiFenShangCheng;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.Main.MineFragment.JiFenShangCheng.ShangPinXiangQing.ShangPinXiangQingActivity;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.JiFenShangChengBean;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/4/26.
 */

public class JiFenShangChengXRVGVAdapter extends RecyclerView.Adapter<JiFenShangChengXRVGVAdapter.ItemViewHolder>  {

    private List<JiFenShangChengBean.ListBean> stringList;
    private Context context;
    private LayoutInflater inflater;
    public JiFenShangChengXRVGVAdapter(Context context1, List<JiFenShangChengBean.ListBean> stringList1){
        stringList = stringList1;
        context = context1;
        inflater = LayoutInflater.from(context1);
    }

    public void setAdapter( List<JiFenShangChengBean.ListBean> listBeen){
        stringList.clear();
        stringList.addAll(listBeen);
        notifyDataSetChanged();

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflater.inflate(R.layout.activity_main_mine_jifenshangcheng_xrv_gv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.tvMainMineJFenShangChengXRVGVItemName.setText(stringList.get(position).getName());
        holder.tvMainMineJiFenShangChengXRVGVItemJF.setText(stringList.get(position).getJifen());
        ImageLoader.getInstance().displayImage(stringList.get(position).getImage(),holder.ivMainMineJiFenShangChengXRVGVItemLogo, ImageLoaderUtils.options1);
        holder.pos = position;
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        int pos = 0;
        @BindView(R.id.lly_main_mine_jifenshangcheng_xrv_gv_item)
        LinearLayout llyMainMineJiFenShangChengXRVGVItem;
        @OnClick(R.id.lly_main_mine_jifenshangcheng_xrv_gv_item)
        public void llyMainMineJiFenShangChengXRVGVItemOnclick(){
            Intent intent = new Intent(context, ShangPinXiangQingActivity.class);
            intent.putExtra("id",stringList.get(pos).getId());
            context.startActivity(intent);
        }

        @BindView(R.id.iv_main_mine_jifenshangcheng_xrv_gv_item_logo)
        ImageView ivMainMineJiFenShangChengXRVGVItemLogo;
        @BindView(R.id.tv_main_mine_jifenshangcheng_xrv_gv_item_name)
        TextView tvMainMineJFenShangChengXRVGVItemName;
        @BindView(R.id.tv_main_mine_jifenshangcheng_xrv_gv_item_jf)
        TextView tvMainMineJiFenShangChengXRVGVItemJF;
        public ItemViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
