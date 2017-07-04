package com.shandian.lu.Main.MineFragment.WoDeRenZheng;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewFaBuPicBean;
import com.example.mynewslayoutlib.Bean.NewRenZhengSubmitResultBean;
import com.shandian.lu.BaseActivity;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.NetWork.NewFaBuNetWork;
import com.shandian.lu.NetWork.RenZhengNetWork;
import com.shandian.lu.R;
import com.yanzhenjie.album.Album;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.BitmapUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ImageView.RoundCornerImageView.RoundCornerImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/6/19.
 */

public class NewWoDeRenZhengActivity extends BaseActivity {
    private final int SMRZ_PHOTO1 = 100;
    private final int SMRZ_PHOTO2 = 101;
    private final int SMRZ_PHOTO3 = 102;
    private final int QYRZ_PHOTO = 103;
    private final int JSZ_PHOTO = 104;
    private final int XSZ_PHOTO = 105;
    private int upPicCount = 0;
    @BindView(R.id.rciv_new_rz_sm_p1)
    RoundCornerImageView rcivNewRZSMP1;
    @OnClick(R.id.rciv_new_rz_sm_p1)
    public void rcivNewRZSMP1Onclick(){
        selectPhoto(SMRZ_PHOTO1,mImageList_SMRZ_PHOTO1);
    }
    @BindView(R.id.pb_new_rz_sm_p1)
    ProgressBar pbNewRZSMP1;
    @BindView(R.id.rciv_new_rz_sm_p2)
    RoundCornerImageView rcivNewRZSMP2;
    @OnClick(R.id.rciv_new_rz_sm_p2)
    public void rcivNewRZSMP2Onclick(){
        selectPhoto(SMRZ_PHOTO2,mImageList_SMRZ_PHOTO2);
    }
    @BindView(R.id.pb_new_rz_sm_p2)
    ProgressBar pbNewRZSMP2;
    @BindView(R.id.rciv_new_rz_sm_p3)
    RoundCornerImageView rcivNewRZSMP3;
    @OnClick(R.id.rciv_new_rz_sm_p3)
    public void rcivNewRZSMP3Onclick(){
        selectPhoto(SMRZ_PHOTO3,mImageList_SMRZ_PHOTO3);
    }
    @BindView(R.id.pb_new_rz_sm_p3)
    ProgressBar pbNewRZSMP3;

    @BindView(R.id.rciv_new_rz_qy_p2)
    RoundCornerImageView rcivNewRZQYP2;
    @OnClick(R.id.rciv_new_rz_qy_p2)
    public void rcivNewRZQYP2Onclick(){
        selectPhoto(QYRZ_PHOTO,mImageList_QYRZ_PHOTO);
    }
    @BindView(R.id.pb_new_rz_qy_p2)
    ProgressBar pbNewRZQYP2;


    @BindView(R.id.rciv_new_rz_jsz)
    RoundCornerImageView rcivNewRZJSZ;
    @OnClick(R.id.rciv_new_rz_jsz)
    public void rcivNewRZJSZOnclick(){
        selectPhoto(JSZ_PHOTO,mImageList_JSZ_PHOTO);
    }
    @BindView(R.id.pb_new_rz_jsz)
    ProgressBar pbNewRZJSZ;
    @BindView(R.id.rciv_new_rz_xsz)
    RoundCornerImageView rcivNewRZXSZ;
    @OnClick(R.id.rciv_new_rz_xsz)
    public void rcivNewRZXSZOnclick(){
        selectPhoto(XSZ_PHOTO,mImageList_XSZ_PHOTO);
    }
    @BindView(R.id.pb_new_rz_xsz)
    ProgressBar pbNewRZXSZ;
    @BindView(R.id.pb_new_rz)
    ProgressBar pbNewRZ;
    @BindView(R.id.bt_new_rz_submit)
    Button btNewRZSubmit;
    @OnClick(R.id.bt_new_rz_submit)
    public void btNewRZSubmitOnclick(){
        if(upPicCount == 0) {
            submitDetailToNet();
        }else {
            Toast.makeText(NewWoDeRenZhengActivity.this,"请等待照片上传完毕",Toast.LENGTH_LONG).show();
        }
    }

    private NewWoDeRenZhengController newWoDeRenZhengController;

    private ArrayList<String> mImageList_SMRZ_PHOTO1 ;
    private ArrayList<String> mImageList_SMRZ_PHOTO2 ;
    private ArrayList<String> mImageList_SMRZ_PHOTO3 ;
    private ArrayList<String> mImageList_QYRZ_PHOTO ;
    private ArrayList<String> mImageList_JSZ_PHOTO ;
    private ArrayList<String> mImageList_XSZ_PHOTO ;
    private List<String> imgList_SMRZ_PHOTO1 ;
    private List<String> imgList_SMRZ_PHOTO2 ;
    private List<String> imgList_SMRZ_PHOTO3 ;
    private List<String> imgList_QYRZ_PHOTO ;
    private List<String> imgList_JSZ_PHOTO ;
    private List<String> imgList_XSZ_PHOTO ;
    private boolean isFinished = true;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_woderenzheng_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        mImageList_SMRZ_PHOTO1 = new ArrayList<>();
        mImageList_SMRZ_PHOTO2 = new ArrayList<>();
        mImageList_SMRZ_PHOTO3 = new ArrayList<>();
        mImageList_QYRZ_PHOTO = new ArrayList<>();
        mImageList_JSZ_PHOTO = new ArrayList<>();
        mImageList_XSZ_PHOTO = new ArrayList<>();
        imgList_SMRZ_PHOTO1 = new ArrayList<>();
        imgList_SMRZ_PHOTO2 = new ArrayList<>();
        imgList_SMRZ_PHOTO3 = new ArrayList<>();
        imgList_QYRZ_PHOTO = new ArrayList<>();
        imgList_JSZ_PHOTO = new ArrayList<>();
        imgList_XSZ_PHOTO = new ArrayList<>();
        initController();
    }

    private void initController(){
        newWoDeRenZhengController = new NewWoDeRenZhengController(this,imgList_SMRZ_PHOTO1,imgList_SMRZ_PHOTO2,imgList_SMRZ_PHOTO3,imgList_QYRZ_PHOTO,imgList_JSZ_PHOTO,imgList_XSZ_PHOTO);
    /*    initImgsLists();*/
    }
    private void initImgsLists(){
        if(newWoDeRenZhengController.imgList_SMRZ_PHOTO1.size() != 0){
            imgList_SMRZ_PHOTO1.clear();
            imgList_SMRZ_PHOTO1.addAll(newWoDeRenZhengController.imgList_SMRZ_PHOTO1);
        }
        if(newWoDeRenZhengController.imgList_SMRZ_PHOTO2.size() != 0){
            imgList_SMRZ_PHOTO2.clear();
            imgList_SMRZ_PHOTO2.addAll(newWoDeRenZhengController.imgList_SMRZ_PHOTO2);
        }
        if(newWoDeRenZhengController.imgList_SMRZ_PHOTO3.size() != 0){
            imgList_SMRZ_PHOTO3.clear();
            imgList_SMRZ_PHOTO3.addAll(newWoDeRenZhengController.imgList_SMRZ_PHOTO3);
        }
        if(newWoDeRenZhengController.imgList_QYRZ_PHOTO.size() != 0){
            imgList_QYRZ_PHOTO.clear();
            imgList_QYRZ_PHOTO.addAll(newWoDeRenZhengController.imgList_QYRZ_PHOTO);
        }
        if(newWoDeRenZhengController.imgList_JSZ_PHOTO.size() != 0){
            imgList_JSZ_PHOTO.clear();
            imgList_JSZ_PHOTO.addAll(newWoDeRenZhengController.imgList_JSZ_PHOTO);
        }
        if(newWoDeRenZhengController.imgList_XSZ_PHOTO.size() != 0){
            imgList_XSZ_PHOTO.clear();
            imgList_XSZ_PHOTO.addAll(newWoDeRenZhengController.imgList_XSZ_PHOTO);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }

        switch (requestCode) {
            case SMRZ_PHOTO1: {
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList_SMRZ_PHOTO1.clear();
                    mImageList_SMRZ_PHOTO1 = Album.parseResult(data); // Parse select result.
                   /* Toast.makeText(this,"ACTIVITY_REQUEST_SELECT_PHOTO:"+mImageList.get(0),Toast.LENGTH_LONG).show();*/
                    upPicToNet(mImageList_SMRZ_PHOTO1,imgList_SMRZ_PHOTO1,pbNewRZSMP1,rcivNewRZSMP1);

                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                   /* Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();*/
                   /* mImageList.clear();*/

                }
                break;
            }
            case SMRZ_PHOTO2: {
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList_SMRZ_PHOTO2.clear();
                    mImageList_SMRZ_PHOTO2 = Album.parseResult(data); // Parse select result.
                   /* Toast.makeText(this,"ACTIVITY_REQUEST_SELECT_PHOTO:"+mImageList.get(0),Toast.LENGTH_LONG).show();*/
                    upPicToNet(mImageList_SMRZ_PHOTO2,imgList_SMRZ_PHOTO2,pbNewRZSMP2,rcivNewRZSMP2);

                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                   /* Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();*/
                   /* mImageList.clear();*/

                }
                break;
            }
            case SMRZ_PHOTO3: {
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList_SMRZ_PHOTO3.clear();
                    mImageList_SMRZ_PHOTO3 = Album.parseResult(data); // Parse select result.
                   /* Toast.makeText(this,"ACTIVITY_REQUEST_SELECT_PHOTO:"+mImageList.get(0),Toast.LENGTH_LONG).show();*/
                    upPicToNet(mImageList_SMRZ_PHOTO3,imgList_SMRZ_PHOTO3,pbNewRZSMP3,rcivNewRZSMP3);

                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                   /* Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();*/
                   /* mImageList.clear();*/

                }
                break;
            }
            case QYRZ_PHOTO: {
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList_QYRZ_PHOTO.clear();
                    mImageList_QYRZ_PHOTO = Album.parseResult(data); // Parse select result.
                   /* Toast.makeText(this,"ACTIVITY_REQUEST_SELECT_PHOTO:"+mImageList.get(0),Toast.LENGTH_LONG).show();*/
                    upPicToNet(mImageList_QYRZ_PHOTO,imgList_QYRZ_PHOTO,pbNewRZQYP2,rcivNewRZQYP2);

                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                   /* Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();*/
                   /* mImageList.clear();*/

                }
                break;
            }
            case JSZ_PHOTO: {
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList_JSZ_PHOTO.clear();
                    mImageList_JSZ_PHOTO = Album.parseResult(data); // Parse select result.
                   /* Toast.makeText(this,"ACTIVITY_REQUEST_SELECT_PHOTO:"+mImageList.get(0),Toast.LENGTH_LONG).show();*/
                    upPicToNet(mImageList_JSZ_PHOTO,imgList_JSZ_PHOTO,pbNewRZJSZ,rcivNewRZJSZ);

                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                   /* Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();*/
                   /* mImageList.clear();*/

                }
                break;
            }
            case XSZ_PHOTO: {
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList_XSZ_PHOTO.clear();
                    mImageList_XSZ_PHOTO = Album.parseResult(data); // Parse select result.
                   /* Toast.makeText(this,"ACTIVITY_REQUEST_SELECT_PHOTO:"+mImageList.get(0),Toast.LENGTH_LONG).show();*/
                    upPicToNet(mImageList_XSZ_PHOTO,imgList_XSZ_PHOTO,pbNewRZXSZ,rcivNewRZXSZ);

                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                   /* Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();*/
                   /* mImageList.clear();*/

                }
                break;
            }
        }
    }


    private void selectPhoto(int type,ArrayList<String> mImageList1){
        Album.album(this)
                .requestCode(type)
                .toolBarColor(ContextCompat.getColor(this, R.color.color_main_index_topbar_blue_bg)) // Toolbar color.
                .statusBarColor(ContextCompat.getColor(this, R.color.color_main_index_topbar_blue_bg)) // StatusBar color.
                .navigationBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryBlack)) // NavigationBar color.
                .selectCount(1) // select count.
                .columnCount(2) // span count.
                .camera(true) // has fromCamera function.
                .checkedList(mImageList1) // The picture has been selected for anti-election.
                .start();
    }

    private Map<String,String> getUpPicParamMap( Bitmap bm){
       Map<String,String> paramMap = new HashMap<>();
      /*   XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(this);
        String loginId= xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            Toast.makeText(this,"请登录",3000).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return paramMap;
        }*/

        paramMap.put("login_id",newWoDeRenZhengController.loginId);

        BitmapUtils bitmapUtils = new BitmapUtils();

        //将图片显示到ImageView中
        String base64_00 = bitmapUtils.bitmapConvertBase64(bm);
        paramMap.put("tu",base64_00);

        return paramMap;

    }

    //图片压缩
    private Bitmap compressImageFromFile(String srcPath) {

        BitmapUtils bitmapUtils = new BitmapUtils();
        Bitmap bitmap = bitmapUtils.getimage(srcPath);
        bitmap = bitmapUtils.compressImage(bitmap);
        bitmap = bitmapUtils.comp(bitmap);

        return bitmap;
    }

    private void upPicToNet(ArrayList<String> imgListParam,final List<String> resultImgList,final ProgressBar progressBar,final RoundCornerImageView rcivImageView){
        NewFaBuNetWork newFaBuNetWork = new NewFaBuNetWork();
        progressBar.setVisibility(View.VISIBLE);
        upPicCount++;

        final Bitmap bitmap = compressImageFromFile(imgListParam.get(0));
        newFaBuNetWork.upPicToNet(getUpPicParamMap(bitmap), new Observer<NewFaBuPicBean>() {
            @Override
            public void onCompleted() {
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {

                progressBar.setVisibility(View.GONE);

                 /*   Toast.makeText(activity,"Throwable:"+e,Toast.LENGTH_LONG).show();*/
            }

            @Override
            public void onNext(NewFaBuPicBean newFaBuPicBean) {
                if(newFaBuPicBean.getStatus().equals("0")){
                    resultImgList.clear();
                    /*resultImgList.add("\""+newFaBuPicBean.getImgurl()+"\"");*/
                    resultImgList.add(newFaBuPicBean.getImgurl());
                    upPicCount--;
                    rcivImageView.setImageBitmap(bitmap);
                        /*pbNewFaBuHuoYuan.setVisibility(View.GONE);*/

                }
            }
        });
    }



    private  Map<String,Object> getSubmitParam(){
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("login_id",newWoDeRenZhengController.loginId);
        paramMap.put("tj","1");
        paramMap.put("sfzzm",imgList_SMRZ_PHOTO1.get(0));
        paramMap.put("sfzfm",imgList_SMRZ_PHOTO2.get(0));
        paramMap.put("scsfz",imgList_SMRZ_PHOTO3.get(0));
        String yyzz ="";
        if(imgList_QYRZ_PHOTO.size() == 0){

        }else{
            yyzz = imgList_QYRZ_PHOTO.get(0);
        }

        paramMap.put("yyzz",yyzz);
        String jsz = "";
        if(imgList_JSZ_PHOTO.size() == 0){

        }else{
            jsz = imgList_JSZ_PHOTO.get(0);
        }
        paramMap.put("jsz",jsz);
        String xsz = "";
        if(imgList_XSZ_PHOTO.size() == 0){

        }else{
            xsz = imgList_XSZ_PHOTO.get(0);
        }
        paramMap.put("xsz",xsz);

        return  paramMap;
    }
    private void submitDetailToNet(){
 /*       Toast.makeText(this,"imgList_SMRZ_PHOTO1"+imgList_SMRZ_PHOTO1,3000).show();*/
        if(imgList_SMRZ_PHOTO1.size() == 0){
            Toast.makeText(this,"请上传身份证正面",3000).show();
            return;
        }
        if(imgList_SMRZ_PHOTO2.size() == 0){
            Toast.makeText(this,"请上传身份证反面",3000).show();
            return;
        }
        if(imgList_SMRZ_PHOTO3.size() == 0){
            Toast.makeText(this,"请上传手持身份证",3000).show();
            return;
        }


        pbNewRZ.setVisibility(View.VISIBLE);


        RenZhengNetWork renZhengNetWork = new RenZhengNetWork();
        renZhengNetWork.submitNewRenZhengToNet(getSubmitParam(), new Observer<NewRenZhengSubmitResultBean>() {
            @Override
            public void onCompleted() {
                pbNewRZ.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                pbNewRZ.setVisibility(View.GONE);
            }

            @Override
            public void onNext(NewRenZhengSubmitResultBean newRenZhengSubmitResultBean) {
                Toast.makeText(NewWoDeRenZhengActivity.this,newRenZhengSubmitResultBean.getMsg(),3000).show();
                pbNewRZ.setVisibility(View.GONE);
                newWoDeRenZhengController.initDetailAfterSubmit(newRenZhengSubmitResultBean);

            }
        });
    }

}
