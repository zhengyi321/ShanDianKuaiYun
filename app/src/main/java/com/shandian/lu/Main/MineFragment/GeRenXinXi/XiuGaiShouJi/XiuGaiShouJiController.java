package com.shandian.lu.Main.MineFragment.GeRenXinXi.XiuGaiShouJi;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewGeRenXinXiSubmitBean;
import com.shandian.lu.Main.MineFragment.GeRenXinXi.GeRenXinXiActivity;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.NetWork.UserNetWork;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.RegisterSendIdentifyDialog;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.PhoneFormatCheckUtils;
import com.shandian.lu.BaseController;
import com.shandian.lu.NetWork.AccountNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.UpdateTelBean;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import rx.Observer;

/**
 * Created by az on 2017/5/22.
 */

public class XiuGaiShouJiController extends BaseController {

    private boolean isTimeBegin = false;
    RegisterSendIdentifyDialog registerSendIdentifyDialog;
    @BindView(R.id.rly_main_mine_gerenxinxi_xiugaishouji_back)
    RelativeLayout rlyMainMineGeRenXinXiXiuGaiShouJiBack;
    @BindView(R.id.pb_new_main_mine_gerenxinxi_xiugaishouji)
    ProgressBar pbNewMainMineGeRenXinXiXiuGaiShouJi;
    @OnClick(R.id.rly_main_mine_gerenxinxi_xiugaishouji_back)
    public void rlyMainMineGeRenXinXiXiuGaiShouJiBackOnclick(){
        activity.finish();
    }
    private String old_tel="";
    @BindView(R.id.et_main_mine_gerenxinxi_xiugaishouji_tel)
    EditText etMainMineGeRenXinXiXiuGaiShouJiTel;
    @BindView(R.id.et_main_mine_gerenxinxi_xiugaishouji_identify)
    EditText etMainMineGeRenXinXiXiuGaiShouJiIdentify;
    @BindView(R.id.tv_main_mine_gerenxinxi_xiugaishouji_get_identify)
    TextView tvMainMineGeRenXinXiXiuGaiShouJiGetIdentify;
    @BindView(R.id.rly_main_mine_gerenxinxi_xiugaishouji_get_identify)
    RelativeLayout rlyMainMineGeRenXinXiXiuGaiShouJiGetIdentify;
    @OnClick(R.id.rly_main_mine_gerenxinxi_xiugaishouji_get_identify)
    public void rlyMainMineGeRenXinXiXiuGaiShouJiGetIdentifyOnclick(){
        getIdentify();
    }
    @BindView(R.id.rly_main_mine_gerenxinxi_xiugaishouji_update_tel_submit)
    RelativeLayout rlyMainMineGeRenXinXiXiuGaiShouJiUpdateTelSubmit;

    @OnClick(R.id.rly_main_mine_gerenxinxi_xiugaishouji_update_tel_submit)
    public void rlyMainMineGeRenXinXiXiuGaiShouJiUpdateTelSubmitOnclick(){
        submitUpdateTel();
    }
    private int time = 60;
    Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if(time == 1){
                        tvMainMineGeRenXinXiXiuGaiShouJiGetIdentify.setText("获取验证码");
                        rlyMainMineGeRenXinXiXiuGaiShouJiGetIdentify.setEnabled(true);
                        isTimeBegin = false;
                        time = 60;
                    }else {
                        time--;
                        tvMainMineGeRenXinXiXiuGaiShouJiGetIdentify.setText("(" + time + ")");
                    }

                    break;
                case 2:
                    Toast.makeText(activity.getBaseContext(), "验证码输入错误", Toast.LENGTH_LONG).show();
                    break;
            }
            super.handleMessage(msg);
        }

    };

    public XiuGaiShouJiController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initTel();
        mobSMSRegister();
    }
    private void initTel(){
        old_tel = activity.getIntent().getStringExtra("tel");

    }

    private void submitUpdateTel(){
        PhoneFormatCheckUtils phoneFormatCheckUtils = new PhoneFormatCheckUtils();
        String tel = etMainMineGeRenXinXiXiuGaiShouJiTel.getText().toString();
        if((!phoneFormatCheckUtils.isNumber(tel))||(tel.length() != 11)){
            Toast.makeText(activity,"请输入正确的手机号",Toast.LENGTH_LONG).show();
            return;
        }
        String identify = etMainMineGeRenXinXiXiuGaiShouJiIdentify.getText().toString();
        if(identify.isEmpty()){
            Toast.makeText(activity,"请输入验证码",Toast.LENGTH_LONG).show();
            return;
        }
        SMSSDK.submitVerificationCode("86", tel, identify);//对验证码进行验证->回调函数
    }
    public void showDialog() {
        if (registerSendIdentifyDialog != null && !registerSendIdentifyDialog.isShowing())
            registerSendIdentifyDialog.show();
    }

    public void dissmissDialog() {
        if (registerSendIdentifyDialog != null && registerSendIdentifyDialog.isShowing())
            registerSendIdentifyDialog.dismiss();
    }

    private void getIdentify(){

        PhoneFormatCheckUtils phoneFormatCheckUtils = new PhoneFormatCheckUtils();
        String tel = etMainMineGeRenXinXiXiuGaiShouJiTel.getText().toString();
        if((!phoneFormatCheckUtils.isNumber(tel))||(tel.length() != 11)){
            Toast.makeText(activity,"请输入正确的手机号",Toast.LENGTH_LONG).show();
            return;
        }
        if(isTimeBegin) {
            rlyMainMineGeRenXinXiXiuGaiShouJiGetIdentify.setEnabled(false);
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
              /*  updateTelToNet();*/
                newUpdateTelToNet();
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
    private void updateTelToNet(){

        String new_tel = etMainMineGeRenXinXiXiuGaiShouJiTel.getText().toString().trim();
        if(new_tel == null){
            new_tel = "";
        }else{
            new_tel = new_tel.replaceAll(" ","");
        }
        AccountNetWork accountNetWork = new AccountNetWork();
        accountNetWork.updateTelToNet(old_tel, new_tel, new Observer<UpdateTelBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(UpdateTelBean updateTelBean) {
                Toast.makeText(activity,updateTelBean.getMsg(),Toast.LENGTH_LONG).show();
                activity.finish();
            }
        });

    }


    private void newUpdateTelToNet(){
        pbNewMainMineGeRenXinXiXiuGaiShouJi.setVisibility(View.VISIBLE);
        UserNetWork userNetWork = new UserNetWork();
        userNetWork.submitNewGeRenXinXiToNet(getNewParamMap(), new Observer<NewGeRenXinXiSubmitBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewGeRenXinXiSubmitBean newGeRenXinXiSubmitBean) {
                Toast.makeText(activity,newGeRenXinXiSubmitBean.getMsg(),3000).show();
                pbNewMainMineGeRenXinXiXiuGaiShouJi.setVisibility(View.GONE);
            }
        });

    }
    private Map<String,Object>  getNewParamMap(){
        Map<String,Object> paramMap = new HashMap<>();
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        String loginId= xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            Toast.makeText(activity,"请登录",3000).show();
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            return paramMap;
        }

        paramMap.put("login_id",loginId);
        String new_tel = etMainMineGeRenXinXiXiuGaiShouJiTel.getText().toString().trim();
        new_tel = new_tel.replaceAll(" ","");
        paramMap.put("iphone",new_tel);
        return paramMap;
    }

    public void onDestroy(){

        SMSSDK.unregisterAllEventHandler();


    }
}
