package com.shandian.lu.Main.MineFragment.GeRenXinXi;

import android.app.Activity;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.NewGeRenXinXiBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.Main.MineFragment.GeRenXinXi.BianJiNiChen.BianJiNiChenActivity;
import com.shandian.lu.Main.MineFragment.GeRenXinXi.QQ.QQActivity;
import com.shandian.lu.Main.MineFragment.GeRenXinXi.XiuGaiShouJi.XiuGaiShouJiActivity;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MineFragment.GeRenXinXi.ErWeiMa.ErWeiMaActivity;
import com.shandian.lu.Main.MineFragment.GeRenXinXi.WeiXin.WeiXinActivity;
import com.shandian.lu.Main.MineFragment.GeRenXinXi.WeiZhi.WeiZhiActivity;
import com.shandian.lu.Main.MineFragment.GeRenXinXi.XingBie.XingBieActivity;
import com.shandian.lu.Main.MineFragment.GeRenXinXi.Email.EmailActivity;
import com.shandian.lu.NetWork.UserNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.MyMessageBean;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ImageView.RoundImageView;

import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by az on 2017/5/4.
 */

public class GeRenXinXiController extends BaseController {
    /**打开相册，并截图*/
    public   final int INTENT_ACTION_PICTURE = 0;
    /**打开相机照相*/
    public   final int INTENT_ACTION_CAREMA = 1;
    public String picturePath;
    /**图片名字*/
    private  final String PICTURE_NAME = "userIcon.jpg";
    @BindView(R.id.riv_main_mine_gerenxinxi_content_headimg)
    RoundImageView rivMainMineGeRenXinXiContentHeadImg;
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

    @BindView(R.id.lly_main_mine_gerenxinxi_nichen)
    LinearLayout llyMainMineGeRenXinXiNiChen;
    @OnClick(R.id.lly_main_mine_gerenxinxi_nichen)
    public void llyMainMineGeRenXinXiNiChenOnclick(){
        Intent intent = new Intent(activity, BianJiNiChenActivity.class);
        String nick = tvMainMineGeRenXinXiContentNick.getText().toString();
        intent.putExtra("nick",nick);
        activity.startActivity(intent);
    };
    @BindView(R.id.lly_main_mine_gerenxinxi_xingbie)
    LinearLayout llyMainMineGeRenXinXiXingBie;
    @OnClick(R.id.lly_main_mine_gerenxinxi_xingbie)
    public void llyMainMineGeRenXinXiXingBieOnclick(){
        Intent intent = new Intent(activity, XingBieActivity.class);
        String sex = tvMainMineGeRenXinXiContentSex.getText().toString();
        intent.putExtra("sex",sex);
        activity.startActivity(intent);
    };
    @BindView(R.id.lly_main_mine_gerenxinxi_erweima)
    LinearLayout llyMainMineGeRenXinXiErWeiMa;
    @OnClick(R.id.lly_main_mine_gerenxinxi_erweima)
    public void llyMainMineGeRenXinXiErWeiMaOnclick(){
        Intent intent = new Intent(activity, ErWeiMaActivity.class);
        String inviteCode = tvMainMineGeRenXinXiContentMyInviteCode.getText().toString();
        intent.putExtra("inviteCode",inviteCode);
        activity.startActivity(intent);
    };
    @BindView(R.id.lly_main_mine_gerenxinxi_weizhi)
    LinearLayout llyMainMineGeRenXinXiWeiZhi;
    @OnClick(R.id.lly_main_mine_gerenxinxi_weizhi)
    public void llyMainMineGeRenXinXiWeiZhiOnclick(){
        Intent intent = new Intent(activity, WeiZhiActivity.class);
        activity.startActivity(intent);
    };

    @BindView(R.id.lly_main_mine_gerenxinxi_xiugaishouji)
    LinearLayout llyMainMineGeRenXinXiXiuGaiShouJi;
    @OnClick(R.id.lly_main_mine_gerenxinxi_xiugaishouji)
    public void llyMainMineGeRenXinXiXiuGaiShouJiOnclick(){
        String tel = tvMainMineGeRenXinXiContentTel.toString().trim();
        if(tel == null){
            tel = "";
        }else{
            tel = tel.replaceAll(" ","");
        }
        Intent intent = new Intent(activity, XiuGaiShouJiActivity.class);
        intent.putExtra("tel",tel);
        activity.startActivity(intent);
    };
    @BindView(R.id.lly_main_mine_gerenxinxi_youxiang)
    LinearLayout llyMainMineGeRenXinXiYouXiang;
    @OnClick(R.id.lly_main_mine_gerenxinxi_youxiang)
    public void llyMainMineGeRenXinXiYouXiangOnclick(){
        Intent intent = new Intent(activity, EmailActivity.class);
        activity.startActivity(intent);
    };
    @BindView(R.id.lly_main_mine_gerenxinxi_weixin)
    LinearLayout llyMainMineGeRenXinXiWeiXin;
    @OnClick(R.id.lly_main_mine_gerenxinxi_weixin)
    public void llyMainMineGeRenXinXiWeiXinOnclick(){
        Intent intent = new Intent(activity, WeiXinActivity.class);
        activity.startActivity(intent);
    };
    @BindView(R.id.lly_main_mine_gerenxinxi_qq)
    LinearLayout llyMainMineGeRenXinXiQQ;
    @OnClick(R.id.lly_main_mine_gerenxinxi_qq)
    public void llyMainMineGeRenXinXiQQOnclick(){
        Intent intent = new Intent(activity, QQActivity.class);
        activity.startActivity(intent);
    };
    @BindView(R.id.rly_main_mine_gerenxinxi_topbar_back)
    RelativeLayout rlyMainMineGeRenXinXiTopBarBack;
    @OnClick(R.id.rly_main_mine_gerenxinxi_topbar_back)
    public void rlyMainMineGeRenXinXiTopBarBackOnclick(){
        activity.finish();
    }



    private ImageLoader loader;
    public GeRenXinXiController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        loader=ImageLoader.getInstance();
        getNewDetailFromNet();
    /*    getMyMessageFromNet();*/
    }

    private void getNewDetailFromNet(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId).toString().trim();
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(activity,"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("login_id",login_id);
        UserNetWork userNetWork = new UserNetWork();
        userNetWork.getNewGeRenXinXiFromNet(paramMap, new Observer<NewGeRenXinXiBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewGeRenXinXiBean newGeRenXinXiBean) {
                initDetail(newGeRenXinXiBean);
            }
        });
    }


    public void getMyMessageFromNet(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId).toString().trim();
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(activity,"请登录",Toast.LENGTH_LONG).show();
            return;
        }

        UserNetWork userNetWork = new UserNetWork();
        userNetWork.getMyMessageFromNet(login_id, new Observer<MyMessageBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(MyMessageBean myMessageBean) {
                /*Toast.makeText(activity,"this is myMessageBeanSuccess",Toast.LENGTH_LONG).show();*/
                initMyMessage(myMessageBean);
                if(myMessageBean.getStatus() == 0){


                }
            }
        });
    }


    private  void initDetail(NewGeRenXinXiBean newGeRenXinXiBean){
        if(newGeRenXinXiBean.getNr().getNickename() != null){
            tvMainMineGeRenXinXiContentNick.setText(newGeRenXinXiBean.getNr().getNickename());
        }

        if(newGeRenXinXiBean.getNr().getSex().equals("1")){
            tvMainMineGeRenXinXiContentSex.setText("男");
        }else{
            tvMainMineGeRenXinXiContentSex.setText("女");
        }
        if(newGeRenXinXiBean.getNr().getOne_code() != null) {
            tvMainMineGeRenXinXiContentMyInviteCode.setText(newGeRenXinXiBean.getNr().getOne_code());
        }
        if(newGeRenXinXiBean.getNr().getAddress() != null) {
            tvMainMineGeRenXinXiContentWZ.setText(newGeRenXinXiBean.getNr().getAddress().toString());
        }
        if(newGeRenXinXiBean.getNr().getEmail() != null) {
            tvMainMineGeRenXinXiContentYouXiang.setText(newGeRenXinXiBean.getNr().getEmail().toString());
        }
        if(newGeRenXinXiBean.getNr().getWei_code() != null) {
            tvMainMineGeRenXinXiContentWX.setText(newGeRenXinXiBean.getNr().getWei_code().toString());
        }
        if(newGeRenXinXiBean.getNr().getQq_code() != null) {
            tvMainMineGeRenXinXiContentQQ.setText(newGeRenXinXiBean.getNr().getQq_code().toString() );
        }

        if(newGeRenXinXiBean.getNr().getImage() != null) {
            loader.displayImage(newGeRenXinXiBean.getNr().getImage(), rivMainMineGeRenXinXiContentHeadImg, ImageLoaderUtils.options1);
        }
        if(newGeRenXinXiBean.getNr().getQr_code() != null){
            loader.displayImage(newGeRenXinXiBean.getNr().getQr_code(), ivMainMineGeRenXinXiContentEWM, ImageLoaderUtils.options1);
        }
        if(newGeRenXinXiBean.getNr().getName() != null){
            tvMainMineGeRenXinXiContentName.setText(newGeRenXinXiBean.getNr().getName());
        }

        if(newGeRenXinXiBean.getNr().getMobile() != null){
            tvMainMineGeRenXinXiContentTel.setText(newGeRenXinXiBean.getNr().getMobile());
        }


        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();

        xcCacheManager.writeCache(xcCacheSaveName.userEWMUrl,newGeRenXinXiBean.getNr().getQr_code());
    }


    private void initMyMessage(MyMessageBean myMessageBean){
        if(myMessageBean.getContent().getNickename() != null){
            tvMainMineGeRenXinXiContentNick.setText(myMessageBean.getContent().getNickename());
        }

        if(myMessageBean.getContent().getSex().equals("1")){
            tvMainMineGeRenXinXiContentSex.setText("男");
        }else{
            tvMainMineGeRenXinXiContentSex.setText("女");
        }
        if(myMessageBean.getContent().getOne_code() != null) {
            tvMainMineGeRenXinXiContentMyInviteCode.setText(myMessageBean.getContent().getOne_code());
        }
        if(myMessageBean.getContent().getAddress() != null) {
            tvMainMineGeRenXinXiContentWZ.setText(myMessageBean.getContent().getAddress().toString());
        }
        if(myMessageBean.getContent().getEmail() != null) {
            tvMainMineGeRenXinXiContentYouXiang.setText(myMessageBean.getContent().getEmail().toString());
        }
        if(myMessageBean.getContent().getWei_code() != null) {
            tvMainMineGeRenXinXiContentWX.setText(myMessageBean.getContent().getWei_code().toString());
        }
        if(myMessageBean.getContent().getQq_code() != null) {
            tvMainMineGeRenXinXiContentQQ.setText(myMessageBean.getContent().getQq_code().toString() );
        }

        if(myMessageBean.getContent().getPath() != null) {
            loader.displayImage(myMessageBean.getContent().getPath(), rivMainMineGeRenXinXiContentHeadImg, ImageLoaderUtils.options1);
        }
        if(myMessageBean.getContent().getQr_code() != null){
            loader.displayImage(myMessageBean.getContent().getQr_code(), ivMainMineGeRenXinXiContentEWM, ImageLoaderUtils.options1);
        }
        if(myMessageBean.getContent().getName() != null){
            tvMainMineGeRenXinXiContentName.setText(myMessageBean.getContent().getName());
        }

        if(myMessageBean.getContent().getMobile() != null){
            tvMainMineGeRenXinXiContentTel.setText(myMessageBean.getContent().getMobile());
        }


    }

    public void onResume(){
        getNewDetailFromNet();
    }



}
