package com.shandian.lu.BroadCast;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;


import com.shandian.lu.R;


import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;

import static android.content.Context.NOTIFICATION_SERVICE;

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
            processCustomMessage(context, bundle1);

        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知");
            int notifactionId = bundle1.getInt(JPushInterface.EXTRA_NOTIFICATION_ID);
            Log.d(TAG, "[MyReceiver] 接收到推送下来的通知的ID: " + notifactionId);
            processCustomMessage(context, bundle1);

        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            Log.d(TAG, "[MyReceiver] 用户点击打开了通知");

            //打开自定义的Activity
			/*点击推送图标后转入的页面*/



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



        context1 = context;
        this.bundle1 = bundle;
        NotificationManager	notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);

        setNotification4(context);
        NotificationCompat.Builder	notification = new NotificationCompat.Builder(context);
        notification.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.shandian));

		notification.setAutoCancel(true)
				.setContentText("请接单")
				.setContentTitle("闪电快运")
				.setSmallIcon(R.mipmap.logo);

		/*String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);*/
        String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
        String title = bundle.getString(JPushInterface.EXTRA_TITLE);
        /*xcCacheManager.writeCache(xcCacheManagerSavedName.jpushOrderType,title);*/
		/*Toast.makeText(context,"message:"+message,Toast.LENGTH_SHORT).show();*/


        String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
/*		String platform = bundle.getString("platform");
		String audience = bundle.getString("audience");*/



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









    //自定义报警通知（震动铃声都不要）  http://blog.csdn.net/fuzhongbin/article/details/51162228
    public void setNotification4(Context context){
        BasicPushNotificationBuilder builder = new BasicPushNotificationBuilder(context);
        builder.statusBarDrawable = R.mipmap.logo;
        builder.notificationFlags = Notification.FLAG_AUTO_CANCEL;  //设置为自动消失
       /* builder.notificationDefaults = Notification.DEFAULT_LIGHTS;*/// 设置为铃声与震动都不要
        JPushInterface.setDefaultPushNotificationBuilder(builder);

    }
}