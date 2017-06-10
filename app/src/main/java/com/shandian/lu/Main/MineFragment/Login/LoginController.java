package com.shandian.lu.Main.MineFragment.Login;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
/*
import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;*/
import com.hyphenate.EMCallBack;
import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;
import com.shandian.lu.Main.MineFragment.Login.FindPass.FindPassActivity;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.PhoneFormatCheckUtils;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MineFragment.Login.Register.RegisterActivity;
import com.shandian.lu.NetWork.UserNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.LoginBean;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import rx.Observer;

/**
 * Created by az on 2017/4/26.
 */

public class LoginController extends BaseController {
    /*极光别名注册*/
    private final int MSG_SET_ALIAS = 1001;
    protected final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:

                    JPushInterface.setAliasAndTags(activity, (String) msg.obj, null, mAliasCallback);
                    break;



                default:

            }
        }
    };
    /*极光别名注册*/
    private String loginId= "",name="",mobile="";
    @BindView(R.id.rly_main_mine_login_cancel)
    RelativeLayout rlyMainMineLoginCancel;
    @OnClick(R.id.rly_main_mine_login_cancel)
    public void rlyMainMineLoginCancelOnclick(){
        activity.finish();
    }
    @BindView(R.id.rly_main_mine_login_register_now)
    RelativeLayout rlyMainMineLoginRegisterNow;
    @OnClick(R.id.rly_main_mine_login_register_now)
    public void rlyMainMineLoginRegisterNowOnclick(){
        Intent intent = new Intent(activity, RegisterActivity.class);
        activity.startActivity(intent);
    }
    @BindView(R.id.tv_main_mine_login_resetpass)
    TextView tvMainMineLoginResetPass;
    @OnClick(R.id.tv_main_mine_login_resetpass)
    public void tvMainMineLoginResetPassOnclick(){
        Intent intent = new Intent(activity, FindPassActivity.class);
        activity.startActivity(intent);
    }

    @BindView(R.id.bt_main_mine_login_submit)
    Button btMainMineLoginSubmit;
    @OnClick(R.id.bt_main_mine_login_submit)
    public void btMainMineLoginSubmitOnclick(){
        login();
    }

    @BindView(R.id.et_main_mine_login_tel)
    EditText etMainMineLoginTel;
    @BindView(R.id.et_main_mine_login_pas)
    EditText etMainMineLoginPas;

    public LoginController(Activity activity1){
        activity = activity1;
        init();
    }
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message message){
            switch (message.what){
                case 1:
                    Toast.makeText(activity,"登录成功",Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }


    private void login(){
        PhoneFormatCheckUtils phoneFormatCheckUtils = new PhoneFormatCheckUtils();
        String tel = etMainMineLoginTel.getText().toString();
        String pass = etMainMineLoginPas.getText().toString();
        if((!phoneFormatCheckUtils.isNumber(tel))||(tel.length() != 11)){
            Toast.makeText(activity,"请输入正确的手机号",Toast.LENGTH_LONG).show();
            return;
        }
        if(pass.length() == 0){
            Toast.makeText(activity,"请输入密码",Toast.LENGTH_LONG).show();
            return;
        }
       loginHuanXin(tel,pass);


    }


    private void loginHuanXin(final String tel,final String pass){

        UserNetWork userNetWork = new UserNetWork();
        userNetWork.userLogin(tel, pass, new Observer<LoginBean>() {
            @Override
            public void onCompleted() {
              /*  Toast.makeText(activity,"onCompleted:",Toast.LENGTH_LONG).show();*/
            }

            @Override
            public void onError(Throwable e) {
                /*Toast.makeText(activity,"onError:"+e,Toast.LENGTH_LONG).show();*/
            }

            @Override
            public void onNext(LoginBean loginBean) {

                if(loginBean.getMsg().equals("成功")) {
                    loginId = loginBean.getContent().getId();
                    name = loginBean.getContent().getName();
                    mobile = loginBean.getContent().getMobile();
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                    XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
                    XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
                    xcCacheManager.writeCache(xcCacheSaveName.logId, loginId);
                    xcCacheManager.writeCache(xcCacheSaveName.loginStatus,"yes");
                    xcCacheManager.writeCache(xcCacheSaveName.userName,name);
                    xcCacheManager.writeCache(xcCacheSaveName.userTel,mobile);
                    /*activity.finish();*/
                    try {
                        EMClient.getInstance().logout(true);
                        EMClient.getInstance().createAccount(loginBean.getContent().getId(), "shandiankuaiyun");
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                    regHuanXin();
                    EMClient.getInstance().login(loginBean.getContent().getId(),"sdwl",new EMCallBack() {//回调
                        @Override
                        public void onSuccess() {
                            EMClient.getInstance().groupManager().loadAllGroups();
                            EMClient.getInstance().chatManager().loadAllConversations();
                Log.d("main", "登录聊天服务器成功！");
                            Message message = new Message();
                            message.what = 1;
                            handler.sendMessage(message);
                            XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
                            XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
                            xcCacheManager.writeCache(xcCacheSaveName.logId, loginId);
                            xcCacheManager.writeCache(xcCacheSaveName.loginStatus,"yes");
                            xcCacheManager.writeCache(xcCacheSaveName.userName,name);
                            xcCacheManager.writeCache(xcCacheSaveName.userTel,mobile);

                            activity.finish();
                        }

                        @Override
                        public void onProgress(int progress, String status) {

                        }

                        @Override
                        public void onError(int code, String message) {
                            Log.d("main", "登录聊天服务器失败！"+code+" mess"+message);
                            ResumeLogin(loginId);
                        }
                    });
                   /* Toast.makeText(activity,"regis succ",Toast.LENGTH_LONG).show();*/


                }else{
                    Toast.makeText(activity,loginBean.getMsg(),Toast.LENGTH_LONG).show();
                }
                initAliasJpush();
                activity.finish();
            }
        });



    }
    private void regHuanXin(){
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    EMClient.getInstance().createAccount(loginId,"sdwl");
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            // save current user
//								Toast.makeText(getApplicationContext(),"恭喜注册成功！！！", Toast.LENGTH_SHORT).show();
                            Log.d("注册环信", "Regist: onSuccess");
                 /*           activity.finish();*/
                        }
                    });
                } catch (final HyphenateException e) {
                    activity.runOnUiThread(new Runnable() {
                        public void run() {
                            int errorCode=e.getErrorCode();
                            if(errorCode== EMError.NETWORK_ERROR){
//									Toast.makeText(getApplicationContext(), "网络异常，请检查网络！", Toast.LENGTH_SHORT).show();
                            }else if(errorCode == EMError.USER_ALREADY_EXIST){
//									Toast.makeText(getApplicationContext(),"用户已存在！", Toast.LENGTH_SHORT).show();
                                Log.d("注册环信", "用户已存在！");
                            }else if(errorCode == EMError.USER_AUTHENTICATION_FAILED){
//									Toast.makeText(getApplicationContext(),"注册失败，无权限！", Toast.LENGTH_SHORT).show();
                            }else if(errorCode == EMError.USER_ILLEGAL_ARGUMENT){
//								    Toast.makeText(getApplicationContext(),"用户名不合法",Toast.LENGTH_SHORT).show();
                            }else{
//									Toast.makeText(getApplicationContext(), "注册失败", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }


            }
        }).start();
    }

    /**
     * 如果登录失败继续登录成功为止
     * @param id
     */
    protected void ResumeLogin(String id) {
    /*    EMClient.getInstance().login(id,"sdwl", new EMCallBack() {*/
        EMClient.getInstance().login(id,"sdwl", new EMCallBack() {

            @Override
            public void onSuccess() {
                Log.d("登陆环信", "login: onSuccess");
                // ** manually load all local groups and conversation
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();


            }
            @Override
            public void onProgress(int progress, String status) {
                Log.d("登陆环信", "login: onProgress");
            }

            @Override
            public void onError(final int code, final String message) {
                Log.d("登陆环信", "login: onError: " + code);
            }
        });

    }












    /*极光别名注册*/


    private void initAliasJpush(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            return;
        }
        String alias = "SDKY"+loginId;
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
        /*mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, "保证此处是唯一的标识"));*/
    }



    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Toast.makeText(activity,"here is success:"+alias+" "+tags,Toast.LENGTH_LONG).show();
               /*     NotificationCompat.Builder	notification = new NotificationCompat.Builder(activity).setSmallIcon(R.mipmap.logo)
                            .setSound(Uri.parse("android.resource://" + activity.getPackageName() + "/" + R.raw.shandian));*/
                            /*.setContentText(title);*/
                    break;

                case 6002:
                    logs = "Failed to set alias and tags due to timeout. Try again after 60s.";


                    break;

                default:
                    logs = "Failed with errorCode = " + code;

            }


        }

    };
    /*极光别名注册*/

}
