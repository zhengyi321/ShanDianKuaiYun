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
    public boolean isPicFinished = false;
    public boolean isUpdate = false;
    private ArrayList<String> netImageList;
    private ArrayList<String> deleteImageList;
    private ArrayList<String> mImageList;
    private ArrayList<String> allImageList;
    private List<Bitmap> bitmapList;
    private ProgressBar pbNewFaBuHuoYuan;
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
        isPicFinished= true;
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
            return;
        }
        if(position != count - 1){

            if(isUpdate){
                if(bitmapList.size()!= 0){
                    if (i == position) {
                        holder.ivNewMainReleaseFaBuCheYuanAdd.setImageBitmap(bitmapList.get(i));
                    } else {
                        holder.ivNewMainReleaseFaBuCheYuanAdd.setBackgroundResource(R.mipmap.pic_add);
                    }
                }else {
                    ImageLoader.getInstance().displayImage(tempList.get(position), holder.ivNewMainReleaseFaBuCheYuanAdd, ImageLoaderUtils.options1);
                }
            }else {

                int size = bitmapList.size();
                if (size > 0) {
                    if (i == position) {
                        holder.ivNewMainReleaseFaBuCheYuanAdd.setImageBitmap(bitmapList.get(i));
                    } else {
                        holder.ivNewMainReleaseFaBuCheYuanAdd.setBackgroundResource(R.mipmap.pic_add);
                    }
                } else {
                    holder.ivNewMainReleaseFaBuCheYuanAdd.setBackgroundResource(R.mipmap.pic_add);
                }
            }

            holder.ivNewMainReleaseFaBuCheYuanAdd.setClickable(false);
            holder.ivNewMainReleaseFaBuCheYuanDelete.setVisibility(View.VISIBLE);
        }else{
            holder.ivNewMainReleaseFaBuCheYuanAdd.setImageResource(R.mipmap.pic_add);
            holder.ivNewMainReleaseFaBuCheYuanAdd.setClickable(true);
            holder.ivNewMainReleaseFaBuCheYuanDelete.setVisibility(View.GONE);
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
        isPicFinished = false;

        if(i < picSize) {
            pbNewFaBuHuoYuan.setVisibility(View.VISIBLE);
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
            for(int i=0;i<size;i++){
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
            int netImgSize = tempList.size();
            if(pos >= netImgSize){
                return;
            }
            String img = netImageList.get(pos);
            deleteImageList.add(img);
            tempList.remove(pos);
            netImageList.remove(img);
            if(!isUpdate) {
                bitmapList.remove(pos);
            }
            if(bitmapList.size() != 0){
                bitmapList.remove(pos);
            }
            allImageList.remove(pos);
            ivNewMainReleaseFaBuCheYuanAdd.setImageResource(R.mipmap.pic_add);
            ivNewMainReleaseFaBuCheYuanAdd.setClickable(true);
            ivNewMainReleaseFaBuCheYuanDelete.setVisibility(View.GONE);
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
                .checkedList(allImageList) // The picture has been selected for anti-election.
                .start();
    }
}