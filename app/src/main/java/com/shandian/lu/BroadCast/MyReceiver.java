package com.shandian.lu.BroadCast;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;


import com.shandian.lu.Main.IndexFragment.NewCheYuanDetail.NewCheYuanDetailOtherActivity;
import com.shandian.lu.Main.IndexFragment.NewHuoYuanDetail.NewHuoYuanDetailOtherActivity;
import com.shandian.lu.Main.IndexFragment.NewHuoYuanDetail.NewHuoYuanDetailSelfActivity;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.NewNoticeDialog;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;

/**
 * 自定义接收器
 * http://www.jianshu.com/p/8e15ae0909d2
 * 极光自定义推送
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {
    private  final String TAG = "JPush";
    /*private GetNewOrderDialog getNewOrderDialog;*/
    private Context context1;
    private NewNoticeDialog newNoticeDialog;
    private boolean isFirst = true;
    private String lx = "",hyId = "",bjId = "",cyid="";
    Bundle bundle1;
    @Override
    public void onReceive(Context context, Intent intent) {
        bundle1 = intent.getExtras();
        context1 = context;
//		Log.d(TAG, "[MyReceiver] onReceive - " + intent.getAction() + ", extras: " + printBundle(bundle));

        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {

            String regId = bundle1.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            Log.d(TAG, "[MyReceiver] 接收Registration Id : " + regId);
            //send the Registration Id to your server...

        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 接收到推送下来的自定义消息: " + bundle1.getString(JPushInterface.EXTRA_MESSAGE));


        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");
            int notifactionId = bundle1.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
            processCustomMessage(context, bundle1);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户点击打开了通知");

            //打开自定义的Activity
			/*点击推送图标后转入的页面*/
			if(lx == null){
                lx = "zzzzzz";
            }

			if(hyId == null){
                hyId = "cccccc";
            }
            processCustomMessage(context, bundle1);

           /*Toast.makeText(context,"lx:"+lx+" hyid:"+hyId,Toast.LENGTH_LONG).show();*/

            activityJump();


/*
            Intent i = new Intent(context1, MainActivity.class);
            i.putExtras(bundle1);
            //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
            context1.startActivity(i);*/



        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle1.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..

        } else if(JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            Log.w(TAG, "[MyReceiver]" + intent.getAction() +" connected state change to "+connected);
        } else {
            Log.d(TAG, "[MyReceiver] Unhandled intent - " + intent.getAction());
        }
    }


    /**
     * 实现自定义推送声音
     * @param context
     * @param bundle
     */

    private void processCustomMessage(final Context context, Bundle bundle) {


        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        String title = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
        System.out.print("\nextras:"+extras);
        System.out.print("\nextras:"+extras);
        System.out.print("\nextras:"+extras);
        System.out.print("\nextras:"+extras);
        System.out.print("\nextras:"+extras);
        System.out.print("\nextras:"+extras);
        System.out.print("\nextras:"+extras);
        System.out.print("\nextras:"+extras);
        System.out.print("\nextras:"+extras);
        System.out.print("\nextras:"+extras);
        System.out.print("\nextras:"+extras);
        System.out.print("\nextras:"+extras);
        context1 = context;
        this.bundle1 = bundle;
     /*   setNotification4(context);*/
     /*   NotificationManager	notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);*/

     /*   setNotification4(context);*/
        NotificationCompat.Builder	notification = new NotificationCompat.Builder(context).setSmallIcon(R.mipmap.logo)
                .setContentText(title);
        notification.build();
        setNotification1(context);
   /*     createLocalMp3();*/
      /*  notificationManager.notify(1,notification.build());*/

       /* notification.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.shandian));*/

		/*String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);*/

 /*       Toast.makeText(context,"this is message:"+title+" content:"+message+" extras:"+extras,Toast.LENGTH_LONG).show();
        System.out.print("\nthis is message:"+title+" content:"+message+" extras:"+extras);
        System.out.print("\nthis is message:"+title+" content:"+message+" extras:"+extras);
        System.out.print("\nthis is message:"+title+" content:"+message+" extras:"+extras);
        System.out.print("\nthis is message:"+title+" content:"+message+" extras:"+extras);
        System.out.print("\nthis is message:"+title+" content:"+message+" extras:"+extras);
        System.out.print("\nthis is message:"+title+" content:"+message+" extras:"+extras);
        System.out.print("\nthis is message:"+title+" content:"+message+" extras:"+extras);*/
        if(isFirst) {
            newNoticeDialog = new NewNoticeDialog(context1, "type").Build.build(context1);
            newNoticeDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);

         /*   newNoticeDialog.show();*/

        }
        isFirst = false;

      /*  Intent intent = new Intent(this, NewFaBuHuoYuanActivity.class);*/
       /* Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);*/
        try {
            JSONObject extraJson = new JSONObject(extras);
            if (null != extraJson && extraJson.length() > 0) {
                    /*String sound = extraJson.getString("sound");*///自定义字段解析
                cyid = extraJson.getString("cyid");//自定义字段解析
                lx = extraJson.getString("lx");//自定义字段解析
            /*    hyId = extraJson.getString("hyid");//自定义字段解析*/

                System.out.print("\nthis is lx:"+lx+" hyId:"+hyId);
                System.out.print("\nthis is lx:"+lx+" hyId:"+hyId);
                System.out.print("\nthis is lx:"+lx+" hyId:"+hyId);
                System.out.print("\nthis is lx:"+lx+" hyId:"+hyId);
                System.out.print("\nthis is lx:"+lx+" hyId:"+hyId);
                System.out.print("\nthis is lx:"+lx+" hyId:"+hyId);
                System.out.print("\nthis is lx:"+lx+" hyId:"+hyId);
                System.out.print("\nthis is lx:"+lx+" hyId:"+hyId);
                System.out.print("\nthis is lx:"+lx+" hyId:"+hyId);
                System.out.print("\nthis is lx:"+lx+" hyId:"+hyId);
            }
        } catch (JSONException e) {

        }
        try {
            JSONObject extraJson = new JSONObject(extras);
            if (null != extraJson && extraJson.length() > 0) {
                    /*String sound = extraJson.getString("sound");*///自定义字段解析
               /* cyid = extraJson.getString("cyid");//自定义字段解析*/
                lx = extraJson.getString("lx");//自定义字段解析
                hyId = extraJson.getString("hyid");//自定义字段解析

                System.out.print("\nthis is lx:"+lx+" hyId:"+hyId);
                System.out.print("\nthis is lx:"+lx+" hyId:"+hyId);
                System.out.print("\nthis is lx:"+lx+" hyId:"+hyId);
                System.out.print("\nthis is lx:"+lx+" hyId:"+hyId);
                System.out.print("\nthis is lx:"+lx+" hyId:"+hyId);
                System.out.print("\nthis is lx:"+lx+" hyId:"+hyId);
                System.out.print("\nthis is lx:"+lx+" hyId:"+hyId);
                System.out.print("\nthis is lx:"+lx+" hyId:"+hyId);
                System.out.print("\nthis is lx:"+lx+" hyId:"+hyId);
                System.out.print("\nthis is lx:"+lx+" hyId:"+hyId);
            }
        } catch (JSONException e) {

        }
        //解析自定义字段
     /*   if (!TextUtils.isEmpty(extras)) {
            try {
                JSONObject extraJson = new JSONObject(extras);
                if (null != extraJson && extraJson.length() > 0) {
                    String sound = extraJson.getString("sound");//自定义字段解析
                     lx = extraJson.getString("lx");//自定义字段解析
                     hyId = extraJson.getString("hyid");//自定义字段解析
                }
            } catch (JSONException e) {

            }

        }*/
        /*xcCacheManager.writeCache(xcCacheManagerSavedName.jpushOrderType,title);*/
		/*Toast.makeText(context,"message:"+message,Toast.LENGTH_SHORT).show();*/
        /*notification.setContentTitle(title);*/


/*		String platform = bundle.getString("platform");
		String audience = bundle.getString("audience");*/

    /*    notificationManager.notify(2, notification.build());*/

/*
		if (!TextUtils.isEmpty(extras)) {
			try {
				JSONObject extraJson = new JSONObject(extras);
				if (null != extraJson && extraJson.length() > 0) {
					String sound = extraJson.getString("sound");
					if("zoutu.mp3".equals(sound)){
						notification.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" +R.raw.zoutu));
					}
				}
			} catch (JSONException e) {
			}
		}*/
		/*点击推送图标后转入的页面*/


    }

    public void showDialog() {
        if (newNoticeDialog != null && !newNoticeDialog.isShowing()) {
            newNoticeDialog.show();
        }
    }

    public void dissmissDialog() {
        if (newNoticeDialog != null && newNoticeDialog.isShowing()) {
            newNoticeDialog.dismiss();
        }
    }




    //自定义报警通知（震动铃声都要）
    public void setNotification1(Context context){
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(context);
        builder.statusBarDrawable = R.mipmap.logo;//消息栏显示的图标
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL;  //设置为自动消失
        builder.notificationDefaults = Notification.DEFAULT_SOUND| Notification.DEFAULT_VIBRATE|Notification.DEFAULT_LIGHTS;// 设置为铃声与震动都要
        JPushInterface.setDefaultPushNotificationBuilder(builder);
    }

    //自定义报警通知（震动铃声都不要）  http://blog.csdn.net/fuzhongbin/article/details/51162228
    public void setNotification4(Context context){
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(context);
        builder.statusBarDrawable = R.mipmap.logo;
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL;  //设置为自动消失
        builder.notificationDefaults = Notification.DEFAULT_LIGHTS;// 设置为铃声与震动都不要
        JPushInterface.setDefaultPushNotificationBuilder(builder);

    }

    /**
     * 创建本地MP3
     * @return
     */
    public MediaPlayer createLocalMp3(){
        /**
         * 创建音频文件的方法：
         * 1、播放资源目录的文件：MediaPlayer.create(MainActivity.this,R.raw.beatit);//播放res/raw 资源目录下的MP3文件
         * 2:播放sdcard卡的文件：mediaPlayer=new MediaPlayer();
         *   mediaPlayer.setDataSource("/sdcard/beatit.mp3");//前提是sdcard卡要先导入音频文件
         */
        MediaPlayer mp=MediaPlayer.create(context1,R.raw.shandian);
        mp.stop();
        try {
            mp.prepare();
            mp.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return mp;
    }


    public void activityJump(){
        Intent i;
        if((hyId == null)&&(!hyId.isEmpty())){
            return;
        }
        switch (lx){
            case "1":
                 i = new Intent(context1, NewHuoYuanDetailSelfActivity.class);
                i.putExtra("hyid",hyId);
                i.putExtra("status","notice");
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                context1.startActivity(i);
                break;
            case "2":
                 i = new Intent(context1, NewHuoYuanDetailOtherActivity.class);
                i.putExtra("hyid",hyId);

                i.putExtra("status","1");
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                context1.startActivity(i);
                break;
            case "3":
                i = new Intent(context1, NewHuoYuanDetailOtherActivity.class);
                i.putExtra("hyid",hyId);
                i.putExtra("status","2");
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                context1.startActivity(i);
                break;
            case "4":
                i = new Intent(context1, NewHuoYuanDetailOtherActivity.class);
                i.putExtra("hyid",hyId);
                i.putExtra("status","3");
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                context1.startActivity(i);
                break;
            case "5":
                i = new Intent(context1, NewHuoYuanDetailOtherActivity.class);
                i.putExtra("hyid",hyId);
                i.putExtra("status","4");
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                context1.startActivity(i);
                break;
            case "6":
                i = new Intent(context1, NewHuoYuanDetailOtherActivity.class);
                i.putExtra("hyid",hyId);
                i.putExtra("status","5");
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                context1.startActivity(i);
                break;
            case "7":
                i = new Intent(context1, NewCheYuanDetailOtherActivity.class);
                i.putExtra("cyid",cyid);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                context1.startActivity(i);
                break;
            case "8":
                i = new Intent(context1, NewHuoYuanDetailOtherActivity.class);
                i.putExtra("hyid",hyId);
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP );
                context1.startActivity(i);
                break;
        }
    }
}