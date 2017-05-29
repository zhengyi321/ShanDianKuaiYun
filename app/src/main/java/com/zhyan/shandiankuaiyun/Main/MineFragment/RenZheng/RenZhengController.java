package com.zhyan.shandiankuaiyun.Main.MineFragment.RenZheng;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.LianXiKeFuDialog;
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.RegisterSendIdentifyDialog;
import com.zhyan.shandiankuaiyun.BaseController;
import com.zhyan.shandiankuaiyun.Main.MineFragment.Login.Register.UserData.UserDataActivity;
import com.zhyan.shandiankuaiyun.Main.MineFragment.RenZheng.CheLiangRenZheng.CheLiangRenZhengActivity;
import com.zhyan.shandiankuaiyun.Main.MineFragment.RenZheng.JiaShiZhengRenZheng.JiaShiZhengRenZhengActivity;
import com.zhyan.shandiankuaiyun.Main.MineFragment.RenZheng.QiYeRenZheng.QiYeRenZhengActivity;
import com.zhyan.shandiankuaiyun.Main.MineFragment.RenZheng.ShiMingRenZheng.ShiMingRenZhengActivity;
import com.zhyan.shandiankuaiyun.Main.MineFragment.RenZheng.XingShiZhengRenZheng.XingShiZhengRenZhengActivity;
import com.zhyan.shandiankuaiyun.NetWork.RenZhengNetWork;
import com.zhyan.shandiankuaiyun.R;
import com.zhyan.shandiankuaiyunlib.Bean.AuthInFoBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.SMSSDK;
import rx.Observer;

/**
 * Created by az on 2017/5/3.
 */

public class RenZhengController extends BaseController {

    private LianXiKeFuDialog lianXiKeFuDialog;
    @BindView(R.id.bt_main_mine_renzheng_shimingrenzheng)
    Button btMainMineRenZhengShiMingRenZheng;

    @OnClick(R.id.bt_main_mine_renzheng_shimingrenzheng)
    public void btMainMineRenZhengShiMingRenZhengOnclick() {
        Intent intent = new Intent(activity, ShiMingRenZhengActivity.class);
        activity.startActivity(intent);
    }

    @BindView(R.id.bt_main_mine_renzheng_qiyerenzheng)
    Button btMainMineRenZhengQiYeRenZheng;

    @OnClick(R.id.bt_main_mine_renzheng_qiyerenzheng)
    public void btMainMineRenZhengQiYeRenZhengOnclick() {
        Intent intent = new Intent(activity, QiYeRenZhengActivity.class);
        activity.startActivity(intent);
    }

    @BindView(R.id.bt_main_mine_renzheng_jiashizhengrenzheng)
    Button btMainMineRenZhengJiaShiZhengRenZheng;

    @OnClick(R.id.bt_main_mine_renzheng_jiashizhengrenzheng)
    public void btMainMineRenZhengJiaShiZhengRenZhengOnclick() {
        Intent intent = new Intent(activity, JiaShiZhengRenZhengActivity.class);
        activity.startActivity(intent);
    }

    @BindView(R.id.bt_main_mine_renzheng_xingshizhengrenzheng)
    Button btMainMineRenZhengXingShiZhengRenZheng;

    @OnClick(R.id.bt_main_mine_renzheng_xingshizhengrenzheng)
    public void btMainMineRenZhengXingShiZhengRenZhengOnclick() {
        Intent intent = new Intent(activity, XingShiZhengRenZhengActivity.class);
        activity.startActivity(intent);
    }


    @BindView(R.id.rly_main_mine_renzheng_back)
    RelativeLayout rlyMainMineRenZhengBack;
    @OnClick(R.id.rly_main_mine_renzheng_back)
    public void rlyMainMineRenZhengBackOnclick(){
        activity.finish();
    }

    @BindView(R.id.bt_main_mine_renzheng_cheliangrenzheng)
    Button btMainMineRenZhengCheLiangRenZheng;

    @OnClick(R.id.bt_main_mine_renzheng_cheliangrenzheng)
    public void btMainMineRenZhengCheLiangRenZhengOnclick() {
        Intent intent = new Intent(activity, CheLiangRenZhengActivity.class);
        activity.startActivity(intent);
    }

    @BindView(R.id.rly_main_mine_renzheng_lianxikefu)
    RelativeLayout rlyMainMineLianXiKeFu;

    @OnClick(R.id.rly_main_mine_renzheng_lianxikefu)
    public void rlyMainMineLianXiKeFuOnclick() {
        lianxikefuCall();
    }

    public RenZhengController(Activity activity1) {
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this, activity);
        initAuthInFoFromNet();
    }


    private void lianxikefuCall() {
        lianXiKeFuDialog = new LianXiKeFuDialog(activity).Build.setNegativeButton("取消", new DialogInterface.OnClickListener() {
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
        }).build(activity);
        showDialog();
    }

    public void showDialog() {
        if (lianXiKeFuDialog != null && !lianXiKeFuDialog.isShowing())
            lianXiKeFuDialog.show();
    }

    public void dissmissDialog() {
        if (lianXiKeFuDialog != null && lianXiKeFuDialog.isShowing())
            lianXiKeFuDialog.dismiss();
    }

    private void startCallTel(String number) {
        /*PhoneFormatCheckUtils phoneFormatCheckUtils = new PhoneFormatCheckUtils();
        if((number != null)&&(phoneFormatCheckUtils.IsNumber(number))) {*/
        //用intent启动拨打电话
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));

        activity.startActivity(intent);
       /* }*/
    }

    private void initAuthInFoFromNet(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if(login_id == null){
            return;
        }
        RenZhengNetWork renZhengNetWork = new RenZhengNetWork();
        renZhengNetWork.getAuthInfoFromNet(login_id, new Observer<AuthInFoBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(AuthInFoBean authInFoBean) {
                initButtonStatus(authInFoBean);
            }
        });
    }

    private void initButtonStatus(AuthInFoBean authInFoBean){
        if(authInFoBean.getContent().getCar_status() == 1){
            btMainMineRenZhengCheLiangRenZheng.setText("已通过");
            btMainMineRenZhengCheLiangRenZheng.setEnabled(false);
        }else if(authInFoBean.getContent().getUser_is_auth() == 1){
            btMainMineRenZhengShiMingRenZheng.setText("已通过");
            btMainMineRenZhengShiMingRenZheng.setEnabled(false);
        }else if(authInFoBean.getContent().getCompany_is_auth() == 1){
            btMainMineRenZhengQiYeRenZheng.setText("已通过");
            btMainMineRenZhengQiYeRenZheng.setEnabled(false);
        }else if(authInFoBean.getContent().getDrive_status().equals("1")){
            btMainMineRenZhengJiaShiZhengRenZheng.setText("已通过");
            btMainMineRenZhengJiaShiZhengRenZheng.setEnabled(false);
        }else if(authInFoBean.getContent().getTravel_status().equals("1")){
            btMainMineRenZhengXingShiZhengRenZheng.setText("已通过");
            btMainMineRenZhengXingShiZhengRenZheng.setEnabled(false);
        }


        if(authInFoBean.getContent().getCar_status() == 2){
            btMainMineRenZhengCheLiangRenZheng.setText("未通过");
        }else if(authInFoBean.getContent().getUser_is_auth() == 2){
            btMainMineRenZhengShiMingRenZheng.setText("未通过");
        }else if(authInFoBean.getContent().getCompany_is_auth() == 2){
            btMainMineRenZhengQiYeRenZheng.setText("未通过");
        }else if(authInFoBean.getContent().getDrive_status().equals("2")){
            btMainMineRenZhengJiaShiZhengRenZheng.setText("未通过");
        }else if(authInFoBean.getContent().getTravel_status().equals("2")){
            btMainMineRenZhengXingShiZhengRenZheng.setText("未通过");
        }
        if(authInFoBean.getContent().getCar_status() == 3){
            btMainMineRenZhengCheLiangRenZheng.setText("审核中");
            btMainMineRenZhengCheLiangRenZheng.setEnabled(false);
        }else if(authInFoBean.getContent().getUser_is_auth() == 3){
            btMainMineRenZhengShiMingRenZheng.setText("审核中");
            btMainMineRenZhengShiMingRenZheng.setEnabled(false);
        }else if(authInFoBean.getContent().getCompany_is_auth() == 3){
            btMainMineRenZhengQiYeRenZheng.setText("审核中");
            btMainMineRenZhengQiYeRenZheng.setEnabled(false);
        }else if(authInFoBean.getContent().getDrive_status().equals("3")){
            btMainMineRenZhengJiaShiZhengRenZheng.setText("审核中");
            btMainMineRenZhengJiaShiZhengRenZheng.setEnabled(false);
        }else if(authInFoBean.getContent().getTravel_status().equals("3")){
            btMainMineRenZhengXingShiZhengRenZheng.setText("审核中");
            btMainMineRenZhengXingShiZhengRenZheng.setEnabled(false);
        }
    }
}
