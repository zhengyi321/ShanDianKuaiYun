package com.shandian.lu.Main.MineFragment.RenZheng.QiYeRenZheng;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.shandian.lu.Main.MineFragment.RenZheng.ShiMingRenZheng.ShiMingRenZhengActivity;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.util.DisplayUtils;
import com.zhyan.shandiankuaiyuanwidgetlib.AlertView.AlertView;
import com.zhyan.shandiankuaiyuanwidgetlib.AlertView.OnItemClickListener;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.BitmapUtils;
import com.shandian.lu.BaseActivity;
import com.shandian.lu.NetWork.RenZhengNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.AuthCompanyBean;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by az on 2017/5/3.
 */

public class QiYeRenZhengActivity extends BaseActivity {

    final int YINGYEZHIZHAO_SELECT_PHONE = 100;
    final int YINGYEZHIZHAO_TAKE_CAMERA = 101;
    final int TOUXIANG_SELECT_PHONE = 102;
    final int TOUXIANG_TAKE_CAMERA = 103;
    final int SHENGFENZHENG_ZHENGMIAN_SELECT_PHONE = 104;
    final int SHENGFENZHENG_ZHENGMIAN_TAKE_CAMERA = 105;
    final int SHENGFENZHENG_FANMIAN_SELECT_PHONE = 106;
    final int SHENGFENZHENG_FANMIAN_TAKE_CAMERA = 107;

    ArrayList<String> mImageList ,mImageList1,mImageList2,mImageList3;

    @BindView(R.id.pb_main_mine_rz_qyrz)
    ProgressBar pbMainMineRZQYRZ;
    @BindView(R.id.et_main_mine_renzheng_qiyerenzheng_addr)
    EditText etMainMineRenZhengQiYeRenZhengAddr;
    @BindView(R.id.et_main_mine_renzheng_qiyerenzheng_companyname)
    EditText etMainMineRenZhengQiYeRenZhengCompanyName;
    @BindView(R.id.et_main_mine_renzheng_qiyerenzheng_lxr)
    EditText etMainMineRenZhengQiYeRenZhengLXR;

    @BindView(R.id.iv_main_mine_renzheng_qiyerenzheng_yezz)
    ImageView ivMainMineRenZhengQiYeRenZhengYEZZ;
    @BindView(R.id.rly_main_mine_renzheng_qiyerenzheng_yezz)
    RelativeLayout rlyMainMineRenZhengQiYeRenZhengYEZZ;
    @OnClick(R.id.rly_main_mine_renzheng_qiyerenzheng_yezz)
    public void rlyMainMineRenZhengQiYeRenZhengYEZZOnclick(){
        //从相册选择头像代码
        new AlertView.Builder().setContext(this)
                .setStyle(AlertView.Style.ActionSheet)
                .setTitle("选择驾驶证")
                .setMessage(null)
                .setCancelText("取消")
                .setDestructive("从相册获取图片")
                .setDestructive1("打开相机照相")
                .setOthers(null)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        /*Toast.makeText(getBaseContext(),"pos"+position,Toast.LENGTH_SHORT).show();*/
                        switch (position){
                            case 0:
                                fromAlbum(YINGYEZHIZHAO_SELECT_PHONE,mImageList);
                                break;
                            case 1:
                                fromCamera(YINGYEZHIZHAO_TAKE_CAMERA);
                                break;
                            case -1:
                                break;
                        }
                    }
                }).build()
                .show();
    }
    @BindView(R.id.iv_main_mine_renzheng_qiyerenzheng_brzp)
    ImageView ivMainMineRenZhengQiYeRenZhengBRZP;
    @BindView(R.id.rly_main_mine_renzheng_qiyerenzheng_brzp)
    RelativeLayout rlyMainMineRenZhengQiYeRenZhengBRZP;
    @OnClick(R.id.rly_main_mine_renzheng_qiyerenzheng_brzp)
    public void ivMainMineRenZhengQiYeRenZhengBRZPOnclick(){
        //从相册选择头像代码
        new AlertView.Builder().setContext(this)
                .setStyle(AlertView.Style.ActionSheet)
                .setTitle("选择本人照片")
                .setMessage(null)
                .setCancelText("取消")
                .setDestructive("从相册获取图片")
                .setDestructive1("打开相机照相")
                .setOthers(null)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        /*Toast.makeText(getBaseContext(),"pos"+position,Toast.LENGTH_SHORT).show();*/
                        switch (position){
                            case 0:
                                fromAlbum(TOUXIANG_SELECT_PHONE,mImageList1);
                                break;
                            case 1:
                                fromCamera(TOUXIANG_TAKE_CAMERA);
                                break;
                            case -1:
                                break;
                        }
                    }
                }).build()
                .show();
    }

    @BindView(R.id.iv_main_mine_renzheng_qiyerenzheng_sfzzm)
    ImageView ivMainMineRenZhengQiYeRenZhengSFZZM;
    @BindView(R.id.rly_main_mine_renzheng_qiyerenzheng_sfzzm)
    RelativeLayout rlyMainMineRenZhengQiYeRenZhengSFZZM;
    @OnClick(R.id.rly_main_mine_renzheng_qiyerenzheng_sfzzm)
    public void rlyMainMineRenZhengQiYeRenZhengSFZZMOnclick(){
        //从相册选择头像代码
        new AlertView.Builder().setContext(this)
                .setStyle(AlertView.Style.ActionSheet)
                .setTitle("选择身份证正面")
                .setMessage(null)
                .setCancelText("取消")
                .setDestructive("从相册获取图片")
                .setDestructive1("打开相机照相")
                .setOthers(null)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        /*Toast.makeText(getBaseContext(),"pos"+position,Toast.LENGTH_SHORT).show();*/
                        switch (position){
                            case 0:
                                fromAlbum(SHENGFENZHENG_ZHENGMIAN_SELECT_PHONE,mImageList2);
                                break;
                            case 1:
                                fromCamera(SHENGFENZHENG_ZHENGMIAN_TAKE_CAMERA);
                                break;
                            case -1:
                                break;
                        }
                    }
                }).build()
                .show();
    }

    @BindView(R.id.iv_main_mine_renzheng_qiyerenzheng_sfzbm)
    ImageView ivMainMineRenZhengQiYeRenZhengSFZBM;
    @BindView(R.id.rly_main_mine_renzheng_qiyerenzheng_sfzbm)
    RelativeLayout rlyMainMineRenZhengQiYeRenZhengSFZBM;
    @OnClick(R.id.rly_main_mine_renzheng_qiyerenzheng_sfzbm)
    public void rlyMainMineRenZhengQiYeRenZhengSFZBMOnclick(){
        //从相册选择头像代码
        new AlertView.Builder().setContext(this)
                .setStyle(AlertView.Style.ActionSheet)
                .setTitle("选择身份证背面")
                .setMessage(null)
                .setCancelText("取消")
                .setDestructive("从相册获取图片")
                .setDestructive1("打开相机照相")
                .setOthers(null)
                .setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(Object o, int position) {
                        /*Toast.makeText(getBaseContext(),"pos"+position,Toast.LENGTH_SHORT).show();*/
                        switch (position){
                            case 0:
                                fromAlbum(SHENGFENZHENG_FANMIAN_SELECT_PHONE,mImageList3);
                                break;
                            case 1:
                                fromCamera(SHENGFENZHENG_FANMIAN_TAKE_CAMERA);
                                break;
                            case -1:
                                break;
                        }
                    }
                }).build()
                .show();
    }


    @BindView(R.id.bt_main_mine_renzheng_qiyerenzheng_submit)
    Button btMainMineRenZhengQiYeRenZhengSubmit;
    @OnClick(R.id.bt_main_mine_renzheng_qiyerenzheng_submit)
    public void btMainMineRenZhengQiYeRenZhengSubmitOnclick(){
        authQiYeRenZhengSubmit();
    }
    private QiYeRenZhengController qiYeRenZhengController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_renzheng_qiyerenzheng_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
        initImagesList();
    }

    private void initController(){
        qiYeRenZhengController = new QiYeRenZhengController(this);
    }

    private void initImagesList(){
        mImageList = new ArrayList<>();
        mImageList1 = new ArrayList<>();
        mImageList2 = new ArrayList<>();
        mImageList3 = new ArrayList<>();
    }




    /**
     * Select image from fromAlbum.
     */
    private void fromAlbum(int RequestCode,ArrayList imgList) {

        Album.album(this)
                .requestCode(RequestCode)
                .toolBarColor(ContextCompat.getColor(this, R.color.colorPrimary)) // Toolbar color.
                .statusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)) // StatusBar color.
                .navigationBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryBlack)) // NavigationBar color.
                .selectCount(1) // select count.
                .columnCount(2) // span count.
                .camera(true) // has fromCamera function.
                .checkedList(imgList) // The picture has been selected for anti-election.
                .start();
    }
    /**
     * Take a picture from fromCamera.
     */
    private void fromCamera(int RequestCode) {
        Album.camera(this)
                .requestCode(RequestCode)
//                .imagePath() // Specify the image path, optional.
                .start();
    }




    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case YINGYEZHIZHAO_SELECT_PHONE:
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList.clear();
                    mImageList = Album.parseResult(data); // Parse select result.
                   /* Toast.makeText(this,"ACTIVITY_REQUEST_SELECT_PHOTO:"+mImageList.get(0),Toast.LENGTH_LONG).show();*/
                    DisplayUtils.initScreen(this);
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.decoration_white, null);
 /*       rvMainReleaseZhuanXiaWuLiuSelectPhoto.addItemDecoration(new AlbumVerticalGirdDecoration(drawable));*/

                    assert drawable != null;
                    int itemSize = (DisplayUtils.screenWidth - (drawable.getIntrinsicWidth() * 4)) / 3;
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengQiYeRenZhengYEZZ, mImageList.get(0), 0, 0);
                    /*previewImage(0);*/
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                   /* Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();*/
                   /* mImageList.clear();*/
                }
                break;
            case YINGYEZHIZHAO_TAKE_CAMERA:
                if (resultCode == RESULT_OK) { // Successfully.
                    List<String> imageList = Album.parseResult(data); // Parse path.
                    mImageList.clear();
                    mImageList.addAll(imageList);
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengQiYeRenZhengYEZZ, mImageList.get(0), 0, 0);
                } else if (resultCode == RESULT_CANCELED) { // User canceled.

                }
                break;
            case TOUXIANG_SELECT_PHONE:
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList1.clear();
                    mImageList1 = Album.parseResult(data); // Parse select result.
                   /* Toast.makeText(this,"ACTIVITY_REQUEST_SELECT_PHOTO:"+mImageList.get(0),Toast.LENGTH_LONG).show();*/
                    DisplayUtils.initScreen(this);
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.decoration_white, null);
 /*       rvMainReleaseZhuanXiaWuLiuSelectPhoto.addItemDecoration(new AlbumVerticalGirdDecoration(drawable));*/

                    assert drawable != null;
                    int itemSize = (DisplayUtils.screenWidth - (drawable.getIntrinsicWidth() * 4)) / 3;
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengQiYeRenZhengBRZP, mImageList1.get(0), 0, 0);
                    /*previewImage(0);*/
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                   /* Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();*/
                   /* mImageList.clear();*/
                }
                break;
            case TOUXIANG_TAKE_CAMERA:
                if (resultCode == RESULT_OK) { // Successfully.
                    List<String> imageList = Album.parseResult(data); // Parse path.
                    mImageList1.clear();
                    mImageList1.addAll(imageList);
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengQiYeRenZhengBRZP, mImageList1.get(0), 0, 0);
                } else if (resultCode == RESULT_CANCELED) { // User canceled.

                }
                break;
            case SHENGFENZHENG_ZHENGMIAN_SELECT_PHONE:
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList2.clear();
                    mImageList2 = Album.parseResult(data); // Parse select result.
                   /* Toast.makeText(this,"ACTIVITY_REQUEST_SELECT_PHOTO:"+mImageList.get(0),Toast.LENGTH_LONG).show();*/
                    DisplayUtils.initScreen(this);
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.decoration_white, null);
 /*       rvMainReleaseZhuanXiaWuLiuSelectPhoto.addItemDecoration(new AlbumVerticalGirdDecoration(drawable));*/

                    assert drawable != null;
                    int itemSize = (DisplayUtils.screenWidth - (drawable.getIntrinsicWidth() * 4)) / 3;
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengQiYeRenZhengSFZZM, mImageList2.get(0), 0, 0);
                    /*previewImage(0);*/
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                   /* Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();*/
                   /* mImageList.clear();*/
                }
                break;
            case SHENGFENZHENG_ZHENGMIAN_TAKE_CAMERA:
                if (resultCode == RESULT_OK) { // Successfully.
                    List<String> imageList = Album.parseResult(data); // Parse path.
                    mImageList2.clear();
                    mImageList2.addAll(imageList);
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengQiYeRenZhengSFZZM, mImageList2.get(0), 0, 0);
                } else if (resultCode == RESULT_CANCELED) { // User canceled.

                }
                break;
            case SHENGFENZHENG_FANMIAN_SELECT_PHONE:
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList3.clear();
                    mImageList3 = Album.parseResult(data); // Parse select result.
                   /* Toast.makeText(this,"ACTIVITY_REQUEST_SELECT_PHOTO:"+mImageList.get(0),Toast.LENGTH_LONG).show();*/
                    DisplayUtils.initScreen(this);
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.decoration_white, null);
 /*       rvMainReleaseZhuanXiaWuLiuSelectPhoto.addItemDecoration(new AlbumVerticalGirdDecoration(drawable));*/

                    assert drawable != null;
                    int itemSize = (DisplayUtils.screenWidth - (drawable.getIntrinsicWidth() * 4)) / 3;
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengQiYeRenZhengSFZBM, mImageList3.get(0), 0, 0);
                    /*previewImage(0);*/
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                   /* Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();*/
                   /* mImageList.clear();*/
                }
                break;
            case SHENGFENZHENG_FANMIAN_TAKE_CAMERA:
                if (resultCode == RESULT_OK) { // Successfully.
                    List<String> imageList = Album.parseResult(data); // Parse path.
                    mImageList3.clear();
                    mImageList3.addAll(imageList);
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengQiYeRenZhengBRZP, mImageList3.get(0), 0, 0);
                } else if (resultCode == RESULT_CANCELED) { // User canceled.

                }
                break;
        }

    }





    private Map<String,String> getParaMap(){
        Map<String,String> paramMap = new HashMap<>();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(this);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if(login_id == null){
            login_id = "";
        }else{
            login_id = login_id.replaceAll(" ","");
        }
        paramMap.put("id",login_id);
        String linkman = etMainMineRenZhengQiYeRenZhengLXR.getText().toString().trim();
        if(linkman == null){
            linkman = "";
        }else{
            linkman = linkman.replaceAll(" ","");
        }
        paramMap.put("linkman",linkman);
        String address = etMainMineRenZhengQiYeRenZhengAddr.getText().toString().trim();
        if(address == null){
            address = "";
        }else{
            address = address.replaceAll(" ","");
        }
        paramMap.put("address",address);
        String company_name = etMainMineRenZhengQiYeRenZhengCompanyName.getText().toString().trim();
        if(company_name == null){
            company_name = "";
        }else{
            company_name = company_name.replaceAll(" ","");
        }
        paramMap.put("company_name",company_name);

        String licence = getBase64(mImageList);
        if(licence == null){
            licence = "";
        }else{
            licence = licence.replaceAll(" ","");
        }
        paramMap.put("licence",licence);
        String phone = getBase64(mImageList1);
        if(phone == null){
            phone = "";
        }else{
            phone = phone.replaceAll(" ","");
        }
        paramMap.put("phone",phone);
        String cards_just = getBase64(mImageList2);
        if(cards_just == null){
            cards_just = "";
        }else{
            cards_just = cards_just.replaceAll(" ","");
        }
        paramMap.put("cards_just",cards_just);
        String cards_back = getBase64(mImageList3);
        if(cards_back == null){
            cards_back = "";
        }else{
            cards_back = cards_back.replaceAll(" ","");
        }
        paramMap.put("cards_back",cards_back);

        return paramMap;
    }


    private String getBase64(ArrayList<String> mImageList1){
        File file;
        BitmapUtils bitmapUtils = new BitmapUtils();
        String base64_00 = "";
        file = new File(mImageList1.get(0));
        if (file.exists()) {
            Bitmap bm = compressImageFromFile(mImageList1.get(0));
            //将图片显示到ImageView中
            /*ibMainReleaseHuiTouChePicPick.setImageBitmap(bm);*/

            base64_00 = bitmapUtils.bitmapConvertBase64(bm);

        }
        return base64_00;
    }

    //图片压缩
    private Bitmap compressImageFromFile(String srcPath) {

        BitmapUtils bitmapUtils = new BitmapUtils();
        Bitmap bitmap = bitmapUtils.getimage(srcPath);
        bitmap = bitmapUtils.compressImage(bitmap);
        bitmap = bitmapUtils.comp(bitmap);

        return bitmap;
    }


    private void authQiYeRenZhengSubmit(){
        pbMainMineRZQYRZ.setVisibility(View.VISIBLE);
        RenZhengNetWork renZhengNetWork = new RenZhengNetWork();
        renZhengNetWork.authCompanyToNet(getParaMap(), new Observer<AuthCompanyBean>() {
            @Override
            public void onCompleted() {
                pbMainMineRZQYRZ.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                pbMainMineRZQYRZ.setVisibility(View.GONE);
            }

            @Override
            public void onNext(AuthCompanyBean authCompanyBean) {
                if(authCompanyBean.getStatus() == 0){
                    Toast.makeText(QiYeRenZhengActivity.this,authCompanyBean.getMsg(),Toast.LENGTH_LONG).show();
                    pbMainMineRZQYRZ.setVisibility(View.GONE);
                    finish();
                }


            }
        });
    }
}
