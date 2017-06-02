package com.shandian.lu.Main.MineFragment.SheZhi.XiuGaiMiMa;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.RegisterSendIdentifyDialog;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.PhoneFormatCheckUtils;
import com.shandian.lu.BaseController;
import com.shandian.lu.NetWork.AccountNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.UpdatePassBean;

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

public class XiuGaiMiMaController extends BaseController {
    private boolean isTimeBegin = false;
    RegisterSendIdentifyDialog registerSendIdentifyDialog;

    @BindView(R.id.rly_main_mine_shezhi_xiugaimima_topbar_cancel)
    RelativeLayout rlyMainMineSheZhiXiuGaiMiMaTopBarCancel;

    @OnClick(R.id.rly_main_mine_shezhi_xiugaimima_topbar_cancel)
    public void rlyMainMineSheZhiXiuGaiMiMaTopBarCancelOnclick(){
        activity.finish();
    }

    @BindView(R.id.et_main_mine_shezhi_xiugaimima_content_tel)
    EditText etMainMineSheZhiXiuGaiMiMaContentTel;
    @BindView(R.id.et_main_mine_shezhi_xiugaimima_content_identify)
    EditText etMainMineSheZhiXiuGaiMiMaContentIdentify;
    @BindView(R.id.et_main_mine_shezhi_xiugaimima_content_old_pass)
    EditText etMainMineSheZhiXiuGaiMiMaContentOldPass;
    @BindView(R.id.et_main_mine_shezhi_xiugaimima_content_new_pass)
    EditText etMainMineSheZhiXiuGaiMiMaContentNewPass;
    @BindView(R.id.et_main_mine_shezhi_xiugaimima_content_new_pass_again)
    EditText etMainMineSheZhiXiuGaiMiMaContentNewPassAgain;
    @BindView(R.id.tv_main_mine_shezhi_xiugaimima_content_get_identify)
    TextView tvMainMineGeRenXinXiXiuGaiMiMaGetIdentify;
    @BindView(R.id.rly_main_mine_shezhi_xiugaimima_content_get_identify)
    RelativeLayout rlyMainMineSheZhiXiuGaiMiMaContentGetIdentify;
    @OnClick(R.id.rly_main_mine_shezhi_xiugaimima_content_get_identify)
    public void rlyMainMineSheZhiXiuGaiMiMaContentGetIdentifyOnclick(){
        getIdentify();
    }
    @BindView(R.id.rly_main_mine_shezhi_xiugaimima_content_submit)
    RelativeLayout rlyMainMineSheZhiXiuGaiMiMaContentSubmit;
    @OnClick(R.id.rly_main_mine_shezhi_xiugaimima_content_submit)
    public void rlyMainMineSheZhiXiuGaiMiMaContentSubmitOnclick(){
        checkPassData();
    }
    private int time = 60;
    Handler handler = new Handler(){
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    if(time == 1){
                        tvMainMineGeRenXinXiXiuGaiMiMaGetIdentify.setText("获取验证码");
                        rlyMainMineSheZhiXiuGaiMiMaContentGetIdentify.setEnabled(true);
                        isTimeBegin = false;
                        time = 60;
                    }else {
                        time--;
                        tvMainMineGeRenXinXiXiuGaiMiMaGetIdentify.setText("(" + time + ")");
                    }

                    break;
                case 2:
                    Toast.makeText(activity.getBaseContext(), "验证码输入错误", Toast.LENGTH_LONG).show();
                    break;
            }
            super.handleMessage(msg);
        }

    };
    public XiuGaiMiMaController(Activity activity1){
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
        String tel = etMainMineSheZhiXiuGaiMiMaContentTel.getText().toString();
        if((!phoneFormatCheckUtils.isNumber(tel))||(tel.length() != 11)){
            Toast.makeText(activity,"请输入正确的手机号",Toast.LENGTH_LONG).show();
            return;
        }
        if(isTimeBegin) {
            rlyMainMineSheZhiXiuGaiMiMaContentGetIdentify.setEnabled(false);
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
                    /*rlyMainMineGeRenXinXiXiuGaiXinXiGetIdentify.setEnabled(false);*/

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
        Log.i("result",result+"");
        Log.i("result",result+"");
        Log.i("result",result+"");
        Log.i("result",result+"");
        Log.i("result",result+"");
        //回调完成
        if (result == SMSSDK.RESULT_COMPLETE)
        {
            //验证码验证成功
            if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE)
            {
         /*       regSubmit();*/
                updatePassToNet();
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

    private void checkPassData(){
        PhoneFormatCheckUtils phoneFormatCheckUtils = new PhoneFormatCheckUtils();
        String tel = etMainMineSheZhiXiuGaiMiMaContentTel.getText().toString();
        if((!phoneFormatCheckUtils.isNumber(tel))||(tel.length() != 11)){
            Toast.makeText(activity,"请输入正确的手机号",Toast.LENGTH_LONG).show();
            return;
        }
        String identify = etMainMineSheZhiXiuGaiMiMaContentIdentify.getText().toString().trim();
        if((identify == null)||(identify.isEmpty())){
            Toast.makeText(activity,"请输入验证码",Toast.LENGTH_LONG).show();
            return;
        }


        String pass = etMainMineSheZhiXiuGaiMiMaContentOldPass.getText().toString().trim();
        if((pass == null)||(pass.isEmpty())){
            Toast.makeText(activity,"请输入旧密码",Toast.LENGTH_LONG).show();
            return;
        }
        String new_pass = etMainMineSheZhiXiuGaiMiMaContentNewPass.getText().toString().trim();
        if((new_pass == null)||(new_pass.isEmpty())){
            Toast.makeText(activity,"请输入新密码",Toast.LENGTH_LONG).show();
            return;
        }
        String repeat_pass = etMainMineSheZhiXiuGaiMiMaContentNewPassAgain.getText().toString().trim();
        if((repeat_pass == null)||(repeat_pass.isEmpty())){
            Toast.makeText(activity,"请再次输入新密码",Toast.LENGTH_LONG).show();
            return;
        }
        if(!new_pass.equals(repeat_pass)){
            Toast.makeText(activity,"两次输入的新密码不一致",Toast.LENGTH_LONG).show();
            return;
        }
        SMSSDK.submitVerificationCode("86", tel, identify);//对验证码进行验证->回调函数
    }


    private Map<String,String> getParamMap(){
        Map<String,String> paramMap = new HashMap<>();
        String mobile = etMainMineSheZhiXiuGaiMiMaContentTel.getText().toString().trim();
        if(mobile == null){
            mobile = "";
        }else{
            mobile = mobile.replaceAll(" ","");
        }
        paramMap.put("mobile",mobile);
        String new_password = etMainMineSheZhiXiuGaiMiMaContentNewPass.getText().toString().trim();
        if(new_password == null){
            new_password = "";
        }else{
            new_password = new_password.replaceAll(" ","");
        }
        paramMap.put("new_password",new_password);
        String password = etMainMineSheZhiXiuGaiMiMaContentOldPass.getText().toString().trim();
        if(password == null){
            password = "";
        }else{
            password = password.replaceAll(" ","");
        }
        paramMap.put("password",password);
        String repeat_password = etMainMineSheZhiXiuGaiMiMaContentNewPassAgain.getText().toString().trim();
        if(repeat_password == null){
            repeat_password = "";
        }else{
            repeat_password = repeat_password.replaceAll(" ","");
        }
        paramMap.put("repeat_password",repeat_password);
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId).trim();
        if(login_id == null){
            login_id = "";
        }else{
            login_id = login_id.replaceAll(" ","");
        }
        paramMap.put("login_id",login_id);
        return paramMap;
    }

    private void updatePassToNet(){
        AccountNetWork accountNetWork = new AccountNetWork();
        accountNetWork.updatePassToNet(getParamMap(), new Observer<UpdatePassBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(UpdatePassBean updatePassBean) {
                Toast.makeText(activity,updatePassBean.getMsg(),Toast.LENGTH_LONG).show();
            }
        });
    }
    public void onDestroy(){

        SMSSDK.unregisterAllEventHandler();


    }
}
