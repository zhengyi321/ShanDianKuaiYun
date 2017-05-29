package com.zhyan.shandiankuaiyun.Main;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.mob.tools.MobHandlerThread;
/*
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.common.SocializeConstants;*/
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyun.Main.AdviceFragment.MainAdviceFragment;
import com.zhyan.shandiankuaiyun.Main.IndexFragment.MainIndexFragment;
import com.zhyan.shandiankuaiyun.Main.MessageFragment.ChatMessageFragment;
import com.zhyan.shandiankuaiyun.Main.MineFragment.MainMineFragment;
import com.zhyan.shandiankuaiyun.Main.ReleaseFragment.MainReleaseFragment;
import com.zhyan.shandiankuaiyun.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.rb_main_bottom_index)
    RadioButton rbMainBottomIndex;
    @OnClick(R.id.rb_main_bottom_index)
    public void rbMainBottomIndexOnclick(){
        getFragment("index");
    }
    @BindView(R.id.rb_main_bottom_release)
    RadioButton rbMainBottomRelease;
    @OnClick(R.id.rb_main_bottom_release)
    public void rbMainBottomReleaseOnclick(){
        getFragment("release");
    }
    @BindView(R.id.rb_main_bottom_advice)
    RadioButton rbMainBottomAdvice;
    @OnClick(R.id.rb_main_bottom_advice)
    public void rbMainBottomAdviceOnclick(){
        getFragment("advice");
    }
    @BindView(R.id.rb_main_bottom_message)
    RadioButton rbMainBottomMessage;
    @OnClick(R.id.rb_main_bottom_message)
    public void rbMainBottomMessageOnclick(){
        getFragment("message");
    }
    @BindView(R.id.rb_main_bottom_mine)
    RadioButton rbMainBottomMine;
    @OnClick(R.id.rb_main_bottom_mine)
    public void rbMainBottomMineOnclick(){
        getFragment("mine");
    }

    private MainIndexFragment mainIndexFragment;
    private MainReleaseFragment mainReleaseFragment;
    private MainAdviceFragment mainAdviceFragment;
    private ChatMessageFragment chatMessageFragmentl;
    private MainMineFragment mainMineFragment;
    private MainController mainController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    // Example of a call to a native method

    }

    private void init(){
        ButterKnife.bind(this);
        initController();
        initFragment();
        getFragment("index");

    /*    initYouMeng();*/
    }
    private void initController(){
        mainController = new MainController(this);
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }






    private void hideFragment(FragmentTransaction transaction){
        if(mainIndexFragment !=null){
            transaction.hide(mainIndexFragment);
        }
        if(mainReleaseFragment != null){
            transaction.hide(mainReleaseFragment);
        }
        if(mainAdviceFragment != null){
            transaction.hide(mainAdviceFragment);
        }
        if(chatMessageFragmentl != null){
            transaction.hide(chatMessageFragmentl);
        }
        if(mainMineFragment != null){
            transaction.hide(mainMineFragment);
        }
    }
    /* 初始化fragment*/
    private void initFragment(){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        if(mainIndexFragment == null){
           /* mainController.initStatusBar("index");*/
            mainIndexFragment = new MainIndexFragment();
            transaction.add(R.id.fly_main_content, mainIndexFragment, "index");
        }
        if(mainReleaseFragment == null){
            mainReleaseFragment = new MainReleaseFragment();
            transaction.add(R.id.fly_main_content, mainReleaseFragment, "release");
        }
        if(mainAdviceFragment == null){
            mainAdviceFragment = new MainAdviceFragment();
            transaction.add(R.id.fly_main_content, mainAdviceFragment, "advice");
        }
        if(chatMessageFragmentl == null){
            chatMessageFragmentl = new ChatMessageFragment();
            transaction.add(R.id.fly_main_content, chatMessageFragmentl, "message");
        }
        if(mainMineFragment != null){
            mainMineFragment = new MainMineFragment();
            transaction.add(R.id.fly_main_content, mainMineFragment, "mine");
        }
       /* transaction.show(mainIndexFragment);*/
        transaction.commit();
    }

    private void getFragment(String type){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragment(transaction);
        // 动态增加Fragment
        switch (type){
            case "index":
                mainController.initStatusBar("index");
                if(mainIndexFragment !=null){
                    transaction.show(mainIndexFragment);
                }else {
                    mainIndexFragment = new MainIndexFragment();
                    transaction.add(R.id.fly_main_content, mainIndexFragment, "index");
                }
                break;
            case "release":
                mainController.initStatusBar("release");
                if(mainReleaseFragment != null){

                    transaction.show(mainReleaseFragment);
                }else {
                    mainReleaseFragment = new MainReleaseFragment();

                    transaction.add(R.id.fly_main_content, mainReleaseFragment, "release");
                }
                break;
            case "advice":
                mainController.initStatusBar("advice");
                if(mainAdviceFragment != null){

                    transaction.show(mainAdviceFragment);
                }else {
                    mainAdviceFragment = new MainAdviceFragment();

                    transaction.add(R.id.fly_main_content, mainAdviceFragment, "advice");
                }
                break;
            case "message":
                mainController.initStatusBar("message");
                if(chatMessageFragmentl != null){

                    transaction.show(chatMessageFragmentl);
                }else {
                    chatMessageFragmentl = new ChatMessageFragment();

                    transaction.add(R.id.fly_main_content, chatMessageFragmentl, "message");
                }
                break;
            case "mine":
                mainController.initStatusBar("mine");
                if(mainMineFragment != null){

                    transaction.show(mainMineFragment);
                }else {
                    mainMineFragment = new MainMineFragment();

                    transaction.add(R.id.fly_main_content, mainMineFragment, "mine");
                }
                break;

        }

        transaction.commit();
    }






















    long mExitTime = 0;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode==event.KEYCODE_BACK){
            if((System.currentTimeMillis()-mExitTime)>2000){
//				Toast.makeText(MainFragmentActivity.this,"在按一次退出程序",Toast.LENGTH_SHORT).show();
                showToast("在按一次退出程序",0,0);
                mExitTime=System.currentTimeMillis();
            }else{
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    public void showToast(String text, int offx, int offy) {
        Toast mToast = null;
        if (mToast == null) {
            mToast = Toast.makeText(this, text, Toast.LENGTH_SHORT);
//	            LogUtil.i("----------------"+mToast.getGravity()+"-"+mToast.getXOffset()+"-"+mToast.getYOffset());
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
//	        mToast.setGravity(gravity, offx, offy);
        mToast.show();
    }
}
