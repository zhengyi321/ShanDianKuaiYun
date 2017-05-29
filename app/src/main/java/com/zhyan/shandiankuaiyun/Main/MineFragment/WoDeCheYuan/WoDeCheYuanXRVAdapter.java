package com.zhyan.shandiankuaiyun.Main.MineFragment.WoDeCheYuan;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhyan.shandiankuaiyun.R;
import com.zhyan.shandiankuaiyunlib.Bean.MyCarSourceBean;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by az on 2017/4/26.
 */

public class WoDeCheYuanXRVAdapter extends RecyclerView.Adapter<WoDeCheYuanXRVAdapter.ItemViewHolder>  {

    private MyCarSourceBean stringList;
    private Context context;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;
    public WoDeCheYuanXRVAdapter(Context context1, MyCarSourceBean stringList1){
        stringList = stringList1;
        if (stringList.getContent() == null){
            stringList.setContent(new ArrayList<MyCarSourceBean.ContentBean>());
        }
        imageLoader = ImageLoader.getInstance();
        context = context1;
        inflater = LayoutInflater.from(context1);
    }
    public void setAdapter(MyCarSourceBean myCarSourceBean){

        stringList = myCarSourceBean;
        notifyDataSetChanged();

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflater.inflate(R.layout.activity_main_mine_wodecheyuan_content_xrv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        imageLoader.displayImage(stringList.getContent().get(position).getImg1(),holder.ivMainMineWoDeHuoYuan, ImageLoaderUtils.options1);
        holder.tvMainMineWoDeCheYuanName.setText(stringList.getContent().get(position).getAddress_set()+" - "+stringList.getContent().get(position).getAddress_out());
        holder.tvMainMineWoDeCheYuanTime.setText(stringList.getContent().get(position).getCreate_time());
        holder.tvMainMineWoDeCheYuanTimes.setText(stringList.getContent().get(position).getNum());
    }

    @Override
    public int getItemCount() {
        return stringList.getContent().size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.iv_main_mine_wodecheyuan)
        ImageView ivMainMineWoDeHuoYuan;
        @BindView(R.id.tv_main_mine_wodecheyuan_name)
        TextView tvMainMineWoDeCheYuanName;
        @BindView(R.id.tv_main_mine_wodecheyuan_time)
        TextView tvMainMineWoDeCheYuanTime;
        @BindView(R.id.tv_main_mine_wodecheyuan_times)
        TextView tvMainMineWoDeCheYuanTimes;
        public ItemViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
