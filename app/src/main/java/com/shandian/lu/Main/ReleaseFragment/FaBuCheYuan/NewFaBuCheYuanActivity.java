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

import com.example.mynewslayoutlib.Bean.NewCheYuanDetailBean;
import com.example.mynewslayoutlib.Bean.NewFaBuCheYuanBean;
import com.example.mynewslayoutlib.Bean.NewFaBuHuoYuanBean;
import com.example.mynewslayoutlib.Bean.NewFaBuPicBean;
import com.example.mynewslayoutlib.Bean.NewHuoYuanDetailBean;
import com.shandian.lu.BaseActivity;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.Main.ReleaseFragment.FaBuHuoYuan.NewFaBuHuoYuanActivity;
import com.shandian.lu.Main.ReleaseFragment.FaBuHuoYuan.NewFaBuHuoYuanController;
import com.shandian.lu.Main.ReleaseFragment.SelectAddAddress.SelectAddAddressActivity;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
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
    private boolean isUpdate = false;
    private String id = "";
    private NewFaBuCheYuanController newFaBuCheYuanController;
    private  final int ACTIVITY_REQUEST_SELECT_PHOTO = 100;

    private final int ACTIVITY_SELECT_ADDRESS_BEGIN = 105;
    private final int ACTIVITY_SELECT_ADDRESS_END = 106;
    private ArrayList<String> mImageList;
    private boolean isFirst = true;
    int picSize = 0;
    int i = 0;

    private String bProvince,eProvince,bCity,eCity,bArea,eArea,beginAddr,endAddr;
    private String blat,blon,elat,elon;
    private ArrayList imgTuList;
    @BindView(R.id.pb_new_fabucheyuan)
    ProgressBar pbNewFaBuCheYuan;
    @BindView(R.id.tv_new_fabucheyuan_submit)
    TextView tvNewFaBuCheYuanSubmit;
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
        if(newFaBuCheYuanController.addPicRVAdapter.isPicFinished) {
            if(checkParam()) {

                if(isUpdate){
                    updateHuoYuanToNet();

                }else
                {
                    faBuCheYuanToNet();

                }
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
        initController();
        mImageList = new ArrayList<>();
        imgTuList = new ArrayList<>();
        getId();
        getType();


    }

    private void getId(){
        id = getIntent().getStringExtra("id");

       /* Toast.makeText(this,"imgTuList:"+imgTuList.size(),Toast.LENGTH_LONG).show();*/
        if((id != null)&&(!id.isEmpty())){
            isUpdate = true;
            Bundle bundle = getIntent().getExtras();
            if(bundle == null){
                bundle = new Bundle();
            }
            imgTuList = bundle.getStringArrayList("imgTu");
            int size = imgTuList.size();
            if(size > 0) {
          /*  Toast.makeText(this, "size:" + imgTuList.size() + imgTuList.get(0), Toast.LENGTH_LONG).show();*/

                List<String> tempList= new ArrayList<>();
                for(int i=0;i<size;i++){
                    String pic = (String) imgTuList.get(i);
                    if(pic != null){
                        pic = "\""+pic+"\"";
                        tempList.add(pic);
                    }
                }
                imgTuList.clear();
                imgTuList.addAll(tempList);
                newFaBuCheYuanController.addPicRVAdapter.setNetImageList(imgTuList);
            }
            getDataFromNet();
            tvNewFaBuCheYuanSubmit.setText("确认修改");
        }else{
            isUpdate = false;
            tvNewFaBuCheYuanSubmit.setText("确认发布");
        }
    }


    private void getDataFromNet(){
        NewCheHuoListNetWork newCheHuoListNetWork = new NewCheHuoListNetWork();
        newCheHuoListNetWork.getCheYuanDetailFromNet(id, new Observer<NewCheYuanDetailBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewCheYuanDetailBean newCheYuanDetailBean) {
                if(newCheYuanDetailBean.getStatus().equals("0")){
                    initDetail(newCheYuanDetailBean);
                }
            }
        });
    }
    private void initDetail(NewCheYuanDetailBean newCheYuanDetailBean){
        etNewFaBuCheYuanTitle.setText(newCheYuanDetailBean.getNr().getCar_title());
        tvNewFaBuCheYuanBegin.setText(newCheYuanDetailBean.getNr().getCfdizhi());
        tvNewFaBuCheYuanEnd.setText(newCheYuanDetailBean.getNr().getDadizhi());
        String length = newCheYuanDetailBean.getNr().getCar_lange();
        if(length == null){
            length = "";
        }
        int indexOfMi= length.indexOf("米");
        if(indexOfMi > 0){
            length = length.substring(0,indexOfMi);
        }
        etNewFaBuCheYuanCalLength.setText(length);
        etNewFaBuCheYuanName.setText(newCheYuanDetailBean.getNr().getPeople());
        etNewFaBuCheYuanTel.setText(newCheYuanDetailBean.getNr().getIphone());
        etNewFaBuCheYuanDesc.setText(newCheYuanDetailBean.getNr().getContent());
        beginAddr = newCheYuanDetailBean.getNr().getCfdizhi();
        endAddr = newCheYuanDetailBean.getNr().getDadizhi();
        bProvince = newCheYuanDetailBean.getNr().getCfsheng();
        bCity = newCheYuanDetailBean.getNr().getCfshi();
        bArea = newCheYuanDetailBean.getNr().getCfqu();
        eProvince = newCheYuanDetailBean.getNr().getDasheng();
        eCity = newCheYuanDetailBean.getNr().getDashi();
        eArea = newCheYuanDetailBean.getNr().getDaqu();
        blat = newCheYuanDetailBean.getNr().getCflat();
        blon = newCheYuanDetailBean.getNr().getCflng();
        elat = newCheYuanDetailBean.getNr().getDalat();
        elon = newCheYuanDetailBean.getNr().getDalng();
        newFaBuCheYuanController.addPicRVAdapter.setUpdateList(newCheYuanDetailBean.getNr().getImgtu());

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
        List<String> currentImgList = new ArrayList<>();
        List<String> tempImgList = new ArrayList<>();
        tempImgList.addAll(mImageList);
        List<String> deleteTempImgList = new ArrayList<>();
        ArrayList<String> nowSelectImgList = new ArrayList<>();
        currentImgList.clear();

        currentImgList.addAll(newFaBuCheYuanController.addPicRVAdapter.getAllImgList());
        if(!isUpdate) {
            if (isFirst) {
                currentImgList.clear();
            }
        }
        for(int i=0;i<currentImgList.size();i++) {
            System.out.print("\ncurrentImgList"+currentImgList.get(i));
        }

        int currentSize = currentImgList.size();
        int imgSize = mImageList.size();
        for(int i = 0;i<imgSize;i++){
            String pic = mImageList.get(i);
            for(int j=0;j<currentSize;j++){
                String currentPic = currentImgList.get(j);
                if(pic.equals(currentPic)){
                    tempImgList.remove(pic);

                }
            }
        }


   /*     for(int i=0;i<currentImgList.size();i++) {
            System.out.print("\ncurrentImgList"+currentImgList.get(i));
        }*/
        nowSelectImgList.addAll(tempImgList);
        if(nowSelectImgList.size() > 0 ) {
            mImageList.clear();
            mImageList.addAll(currentImgList);
            mImageList.addAll(nowSelectImgList);
            isFirst = false;
        }else{
            if(isUpdate){
                mImageList.clear();
                mImageList.addAll(currentImgList);
            }
        }
        for(int i=0;i<mImageList.size();i++) {
            System.out.print("\nmImageList"+mImageList.get(i));
        }
        for(int i=0;i<nowSelectImgList.size();i++) {
            System.out.print("\nnowSelectImgList"+nowSelectImgList.get(i));
        }

        newFaBuCheYuanController.addPicRVAdapter.setAdapterImage(mImageList);
        newFaBuCheYuanController.addPicRVAdapter.setNewImgList(nowSelectImgList);
        isFirst = false;
    }/*
    private void isSamePicDelete(){
        currentNameNetImageList.clear();
        currentNameNetImageList.addAll(newFaBuCheYuanController.addPicRVAdapter.getCurrentNetImageList());
        int size = currentNameNetImageList.size();
        int sizeT = mImageList.size();
        deleteTempList.clear();
        deleteTempList.addAll(mImageList);
      *//*  Toast.makeText(this,"netSize:"+size+" ImgSize:"+size2,Toast.LENGTH_LONG).show();*//*
        System.out.print("\nnetSize:"+size+" ImgSize:"+sizeT);

        for(int i = 0;i<size;i++){
            String netPic = currentNameNetImageList.get(i);*//*

            System.out.print("\npic:"+netPic);*//*

            for(int j = 0;j<sizeT;j++){
        *//*        System.out.print("\njjjjjpic:"+j);*//*

                String pic = mImageList.get(j);*//*
                System.out.print("\n111pic:"+pic);
*//*
                if(netPic.equals(pic)){
                    int count = deleteTempList.size();
                    if(count == 0){
                        return;
                    }
*//*

                    System.out.print("\ni am delete success:"+pic);*//*
                    deleteTempList.remove(pic);
                }
                continue;
            }
        }

    }
*/



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


    private void updateHuoYuanToNet(){
        pbNewFaBuCheYuan.setVisibility(View.VISIBLE);

        PhoneFormatCheckUtils phoneFormatCheckUtils = new PhoneFormatCheckUtils();
        String tel = etNewFaBuCheYuanTel.getText().toString();
        if(!phoneFormatCheckUtils.isNumber(tel)){
            Toast.makeText(this,"联系电话请输入数字",Toast.LENGTH_LONG).show();
            pbNewFaBuCheYuan.setVisibility(View.GONE);
            return;
        }
        String weight = etNewFaBuCheYuanCalLength.getText().toString();
        if(!phoneFormatCheckUtils.isNumber(weight)){
            Toast.makeText(this,"重量请输入数字",Toast.LENGTH_LONG).show();
            pbNewFaBuCheYuan.setVisibility(View.GONE);
            return;
        }




        NewFaBuNetWork newFaBuNetWork = new NewFaBuNetWork();
        newFaBuNetWork.updateCheYuanToNet( id,getFaBuParamMap(), new Observer<NewFaBuCheYuanBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

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
    }/*

    //图片压缩
    private Bitmap compressImageFromFile(String srcPath) {

        BitmapUtils bitmapUtils = new BitmapUtils();
        Bitmap bitmap = bitmapUtils.getimage(srcPath);
        bitmap = bitmapUtils.compressImage(bitmap);
        bitmap = bitmapUtils.comp(bitmap);

        return bitmap;
    }
*/

}
