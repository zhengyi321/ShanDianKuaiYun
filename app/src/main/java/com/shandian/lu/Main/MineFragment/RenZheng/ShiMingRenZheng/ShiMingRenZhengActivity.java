package com.shandian.lu.Main.MineFragment.RenZheng.ShiMingRenZheng;

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
import com.zhyan.shandiankuaiyunlib.Bean.AuthRealNameBean;

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

public class ShiMingRenZhengActivity extends BaseActivity {
    final int TOUXIANG_SELECT_PHONE = 102;
    final int TOUXIANG_TAKE_CAMERA = 103;
    final int SHENGFENZHENG_ZHENGMIAN_SELECT_PHONE = 104;
    final int SHENGFENZHENG_ZHENGMIAN_TAKE_CAMERA = 105;
    final int SHENGFENZHENG_FANMIAN_SELECT_PHONE = 106;
    final int SHENGFENZHENG_FANMIAN_TAKE_CAMERA = 107;
    ArrayList<String> mImageList ,mImageList1,mImageList2;

    @BindView(R.id.pb_main_mine_rz_smrz)
    ProgressBar pbMainMineRZSMRZ;
    @BindView(R.id.et_main_mine_renzheng_shimingrenzheng_name)
    EditText etMainMineRenZhengShiMingRenZhengName;
    @BindView(R.id.et_main_mine_renzheng_shimingrenzheng_idcard)
    EditText etMainMineRenZhengShiMingRenZhengIdCard;
    @BindView(R.id.et_main_mine_renzheng_shimingrenzheng_addr)
    EditText etMainMineRenZhengShiMingRenZhengAddr;
    @BindView(R.id.iv_main_mine_renzheng_shimingrenzheng_brzp)
    ImageView ivMainMineRenZhengShiMingRenZHengBRZP;
    @BindView(R.id.rly_main_mine_renzheng_shimingrenzheng_brzp)
    RelativeLayout rlyMainMineRenZhengShiMingRenZHengBRZP;
    @OnClick(R.id.rly_main_mine_renzheng_shimingrenzheng_brzp)
    public void rlyMainMineRenZhengShiMingRenZHengBRZPOnclick(){
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
                                fromAlbum(TOUXIANG_SELECT_PHONE,mImageList);
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

    @BindView(R.id.iv_main_mine_renzheng_shimingrenzheng_sfzzm)
    ImageView ivMainMineRenZhengShiMingRenZHengSFZZM;
    @BindView(R.id.rly_main_mine_renzheng_shimingrenzheng_sfzzm)
    RelativeLayout rlyMainMineRenZhengShiMingRenZHengSFZZM;
    @OnClick(R.id.rly_main_mine_renzheng_shimingrenzheng_sfzzm)
    public void rlyMainMineRenZhengShiMingRenZHengSFZZMOnclick(){
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
                                fromAlbum(SHENGFENZHENG_ZHENGMIAN_SELECT_PHONE,mImageList1);
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

    @BindView(R.id.iv_main_mine_renzheng_shimingrenzheng_sfzfm)
    ImageView ivMainMineRenZhengShiMingRenZHengSFZFM;
    @BindView(R.id.rly_main_mine_renzheng_shimingrenzheng_sfzfm)
    RelativeLayout rlyMainMineRenZhengShiMingRenZHengSFZFM;
    @OnClick(R.id.rly_main_mine_renzheng_shimingrenzheng_sfzfm)
    public void rlyMainMineRenZhengShiMingRenZHengSFZFMOnclick(){
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
                                fromAlbum(SHENGFENZHENG_FANMIAN_SELECT_PHONE,mImageList2);
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
    @BindView(R.id.bt_main_mine_renzheng_shimingrenzheng_submit)
    Button btMainMineRenZhengShiMingRenZhengSubmit;
    @OnClick(R.id.bt_main_mine_renzheng_shimingrenzheng_submit)
    public void btMainMineRenZhengShiMingRenZhengSubmitOnclick(){
        authRealNameToNetSubmit();
    }


    private ShiMingRenZhengController shiMingRenZhengController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_renzheng_shimingrenzheng_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
        initImagesList();
    }


    private void initController(){
        shiMingRenZhengController = new ShiMingRenZhengController(this);
    }

    private void initImagesList(){
        mImageList = new ArrayList<>();
        mImageList1 = new ArrayList<>();
        mImageList2 = new ArrayList<>();

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

            case TOUXIANG_SELECT_PHONE:
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList.clear();
                    mImageList = Album.parseResult(data); // Parse select result.
                   /* Toast.makeText(this,"ACTIVITY_REQUEST_SELECT_PHOTO:"+mImageList.get(0),Toast.LENGTH_LONG).show();*/
                    DisplayUtils.initScreen(this);
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.decoration_white, null);
 /*       rvMainReleaseZhuanXiaWuLiuSelectPhoto.addItemDecoration(new AlbumVerticalGirdDecoration(drawable));*/

                    assert drawable != null;
                    int itemSize = (DisplayUtils.screenWidth - (drawable.getIntrinsicWidth() * 4)) / 3;
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengShiMingRenZHengBRZP, mImageList.get(0), 0, 0);
                    /*previewImage(0);*/
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                   /* Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();*/
                   /* mImageList.clear();*/
                }
                break;
            case TOUXIANG_TAKE_CAMERA:
                if (resultCode == RESULT_OK) { // Successfully.
                    List<String> imageList = Album.parseResult(data); // Parse path.
                    mImageList.clear();
                    mImageList.addAll(imageList);
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengShiMingRenZHengBRZP, mImageList.get(0), 0, 0);
                } else if (resultCode == RESULT_CANCELED) { // User canceled.

                }
                break;
            case SHENGFENZHENG_ZHENGMIAN_SELECT_PHONE:
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList1.clear();
                    mImageList1 = Album.parseResult(data); // Parse select result.
                   /* Toast.makeText(this,"ACTIVITY_REQUEST_SELECT_PHOTO:"+mImageList.get(0),Toast.LENGTH_LONG).show();*/
                    DisplayUtils.initScreen(this);
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.decoration_white, null);
 /*       rvMainReleaseZhuanXiaWuLiuSelectPhoto.addItemDecoration(new AlbumVerticalGirdDecoration(drawable));*/

                    assert drawable != null;
                    int itemSize = (DisplayUtils.screenWidth - (drawable.getIntrinsicWidth() * 4)) / 3;
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengShiMingRenZHengSFZZM, mImageList1.get(0), 0, 0);
                    /*previewImage(0);*/
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                   /* Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();*/
                   /* mImageList.clear();*/
                }
                break;
            case SHENGFENZHENG_ZHENGMIAN_TAKE_CAMERA:
                if (resultCode == RESULT_OK) { // Successfully.
                    List<String> imageList = Album.parseResult(data); // Parse path.
                    mImageList1.clear();
                    mImageList1.addAll(imageList);
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengShiMingRenZHengSFZZM, mImageList1.get(0), 0, 0);
                } else if (resultCode == RESULT_CANCELED) { // User canceled.

                }
                break;
            case SHENGFENZHENG_FANMIAN_SELECT_PHONE:
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList2.clear();
                    mImageList2 = Album.parseResult(data); // Parse select result.
                   /* Toast.makeText(this,"ACTIVITY_REQUEST_SELECT_PHOTO:"+mImageList.get(0),Toast.LENGTH_LONG).show();*/
                    DisplayUtils.initScreen(this);
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.decoration_white, null);
 /*       rvMainReleaseZhuanXiaWuLiuSelectPhoto.addItemDecoration(new AlbumVerticalGirdDecoration(drawable));*/

                    assert drawable != null;
                    int itemSize = (DisplayUtils.screenWidth - (drawable.getIntrinsicWidth() * 4)) / 3;
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengShiMingRenZHengSFZFM, mImageList2.get(0), 0, 0);
                    /*previewImage(0);*/
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                   /* Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();*/
                   /* mImageList.clear();*/
                }
                break;
            case SHENGFENZHENG_FANMIAN_TAKE_CAMERA:
                if (resultCode == RESULT_OK) { // Successfully.
                    List<String> imageList = Album.parseResult(data); // Parse path.
                    mImageList2.clear();
                    mImageList2.addAll(imageList);
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengShiMingRenZHengSFZFM, mImageList2.get(0), 0, 0);
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

        String id_number = etMainMineRenZhengShiMingRenZhengIdCard.getText().toString().trim();
        if(id_number == null){
            id_number = "";
        }else{
            id_number = id_number.replaceAll(" ","");
        }
        paramMap.put("id_number",id_number);
        String address = etMainMineRenZhengShiMingRenZhengAddr.getText().toString().trim();
        if(address == null){
            address = "";
        }else{
            address = address.replaceAll(" ","");
        }
        paramMap.put("address",address);
        String name = etMainMineRenZhengShiMingRenZhengName.getText().toString().trim();
        if(name == null){
            name = "";
        }else{
            name = name.replaceAll(" ","");
        }
        paramMap.put("name",name);

/*        String licence = getBase64(mImageList);
        if(licence == null){
            licence = "";
        }else{
            licence = licence.replaceAll(" ","");
        }
        paramMap.put("licence",licence);*/
        String phone = getBase64(mImageList);
        if(phone == null){
            phone = "";
        }else{
            phone = phone.replaceAll(" ","");
        }
        paramMap.put("phone",phone);
        String cards_just = getBase64(mImageList1);
        if(cards_just == null){
            cards_just = "";
        }else{
            cards_just = cards_just.replaceAll(" ","");
        }
        paramMap.put("cards_just",cards_just);
        String cards_back = getBase64(mImageList2);
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

    private void authRealNameToNetSubmit(){
        pbMainMineRZSMRZ.setVisibility(View.VISIBLE);
        RenZhengNetWork renZhengNetWork = new RenZhengNetWork();
        renZhengNetWork.authRealNameToNet(getParaMap(), new Observer<AuthRealNameBean>() {
            @Override
            public void onCompleted() {
                pbMainMineRZSMRZ.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                pbMainMineRZSMRZ.setVisibility(View.GONE);
            }

            @Override
            public void onNext(AuthRealNameBean authRealNameBean) {
                if(authRealNameBean.getStatus() == 0){
                    Toast.makeText(ShiMingRenZhengActivity.this,authRealNameBean.getMsg(),Toast.LENGTH_LONG).show();
                    pbMainMineRZSMRZ.setVisibility(View.GONE);
                    finish();
                }
            }
        });
    }
}
