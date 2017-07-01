package com.shandian.lu.Main.IndexFragment.NewHuoYuanDetail;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

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

public class NewHuoYuanDetailImgRVAdapter extends RecyclerView.Adapter<NewHuoYuanDetailImgRVAdapter.MyItemViewHold> {

    private Activity activity;
    private ArrayList<String> imgList;
    private LayoutInflater inflater;
    private int ACTIVITY_REQUEST_PREVIEW_PHOTO = 111;
    public NewHuoYuanDetailImgRVAdapter(Activity activity1, ArrayList<String> imgList1){
        activity = activity1;
        imgList = imgList1;
        inflater = LayoutInflater.from(activity1);
    }

    public void setAdapter(List<String> imgList1){
        imgList.clear();
        imgList.addAll(imgList1);
   /*     Toast.makeText(activity,"imgList:"+imgList1.size(),Toast.LENGTH_LONG).show();*/
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
        holder.pos = position;
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
        @OnClick(R.id.iv_new_self_other_huoyuanxiangqing)
        public void ivNewSelfOtherHuoYuanXiangQingOnclick(){
            previewImage(pos);
        }


        private void previewImage(int position) {
            Album.gallery(activity)
                    .requestCode(ACTIVITY_REQUEST_PREVIEW_PHOTO)
                    .toolBarColor(ContextCompat.getColor(activity, R.color.colorPrimary)) // Toolbar color.
                    .statusBarColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark)) // StatusBar color.
                    .navigationBarColor(ActivityCompat.getColor(activity, R.color.colorPrimaryBlack)) // NavigationBar color.
                    .checkedList(imgList) // Image list.
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
