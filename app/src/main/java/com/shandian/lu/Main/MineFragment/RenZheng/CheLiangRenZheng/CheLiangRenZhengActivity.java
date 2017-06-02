package com.shandian.lu.Main.MineFragment.RenZheng.CheLiangRenZheng;

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
import android.widget.TextView;
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
import com.zhyan.shandiankuaiyunlib.Bean.AuthCarBean;

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

public class CheLiangRenZhengActivity extends BaseActivity{

    private ArrayList<String> mImageList;
    private  final int ACTIVITY_REQUEST_SELECT_PHOTO = 100;
    private  final int ACTIVITY_REQUEST_TAKE_PICTURE = 101;
    private  final int ACTIVITY_REQUEST_PREVIEW_PHOTO = 102;

    @BindView(R.id.et_main_mine_renzheng_cheliangrenzheng_name)
    EditText etMainMineRenZhengCheLiangRenZhengName;
    @BindView(R.id.et_main_mine_renzheng_cheliangrenzheng_shenfenzheng)
    EditText etMainMineRenZhengCheLiangRenZhengShenFenZheng;




    @BindView(R.id.et_main_mine_renzheng_cheliangrenzheng_cph)
    EditText etMainMineRenZhengCheLiangRenZhengCPH;
    @BindView(R.id.tv_main_mine_renzheng_cheliangrenzheng_cartype)
    TextView tvMainMineRenZhengCheLiangRenZhengCarType;
    @BindView(R.id.tv_main_mine_renzheng_cheliangrenzheng_carlength)
    TextView tvMainMineRenZhengCheLiangRenZhengCarLength;
    @BindView(R.id.tv_main_mine_renzheng_cheliangrenzheng_shengfendaihao)
    TextView tvMainMineRenZhengCheLiangRenZhengShengFenDaiHao;
    @BindView(R.id.et_main_mine_renzheng_cheliangrenzheng_zaizhong)
    EditText etMainMineRenZhengCheLiangRenZhengZaiZhong;
    @BindView(R.id.bt_main_mine_renzheng_cheliangrenzheng_submit)
    Button btMainMineRenZhengCheLiangRenZhengSubmit;
    @OnClick(R.id.bt_main_mine_renzheng_cheliangrenzheng_submit)
    public void btMainMineRenZhengCheLiangRenZhengSubmitOnclick(){
        applyAuthToNet();

    }

    @BindView(R.id.iv_main_mine_renzheng_cheliangrenzheng_clzp)
    ImageView ivMainMineRenZhengCheLiangRenZhengCLZP;
    @BindView(R.id.rly_main_mine_renzheng_cheliangrenzheng_clzp)
    RelativeLayout rlyMainMineRenZhengCheLiangRenZhengCLZP;

    @OnClick(R.id.rly_main_mine_renzheng_cheliangrenzheng_clzp)
    public void rlyMainMineRenZhengCheLiangRenZhengCLZPOnclick(){
        //从相册选择头像代码
        new AlertView.Builder().setContext(this)
                .setStyle(AlertView.Style.ActionSheet)
                .setTitle("选择车辆照片")
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
                                fromAlbum();
                                break;
                            case 1:
                                fromCamera();
                                break;
                            case -1:
                                break;
                        }
                    }
                }).build()
                .show();


    }


    private CheLiangRenZhengController cheLiangRenZhengController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_renzheng_cheliangrenzheng_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
        mImageList = new ArrayList<>();
    }


    private void initController(){
        cheLiangRenZhengController = new CheLiangRenZhengController(this);
    }




    /**
     * Select image from fromAlbum.
     */
    private void fromAlbum() {

        Album.album(this)
                .requestCode(ACTIVITY_REQUEST_SELECT_PHOTO)
                .toolBarColor(ContextCompat.getColor(this, R.color.colorPrimary)) // Toolbar color.
                .statusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)) // StatusBar color.
                .navigationBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryBlack)) // NavigationBar color.
                .selectCount(1) // select count.
                .columnCount(2) // span count.
                .camera(true) // has fromCamera function.
                .checkedList(mImageList) // The picture has been selected for anti-election.
                .start();
    }
    /**
     * Take a picture from fromCamera.
     */
    private void fromCamera() {
        Album.camera(this)
                .requestCode(ACTIVITY_REQUEST_TAKE_PICTURE)
//                .imagePath() // Specify the image path, optional.
                .start();
    }
    /**
     * Preview image.
     *
     * @param position current position.
     */
    private void previewImage(int position) {
        Album.gallery(this)
                .requestCode(ACTIVITY_REQUEST_PREVIEW_PHOTO)
                .toolBarColor(ContextCompat.getColor(this, R.color.colorPrimary)) // Toolbar color.
                .statusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)) // StatusBar color.
                .navigationBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryBlack)) // NavigationBar color.
                .checkedList(mImageList) // Image list.
                .currentPosition(position) // Preview first to show the first few.
                .checkFunction(true) // Does the user have an anti-selection when previewing.
                .start();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ACTIVITY_REQUEST_SELECT_PHOTO:
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList.clear();
                    mImageList = Album.parseResult(data); // Parse select result.
                   /* Toast.makeText(this,"ACTIVITY_REQUEST_SELECT_PHOTO:"+mImageList.get(0),Toast.LENGTH_LONG).show();*/
                    DisplayUtils.initScreen(this);
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.decoration_white, null);
 /*       rvMainReleaseZhuanXiaWuLiuSelectPhoto.addItemDecoration(new AlbumVerticalGirdDecoration(drawable));*/

                    assert drawable != null;
                    int itemSize = (DisplayUtils.screenWidth - (drawable.getIntrinsicWidth() * 4)) / 3;
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengCheLiangRenZhengCLZP, mImageList.get(0), 0, 0);
                    /*previewImage(0);*/
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                   /* Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();*/
                   /* mImageList.clear();*/
                }
                break;
            case ACTIVITY_REQUEST_TAKE_PICTURE:
                if (resultCode == RESULT_OK) { // Successfully.
                    List<String> imageList = Album.parseResult(data); // Parse path.
                    mImageList.clear();
                    mImageList.addAll(imageList);
                    Album.getAlbumConfig().getImageLoader().loadImage(ivMainMineRenZhengCheLiangRenZhengCLZP, mImageList.get(0), 0, 0);
                } else if (resultCode == RESULT_CANCELED) { // User canceled.

                }
                break;
            }

    }



    private Map<String,String> getParamMap(){
        Map<String,String> paramMap = new HashMap<>();
        String name = etMainMineRenZhengCheLiangRenZhengName.getText().toString().trim();
        if(name == null){
            name = "";
        }else{
            name = name.replaceAll(" ","");
        }
        paramMap.put("name",name);
        String id_number = etMainMineRenZhengCheLiangRenZhengShenFenZheng.getText().toString().trim();
        if(id_number == null){
            id_number = "";
        }else{
            id_number = id_number.replaceAll(" ","");
        }
        paramMap.put("id_number",id_number);
        String image = getBase64();
        if(image == null){
            image = "";
        }else{
            image = image.replaceAll(" ","");
        }
        paramMap.put("image",image);
        String province_number = etMainMineRenZhengCheLiangRenZhengCPH.getText().toString().trim();
        if(province_number == null){
            province_number = "";
        }else{
            province_number = province_number.replaceAll(" ","");
        }
        paramMap.put("province_number",province_number);

        String car_number = tvMainMineRenZhengCheLiangRenZhengShengFenDaiHao.getText().toString().trim();
        if(car_number == null){
            car_number = "";
        }else{
            car_number = car_number.replaceAll(" ","");
        }
        paramMap.put("car_number",car_number);
        String car_type = tvMainMineRenZhengCheLiangRenZhengCarType.getText().toString().trim();
        if(car_type == null){
            car_type = "";
        }else{
            car_type = car_type.replaceAll(" ","");
        }
        paramMap.put("car_type",car_type);

        String car_lange = tvMainMineRenZhengCheLiangRenZhengCarLength.getText().toString().trim();
        if(car_lange == null){
            car_lange = "";
        }else{
            car_lange = car_lange.replaceAll(" ","");
        }
        paramMap.put("car_lange",car_lange);

        String car_weight = etMainMineRenZhengCheLiangRenZhengZaiZhong.getText().toString().trim();
        if(car_weight == null){
            car_weight = "";
        }else{
            car_weight = car_weight.replaceAll(" ","");
        }
        paramMap.put("car_weight",car_weight);

        XCCacheManager xcCacheManager = XCCacheManager.getInstance(this);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if(login_id == null){
            login_id = "";
        }else{
            login_id = login_id.replaceAll(" ","");
        }
        paramMap.put("login_id",login_id);
        return paramMap;
    }


    private String getBase64(){
        File file;
        BitmapUtils bitmapUtils = new BitmapUtils();
        String base64_00 = "";
        file = new File(mImageList.get(0));
        if (file.exists()) {
            Bitmap bm = compressImageFromFile(mImageList.get(0));
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


    private void applyAuthToNet(){
        RenZhengNetWork renZhengNetWork = new RenZhengNetWork();
        renZhengNetWork.authCarToNet(getParamMap(), new Observer<AuthCarBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(AuthCarBean authCarBean) {
                Toast.makeText(CheLiangRenZhengActivity.this,authCarBean.getMessage().toString(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
