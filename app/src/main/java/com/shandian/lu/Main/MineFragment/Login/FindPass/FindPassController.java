package com.shandian.lu.Main.MineFragment.Login.FindPass;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.shandian.lu.Main.MineFragment.Login.FindPass.ResetPass.ResetPassActivity;
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.RegisterSendIdentifyDialog;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.PhoneFormatCheckUtils;
import com.shandian.lu.BaseController;
import com.shandian.lu.R;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

/**
 * Created by az on 2017/4/27.
 */

public class FindPassController extends BaseController{

    @BindView(R.id.rly_main_mine_login_findpass_topbar_cancel)
    RelativeLayout rlyMainMineLoginResetPassTopBarCancel;
    @OnClick(R.id.rly_main_mine_login_findpass_topbar_cancel)
    public void rlyMainMineLoginResetPassTopBarCancelOnclick(){
        activity.finish();
    }

    @BindView(R.id.et_main_mine_login_findpass_content_tel)
    EditText etMainMineLoginFindPassContentTel;
    @BindView(R.id.et_main_mine_login_findpass_content_identify)
    EditText etMainMineLoginFindPassContentIdentify;
    @BindView(R.id.tv_main_mine_login_findpass_content_get_identify)
    TextView tvMainMineLoginFindPassContentGetIdentify;
    @BindView(R.id.rly_main_mine_login_findpass_content_get_identify)
    RelativeLayout rlyMainMineLoginFindPassContentGetIdentify;
    @OnClick(R.id.rly_main_mine_login_findpass_content_get_identify)
    public void rlyMainMineLoginFindPassContentGetIdentifyOnclick(){
        getIdentify();
    }
    @BindView(R.id.rly_main_mine_login_findpass_content_submit)
    RelativeLayout rlyMainMineLoginFindPassContentSubmit;
    @OnClick(R.id.rly_main_mine_login_findpass_content_submit)
    public void  rlyMainMineLoginFindPassContentSubmitOnclick(){
        submitUpdateTel();
    }
    private int time = 60;
    private boolean isTimeBegin = false;
    RegisterSendIdentifyDialog registerSendIdentifyDialog;
    Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if(time == 1){
                        tvMainMineLoginFindPassContentGetIdentify.setText("获取验证码");
                        rlyMainMineLoginFindPassContentGetIdentify.setEnabled(true);
                        isTimeBegin = false;
                        time = 60;
                    }else {
                        time--;
                        tvMainMineLoginFindPassContentGetIdentify.setText("(" + time + ")");
                    }

                    break;
                case 2:
                    Toast.makeText(activity.getBaseContext(), "验证码输入错误", Toast.LENGTH_LONG).show();
                    break;
            }
            super.handleMessage(msg);
        }

    };
    public FindPassController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        mobSMSRegister();
    }



    private void getIdentify(){

        PhoneFormatCheckUtils phoneFormatCheckUtils = new PhoneFormatCheckUtils();
        String tel = etMainMineLoginFindPassContentTel.getText().toString();
        if((!phoneFormatCheckUtils.isNumber(tel))||(tel.length() != 11)){
            Toast.makeText(activity,"请输入正确的手机号",Toast.LENGTH_LONG).show();
            return;
        }
        if(isTimeBegin) {
            rlyMainMineLoginFindPassContentGetIdentify.setEnabled(false);
            return;
        }
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
                Thread timesStartThread = new TimesStartThread();
                timesStartThread.start();
            }
        }).build(activity);
        showDialog();
        /*Toast.makeText(activity,"验证码已发送",Toast.LENGTH_LONG).show();*/

       /* SMSSDK.getVerificationCode("86", tel);*/

    }
    public void showDialog() {
        if (registerSendIdentifyDialog != null && !registerSendIdentifyDialog.isShowing())
            registerSendIdentifyDialog.show();
    }

    public void dissmissDialog() {
        if (registerSendIdentifyDialog != null && registerSendIdentifyDialog.isShowing())
            registerSendIdentifyDialog.dismiss();
    }
    public class TimesStartThread extends Thread{

        @Override
        public void run(){
            for(int i=60;i>0;i--){
                try {
                    isTimeBegin = true;
                    /*rlyMainMineGeRenXinXiXiuGaiShouJiGetIdentify.setEnabled(false);*/

                    sleep(1000);
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                } catch (InterruptedException e) {


                }
            }


        }
    }
    private void mobSMSRegister(){
        EventHandler eventHandler = new EventHandler() {
            @Override
            public void afterEvent(int event, int result, Object data) {
                smsSDKResultComplete(event,result,data);
            }
        };
        // 注册回调监听接口
        SMSSDK.registerEventHandler(eventHandler);
    }


    public void smsSDKResultComplete(int event, int result, Object data){
        //回调完成
        if (result == SMSSDK.RESULT_COMPLETE)
        {
            //验证码验证成功
            if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE)
            {
         /*       regSubmit();*/
             /*   updateTelToNet();*/
             resetPass();
            }
            //已发送验证码
            else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE)
            {
                /*Toast.makeText(activity, "验证码已经发送", Toast.LENGTH_SHORT).show();*/

            } else
            {
                ((Throwable) data).printStackTrace();

            }
        }if(result==SMSSDK.RESULT_ERROR)
        {
            Log.i("this is error","验证码错误");
            Log.i("this is error","验证码错误");
            Log.i("this is error","验证码错误");
            Log.i("this is error","验证码错误");
            Log.i("this is error","验证码错误");
            Message message = new Message();
            message.what = 2;
            handler.sendMessage(message);
            try {
                Toast.makeText(activity, "验证码输入错误", Toast.LENGTH_LONG).show();
                Throwable throwable = (Throwable) data;
                throwable.printStackTrace();
                JSONObject object = new JSONObject(throwable.getMessage());
                String des = object.optString("detail");//错误描述
                int status = object.optInt("status");//错误代码
                if (status > 0 && !TextUtils.isEmpty(des)) {
                    Toast.makeText(activity, des, Toast.LENGTH_LONG).show();
                    return;
                }

            } catch (Exception e) {

                //do something
            }
        }
    }
    private void submitUpdateTel(){
        PhoneFormatCheckUtils phoneFormatCheckUtils = new PhoneFormatCheckUtils();
        String tel = etMainMineLoginFindPassContentTel.getText().toString();
        if((!phoneFormatCheckUtils.isNumber(tel))||(tel.length() != 11)){
            Toast.makeText(activity,"请输入正确的手机号",Toast.LENGTH_LONG).show();
            return;
        }
        String identify = etMainMineLoginFindPassContentIdentify.getText().toString();
        if(identify.isEmpty()){
            Toast.makeText(activity,"请输入验证码",Toast.LENGTH_LONG).show();
            return;
        }
        SMSSDK.submitVerificationCode("86", tel, identify);//对验证码进行验证->回调函数
    }

    private void resetPass(){
        String tel = etMainMineLoginFindPassContentTel.getText().toString();
        PhoneFormatCheckUtils phoneFormatCheckUtils = new PhoneFormatCheckUtils();
        if((!phoneFormatCheckUtils.isNumber(tel))||(tel.length() != 11)){
            Toast.makeText(activity,"请输入正确的手机号",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(activity, ResetPassActivity.class);
        intent.putExtra("mobile",tel);
        activity.startActivity(intent);
    }

    public void onDestroy(){

        SMSSDK.unregisterAllEventHandler();


    }

}
