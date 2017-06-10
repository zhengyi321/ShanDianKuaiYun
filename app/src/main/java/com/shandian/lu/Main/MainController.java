package com.shandian.lu.Main;

import android.annotation.TargetApi;
import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.NotificationCompat;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.shandian.lu.BaseController;
/*import ChatMessageFragment;*/
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyunlib.Widget.ImmersionBar.SystemBarTintManager;

import java.util.Set;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

/**
 * Created by az on 2017/4/25.
 */

public class MainController extends BaseController {

/*
    @BindView(R.id.fly_main_content)
    FrameLayout flyMainContent;*/
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
    @BindView(R.id.fly_new_main_content)
    FrameLayout flyMainContent;

    public MainController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initAliasJpush();
    }




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

}
