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
import com.example.mynewslayoutlib.Bean.NewFaBuCheYuanBean;
import com.example.mynewslayoutlib.Bean.NewFaBuPicBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.Main.MineFragment.WoDeCheYuan.NewWoDeCheYuanActivity;
import com.shandian.lu.Main.ReleaseFragment.SelectAddAddress.SelectAddAddressActivity;
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

public class NewFaBuBanJiaActivity extends BaseActivity {


    private String bProvince,eProvince,bCity,eCity,bArea,eArea,beginAddr,endAddr;
    private String blat,blon,elat,elon;
    private final int ACTIVITY_SELECT_ADDRESS_BEGIN = 105;
    private final int ACTIVITY_SELECT_ADDRESS_END = 106;
    private final int PICK_PHOTO_FROM_PHONE_CARTYPE_HEADIMG = 100;
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

    @BindView(R.id.rly_new_fabubanjia_submit)
    RelativeLayout rlyNewFaBuBanJiaSubmit;
    @OnClick(R.id.rly_new_fabubanjia_submit)
    public void rlyNewFaBuBanJiaSubmitOnclick(){
        faBuBanJiaToNet();
    }
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
            public void msgCallBack(String name, String tj, String zz) {
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
                carTypeMap = new HashMap<String, Object>();
                carTypeMap.put("img",touXiangBitmap);
                carTypeMap.put("picPath",picPath);
                carTypeMap.put("name",name);
                carTypeMap.put("tj",tj);
                carTypeMap.put("zz",zz);
                carTypeList.add(carTypeMap);
                netImgList.add(netImgUrl);
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
        initController();
        carTypeList = new ArrayList<>();
        imgList = new ArrayList<>();
        netImgList = new ArrayList<>();
    }
    private void initController(){
        newFaBuBanJiaController = new NewFaBuBanJiaController(this);
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
                   /* Toast.makeText(this,"ACTIVITY_REQUEST_SELECT_PHOTO:"+mImageList.get(0),3000).show();*/
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
}
