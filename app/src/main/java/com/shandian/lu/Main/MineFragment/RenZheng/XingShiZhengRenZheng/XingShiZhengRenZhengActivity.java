package com.shandian.lu.Main.MineFragment.RenZheng.XingShiZhengRenZheng;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.zhyan.shandiankuaiyunlib.Bean.AuthXingShiZhengBean;

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

public class XingShiZhengRenZhengActivity extends BaseActivity {

    final int TOUXIANG_SELECT_PHONE = 100;
    final int TOUXIANG_TAKE_CAMERA = 101;
    final int XINGSHIZHENG_SELECT_PHONE = 102;
    final int XINGSHIZHENG_TAKE_CAMERA = 103;

    ArrayList<String> mImageList ,mImageList1;
    @BindView(R.id.et_main_mine_renzheng_xingshizhengrenzheng_name)
    EditText etMainMineRenZhengXingShiZhengRenZhengName;
    @BindView(R.id.et_main_mine_renzheng_xingshizhengrenzheng_idcard)
    EditText etmainMineRenZhengXingShiZhengRenZhengIdCard;
    @BindView(R.id.iv_main_mine_renzheng_xingshizhengrenzheng_head)
    ImageView ivMainMineRenZhengXingShiZhengRenZhengHead;
    @BindView(R.id.rly_main_mine_renzheng_xingshizhengrenzheng_head)
    RelativeLayout rlyMainMineRenZhengXingShiZhengRenZhengHead;
    @OnClick(R.id.rly_main_mine_renzheng_xingshizhengrenzheng_head)
    public  void rlyMainMineRenZhengXingShiZhengRenZhengHead(){
        //从相册选择头像代码
        new AlertView.Builder().setContext(this)
                .setStyle(AlertView.Style.ActionSheet)
                .setTitle("选择头像")
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
    @BindView(R.id.iv_main_mine_renzheng_xingshizhengrenzheng_xingshizheng)
    ImageView ivMainMineRenZhengXingShiZhengRenZhengXingShiZheng;
    @BindView(R.id.rly_main_mine_renzheng_xingshizhengrenzheng_xingshizheng)
    RelativeLayout rlyMainMineRenZhengXingShiZhengRenZhengXingShiZheng;
    @OnClick(R.id.rly_main_mine_renzheng_xingshizhengrenzheng_xingshizheng)
    public void rlyMainMineRenZhengXingShiZhengRenZhengXingShiZhengOnclick(){
        //从相册选择头像代码
        new AlertView.Builder().setContext(this)
                .setStyle(AlertView.Style.ActionSheet)
                .setTitle("选择行驶证")
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
                                fromAlbum(XINGSHIZHENG_SELECT_PHONE,mImageList1);
                                break;
                            case 1:
                                fromCamera(XINGSHIZHENG_TAKE_CAMERA);
                                break;
                            case -1:
                                break;
                        }
                    }
                }).build()
                .show();
    }

    @BindView(R.id.bt_main_mine_renzheng_xingshizhengrenzheng_submit)
    Button btMainMineRenZhengXingShiZHengRenZhengSubmit;
    @OnClick(R.id.bt_main_mine_renzheng_xingshizhengrenzheng_submit)
    public void btMainMineRenZhengXingShiZHengRenZhengSubmitOnclick(){
        authXingShiZhengToNet();
    }
    private XingShiZhengRenZhengController xingShiZhengRenZhengController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_renzheng_xingshizhengrenzheng_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
        initImagesList();
    }
    private void initImagesList(){
        mImageList = new ArrayList<>();
        mImageList1 = new ArrayList<>();
    }

    private void initController(){
        xingShiZhengRenZhengController = new XingShiZhengRenZhengController(this);
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
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengXingShiZhengRenZhengHead, mImageList.get(0), 0, 0);
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
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengXingShiZhengRenZhengHead, mImageList.get(0), 0, 0);
                } else if (resultCode == RESULT_CANCELED) { // User canceled.

                }
                break;
            case XINGSHIZHENG_SELECT_PHONE:
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList1.clear();
                    mImageList1 = Album.parseResult(data); // Parse select result.
                   /* Toast.makeText(this,"ACTIVITY_REQUEST_SELECT_PHOTO:"+mImageList.get(0),Toast.LENGTH_LONG).show();*/
                    DisplayUtils.initScreen(this);
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.decoration_white, null);
 /*       rvMainReleaseZhuanXiaWuLiuSelectPhoto.addItemDecoration(new AlbumVerticalGirdDecoration(drawable));*/

                    assert drawable != null;
                    int itemSize = (DisplayUtils.screenWidth - (drawable.getIntrinsicWidth() * 4)) / 3;
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengXingShiZhengRenZhengXingShiZheng, mImageList1.get(0), 0, 0);
                    /*previewImage(0);*/
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                   /* Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();*/
                   /* mImageList.clear();*/
                }
                break;
            case XINGSHIZHENG_TAKE_CAMERA:
                if (resultCode == RESULT_OK) { // Successfully.
                    List<String> imageList = Album.parseResult(data); // Parse path.
                    mImageList1.clear();
                    mImageList1.addAll(imageList);
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengXingShiZhengRenZhengXingShiZheng, mImageList1.get(0), 0, 0);
                } else if (resultCode == RESULT_CANCELED) { // User canceled.

                }
                break;
        }

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



    private Map<String,String> getParaMap(){
        Map<String,String> paramMap = new HashMap<>();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(this);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String name = etMainMineRenZhengXingShiZhengRenZhengName.getText().toString().trim();
        if(name == null){
            name = "";
        }else{
            name = name.replaceAll(" ","");
        }
        paramMap.put("name",name);

        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if(login_id == null){
            login_id = "";
        }else{
            login_id = login_id.replaceAll(" ","");
        }
        paramMap.put("login_id",login_id);
        String license = getBase64(mImageList1);
        if(license == null){
            license = "";
        }else{
            license = license.replaceAll(" ","");
        }
        paramMap.put("license",license);
        String id_number = etmainMineRenZhengXingShiZhengRenZhengIdCard.getText().toString().trim();
        if(id_number == null){
            id_number = "";
        }else{
            id_number = id_number.replaceAll(" ","");
        }
        paramMap.put("id_number",id_number);

        String type = "2";
        paramMap.put("type",type);



        String head = getBase64(mImageList);
        if(head == null){
            head = "";
        }else{
            head = head.replaceAll(" ","");
        }
        paramMap.put("head",head);


        return paramMap;
    }

    private void authXingShiZhengToNet(){
        RenZhengNetWork renZhengNetWork = new RenZhengNetWork();
        renZhengNetWork.authXingShiZhengToNet(getParaMap(), new Observer<AuthXingShiZhengBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(AuthXingShiZhengBean authXingShiZhengBean) {
                if(authXingShiZhengBean.getStatus() == 0){
                    Toast.makeText(XingShiZhengRenZhengActivity.this,authXingShiZhengBean.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
