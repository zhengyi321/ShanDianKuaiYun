package com.shandian.lu.Main.IndexFragment.NewCheYuanDetail;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.R;
import com.yanzhenjie.album.Album;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/9.
 */

public class NewCheYuanDetailImgRVAdapter extends RecyclerView.Adapter<NewCheYuanDetailImgRVAdapter.MyItemViewHold> {

    private Activity activity;
    private List<String> imgList;
    private LayoutInflater inflater;
    private int ACTIVITY_REQUEST_PREVIEW_PHOTO = 111;
    public NewCheYuanDetailImgRVAdapter(Activity activity1,List<String> imgList1){
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
        return new MyItemViewHold(inflater.inflate(R.layout.activity_new_self_cheyuanxiangqing_content_rv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(MyItemViewHold holder, int position) {
        holder.pos = position;
        ImageLoader.getInstance().displayImage(imgList.get(position),holder.ivNewSelfCheYuanXiangQing, ImageLoaderUtils.options1);
    }

    @Override
    public int getItemCount() {
        return imgList.size();
    }

    public class MyItemViewHold extends RecyclerView.ViewHolder{
        int pos = 0;
        @BindView(R.id.iv_new_self_cheyuanxiangqing)
        ImageView ivNewSelfCheYuanXiangQing;
        @OnClick(R.id.iv_new_self_cheyuanxiangqing)
        public void ivNewSelfCheYuanXiangQingOnclick(){
            previewImage(pos);
        }



        private void previewImage(int position) {
            Album.gallery(activity)
                    .requestCode(ACTIVITY_REQUEST_PREVIEW_PHOTO)
                    .toolBarColor(ContextCompat.getColor(activity, R.color.colorPrimary)) // Toolbar color.
                    .statusBarColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark)) // StatusBar color.
                    .navigationBarColor(ActivityCompat.getColor(activity, R.color.colorPrimaryBlack)) // NavigationBar color.
                    .checkedList((ArrayList<String>) imgList) // Image list.
                    .currentPosition(position) // Preview first to show the first few.
                    .checkFunction(true) // Does the user have an anti-selection when previewing.
                    .start();

        }
        public MyItemViewHold(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
