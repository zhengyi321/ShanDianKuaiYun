package com.shandian.lu.Main.ReleaseFragment.FaBuHuoYuan;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.shandian.lu.Main.IndexFragment.CityChange.CountryXRVAdapter;
import com.shandian.lu.R;
import com.yanzhenjie.album.Album;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.BitmapUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/7.
 */

public class NewFaBuHuoYuanAddPicRVAdapter extends RecyclerView.Adapter<NewFaBuHuoYuanAddPicRVAdapter.MyItemViewHolder> {

    private List<String> tempList;
    private Activity activity;
    private LayoutInflater inflater;
    private  final int ACTIVITY_REQUEST_SELECT_PHOTO = 100;
    private ArrayList<String> mImageList;
    private ArrayList<String> netImageList;
    private ArrayList<String> deleteImageList;

    public NewFaBuHuoYuanAddPicRVAdapter(Activity activity1, List<String> tempList1){
        activity = activity1;
        tempList = tempList1;
        inflater = LayoutInflater.from(activity1);
        mImageList = new ArrayList<>();
        deleteImageList = new ArrayList<>();
        netImageList = new ArrayList<>();
    }

    @Override
    public MyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyItemViewHolder(inflater.inflate(R.layout.activity_new_fabuhuoyuan_content_rv_item_lly,parent,false));
    }

    public void setmImageList(ArrayList<String> arrayList){
        mImageList.clear();
        mImageList.addAll(arrayList);
    }
    public void setNetImageList(ArrayList<String> arrayList){
        netImageList.clear();
        netImageList.addAll(arrayList);
    }

    public ArrayList<String> getNetImageList(){
        return netImageList;
    }

    public ArrayList<String> getDeleteImageLists(){
        return deleteImageList;
    }

    public void setAdapterImage(ArrayList<String> adapterImage){
        tempList.clear();
        tempList.addAll(adapterImage);
        tempList.add("");
        notifyDataSetChanged();
    }
    @Override
    public void onBindViewHolder(MyItemViewHolder holder, int position) {
        holder.pos = position;
        int count = tempList.size();
        if(count <= 1){
            return;
        }
        if(position != count - 1){
            File file = new File(mImageList.get(position));
            if (file.exists()) {
                Bitmap bm = compressImageFromFile(mImageList.get(position));
                //将图片显示到ImageView中
                holder.ivNewMainReleaseFaBuHuoYuanAdd.setImageBitmap(bm);

                holder.ivNewMainReleaseFaBuHuoYuanAdd.setClickable(false);
                holder.ivNewMainReleaseFaBuHuoYuanDelete.setVisibility(View.VISIBLE);
            }
        }else{
            holder.ivNewMainReleaseFaBuHuoYuanAdd.setImageResource(R.mipmap.pic_add);
            holder.ivNewMainReleaseFaBuHuoYuanAdd.setClickable(true);
            holder.ivNewMainReleaseFaBuHuoYuanDelete.setVisibility(View.GONE);
        }
    }

    //图片压缩
    private Bitmap compressImageFromFile(String srcPath) {

        BitmapUtils bitmapUtils = new BitmapUtils();
        Bitmap bitmap = bitmapUtils.getimage(srcPath);
        bitmap = bitmapUtils.compressImage(bitmap);
        bitmap = bitmapUtils.comp(bitmap);

        return bitmap;
    }
    @Override
    public int getItemCount() {
        return tempList.size();
    }

    public class MyItemViewHolder extends RecyclerView.ViewHolder{
        int pos = 0;

        @BindView(R.id.iv_new_main_release_fabuhuoyuan_add)
        ImageView ivNewMainReleaseFaBuHuoYuanAdd;
        @OnClick(R.id.iv_new_main_release_fabuhuoyuan_add)
        public void ivNewMainReleaseFaBuHuoYuanAddOnclick(){
            if(pos == (tempList.size() -1)){
                fromAlbum();
            }
        }
        @BindView(R.id.iv_new_main_release_fabuhuoyuan_delete)
        ImageView ivNewMainReleaseFaBuHuoYuanDelete;
        @OnClick(R.id.iv_new_main_release_fabuhuoyuan_delete)
        public void ivNewMainReleaseFaBuHuoYuanDeleteOnclick(){
      /*      Toast.makeText(activity,"pos:"+pos,Toast.LENGTH_LONG).show();*/

            int netImgSize = netImageList.size();
            if(pos >= netImgSize){
                return;
            }
            String img = netImageList.get(pos);
            deleteImageList.add(img);
            tempList.remove(pos);
            mImageList.remove(pos);
            netImageList.remove(img);
            ivNewMainReleaseFaBuHuoYuanAdd.setImageResource(R.mipmap.pic_add);
            ivNewMainReleaseFaBuHuoYuanAdd.setClickable(true);
            ivNewMainReleaseFaBuHuoYuanDelete.setVisibility(View.GONE);
            notifyDataSetChanged();
        }

        public MyItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }



    private void fromAlbum() {

        Album.album(activity)
                .requestCode(ACTIVITY_REQUEST_SELECT_PHOTO)
                .toolBarColor(ContextCompat.getColor(activity, R.color.colorPrimary)) // Toolbar color.
                .statusBarColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark)) // StatusBar color.
                .navigationBarColor(ActivityCompat.getColor(activity, R.color.colorPrimaryBlack)) // NavigationBar color.
                .selectCount(8) // select count.
                .columnCount(2) // span count.
                .camera(true) // has fromCamera function.
                .checkedList(mImageList) // The picture has been selected for anti-election.
                .start();
    }
}
