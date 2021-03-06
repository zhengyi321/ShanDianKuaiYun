package com.shandian.lu.Main.MineFragment;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MineFragment.AboutUs.AboutUsActivity;
import com.shandian.lu.Main.MineFragment.GeRenXinXi.GeRenXinXiActivity;
import com.shandian.lu.Main.MineFragment.JiFenShangCheng.JiFenShangChengActivity;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.Main.MineFragment.MengYou.MengYouActivity;
import com.shandian.lu.Main.MineFragment.RenZheng.RenZhengActivity;
import com.shandian.lu.Main.MineFragment.SheZhi.SheZhiActivity;
import com.shandian.lu.Main.MineFragment.WoDeCheYuan.NewWoDeCheYuanActivity;
import com.shandian.lu.Main.MineFragment.WoDeCheYuan.WoDeCheYuanActivity;
import com.shandian.lu.Main.MineFragment.WoDeHuoYuan.NewWoDeHuoYuanActivity;
import com.shandian.lu.Main.MineFragment.WoDeHuoYuan.WoDeHuoYuanActivity;
import com.shandian.lu.Main.MineFragment.WoDeQianBao.NewMyWalletsHistoryListActivity;
import com.shandian.lu.Main.MineFragment.WoDeRenZheng.NewWoDeRenZhengActivity;
import com.shandian.lu.Main.MineFragment.WoDeYaoQing.WoDeYaoQingActivity;
import com.shandian.lu.NetWork.UserNetWork;
import com.shandian.lu.R;
import com.shandian.lu.Widget.YouMeng.Defaultcontent;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.utils.Log;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.LianXiKeFuDialog;
import com.zhyan.shandiankuaiyuanwidgetlib.Fragment.MyFragment;
import com.zhyan.shandiankuaiyunlib.Bean.MyMessageBean;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ImageView.RoundImageView;

import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import rx.Observer;

/**
 * Created by Administrator on 2017/6/7.
 */

public class NewMainMineController extends BaseController {


    private LianXiKeFuDialog lianXiKeFuDialog;
    private String nick = "",sex="",myInviteCode = "",wz="",youxiang="",wx="",qq="";


    @BindView(R.id.fly_new_main_mine_login_or_update_gerenxinxi)
    FrameLayout flyNewMainMineLoginOrUpdateGeRenXinXi;
    @OnClick(R.id.fly_new_main_mine_login_or_update_gerenxinxi)
    public void flyNewMainMineLoginOrUpdateGeRenXinXiOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String loginStatus = xcCacheManager.readCache(xcCacheSaveName.loginStatus);
        if((loginStatus != null)&&(loginStatus.equals("yes"))){
            Intent intent = new Intent(view.getContext(), GeRenXinXiActivity.class);
            view.getContext().startActivity(intent);
        }else{
            Intent intent = new Intent(view.getContext(), LoginActivity.class);
            view.getContext().startActivity(intent);
        }


       /* Intent intent = new Intent(view.getContext(), LoginActivity.class);
        Intent intent = new Intent(view.getContext(), GeRenXinXiActivity.class);
        view.getContext().startActivity(intent);*/
    }

    @BindView(R.id.lly_new_main_mine_renzheng)
    LinearLayout llyNewMainMineRenZheng;
    @OnClick(R.id.lly_new_main_mine_renzheng)
    public void llyNewMainMineRenZhengOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
          /*  Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();*/
            view.getContext().startActivity(new Intent(view.getContext(),LoginActivity.class));
            return;
        }
        /*Intent intent = new Intent(view.getContext(), NewWoDeRenZhengActivity.class);*/
        Intent intent = new Intent(view.getContext(), NewWoDeRenZhengActivity.class);
        view.getContext().startActivity(intent);
    }

    @BindView(R.id.lly_new_main_mine_mywallet)
    LinearLayout llyNewMainMineMyWallet;
    @OnClick(R.id.lly_new_main_mine_mywallet)
    public void  llyNewMainMineMyWalletOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            /*Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();*/
            view.getContext().startActivity(new Intent(view.getContext(),LoginActivity.class));
            return;
        }
        Intent intent = new Intent(view.getContext(), NewMyWalletsHistoryListActivity.class);
        view.getContext().startActivity(intent);
    }

    @BindView(R.id.lly_new_main_mine_wodecheyuan)
    LinearLayout llyNewMainMineWoDeCheYuan;
    @OnClick(R.id.lly_new_main_mine_wodecheyuan)
    public void llyNewMainMineWoDeCheYuanOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            /*Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();*/
            view.getContext().startActivity(new Intent(view.getContext(),LoginActivity.class));
            return;
        }
        Intent intent = new Intent(view.getContext(), NewWoDeCheYuanActivity.class);
        view.getContext().startActivity(intent);
    }
    @BindView(R.id.lly_new_main_mine_wodehuoyuan)
    LinearLayout llyNewMainMineWoDeHuoYuan;
    @OnClick(R.id.lly_new_main_mine_wodehuoyuan)
    public void llyNewMainMineWoDeHuoYuanOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
      /*      Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();*/
            view.getContext().startActivity(new Intent(view.getContext(),LoginActivity.class));
            return;
        }
        Intent intent = new Intent(view.getContext(), NewWoDeHuoYuanActivity.class);
        view.getContext().startActivity(intent);
    }
    @BindView(R.id.lly_new_main_mine_wodeyaoqing)
    LinearLayout llyNewMainMineWoDeYaoQing;
    @OnClick(R.id.lly_new_main_mine_wodeyaoqing)
    public void llyNewMainMineWoDeYaoQingOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
        /*    Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();*/
            view.getContext().startActivity(new Intent(view.getContext(),LoginActivity.class));
            return;
        }
        Intent intent = new Intent(view.getContext(), WoDeYaoQingActivity.class);
        view.getContext().startActivity(intent);
    }

    @BindView(R.id.lly_new_main_mine_fenxiang)
    LinearLayout llyNewMainMineFenXiang;
    @OnClick(R.id.lly_new_main_mine_fenxiang)
    public void llyNewMainMineFenXiangOnclickk(){
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

    @BindView(R.id.lly_new_main_mine_mengyou)
    LinearLayout llyNewMainMineMengYou;
    @OnClick(R.id.lly_new_main_mine_mengyou)
    public void llyNewMainMineMengYouOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            /*Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();*/
            view.getContext().startActivity(new Intent(view.getContext(),LoginActivity.class));
            return;
        }
        Intent intent = new Intent(view.getContext(), MengYouActivity.class);
/*        view.getContext().startActivity(intent);*/
    }
    @BindView(R.id.lly_new_main_mine_jifenshangcheng)
    LinearLayout llyNewMainMineJiFenShangCheng;
    @OnClick(R.id.lly_new_main_mine_jifenshangcheng)
    public void llyNewMainMineJiFenShangChengOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            /*Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();*/
            view.getContext().startActivity(new Intent(view.getContext(),LoginActivity.class));
            return;
        }
        Intent intent = new Intent(view.getContext(), JiFenShangChengActivity.class);
        view.getContext().startActivity(intent);
    }
    @BindView(R.id.lly_new_main_mine_shezhi)
    LinearLayout llyNewMainMineSheZhi;
    @OnClick(R.id.lly_new_main_mine_shezhi)
    public void llyNewMainMineSheZhiOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            /*Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();*/
            view.getContext().startActivity(new Intent(view.getContext(),LoginActivity.class));
            return;
        }
        Intent intent = new Intent(view.getContext(), SheZhiActivity.class);
        view.getContext().startActivity(intent);
    }
    @BindView(R.id.lly_new_main_mine_aboutus)
    LinearLayout llyNewMainMineAboutUs;
    @OnClick(R.id.lly_new_main_mine_aboutus)
    public void llyNewMainMineAboutUsOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            /*Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();*/
            view.getContext().startActivity(new Intent(view.getContext(),LoginActivity.class));
            return;
        }
        Intent intent = new Intent(view.getContext(), AboutUsActivity.class);
        view.getContext().startActivity(intent);
    }

    @BindView(R.id.tv_new_main_mine_name)
    TextView tvNewMainMineName;
    @BindView(R.id.tv_new_main_mine_tel)
    TextView tvNewMainMineTel;
    @BindView(R.id.riv_new_main_mine_headimg)
    CircleImageView rivNewMainMinHeadImg;
    @BindView(R.id.lly_new_main_mine_lianxikefu)
    LinearLayout llyNewMainMineLianXiKeFu;
    @OnClick(R.id.lly_new_main_mine_lianxikefu)
    public void llyNewMainMineLianXiKeFuOnclick(){
        lianxikefuCall();
    }


    private ImageLoader loader;

    public NewMainMineController(View view1){
        view  = view1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,view);
        loader=ImageLoader.getInstance();

    }




    public void onResume(){
        isLogin();
    }


    private void isLogin(){
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        String loginStatus = xcCacheManager.readCache(xcCacheSaveName.loginStatus);
        if(loginStatus == null){
            return;
        }
        if((loginStatus != null)&&(loginStatus.equals("no"))){
            rivNewMainMinHeadImg.setImageResource(R.mipmap.mylogin);
            tvNewMainMineName.setText("我叫闪电");
            tvNewMainMineTel.setText("");
            return;
        }
        if((loginStatus != null)&&(loginStatus.equals("yes"))){
            String userName = xcCacheManager.readCache(xcCacheSaveName.userName);
            String userTel = xcCacheManager.readCache(xcCacheSaveName.userTel);
            String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
            if(userName!= null){
                tvNewMainMineName.setText(userName);
            }
            if(userTel != null){
                tvNewMainMineTel.setText(userTel);
            }
            UserNetWork userNetWork = new UserNetWork();
            userNetWork.getMyMessageFromNet(loginId, new Observer<MyMessageBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(MyMessageBean myMessageBean) {/*
                    FinalBitmap finalBitMap = null;
                    finalBitMap = FinalBitmap.create(view.getContext());
                    finalBitMap.display(rivMainMinHeadImg, myMessageBean.getContent().getPath());*/
                    XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
                    XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
                    xcCacheManager.writeCache(xcCacheSaveName.userHeadImgUrl,myMessageBean.getContent().getPath());
                    xcCacheManager.writeCache(xcCacheSaveName.userEWMUrl,myMessageBean.getContent().getQr_code());
                    loader.displayImage(myMessageBean.getContent().getPath(),rivNewMainMinHeadImg, ImageLoaderUtils.options1);
                    nick = myMessageBean.getContent().getNickename();
                    if(nick == null){
                        nick = "";
                    }
                    sex=myMessageBean.getContent().getSex().toString();
                    if(sex == null){
                        sex = "";
                    }
                    myInviteCode = myMessageBean.getContent().getOne_code();
                    if(myInviteCode == null){
                        myInviteCode = "";
                    }
                    wz=myMessageBean.getContent().getAddress().toString();
                    if(wz == null){
                        wz = "";
                    }
                    youxiang=myMessageBean.getContent().getEmail().toString();
                    if(youxiang == null){
                        youxiang = "";
                    }
                    wx=myMessageBean.getContent().getWei_code().toString();
                    if(wx == null){
                        wx = "";
                    }
                    qq=myMessageBean.getContent().getQq_code().toString();
                    if(qq == null){
                        qq = "";
                    }

                }
            });
        }
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

}
