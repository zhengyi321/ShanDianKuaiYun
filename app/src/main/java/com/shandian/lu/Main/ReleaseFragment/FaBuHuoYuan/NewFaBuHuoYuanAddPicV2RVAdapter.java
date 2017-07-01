package com.shandian.lu.Main.ReleaseFragment.FaBuHuoYuan;

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
import android.widget.LinearLayout;
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

public class NewFaBuHuoYuanAddPicV2RVAdapter extends RecyclerView.Adapter<NewFaBuHuoYuanAddPicV2RVAdapter.MyItemViewHolder> {

    private ArrayList<String> tempList;
    private Activity activity;
    private LayoutInflater inflater;
    private  final int ACTIVITY_REQUEST_SELECT_PHOTO = 100;
    public boolean isPicFinished = true;
    public boolean isUpdate = false;
    private ArrayList<String> netImageList;
    private ArrayList<String> deleteImageList;
    private ArrayList<String> mImageList;
    private ArrayList<String> allImageList;
    private boolean isFirst = true;
    private List<Bitmap> bitmapList;
    private ProgressBar pbNewFaBuHuoYuan;
    private int i,picSize;
    private List<MyItemViewHolder> myItemViewHolders;
    private int viewPos = -1;
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
    public NewFaBuHuoYuanAddPicV2RVAdapter(Activity activity1, ArrayList<String> tempList1, ProgressBar progressBar1){
        activity = activity1;
        tempList = tempList1;
        inflater = LayoutInflater.from(activity1);
/*        mImageList = new ArrayList<>();*/
        deleteImageList = new ArrayList<>();
        netImageList = new ArrayList<>();
        mImageList = new ArrayList<>();
        bitmapList = new ArrayList<>();
        allImageList = new ArrayList<>();
        pbNewFaBuHuoYuan = progressBar1;
        myItemViewHolders = new ArrayList<>();
    }

    @Override
    public MyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyItemViewHolder(inflater.inflate(R.layout.activity_new_fabuhuoyuan_content_rv_item_lly,parent,false));
    }

/*    public void setmImageList(ArrayList<String> arrayList){
        mImageList.clear();
        mImageList.addAll(arrayList);
        notifyDataSetChanged();
    }*/
  /*  public void setNetImageList(ArrayList<String> netImageList1){
        netImageList.clear();

        netImageList.addAll(netImageList1);
        notifyDataSetChanged();

    }*/


    public ArrayList<String> getNetImageList(){
        return netImageList;
    }
    public ArrayList<String> getAllImgList(){
        return allImageList;
    }



    public ArrayList<String> getDeleteImageLists(){
        return deleteImageList;
    }

    public void setAdapterImage(List<String> adapterImage){
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
        myItemViewHolders.add(holder);
        viewPos = position;

        int count = tempList.size();
        if(count <= 1){
            holder.rcivNewMainReleaseFaBuHuoYuanAdd.setImageResource(R.mipmap.pic_add);
            holder.rcivNewMainReleaseFaBuHuoYuanAdd.setClickable(true);
            holder.ivNewMainReleaseFaBuHuoYuanDelete.setVisibility(View.GONE);
            return;
        }
        if(position < count - 1){
                if(isUpdate){
                    int bitSize = bitmapList.size();
                    if(position < bitSize){

                            String pic = allImageList.get(position);
                            int indexOf = pic.indexOf(":");
                            if(indexOf <= 0){
                                Bitmap bitmap = bitmapList.get(position);

                                holder.rcivNewMainReleaseFaBuHuoYuanAdd.setImageBitmap(bitmap);

                            }else{
                                ImageLoader.getInstance().displayImage(tempList.get(position), holder.rcivNewMainReleaseFaBuHuoYuanAdd, ImageLoaderUtils.options1);
                            }

                    }else {
                        ImageLoader.getInstance().displayImage(tempList.get(position), holder.rcivNewMainReleaseFaBuHuoYuanAdd, ImageLoaderUtils.options1);
                    }
                }else {
                    int bitSize = bitmapList.size();
                    if(position < bitSize){
                        holder.rcivNewMainReleaseFaBuHuoYuanAdd.setImageBitmap(bitmapList.get(position));
                    }
                /*
                    int size = allImageList.size();
                    if (size > 0) {
                       *//* if(position == size-1) {
                            Bitmap bitmap = bitmapList.get(position);

                            holder.ivNewMainReleaseFaBuHuoYuanAdd.setImageBitmap(bitmap);
                        }*//*
                       if(position == size){
                           holder.ivNewMainReleaseFaBuHuoYuanAdd.setBackgroundResource(R.mipmap.pic_add);
                       }else{
                           int bitSize = bitmapList.size();
                           if(position < bitSize){
                               holder.ivNewMainReleaseFaBuHuoYuanAdd.setImageBitmap(bitmapList.get(position));
                           }

                       }

                    } else {
                        holder.ivNewMainReleaseFaBuHuoYuanAdd.setBackgroundResource(R.mipmap.pic_add);
                    }*/
                }

            holder.rcivNewMainReleaseFaBuHuoYuanAdd.setClickable(false);
            holder.ivNewMainReleaseFaBuHuoYuanDelete.setVisibility(View.VISIBLE);

           /* File file = new File(tempList.get(position));
            if (file.exists()) {
                Bitmap bm = compressImageFromFile(tempList.get(position));
                //将图片显示到ImageView中
                holder.ivNewMainReleaseFaBuHuoYuanAdd.setImageBitmap(bm);

                holder.ivNewMainReleaseFaBuHuoYuanAdd.setClickable(false);
                holder.ivNewMainReleaseFaBuHuoYuanDelete.setVisibility(View.VISIBLE);
            }*/

        }else{

            int size = allImageList.size();
            if(position == size-1){
                int bitSize = bitmapList.size();
                if(position == bitSize -1) {
                    Bitmap bitmap = bitmapList.get(position);

                    holder.rcivNewMainReleaseFaBuHuoYuanAdd.setImageBitmap(bitmap);

                }else{
                    ImageLoader.getInstance().displayImage(tempList.get(position), holder.rcivNewMainReleaseFaBuHuoYuanAdd, ImageLoaderUtils.options1);
                }

                holder.rcivNewMainReleaseFaBuHuoYuanAdd.setClickable(false);
            }else {
                holder.rcivNewMainReleaseFaBuHuoYuanAdd.setImageResource(R.mipmap.pic_add);
                holder.rcivNewMainReleaseFaBuHuoYuanAdd.setClickable(true);
                holder.ivNewMainReleaseFaBuHuoYuanDelete.setVisibility(View.GONE);
            }

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



    private void sendPicToNet(){



        if(i < picSize) {
            String pic = mImageList.get(i);

                int indexOf = pic.indexOf(":");
                if(indexOf > 0){
                   /* Toast.makeText(activity,"no up",Toast.LENGTH_LONG).show()*/;
                    i++;
                    sendPicToNet();
                    return;
                }
            if(viewPos != -1) {
                myItemViewHolders.get(0).pbNewFaBuHuoYuanPicItem.setVisibility(View.VISIBLE);
            }
          /*  pbNewFaBuHuoYuan.setVisibility(View.VISIBLE);*/
            isPicFinished = false;
            NewFaBuNetWork newFaBuNetWork = new NewFaBuNetWork();
            newFaBuNetWork.upPicToNet(getParamMap(), new Observer<NewFaBuPicBean>() {
                @Override
                public void onCompleted() {
                    if(viewPos != -1) {
                        myItemViewHolders.get(0).pbNewFaBuHuoYuanPicItem.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    if(viewPos != -1) {
                        myItemViewHolders.get(0).pbNewFaBuHuoYuanPicItem.setVisibility(View.GONE);
                    }
                 /*   Toast.makeText(activity,"Throwable:"+e,Toast.LENGTH_LONG).show();*/
                }

                @Override
                public void onNext(NewFaBuPicBean newFaBuPicBean) {
                    if(newFaBuPicBean.getStatus().equals("0")){
                        netImageList.add("\""+newFaBuPicBean.getImgurl()+"\"");
                        /*pbNewFaBuHuoYuan.setVisibility(View.GONE);*/
                        if(viewPos != -1) {
                            myItemViewHolders.get(0).pbNewFaBuHuoYuanPicItem.setVisibility(View.GONE);
                        }


                     /*   Toast.makeText(activity,"size"+netImageList.size()+newFaBuPicBean.getImgurl(),Toast.LENGTH_LONG).show();*/
                        notifyDataSetChanged();
                        i++;
                        sendPicToNet();
                    }
                }
            });
        }else{
        /*    pbNewFaBuHuoYuan.setVisibility(View.GONE);*/
            if(viewPos != -1) {
                myItemViewHolders.get(0).pbNewFaBuHuoYuanPicItem.setVisibility(View.GONE);
            }
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
        @BindView(R.id.pb_new_fabuhuoyuan_pic_item)
        ProgressBar pbNewFaBuHuoYuanPicItem;
        @BindView(R.id.rciv_new_main_release_fabuhuoyuan_add)
        RoundCornerImageView rcivNewMainReleaseFaBuHuoYuanAdd;
        @OnClick(R.id.rciv_new_main_release_fabuhuoyuan_add)
        public void rcivNewMainReleaseFaBuHuoYuanAddOnclick(){
            if(pos == (tempList.size() -1)){
                fromAlbum();
            }
        }
        @BindView(R.id.iv_new_main_release_fabuhuoyuan_delete)
        ImageView ivNewMainReleaseFaBuHuoYuanDelete;
        @OnClick(R.id.iv_new_main_release_fabuhuoyuan_delete)
        public void ivNewMainReleaseFaBuHuoYuanDeleteOnclick(){
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
               /* Toast.makeText(activity,"this is after finish",Toast.LENGTH_LONG).show();*/
                int netImgSize = netImageList.size();
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
