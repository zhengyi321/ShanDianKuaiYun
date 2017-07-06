package com.shandian.lu.Main;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mynewslayoutlib.Bean.NewSplashBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.BaseActivity;
import com.shandian.lu.Main.IndexFragment.WebView.WebViewActivity;
import com.shandian.lu.NetWork.SplashNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by az on 2017/5/17.
 */

public class SplashActivity extends BaseActivity {
    int second = 5;
 /*   @BindView(R.id.pg_main_splash)
    CustomClipLoading pgMainSplash;*/

    @BindView(R.id.bt_sp)
    Button btSp;
 /*   @OnClick(R.id.bt_sp)
    public void btSpOnclick(){
        second = 1;

    }*/
    String url = "";
    @BindView(R.id.iv_splash)
    ImageView ivSplash;
    @OnClick(R.id.iv_splash)
    public void ivSplashOnclick(){
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        if((url == null)||(url.isEmpty())){
            second = 1;
            return;
        }
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
        startActivity(intent);


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
        getSplashFromNet();


    }
    private void getSplashFromNet(){
        SplashNetWork splashNetWork = new SplashNetWork();
        splashNetWork.getSpalshFromNet(new Observer<NewSplashBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewSplashBean newSplashBean) {
                String miaoshu = newSplashBean.getNr().getMiaoshu();
                if((miaoshu != null)&&(!miaoshu.isEmpty())){
                    second = Integer.parseInt(miaoshu);
                }
                ImageLoader.getInstance().displayImage(newSplashBean.getNr().getImg(),ivSplash, ImageLoaderUtils.options1);
                url = newSplashBean.getNr().getUrl();
                Thread thread = new MyThread();
                thread.start();
            }
        });
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
