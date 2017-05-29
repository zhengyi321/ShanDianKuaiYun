package com.zhyan.shandiankuaiyun.Main.MineFragment.WoDeHuoYuan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhyan.shandiankuaiyun.R;
import com.zhyan.shandiankuaiyunlib.Bean.MyHuoSourceBean;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by az on 2017/4/26.
 */

public class WoDeHuoYuanXRVAdapter extends RecyclerView.Adapter<WoDeHuoYuanXRVAdapter.ItemViewHolder>  {

    private MyHuoSourceBean stringList;
    private Context context;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;
    public WoDeHuoYuanXRVAdapter(Context context1, MyHuoSourceBean stringList1){
        stringList = stringList1;
        if(stringList.getContent() == null){
            stringList.setContent(new ArrayList<MyHuoSourceBean.ContentBean>());
        }
        context = context1;
        inflater = LayoutInflater.from(context1);
        imageLoader = ImageLoader.getInstance();
    }
    public void setAdapter(MyHuoSourceBean myHuoSourceBeen){
        stringList = myHuoSourceBeen;
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflater.inflate(R.layout.activity_main_mine_wodehuoyuan_content_xrv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        imageLoader.displayImage(stringList.getContent().get(position).getImg1(),holder.ivMainMineWoDeHuoYuan, ImageLoaderUtils.options1);
        holder.tvMainMineWoDeHuoYuanName.setText(stringList.getContent().get(position).getAddress_set()+" - "+stringList.getContent().get(position).getAddress_out());
        holder.tvMainMineWoDeHuoYuanTime.setText(stringList.getContent().get(position).getCreate_time());
        holder.tvMainMineWoDeHuoYuanTimes.setText(stringList.getContent().get(position).getNum());
       /* imageLoader.displayImage(holder.ivMainMineWoDeHuoYuan,stringList.getContent().get(position).getImg1());*/
    }

    @Override
    public int getItemCount() {
        return stringList.getContent().size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_main_mine_wodehuoyuan)
        ImageView ivMainMineWoDeHuoYuan;
        @BindView(R.id.tv_main_mine_wodehuoyuan_name)
        TextView tvMainMineWoDeHuoYuanName;

        @BindView(R.id.tv_main_mine_wodehuoyuan_time)
        TextView tvMainMineWoDeHuoYuanTime;
        @BindView(R.id.tv_main_mine_wodehuoyuan_times)
        TextView tvMainMineWoDeHuoYuanTimes;

        public ItemViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
