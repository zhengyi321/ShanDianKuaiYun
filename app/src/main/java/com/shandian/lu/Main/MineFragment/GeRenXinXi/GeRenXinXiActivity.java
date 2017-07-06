package com.shandian.lu.Main.MineFragment.GeRenXinXi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewFaBuPicBean;
import com.example.mynewslayoutlib.Bean.NewGeRenXinXiSubmitBean;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.NetWork.NewFaBuNetWork;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.util.DisplayUtils;
import com.zhyan.shandiankuaiyuanwidgetlib.AlertView.AlertView;
import com.zhyan.shandiankuaiyuanwidgetlib.AlertView.OnItemClickListener;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.BitmapUtils;
import com.shandian.lu.BaseActivity;
import com.shandian.lu.NetWork.UserNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.UpdateResultBean;
import com.zhyan.shandiankuaiyunlib.Utils.SharedPreferencesUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ImageView.RoundImageView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import rx.Observer;

/**
 * Created by az on 2017/5/4.
 */

public class GeRenXinXiActivity extends BaseActivity {
    private final int MSG_SET_ALIAS = 1001;
    protected final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:

                    /*JPushInterface.setAliasAndTags(GeRenXinXiActivity.this, (String) msg.obj, null, mAliasCallback);*/
                    JPushInterface.setAliasAndTags(GeRenXinXiActivity.this, (String) msg.obj,null,  mAliasCallback);
                    break;



                default:

            }
        }
    };
    private ArrayList<String> mImageList;
    private  final int ACTIVITY_REQUEST_SELECT_PHOTO = 100;
    private  final int ACTIVITY_REQUEST_TAKE_PICTURE = 101;
    private  final int ACTIVITY_REQUEST_PREVIEW_PHOTO = 102;
    private String MyPhotobase64;
    private boolean isPhoto = false;
    private List<String> resultImgList;
    private GeRenXinXiController geRenXinXiController;

    @BindView(R.id.riv_main_mine_gerenxinxi_content_headimg)
    RoundImageView rivMainMineGeRenXinXiContentHeadImg;
    @BindView(R.id.pb_main_mine_gerenxinxi_content_headimg)
    ProgressBar pbMainMineGeRenXinXiContentHeadImg;
    @BindView(R.id.pb_new_main_mine_gerenxinxi)
    ProgressBar pbNewMainMineGeRenXinXi;
    @BindView(R.id.tv_main_mine_gerenxinxi_content_name)
    TextView tvMainMineGeRenXinXiContentName;
    @BindView(R.id.tv_main_mine_gerenxinxi_content_nick)
    TextView tvMainMineGeRenXinXiContentNick;
    @BindView(R.id.tv_main_mine_gerenxinxi_content_sex)
    TextView tvMainMineGeRenXinXiContentSex;
    @BindView(R.id.tv_main_mine_gerenxinxi_content_myinvitecode)
    TextView tvMainMineGeRenXinXiContentMyInviteCode;
    @BindView(R.id.iv_main_mine_gerenxinxi_content_ewm)
    ImageView ivMainMineGeRenXinXiContentEWM;
    @BindView(R.id.tv_main_mine_gerenxinxi_content_wz)
    TextView tvMainMineGeRenXinXiContentWZ;
    @BindView(R.id.tv_main_mine_gerenxinxi_content_tel)
    TextView tvMainMineGeRenXinXiContentTel;
    @BindView(R.id.tv_main_mine_gerenxinxi_content_youxiang)
    TextView tvMainMineGeRenXinXiContentYouXiang;
    @BindView(R.id.tv_main_mine_gerenxinxi_content_wx)
    TextView tvMainMineGeRenXinXiContentWX;
    @BindView(R.id.tv_main_mine_gerenxinxi_content_qq)
    TextView tvMainMineGeRenXinXiContentQQ;

    @BindView(R.id.bt_main_mine_gerenxinxi_exit)
    Button btMainMineGeRenXinXiExit;
    @OnClick(R.id.bt_main_mine_gerenxinxi_exit)
    public void btMainMineGeRenXinXiExitOnclick(){
        loginOut();
    }


    private void loginOut(){
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(this);
        xcCacheManager.writeCache(xcCacheSaveName.logId,"");
        xcCacheManager.writeCache(xcCacheSaveName.loginStatus,"no");
        xcCacheManager.writeCache(xcCacheSaveName.userName,"");
        xcCacheManager.writeCache(xcCacheSaveName.userTel,"");
        xcCacheManager.writeCache(xcCacheSaveName.userHeadImgUrl,"");
        Toast.makeText(this,"已成功退出登录",Toast.LENGTH_LONG).show();
        SharedPreferencesUtils sharedPreferencesUtils = new SharedPreferencesUtils();
        sharedPreferencesUtils.setParam(this,xcCacheSaveName.logId, "");
        sharedPreferencesUtils.setParam(this,xcCacheSaveName.loginStatus,"no");
        sharedPreferencesUtils.setParam(this,xcCacheSaveName.userName,"");
        sharedPreferencesUtils.setParam(this,xcCacheSaveName.userTel,"");
        sharedPreferencesUtils.setParam(this,xcCacheSaveName.userHeadImgUrl,"");
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, ""));
        JPushInterface.stopPush(this);


        this.finish();
    }



    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {


        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i("success",logs);
                 /*   Toast.makeText(activity,"here is success:"+alias+" "+tags,Toast.LENGTH_LONG).show();*/
               /*     NotificationCompat.Builder	notification = new NotificationCompat.Builder(activity).setSmallIcon(R.mipmap.logo)
                            .setSound(Uri.parse("android.resource://" + activity.getPackageName() + "/" + R.raw.shandian));*/
                            /*.setContentText(title);*/
                    break;

                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";


                    break;

                default:
                    logs = "Failed with errorCode = " + code;

            }


        }

    };
    @BindView(R.id.lly_main_mine_gerenxinxi_content_headimg)
    LinearLayout llyMainMineGeRenXinXiContentHeadImg;
    @OnClick(R.id.lly_main_mine_gerenxinxi_content_headimg)
    public void llyMainMineGeRenXinXiContentHeadImgOnclick(){
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

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_gerenxinxi_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);

        initImgList();
        initController();
    }
    private void initImgList(){
        mImageList = new ArrayList<>();
        resultImgList = new ArrayList<>();
    }
    private void initController(){
        geRenXinXiController = new GeRenXinXiController(this);
       /* geRenXinXiController.getMyMessageFromNet();*/

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
                  /*  isPhoto = true;*/
                    mImageList.clear();
                    mImageList = Album.parseResult(data); // Parse select result.
                    upPicToNet();

                  /*  Toast.makeText(this,"ACTIVITY_REQUEST_SELECT_PHOTO:"+mImageList.get(0),Toast.LENGTH_LONG).show();*/
          /*          DisplayUtils.initScreen(this);
                    Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.decoration_white, null);
 *//*       rvMainReleaseZhuanXiaWuLiuSelectPhoto.addItemDecoration(new AlbumVerticalGirdDecoration(drawable));*//*

                    assert drawable != null;
                    int itemSize = (DisplayUtils.screenWidth - (drawable.getIntrinsicWidth() * 4)) / 3;
                    Album.getAlbumConfig().getImageLoader().loadImage(rivMainMineGeRenXinXiContentHeadImg, mImageList.get(0), 0, 0);*/

                    /*previewImage(0);*/
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                   /* Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();*/
                   /* mImageList.clear();*/
                }
                break;
            case ACTIVITY_REQUEST_TAKE_PICTURE:
                if (resultCode == RESULT_OK) { // Successfully.
                   /* isPhoto = true;*/
                    List<String> imageList = Album.parseResult(data); // Parse path.
                    mImageList.clear();
                    mImageList.addAll(imageList);
                    upPicToNet();

        /*            Album.getAlbumConfig().getImageLoader().loadImage(rivMainMineGeRenXinXiContentHeadImg, mImageList.get(0), 0, 0);*/

                } else if (resultCode == RESULT_CANCELED) { // User canceled.

                }
                break;
        }

    }



    private void initImages(){
        BitmapUtils bitmapUtils = new BitmapUtils();
        File file;
        if((mImageList == null)||(mImageList.size() == 0)){
            return;
        }
        file = new File(mImageList.get(0));
        if (file.exists()) {
            Bitmap bm = compressImageFromFile(mImageList.get(0));
            //将图片显示到ImageView中


            MyPhotobase64= bitmapUtils.bitmapConvertBase64(bm);

        }
    }

    //图片压缩
    private Bitmap compressImageFromFile(String srcPath) {

        BitmapUtils bitmapUtils = new BitmapUtils();
        Bitmap bitmap = bitmapUtils.getimage(srcPath);
        bitmap = bitmapUtils.compressImage(bitmap);
        bitmap = bitmapUtils.comp(bitmap);

        return bitmap;
    }

    private Map<String,String> getParamMap(){
        Map<String,String> paramMap = new HashMap<>();
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(this);
        String address = tvMainMineGeRenXinXiContentWZ.getText().toString().trim();
        if(address == null){
            address = "";
        }else{
            address = address.replaceAll(" ","");
        }
        paramMap.put("address","");

        String wecat = tvMainMineGeRenXinXiContentWX.getText().toString().trim();
        if(wecat == null){
            wecat = "";
        }else{
            wecat = wecat.replaceAll(" ","");
        }
        paramMap.put("wecat","");



        String nickename= tvMainMineGeRenXinXiContentNick.getText().toString().trim();
        if(nickename == null){
            nickename = "";
        }else{
            nickename = nickename.replaceAll(" ","");
        }
        paramMap.put("nickename","");

        initImages();
        if(MyPhotobase64 != null){
            paramMap.put("image",MyPhotobase64);
        }

        String email = tvMainMineGeRenXinXiContentYouXiang.getText().toString().trim();
        if(email == null){
            email = "";
        }else{
            email = email.replaceAll(" ","");
        }
        paramMap.put("email","");
        String qq = tvMainMineGeRenXinXiContentQQ.getText().toString().trim();
        if(qq == null){
            qq = "";
        }else{
            qq = qq.replaceAll(" ","");
        }
        paramMap.put("qq","");
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId).trim();
        if(login_id == null){
            login_id = "";
        }else{
            login_id = login_id.replaceAll(" ","");
        }
        paramMap.put("login_id",login_id);





        String sex = tvMainMineGeRenXinXiContentSex.getText().toString().trim();
        if(sex == null){
            sex = "";
        }else{
            sex = sex.replaceAll(" ","");
        }
        if(sex.equals("男")){
            sex = "1";
        }else{
            sex = "2";
        }
        paramMap.put("sex","0");
        return paramMap;
    }

    public void updateInfoToNet(){
        UserNetWork userNetWork = new UserNetWork();
        userNetWork.updateMyMessageToNet(getParamMap(), new Observer<UpdateResultBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(UpdateResultBean updateResultBean) {
                Toast.makeText(GeRenXinXiActivity.this,updateResultBean.getMsg(),Toast.LENGTH_LONG).show();
                geRenXinXiController.getMyMessageFromNet();
            }
        });
    }


    private Map<String,String> getUpPicParamMap( Bitmap bm){
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

        BitmapUtils bitmapUtils = new BitmapUtils();

        //将图片显示到ImageView中
        String base64_00 = bitmapUtils.bitmapConvertBase64(bm);
        paramMap.put("tu",base64_00);

        return paramMap;

    }

    private void upPicToNet(){
        NewFaBuNetWork newFaBuNetWork = new NewFaBuNetWork();
        pbMainMineGeRenXinXiContentHeadImg.setVisibility(View.VISIBLE);
        final Bitmap bitmap = compressImageFromFile(mImageList.get(0));
        newFaBuNetWork.upPicToNet(getUpPicParamMap(bitmap), new Observer<NewFaBuPicBean>() {
            @Override
            public void onCompleted() {
    /*            progressBar.setVisibility(View.GONE);*/
            }

            @Override
            public void onError(Throwable e) {

         /*       progressBar.setVisibility(View.GONE);*/

                 /*   Toast.makeText(activity,"Throwable:"+e,Toast.LENGTH_LONG).show();*/
            }

            @Override
            public void onNext(NewFaBuPicBean newFaBuPicBean) {
                if(newFaBuPicBean.getStatus().equals("0")){
                    resultImgList.clear();
                    /*resultImgList.add("\""+newFaBuPicBean.getImgurl()+"\"");*/
                    resultImgList.add(newFaBuPicBean.getImgurl());
               /*     upPicCount--;*/
                    rivMainMineGeRenXinXiContentHeadImg.setImageBitmap(bitmap);
                        /*pbNewFaBuHuoYuan.setVisibility(View.GONE);*/
                    pbMainMineGeRenXinXiContentHeadImg.setVisibility(View.GONE);
                    submitPicToNet();
                }
            }
        });
    }


    private Map<String,Object> getSubmitPicToNetParamMap(){
        Map<String,Object> paramMap = new HashMap<>();
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
        paramMap.put("image",resultImgList.get(0));
        return paramMap;

    }

    private void submitPicToNet(){
        pbNewMainMineGeRenXinXi.setVisibility(View.VISIBLE);
        UserNetWork userNetWork = new UserNetWork();
        userNetWork.submitNewGeRenXinXiToNet(getSubmitPicToNetParamMap(), new Observer<NewGeRenXinXiSubmitBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewGeRenXinXiSubmitBean newGeRenXinXiSubmitBean) {
                Toast.makeText(GeRenXinXiActivity.this,newGeRenXinXiSubmitBean.getMsg(),3000).show();
                pbNewMainMineGeRenXinXi.setVisibility(View.GONE);
            }
        });

    }



    @Override
    protected void onResume(){
        super.onResume();
       /* geRenXinXiController.getMyMessageFromNet();*/
      /*  if(isPhoto){
           *//* updateInfoToNet();*//*
            isPhoto = false;
        }else{
            geRenXinXiController.getMyMessageFromNet();
        }*/
        geRenXinXiController.onResume();
    }
}
