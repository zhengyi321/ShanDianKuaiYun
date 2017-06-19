package com.shandian.lu.Main;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/5/17.
 */

public class SplashActivity extends BaseActivity {
    int second = 5;
 /*   @BindView(R.id.pg_main_splash)
    CustomClipLoading pgMainSplash;*/

    @BindView(R.id.bt_sp)
    Button btSp;
    @OnClick(R.id.bt_sp)
    public void btSpOnclick(){
        second = 1;

    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:

                    btSp.setText("跳转("+second+")");

                    if(second == 0) {
                        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }
                   /* pgMainSplash.setVisibility(View.GONE);*/
                    break;
            }
            super.handleMessage(msg);
        }
    };


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_splash_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        Thread thread = new MyThread();
        thread.start();

    }


    public class MyThread extends Thread{

        @Override
        public void run(){
            try {
                for(;second>0;second--) {
                    sleep(1000);
                    Message message = new Message();
                    message.what = 1;
                    handler.sendMessage(message);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
