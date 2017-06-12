package com.shandian.lu.Main.ReleaseFragment.FaBuCheYuan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewFaBuCheYuanBean;
import com.example.mynewslayoutlib.Bean.NewFaBuHuoYuanBean;
import com.example.mynewslayoutlib.Bean.NewFaBuPicBean;
import com.shandian.lu.BaseActivity;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.Main.ReleaseFragment.FaBuHuoYuan.NewFaBuHuoYuanActivity;
import com.shandian.lu.Main.ReleaseFragment.FaBuHuoYuan.NewFaBuHuoYuanController;
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
 * Created by Administrator on 2017/6/8.
 */

public class NewFaBuCheYuanActivity extends BaseActivity  {

    private String typeName ;
    private NewFaBuCheYuanController newFaBuCheYuanController;
    private  final int ACTIVITY_REQUEST_SELECT_PHOTO = 100;

    private final int ACTIVITY_SELECT_ADDRESS_BEGIN = 105;
    private final int ACTIVITY_SELECT_ADDRESS_END = 106;
    private ArrayList<String> mImageList;
    private ArrayList<String> netImageList;
    private ArrayList<String> currentNameNetImageList;
    private ArrayList<String> deleteTempList;
    int picSize = 0;
    int i = 0;
    private boolean isPicFinished = false;
    private String bProvince,eProvince,bCity,eCity,bArea,eArea,beginAddr,endAddr;
    private String blat,blon,elat,elon;

    @BindView(R.id.pb_new_fabucheyuan)
    ProgressBar pbNewFaBuCheYuan;
    @BindView(R.id.rly_new_fabucheyuan_submit)
    RelativeLayout rlyNewFaBuCheYuanSubmit;
    @OnClick(R.id.rly_new_fabucheyuan_submit)
    public void  rlyNewFaBuCheYuanSubmitOnclick(){
        pbNewFaBuCheYuan.setVisibility(View.VISIBLE);
        int imgSize = newFaBuCheYuanController.addPicRVAdapter.getNetImageList().size();

        if(imgSize <= 0){
            Toast.makeText(this,"请至少上传1张图片",Toast.LENGTH_LONG).show();
            pbNewFaBuCheYuan.setVisibility(View.GONE);
            return;
        }
        if(isPicFinished) {
            if(checkParam()) {
                faBuCheYuanToNet();
            }else{
                pbNewFaBuCheYuan.setVisibility(View.GONE);
            }
        }else{
            Toast.makeText(this,"请等待图片上传完成",Toast.LENGTH_LONG).show();
            pbNewFaBuCheYuan.setVisibility(View.GONE);
        }
    }
    private boolean checkParam(){

        String title = etNewFaBuCheYuanTitle.getText().toString();
        if(title.length() == 0){
            Toast.makeText(this,"请输入车源标题",Toast.LENGTH_LONG).show();
            return false;
        }
        String bAddr = tvNewFaBuCheYuanBegin.getText().toString();
        if(bAddr.length() == 0){
            Toast.makeText(this,"请输入出发地",Toast.LENGTH_LONG).show();
            return false;
        }
        String eAddr = tvNewFaBuCheYuanEnd.getText().toString();
        if(eAddr.length() == 0){
            Toast.makeText(this,"请选择目的地",Toast.LENGTH_LONG).show();
            return false;
        }
        String length = etNewFaBuCheYuanCalLength.getText().toString();
        if(length.length() == 0){
            Toast.makeText(this,"请输入车长",Toast.LENGTH_LONG).show();
            return false;
        }
        String type = tvNewFaBuCheYuanCarType.getText().toString();
        if(length.length() == 0){
            Toast.makeText(this,"请输入车型",Toast.LENGTH_LONG).show();
            return false;
        }
        String name = etNewFaBuCheYuanName.getText().toString();
        if(name.length() == 0){
            Toast.makeText(this,"请输入联系人",Toast.LENGTH_LONG).show();
            return false;
        }
        String tel = etNewFaBuCheYuanTel.getText().toString();
        if(tel.length() == 0){
            Toast.makeText(this,"请输入电话",Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }
    @BindView(R.id.lly_new_fabucheyuan_begin)
    LinearLayout llyNewFaBuCheYuanBegin;
    @OnClick(R.id.lly_new_fabucheyuan_begin)
    public void llyNewFaBuCheYuanBeginOnclick(){
        Intent intent = new Intent(this, SelectAddAddressActivity.class);
        intent.putExtra("type","begin");
        startActivityForResult(intent,ACTIVITY_SELECT_ADDRESS_BEGIN);
    }
    @BindView(R.id.lly_new_fabucheyuan_end)
    LinearLayout llyNewFaBuCheYuanEnd;
    @OnClick(R.id.lly_new_fabucheyuan_end)
    public void llyNewFaBuCheYuanEndOnclick(){
        Intent intent = new Intent(this, SelectAddAddressActivity.class);
        intent.putExtra("type","end");
        startActivityForResult(intent,ACTIVITY_SELECT_ADDRESS_END);
    }

    @BindView(R.id.et_new_fabucheyuan_title)
    EditText etNewFaBuCheYuanTitle;
    @BindView(R.id.et_new_fabucheyuan_carlength)
    EditText etNewFaBuCheYuanCalLength;
    @BindView(R.id.et_new_fabucheyuan_name)
    EditText etNewFaBuCheYuanName;
    @BindView(R.id.et_new_fabucheyuan_tel)
    EditText etNewFaBuCheYuanTel;
    @BindView(R.id.et_new_fabucheyuan_desc)
    EditText etNewFaBuCheYuanDesc;

    @BindView(R.id.tv_new_fabucheyuan_topbar_title)
    TextView tvNewFaBuCheYuanTopBarTitle;
    @BindView(R.id.tv_new_fabucheyuan_begin)
    TextView tvNewFaBuCheYuanBegin;
    @BindView(R.id.tv_new_fabucheyuan_end)
    TextView tvNewFaBuCheYuanEnd;
    @BindView(R.id.tv_new_fabucheyuan_cartype)
    TextView tvNewFaBuCheYuanCarType;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_fabucheyuan_lly);
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
        initEditListener();
    }
    private void getType(){
        typeName = getIntent().getStringExtra("type_name");
        if(typeName == null){
            return;
        }

        switch (typeName){
            case "1":
                tvNewFaBuCheYuanTopBarTitle.setText("同城物流");
                break;
            case "2":
                tvNewFaBuCheYuanTopBarTitle.setText("长途物流");
                break;
            case "3":
                tvNewFaBuCheYuanTopBarTitle.setText("特种物流");
                break;
            case "4":
                tvNewFaBuCheYuanTopBarTitle.setText("专线物流");
                break;
        }
    }

    private void initController(){
        newFaBuCheYuanController = new NewFaBuCheYuanController(this);
    }
    private void initEditListener(){

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
              /*  Toast.makeText(this,"1",Toast.LENGTH_LONG).show();*/
                bProvince = begin.getString("province");
                bCity = begin.getString("city");
                bArea = begin.getString("area");
                blat = begin.getString("lat");
                blon = begin.getString("lon");
                beginAddr = begin.getString("addr");
                tvNewFaBuCheYuanBegin.setText(beginAddr);
                break;
            }
            case ACTIVITY_SELECT_ADDRESS_END:{

                Bundle begin = data.getExtras();
              /*  Toast.makeText(this,"2",Toast.LENGTH_LONG).show();*/
                eProvince = begin.getString("province");
                eCity = begin.getString("city");
                eArea = begin.getString("area");
                elat = begin.getString("lat");
                elon = begin.getString("lon");
                endAddr = begin.getString("addr");
                tvNewFaBuCheYuanEnd.setText(endAddr);
                break;
            }
            default:
             /*   Toast.makeText(this,"3",Toast.LENGTH_LONG).show();*/
                break;

        }
    }


    private void refreshImage(){
        newFaBuCheYuanController.addPicRVAdapter.setAdapterImage(mImageList);
        newFaBuCheYuanController.addPicRVAdapter.setmImageList(mImageList);
        isSamePicDelete();
        mImageList.clear();
        mImageList.addAll(deleteTempList);
        picSize = mImageList.size();
        if(picSize <= 0){
            return;
        }
        i = 0;
        isPicFinished = false;

        sendPicToNet();
    }
    private void isSamePicDelete(){
        currentNameNetImageList.clear();
        currentNameNetImageList.addAll(newFaBuCheYuanController.addPicRVAdapter.getCurrentNetImageList());
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
            pbNewFaBuCheYuan.setVisibility(View.VISIBLE);
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
                        i++;
                        sendPicToNet();
                    }
                }
            });
        }else{
            newFaBuCheYuanController.addPicRVAdapter.setNetImageList(netImageList);
            newFaBuCheYuanController.addPicRVAdapter.setCurrentNetImageList(currentNameNetImageList);
            pbNewFaBuCheYuan.setVisibility(View.GONE);
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
        String title = etNewFaBuCheYuanTitle.getText().toString();
        if(title == null){
            title = "";
        }

        paramMap.put("car_title",title);
        String goodsName = etNewFaBuCheYuanName.getText().toString();
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
        String dZuoBiao = elat+ ","+elon;
        paramMap.put("dazuobiao",dZuoBiao);
        String carLength = etNewFaBuCheYuanCalLength.getText().toString();
        if(null == carLength){
            carLength = "" ;
        }
        paramMap.put("car_lange",carLength);
        String carType = tvNewFaBuCheYuanCarType.getText().toString();
        if(null == carType){
            carType = "";
        }
        paramMap.put("car_type",carType);

        String people = etNewFaBuCheYuanName.getText().toString();
        if(people == null){
            people = "";
        }
        paramMap.put("people",people);
        String iphone = etNewFaBuCheYuanTel.getText().toString();
        if(iphone == null){
            iphone = "";
        }

        paramMap.put("iphone",iphone);
        String content = etNewFaBuCheYuanDesc.getText().toString();
        if(content  == null){
            content = "";
        }
        paramMap.put("content",content);
        List<String> lastNetImageList = newFaBuCheYuanController.addPicRVAdapter.getNetImageList();
        if(lastNetImageList == null){
            lastNetImageList = new ArrayList<>();
        }
        paramMap.put("imgtu",lastNetImageList);
/*        paramMap.put("imgtu",netImageList);*/
        List<String> deleteImageList = newFaBuCheYuanController.addPicRVAdapter.getDeleteImageLists();
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

    private void faBuCheYuanToNet(){


        pbNewFaBuCheYuan.setVisibility(View.VISIBLE);


        PhoneFormatCheckUtils phoneFormatCheckUtils = new PhoneFormatCheckUtils();
        String tel = etNewFaBuCheYuanTel.getText().toString();
        if(!phoneFormatCheckUtils.isNumber(tel)){
            Toast.makeText(this,"联系电话请输入数字",Toast.LENGTH_LONG).show();
            pbNewFaBuCheYuan.setVisibility(View.GONE);
            return;
        }
        String carLength = etNewFaBuCheYuanCalLength.getText().toString();
        if(!phoneFormatCheckUtils.isNumber(carLength)){
            Toast.makeText(this,"车长请输入数字",Toast.LENGTH_LONG).show();
            pbNewFaBuCheYuan.setVisibility(View.GONE);
            return;
        }

        NewFaBuNetWork newFaBuNetWork = new NewFaBuNetWork();
        newFaBuNetWork.faBuOrUpdateCheYuanToNet(getFaBuParamMap(), new Observer<NewFaBuCheYuanBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(NewFaBuCheYuanActivity.this,"提交失败",Toast.LENGTH_LONG).show();
                pbNewFaBuCheYuan.setVisibility(View.GONE);
            }

            @Override
            public void onNext(NewFaBuCheYuanBean newFaBuCheYuanBean) {
                Toast.makeText(NewFaBuCheYuanActivity.this,newFaBuCheYuanBean.getMsg(),Toast.LENGTH_LONG).show();
                pbNewFaBuCheYuan.setVisibility(View.GONE);

                if(newFaBuCheYuanBean.getStatus().equals("0")){
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
