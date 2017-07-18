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
import com.shandian.lu.NetWork.NewFaBuNetWork;
import com.shandian.lu.R;
import com.yanzhenjie.album.Album;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.BitmapUtils;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ImageView.RoundCornerImageView.RoundCornerImageView;

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

public class NewFaBuCheYuanAddPicV3RVAdapter extends RecyclerView.Adapter<NewFaBuCheYuanAddPicV3RVAdapter.MyItemViewHolder> {



    private Activity activity;
    private LayoutInflater inflater;

    private  final int ACTIVITY_REQUEST_SELECT_PHOTO = 100;

    public int isPicFinishedCount= 0;
    public boolean isUpdate = false;
    private ArrayList<String> netImageList;
    private ArrayList<String> deleteImageList;
    private ArrayList<String> mImageList;


    public boolean isPicFinished = true;
    private boolean isFirst = true;
    private int i,picSize;
    private int viewPos = -1;
/*
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
    };*/
    public NewFaBuCheYuanAddPicV3RVAdapter(Activity activity1, ArrayList<String> mImageList1){
        activity = activity1;
        mImageList = mImageList1;
        inflater = LayoutInflater.from(activity1);
        deleteImageList = new ArrayList<>();
        netImageList = new ArrayList<>();

    }

    @Override
    public MyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyItemViewHolder(inflater.inflate(R.layout.activity_new_fabucheyuan_content_rv_item_lly,parent,false));
    }
    public ArrayList<String> getNetImageList(){
        return netImageList;
    }
    public ArrayList<String> getDeleteImageLists(){
        return deleteImageList;
    }

    public void setUpdateAdapter(List<String> newImgList){
        if(newImgList == null){
            return;
        }
        List<String> tempNewImgList = new ArrayList<>();
        tempNewImgList.addAll(newImgList);
        int newSize = newImgList.size();
        int mSize = mImageList.size();
        for(int i=0;i<newSize;i++){
            String newPic = newImgList.get(i);
            for(int j = 0;j<mSize;j++){
                String mPic = mImageList.get(j);
                if(newPic.equals(mPic)){
                    tempNewImgList.remove(newPic);
                }
            }
        }

        isUpdate = true;
        mImageList.clear();
        mImageList.addAll(netImageList);
        mImageList.addAll(tempNewImgList);
        int size = mImageList.size();
        if(size < 8){
            mImageList.add("");
        }
        notifyDataSetChanged();
    }





    @Override
    public void onBindViewHolder(MyItemViewHolder holder, int position) {
        holder.pos = position;
        if(isUpdate){
            int count = mImageList.size();
            if(position == count -1){
                String pic = mImageList.get(position);
                if(pic.isEmpty()){
                    holder.rcivNewMainReleaseFaBuCheYuanAdd.setImageResource(R.mipmap.pic_add);
                    holder.ivNewMainReleaseFaBuCheYuanDelete.setVisibility(View.GONE);
                    holder.rcivNewMainReleaseFaBuCheYuanAdd.setClickable(true);
                    return;
                }
            }

            int netSize = netImageList.size();
            System.out.print("\nnetSize:"+netSize);
            System.out.print("\nnetSize:"+netSize);
            System.out.print("\nnetSize:"+netSize);
            System.out.print("\nmImageListSize:"+count);
            System.out.print("\nmImageListSize:"+count);
            System.out.print("\nmImageListSize:"+count);
           /* Toast.makeText(activity,"netSize:"+netSize,3000).show();
            Toast.makeText(activity,"mImageListSize:"+count,3000).show();*/
            if(position < netSize){
                String pic = netImageList.get(position);
                int indexOfWWW = pic.indexOf(":");
                if(indexOfWWW < 0){
                    pic = pic.substring(1,pic.length()-1);
                    pic = "http://www.lianshiding.com"+pic;
                }
                String firstWord = pic.substring(0,1);
                if(!firstWord.equals("h")){
                    pic = pic.substring(1,pic.length()-1);
                }
                ImageLoader.getInstance().displayImage(pic,holder.rcivNewMainReleaseFaBuCheYuanAdd,ImageLoaderUtils.options1);
                holder.rcivNewMainReleaseFaBuCheYuanAdd.setClickable(false);
                holder.ivNewMainReleaseFaBuCheYuanDelete.setVisibility(View.VISIBLE);
                return;
            }
            if(isPicFinishedCount != 0){
                holder.rcivNewMainReleaseFaBuCheYuanAdd.setImageResource(R.mipmap.pic_add);
                holder.ivNewMainReleaseFaBuCheYuanDelete.setVisibility(View.VISIBLE);
                return;
            }
            String pic = mImageList.get(position);
            if(pic == null){
                return;
            }
            int indexOfWWW = pic.indexOf(":");
            if(indexOfWWW >= 0){
                String firstWord = pic.substring(0,1);
               /* Toast.makeText(activity,"fitst:"+firstWord,3000).show();*/
                if(!firstWord.equals("h")){
                    pic = pic.substring(1,pic.length()-1);
                }
                ImageLoader.getInstance().displayImage(pic,holder.rcivNewMainReleaseFaBuCheYuanAdd,ImageLoaderUtils.options1);
                holder.rcivNewMainReleaseFaBuCheYuanAdd.setClickable(false);
                holder.ivNewMainReleaseFaBuCheYuanDelete.setVisibility(View.VISIBLE);
                netImageList.add("\""+pic+"\"");
            }else {
               /* Toast.makeText(activity,"sec"+pic,3000).show();*/
                Bitmap bitmap = compressImageFromFile(pic);
                sendPicToNet(bitmap,holder.pbNewFaBuCheYuanPicItem,holder.rcivNewMainReleaseFaBuCheYuanAdd,pic,holder.ivNewMainReleaseFaBuCheYuanDelete);
            }

           /* if(position > newImagePos){
                String pic = mImageList.get(position);
                Bitmap bitmap = compressImageFromFile(pic);
                sendPicToNet(bitmap,holder.pbNewFaBuCheYuanPicItem,holder.rcivNewMainReleaseFaBuCheYuanAdd,pic,holder.ivNewMainReleaseFaBuCheYuanDelete);
                return;
            }
            String pic = mImageList.get(position);
            if((pic != null)&&(pic.isEmpty())){
                ImageLoader.getInstance().displayImage(pic,holder.rcivNewMainReleaseFaBuCheYuanAdd,ImageLoaderUtils.options1);
                holder.rcivNewMainReleaseFaBuCheYuanAdd.setClickable(false);
                holder.ivNewMainReleaseFaBuCheYuanDelete.setVisibility(View.VISIBLE);
            }*/

        }else {



        }

    }
    /*    Bitmap bitmap = compressImageFromFile(pic);
                if(tempPic.equals(pic)){
                    holder.rcivNewMainReleaseFaBuCheYuanAdd.setImageBitmap(bitmap);
                    return;
                }

                sendPicToNet(bitmap,holder.pbNewFaBuCheYuanPicItem,holder.rcivNewMainReleaseFaBuCheYuanAdd,pic,holder.ivNewMainReleaseFaBuCheYuanDelete);*/
    //图片压缩
    private Bitmap compressImageFromFile(String srcPath) {

        BitmapUtils bitmapUtils = new BitmapUtils();
        Bitmap bitmap = bitmapUtils.getimage(srcPath);
/*        bitmap = bitmapUtils.compressImage(bitmap);
        bitmap = bitmapUtils.comp(bitmap);*/

        return bitmap;
    }

    private void sendPicToNet(final Bitmap bm, final ProgressBar progressBar, final RoundCornerImageView roundCornerImageView, final String pic,final ImageView ivDelete){

            progressBar.setVisibility(View.VISIBLE);
            isPicFinished = false;
            isPicFinishedCount++;
            NewFaBuNetWork newFaBuNetWork = new NewFaBuNetWork();
            newFaBuNetWork.upPicToNet(getParamMap(bm), new Observer<NewFaBuPicBean>() {
                @Override
                public void onCompleted() {
                    progressBar.setVisibility(View.GONE);
                    isPicFinishedCount--;
                }

                @Override
                public void onError(Throwable e) {
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onNext(NewFaBuPicBean newFaBuPicBean) {
                    if(newFaBuPicBean.getStatus().equals("0")){
                        netImageList.add("\""+newFaBuPicBean.getImgurl()+"\"");
                        notifyDataSetChanged();
                        roundCornerImageView.setImageBitmap(bm);
                        roundCornerImageView.setClickable(false);
                        ivDelete.setVisibility(View.VISIBLE);
                        progressBar.setVisibility(View.GONE);

                        /*pbNewFaBuHuoYuan.setVisibility(View.GONE);*/
                        isPicFinished = true;
                    }
                }
            });

/*        System.out.print("\nbase64:"+base64_00);*/
        /*Log.i("base64:",base64_00);*/

    }

    private Map<String,String> getParamMap(Bitmap bm){
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
       /* Bitmap bm = compressImageFromFile(mImageList.get(i));*/
        //将图片显示到ImageView中
        String base64_00 = bitmapUtils.bitmapConvertBase64(bm);
        paramMap.put("tu",base64_00);
        return paramMap;

    }

    @Override
    public int getItemCount() {
        return mImageList.size();
    }

    public class MyItemViewHolder extends RecyclerView.ViewHolder{
        int pos = 0;
        @BindView(R.id.pb_new_fabucheyuan_pic_item)
        ProgressBar pbNewFaBuCheYuanPicItem;
        @BindView(R.id.rciv_new_main_release_fabucheyuan_add)
        RoundCornerImageView rcivNewMainReleaseFaBuCheYuanAdd;
        @OnClick(R.id.rciv_new_main_release_fabucheyuan_add)
        public void rcivNewMainReleaseFaBuCheYuanAddOnclick(){
            /*if(pos == (tempList.size() -1)){*/
                fromAlbum();
          /*  }*/
        }
        @BindView(R.id.iv_new_main_release_fabucheyuan_delete)
        ImageView ivNewMainReleaseFaBuCheYuanDelete;
        @OnClick(R.id.iv_new_main_release_fabucheyuan_delete)
        public void ivNewMainReleaseFaBuCheYuanDeleteOnclick(){
      /*      Toast.makeText(activity,"pos:"+pos,Toast.LENGTH_LONG).show();*/
            int netSize = netImageList.size();
            if(pos >= netSize){
                return;
            }
            mImageList.remove(pos);
            String pic = netImageList.get(pos);
            netImageList.remove(pos);
            deleteImageList.add(pic);
            int size = netImageList.size();
            /*Toast.makeText(activity,"size:"+size,3000).show();*/
            if(size == 7){
                mImageList.add("");
            }

            notifyDataSetChanged();
        }

        public MyItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }



    private void fromAlbum() {

        if(!isPicFinished){
            return;
        }
/*        int wwwSize = 0;*/
        int size = netImageList.size();
      /*  for(int i=0;i<size;i++){
            String pic = mImageList.get(i);
            int indexOfWWW = pic.indexOf(":");
            if(indexOfWWW >= 0 ){
                wwwSize++;
            }
        }*/
        int selectCount = 8 - size;
        mImageList.clear();
        mImageList.addAll(netImageList);
        if(mImageList.size() < 8){
            mImageList.add("");
        }
        Album.album(activity)
                .requestCode(ACTIVITY_REQUEST_SELECT_PHOTO)
                .toolBarColor(ContextCompat.getColor(activity, R.color.colorPrimary)) // Toolbar color.
                .statusBarColor(ContextCompat.getColor(activity, R.color.colorPrimaryDark)) // StatusBar color.
                .navigationBarColor(ActivityCompat.getColor(activity, R.color.colorPrimaryBlack)) // NavigationBar color.
                .selectCount(selectCount) // select count.
                .columnCount(2) // span count.
                .camera(true) // has fromCamera function.
                .checkedList(mImageList) // The picture has been selected for anti-election.
                .start();
    }
}
