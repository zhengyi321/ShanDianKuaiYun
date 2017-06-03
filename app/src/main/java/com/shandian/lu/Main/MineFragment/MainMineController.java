package com.shandian.lu.Main.MineFragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.Main.MineFragment.AboutUs.AboutUsActivity;
import com.shandian.lu.Main.MineFragment.GeRenXinXi.GeRenXinXiActivity;
import com.shandian.lu.Main.MineFragment.JiFenShangCheng.JiFenShangChengActivity;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.Main.MineFragment.MengYou.MengYouActivity;
import com.shandian.lu.Main.MineFragment.RenZheng.RenZhengActivity;
import com.shandian.lu.Main.MineFragment.SheZhi.SheZhiActivity;
import com.shandian.lu.Main.MineFragment.WoDeCheYuan.WoDeCheYuanActivity;
import com.shandian.lu.Main.MineFragment.WoDeHuoYuan.WoDeHuoYuanActivity;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.LianXiKeFuDialog;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MineFragment.WoDeYaoQing.WoDeYaoQingActivity;
import com.shandian.lu.NetWork.UserNetWork;
import com.shandian.lu.R;
/*import CommonSharePopWindowActivity;*/
import com.zhyan.shandiankuaiyunlib.Bean.MyMessageBean;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ImageView.RoundImageView;



import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by az on 2017/4/26.
 */

public class MainMineController extends BaseController{

    private LianXiKeFuDialog lianXiKeFuDialog;
    private String nick = "",sex="",myInviteCode = "",wz="",youxiang="",wx="",qq="";

    @BindView(R.id.lly_main_mine_login_or_update_gerenxinxi)
    LinearLayout llyMainMineLoginOrUpdateGeRenXinXi;
    @OnClick(R.id.lly_main_mine_login_or_update_gerenxinxi)
    public void llyMainMineLoginOrUpdateGeRenXinXiOnclick(){
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

    @BindView(R.id.lly_main_mine_renzheng)
    LinearLayout llyMainMineRenZheng;
    @OnClick(R.id.lly_main_mine_renzheng)
    public void llyMainMineRenZhengOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
          /*  Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();*/
            view.getContext().startActivity(new Intent(view.getContext(),LoginActivity.class));
            return;
        }
        Intent intent = new Intent(view.getContext(), RenZhengActivity.class);
        view.getContext().startActivity(intent);
    }
    @BindView(R.id.lly_main_mine_wodecheyuan)
    LinearLayout llyMainMineWoDeCheYuan;
    @OnClick(R.id.lly_main_mine_wodecheyuan)
    public void llyMainMineWoDeCheYuanOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            /*Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();*/
            view.getContext().startActivity(new Intent(view.getContext(),LoginActivity.class));
            return;
        }
        Intent intent = new Intent(view.getContext(), WoDeCheYuanActivity.class);
        view.getContext().startActivity(intent);
    }
    @BindView(R.id.lly_main_mine_wodehuoyuan)
    LinearLayout llyMainMineWoDeHuoYuan;
    @OnClick(R.id.lly_main_mine_wodehuoyuan)
    public void llyMainMineWoDeHuoYuanOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
      /*      Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();*/
            view.getContext().startActivity(new Intent(view.getContext(),LoginActivity.class));
            return;
        }
        Intent intent = new Intent(view.getContext(), WoDeHuoYuanActivity.class);
        view.getContext().startActivity(intent);
    }
    @BindView(R.id.lly_main_mine_wodeyaoqing)
    LinearLayout llyMainMineWoDeYaoQing;
    @OnClick(R.id.lly_main_mine_wodeyaoqing)
    public void llyMainMineWoDeYaoQingOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
        /*    Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();*/
            view.getContext().startActivity(new Intent(view.getContext(),LoginActivity.class));
            return;
        }
        Intent intent = new Intent(view.getContext(), WoDeYaoQingActivity.class);
/*        view.getContext().startActivity(intent);*/
    }



    @BindView(R.id.lly_main_mine_mengyou)
    LinearLayout llyMainMineMengYou;
    @OnClick(R.id.lly_main_mine_mengyou)
    public void llyMainMineMengYouOnclick(){
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
    @BindView(R.id.lly_main_mine_jifenshangcheng)
    LinearLayout llyMainMineJiFenShangCheng;
    @OnClick(R.id.lly_main_mine_jifenshangcheng)
    public void llyMainMineJiFenShangChengOnclick(){
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
    @BindView(R.id.lly_main_mine_shezhi)
    LinearLayout llyMainMineSheZhi;
    @OnClick(R.id.lly_main_mine_shezhi)
    public void llyMainMineSheZhiOnclick(){
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
    @BindView(R.id.lly_main_mine_aboutus)
    LinearLayout llyMainMineAboutUs;
    @OnClick(R.id.lly_main_mine_aboutus)
    public void llyMainMineAboutUsOnclick(){
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

    @BindView(R.id.tv_main_mine_name)
    TextView tvMainMineName;
    @BindView(R.id.tv_main_mine_tel)
    TextView tvMainMineTel;
    @BindView(R.id.riv_main_mine_headimg)
    RoundImageView rivMainMinHeadImg;
    @BindView(R.id.lly_main_mine_lianxikefu)
    LinearLayout llyMainMineLianXiKeFu;
    @OnClick(R.id.lly_main_mine_lianxikefu)
    public void llyMainMineLianXiKeFuOnclick(){
        lianxikefuCall();
    }


    private ImageLoader loader;
    public MainMineController(View view1){
        view = view1;
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
            rivMainMinHeadImg.setImageResource(R.mipmap.mylogin);
            tvMainMineName.setText("");
            tvMainMineTel.setText("");
            return;
        }
        if((loginStatus != null)&&(loginStatus.equals("yes"))){
            String userName = xcCacheManager.readCache(xcCacheSaveName.userName);
            String userTel = xcCacheManager.readCache(xcCacheSaveName.userTel);
            String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
            if(userName!= null){
                tvMainMineName.setText(userName);
            }
            if(userTel != null){
                tvMainMineTel.setText(userTel);
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
                    loader.displayImage(myMessageBean.getContent().getPath(),rivMainMinHeadImg, ImageLoaderUtils.options1);
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
