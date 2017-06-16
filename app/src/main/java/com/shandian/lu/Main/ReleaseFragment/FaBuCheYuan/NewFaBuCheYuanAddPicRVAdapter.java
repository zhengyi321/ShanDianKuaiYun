package com.shandian.lu.Main.ReleaseFragment.FaBuCheYuan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewFaBuPicBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.Main.ReleaseFragment.FaBuHuoYuan.NewFaBuHuoYuanAddPicRVAdapter;
import com.shandian.lu.NetWork.NewFaBuNetWork;
import com.shandian.lu.R;
import com.yanzhenjie.album.Album;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.BitmapUtils;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/6/7.
 */

public class NewFaBuCheYuanAddPicRVAdapter extends RecyclerView.Adapter<NewFaBuCheYuanAddPicRVAdapter.MyItemViewHolder> {


    private List<String> tempList;
    private Activity activity;
    private LayoutInflater inflater;
    private  final int ACTIVITY_REQUEST_SELECT_PHOTO = 100;
    public boolean isPicFinished = true;
    public boolean isUpdate = false;
    private ArrayList<String> netImageList;
    private ArrayList<String> deleteImageList;
    private ArrayList<String> mImageList;
    private ArrayList<String> allImageList;
    private List<Bitmap> bitmapList;
    private ProgressBar pbNewFaBuHuoYuan;
    private boolean isFirst = true;
    private int i,picSize;
    private Handler mHandler = new Handler(){


        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                //根据msg.what的值来处理不同的UI操作
                case 0:
                    notifyDataSetChanged();
                    break;

            }

        }
    };
    public NewFaBuCheYuanAddPicRVAdapter(Activity activity1, List<String> tempList1,ProgressBar progressBar1){
        activity = activity1;
        tempList = tempList1;
        inflater = LayoutInflater.from(activity1);

        deleteImageList = new ArrayList<>();
        netImageList = new ArrayList<>();
        mImageList = new ArrayList<>();
        bitmapList = new ArrayList<>();
        allImageList = new ArrayList<>();
        pbNewFaBuHuoYuan = progressBar1;
    }

    @Override
    public MyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyItemViewHolder(inflater.inflate(R.layout.activity_new_fabucheyuan_content_rv_item_lly,parent,false));
    }
    public ArrayList<String> getNetImageList(){
        return netImageList;
    }
    public ArrayList<String> getAllImgList(){
        return allImageList;
    }
    public ArrayList<String> getDeleteImageLists(){
        return deleteImageList;
    }

    public void setAdapterImage(ArrayList<String> adapterImage){
        tempList.clear();
        tempList.addAll(adapterImage);
        allImageList.clear();
        allImageList.addAll(tempList);
        if(tempList.size() < 8) {
            tempList.add("");
        }

        Thread myThread = new MyNewBitMapThread();
        myThread.start();
        notifyDataSetChanged();
    }
    public void setUpdateList(List<String> adapterImage){
        isUpdate = true;
        tempList.clear();
        tempList.addAll(adapterImage);
        allImageList.clear();
        allImageList.addAll(tempList);

        if(tempList.size() < 8) {
            tempList.add("");
        }

        notifyDataSetChanged();
    }
    public void setNetImageList(ArrayList<String> netImageList1){
        netImageList.clear();
        netImageList.addAll(netImageList1);
        notifyDataSetChanged();
    }

    public void setNewImgList(ArrayList<String> newImgList1){
        mImageList.clear();
        mImageList.addAll(newImgList1);
        picSize = mImageList.size();
        if(picSize <= 0){
            return;
        }
        i = 0;
        sendPicToNet();
    }
    @Override
    public void onBindViewHolder(MyItemViewHolder holder, int position) {
        holder.pos = position;
        int count = tempList.size();

        if(count <= 1){
            holder.ivNewMainReleaseFaBuCheYuanAdd.setImageResource(R.mipmap.pic_add);
            holder.ivNewMainReleaseFaBuCheYuanAdd.setClickable(true);
            holder.ivNewMainReleaseFaBuCheYuanDelete.setVisibility(View.GONE);
          /*  Toast.makeText(activity,"count:"+count,Toast.LENGTH_LONG).show();*/
            return;
        }
        if(position < count - 1){

            if(isUpdate) {
                int bitSize = bitmapList.size();
                if(position < bitSize){

                    String pic = allImageList.get(position);
                    int indexOf = pic.indexOf(":");
                    if (indexOf <= 0) {
                        Bitmap bitmap = bitmapList.get(position);

                        holder.ivNewMainReleaseFaBuCheYuanAdd.setImageBitmap(bitmap);

                    } else {
                        ImageLoader.getInstance().displayImage(tempList.get(position), holder.ivNewMainReleaseFaBuCheYuanAdd, ImageLoaderUtils.options1);
                    }

                } else {
                    ImageLoader.getInstance().displayImage(tempList.get(position), holder.ivNewMainReleaseFaBuCheYuanAdd, ImageLoaderUtils.options1);
                }
            }else{
                int bitSize = bitmapList.size();
                if(position < bitSize){
                    holder.ivNewMainReleaseFaBuCheYuanAdd.setImageBitmap(bitmapList.get(position));
                }
               /* int size = allImageList.size();
                if (size > 0) {
                   *//* if(position == size-1) {
                        Bitmap bitmap = bitmapList.get(position);

                        holder.ivNewMainReleaseFaBuHuoYuanAdd.setImageBitmap(bitmap);
                    }*//*
                    if(position == size){
                        holder.ivNewMainReleaseFaBuCheYuanAdd.setBackgroundResource(R.mipmap.pic_add);
                    }else{
                        int bitSize = bitmapList.size();
                        if(position < bitSize){
                            holder.ivNewMainReleaseFaBuCheYuanAdd.setImageBitmap(bitmapList.get(position));
                        }

                    }

                } else {
                    holder.ivNewMainReleaseFaBuCheYuanAdd.setBackgroundResource(R.mipmap.pic_add);
                }*/
            }
            holder.ivNewMainReleaseFaBuCheYuanAdd.setClickable(false);
            holder.ivNewMainReleaseFaBuCheYuanDelete.setVisibility(View.VISIBLE);
        }else{

            int size = allImageList.size();
            if(position == size-1){
                int bitSize = bitmapList.size();
                if(position == bitSize -1) {
                    Bitmap bitmap = bitmapList.get(position);

                    holder.ivNewMainReleaseFaBuCheYuanAdd.setImageBitmap(bitmap);

                }else{
                    ImageLoader.getInstance().displayImage(tempList.get(position), holder.ivNewMainReleaseFaBuCheYuanAdd, ImageLoaderUtils.options1);
                }

                holder.ivNewMainReleaseFaBuCheYuanAdd.setClickable(false);
            }else {
                holder.ivNewMainReleaseFaBuCheYuanAdd.setImageResource(R.mipmap.pic_add);
                holder.ivNewMainReleaseFaBuCheYuanAdd.setClickable(true);
                holder.ivNewMainReleaseFaBuCheYuanDelete.setVisibility(View.GONE);
            }
        }
    }

    //图片压缩
    private Bitmap compressImageFromFile(String srcPath) {

        BitmapUtils bitmapUtils = new BitmapUtils();
        Bitmap bitmap = bitmapUtils.getimage(srcPath);
/*        bitmap = bitmapUtils.compressImage(bitmap);
        bitmap = bitmapUtils.comp(bitmap);*/

        return bitmap;
    }

    private void sendPicToNet(){


        if(i < picSize) {

            String pic = mImageList.get(i);

            int indexOf = pic.indexOf(":");
            if(indexOf > 0){
               /* Toast.makeText(activity,"no up",Toast.LENGTH_LONG).show();*/
                i++;
                sendPicToNet();
                return;
            }


            pbNewFaBuHuoYuan.setVisibility(View.VISIBLE);
            isPicFinished = false;

            NewFaBuNetWork newFaBuNetWork = new NewFaBuNetWork();
            newFaBuNetWork.upPicToNet(getParamMap(), new Observer<NewFaBuPicBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(NewFaBuPicBean newFaBuPicBean) {
                    if(newFaBuPicBean.getStatus().equals("0")){
                        netImageList.add("\""+newFaBuPicBean.getImgurl()+"\"");
                        pbNewFaBuHuoYuan.setVisibility(View.GONE);
                        isPicFinished = true;
                        notifyDataSetChanged();

                        i++;
                        sendPicToNet();
                    }
                }
            });
        }else{

            pbNewFaBuHuoYuan.setVisibility(View.GONE);
            isPicFinished = true;
        }
/*        System.out.print("\nbase64:"+base64_00);*/
        /*Log.i("base64:",base64_00);*/

    }

    private Map<String,String> getParamMap(){
        Map<String,String> paramMap = new HashMap<>();
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        String loginId= xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            Toast.makeText(activity,"请登录",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            return paramMap;
        }
        paramMap.put("login_id",loginId);

        BitmapUtils bitmapUtils = new BitmapUtils();
        Bitmap bm = compressImageFromFile(mImageList.get(i));
        //将图片显示到ImageView中
        String base64_00 = bitmapUtils.bitmapConvertBase64(bm);
        paramMap.put("tu",base64_00);
        return paramMap;

    }
    class MyNewBitMapThread extends Thread{
        @Override
        public void run(){
            int size = allImageList.size();
            bitmapList.clear();
            for(int i=0;i<size;i++){
                String pic = allImageList.get(i);
                int indeOfWWW = pic.indexOf(":");
                if(indeOfWWW > 0){
                    bitmapList.add(null);
                    continue;
                }
                Bitmap bm = compressImageFromFile(allImageList.get(i));
                bitmapList.add(bm);
            }
            Message message= new Message();
            message.what = 0;
            mHandler.sendMessage(message);

        }
    }

    @Override
    public int getItemCount() {
        return tempList.size();
    }

    public class MyItemViewHolder extends RecyclerView.ViewHolder{
        int pos = 0;

        @BindView(R.id.iv_new_main_release_fabucheyuan_add)
        ImageView ivNewMainReleaseFaBuCheYuanAdd;
        @OnClick(R.id.iv_new_main_release_fabucheyuan_add)
        public void ivNewMainReleaseFaBuCheYuanAddOnclick(){
            if(pos == (tempList.size() -1)){
                fromAlbum();
            }
        }
        @BindView(R.id.iv_new_main_release_fabucheyuan_delete)
        ImageView ivNewMainReleaseFaBuCheYuanDelete;
        @OnClick(R.id.iv_new_main_release_fabucheyuan_delete)
        public void ivNewMainReleaseFaBuCheYuanDeleteOnclick(){
      /*      Toast.makeText(activity,"pos:"+pos,Toast.LENGTH_LONG).show();*/
            if(isUpdate){
                if (!isPicFinished) {
                    return;
                }
                int netImgSize = netImageList.size();
                if (pos >= netImgSize) {
                    return;
                }
                tempList.remove(pos);
                String img = netImageList.get(pos);
                deleteImageList.add(img);
                netImageList.remove(pos);
                allImageList.remove(pos);
                if (bitmapList.size() != 0) {
                    bitmapList.remove(pos);
                }

            }else {
                if (!isPicFinished) {
                    return;
                }
                int netImgSize = tempList.size();
                if (pos >= netImgSize) {
                    return;
                }
                String img = netImageList.get(pos);
                deleteImageList.add(img);
                tempList.remove(pos);
                netImageList.remove(img);
                if (!isUpdate) {
                    bitmapList.remove(pos);
                } else {
                    if (bitmapList.size() != 0) {
                        bitmapList.remove(pos);
                    }
                }
                allImageList.remove(pos);
            }

            isPicFinished = true;
            if(allImageList.size() == 7) {
                tempList.add("");
            }
            notifyDataSetChanged();
        }

        public MyItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }



    private void fromAlbum() {
        if(isFirst){
            isPicFinished = false;
            Album.album(activity)
                    .requestCode(ACTIVITY_REQUEST_SELECT_PHOTO)
                    .toolBarColor(ContextCompat.getColor(activity, R.color.colorPrimary)) // Toolbar color.
                    .statusBarColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark)) // StatusBar color.
                    .navigationBarColor(ActivityCompat.getColor(activity, R.color.colorPrimaryBlack)) // NavigationBar color.
                    .selectCount(8) // select count.
                    .columnCount(2) // span count.
                    .camera(true) // has fromCamera function.
                    .checkedList(allImageList) // The picture has been selected for anti-election.
                    .start();

            isFirst = false;
            return;
        }
        if(!isPicFinished){
            return;
        }
        isPicFinished = false;
        Album.album(activity)
                .requestCode(ACTIVITY_REQUEST_SELECT_PHOTO)
                .toolBarColor(ContextCompat.getColor(activity, R.color.colorPrimary)) // Toolbar color.
                .statusBarColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark)) // StatusBar color.
                .navigationBarColor(ActivityCompat.getColor(activity, R.color.colorPrimaryBlack)) // NavigationBar color.
                .selectCount(8) // select count.
                .columnCount(2) // span count.
                .camera(true) // has fromCamera function.
                .checkedList(allImageList) // The picture has been selected for anti-election.
                .start();
    }
}
