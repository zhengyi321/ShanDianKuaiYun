package com.shandian.lu.Main.MineFragment.Login.Register.UserData;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
/*
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;*/
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.PhoneFormatCheckUtils;
import com.shandian.lu.BaseController;
import com.shandian.lu.NetWork.UserNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.RegisterBean;

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

public class UserDataController extends BaseController{


    @BindView(R.id.rly_main_mine_login_reg_userdata_topbar_cancel)
    RelativeLayout rlyMainMineLoginRegUserDataTopBarCancel;
    @OnClick(R.id.rly_main_mine_login_reg_userdata_topbar_cancel)
    public void rlyMainMineLoginRegUserDataTopBarCancelOnclick(){
        activity.finish();
    }

    @BindView(R.id.et_main_mine_login_reg_userdata_content_tel)
    EditText etMainMineLoginRegUserDataContentTel;
    @BindView(R.id.et_main_mine_login_reg_userdata_content_yzm)
    EditText etMainMineLoginRegUserDataContentYZM;
    @BindView(R.id.et_main_mine_login_reg_userdata_content_pass)
    EditText etMainMineLoginRegUserDataContentPass;
    @BindView(R.id.et_main_mine_login_reg_userdata_content_name)
    EditText etMainMineLoginRegUserDataContentName;
    @BindView(R.id.et_main_mine_login_reg_userdata_content_yqm)
    EditText etMainMineLoginRegUserDataContentYQM;
    @BindView(R.id.bt_main_mine_login_reg_userdata_content_reg_submit)
    Button btMainMineLoginRegUserDataContentRegSubmit;
    @OnClick(R.id.bt_main_mine_login_reg_userdata_content_reg_submit)
    public void btMainMineLoginRegUserDataContentRegSubmitOnclick(){
        regDataCheck();
    }


    public UserDataController(Activity activity1){
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
        Intent intent = activity.getIntent();
        String tel = intent.getStringExtra("tel");
        if(tel != null){
            etMainMineLoginRegUserDataContentTel.setText(tel);
        }
    }

    private void regDataCheck(){
        PhoneFormatCheckUtils phoneFormatCheckUtils = new PhoneFormatCheckUtils();
        String tel = etMainMineLoginRegUserDataContentTel.getText().toString();
        if((!phoneFormatCheckUtils.isNumber(tel))||(tel.length() != 11)){
            Toast.makeText(activity,"请输入正确的手机号",Toast.LENGTH_LONG).show();
            return;
        }
        String yanzhengma = etMainMineLoginRegUserDataContentYZM.getText().toString();
        if(yanzhengma.length() == 0){
            Toast.makeText(activity,"请输入验证码",Toast.LENGTH_LONG).show();
            return;
        }
        String pass = etMainMineLoginRegUserDataContentPass.getText().toString();
        if(pass.length() <= 6){
            Toast.makeText(activity,"请输入6位以上的安全密码",Toast.LENGTH_LONG).show();
            return;
        }
        String name = etMainMineLoginRegUserDataContentName.getText().toString();
        if(name.length() == 0){
            Toast.makeText(activity,"请输入用户名",Toast.LENGTH_LONG).show();
            return;
        }
        SMSSDK.submitVerificationCode("86", tel, yanzhengma);//对验证码进行验证->回调函数
    }

    private void regSubmit(){
        String tel = etMainMineLoginRegUserDataContentTel.getText().toString();
        String pass = etMainMineLoginRegUserDataContentPass.getText().toString();
        String name = etMainMineLoginRegUserDataContentName.getText().toString();
        String code = etMainMineLoginRegUserDataContentYQM.getText().toString();

/*
        try {
            EMClient.getInstance().createAccount(tel, pass);
        } catch (HyphenateException e) {
            e.printStackTrace();
            Toast.makeText(activity,"注册失败",Toast.LENGTH_LONG).show();
            return;
        }*/

        UserNetWork userNetWork = new UserNetWork();
        userNetWork.registerToNet(tel, pass, code, name, new Observer<RegisterBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(RegisterBean registerBean) {
                Toast.makeText(activity,registerBean.getMsg(),Toast.LENGTH_LONG).show();
                if(registerBean.getStatus() == 0) {
                    activity.finish();
                }
            }
        });
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
                regSubmit();

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
           /* Toast.makeText(activity, "验证码输入错误", Toast.LENGTH_LONG).show();*/
            try {
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

    public void onDestroy(){

        SMSSDK.unregisterAllEventHandler();


    }
}
