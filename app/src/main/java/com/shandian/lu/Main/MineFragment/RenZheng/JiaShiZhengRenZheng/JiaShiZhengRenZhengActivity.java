package com.shandian.lu.Main.MineFragment.RenZheng.JiaShiZhengRenZheng;

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
import com.zhyan.shandiankuaiyunlib.Bean.AuthJiaShiZhengBean;

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

public class JiaShiZhengRenZhengActivity extends BaseActivity {

    final int TOUXIANG_SELECT_PHONE = 100;
    final int TOUXIANG_TAKE_CAMERA = 101;
    final int JIASHIZHENG_SELECT_PHONE = 102;
    final int JIASHIZHENG_TAKE_CAMERA = 103;

    ArrayList<String> mImageList ,mImageList1;
    @BindView(R.id.et_main_mine_renzheng_jiashizhengrenzheng_name)
    EditText etMainMineRenZhengJiaShiZHengRenZhengName;
    @BindView(R.id.et_main_mine_renzheng_jiashizhengrenzheng_idcard)
    EditText etMainMineRenZhengJiaShiZhengRenZhengIdCard;
    @BindView(R.id.iv_main_mine_renzheng_jiashizhengrenzheng_head)
    ImageView ivMainMineRenZhengJiaShiZhengRenZhengHead;
    @BindView(R.id.rly_main_mine_renzheng_jiashizhengrenzheng_head)
    RelativeLayout rlyMainMineRenZhengJiaShiZhengRenZhengHead;
    @OnClick(R.id.rly_main_mine_renzheng_jiashizhengrenzheng_head)
    public void rlyMainMineRenZhengJiaShiZhengRenZhengHeadOnclick(){
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
    @BindView(R.id.iv_main_mine_renzheng_jiashizhengrenzheng_jsz)
    ImageView ivMainMineRenZhengJiaShiZhengRenZhengJSZ;
    @BindView(R.id.rly_main_mine_renzheng_jiashizhengrenzheng_jsz)
    RelativeLayout rlyMainMineRenZhengJiaShiZhengRenZHengJSZ;
    @OnClick(R.id.rly_main_mine_renzheng_jiashizhengrenzheng_jsz)
    public void rlyMainMineRenZhengJiaShiZhengRenZHengJSZOnclick(){
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
                                fromAlbum(JIASHIZHENG_SELECT_PHONE,mImageList1);
                                break;
                            case 1:
                                fromCamera(JIASHIZHENG_TAKE_CAMERA);
                                break;
                            case -1:
                                break;
                        }
                    }
                }).build()
                .show();
    }

    @BindView(R.id.bt_main_mine_renzheng_jiashizhengrenzheng_submit)
    Button btMainMineRenZhengJiaShiZhengSubmit;
    @OnClick(R.id.bt_main_mine_renzheng_jiashizhengrenzheng_submit)
    public void btMainMineRenZhengJiaShiZhengSubmitOnclick(){
        authJiaShiZhengSubmit();
    }


    private JiaShiZhengRenZhengController jiaShiZhengRenZhengController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_renzheng_jiashizhengrenzheng_lly);
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
        jiaShiZhengRenZhengController = new JiaShiZhengRenZhengController(this);
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
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengJiaShiZhengRenZhengHead, mImageList.get(0), 0, 0);
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
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengJiaShiZhengRenZhengHead, mImageList.get(0), 0, 0);
                } else if (resultCode == RESULT_CANCELED) { // User canceled.

                }
                break;
            case JIASHIZHENG_SELECT_PHONE:
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList1.clear();
                    mImageList1 = Album.parseResult(data); // Parse select result.
                   /* Toast.makeText(this,"ACTIVITY_REQUEST_SELECT_PHOTO:"+mImageList.get(0),Toast.LENGTH_LONG).show();*/
                    DisplayUtils.initScreen(this);
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.decoration_white, null);
 /*       rvMainReleaseZhuanXiaWuLiuSelectPhoto.addItemDecoration(new AlbumVerticalGirdDecoration(drawable));*/

                    assert drawable != null;
                    int itemSize = (DisplayUtils.screenWidth - (drawable.getIntrinsicWidth() * 4)) / 3;
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengJiaShiZhengRenZhengJSZ, mImageList1.get(0), 0, 0);
                    /*previewImage(0);*/
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                   /* Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();*/
                   /* mImageList.clear();*/
                }
                break;
            case JIASHIZHENG_TAKE_CAMERA:
                if (resultCode == RESULT_OK) { // Successfully.
                    List<String> imageList = Album.parseResult(data); // Parse path.
                    mImageList1.clear();
                    mImageList1.addAll(imageList);
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengJiaShiZhengRenZhengJSZ, mImageList1.get(0), 0, 0);
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
        paramMap.put("login_id",login_id);
        String type = "1";
        paramMap.put("type",type);
        String id_number = etMainMineRenZhengJiaShiZhengRenZhengIdCard.getText().toString().trim();
        if(id_number == null){
            id_number = "";
        }else{
            id_number = id_number.replaceAll(" ","");
        }
        paramMap.put("id_number",id_number);
        String name = etMainMineRenZhengJiaShiZHengRenZhengName.getText().toString().trim();
        if(name == null){
            name = "";
        }else{
            name = name.replaceAll(" ","");
        }
        paramMap.put("name",name);

        String head = getBase64(mImageList);
        if(head == null){
            head = "";
        }else{
            head = head.replaceAll(" ","");
        }
        paramMap.put("head",head);
        String license = getBase64(mImageList1);
        if(license == null){
            license = "";
        }else{
            license = license.replaceAll(" ","");
        }
        paramMap.put("license",license);

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

    private void authJiaShiZhengSubmit(){
        RenZhengNetWork renZhengNetWork = new RenZhengNetWork();
        renZhengNetWork.authJiaShiZhengToNet(getParaMap(), new Observer<AuthJiaShiZhengBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(AuthJiaShiZhengBean authJiaShiZhengBean) {
                Toast.makeText(JiaShiZhengRenZhengActivity.this,authJiaShiZhengBean.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
