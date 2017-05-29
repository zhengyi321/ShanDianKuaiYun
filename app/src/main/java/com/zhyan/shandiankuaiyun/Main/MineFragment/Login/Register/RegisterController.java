package com.zhyan.shandiankuaiyun.Main.MineFragment.Login.Register;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
/*
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;*/
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.RegisterSendIdentifyDialog;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.PhoneFormatCheckUtils;
import com.zhyan.shandiankuaiyun.BaseController;
import com.zhyan.shandiankuaiyun.Main.MineFragment.Login.Register.UserData.UserDataActivity;
import com.zhyan.shandiankuaiyun.NetWork.UserNetWork;
import com.zhyan.shandiankuaiyun.R;
import com.zhyan.shandiankuaiyunlib.Bean.IsRegisterBean;


import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import rx.Observer;

/**
 * Created by az on 2017/4/27.
 */

public class RegisterController extends BaseController {

    @BindView(R.id.rly_main_mine_login_reg_topbar_cancel)
    RelativeLayout rlyMainMineLoginRegTopBarCancel;
    @OnClick(R.id.rly_main_mine_login_reg_topbar_cancel)
    public void rlyMainMineLoginRegTopBarCancelOnclick(){
        activity.finish();
    }

    @BindView(R.id.bt_main_mine_login_reg_content_submit)
    Button btMainMineLoginRegContentSubmit;
    @OnClick(R.id.bt_main_mine_login_reg_content_submit)
    public void btMainMineLoginRegContentSubmitOnclick(){

        regNow();
    }

    @BindView(R.id.et_main_mine_login_reg_content_tel)
    EditText etMainMineLoginRegContentTel;
    @BindView(R.id.cb_main_mine_login_reg_content_agree)
    CheckBox cbMainMineLoginRegContentAgree;
    RegisterSendIdentifyDialog registerSendIdentifyDialog;

    public RegisterController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }

    private void regNow(){
        PhoneFormatCheckUtils phoneFormatCheckUtils = new PhoneFormatCheckUtils();
        String tel = etMainMineLoginRegContentTel.getText().toString();
        if((!phoneFormatCheckUtils.isNumber(tel))||(tel.length() != 11)){
            Toast.makeText(activity,"请输入正确的手机号",Toast.LENGTH_LONG).show();
            return;
        }
        if(!cbMainMineLoginRegContentAgree.isChecked()){
            Toast.makeText(activity,"请仔细阅读协议并勾选下方协议！",Toast.LENGTH_LONG).show();
            return;
        }


        UserNetWork userNetWork = new UserNetWork();
        userNetWork.isRegisterByNet(tel, new Observer<IsRegisterBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(IsRegisterBean isRegisterBean) {
                if(isRegisterBean.getStatus() == 0){
                    Toast.makeText(activity,"该手机号已经被注册",Toast.LENGTH_LONG).show();
                }else{
                    String tel = etMainMineLoginRegContentTel.getText().toString();
                    registerSendIdentifyDialog = new RegisterSendIdentifyDialog(activity,tel).Build.setNegativeButton("取消",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dissmissDialog();
                        }
                    }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dissmissDialog();
                        }
                    }).setCallBackListener(new RegisterSendIdentifyDialog.DialogCallBackListener() {
                        @Override
                        public void callBack(String tel) {
                            SMSSDK.getVerificationCode("86", tel);
                            Toast.makeText(activity,"验证码已发送",Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(activity, UserDataActivity.class);
                            intent.putExtra("tel",tel);
                            activity.startActivity(intent);
                        }
                    }).build(activity);
                    showDialog();
                }
            }
        });

    }


    public void showDialog() {
        if (registerSendIdentifyDialog != null && !registerSendIdentifyDialog.isShowing())
            registerSendIdentifyDialog.show();
    }

    public void dissmissDialog() {
        if (registerSendIdentifyDialog != null && registerSendIdentifyDialog.isShowing())
            registerSendIdentifyDialog.dismiss();
    }
}
