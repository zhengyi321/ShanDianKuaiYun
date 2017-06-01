package com.shandian.lu.Main.ReleaseFragment.ZhuanXianWuLiu;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shandian.lu.Main.ReleaseFragment.ZhuanXianWuLiu.MiaoShu.MiaoShuActivity;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.BitmapUtils;
import com.shandian.lu.BaseActivity;
import com.shandian.lu.Main.IndexFragment.CityChange.CityChangeActivity;
import com.shandian.lu.NetWork.MainReleaseNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.CarSourceBean;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by az on 2017/4/28.
 */

public class ZhuanXianWuLiuReleaseActivity extends BaseActivity {
    public   final int ACTIVITY_REQUEST_SELECT_PHOTO = 100;
    private final int CITY_CHANGE_SELECTED = 99;
    private final int OTHER_DESC = 98;

    private ZhuanXianWuLiuReleaseController zhuanXianWuLiuReleaseController;
    private boolean isBegin = true;
    private String aid,cid,tid,aid1,cid1,tid1;
    ArrayList<String> mImageList;
    String base64_00="",base64_01="",base64_02="",base64_03="",base64_04="",base64_05="",base64_06="",base64_07="";
    @BindView(R.id.ib_main_release_zhuanxianwuliu_pic_pick)
    ImageButton ibMainReleaseZhuanXianWuLiuPicPick;
    @OnClick(R.id.ib_main_release_zhuanxianwuliu_pic_pick)
    public void ibMainReleaseZhuanXianWuLiuPicPickOnclick(){
        Intent intent = new Intent(this,SelectPicActivity.class);

        intent.putStringArrayListExtra("mImageList", mImageList);
        startActivityForResult(intent,ACTIVITY_REQUEST_SELECT_PHOTO);

    }
    @BindView(R.id.lly_main_release_zhuanxianwuliu_content_begin_place)
    LinearLayout llyMainReleaseZhuanXianWuLiuContentBeginPlace;
    @BindView(R.id.tv_main_release_zhuanxianwuliu_content_begin_place)
    TextView tvMainReleaseZhuanXianWuLiuContentBeginPlace;
    @OnClick(R.id.lly_main_release_zhuanxianwuliu_content_begin_place)
    public void llyMainReleaseZhuanXianWuLiuContentBeginPlaceOnclick(){
        isBegin = true;
        Intent intent = new Intent(ZhuanXianWuLiuReleaseActivity.this, CityChangeActivity.class);
        startActivityForResult(intent,CITY_CHANGE_SELECTED);
    }
    @BindView(R.id.lly_main_release_zhuanxianwuliu_content_reach_place)
    LinearLayout llyMainReleaseZhuanXianWuLiuContentReachPlace;
    @BindView(R.id.tv_main_release_zhuanxianwuliu_content_reach_place)
    TextView tvMainReleaseZhuanXianWuLiuContentReachPlace;
    @OnClick(R.id.lly_main_release_zhuanxianwuliu_content_reach_place)
    public void llyMainReleaseZhuanXianWuLiuContentReachPlaceOnclick(){
        isBegin = false;
        Intent intent = new Intent(ZhuanXianWuLiuReleaseActivity.this, CityChangeActivity.class);
        startActivityForResult(intent,CITY_CHANGE_SELECTED);
    }
    @BindView(R.id.et_main_release_zhuanxianwuliu_content_title)
    EditText etMainReleaseZhuanXianWuLiuContentTitle;


    @BindView(R.id.tv_main_release_zhuanxianwuliu_content_cartype)
    TextView tvMainReleaseZhuanXianWuLiuContentCarType;
/*    @BindView(R.id.lly_main_release_zhuanxianwuliu_content_cartype)
    LinearLayout llyMainReleaseZhuanXianWuLiuContentCarType;
    @OnClick(R.id.lly_main_release_zhuanxianwuliu_content_cartype)
    public void llyMainReleaseZhuanXianWuLiuContentCarTypeOnclick(){

    }*/
    @BindView(R.id.tv_main_release_zhuanxianwuliu_content_carlength)
    TextView tvMainReleaseZhuanXianWuLiuContentCarLength;
 /*   @BindView(R.id.lly_main_release_zhuanxianwuliu_content_carlength)
    LinearLayout llyMainReleaseZhuanXianWuLiuContentCarLength;
    @OnClick(R.id.lly_main_release_zhuanxianwuliu_content_carlength)
    public void llyMainReleaseZhuanXianWuLiuContentCarLengthOnclick(){

    }*/

    @BindView(R.id.tv_main_release_zhuanxianwuliu_selected_num)
    TextView tvMainReleaseZhuanXianWuLiuSelectedNum;

    @BindView(R.id.et_main_release_zhuanxianwuliu_content_tel)
    EditText etMainReleaseZhuanXianWuLiuContentTel;

    @BindView(R.id.et_main_release_zhuanxianwuliu_content_lxr)
    EditText etMainReleaseZhuanXianWuLiuContentLXR;

    @BindView(R.id.et_main_release_zhuanxianwuliu_content_addr)
    EditText etMainReleaseZhuanXianWuLiuContentAddr;

    @BindView(R.id.lly_main_release_zhuanxianwuliu_content_desc)
    LinearLayout llyMainReleaseZhuanXianWuLiuContentDesc;
    @BindView(R.id.tv_main_release_zhuanxianwuliu_content_desc)
    TextView tvMainReleaseZhuanXianWuLiuContentDesc;
    @OnClick(R.id.lly_main_release_zhuanxianwuliu_content_desc)
    public void llyMainReleaseZhuanXianWuLiuContentDescOnclick(){
        Intent intent = new Intent(this, MiaoShuActivity.class);
        startActivityForResult(intent,OTHER_DESC);
    }
    @BindView(R.id.bt_main_release_zhuanxianwuliu_content_release)
    Button btMainReleaseZhuanXianWuLiuContentRelease;
    @OnClick(R.id.bt_main_release_zhuanxianwuliu_content_release)
    public void btMainReleaseZhuanXianWuLiuContentReleaseOnclick(){
        submitReleaseDataToNet();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_release_zhuanxianwuliu_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        mImageList = new ArrayList<String>();
        initController();
      /*  queue= Volley.newRequestQueue(this);*/

    }
    private void initController(){
        zhuanXianWuLiuReleaseController = new ZhuanXianWuLiuReleaseController(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
/*        Toast.makeText(ZhuanXianWuLiuReleaseActivity.this,"requestCode:"+requestCode,Toast.LENGTH_LONG).show();
        Toast.makeText(ZhuanXianWuLiuReleaseActivity.this,"resultCode:"+resultCode,Toast.LENGTH_LONG).show();
        Toast.makeText(ZhuanXianWuLiuReleaseActivity.this,"data:"+resultCode,Toast.LENGTH_LONG).show();*/
        Log.i("resultCode",resultCode+"");
        switch (resultCode) {
            case ACTIVITY_REQUEST_SELECT_PHOTO:
                mImageList = new ArrayList<>();
                mImageList = data.getStringArrayListExtra("mImageList");
                if(mImageList == null){
                    mImageList = new ArrayList<>();

                }
               /* Log.i("mImageListSize",mImageList.get(0)+"");
                Log.i("mImageListSize",mImageList.size()+"");
                Log.i("mImageListSize",mImageList.size()+"");
                Log.i("mImageListSize",mImageList.size()+"");*/
                if(mImageList.size()>0) {
                    File file = new File(mImageList.get(0));
                    if (file.exists()) {
                        Bitmap bm = compressImageFromFile(mImageList.get(0));
                        //将图片显示到ImageView中
                        ibMainReleaseZhuanXianWuLiuPicPick.setImageBitmap(bm);
                        base64_00 = "";
                        base64_00 = bitmapConvertBase64(bm);
                        if((base64_00 == null )||(base64_00.isEmpty())){
                            base64_00 = "0";
                        }
                    }
                    tvMainReleaseZhuanXianWuLiuSelectedNum.setText("已选择"+mImageList.size()+"张照片");

                    Log.i("\nthis is mImageList:",""+mImageList.size());

                }else{
                    tvMainReleaseZhuanXianWuLiuSelectedNum.setText("");

                    ibMainReleaseZhuanXianWuLiuPicPick.setImageResource(R.mipmap.paizhao);
                }

                break;
            case CITY_CHANGE_SELECTED:
                Bundle b=data.getExtras(); //data为B中回传的Intent
                String city=b.getString("city");//str即为回传的值
                String id1 = b.getString("aid");
                String id2 = b.getString("cid");
                String id3 = b.getString("tid");
                /*String city = data.getStringExtra("city");*/
              /*  Toast.makeText(ZhuanXianWuLiuReleaseActivity.this,"city:"+city,Toast.LENGTH_LONG).show();*/
                if(city != null){
                    if(isBegin) {
                        aid = id1;
                        cid = id2;
                        tid = id3;
                        tvMainReleaseZhuanXianWuLiuContentBeginPlace.setText(city);
                    }else {
                        aid1 = id1;
                        cid1 = id2;
                        tid1 = id3;
                        tvMainReleaseZhuanXianWuLiuContentReachPlace.setText(city);
                    }
                }

                break;
            case OTHER_DESC:
                Bundle bundle = data.getExtras();
                String desc = bundle.getString("desc");
                if(desc != null){
                    tvMainReleaseZhuanXianWuLiuContentDesc.setText(desc);
                }
                break;

        }
    }
    public void initImgs(){
        int size = mImageList.size();
        if(size <= 0){
            Toast.makeText(this,"请至少上传一张图片",Toast.LENGTH_LONG).show();
            return;
        }
        for(int i = (size-1);i < 8;i++){
            mImageList.add("");
        }
        File file;
         file = new File(mImageList.get(0));
        if (file.exists()) {
            Bitmap bm = compressImageFromFile(mImageList.get(0));
            //将图片显示到ImageView中
            ibMainReleaseZhuanXianWuLiuPicPick.setImageBitmap(bm);

            base64_00 = bitmapConvertBase64(bm);
            if((base64_00 == null )||(base64_00.isEmpty())){
                base64_00 = "0";
            }
        }
         file = new File(mImageList.get(1));
        if (file.exists()) {
            Bitmap bm = compressImageFromFile(mImageList.get(1));
            //将图片显示到ImageView中
           /* ibMainReleaseZhuanXianWuLiuPicPick.setImageBitmap(bm);*/

            base64_01 = bitmapConvertBase64(bm);
            if((base64_01 == null )||(base64_01.isEmpty())){
                base64_01 = "0";
            }

        }
         file = new File(mImageList.get(2));
        if (file.exists()) {
            Bitmap bm = compressImageFromFile(mImageList.get(2));
            //将图片显示到ImageView中
            /*ibMainReleaseZhuanXianWuLiuPicPick.setImageBitmap(bm);*/

            base64_02 = bitmapConvertBase64(bm);
            if((base64_02 == null )||(base64_02.isEmpty())){
                base64_02 = "0";
            }

        }
        file = new File(mImageList.get(3));
        if (file.exists()) {
            Bitmap bm = compressImageFromFile(mImageList.get(3));
            //将图片显示到ImageView中
           /* ibMainReleaseZhuanXianWuLiuPicPick.setImageBitmap(bm);*/

            base64_03 = bitmapConvertBase64(bm);
            if((base64_03 == null )||(base64_03.isEmpty())){
                base64_03 = "0";
            }

        }
         file = new File(mImageList.get(4));
        if (file.exists()) {
            Bitmap bm = compressImageFromFile(mImageList.get(4));
            //将图片显示到ImageView中
          /*  ibMainReleaseZhuanXianWuLiuPicPick.setImageBitmap(bm);*/

            base64_04 = bitmapConvertBase64(bm);
            if((base64_04 == null )||(base64_04.isEmpty())){
                base64_04 = "0";
            }

        }
         file = new File(mImageList.get(5));
        if (file.exists()) {
            Bitmap bm = compressImageFromFile(mImageList.get(5));
            //将图片显示到ImageView中
/*            ibMainReleaseZhuanXianWuLiuPicPick.setImageBitmap(bm);*/

            base64_05 = bitmapConvertBase64(bm);
            if((base64_05 == null )||(base64_05.isEmpty())){
                base64_05 = "0";
            }

        }
         file = new File(mImageList.get(6));
        if (file.exists()) {
            Bitmap bm = compressImageFromFile(mImageList.get(6));
            //将图片显示到ImageView中
           /* ibMainReleaseZhuanXianWuLiuPicPick.setImageBitmap(bm);*/

            base64_06 = bitmapConvertBase64(bm);
            if((base64_06 == null )||(base64_06.isEmpty())){
                base64_06 = "0";
            }

        }
         file = new File(mImageList.get(7));
        if (file.exists()) {
            Bitmap bm = compressImageFromFile(mImageList.get(7));
            //将图片显示到ImageView中
    /*        ibMainReleaseZhuanXianWuLiuPicPick.setImageBitmap(bm);*/

            base64_07 = bitmapConvertBase64(bm);
            if((base64_07 == null )||(base64_07.isEmpty())){
                base64_07 = "0";
            }

        }
        /*

        if(img0 == null){
            img0 = " ";
        }else{
            Bitmap bm = BitmapFactory.decodeFile(img0);

            base64_00 = bitmapConvertBase64(bm);
        }
        if(img1 == null){
            img1 = " ";
        }else{
            Bitmap bm = BitmapFactory.decodeFile(img1);
            base64_01 = bitmapConvertBase64(bm);
        }
        if(img2 == null){
            img2 = " ";
        }else{
            Bitmap bm = BitmapFactory.decodeFile(img2);
            base64_02 = bitmapConvertBase64(bm);
        }
        if(img3 == null){
            img3 = " ";
        }else{
            Bitmap bm = BitmapFactory.decodeFile(img3);
            base64_03 = bitmapConvertBase64(bm);
        }
        if(img4 == null){
            img4 = " ";
        }else{
            Bitmap bm = BitmapFactory.decodeFile(img4);
            base64_04 = bitmapConvertBase64(bm);
        }
        if(img5 == null){
            img5 = " ";
        }else{
            Bitmap bm = BitmapFactory.decodeFile(img5);
            base64_05 = bitmapConvertBase64(bm);
        }
        if(img6 == null){
            img6 = " ";
        }else{
            Bitmap bm = BitmapFactory.decodeFile(img6);
            base64_06 = bitmapConvertBase64(bm);
        }
        if(img7 == null){
            img7 = " ";
        }else{
            Bitmap bm = BitmapFactory.decodeFile(img7);
            base64_07 = bitmapConvertBase64(bm);
        }*/
    }
    public class MyThread extends Thread{

        @Override
        public void run(){
            int size = mImageList.size();
            for(int i = (size-1);i < 8;i++){
                mImageList.add("");
            }
            String img0 = mImageList.get(0);
            String img1 = mImageList.get(1);
            String img2 = mImageList.get(2);
            String img3 = mImageList.get(3);
            String img4 = mImageList.get(4);
            String img5 = mImageList.get(5);
            String img6 = mImageList.get(6);
            String img7 = mImageList.get(7);

            if(img0 == null){
                img0 = " ";
            }else{
                Bitmap bm = BitmapFactory.decodeFile(img0);

                base64_00 = bitmapConvertBase64(bm);
            }
            if(img1 == null){
                img1 = " ";
            }else{
                Bitmap bm = BitmapFactory.decodeFile(img1);
                base64_01 = bitmapConvertBase64(bm);
            }
            if(img2 == null){
                img2 = " ";
            }else{
                Bitmap bm = BitmapFactory.decodeFile(img2);
                base64_02 = bitmapConvertBase64(bm);
            }
            if(img3 == null){
                img3 = " ";
            }else{
                Bitmap bm = BitmapFactory.decodeFile(img3);
                base64_03 = bitmapConvertBase64(bm);
            }
            if(img4 == null){
                img4 = " ";
            }else{
                Bitmap bm = BitmapFactory.decodeFile(img4);
                base64_04 = bitmapConvertBase64(bm);
            }
            if(img5 == null){
                img5 = " ";
            }else{
                Bitmap bm = BitmapFactory.decodeFile(img5);
                base64_05 = bitmapConvertBase64(bm);
            }
            if(img6 == null){
                img6 = " ";
            }else{
                Bitmap bm = BitmapFactory.decodeFile(img6);
                base64_06 = bitmapConvertBase64(bm);
            }
            if(img7 == null){
                img7 = " ";
            }else{
                Bitmap bm = BitmapFactory.decodeFile(img7);
                base64_07 = bitmapConvertBase64(bm);
            }
        }
    }
    //图片压缩
    private Bitmap compressImageFromFile(String srcPath) {
        /*BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;//只读边,不读内容
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);*/

        BitmapUtils bitmapUtils = new BitmapUtils();
        Bitmap bitmap = bitmapUtils.getimage(srcPath);
        bitmap = bitmapUtils.compressImage(bitmap);
        bitmap = bitmapUtils.comp(bitmap);
       /* newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = w/10;//
        float ww = h/10;//
        int be = 1;
        if (w > h && w > ww) {
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0)
            be = 1;
        newOpts.inSampleSize = be;//设置采样率

        newOpts.inPreferredConfig = Bitmap.Config.ARGB_8888;//该模式是默认的,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收

        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);*/
//		return compressBmpFromBmp(bitmap);//原来的方法调用了这个方法企图进行二次压缩
        //其实是无效的,大家尽管尝试
        return bitmap;
    }
    private String bitmapConvertBase64(Bitmap bitmap){
        String base64 = "";
        ByteArrayOutputStream bos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        byte[]b=bos.toByteArray();
        base64= Base64.encodeToString(b, Base64.DEFAULT);
        return base64;
    }
    private void submitReleaseDataToNet(){
        MainReleaseNetWork mainReleaseNetWork = new MainReleaseNetWork();
/*        mainReleaseNetWork.releaseZhuanXianWuLiuToNet(paramMap,base64_00,base64_01,base64_02,base64_03,base64_04,base64_05,base64_06,base64_07, new Observer<CarSourceBean>() {
        mainReleaseNetWork.releaseZhuanXianWuLiuToNet(paramMap,no18,no19,no20,no21,no22,no23,no24,no25, new Observer<CarSourceBean>() {*/
        Map<String,String> parMap = getParam();
        if(parMap == null){
            return;
        }
        mainReleaseNetWork.releaseCarSourceToNet(parMap, new Observer<CarSourceBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(ZhuanXianWuLiuReleaseActivity.this,"this is Throwable:"+e,Toast.LENGTH_LONG).show();
                System.out.print("\nthis is Throwable:"+e);
                System.out.print("\nthis is Throwable:"+e);
                System.out.print("\nthis is Throwable:"+e);
                System.out.print("\nthis is Throwable:"+e);
                System.out.print("\nthis is Throwable:"+e);
            }

            @Override
            public void onNext(CarSourceBean carSourceBean) {
                Toast.makeText(ZhuanXianWuLiuReleaseActivity.this,""+carSourceBean.getMsg().toString(),Toast.LENGTH_LONG).show();
            }
        });

    }
    private  Map<String, String> getParam(){
        Map<String,String> paramMap = new HashMap<>();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(this);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        if(mImageList == null){
            return paramMap;
        }
        int size = mImageList.size();
        Log.i("this is size:",size+"");
        initImgs();
     /*   Log.i("\nthis is dis:",""+dis);*/
    /*    initImgs();*/
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId).trim();
         String type = "0";
        String type_name = "专线物流";
        String address_set = tvMainReleaseZhuanXianWuLiuContentBeginPlace.getText().toString().trim();
        /*String set_aid = "1";*/
        if((address_set == null)||(address_set.isEmpty())){
            Toast.makeText(this,"请输入出发地",Toast.LENGTH_LONG).show();
            return null;
        }
        String address_out = tvMainReleaseZhuanXianWuLiuContentReachPlace.getText().toString().trim();
        /*String set_cid = "2";*/
        if((address_out == null)||(address_out.isEmpty())){
            Toast.makeText(this,"请输入到达地",Toast.LENGTH_LONG).show();
            return null;
        }
        String iphone = etMainReleaseZhuanXianWuLiuContentTel.getText().toString().trim();
        /*String iphone = "3";*/
        if((iphone == null)||(iphone.isEmpty())){
            Toast.makeText(this,"请输入电话",Toast.LENGTH_LONG).show();
            return null;

        }
        String people = etMainReleaseZhuanXianWuLiuContentLXR.getText().toString().trim();
        /*String people = "3";*/
        if((people == null)||(people.isEmpty())){
            Toast.makeText(this,"请输入联系人",Toast.LENGTH_LONG).show();
            return null;

        }
        String car_title = etMainReleaseZhuanXianWuLiuContentTitle.getText().toString().trim();
        /*String car_title = "4";*/
        if((car_title == null)||(car_title.isEmpty())){
            Toast.makeText(this,"请输入标题",Toast.LENGTH_LONG).show();
            return  null;

        }
        String car_type = tvMainReleaseZhuanXianWuLiuContentCarType.getText().toString().trim();
        /*String car_type = "5";*/
        if((car_type == null)||(car_type.isEmpty())){
            car_type ="0";
        }
        String car_lange = tvMainReleaseZhuanXianWuLiuContentCarLength.getText().toString().trim();
        /*String car_lange ="1";*/
        if((car_lange == null)||(car_lange.isEmpty())){
            car_lange ="0";
        }


        String set_tid = tid;
        if((set_tid == null)||(set_tid.isEmpty())){
            set_tid = "0";
        }
        String out_tid = tid1;
        if((out_tid == null)||(out_tid.isEmpty())){
            out_tid = "0";
        }
        String set_aid = aid;
        if((set_aid == null)||(set_aid.isEmpty())){
            set_aid = "0";
        }
        String out_aid = aid1;
        if((out_aid == null)||(out_aid.isEmpty())){
            out_aid = "0";
        }
        String set_cid = cid;
        if((set_cid == null)||(set_cid.isEmpty())){
            set_cid = "0";
        }
        String out_cid = cid1;
        if((out_cid == null)||(out_cid.isEmpty())){
            out_cid = "0";
        }
        String address = etMainReleaseZhuanXianWuLiuContentAddr.getText().toString().trim();

        if((address == null)||(address.isEmpty())){
            address = "0";
        }


        String content = tvMainReleaseZhuanXianWuLiuContentDesc.getText().toString().trim();

        if((content == null)||(content.isEmpty())){
            content = "0";
        }

        login_id = login_id.replaceAll(" ","");
        login_id = login_id.trim();
        paramMap.put("login_id",login_id);
        type = type.replaceAll(" ","");
        type = type.trim();
        paramMap.put("type",type+"");
        type_name = type_name.replaceAll(" ","");
        type_name = type_name.trim();
        paramMap.put("type_name",type_name);
        set_aid = set_aid.replaceAll(" ","");
        set_aid = set_aid.trim();
        paramMap.put("set_aid",set_aid);
        set_cid = set_cid.replaceAll(" ","");
        set_cid = set_cid.trim();
        paramMap.put("set_cid",set_cid);
        car_title = car_title.replaceAll(" ","");
        car_title = car_title.trim();
        paramMap.put("car_title",car_title);
        out_aid = out_aid.replaceAll(" ","");
        out_aid = out_aid.trim();
        paramMap.put("out_aid",out_aid);
        out_cid = out_cid.replaceAll(" ","");
        out_cid = out_cid.trim();
        paramMap.put("out_cid",out_cid);
        set_tid = set_tid.replaceAll(" ","");
        set_tid = set_tid.trim();
        paramMap.put("set_tid",set_tid);
        out_tid = out_tid.replaceAll(" ","");
        out_tid = out_tid.trim();
        paramMap.put("out_tid",out_tid);
        iphone = iphone.replaceAll(" ","");
        iphone = iphone.trim();
        paramMap.put("iphone",iphone);
        people = people.replaceAll(" ","");
        people = people.trim();
        paramMap.put("people",people);
        car_type = car_type.replaceAll(" ","");
        car_type = car_type.trim();
        paramMap.put("car_type",car_type);
        car_lange = car_lange.replaceAll(" ","");
        car_lange = car_lange.trim();
        paramMap.put("car_lange",car_lange);
        address_set = address_set.replaceAll(" ","");
        address_set = address_set.trim();
        paramMap.put("address_set", address_set);
        address_out = address_out.replaceAll(" ","");
        address_out = address_out.trim();
        paramMap.put("address_out", address_out);
        address = address.replaceAll(" ","");
        address = address.trim();
        paramMap.put("address",address);


        System.out.print("\nthis is img1:"+base64_00);
        System.out.print("\nthis is img2:"+base64_01);
        System.out.print("\nthis is img3:"+base64_02);
        System.out.print("\nthis is img1:"+base64_00);
        System.out.print("\nthis is img1:"+base64_00);
        base64_00 = base64_00.replaceAll(" ","");
        base64_00 = base64_00.trim();
        paramMap.put("img1",base64_00);
        base64_01 = base64_01.replaceAll(" ","");
        base64_01 = base64_01.trim();
        paramMap.put("img2",base64_01);
        base64_02 = base64_02.replaceAll(" ","");
        base64_02 = base64_02.trim();
        paramMap.put("img3",base64_02);
        base64_03 = base64_03.replaceAll(" ","");
        base64_03 = base64_03.trim();
        paramMap.put("img4",base64_03);
        base64_04 = base64_04.replaceAll(" ","");
        base64_04 = base64_04.trim();
        paramMap.put("img5",base64_04);
        base64_05 = base64_05.replaceAll(" ","");
        base64_05 = base64_05.trim();
        paramMap.put("img6",base64_05);
        base64_06 = base64_06.replaceAll(" ","");
        base64_06 = base64_06.trim();
        paramMap.put("img7",base64_06);
        base64_07 = base64_07.replaceAll(" ","");
        base64_07 = base64_07.trim();
        paramMap.put("img8",base64_07);
        content = content.replaceAll(" ","");
        content = content.trim();
        paramMap.put("content",content);

        return paramMap;
    }

}
