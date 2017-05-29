package com.zhyan.shandiankuaiyun;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

/**
 * Created by az on 2017/4/25.
 */

public  abstract class BaseFragmentActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView();
        init();
    }

    protected abstract void setContentView();

    protected abstract void  init();

}
