package com.shandian.lu.Main;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMConnectionListener;
import com.hyphenate.EMError;
import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;
import com.shandian.lu.BaseController;
/*import ChatMessageFragment;*/
import com.shandian.lu.R;
import com.shandian.lu.Widget.DBHuanXin.SharePrefConstant;
import com.shandian.lu.Widget.DBHuanXin.UserInfoCacheSvc;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyunlib.Utils.SharedPreferencesUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ImmersionBar.SystemBarTintManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by az on 2017/4/25.
 */

public class MainController extends BaseController {

/*
    @BindView(R.id.fly_main_content)
    FrameLayout flyMainContent;*/

    private MyBroadCast broadcast;
    public boolean isSound = true;
    public boolean isNotice = false;
    private int unReadCountTotal = 0;
    private final int MSG_SET_ALIAS = 1001;
    protected final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SET_ALIAS:

                    /*JPushInterface.setAliasAndTags(activity, (String) msg.obj, null, mAliasCallback);*/
                    JPushInterface.setAliasAndTags(activity, (String) msg.obj,null,  mAliasCallback);
                    break;



                default:

            }
        }
    };
    @BindView(R.id.fly_new_main_content)
    FrameLayout flyMainContent;
    @BindView(R.id.tv_new_main_bottom_message_unread)
    TextView tvNewMainBottomMessageUnRead;
    public MainController(Activity activity1 ){
        activity = activity1;

        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initAliasJpush();
        initLoginId();

        broadcast = new MyBroadCast();
        activity.registerReceiver(broadcast, new IntentFilter("unReadMessage"));

    }



    public void initLoginId(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        SharedPreferencesUtils sharedPreferencesUtils = new SharedPreferencesUtils();
        String loginId=(String)sharedPreferencesUtils.getParam(activity,xcCacheSaveName.logId,"");
        String loginStatus=(String)sharedPreferencesUtils.getParam(activity,xcCacheSaveName.loginStatus,"");
        String userName=(String)sharedPreferencesUtils.getParam(activity,xcCacheSaveName.userName,"");
        String userTel=(String)sharedPreferencesUtils.getParam(activity,xcCacheSaveName.userTel,"");
        String userHeadImgUrl=(String)sharedPreferencesUtils.getParam(activity,xcCacheSaveName.userHeadImgUrl,"");

        xcCacheManager.writeCache(xcCacheSaveName.logId, loginId);
        xcCacheManager.writeCache(xcCacheSaveName.loginStatus,loginStatus);
        xcCacheManager.writeCache(xcCacheSaveName.userName,userName);
        xcCacheManager.writeCache(xcCacheSaveName.userTel,userTel);
        xcCacheManager.writeCache(xcCacheSaveName.userHeadImgUrl,userHeadImgUrl);
    }



    private void initAliasJpush(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, ""));
            JPushInterface.stopPush(activity);
            return;
        }
        String alias = "SDKY"+loginId;
        mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, alias));
        JPushInterface.resumePush(activity);
        /*mHandler.sendMessage(mHandler.obtainMessage(MSG_SET_ALIAS, "保证此处是唯一的标识"));*/
    }



    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {

        @Override
        public void gotResult(int code, String alias, Set<String> tags) {
            String logs;
            switch (code) {
                case 0:
                    logs = "Set tag and alias success";
                    Log.i("success",logs);
                 /*   Toast.makeText(activity,"here is success:"+alias+" "+tags,Toast.LENGTH_LONG).show();*/
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




    /*沉浸式状态栏*/
    public void initStatusBar(String type){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }
        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        tintManager.setStatusBarTintEnabled(true);
        switch (type) {
            case "index":
                tintManager.setStatusBarTintResource(R.color.color_main_index_topbar_blue_bg);
                break;
            case "release":
                tintManager.setStatusBarTintResource(R.color.color_main_release_topbar_blue_bg);
                break;
            case "advice":
                tintManager.setStatusBarTintResource(R.color.color_main_advice_content_white_bg);
                break;
            case "message":
                tintManager.setStatusBarTintResource(R.color.color_main_message_content_white_bg);
                break;
            case "mine":
                tintManager.setStatusBarTintResource(R.mipmap.top_big_blue_bg);
                break;
        }
    }
    /*沉浸式状态栏*/
    private class MyBroadCast extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String count = intent.getStringExtra("count");
            if(count == null){
                return;
            }
            int intCount = Integer.parseInt(count);

            unReadCountTotal = intCount;
/*            Toast.makeText(activity,"count"+unReadCountTotal,2000).show();*/
            if(unReadCountTotal == 0){
                tvNewMainBottomMessageUnRead.setVisibility(View.GONE);
            }else {

                tvNewMainBottomMessageUnRead.setVisibility(View.VISIBLE);
                tvNewMainBottomMessageUnRead.setText(unReadCountTotal+"");
                if(isSound) {
                    if(isNotice) {
                        setNotification1(activity);
                    }else {
                        playNotice();
                    }
                }
            }
        }

    }

    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public void onResume() {



    }

    public void onStop() {


    }
    //自定义报警通知（震动铃声都要）
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setNotification1(Context context){
        NotificationManager manager = (NotificationManager) activity.getSystemService(NOTIFICATION_SERVICE);

        Notification.Builder builder = new Notification.Builder(activity);
/*        builder.setContentInfo("补充内容");
        builder.setContentText("主内容区");
        builder.setContentTitle("通知标题");*/

            builder.setSmallIcon(R.mipmap.logo);
            builder.setTicker("新消息");

            builder.setAutoCancel(true);
            builder.setWhen(System.currentTimeMillis());//设置时间，设置为系统当前的时间

      /*  Intent intent = new Intent(activity, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(activity, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setContentIntent(pendingIntent);*/
        Notification notification = builder.build();

        /**
         * vibrate属性是一个长整型的数组，用于设置手机静止和振动的时长，以毫秒为单位。
         * 参数中下标为0的值表示手机静止的时长，下标为1的值表示手机振动的时长， 下标为2的值又表示手机静止的时长，以此类推。
         */
        long[] vibrates = { 0, 1000, 1000, 1000 };
        notification.vibrate = vibrates;
        /**
         * 手机设置的默认提示音
         */
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        /**
         * sound属性是一个 Uri 对象。 可以在通知发出的时候播放一段音频，这样就能够更好地告知用户有通知到来.
         * 如：手机的/system/media/audio/ringtones 目录下有一个 Basic_tone.ogg音频文件，
         * 可以写成： Uri soundUri = Uri.fromFile(new
         * File("/system/media/audio/ringtones/Basic_tone.ogg"));
         * notification.sound = soundUri; 我这里为了省事，就去了手机默认设置的铃声
         */
        notification.sound = uri;
        /**
         * 手机处于锁屏状态时， LED灯就会不停地闪烁， 提醒用户去查看手机,下面是绿色的灯光一 闪一闪的效果
         */
        notification.ledARGB = Color.GREEN;// 控制 LED 灯的颜色，一般有红绿蓝三种颜色可选
        notification.ledOnMS = 1000;// 指定 LED 灯亮起的时长，以毫秒为单位
        notification.ledOffMS = 1000;// 指定 LED 灯暗去的时长，也是以毫秒为单位
        notification.flags = Notification.FLAG_SHOW_LIGHTS | Notification.FLAG_AUTO_CANCEL;// 指定通知的一些行为，其中就包括显示
        // LED 灯这一选项



//                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
//                notification.sound = uri;
//                notification.defaults = Notification.DEFAULT_ALL;
        manager.notify(1, notification);
    }

    //播放系统提示音
    public void playNotice(){
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Ringtone r = RingtoneManager.getRingtone(activity, notification);
        r.play();
    }

    /**
     * 创建本地MP3
     * @return
     */
 /*   public MediaPlayer createLocalMp3(){
        *//**
         * 创建音频文件的方法：
         * 1、播放资源目录的文件：MediaPlayer.create(MainActivity.this,R.raw.beatit);//播放res/raw 资源目录下的MP3文件
         * 2:播放sdcard卡的文件：mediaPlayer=new MediaPlayer();
         *   mediaPlayer.setDataSource("/sdcard/beatit.mp3");//前提是sdcard卡要先导入音频文件
         *//*
        Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        MediaPlayer mp=MediaPlayer.create(activity,uri);
        mp.stop();
        try {
            mp.prepare();
            mp.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return mp;
    }*/
}
