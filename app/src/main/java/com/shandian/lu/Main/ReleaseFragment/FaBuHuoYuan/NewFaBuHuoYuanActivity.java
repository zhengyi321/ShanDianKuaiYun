package com.shandian.lu.Main.ReleaseFragment.FaBuHuoYuan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewFaBuHuoYuanBean;
import com.example.mynewslayoutlib.Bean.NewFaBuPicBean;
import com.j256.ormlite.stmt.query.In;
import com.shandian.lu.BaseActivity;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.Main.ReleaseFragment.SelectAddAddress.SelectAddAddressActivity;
import com.shandian.lu.NetWork.NewFaBuNetWork;
import com.shandian.lu.R;
import com.yanzhenjie.album.Album;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.BitmapUtils;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.PhoneFormatCheckUtils;

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

public class NewFaBuHuoYuanActivity extends BaseActivity {

    private String typeName ;
    private NewFaBuHuoYuanController newFaBuHuoYuanController;
    private  final int ACTIVITY_REQUEST_SELECT_PHOTO = 100;

    private final int ACTIVITY_SELECT_ADDRESS_BEGIN = 105;
    private final int ACTIVITY_SELECT_ADDRESS_END = 106;
    private ArrayList<String> mImageList;
    private ArrayList<String> netImageList;
    private ArrayList<String> currentNameNetImageList;
    private ArrayList<String> deleteTempList;

    int picSize = 0;
    int i = 0;

    private String bProvince,eProvince,bCity,eCity,bArea,eArea,beginAddr,endAddr;
    private String blat,blon,elat,elon;
    private boolean isPicFinished = false;
    @BindView(R.id.pb_new_fabuhuoyuan)
    ProgressBar pbNewFaBuHuoYuan;
    @BindView(R.id.lly_new_fabuhuoyuan_submit)
    LinearLayout llyNewFaBuHuoYuanSubmit;
    @OnClick(R.id.lly_new_fabuhuoyuan_submit)
    public void  llyNewFaBuHuoYuanSubmitOnclick(){
        pbNewFaBuHuoYuan.setVisibility(View.VISIBLE);
        int imgSize = newFaBuHuoYuanController.addPicRVAdapter.getNetImageList().size();
        /*List<String> deleteImageList = newFaBuHuoYuanController.addPicRVAdapter.getDeleteImageLists();
        int delImgSize = deleteImageList.size();*/
        if(imgSize <= 0){
            Toast.makeText(this,"请至少上传1张图片",Toast.LENGTH_LONG).show();
            pbNewFaBuHuoYuan.setVisibility(View.GONE);
            return;
        }
        if(isPicFinished) {
            if(checkParam()) {
                faBuHuoYuanToNet();
            }else{
                pbNewFaBuHuoYuan.setVisibility(View.GONE);
            }
        }else{
            Toast.makeText(this,"请等待图片上传完成",Toast.LENGTH_LONG).show();
            pbNewFaBuHuoYuan.setVisibility(View.GONE);
        }


    }
    private boolean checkParam(){

        String title = etNewFaBuHuoYuanGoodsName.getText().toString();
        if(title.length() == 0){
            Toast.makeText(this,"请输入货物名称",Toast.LENGTH_LONG).show();
            return false;
        }
        String bAddr = tvNewFaBuHuoYuanBegin.getText().toString();
        if(bAddr.length() == 0){
            Toast.makeText(this,"请选择出发地址",Toast.LENGTH_LONG).show();
            return false;
        }
        String eAddr = tvNewFaBuHuoYuanEnd.getText().toString();
        if(eAddr.length() == 0){
            Toast.makeText(this,"请选择目的地",Toast.LENGTH_LONG).show();
            return false;
        }
        String weight = etNewFaBuHuoYuanWeight.getText().toString();
        if(weight.length() == 0){
            Toast.makeText(this,"请输入重量",Toast.LENGTH_LONG).show();
            return false;
        }
        String name = etNewFaBuHuoYuanName.getText().toString();
        if(name.length() == 0){
            Toast.makeText(this,"请输入联系人",Toast.LENGTH_LONG).show();
            return false;
        }
        String tel = etNewFaBuHuoYuanTel.getText().toString();
        if(tel.length() == 0){
            Toast.makeText(this,"请输入电话",Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }


    @BindView(R.id.lly_new_fabuhuoyuan_begin)
    LinearLayout llyNewFaBuHuoYuanBegin;
    @OnClick(R.id.lly_new_fabuhuoyuan_begin)
    public void llyNewFaBuHuoYuanBeginOnclick(){
        Intent intent = new Intent(this, SelectAddAddressActivity.class);
        intent.putExtra("type","begin");
        startActivityForResult(intent,ACTIVITY_SELECT_ADDRESS_BEGIN);
    }
    @BindView(R.id.lly_new_fabuhuoyuan_end)
    LinearLayout llyNewFaBuHuoYuanEnd;
    @OnClick(R.id.lly_new_fabuhuoyuan_end)
    public void llyNewFaBuHuoYuanEndOnclick(){
        Intent intent = new Intent(this, SelectAddAddressActivity.class);
        intent.putExtra("type","end");
        startActivityForResult(intent,ACTIVITY_SELECT_ADDRESS_END);
    }

    @BindView(R.id.et_new_fabuhuoyuan_goodsname)
    EditText etNewFaBuHuoYuanGoodsName;
    @BindView(R.id.et_new_fabuhuoyuan_weight)
    EditText etNewFaBuHuoYuanWeight;
    @BindView(R.id.et_new_fabuhuoyuan_name)
    EditText etNewFaBuHuoYuanName;
    @BindView(R.id.et_new_fabuhuoyuan_tel)
    EditText etNewFaBuHuoYuanTel;
    @BindView(R.id.et_new_fabuhuoyuan_desc)
    EditText etNewFaBuHuoYuanDesc;

    @BindView(R.id.tv_new_fabuhuoyuan_begin)
    TextView tvNewFaBuHuoYuanBegin;
    @BindView(R.id.tv_new_fabuhuoyuan_end)
    TextView tvNewFaBuHuoYuanEnd;
    @BindView(R.id.tv_new_fabuhuoyuan_topbar_title)
    TextView tvNewFaBuHuoYuanTopBarTitle;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_fabuhuoyuan_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        mImageList = new ArrayList<>();
        netImageList = new ArrayList<>();
        currentNameNetImageList = new ArrayList<>();
        deleteTempList = new ArrayList<>();
        getType();
        initController();

    }
    private void getType(){
        typeName = getIntent().getStringExtra("type_name");
        if(typeName == null){
            return;
        }

        switch (typeName){
            case "1":
                tvNewFaBuHuoYuanTopBarTitle.setText("同城货源");
                break;
            case "2":
                tvNewFaBuHuoYuanTopBarTitle.setText("长途货源");
                break;
            case "3":
                tvNewFaBuHuoYuanTopBarTitle.setText("特种货源");
                break;
            case "4":
                tvNewFaBuHuoYuanTopBarTitle.setText("专线货源");
                break;
        }
    }

    private void initController(){
        newFaBuHuoYuanController = new NewFaBuHuoYuanController(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data == null){
            return;
        }
        switch (requestCode) {
            case ACTIVITY_REQUEST_SELECT_PHOTO: {
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList.clear();
                    mImageList = Album.parseResult(data); // Parse select result.
                   /* Toast.makeText(this,"ACTIVITY_REQUEST_SELECT_PHOTO:"+mImageList.get(0),Toast.LENGTH_LONG).show();*/
                    refreshImage();
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                   /* Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();*/
                   /* mImageList.clear();*/
                }
                break;
            }
            case ACTIVITY_SELECT_ADDRESS_BEGIN:{

                Bundle begin = data.getExtras();

                bProvince = begin.getString("province");
                bCity = begin.getString("city");
                bArea = begin.getString("area");
                blat = begin.getString("lat");
                blon = begin.getString("lon");
                beginAddr = begin.getString("addr");
                tvNewFaBuHuoYuanBegin.setText(beginAddr);
                break;
            }
            case ACTIVITY_SELECT_ADDRESS_END:{

                Bundle begin = data.getExtras();

                eProvince = begin.getString("province");
                eCity = begin.getString("city");
                eArea = begin.getString("area");
                elat = begin.getString("lat");
                elon = begin.getString("lon");
                endAddr = begin.getString("addr");
                tvNewFaBuHuoYuanEnd.setText(endAddr);
                break;
            }

            default:

                break;
        }
    }


    private void refreshImage(){
        newFaBuHuoYuanController.addPicRVAdapter.setAdapterImage(mImageList);
        newFaBuHuoYuanController.addPicRVAdapter.setmImageList(mImageList);
        isSamePicDelete();
        mImageList.clear();
        mImageList.addAll(deleteTempList);
        picSize = mImageList.size();
        if(picSize <= 0){
            return;
        }
        i = 0;

        Thread thread = new PicToNetThread();
        thread.run();
    }
    class PicToNetThread extends Thread{
        @Override
        public void run(){
            sendPicToNet();
        }
    }
    private void isSamePicDelete(){
        currentNameNetImageList.clear();
        currentNameNetImageList.addAll(newFaBuHuoYuanController.addPicRVAdapter.getCurrentNetImageList());
        int size = currentNameNetImageList.size();
        int sizeT = mImageList.size();
        deleteTempList.clear();
        deleteTempList.addAll(mImageList);
      /*  Toast.makeText(this,"netSize:"+size+" ImgSize:"+size2,Toast.LENGTH_LONG).show();*/
        System.out.print("\nnetSize:"+size+" ImgSize:"+sizeT);

        for(int i = 0;i<size;i++){
            String netPic = currentNameNetImageList.get(i);/*

            System.out.print("\npic:"+netPic);*/

            for(int j = 0;j<sizeT;j++){
        /*        System.out.print("\njjjjjpic:"+j);*/

                String pic = mImageList.get(j);/*
                System.out.print("\n111pic:"+pic);
*/
                if(netPic.equals(pic)){
                    int count = deleteTempList.size();
                    if(count == 0){
                        return;
                    }
/*

                    System.out.print("\ni am delete success:"+pic);*/
                    deleteTempList.remove(pic);
                }
                continue;
            }
        }

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
                        currentNameNetImageList.add(mImageList.get(i));
                        int size = netImageList.size();
                        System.out.print("\nnet000000000Size:"+size);
                        i++;
                        sendPicToNet();
                    }
                }
            });
        }else{
            int size = netImageList.size();
            int size2 = newFaBuHuoYuanController.addPicRVAdapter.getNetImageList().size();
            System.out.print("\nnet111111111Size:"+size+" ImgSize:"+size2);
            System.out.print("\nnet111111111Size:"+size+" ImgSize:"+size2);

            newFaBuHuoYuanController.addPicRVAdapter.setNetImageList(netImageList);
            newFaBuHuoYuanController.addPicRVAdapter.setCurrentNetImageList(currentNameNetImageList);
            System.out.print("\nnet333333333Size:"+size+" ImgSize:"+size2);
            System.out.print("\nnet333333333Size:"+size+" ImgSize:"+size2);
            System.out.print("\nnet333333333Size:"+size+" ImgSize:"+size2);

            pbNewFaBuHuoYuan.setVisibility(View.GONE);
            isPicFinished = true;
        }
/*        System.out.print("\nbase64:"+base64_00);*/
        /*Log.i("base64:",base64_00);*/

    }
    private Map<String,String> getParamMap(){
        Map<String,String> paramMap = new HashMap<>();
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(this);
        String loginId= xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            Toast.makeText(this,"请登录",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
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


    private Map<String,Object> getFaBuParamMap(){
        Map<String,Object> paramMap = new HashMap<>();
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(this);
        String loginId= xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            Toast.makeText(this,"请登录",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return paramMap;
        }
        paramMap.put("login_id",loginId);
        if(typeName == null){
            return paramMap;
        }
        paramMap.put("type_name",typeName);
        String goodsName = etNewFaBuHuoYuanGoodsName.getText().toString();
        if(goodsName == null){
            goodsName = "";
        }
        paramMap.put("good_name",goodsName);
        if(bProvince == null){
            bProvince = "";
        }
        paramMap.put("cfsheng",bProvince);

        if(bCity == null){
            bCity = "";
        }
        paramMap.put("cfshi",bCity);
        if(bArea == null){
            bArea = "";
        }
        paramMap.put("cfqu",bArea);
        String bZuoBiao = blat+","+blon;
        paramMap.put("cfzuobiao",bZuoBiao);
        if(eProvince == null){
            eProvince = "";
        }
        paramMap.put("dasheng",eProvince);
        if(eCity == null){
            eCity = "";
        }
        paramMap.put("dashi",eCity);
        if(eArea == null){
            eArea = "";
        }
        paramMap.put("daqu",eArea);
        String dZuoBiao = elat+","+elon;
        paramMap.put("dazuobiao",dZuoBiao);
        String weight = etNewFaBuHuoYuanWeight.getText().toString();
        if(weight == null){
            weight = "";
        }
        paramMap.put("weight",weight);
        String people = etNewFaBuHuoYuanName.getText().toString();
        if(people == null){
            people = "";
        }
        paramMap.put("people",people);
        String iphone = etNewFaBuHuoYuanTel.getText().toString();
        if(iphone == null){
            iphone = "";
        }

        paramMap.put("iphone",iphone);
        String context = etNewFaBuHuoYuanDesc.getText().toString();
        if(context  == null){
            context = "";
        }
        paramMap.put("context",context);
        List<String> lastNetImageList = newFaBuHuoYuanController.addPicRVAdapter.getNetImageList();
        if(lastNetImageList == null){
            lastNetImageList = new ArrayList<>();
        }
        paramMap.put("imgtu",lastNetImageList);
        List<String> deleteImageList = newFaBuHuoYuanController.addPicRVAdapter.getDeleteImageLists();
        if(deleteImageList == null){
            deleteImageList = new ArrayList<>();
        }
        paramMap.put("deltu",deleteImageList);
        if(beginAddr == null){
            beginAddr = "";
        }
        paramMap.put("cfdizhi",beginAddr);
        if(endAddr == null){
            endAddr = "";
        }
        paramMap.put("dadizhi",endAddr);

        return paramMap;
    }

    private void faBuHuoYuanToNet(){
        pbNewFaBuHuoYuan.setVisibility(View.VISIBLE);

        PhoneFormatCheckUtils phoneFormatCheckUtils = new PhoneFormatCheckUtils();
        String tel = etNewFaBuHuoYuanTel.getText().toString();
        if(!phoneFormatCheckUtils.isNumber(tel)){
            Toast.makeText(this,"联系电话请输入数字",Toast.LENGTH_LONG).show();
            pbNewFaBuHuoYuan.setVisibility(View.GONE);
            return;
        }
        String weight = etNewFaBuHuoYuanWeight.getText().toString();
        if(!phoneFormatCheckUtils.isNumber(weight)){
            Toast.makeText(this,"重量请输入数字",Toast.LENGTH_LONG).show();
            pbNewFaBuHuoYuan.setVisibility(View.GONE);
            return;
        }




        NewFaBuNetWork newFaBuNetWork = new NewFaBuNetWork();
        newFaBuNetWork.faBuOrUpdateHuoYuanToNet(getFaBuParamMap(), new Observer<NewFaBuHuoYuanBean>() {
            @Override
            public void onCompleted() {
                /*pbNewFaBuHuoYuan.setVisibility(View.GONE);*/
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(NewFaBuHuoYuanActivity.this,"提交失败",Toast.LENGTH_LONG).show();
                pbNewFaBuHuoYuan.setVisibility(View.GONE);
            }

            @Override
            public void onNext(NewFaBuHuoYuanBean newFaBuHuoYuanBean) {
                Toast.makeText(NewFaBuHuoYuanActivity.this,newFaBuHuoYuanBean.getMsg(),Toast.LENGTH_LONG).show();
                pbNewFaBuHuoYuan.setVisibility(View.GONE);
                if(newFaBuHuoYuanBean.getStatus().equals("0")){
                    finish();
                }
            }
        });
    }

    //图片压缩
    private Bitmap compressImageFromFile(String srcPath) {

        BitmapUtils bitmapUtils = new BitmapUtils();
        Bitmap bitmap = bitmapUtils.getimage(srcPath);
        bitmap = bitmapUtils.compressImage(bitmap);
        bitmap = bitmapUtils.comp(bitmap);

        return bitmap;
    }
}
