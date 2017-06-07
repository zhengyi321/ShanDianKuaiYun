package com.example.mynewslayoutlib.Utils;

import android.app.Activity;
import android.view.WindowManager;

/**
 * Created by zhyan on 2017/6/7.
 */

public class SystemUtils {
    private Activity activity;
    WindowManager wm;
    int width;
    int height;
    public SystemUtils(Activity activity){
        this.activity = activity;
        wm = activity.getWindowManager();
        width = wm.getDefaultDisplay().getWidth();
        height = wm.getDefaultDisplay().getHeight();
    }

    public int getWindowWidth(){
        return width;

    }
    public int getWindowHeight(){
        return height;
    }
}
