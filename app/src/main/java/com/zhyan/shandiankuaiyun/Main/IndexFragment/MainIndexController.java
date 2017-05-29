package com.zhyan.shandiankuaiyun.Main.IndexFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.LianXiKeFuDialog;
import com.zhyan.shandiankuaiyun.BaseController;
import com.zhyan.shandiankuaiyun.Main.IndexFragment.BanJia.BanJiaActivity;
import com.zhyan.shandiankuaiyun.Main.IndexFragment.NearByDriver.NearByDriverActivity;
import com.zhyan.shandiankuaiyun.Main.IndexFragment.PeiHuoZhongXin.PeiHuoZhongXinActivity;
import com.zhyan.shandiankuaiyun.Main.IndexFragment.RenRenWuLiu.RenRenWuLiuActivity;
import com.zhyan.shandiankuaiyun.Main.IndexFragment.TeZhongWuLiu.TeZhongWuLiuActivity;
import com.zhyan.shandiankuaiyun.Main.IndexFragment.ZhengCheHuoYun.ZhengCheHuoYunActivity;
import com.zhyan.shandiankuaiyun.Main.IndexFragment.ZhuanXianWuLiu.ZhuanXianWuLiuActivity;
import com.zhyan.shandiankuaiyun.NetWork.MainIndexNetWork;
import com.zhyan.shandiankuaiyun.R;
import com.zhyan.shandiankuaiyunlib.Bean.MainIndexAdBean;
import com.zhyan.shandiankuaiyunlib.Widget.ViewPage.ImageCycleView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

import static com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils.options;

/**
 * Created by az on 2017/4/27.
 */

public class MainIndexController extends BaseController{

    LianXiKeFuDialog lianXiKeFuDialog;

    @BindView(R.id.icv_main_index_ad_circle)
    ImageCycleView icvMainIndexAdCircle;
/*    @BindView(R.id.vp_main_index_ad_circle)
    ViewPager vpMainIndexAdCircle;*/
/*    @BindView(R.id.lly_main_index_content_points)
    LinearLayout llyMainIndexContentPoints;*/

    @BindView(R.id.ib_main_index_phzx)
    ImageButton ibMainIndexPHZX;
    @OnClick(R.id.ib_main_index_phzx)
    public void ibMainIndexPHZXOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(view.getContext(), PeiHuoZhongXinActivity.class);
        view.getContext().startActivity(intent);
    }

    @BindView(R.id.ib_main_index_zxwl)
    ImageButton ibMainIndexZXWL;
    @OnClick(R.id.ib_main_index_zxwl)
    public void ibMainIndexZXWLOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(view.getContext(), ZhuanXianWuLiuActivity.class);
        view.getContext().startActivity(intent);
    }

    @BindView(R.id.ib_main_index_tzwl)
    ImageButton ibMainIndexTZWL;
    @OnClick(R.id.ib_main_index_tzwl)
    public void ibMainIndexTZWLOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(view.getContext(), TeZhongWuLiuActivity.class);
        view.getContext().startActivity(intent);

    }
    @BindView(R.id.ib_main_index_fjsj)
    ImageButton ibMainIndexFJSJ;
    @OnClick(R.id.ib_main_index_fjsj)
    public void ibMainIndexFJSJOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(view.getContext(), NearByDriverActivity.class);
        view.getContext().startActivity(intent);

    }

    @BindView(R.id.ib_main_index_zchy)
    ImageButton ibMainIndexZCHY;
    @OnClick(R.id.ib_main_index_zchy)
    public void ibMainIndexZCHYOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(view.getContext(), ZhengCheHuoYunActivity.class);
        view.getContext().startActivity(intent);
    }

    @BindView(R.id.ib_main_index_rrwl)
    ImageButton ibMainIndexRRWL;
    @OnClick(R.id.ib_main_index_rrwl)
    public void ibMainIndexRRWLOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(view.getContext(), RenRenWuLiuActivity.class);
        view.getContext().startActivity(intent);

    }

    @BindView(R.id.ib_main_index_bj)
    ImageButton ibMainIndexBJ;
    @OnClick(R.id.ib_main_index_bj)
    public void ibMainIndexBJOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(view.getContext(), BanJiaActivity.class);
        view.getContext().startActivity(intent);

    }

    @BindView(R.id.ib_main_index_kf)
    ImageButton ibMainIndexKF;
    @OnClick(R.id.ib_main_index_kf)
    public void ibMainIndexKFOnclick(){
        lianxikefuCall();
    }

    public MainIndexController(View view1){
        view = view1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,view);
/*        initViews();*/
        initCircleFromNet();
    }

    private void initViews(){
        ArrayList<String> imgList = new ArrayList<>();
        ArrayList<String> imgList2 = new ArrayList<>();
        imgList2.add("");
        imgList2.add("");
        imgList2.add("");
        imgList.add("https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=900387085,507082200&fm=117&gp=0.jpg");
        imgList.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=3597382613,1842885761&fm=117&gp=0.jpg");
        icvMainIndexAdCircle.setImageResources(imgList,imgList2,imageCycleViewListener);
        /*loopViewPage = new LoopViewPage(view.getContext(),vpMainIndexAdCircle,llyMainIndexContentPoints,imgList);*/
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
                imgList2.add("");
                imgList2.add("");
                ArrayList<String> picList = new ArrayList<String>();
                for(int i = 0;i < size;i++){
                    picList.add(mainIndexAdBean.getContent().getSide().get(i).getImage());
                }
                icvMainIndexAdCircle.setImageResources(picList,imgList2,imageCycleViewListener);
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

        }
    };













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
}
