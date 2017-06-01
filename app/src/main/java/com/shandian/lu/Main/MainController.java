package com.shandian.lu.Main;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.shandian.lu.BaseController;
/*import ChatMessageFragment;*/
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Widget.ImmersionBar.SystemBarTintManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by az on 2017/4/25.
 */

public class MainController extends BaseController {


    @BindView(R.id.fly_main_content)
    FrameLayout flyMainContent;


    public MainController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);

    }



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
                tintManager.setStatusBarTintResource(R.color.color_main_mine_tophead_green_bg);
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
