package com.shandian.lu.Main.IndexFragment.NewHuoYuanDetail;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/9.
 */

public class NewHuoYuanDetailImgRVAdapter extends RecyclerView.Adapter<NewHuoYuanDetailImgRVAdapter.MyItemViewHold> {

    private Activity activity;
    private List<String> imgList;
    private LayoutInflater inflater;
    public NewHuoYuanDetailImgRVAdapter(Activity activity1, List<String> imgList1){
        activity = activity1;
        imgList = imgList1;
        inflater = LayoutInflater.from(activity1);
    }

    public void setAdapter(List<String> imgList1){
        imgList.clear();
        imgList.addAll(imgList1);
        notifyDataSetChanged();
    }
    @Override
    public MyItemViewHold onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyItemViewHold(inflater.inflate(R.layout.activity_new_self_other_huoyuanxiangqing_content_rv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(MyItemViewHold holder, int position) {
        System.out.print(imgList.get(position)+"\n");
        System.out.print(imgList.get(position)+"\n");
        System.out.print(imgList.get(position)+"\n");
        System.out.print(imgList.get(position)+"\n");
        ImageLoader.getInstance().displayImage(imgList.get(position),holder.ivNewSelfOtherHuoYuanXiangQing, ImageLoaderUtils.options1);
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    public class MyItemViewHold extends RecyclerView.ViewHolder{
        int pos = 0;
        @BindView(R.id.iv_new_self_other_huoyuanxiangqing)
        ImageView ivNewSelfOtherHuoYuanXiangQing;

        public MyItemViewHold(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}