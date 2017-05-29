package com.zhyan.shandiankuaiyun.Main.MineFragment.JiFenShangCheng.SuoYouShangPin;

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
import com.zhyan.shandiankuaiyun.Main.MineFragment.JiFenShangCheng.ShangPinXiangQing.ShangPinXiangQingActivity;
import com.zhyan.shandiankuaiyun.R;
import com.zhyan.shandiankuaiyunlib.Bean.AllGoodsBean;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/4/26.
 */

public class SuoYouShangPinXRVGVAdapter extends RecyclerView.Adapter<SuoYouShangPinXRVGVAdapter.ItemViewHolder>  {

    private List<AllGoodsBean> stringList;
    private Context context;
    private LayoutInflater inflater;
    public SuoYouShangPinXRVGVAdapter(Context context1, List<AllGoodsBean> stringList1){
        stringList = stringList1;
        context = context1;
        inflater = LayoutInflater.from(context1);
    }

    public void setAdapter(List<AllGoodsBean> goodsBeanList){
        stringList.clear();
        stringList.addAll(goodsBeanList);
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflater.inflate(R.layout.activity_main_mine_jifenshangcheng_suoyoushangpin_xrv_gv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.pos = position;
        holder.tvMainMineJiFenShangChengSuoYouShangPinName.setText(stringList.get(position).getName());
        holder.tvMainMineJiFenShangChengSuoYouShangPinJiFen.setText(stringList.get(position).getJifen());
        ImageLoader.getInstance().displayImage(stringList.get(position).getImage(),holder.ivMainMineJiFenShangChengSuoYouShangPinXRVGVItemLogo, ImageLoaderUtils.options1);
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        int pos = 0;

        @BindView(R.id.iv_main_mine_jifenShangCheng_suoyoushangpin_xrv_gv_item_logo)
        ImageView ivMainMineJiFenShangChengSuoYouShangPinXRVGVItemLogo;
        @BindView(R.id.tv_main_mine_jifengshangcheng_suoyoushangpin_name)
        TextView tvMainMineJiFenShangChengSuoYouShangPinName;
        @BindView(R.id.tv_main_mine_jifengshangcheng_suoyoushangpin_jifen)
        TextView tvMainMineJiFenShangChengSuoYouShangPinJiFen;
        @BindView(R.id.lly_main_mine_jifengshangcheng_suoyoushangpin_duihuan)
        LinearLayout llyMainMineJiFenShangChengSuoYouShangPinDuiHuan;
        @OnClick(R.id.lly_main_mine_jifengshangcheng_suoyoushangpin_duihuan)
        public void llyMainMineJiFenShangChengSuoYouShangPinDuiHuanOnclick(){
            Intent intent = new Intent(context, ShangPinXiangQingActivity.class);
            intent.putExtra("id",stringList.get(pos).getId());
            context.startActivity(intent);
        }
        public ItemViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
