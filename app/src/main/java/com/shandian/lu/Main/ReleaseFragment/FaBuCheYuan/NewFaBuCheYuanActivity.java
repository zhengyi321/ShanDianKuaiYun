package com.shandian.lu.Main.ReleaseFragment.FaBuCheYuan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
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

public class NewFaBuCheYuanActivity extends BaseActivity {

    private String typeName ;
    private NewFaBuCheYuanController newFaBuCheYuanController;
    private  final int ACTIVITY_REQUEST_SELECT_PHOTO = 100;

    private final int ACTIVITY_SELECT_ADDRESS_BEGIN = 105;
    private final int ACTIVITY_SELECT_ADDRESS_END = 106;
    private ArrayList<String> mImageList;
    private ArrayList<String> netImageList;
    int picSize = 0;
    int i = 0;

    private String bProvince,eProvince,bCity,eCity,bArea,eArea,beginAddr,endAddr;
    private String blat,blon,elat,elon;

    @BindView(R.id.pb_new_fabucheyuan)
    ProgressBar pbNewFaBuCheYuan;
    @BindView(R.id.lly_new_fabucheyuan_submit)
    LinearLayout llyNewFaBuCheYuanSubmit;
    @OnClick(R.id.lly_new_fabucheyuan_submit)
    public void  llyNewFaBuCheYuanSubmitOnclick(){
        faBuCheYuanToNet();
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
        getType();
        initController();

    }
    private void getType(){
        typeName = getIntent().getStringExtra("type_name");
    }

    private void initController(){
        newFaBuCheYuanController = new NewFaBuCheYuanController(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
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
                tvNewFaBuCheYuanBegin.setText(beginAddr);
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
                tvNewFaBuCheYuanEnd.setText(endAddr);
                break;
            }


        }
    }


    private void refreshImage(){
        newFaBuCheYuanController.addPicRVAdapter.setAdapterImage(mImageList);
        newFaBuCheYuanController.addPicRVAdapter.setmImageList(mImageList);
        picSize = mImageList.size();
        if(picSize <= 0){
            return;
        }
        i = 0;
        sendPicToNet();
    }


    private void sendPicToNet(){


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
                        netImageList.add(newFaBuPicBean.getImgurl());
                        i++;
                        sendPicToNet();
                    }
                }
            });
        }else{
            newFaBuCheYuanController.addPicRVAdapter.setNetImageList(netImageList);
            pbNewFaBuCheYuan.setVisibility(View.GONE);
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
        List<String> bZuoBiao = new ArrayList<>();
        bZuoBiao.add(blat);
        bZuoBiao.add(blon);
        paramMap.put("cfzuobiao",bZuoBiao);
        if(eProvince == null){
            eProvince = "";
        }
        paramMap.put("dasheng ",eProvince);
        if(eCity == null){
            eCity = "";
        }
        paramMap.put("dashi ",eCity);
        if(eArea == null){
            eArea = "";
        }
        paramMap.put("daqu ",eArea);
        List<String> dZuoBiao = new ArrayList<>();
        dZuoBiao.add(elat);
        dZuoBiao.add(elon);
        paramMap.put("dazuobiao ",dZuoBiao);
        String carLength = etNewFaBuCheYuanCalLength.getText().toString();
        if(null == carLength){
            carLength = "" ;
        }
        paramMap.put("car_lange ",dZuoBiao);
        String carType = tvNewFaBuCheYuanCarType.getText().toString();
        if(null == carType){
            carType = "";
        }
        paramMap.put("car_type ",carType);

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
        paramMap.put("imgtu",netImageList);
        List<String> deleteImageList = newFaBuCheYuanController.addPicRVAdapter.getDeleteImageLists();
        if(deleteImageList == null){
            deleteImageList = new ArrayList<>();
        }
        paramMap.put("deltu",deleteImageList);
        if(beginAddr == null){
            beginAddr = "";
        }
        paramMap.put("cfdizh",beginAddr);
        if(endAddr == null){
            endAddr = "";
        }
        paramMap.put("dadizhi",endAddr);

        return paramMap;
    }

    private void faBuCheYuanToNet(){
        pbNewFaBuCheYuan.setVisibility(View.VISIBLE);
        NewFaBuNetWork newFaBuNetWork = new NewFaBuNetWork();
        newFaBuNetWork.faBuOrUpdateCheYuanToNet(getFaBuParamMap(), new Observer<NewFaBuCheYuanBean>() {
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
                /*if(newFaBuCheYuanBean.getStatus().equals("0")){

                }*/
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
