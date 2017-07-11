package com.shandian.lu.Main.IndexFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mynewslayoutlib.Views.MyGiftView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.BaseController;


import com.shandian.lu.ErWeiMa.CaptureActivity;
import com.shandian.lu.Main.IndexFragment.BanJia.BanJiaActivity;
import com.shandian.lu.Main.IndexFragment.NewCheYuanList.CheYuanListActivity;
import com.shandian.lu.Main.IndexFragment.HongBao.HongBaoActivity;
import com.shandian.lu.Main.IndexFragment.NearByDriver.NearByDriverActivity;
import com.shandian.lu.Main.IndexFragment.NewCheYuanList.NewBanJiaListActivity;
import com.shandian.lu.Main.IndexFragment.NewHuoYuanList.HuoYuanListActivity;
import com.shandian.lu.Main.IndexFragment.RenRenWuLiu.RenRenWuLiuActivity;
import com.shandian.lu.Main.IndexFragment.WebView.WebViewActivity;
import com.shandian.lu.Main.IndexFragment.ZhengCheHuoYun.ZhengCheHuoYunActivity;
import com.shandian.lu.NetWork.MainIndexNetWork;
import com.shandian.lu.R;
import com.shandian.lu.Widget.XuanFu.MserServes;
import com.shandian.lu.Widget.YouMeng.Defaultcontent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Log;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.LianXiKeFuDialog;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.PermissionUtil;
import com.zhyan.shandiankuaiyunlib.Bean.MainIndexAdBean;
import com.zhyan.shandiankuaiyunlib.Utils.SharedPreferencesUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ViewPage.ImageCycleView;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.PermissionUtil.RequestCode;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import android.Manifest.permission;
import static com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils.options;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.PermissionUtil.AfterPermissionGranted;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.PermissionUtil.PermissionCallback;
/*import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Log;*/

/**
 * Created by az on 2017/4/27.
 */

public class NewMainIndexController extends BaseController implements PermissionCallback{
    public NewMainIndexController(View view1){
        view = view1;
        init();
    }
    LianXiKeFuDialog lianXiKeFuDialog;
    private MainIndexAdBean mainIndexAdBean1;
    @BindView(R.id.icv_new_main_index_ad_circle)
    ImageCycleView icvNewMainIndexAdCircle;
    @BindView(R.id.pb_new_main_index)
    ProgressBar pbNewMainIndex;
    @BindView(R.id.mgv_new_main_index)
    MyGiftView mgvNewMainIndex;
    @OnClick(R.id.mgv_new_main_index)
    public void mgvNewMainIndexOnclick(){
        Intent intent = new Intent(view.getContext(), HongBaoActivity.class);
        view.getContext().startActivity(intent);
    }
    @BindView(R.id.ib_new_main_index_hyzx)
    ImageButton ibNewMainIndexHYZX;
    @OnClick(R.id.ib_new_main_index_hyzx)
    public void  ibNewMainIndexHYZXOnclick(){
        pbNewMainIndex.setVisibility(View.VISIBLE);
        /*Intent intent = new Intent(view.getContext(), ZhengCheHuoYunActivity.class);*/
        Intent intent = new Intent(view.getContext(), HuoYuanListActivity.class);
        view.getContext().startActivity(intent);
        pbNewMainIndex.setVisibility(View.GONE);
    }

    @BindView(R.id.rly_main_index_erweima)
    RelativeLayout rlyMainIndexErWeiMa;
    @OnClick(R.id.rly_main_index_erweima)
    public void rlyMainIndexErWeiMaOnclick(){
        try {
            startSao();
        } catch (Exception e) {
            Toast.makeText(view.getContext(), "您已拒绝拍摄权限，请到系统设置打开权限！",0).show();
            e.printStackTrace();
        }
    }
    @AfterPermissionGranted(RequestCode.CAMERA_CAPTURE)
    public void startSao() {
        if (checkPermission(permission.CAMERA)) {
            Intent intent = new Intent(view.getContext(), CaptureActivity.class);
            view.getContext().startActivity(intent);
        }else{
            requestPermissions(RequestCode.CAMERA_CAPTURE,
                    permission.CAMERA);
        }

    }
    /**
     * 检查权限
     *
     * @param permissions
     * @return
     */
    public boolean checkPermission(@NonNull String... permissions) {
        return PermissionUtil.checkPermission(view.getContext(), permissions);
    }
    /**
     * 请求权限
     *
     * @param requestCode
     * @param permissions
     */
    public void requestPermissions(int requestCode, String... permissions) {
        PermissionUtil.requestPermissions(view.getContext(), requestCode, permissions);
    }
    @BindView(R.id.ib_new_main_index_ctwl)
    ImageButton ibNewMainIndexCTWL;
    @OnClick(R.id.ib_new_main_index_ctwl)
    public void ibNewMainIndexCTWLOnclick(){
        Intent intent = new Intent(view.getContext(), CheYuanListActivity.class);
        intent.putExtra("typeName","2");
        view.getContext().startActivity(intent);


    }


    @BindView(R.id.ib_new_main_index_zxwl)
    ImageButton ibNewMainIndexZXWL;
    @OnClick(R.id.ib_new_main_index_zxwl)
    public void ibNewMainIndexZXWLOnclick(){

        Intent intent = new Intent(view.getContext(), CheYuanListActivity.class);
        intent.putExtra("typeName","4");
        view.getContext().startActivity(intent);
    }

    @BindView(R.id.ib_new_main_index_tzwl)
    ImageButton ibNewMainIndexTZWL;
    @OnClick(R.id.ib_new_main_index_tzwl)
    public void ibNewMainIndexTZWLOnclick(){

        Intent intent = new Intent(view.getContext(), CheYuanListActivity.class);
        intent.putExtra("typeName","3");
        view.getContext().startActivity(intent);

    }



    @BindView(R.id.ib_new_main_index_tchy)
    ImageButton ibNewMainIndexTCHY;
    @OnClick(R.id.ib_new_main_index_tchy)
    public void ibNewMainIndexTCHYOnclick(){

        Intent intent = new Intent(view.getContext(), CheYuanListActivity.class);
        intent.putExtra("typeName","1");
        view.getContext().startActivity(intent);
    }




    @BindView(R.id.ib_new_main_index_fx)
    ImageButton ibNewMainIndexFX;
    @OnClick(R.id.ib_new_main_index_fx)
    public void ibNewMainIndexFXOnclick(){

        Defaultcontent defaultcontent = new Defaultcontent();
        UMImage image=new UMImage(view.getContext(), R.mipmap.logo);
        new ShareAction((Activity) view.getContext()).setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.WEIXIN_FAVORITE,SHARE_MEDIA.QZONE)
                .withTitle(defaultcontent.title)
                .withText(defaultcontent.text)
                .withMedia(image)
                .withTargetUrl(defaultcontent.url)
                .setCallback(umShareListener)
                .open();
    }

    @BindView(R.id.ib_new_main_index_rrwl)
    ImageButton ibNewMainIndexRRWL;
    @OnClick(R.id.ib_new_main_index_rrwl)
    public void ibNewMainIndexRRWLOnclick(){

        /*Intent intent = new Intent(view.getContext(), RenRenWuLiuActivity.class);*/
        Intent intent = new Intent(view.getContext(), CheYuanListActivity.class);
        intent.putExtra("typeName","5");
        view.getContext().startActivity(intent);

    }

    @BindView(R.id.ib_new_main_index_bj)
    ImageButton ibNewMainIndexBJ;
    @OnClick(R.id.ib_new_main_index_bj)
    public void ibNewMainIndexBJOnclick(){
/*
        Intent intent = new Intent(view.getContext(), BanJiaActivity.class);*/
        /*Intent intent = new Intent(view.getContext(), CheYuanListActivity.class);*/
        Intent intent = new Intent(view.getContext(), NewBanJiaListActivity.class);
        intent.putExtra("typeName","6");
        ((Activity)view.getContext()).startActivity(intent);

    }


    @BindView(R.id.ib_new_main_index_kf)
    ImageButton ibNewMainIndexKF;
    @OnClick(R.id.ib_new_main_index_kf)
    public void ibNewMainIndexKFOnclick(){
        lianxikefuCall();
    }
/*    @BindView(R.id.vp_main_index_ad_circle)
    ViewPager vpMainIndexAdCircle;*/
/*    @BindView(R.id.lly_main_index_content_points)
    LinearLayout llyMainIndexContentPoints;*/

/*    @BindView(R.id.ib_main_index_phzx)
    ImageButton ibMainIndexPHZX;
    @OnClick(R.id.ib_main_index_phzx)
    public void ibMainIndexPHZXOnclick(){

        Intent intent = new Intent(view.getContext(), PeiHuoZhongXinActivity.class);
        view.getContext().startActivity(intent);
    }*/
/*
    @BindView(R.id.ib_main_index_zxwl)
    ImageButton ibMainIndexZXWL;
    @OnClick(R.id.ib_main_index_zxwl)
    public void ibMainIndexZXWLOnclick(){

        Intent intent = new Intent(view.getContext(), ZhuanXianWuLiuActivity.class);
        view.getContext().startActivity(intent);
    }*/

/*    @BindView(R.id.ib_main_index_tzwl)
    ImageButton ibMainIndexTZWL;
    @OnClick(R.id.ib_main_index_tzwl)
    public void ibMainIndexTZWLOnclick(){

        Intent intent = new Intent(view.getContext(), TeZhongWuLiuActivity.class);
        view.getContext().startActivity(intent);

    }*/
  /*  @BindView(R.id.ib_main_index_fjsj)
    ImageButton ibMainIndexFJSJ;
    @OnClick(R.id.ib_main_index_fjsj)
    public void ibMainIndexFJSJOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
          *//*  Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();*//*
          Intent intent = new Intent(view.getContext(), LoginActivity.class);
          view.getContext().startActivity(intent);
            return;
        }
        Intent intent = new Intent(view.getContext(), NearByDriverActivity.class);
        view.getContext().startActivity(intent);

    }*/

/*    @BindView(R.id.ib_main_index_zchy)
    ImageButton ibMainIndexZCHY;
    @OnClick(R.id.ib_main_index_zchy)
    public void ibMainIndexZCHYOnclick(){

        Intent intent = new Intent(view.getContext(), ZhengCheHuoYunActivity.class);
        view.getContext().startActivity(intent);
    }*/
/*
    @BindView(R.id.ib_main_index_rrwl)
    ImageButton ibMainIndexRRWL;
    @OnClick(R.id.ib_main_index_rrwl)
    public void ibMainIndexRRWLOnclick(){

        Intent intent = new Intent(view.getContext(), RenRenWuLiuActivity.class);
        view.getContext().startActivity(intent);

    }*/

 /*   @BindView(R.id.ib_main_index_bj)
    ImageButton ibMainIndexBJ;
    @OnClick(R.id.ib_main_index_bj)
    public void ibMainIndexBJOnclick(){

        Intent intent = new Intent(view.getContext(), BanJiaActivity.class);
        ((Activity)view.getContext()).startActivity(intent);

    }*/

 /*   @BindView(R.id.ib_main_index_kf)
    ImageButton ibMainIndexKF;
    @OnClick(R.id.ib_main_index_kf)
    public void ibMainIndexKFOnclick(){
        lianxikefuCall();
    }
    @BindView(R.id.ib_main_index_fx)
    ImageButton ibMainIndexFX;
    @OnClick(R.id.ib_main_index_fx)
    public void ibMainIndexFXOnclick(){

        Defaultcontent defaultcontent = new Defaultcontent();
        UMImage image=new UMImage(view.getContext(), R.mipmap.logo);
        new ShareAction((Activity) view.getContext()).setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.WEIXIN_FAVORITE*//*,SHARE_MEDIA.SMS*//*,SHARE_MEDIA.QZONE)
                .withTitle(defaultcontent.title)
                .withText(defaultcontent.text)
                .withMedia(image)
                .withTargetUrl(defaultcontent.url)
                .setCallback(umShareListener)
                .open();
    }*/
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat","platform"+platform);
            if(platform.name().equals("WEIXIN_FAVORITE")){
                Toast.makeText(view.getContext(),"收藏成功",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(view.getContext(), "分享成功", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(view.getContext(),  "分享失败", Toast.LENGTH_SHORT).show();
            if(t!=null){
                Log.d("throw","throw:"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(view.getContext(),"分享取消了", Toast.LENGTH_SHORT).show();
        }
    };




    @Override
    protected void init() {
        ButterKnife.bind(this,view);
       view.getContext().startService(new Intent( view.getContext(), MserServes.class));
/*        initViews();*/

    }



    private void initCircleFromNet(){
        MainIndexNetWork mainIndexNetWork = new MainIndexNetWork();
        mainIndexNetWork.getAdFromNet(new Observer<MainIndexAdBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MainIndexAdBean mainIndexAdBean) {
                int size = mainIndexAdBean.getContent().getSide().size();
                ArrayList<String> imgList2 = new ArrayList<>();
                ArrayList<String> picList = new ArrayList<String>();
                for(int i = 0;i < size;i++){
                    picList.add(mainIndexAdBean.getContent().getSide().get(i).getImage());
                    imgList2.add("");//标题title

                }
                mainIndexAdBean1 = mainIndexAdBean;
                icvNewMainIndexAdCircle.setImageResources(picList,imgList2,imageCycleViewListener);
            }
        });
    }
    private ImageCycleView.ImageCycleViewListener imageCycleViewListener=new ImageCycleView.ImageCycleViewListener() {
        @Override
        public void displayImage(String imageURL, ImageView imageView) {
            ImageLoader.getInstance().displayImage(imageURL,imageView,options);
        }

        @Override
        public void onImageClick(String info, int postion, View imageView) {
            /*Toast.makeText(view.getContext(),""+postion+info,Toast.LENGTH_LONG).show();*/
            if(mainIndexAdBean1 != null){
                String url = mainIndexAdBean1.getContent().getSide().get(postion).getUrl().toString();
                Intent intent = new Intent(view.getContext(), WebViewActivity.class);
                intent.putExtra("url",url);
                view.getContext().startActivity(intent);
            }
        }
    };


    public void onResume(){
        initCircleFromNet();
    }










    private void lianxikefuCall() {
        lianXiKeFuDialog = new LianXiKeFuDialog(view.getContext()).Build.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dissmissDialog();
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dissmissDialog();
            }
        }).setCallBackListener(new LianXiKeFuDialog.DialogCallBackListener() {
            @Override
            public void callBack(String tel) {
                startCallTel(tel);
            }
        }).build(view.getContext());
        showDialog();
    }
    private void startCallTel(String number) {
        /*PhoneFormatCheckUtils phoneFormatCheckUtils = new PhoneFormatCheckUtils();
        if((number != null)&&(phoneFormatCheckUtils.IsNumber(number))) {*/
        //用intent启动拨打电话
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));

        view.getContext().startActivity(intent);
       /* }*/
    }

    public void showDialog() {
        if (lianXiKeFuDialog != null && !lianXiKeFuDialog.isShowing())
            lianXiKeFuDialog.show();
    }

    public void dissmissDialog() {
        if (lianXiKeFuDialog != null && lianXiKeFuDialog.isShowing())
            lianXiKeFuDialog.dismiss();
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(view.getContext(), "您已拒绝拍摄权限，请到系统设置打开权限！",0).show();
    }
    @SuppressLint("NewApi")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
       /* super.onRequestPermissionsResult(requestCode, permissions, grantResults);*/
        PermissionUtil.onRequestPermissionsResult(requestCode, permissions,
                grantResults, this);
    }
}
