package com.shandian.lu.Main.ReleaseFragment.FaBuHuoYuan;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.example.mynewslayoutlib.Bean.NewFaBuHuoYuanBean;
import com.example.mynewslayoutlib.Bean.NewHuoYuanDetailBean;
import com.example.mynewslayoutlib.Bean.NewHuoYuanDetailSelfBean;
import com.shandian.lu.BaiDu.DrivingRouteOverlay;
import com.shandian.lu.BaiDu.RouteLineAdapter;
import com.shandian.lu.BaseActivity;
import com.shandian.lu.Main.IndexFragment.BaiDuRoutePlan.NewBaiDuRoutePlanController;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.Main.MineFragment.WoDeHuoYuan.NewWoDeHuoYuanActivity;
import com.shandian.lu.Main.ReleaseFragment.SelectAddAddress.SelectAddAddressActivity;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.NetWork.NewFaBuNetWork;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Utils.Util;
import com.yanzhenjie.album.Album;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.PhoneFormatCheckUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import zhyan.likeiosselectpopuplib.TimePickerView;

/**
 * Created by Administrator on 2017/6/7.
 */

public class NewFaBuHuoYuanV2Activity extends BaseActivity implements OnGetRoutePlanResultListener {

    private float dis;

    private String typeName ;
    private boolean isUpdate = false;
    private String id = "";
    private NewFaBuHuoYuanV2Controller newFaBuHuoYuanController;
    private  final int ACTIVITY_REQUEST_SELECT_PHOTO = 100;

    private final int ACTIVITY_SELECT_ADDRESS_BEGIN = 105;
    private final int ACTIVITY_SELECT_ADDRESS_END = 106;
    private ArrayList<String> mImageList;
    RoutePlanSearch mSearch = null;    // 搜索模块，也可去掉地图模块独立使用
    private boolean isFirst = true;
    int picSize = 0;
    int i = 0;

    private String bProvince,eProvince,bCity,eCity,bArea,eArea,beginAddr,endAddr;
    private String blat,blon,elat,elon;
    private String juli;
    private ArrayList imgTuList;
    @BindView(R.id.pb_new_fabuhuoyuan)
    ProgressBar pbNewFaBuHuoYuan;
    @BindView(R.id.tv_new_fabuhuoyuan_submit)
    TextView tvNewFaBuHuoYuanSubmit;
    @BindView(R.id.rly_new_fabuhuoyuan_submit)
    RelativeLayout rlyNewFaBuHuoYuanSubmit;
    @OnClick(R.id.rly_new_fabuhuoyuan_submit)
    public void  rlyNewFaBuHuoYuanSubmitOnclick(){
        pbNewFaBuHuoYuan.setVisibility(View.VISIBLE);
        int imgSize = newFaBuHuoYuanController.addPicRVAdapter.getNetImageList().size();
        /*List<String> deleteImageList = newFaBuHuoYuanController.addPicRVAdapter.getDeleteImageLists();
        int delImgSize = deleteImageList.size();*/
        if(imgSize <= 0){
            Toast.makeText(this,"请至少上传1张图片",Toast.LENGTH_LONG).show();
            pbNewFaBuHuoYuan.setVisibility(View.GONE);
            return;
        }
        if(newFaBuHuoYuanController.addPicRVAdapter.isPicFinished) {
            if(checkParam()) {
                if(isUpdate){
                    updateHuoYuanToNet();

                }else
                {
                    faBuHuoYuanToNet();

                }
            }else{
                pbNewFaBuHuoYuan.setVisibility(View.GONE);
            }
        }else{
            Toast.makeText(this,"请等待图片上传完成",Toast.LENGTH_LONG).show();
            pbNewFaBuHuoYuan.setVisibility(View.GONE);
        }


    }
    private boolean checkParam(){

        String price = etNewFaBuHuoYuanPrice.getText().toString();
        if(price.length() == 0){
            Toast.makeText(this,"请输入货主价格",Toast.LENGTH_LONG).show();
            return false;
        }
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
        String tiji = etNewFaBuHuoYuanTiJi.getText().toString();
        if(tiji.length() == 0){
            Toast.makeText(this,"请输入单位体积",Toast.LENGTH_LONG).show();
            return false;
        }
        String xiangshu = etNewFaBuHuoYuanNums.getText().toString();
        if(xiangshu.length() == 0){
            Toast.makeText(this,"请输入装件数",Toast.LENGTH_LONG).show();
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
        String time = tvNewFabuHuoYuanCarTime.getText().toString();
        if(typeName.equals("5")||typeName.equals("6")){

        }else {
            if (time.length() == 0) {
                Toast.makeText(this, "请选择用车时间", Toast.LENGTH_LONG).show();
                return false;
            }
        }
        return true;
    }


    @BindView(R.id.lly_new_fabuhuoyuan_begin)
    LinearLayout llyNewFaBuHuoYuanBegin;
    @OnClick(R.id.lly_new_fabuhuoyuan_begin)
    public void llyNewFaBuHuoYuanBeginOnclick(){
        Intent intent = new Intent(this, SelectAddAddressActivity.class);
        intent.putExtra("type","begin");
        if(blat == null){
            blat = "";
        }
        if(blon == null){
            blon = "";
        }
        intent.putExtra("lat",blat);
        intent.putExtra("lon",blon);
        startActivityForResult(intent,ACTIVITY_SELECT_ADDRESS_BEGIN);
    }
    @BindView(R.id.lly_new_fabuhuoyuan_end)
    LinearLayout llyNewFaBuHuoYuanEnd;
    @OnClick(R.id.lly_new_fabuhuoyuan_end)
    public void llyNewFaBuHuoYuanEndOnclick(){
        Intent intent = new Intent(this, SelectAddAddressActivity.class);
        intent.putExtra("type","end");
        if(elat == null){
            elat = "";
        }
        if(elon == null){
            elon = "";
        }
        intent.putExtra("lat",elat);
        intent.putExtra("lon",elon);
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
    @BindView(R.id.et_new_fabuhuoyuan_tiji)
    EditText etNewFaBuHuoYuanTiJi;
    @BindView(R.id.et_new_fabuhuoyuan_nums)
    EditText etNewFaBuHuoYuanNums;
    @BindView(R.id.et_new_fabuhuoyuan_price)
    EditText etNewFaBuHuoYuanPrice;

    @BindView(R.id.lly_new_fabuhuoyuan_car_time)
    LinearLayout llyNewFaBuHuoYuanCarTime;
    @BindView(R.id.tv_new_fabuhuoyuan_car_time)
    TextView tvNewFabuHuoYuanCarTime;
    @OnClick(R.id.tv_new_fabuhuoyuan_car_time)
    public void tvNewFabuHuoYuanCarTimeOnclick(){
        getTime();
    }


    @BindView(R.id.tv_new_fabuhuoyuan_begin)
    TextView tvNewFaBuHuoYuanBegin;
    @BindView(R.id.tv_new_fabuhuoyuan_end)
    TextView tvNewFaBuHuoYuanEnd;
    @BindView(R.id.tv_new_fabuhuoyuan_topbar_title)
    TextView tvNewFaBuHuoYuanTopBarTitle;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_fabuhuoyuan_v2_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        mImageList = new ArrayList<>();
        initController();


        getId();
        getType();
        initRouteOverLay();

    }
    private void initRouteOverLay(){
        // 初始化搜索模块，注册事件监听
        mSearch = RoutePlanSearch.newInstance();
        mSearch.setOnGetRoutePlanResultListener(this);
    }
    private void searchProcessByLLG(LatLng begLlg , LatLng endLlg){




        PlanNode stNode,enNode;
        stNode = PlanNode.withLocation(begLlg);
        enNode = PlanNode.withLocation(endLlg);

        mSearch.drivingSearch(new DrivingRoutePlanOption().from(stNode).to(enNode));
    }
    private void getTime(){
        String format = "";
        TimePickerView.Type type = null;
        type = TimePickerView.Type.YEAR_MONTH_DAY;
        format = "yyyy-MM-dd";
        Util.alertTimerPicker(this, type, format, new Util.TimerPickerCallBack() {
            @Override
            public void onTimeSelect(String date) {
                /*Toast.makeText(NewFaBuHuoYuanV2Activity.this, date, Toast.LENGTH_SHORT).show();*/
                tvNewFabuHuoYuanCarTime.setText(date);
            }
        });
    }
    private void getDataFromNet(){
        NewCheHuoListNetWork newCheHuoListNetWork = new NewCheHuoListNetWork();
        newCheHuoListNetWork.getHuoYuanDetailSelfV2FromNet(id, "", new Observer<NewHuoYuanDetailSelfBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewHuoYuanDetailSelfBean newHuoYuanDetailSelfBean) {
                if(newHuoYuanDetailSelfBean.getStatus().equals("0")){
                    initDetail(newHuoYuanDetailSelfBean);
                }
            }
        });
    }
    private void initDetail(NewHuoYuanDetailSelfBean newHuoYuanDetailBean){
        etNewFaBuHuoYuanGoodsName.setText(newHuoYuanDetailBean.getNr().getGood_name());
        tvNewFaBuHuoYuanBegin.setText(newHuoYuanDetailBean.getNr().getCfdizhi());
        tvNewFaBuHuoYuanEnd.setText(newHuoYuanDetailBean.getNr().getDadizhi());
        String weight = newHuoYuanDetailBean.getNr().getWeight();
        if(weight == null){
            weight = "";
        }
        int indexOfDun = weight.indexOf("k");
        if(indexOfDun > 0){
            weight = weight.substring(0,indexOfDun);
        }

        etNewFaBuHuoYuanWeight.setText(weight);
        String tiji = newHuoYuanDetailBean.getNr().getTiji();
        if(tiji == null){
            tiji = "";
        }
        int indexOfTiJi = tiji.indexOf("m³");
        if(indexOfTiJi > 0){
            tiji = tiji.substring(0,indexOfTiJi);
        }

        etNewFaBuHuoYuanTiJi.setText(tiji);
        String xiangshu = newHuoYuanDetailBean.getNr().getXiangshu();
        if(xiangshu == null){
            xiangshu = "";
        }
        int indexOfXiangShu = xiangshu.indexOf("件");
        if(indexOfXiangShu > 0){
            xiangshu = xiangshu.substring(0,indexOfXiangShu);
        }

        etNewFaBuHuoYuanNums.setText(xiangshu);
        juli = newHuoYuanDetailBean.getNr().getJuli();
        etNewFaBuHuoYuanName.setText(newHuoYuanDetailBean.getNr().getPeople());
        etNewFaBuHuoYuanTel.setText(newHuoYuanDetailBean.getNr().getIphone());
        etNewFaBuHuoYuanDesc.setText(newHuoYuanDetailBean.getNr().getContext());
        String price = newHuoYuanDetailBean.getNr().getHyjiage();
        if(price == null){
            price = "";
        }
        int indexOfPrice = price.indexOf("元");
        if(indexOfPrice > 0){
            price = price.substring(0,indexOfPrice);
        }

        etNewFaBuHuoYuanPrice.setText(price);
        tvNewFabuHuoYuanCarTime.setText(newHuoYuanDetailBean.getNr().getYcsj());
        beginAddr = newHuoYuanDetailBean.getNr().getCfdizhi();
        endAddr = newHuoYuanDetailBean.getNr().getDadizhi();
        bProvince = newHuoYuanDetailBean.getNr().getCfsheng();
        bCity = newHuoYuanDetailBean.getNr().getCfshi();
        bArea = newHuoYuanDetailBean.getNr().getCfqu();
        eProvince = newHuoYuanDetailBean.getNr().getDasheng();
        eCity = newHuoYuanDetailBean.getNr().getDashi();
        eArea = newHuoYuanDetailBean.getNr().getDaqu();
        blat = newHuoYuanDetailBean.getNr().getCflat();
        blon = newHuoYuanDetailBean.getNr().getCflng();
        elat = newHuoYuanDetailBean.getNr().getDalat();
        elon = newHuoYuanDetailBean.getNr().getDalng();
        newFaBuHuoYuanController.addPicRVAdapter.setUpdateAdapter(newHuoYuanDetailBean.getNr().getImgtu());

        if(blat == null){
            blat = "0.0";
        }
        if(blon == null){
            blon = "0.0";
        }
        if(elat == null){
            elat = "0.0";
        }
        if(elon == null){
            elon = "0.0";
        }
        LatLng bllg = new LatLng(Double.parseDouble(blat),Double.parseDouble(blon));
        LatLng ellg = new LatLng(Double.parseDouble(elat),Double.parseDouble(elon));
        searchProcessByLLG(bllg,ellg);

    }

    private void getId(){
        id = getIntent().getStringExtra("id");

       /* Toast.makeText(this,"imgTuList:"+imgTuList.size(),Toast.LENGTH_LONG).show();*/
        if((id != null)&&(!id.isEmpty())){
            isUpdate = true;
           /* Bundle bundle = getIntent().getExtras();
            if(bundle == null){
                bundle = new Bundle();
            }
            imgTuList = bundle.getStringArrayList("imgTu");
            int size = imgTuList.size();
            if(size > 0) {
          *//*  Toast.makeText(this, "size:" + imgTuList.size() + imgTuList.get(0), Toast.LENGTH_LONG).show();*//*

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
                newFaBuHuoYuanController.addPicRVAdapter.setUpdateAdapter(imgTuList);
            }*/
            getDataFromNet();
            tvNewFaBuHuoYuanSubmit.setText("确认修改");
        }else{
            isUpdate = false;
            tvNewFaBuHuoYuanSubmit.setText("确认发布");
        }
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
            case "5":
                tvNewFaBuHuoYuanTopBarTitle.setText("人人快递");
                llyNewFaBuHuoYuanCarTime.setVisibility(View.GONE);
                break;
            case "6":
                tvNewFaBuHuoYuanTopBarTitle.setText("搬家");
                llyNewFaBuHuoYuanCarTime.setVisibility(View.GONE);
                break;
        }
    }

    private void initController(){
        newFaBuHuoYuanController = new NewFaBuHuoYuanV2Controller(this,mImageList);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data == null){
            return;
        }
        newFaBuHuoYuanController.addPicRVAdapter.isPicFinished = true;
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
                if(blat == null){
                    blat = "0.0";
                }
                if(blon == null){
                    blon = "0.0";
                }
                if(elat == null){
                    elat = "0.0";
                }
                if(elon == null){
                    elon = "0.0";
                }
                LatLng bllg = new LatLng(Double.parseDouble(blat),Double.parseDouble(blon));
                LatLng ellg = new LatLng(Double.parseDouble(elat),Double.parseDouble(elon));
                searchProcessByLLG(bllg,ellg);
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
                if(blat == null){
                    blat = "0.0";
                }
                if(blon == null){
                    blon = "0.0";
                }
                if(elat == null){
                    elat = "0.0";
                }
                if(elon == null){
                    elon = "0.0";
                }
                LatLng bllg = new LatLng(Double.parseDouble(blat),Double.parseDouble(blon));
                LatLng ellg = new LatLng(Double.parseDouble(elat),Double.parseDouble(elon));
                searchProcessByLLG(bllg,ellg);


                break;
            }

            default:

                break;
        }
    }
    private void refreshImage(){
        newFaBuHuoYuanController.addPicRVAdapter.setUpdateAdapter(mImageList);
    }
/*
    private void refreshImage(){
        List<String> currentImgList = new ArrayList<>();
        List<String> tempImgList = new ArrayList<>();
        tempImgList.addAll(mImageList);
        List<String> deleteTempImgList = new ArrayList<>();
        ArrayList<String> nowSelectImgList = new ArrayList<>();
        currentImgList.clear();

        currentImgList.addAll(newFaBuHuoYuanController.addPicRVAdapter.getAllImgList());
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
*//*
        for(int i=0;i<tempImgList.size();i++) {
            System.out.print("\ntempImgList"+tempImgList.get(i));
        }*//*
        int size = tempImgList.size();
        int allImgSize = size + currentSize;
        if(allImgSize > 8){
            int moreSize = allImgSize -8;
            System.out.print("\nmoreSize"+moreSize);
            for(int i=moreSize-1;i>=0;i--){
                String pic = tempImgList.get(i);
                System.out.print("\ntempImgList__delete  pic"+pic);
                tempImgList.remove(pic);

                System.out.print("\ntempImgList__delete"+pic);
            }
        }

   *//*     for(int i=0;i<currentImgList.size();i++) {
            System.out.print("\ncurrentImgList"+currentImgList.get(i));
        }*//*
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
        }*//*
        for(int i=0;i<mImageList.size();i++) {
            System.out.print("\nmImageList"+mImageList.get(i));
        }
        for(int i=0;i<nowSelectImgList.size();i++) {
            System.out.print("\nnowSelectImgList"+nowSelectImgList.get(i));
        }*//*

        newFaBuHuoYuanController.addPicRVAdapter.setAdapterImage(mImageList);
        newFaBuHuoYuanController.addPicRVAdapter.setNewImgList(nowSelectImgList);
        isFirst = false;
      *//*  Toast.makeText(this,"size:"+mImageList.size(),Toast.LENGTH_LONG).show();*//*
*//*        picSize = mImageList.size();
        if(picSize <= 0){
            return;
        }
        i = 0;*//*

 *//*       Thread thread = new PicToNetThread();
        thread.run();*//*
    }*/
/*    class PicToNetThread extends Thread{
        @Override
        public void run(){

        *//*newFaBuHuoYuanController.addPicRVAdapter.setmImageList(mImageList);*//*
       *//*     isSamePicDelete();
            mImageList.clear();
            mImageList.addAll(deleteTempList);
            picSize = mImageList.size();
            if(picSize <= 0){
                return;
            }
            i = 0;
            sendPicToNet();*//*
        }
    }*/





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
        String goodsPrice = etNewFaBuHuoYuanPrice.getText().toString();
        if(goodsPrice == null){
            goodsPrice = "";
        }
        paramMap.put("hyjiage",goodsPrice);
        String goodsName = etNewFaBuHuoYuanGoodsName.getText().toString();
        if(goodsName == null){
            goodsName = "";
        }
        paramMap.put("good_name",goodsName);
        paramMap.put("huowulx",goodsName);
        if(bProvince == null){
            bProvince = "";
        }
      /*  Toast.makeText(this,"bProv"+bProvince,Toast.LENGTH_LONG).show();*/
        paramMap.put("cfsheng",bProvince);

        if(bCity == null){
            bCity = "";
        }
      /*  Toast.makeText(this,"bCity"+bCity,Toast.LENGTH_LONG).show();*/
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
        String tiji = etNewFaBuHuoYuanTiJi.getText().toString();
        if(tiji == null){
            tiji = "";
        }
        paramMap.put("tiji",tiji);
        String nums = etNewFaBuHuoYuanNums.getText().toString();
        if(nums == null){
            nums = "";
        }
        paramMap.put("xiangshu",nums);
        String ycsj = tvNewFabuHuoYuanCarTime.getText().toString();
        if(ycsj == null){
            ycsj = "";
        }
        paramMap.put("ycsj",ycsj);
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
        if(juli  == null){
            juli = "";
        }
        paramMap.put("juli",juli);
        List<String> lastNetImageList = newFaBuHuoYuanController.addPicRVAdapter.getNetImageList();
        if(lastNetImageList == null){
            lastNetImageList = new ArrayList<>();
        }
      /*  Toast.makeText(this,"lastNetImgList:"+lastNetImageList.size()+lastNetImageList.get(0),Toast.LENGTH_LONG).show();*/
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



        System.out.print("\n begin paramMap"+paramMap);/*
        System.out.print("\n begin paramMap"+paramMap);
        System.out.print("\n begin paramMap"+paramMap);
        System.out.print("\n begin paramMap"+paramMap);
        System.out.print("\n begin paramMap"+paramMap);
        System.out.print("\n begin paramMap"+paramMap);*/
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
        newFaBuNetWork.faBuHuoYuanToNet(getFaBuParamMap(), new Observer<NewFaBuHuoYuanBean>() {
            @Override
            public void onCompleted() {
                /*pbNewFaBuHuoYuan.setVisibility(View.GONE);*/
            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(NewFaBuHuoYuanV2Activity.this,"提交失败",Toast.LENGTH_LONG).show();
                pbNewFaBuHuoYuan.setVisibility(View.GONE);
            }

            @Override
            public void onNext(NewFaBuHuoYuanBean newFaBuHuoYuanBean) {
                Toast.makeText(NewFaBuHuoYuanV2Activity.this,newFaBuHuoYuanBean.getMsg(),Toast.LENGTH_LONG).show();
                pbNewFaBuHuoYuan.setVisibility(View.GONE);
                if(newFaBuHuoYuanBean.getStatus().equals("0")){
                    Intent intent = new Intent(NewFaBuHuoYuanV2Activity.this, NewWoDeHuoYuanActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void updateHuoYuanToNet(){
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
        newFaBuNetWork.updateHuoYuanToNet( id,getFaBuParamMap(), new Observer<NewFaBuHuoYuanBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewFaBuHuoYuanBean newFaBuHuoYuanBean) {
                Toast.makeText(NewFaBuHuoYuanV2Activity.this,newFaBuHuoYuanBean.getMsg(),Toast.LENGTH_LONG).show();
                pbNewFaBuHuoYuan.setVisibility(View.GONE);
                if(newFaBuHuoYuanBean.getStatus().equals("0")){
                    finish();
                }
            }
        });
    }

    @Override
    public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {

    }

    @Override
    public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

    }

    @Override
    public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

    }

    @Override
    public void onGetDrivingRouteResult(DrivingRouteResult result) {
        if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
          /*  Toast.makeText(this, "抱歉，未找到结果", Toast.LENGTH_SHORT).show();*/
        }
        if (result.error == SearchResult.ERRORNO.AMBIGUOUS_ROURE_ADDR) {
            // 起终点或途经点地址有岐义，通过以下接口获取建议查询信息
            // result.getSuggestAddrInfo()
            return;
        }
        if (result.error == SearchResult.ERRORNO.NO_ERROR) {



            if (result.getRouteLines().size() > 0) {
                dis = result.getRouteLines().get(0).getDistance();
                int size = result.getRouteLines().size();
                float tempDis = 0;
                for(int i=0;i<size;i++){
                    float routeLines = result.getRouteLines().get(i).getDistance();
                     if(routeLines < dis){
                         dis = routeLines;
                     }
                }
                juli = dis+"";
              /*  Toast.makeText(this,"juli:"+dis,Toast.LENGTH_LONG).show();*/
            }

        }
    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mSearch.destroy();
    }

/*    //图片压缩
    private Bitmap compressImageFromFile(String srcPath) {

        BitmapUtils bitmapUtils = new BitmapURtils();
        Bitmap bitmap = bitmapUtils.getimage(srcPath);
        bitmap = bitmapUtils.compressImage(bitmap);
        bitmap = bitmapUtils.comp(bitmap);

        return bitmap;
    }*/
}
