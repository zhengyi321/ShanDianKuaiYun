package com.shandian.lu.Application;

import android.app.Application;


import com.baidu.mapapi.SDKInitializer;
/*
import com.hyphenate.easeui.EaseUI;*/
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/*
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.common.SocializeConstants;*/
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.zhyan.myhuanxin.EaseUI;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.AppThirdDataUtils;

import cn.smssdk.SMSSDK;


/**
 *非常好用的插件http://blog.csdn.net/liang5630/article/details/46366901/
 */
public class MyApplication extends Application {
    public static final String APP_ID = "900029763"; // TODO 替换成bugly上注册的appid
    public static final String APP_CHANNEL = "DEBUG"; // TODO 自定义渠道
    private static final String TAG = "OnUILifecycleListener";
    public static  MyApplication instance;


    @Override
    public void onCreate() {
        this.instance = this;


        initBaiDuSDK();
        initHuanXin();
        initYouMeng();


        super.onCreate();



        initMobSDK();
        // 图片缓存初始化
        ImageLoaderConfiguration configuration=ImageLoaderConfiguration.createDefault(this);
        // setImageLoader(configuration);
        ImageLoader.getInstance().init(configuration);

        /*极光推送*/
     /*   JPushInterface.setDebugMode(true);
        JPushInterface.init(this);*/
       /*极光推送*/

/*        *//*友盟第三方登录*//*
        ShareSDK.initSDK(this);
        *//*友盟第三方登录*/
    }



    public  MyApplication getInstance() {
        return instance;
    }

    public static  MyApplication getContext() {

        return instance;
    }
    /*百度地图定位begin*/
    private void initBaiDuSDK(){
        // 在使用SDK各组件之前初始化context信息，传入ApplicationContext
        // 注意该方法要再setContentView方法之前实现
        SDKInitializer.initialize(getApplicationContext());
    }


    /*mob短信 sdk初始化*/
    private void initMobSDK(){
        AppThirdDataUtils appThirdDataUtils = new AppThirdDataUtils();
        SMSSDK.initSDK(this, appThirdDataUtils.MobAppKey, appThirdDataUtils.MobAppSecret);
    }
    /*mob短信 sdk初始化*/

    /*环信初始化*/
    private void initHuanXin(){


        EMOptions options = new EMOptions();
        // 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);

        EaseUI.getInstance().init(this, null);
        EMClient.getInstance().setDebugMode(true);

    }
    /*环信初始化*/

    /*友盟初始化*/
    private void initYouMeng(){

      /*  QueuedWork.isUseThreadPool = false;*/
        UMShareAPI.get(this);

        //微信 wx12342956d1cab4f9,a5ae111de7d9ea137e88a5e02c07c94d
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setWeixin("wx45d0c1ee001c7272", "3f350d810683b0322a7fdffa2a905f64");
        //新浪微博
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad");
      /*  最新的版本需要加上这个回调地址，可以在微博开放平台申请的应用获取，必须要有*/
      /*  Config.REDIRECT_URL="http://sns.whalecloud.com/sina2/callback";*/
        //QQ
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");
       /* UMShareAPI.get(instance);
        Config.DEBUG = true;
        Log.i("友盟的版本号", SocializeConstants.SDK_VERSION);
        MobclickAgent.setScenarioType(getContext(), MobclickAgent.EScenarioType.E_UM_NORMAL);
        //微信
        PlatformConfig.setWeixin("wx45d0c1ee001c7272", "3f350d810683b0322a7fdffa2a905f64");
        //新浪微博
        PlatformConfig.setSinaWeibo("2829995218", "a8423ca0652139f8d28a0791876aaa36");
        //QQ
        PlatformConfig.setQQZone("1105709541", "S3qvHhvRY18jVriy");*/

    }

    /*热更新*/
    private void tencent(){
   /*     CrashReport.initCrashReport(getApplicationContext(), "注册时申请的APPID", false);
        Bugly.init(getApplicationContext(), "注册时申请的APPID", false);*/
    }
    /*热更新*/
}

