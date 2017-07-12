package com.shandian.lu.Main.ReleaseFragment.FaBuCheYuan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
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
import com.example.mynewslayoutlib.Bean.NewCheYuanDetailBean;
import com.example.mynewslayoutlib.Bean.NewFaBuCheYuanBean;
import com.example.mynewslayoutlib.Bean.NewFaBuPicBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.Main.MineFragment.WoDeCheYuan.NewWoDeCheYuanActivity;
import com.shandian.lu.Main.ReleaseFragment.SelectAddAddress.SelectAddAddressActivity;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.NetWork.NewFaBuNetWork;
import com.shandian.lu.Widget.Dialog.NewFaBuBanJiaAddCarTypeDialog;
import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;
import com.yanzhenjie.album.Album;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.BitmapUtils;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ImageView.RoundCornerImageView.RoundCornerImageView;
import com.shandian.lu.Widget.Dialog.NewFaBuBanJiaAddCarTypeDialog.RoundCornerImageViewCallBackListener;
import com.shandian.lu.Widget.Dialog.NewFaBuBanJiaAddCarTypeDialog.MsgCallBackListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/7/6.
 */

public class NewFaBuBanJiaActivity extends BaseActivity implements OnGetRoutePlanResultListener {


    private String bProvince,eProvince,bCity,eCity,bArea,eArea,beginAddr,endAddr;
    private String blat,blon,elat,elon;
    private final int ACTIVITY_SELECT_ADDRESS_BEGIN = 105;
    private final int ACTIVITY_SELECT_ADDRESS_END = 106;
    private final int PICK_PHOTO_FROM_PHONE_CARTYPE_HEADIMG = 100;
    private final int UPDATE_PHOTO_FROM_PHONE_CARTYPE_HEADIMG = 101;
    NewFaBuBanJiaAddCarTypeDialog newFaBuBanJiaAddCarTypeDialog;
    RoundCornerImageView rcivHeadImageView;
    ProgressBar rcivProgressBar;
    Map<String,Object> carTypeMap;
    private String typeName ;
    List<Object> carTypeList;
    private boolean isTouXiangUpFinished = false;
    private ArrayList<String> imgList;
    private ArrayList<String> netImgList;
    private String netImgUrl = "";
    private String picPath = "";
    private Bitmap touXiangBitmap;
    private float dis;
    private String juli;
    private String cyId = "";
    private String id = "";
    private int pos1 = 0;
    private boolean isUpdate = false;
    @BindView(R.id.rly_new_fabubanjia_submit)
    RelativeLayout rlyNewFaBuBanJiaSubmit;
    @OnClick(R.id.rly_new_fabubanjia_submit)
    public void rlyNewFaBuBanJiaSubmitOnclick(){
        if(isUpdate){
            updateBanJiaToNet();
        }else {
            faBuBanJiaToNet();
        }
    }
    @BindView(R.id.tv_new_fabubanjia_submit)
    TextView tvNewFaBuBanJiaSubmit;
    @BindView(R.id.pb_new_fabubanjia)
    ProgressBar pbNewFaBuBanJia;
    @BindView(R.id.tv_new_fabubanjia_topbar_title)
    TextView tvNewFaBuBanJiaTopBarTitle;
    @BindView(R.id.et_new_fabubanjia_title)
    EditText etNewFaBuBanJiaTitle;
    @BindView(R.id.lly_new_fabubanjia_begin)
    LinearLayout llyNewFaBuBanJiaBegin;
    @OnClick(R.id.lly_new_fabubanjia_begin)
    public void llyNewFaBuBanJiaBeginOnclick(){
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
    @BindView(R.id.tv_new_fabubanjia_begin)
    TextView tvNewFaBuBanJiaBegin;
    @BindView(R.id.lly_new_fabubanjia_end)
    LinearLayout llyNewFaBuBanJiaEnd;
    @OnClick(R.id.lly_new_fabubanjia_end)
    public void llyNewFaBuBanJiaEndOnclick(){
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
    RoutePlanSearch mSearch = null;    // 搜索模块，也可去掉地图模块独立使用
    @BindView(R.id.tv_new_fabubanjia_end)
    TextView tvNewFaBuBanJiaEnd;
    @BindView(R.id.et_new_fabubanjia_name)
    EditText etNewFaBuBanJiaName;
    @BindView(R.id.et_new_fabubanjia_tel)
    EditText etNewFaBuBanJiaTel;
    @BindView(R.id.et_new_fabubanjia_company_introdu)
    EditText etNewFaBuBanJiaCompanyIntrodu;
    @BindView(R.id.et_new_fabubanjia_desc)
    EditText etNewFabuBanJiaDesc;
    @BindView(R.id.lly_new_fabubanjia_cartype)
    LinearLayout llyNewFaBuBanJiaCarType;
    @OnClick(R.id.lly_new_fabubanjia_cartype)
    public void llyNewFaBuBanJiaCarTypeOnclick(){
        newFaBuBanJiaAddCarTypeDialog = new NewFaBuBanJiaAddCarTypeDialog(this,imgList,null,null,null,null,null,0,false).Build.setRoundCornerImageViewCallBackListener(new RoundCornerImageViewCallBackListener() {
            @Override
            public void rcivCallBack(RoundCornerImageView rcivImageView, ProgressBar rcivIvPB) {
                rcivHeadImageView = rcivImageView;
                rcivProgressBar = rcivIvPB;
            }
        }).setMsgCallBackListener(new MsgCallBackListener() {
            @Override
            public void msgCallBack(String name, String tj, String zz,int pos) {
                if( !isTouXiangUpFinished ){
                    Toast.makeText(NewFaBuBanJiaActivity.this,"等待头像上传完毕",3000).show();
                    return;
                }
                if((touXiangBitmap == null)||( picPath == null)){
                    Toast.makeText(NewFaBuBanJiaActivity.this,"头像不能为空",3000).show();
                    return;
                }
                if(name.isEmpty()){
                    Toast.makeText(NewFaBuBanJiaActivity.this,"名称不能为空",3000).show();
                    return;
                }
                if(tj.isEmpty()){
                    Toast.makeText(NewFaBuBanJiaActivity.this,"体积不能为空",3000).show();
                    return;
                }
                if(zz.isEmpty()){
                    Toast.makeText(NewFaBuBanJiaActivity.this,"载重不能为空",3000).show();
                    return;
                }
              /*  Toast.makeText(NewFaBuBanJiaActivity.this,"not",3000).show();*/
           /*     if(isUpdate){
                    Toast.makeText(NewFaBuBanJiaActivity.this,"isUpdate",3000).show();
                    carTypeMap = (HashMap<String,Object>) carTypeList.get(pos);
                    carTypeMap.put("img", touXiangBitmap);
                    carTypeMap.put("picPath", picPath);
                    carTypeMap.put("name", name);
                    carTypeMap.put("tj", tj);
                    carTypeMap.put("zz", zz);
                    carTypeList.set(pos,carTypeMap);
                    netImgList.set(pos,netImgUrl);
                }else {*/
                    /*Toast.makeText(NewFaBuBanJiaActivity.this,"noUpdate",3000).show();*/
                    carTypeMap = new HashMap<String, Object>();
                    carTypeMap.put("img", touXiangBitmap);
                    carTypeMap.put("picPath", picPath);
                    carTypeMap.put("name", name);
                    carTypeMap.put("tj", tj);
                    carTypeMap.put("zz", zz);
                    carTypeList.add(carTypeMap);
                    netImgList.add(netImgUrl);
              /*  }*/
                if(newFaBuBanJiaController != null){
                    newFaBuBanJiaController.newFaBuBanJiaCarTypeRVAdapter.setAdapter(carTypeList,netImgList);
                    dimssAddCarTypeDialog();
                }
            }
        }).build(this);
        showAddCarTypeDialog();
    }



    public void showAddCarTypeDialog() {
        if (newFaBuBanJiaAddCarTypeDialog != null && !newFaBuBanJiaAddCarTypeDialog.isShowing())
            newFaBuBanJiaAddCarTypeDialog.show();
    }

    public void dimssAddCarTypeDialog() {
        if (newFaBuBanJiaAddCarTypeDialog != null && newFaBuBanJiaAddCarTypeDialog.isShowing())
            newFaBuBanJiaAddCarTypeDialog.dismiss();
    }

    private NewFaBuBanJiaController newFaBuBanJiaController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_fabubanjia_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        getType();
        initRouteOverLay();
        initController();
        getCYId();
        getNewCheYuanDetailFromNet();
        carTypeList = new ArrayList<>();
        imgList = new ArrayList<>();
        netImgList = new ArrayList<>();
    }
    private void initController(){
        newFaBuBanJiaController = new NewFaBuBanJiaController(this);
        newFaBuBanJiaController.newFaBuBanJiaCarTypeRVAdapter.setAdapterUpdateImageViewCallBack(new NewFaBuBanJiaCarTypeRVAdapter.AdapterUpdateImageViewListener() {
            @Override
            public void updateRcivCallBack(RoundCornerImageView rcivImageView, ProgressBar rcivIvPB, int pos) {
                rcivHeadImageView = rcivImageView;
                rcivProgressBar = rcivIvPB;
                pos1 = pos;
            }
        });
        newFaBuBanJiaController.newFaBuBanJiaCarTypeRVAdapter.setAdapterMsgCallBackListener(new NewFaBuBanJiaCarTypeRVAdapter.AdapterMsgCallBackListener() {
            @Override
            public void msgCallBack(String name, String tj, String zz, int pos) {
                if( !isTouXiangUpFinished ){
                    Toast.makeText(NewFaBuBanJiaActivity.this,"等待头像上传完毕",3000).show();
                    return;
                }
                if((touXiangBitmap == null)||( picPath == null)){
                    Toast.makeText(NewFaBuBanJiaActivity.this,"头像不能为空",3000).show();
                    return;
                }
                if(name.isEmpty()){
                    Toast.makeText(NewFaBuBanJiaActivity.this,"名称不能为空",3000).show();
                    return;
                }
                if(tj.isEmpty()){
                    Toast.makeText(NewFaBuBanJiaActivity.this,"体积不能为空",3000).show();
                    return;
                }
                if(zz.isEmpty()){
                    Toast.makeText(NewFaBuBanJiaActivity.this,"载重不能为空",3000).show();
                    return;
                }


                   /* Toast.makeText(NewFaBuBanJiaActivity.this,"isUpdate",3000).show();*/
                carTypeMap = (HashMap<String,Object>) carTypeList.get(pos);
                carTypeMap.put("img", touXiangBitmap);
                carTypeMap.put("picPath", picPath);
                carTypeMap.put("name", name);
                carTypeMap.put("tj", tj);
                carTypeMap.put("zz", zz);
                carTypeList.set(pos,carTypeMap);
                netImgList.set(pos,netImgUrl);
                if(newFaBuBanJiaController != null){
                    newFaBuBanJiaController.newFaBuBanJiaCarTypeRVAdapter.setAdapter(carTypeList,netImgList);
                    dimssAddCarTypeDialog();
                }
            }
        });
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
    private void getType(){
        typeName = getIntent().getStringExtra("type_name");
        if(typeName == null){
            return;
        }

        switch (typeName){
            case "1":
                tvNewFaBuBanJiaTopBarTitle.setText("同城货运");
                break;
            case "2":
                tvNewFaBuBanJiaTopBarTitle.setText("长途物流");
                break;
            case "3":
                tvNewFaBuBanJiaTopBarTitle.setText("特种物流");
                break;
            case "4":
                tvNewFaBuBanJiaTopBarTitle.setText("专线物流");
                break;
            case "5":
                tvNewFaBuBanJiaTopBarTitle.setText("人人快递");
                break;
            case "6":
                tvNewFaBuBanJiaTopBarTitle.setText("搬家");

                break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        switch (requestCode){
            case PICK_PHOTO_FROM_PHONE_CARTYPE_HEADIMG:
            if (resultCode == RESULT_OK) { // Successfully.
                imgList.clear();
                imgList = Album.parseResult(data); // Parse select result.
                   /* Toast.makeText(this,"ACTIVITY_REQUEST_SELECT_PHOTO:"+imgList.size(),3000).show();*/
                if(imgList.size() != 0){
                    refreshImg();
                }
            } else if (resultCode == RESULT_CANCELED) { // User canceled.
                   /* Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();*/
                   /* mImageList.clear();*/
            }
            break;
            case UPDATE_PHOTO_FROM_PHONE_CARTYPE_HEADIMG:
            if (resultCode == RESULT_OK) { // Successfully.
                imgList.clear();
                imgList = Album.parseResult(data); // Parse select result.
                /*    Toast.makeText(this,"ACTIVITY_REQUEST_SELECT_PHOTO:"+imgList.size(),3000).show();*/
                if(imgList.size() != 0){
                    refreshImg();
                }
            } else if (resultCode == RESULT_CANCELED) { // User canceled.
                   /* Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();*/
                   /* mImageList.clear();*/
            }
            break;
            case ACTIVITY_SELECT_ADDRESS_BEGIN:{

                Bundle begin = data.getExtras();
              /*  Toast.makeText(this,"1",Toast.LENGTH_LONG).show();*/
                bProvince = begin.getString("province");
                bCity = begin.getString("city");
                bArea = begin.getString("area");
                blat = begin.getString("lat");
                blon = begin.getString("lon");
                beginAddr = begin.getString("addr");
                tvNewFaBuBanJiaBegin.setText(beginAddr);
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
/*                LatLng bllg = new LatLng(Double.parseDouble(blat),Double.parseDouble(blon));
                LatLng ellg = new LatLng(Double.parseDouble(elat),Double.parseDouble(elon));*/
                LatLng bllg = new LatLng(Double.parseDouble(blat),Double.parseDouble(blon));
                LatLng ellg = new LatLng(Double.parseDouble(elat),Double.parseDouble(elon));
                searchProcessByLLG(bllg,ellg);
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
                tvNewFaBuBanJiaEnd.setText(endAddr);
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
/*                LatLng bllg = new LatLng(Double.parseDouble(blat),Double.parseDouble(blon));
                LatLng ellg = new LatLng(Double.parseDouble(elat),Double.parseDouble(elon));*/
                LatLng bllg = new LatLng(Double.parseDouble(blat),Double.parseDouble(blon));
                LatLng ellg = new LatLng(Double.parseDouble(elat),Double.parseDouble(elon));
                searchProcessByLLG(bllg,ellg);
                break;
            }
        }

    }

    private void refreshImg(){

        touXiangBitmap = compressImageFromFile(imgList.get(0));
        upPicToNet(touXiangBitmap);
    }
    private Map<String,String> getUpPicParamMap(Bitmap bm){
        Map<String,String> paramMap = new HashMap<>();
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(this);
        String loginId= xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            Toast.makeText(this,"请登录",3000).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return paramMap;
        }
        paramMap.put("login_id",loginId);
        if(bm == null){
            return paramMap;
        }
        BitmapUtils bitmapUtils = new BitmapUtils();
        //将图片显示到ImageView中
        String base64_00 = bitmapUtils.bitmapConvertBase64(bm);
        paramMap.put("tu",base64_00);
        return paramMap;

    }

    private void upPicToNet(final Bitmap bitmap){
        rcivProgressBar.setVisibility(View.VISIBLE);
        isTouXiangUpFinished = false;
        NewFaBuNetWork newFaBuNetWork = new NewFaBuNetWork();
        newFaBuNetWork.upPicToNet(getUpPicParamMap(bitmap), new Observer<NewFaBuPicBean>() {
            @Override
            public void onCompleted() {
                rcivProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                rcivProgressBar.setVisibility(View.GONE);
            }

            @Override
            public void onNext(NewFaBuPicBean newFaBuPicBean) {
                if(newFaBuPicBean.getStatus().equals("0")){
                    netImgUrl = "";
                    String netImg = newFaBuPicBean.getImgurl();
                    if((netImg == null)||(netImg.isEmpty())){
                        return;
                    }
                    netImgUrl = netImg;
                    picPath = imgList.get(0);
                    rcivHeadImageView.setImageBitmap(bitmap);
                    rcivProgressBar.setVisibility(View.GONE);
                    isTouXiangUpFinished = true;
                    imgList.clear();
                        /*pbNewFaBuHuoYuan.setVisibility(View.GONE);*/
                }
            }
        });
    }
    //图片压缩
    private Bitmap compressImageFromFile(String srcPath) {

        BitmapUtils bitmapUtils = new BitmapUtils();
        Bitmap bitmap = bitmapUtils.getimage(srcPath);
/*        bitmap = bitmapUtils.compressImage(bitmap);
        bitmap = bitmapUtils.comp(bitmap);*/

        return bitmap;
    }


    private Map<String,Object> getFuBuParamMap(){
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
        List<Object> carTypeList = newFaBuBanJiaController.newFaBuBanJiaCarTypeRVAdapter.getFaBuList();

        if(carTypeList == null){
            return paramMap;
        }
        paramMap.put("bjcxixni",carTypeList);

        if(typeName == null){
            return paramMap;
        }
        paramMap.put("type_name",typeName);

        String carTitle = etNewFaBuBanJiaTitle.getText().toString();
        if(carTitle == null){
            carTitle = "";
        }
        paramMap.put("car_title",carTitle);

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
        if(beginAddr == null){
            beginAddr = "";
        }
        paramMap.put("cfdizhi",beginAddr);
        paramMap.put("dasheng", eProvince);
        if (eCity == null) {
            eCity = "";
        }
        paramMap.put("dashi", eCity);
        if (eArea == null) {
            eArea = "";
        }
        paramMap.put("daqu", eArea);
        String dZuoBiao = elat + "," + elon;
        paramMap.put("dazuobiao", dZuoBiao);
        if(endAddr == null){
            endAddr = "";
        }
        paramMap.put("dadizhi",endAddr);


        String people = etNewFaBuBanJiaName.getText().toString();
        if(people == null){
            people = "";
        }
        paramMap.put("people",people);
        String iphone = etNewFaBuBanJiaTel.getText().toString();
        if(iphone == null){
            iphone = "";
        }
        paramMap.put("iphone",iphone);
        String jianjie = etNewFaBuBanJiaCompanyIntrodu.getText().toString();
        if(jianjie == null){
            jianjie = "";
        }
        paramMap.put("jianjie",jianjie);
        String content = etNewFabuBanJiaDesc.getText().toString();
        if(content == null){
            content = "";
        }
        paramMap.put("content",content);
        if(juli  == null){
            juli = "";
        }
        paramMap.put("juli",juli);

        return paramMap;
    }


    private void faBuBanJiaToNet(){
        pbNewFaBuBanJia.setVisibility(View.VISIBLE);
        NewFaBuNetWork newFaBuNetWork = new NewFaBuNetWork();
        newFaBuNetWork.faBuOrUpdateCheYuanToNet(getFuBuParamMap(), new Observer<NewFaBuCheYuanBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Toast.makeText(NewFaBuBanJiaActivity.this,"提交失败",Toast.LENGTH_LONG).show();
                pbNewFaBuBanJia.setVisibility(View.GONE);
            }

            @Override
            public void onNext(NewFaBuCheYuanBean newFaBuCheYuanBean) {
                Toast.makeText(NewFaBuBanJiaActivity.this,newFaBuCheYuanBean.getMsg(),Toast.LENGTH_LONG).show();
                pbNewFaBuBanJia.setVisibility(View.GONE);

                if(newFaBuCheYuanBean.getStatus().equals("0")){
                    /*Intent intent = new Intent(NewFaBuBanJiaActivity.this, NewWoDeCheYuanActivity.class);
                    startActivity(intent);
                    finish();*/
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

           /*     Toast.makeText(this,"juli:"+dis,Toast.LENGTH_LONG).show();*/
            }

        }
    }

    @Override
    public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

    }

    @Override
    public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

    }







    private void getCYId(){
        cyId = getIntent().getStringExtra("cyid");
        if((cyId == null)||(cyId.isEmpty())){
            cyId = "";
        }else {
            tvNewFaBuBanJiaSubmit.setText("确认修改");
            isUpdate = true;
        }

    }

    private void getNewCheYuanDetailFromNet(){
        pbNewFaBuBanJia.setVisibility(View.VISIBLE);
        NewCheHuoListNetWork cheHuoListNetWork = new NewCheHuoListNetWork();
        cheHuoListNetWork.getCheYuanDetailFromNet(cyId, new Observer<NewCheYuanDetailBean>() {
            @Override
            public void onCompleted() {
                pbNewFaBuBanJia.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                pbNewFaBuBanJia.setVisibility(View.GONE);
            }

            @Override
            public void onNext(NewCheYuanDetailBean newCheYuanDetailBean) {
                initDetail(newCheYuanDetailBean);
                pbNewFaBuBanJia.setVisibility(View.GONE);
            }
        });
    }
    private void initDetail(NewCheYuanDetailBean newCheYuanDetailBean){
        blat = newCheYuanDetailBean.getNr().getCflat();
        bProvince = newCheYuanDetailBean.getNr().getCfsheng();
        bCity = newCheYuanDetailBean.getNr().getCfshi();
        bArea = newCheYuanDetailBean.getNr().getCfqu();
        elat = newCheYuanDetailBean.getNr().getDalat();
        eProvince = newCheYuanDetailBean.getNr().getDasheng();
        eCity = newCheYuanDetailBean.getNr().getDashi();
        eArea = newCheYuanDetailBean.getNr().getDaqu();
        beginAddr = newCheYuanDetailBean.getNr().getCfdizhi();
        tvNewFaBuBanJiaBegin.setText(beginAddr);
        endAddr = newCheYuanDetailBean.getNr().getDadizhi();
        juli = newCheYuanDetailBean.getNr().getJuli();
        tvNewFaBuBanJiaEnd.setText(endAddr);
        etNewFaBuBanJiaTitle.setText(newCheYuanDetailBean.getNr().getCar_title());
        etNewFaBuBanJiaName.setText(newCheYuanDetailBean.getNr().getPeople());
        etNewFaBuBanJiaTel.setText(newCheYuanDetailBean.getNr().getIphone());
        etNewFaBuBanJiaCompanyIntrodu.setText(newCheYuanDetailBean.getNr().getJianjie());
        etNewFabuBanJiaDesc.setText(newCheYuanDetailBean.getNr().getContent());
        id = newCheYuanDetailBean.getNr().getId();
        List<NewCheYuanDetailBean.NrBean.BjcxixniBean> dataList = newCheYuanDetailBean.getNr().getBjcxixni();
        if(dataList == null){
            return;
        }
        int size = dataList.size();



        for(int i=0;i<size;i++){
            Map<String,Object> paramMap = new HashMap<>();
            String imgUrl = dataList.get(i).getImg();
            String name = dataList.get(i).getName();
            String tj = dataList.get(i).getTj();
            String zz = dataList.get(i).getZz();
            netImgList.add(imgUrl);
            paramMap.put("img",null);
            paramMap.put("name",name);
            paramMap.put("tj",tj);
            paramMap.put("zz",zz);
            carTypeList.add(paramMap);
        }

        newFaBuBanJiaController.newFaBuBanJiaCarTypeRVAdapter.setAdapter(carTypeList,netImgList);
    }



    private void updateBanJiaToNet(){

        pbNewFaBuBanJia.setVisibility(View.VISIBLE);
        NewFaBuNetWork newFaBuNetWork = new NewFaBuNetWork();
        newFaBuNetWork.updateCheYuanToNet( id,getFuBuParamMap(), new Observer<NewFaBuCheYuanBean>() {
            @Override
            public void onCompleted() {
                pbNewFaBuBanJia.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                pbNewFaBuBanJia.setVisibility(View.GONE);
            }

            @Override
            public void onNext(NewFaBuCheYuanBean newFaBuCheYuanBean) {
                Toast.makeText(NewFaBuBanJiaActivity.this,newFaBuCheYuanBean.getMsg(),Toast.LENGTH_LONG).show();
                pbNewFaBuBanJia.setVisibility(View.GONE);
                if(newFaBuCheYuanBean.getStatus().equals("0")){
                    finish();
                }
            }
        });
    }





    @Override
    public void onDestroy(){
        super.onDestroy();
        mSearch.destroy();
    }
}
