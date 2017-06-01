package com.shandian.lu;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by az on 2017/4/25.
 */

public  abstract class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView();
        init();
    }

    protected abstract void setContentView();

    protected abstract void  init();

}
