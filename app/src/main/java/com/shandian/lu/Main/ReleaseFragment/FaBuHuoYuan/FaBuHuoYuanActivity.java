package com.shandian.lu.Main.ReleaseFragment.FaBuHuoYuan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.BitmapUtils;
import com.shandian.lu.BaseActivity;
import com.shandian.lu.Main.IndexFragment.CityChange.CityChangeActivity;
import com.shandian.lu.Main.ReleaseFragment.FaBuHuoYuan.HuoWuLeiBie.GoodsTypeActivity;
import com.shandian.lu.Main.ReleaseFragment.ZhuanXianWuLiu.MiaoShu.MiaoShuActivity;
import com.shandian.lu.Main.ReleaseFragment.ZhuanXianWuLiu.SelectPicActivity;
import com.shandian.lu.NetWork.MainReleaseNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.CarSourceBean;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by az on 2017/5/17.
 */

public class FaBuHuoYuanActivity extends BaseActivity {
    public   final int ACTIVITY_REQUEST_SELECT_PHOTO = 100;
    private final int CITY_CHANGE_SELECTED = 99;
    private boolean isBegin = true;
    private final int OTHER_DESC = 98;
    private final int LEIBIE = 97;

    private String aid,cid,tid,aid1,cid1,tid1;
    ArrayList<String> mImageList;
    String base64_00="",base64_01="",base64_02="",base64_03="",base64_04="",base64_05="",base64_06="",base64_07="";



    @BindView(R.id.et_main_release_fabuhuoyuan_content_huowumingcheng)
    EditText etMainReleaseFaBuHuoYuanContentHuoWuMingCheng;

    @BindView(R.id.tv_main_release_fabuhuoyuan_content_huowuleibie)
    TextView tvMainReleaseFaBuHuoYuanContentHuoWuLeiBie;

    @BindView(R.id.lly_main_release_fabuhuoyuan_content_huowuleibie)
    LinearLayout llyMainReleaseFaBuHuoYuanContentHuoWuLeiBie;
    @OnClick(R.id.lly_main_release_fabuhuoyuan_content_huowuleibie)
    public void llyMainReleaseFaBuHuoYuanContentHuoWuLeiBieOnclick(){
        Intent intent = new Intent(FaBuHuoYuanActivity.this, GoodsTypeActivity.class);
        startActivityForResult(intent,LEIBIE);
    }
    @BindView(R.id.tv_main_release_fabuhuoyuan_selected_num)
    TextView tvMainReleaseFaBuHuoYuanCheSelectedNum;

    @BindView(R.id.ib_main_release_fabuhuoyuan_pic_pick)
    ImageButton ibMainReleaseFaBuHuoYuanChePicPick;
    @OnClick(R.id.ib_main_release_fabuhuoyuan_pic_pick)
    public void ibMainReleaseFaBuHuoYuanChePicPickOnclick(){
        Intent intent = new Intent(this,SelectPicActivity.class);
        intent.putStringArrayListExtra("mImageList", mImageList);
        startActivityForResult(intent,ACTIVITY_REQUEST_SELECT_PHOTO);
    }


    @BindView(R.id.tv_main_release_fabuhuoyuan_content_begin)
    TextView tvMainReleaseFaBuHuoYuanContentBegin;
    @BindView(R.id.lly_main_release_fabuhuoyuan_content_begin)
    LinearLayout llyMainReleaseFaBuHuoYuanContentBegin;
    @OnClick(R.id.lly_main_release_fabuhuoyuan_content_begin)
    public void llyMainReleaseFaBuHuoYuanContentBeginOnclick(){
        isBegin = true;
        Intent intent = new Intent(FaBuHuoYuanActivity.this, CityChangeActivity.class);
        startActivityForResult(intent,CITY_CHANGE_SELECTED);
    }

    @BindView(R.id.tv_main_release_fabuhuoyuan_content_reach)
    TextView tvMainReleaseFaBuHuoYuanContentReach;
    @BindView(R.id.lly_main_release_fabuhuoyuan_content_reach)
    LinearLayout llyMainReleaseFaBuHuoYuanContentReach;
    @OnClick(R.id.lly_main_release_fabuhuoyuan_content_reach)
    public void llyMainReleaseFaBuHuoYuanContentReachOnclick(){
        isBegin = false;
        Intent intent = new Intent(FaBuHuoYuanActivity.this, CityChangeActivity.class);
        startActivityForResult(intent,CITY_CHANGE_SELECTED);
    }


    @BindView(R.id.et_main_release_fabuhuoyuan_content_tel)
    EditText etMainReleaseFaBuHuoYuanContentTel;
    @BindView(R.id.et_main_release_fabuhuoyuan_content_huowuzhongliang)
    EditText etMainReleaseFaBuHuoYuanContentHuoWuZhongLiang;
    @BindView(R.id.et_main_release_fabuhuoyuan_content_lxr)
    EditText etMainReleaseFaBuHuoYuanContentLXR;
    @BindView(R.id.et_main_release_fabuhuoyuan_content_addr)
    EditText etMainReleaseFaBuHuoYuanContentAddr;
    @BindView(R.id.tv_main_release_fabuhuoyuan_content_desc)
    TextView tvMainReleaseFaBuHuoYuanContentDesc;
    @BindView(R.id.lly_main_release_fabuhuoyuan_content_desc)
    LinearLayout llyMainReleaseFaBuHuoYuanContentDesc;
    @OnClick(R.id.lly_main_release_fabuhuoyuan_content_desc)
    public void llyMainReleaseFaBuHuoYuanContentDescOnclick(){
        Intent intent = new Intent(this, MiaoShuActivity.class);
        startActivityForResult(intent,OTHER_DESC);
    }
    @BindView(R.id.bt_main_release_fabuhuoyuan_content_submit)
    Button btMainReleaseFaBuHuoYuanContentSubmit;
    @OnClick(R.id.bt_main_release_fabuhuoyuan_content_submit)
    public void btMainReleaseFaBuHuoYuanContentSubmitOnclick(){
        submitReleaseDataToNet();
    }



    private FaBuHuoYuanController faBuHuoYuanController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_release_fabuhuoyuan_lly);

    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        faBuHuoYuanController = new FaBuHuoYuanController(this);
    }



    private void submitReleaseDataToNet(){
        MainReleaseNetWork mainReleaseNetWork = new MainReleaseNetWork();
        mainReleaseNetWork.releaseGoodsSourceToNet(getParam(), new Observer<CarSourceBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(FaBuHuoYuanActivity.this,"this is Throwable:"+e,Toast.LENGTH_LONG).show();
                System.out.print("\nthis is Throwable:"+e);
                System.out.print("\nthis is Throwable:"+e);
                System.out.print("\nthis is Throwable:"+e);
                System.out.print("\nthis is Throwable:"+e);
                System.out.print("\nthis is Throwable:"+e);
            }

            @Override
            public void onNext(CarSourceBean carSourceBean) {
                if(!carSourceBean.getStatus() .equals("0") ){
                    Toast.makeText(FaBuHuoYuanActivity.this,"请上传正确的参数",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(FaBuHuoYuanActivity.this, "" + carSourceBean.getMsg().toString(), Toast.LENGTH_LONG).show();
                }
              /*  Toast.makeText(FaBuHuoYuanActivity.this,""+carSourceBean.getMsg().toString(),Toast.LENGTH_LONG).show();*/
            }
        });

    }


    private Map<String, String> getParam(){
        final Map<String,String> paramMap = new HashMap<>();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(this);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        if(mImageList == null){
            return paramMap;
        }
        int size = mImageList.size();
        Log.i("this is size:",size+"");
        initImgs();


        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId).trim();
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(this,"清请登录",Toast.LENGTH_LONG).show();
            return paramMap;
        }
        String type_name = tvMainReleaseFaBuHuoYuanContentHuoWuLeiBie.getText().toString();
        if((type_name == null)||(type_name.isEmpty())){
            Toast.makeText(this,"请选择货物类别",Toast.LENGTH_LONG).show();
            return paramMap;
        }
        String address_set = tvMainReleaseFaBuHuoYuanContentBegin.getText().toString().trim();

        if((address_set == null)||(address_set.isEmpty())){
            Toast.makeText(this,"请输入出发地",Toast.LENGTH_LONG).show();
            return paramMap;
        }
        String address_out = tvMainReleaseFaBuHuoYuanContentReach.getText().toString().trim();

        if((address_out == null)||(address_out.isEmpty())){
            Toast.makeText(this,"请输入到达地",Toast.LENGTH_LONG).show();
            return paramMap;
        }
        String title = etMainReleaseFaBuHuoYuanContentHuoWuMingCheng.getText().toString().trim();
        if(title == null){
            Toast.makeText(this,"请输入货物名称",Toast.LENGTH_LONG).show();
            title ="";
        }


        String zhongLiange = etMainReleaseFaBuHuoYuanContentHuoWuZhongLiang.getText().toString().trim();

        if((zhongLiange == null)||(zhongLiange.isEmpty())){
            Toast.makeText(this,"请输入货物重量",Toast.LENGTH_LONG).show();
            return paramMap;

        }
        String iphone = etMainReleaseFaBuHuoYuanContentTel.getText().toString().trim();
        if((iphone == null)||(iphone.isEmpty())){
            Toast.makeText(this,"请输入电话",Toast.LENGTH_LONG).show();
            return paramMap;

        }
        String people = etMainReleaseFaBuHuoYuanContentLXR.getText().toString().trim();
        if((people == null)||(people.isEmpty())){
            Toast.makeText(this,"请输入联系人",Toast.LENGTH_LONG).show();
            return null;

        }
        String set_tid = tid;
        if(set_tid == null){
            set_tid = "";
        }
        String out_tid = tid1;
        if(out_tid == null){
            out_tid = "";
        }
        String set_aid = aid;
        if(set_aid == null){
            set_aid = "";
        }
        String out_aid = aid1;
        if(out_aid == null){
            out_aid = "";
        }
        String set_cid = cid;
        if(set_cid == null){
            set_cid = "";
        }
        String out_cid = cid1;
        if(out_cid == null){
            out_cid = "";
        }
        String address = etMainReleaseFaBuHuoYuanContentAddr.getText().toString().trim();

        if((address == null)||(address.isEmpty())){
            Toast.makeText(this,"请输入地址",Toast.LENGTH_LONG).show();
            return paramMap;

        }
        if(address == null){
            address = "";
        }


        String content = tvMainReleaseFaBuHuoYuanContentDesc.getText().toString().trim();

        if(content == null){
            content = "";
        }

        try {
            URLEncoder.encode(login_id, "UTF-8");
            URLEncoder.encode(type_name, "UTF-8");
            URLEncoder.encode(set_aid, "UTF-8");
            URLEncoder.encode(set_cid, "UTF-8");
            URLEncoder.encode(title, "UTF-8");
            URLEncoder.encode(out_aid, "UTF-8");
            URLEncoder.encode(out_cid, "UTF-8");
            URLEncoder.encode(set_tid, "UTF-8");
            URLEncoder.encode(out_tid, "UTF-8");
            URLEncoder.encode(iphone, "UTF-8");
            URLEncoder.encode(people, "UTF-8");
            URLEncoder.encode(address_set, "UTF-8");
            URLEncoder.encode(address_out, "UTF-8");
            URLEncoder.encode(address, "UTF-8");
        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        login_id = login_id.replaceAll(" ","");
        login_id = login_id.trim();
        paramMap.put("login_id",login_id);
        title = title.replaceAll(" ","");
        title = title.trim();
        paramMap.put("good_name",title+"");
        set_aid = set_aid.replaceAll(" ","");
        set_aid = set_aid.trim();
        paramMap.put("set_aid",set_aid);
        set_cid = set_cid.replaceAll(" ","");
        set_cid = set_cid.trim();
        paramMap.put("set_cid",set_cid);
        out_aid = out_aid.replaceAll(" ","");
        out_aid = out_aid.trim();
        paramMap.put("out_aid",out_aid);
        out_cid = out_cid.replaceAll(" ","");
        out_cid = out_cid.trim();
        paramMap.put("out_cid",out_cid);
        address_set = address_set.replaceAll(" ","");
        address_set = address_set.trim();
        paramMap.put("address_set", address_set);
        address_out = address_out.replaceAll(" ","");
        address_out = address_out.trim();
        paramMap.put("address_out", address_out);
        people = people.replaceAll(" ","");
        people = people.trim();
        paramMap.put("people",people);
        address = address.replaceAll(" ","");
        address = address.trim();
        paramMap.put("address",address);
        iphone = iphone.replaceAll(" ","");
        iphone = iphone.trim();
        paramMap.put("iphone",iphone);


        type_name = type_name.replaceAll(" ","");
        type_name = type_name.trim();
        paramMap.put("type_name",type_name);

        title = title.replaceAll(" ","");
        title = title.trim();
        paramMap.put("car_title",title);

        set_tid = set_tid.replaceAll(" ","");
        set_tid = set_tid.trim();
        paramMap.put("set_tid",set_tid);
        out_tid = out_tid.replaceAll(" ","");
        out_tid = out_tid.trim();
        paramMap.put("out_tid",out_tid);
        content = content.replaceAll(" ","");
        content = content.trim();
        paramMap.put("content",content);


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


        return paramMap;
    }
    public void initImgs(){
        BitmapUtils bitmapUtils = new BitmapUtils();
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
            ibMainReleaseFaBuHuoYuanChePicPick.setImageBitmap(bm);

            base64_00 = bitmapUtils.bitmapConvertBase64(bm);

        }
        file = new File(mImageList.get(1));
        if (file.exists()) {
            Bitmap bm = compressImageFromFile(mImageList.get(1));
            //将图片显示到ImageView中
            ibMainReleaseFaBuHuoYuanChePicPick.setImageBitmap(bm);

            base64_01 = bitmapUtils.bitmapConvertBase64(bm);

        }
        file = new File(mImageList.get(2));
        if (file.exists()) {
            Bitmap bm = compressImageFromFile(mImageList.get(2));
            //将图片显示到ImageView中
            ibMainReleaseFaBuHuoYuanChePicPick.setImageBitmap(bm);

            base64_02 = bitmapUtils.bitmapConvertBase64(bm);

        }
        file = new File(mImageList.get(3));
        if (file.exists()) {
            Bitmap bm = compressImageFromFile(mImageList.get(3));
            //将图片显示到ImageView中
            ibMainReleaseFaBuHuoYuanChePicPick.setImageBitmap(bm);

            base64_03 = bitmapUtils.bitmapConvertBase64(bm);

        }
        file = new File(mImageList.get(4));
        if (file.exists()) {
            Bitmap bm = compressImageFromFile(mImageList.get(4));
            //将图片显示到ImageView中
            ibMainReleaseFaBuHuoYuanChePicPick.setImageBitmap(bm);

            base64_04 = bitmapUtils.bitmapConvertBase64(bm);

        }
        file = new File(mImageList.get(5));
        if (file.exists()) {
            Bitmap bm = compressImageFromFile(mImageList.get(5));
            //将图片显示到ImageView中
            ibMainReleaseFaBuHuoYuanChePicPick.setImageBitmap(bm);

            base64_05 = bitmapUtils.bitmapConvertBase64(bm);

        }
        file = new File(mImageList.get(6));
        if (file.exists()) {
            Bitmap bm = compressImageFromFile(mImageList.get(6));
            //将图片显示到ImageView中
            ibMainReleaseFaBuHuoYuanChePicPick.setImageBitmap(bm);

            base64_06 = bitmapUtils.bitmapConvertBase64(bm);

        }
        file = new File(mImageList.get(7));
        if (file.exists()) {
            Bitmap bm = compressImageFromFile(mImageList.get(7));
            //将图片显示到ImageView中
            ibMainReleaseFaBuHuoYuanChePicPick.setImageBitmap(bm);

            base64_07 = bitmapUtils.bitmapConvertBase64(bm);

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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
   /*     Toast.makeText(FaBuHuoYuanActivity.this,"requestCode:"+requestCode,Toast.LENGTH_LONG).show();
        Toast.makeText(FaBuHuoYuanActivity.this,"resultCode:"+resultCode,Toast.LENGTH_LONG).show();
        Toast.makeText(FaBuHuoYuanActivity.this,"data:"+resultCode,Toast.LENGTH_LONG).show();*/
        Log.i("resultCode",resultCode+"");
        BitmapUtils bitmapUtils = new BitmapUtils();
        switch (resultCode) {
            case ACTIVITY_REQUEST_SELECT_PHOTO:
                mImageList = new ArrayList<>();
                mImageList = data.getStringArrayListExtra("mImageList");
                if(mImageList == null){
                    mImageList = new ArrayList<>();

                }
                Log.i("mImageListSize",mImageList.get(0)+"");
                Log.i("mImageListSize",mImageList.size()+"");
                Log.i("mImageListSize",mImageList.size()+"");
                Log.i("mImageListSize",mImageList.size()+"");
                if(mImageList.size()>0) {
                    File file = new File(mImageList.get(0));
                    if (file.exists()) {
                        Bitmap bm = compressImageFromFile(mImageList.get(0));
                        //将图片显示到ImageView中
                        ibMainReleaseFaBuHuoYuanChePicPick.setImageBitmap(bm);
                        base64_00 = "";
                        base64_00 = bitmapUtils.bitmapConvertBase64(bm);
                    }
                    tvMainReleaseFaBuHuoYuanCheSelectedNum.setText("已选择"+mImageList.size()+"张照片");

                    Log.i("\nthis is mImageList:",""+mImageList.size());

                }else{
                    tvMainReleaseFaBuHuoYuanCheSelectedNum.setText("");

                    ibMainReleaseFaBuHuoYuanChePicPick.setImageResource(R.mipmap.paizhao);
                }

                break;
            case CITY_CHANGE_SELECTED:
                Bundle b=data.getExtras(); //data为B中回传的Intent
                String city=b.getString("city");//str即为回传的值
                String id1 = b.getString("aid");
                String id2 = b.getString("cid");
                String id3 = b.getString("tid");

                /*Toast.makeText(FaBuHuoYuanActivity.this,"city:"+city,Toast.LENGTH_LONG).show();*/
                if(city != null){
                    if(isBegin) {
                        aid = id1;
                        cid = id2;
                        tid = id3;
                        tvMainReleaseFaBuHuoYuanContentBegin.setText(city);
                    }else {
                        aid1 = id1;
                        cid1 = id2;
                        tid1 = id3;
                        tvMainReleaseFaBuHuoYuanContentReach.setText(city);
                    }
                }

                break;
            case OTHER_DESC:
                Bundle bundle = data.getExtras();
                String desc = bundle.getString("desc");
                if(desc != null){
                    tvMainReleaseFaBuHuoYuanContentDesc.setText(desc);
                }
                break;
            case LEIBIE:
                Bundle bundle1 = data.getExtras();
                String leibie = bundle1.getString("huowuleibie");
                if(leibie!= null){
                    tvMainReleaseFaBuHuoYuanContentHuoWuLeiBie.setText(leibie);
                }
                break;

        }
    }

}
