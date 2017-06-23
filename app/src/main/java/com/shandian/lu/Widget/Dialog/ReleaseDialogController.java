package com.shandian.lu.Widget.Dialog;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynewslayoutlib.Utils.SystemUtils;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.Main.ReleaseFragment.FaBuCheYuan.NewFaBuCheYuanActivity;
import com.shandian.lu.Main.ReleaseFragment.FaBuHuoYuan.NewFaBuHuoYuanActivity;
import com.shandian.lu.Main.ReleaseFragment.FaBuHuoYuan.NewFaBuHuoYuanV2Activity;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhyan on 2017/6/7.
 */

public class ReleaseDialogController extends BaseController {
    private int offset = 0;// 动画图片偏移量
    private int bmpW = 70;// 动画图片宽度
    private int currIndex = 0;// 当前页卡编号

    @BindView(R.id.iv_dialog_release_che_huo)
    ImageView ivDialogReleaseCheHuo;

    @BindView(R.id.tv_dialog_che_ct)
    TextView tvDialogCheCT;
    @BindView(R.id.tv_dialog_che_zx)
    TextView tvDialogCheZX;
    @BindView(R.id.tv_dialog_che_tc)
    TextView tvDialogCheTC;
    @BindView(R.id.tv_dialog_che_tz)
    TextView tvDialogCheTZ;

    @BindView(R.id.tv_dialog_huo_ct)
    TextView tvDialogHuoCT;
    @BindView(R.id.tv_dialog_huo_zx)
    TextView tvDialogHuoZX;
    @BindView(R.id.tv_dialog_huo_tc)
    TextView tvDialogHuoTC;
    @BindView(R.id.tv_dialog_huo_tz)
    TextView tvDialogHuoTZ;


    @BindView(R.id.lly_dialog_che_huo_ct)
    LinearLayout llyDialogCheHuoCT;
    @OnClick(R.id.lly_dialog_che_huo_ct)
    public void rlyDialogCheHuoCTOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        Intent intent;
        if((loginId == null)||(loginId.isEmpty())){
            Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();
            intent = new Intent(view.getContext(), LoginActivity.class);
            view.getContext().startActivity(intent);
            return;
        }
        System.out.print("\nloginId:"+loginId);
        System.out.print("\nloginId:"+loginId);
        System.out.print("\nloginId:"+loginId);
        System.out.print("\nloginId:"+loginId);
        System.out.print("\nloginId:"+loginId);
        System.out.print("\nloginId:"+loginId);
        System.out.print("\nloginId:"+loginId);
        if(rbDialogReleaseChe.isChecked()){
            intent = new Intent(view.getContext(), NewFaBuCheYuanActivity.class);
            intent.putExtra("type_name","1");
            view.getContext().startActivity(intent);
        }
        if(rbDialogReleaseHuo.isChecked()){
            /*intent = new Intent(view.getContext(), NewFaBuHuoYuanActivity.class);*/
            intent = new Intent(view.getContext(), NewFaBuHuoYuanV2Activity.class);
            intent.putExtra("type_name","1");
            view.getContext().startActivity(intent);
        }
    }

    @BindView(R.id.lly_dialog_che_huo_zx)
    LinearLayout llyDialogCheHuoZX;
    @OnClick(R.id.lly_dialog_che_huo_zx)
    public void rlyDialogCheHuoZXOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        Intent intent;
        if((loginId == null)||(loginId.isEmpty())){
            Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();
            intent = new Intent(view.getContext(), LoginActivity.class);
            view.getContext().startActivity(intent);
            return;
        }
        if(rbDialogReleaseChe.isChecked()){
            intent = new Intent(view.getContext(), NewFaBuCheYuanActivity.class);
            intent.putExtra("type_name","2");
            view.getContext().startActivity(intent);
        }
        if(rbDialogReleaseHuo.isChecked()){
            /*intent = new Intent(view.getContext(), NewFaBuHuoYuanActivity.class);*/
            intent = new Intent(view.getContext(), NewFaBuHuoYuanV2Activity.class);
            intent.putExtra("type_name","2");
            view.getContext().startActivity(intent);
        }
    }

    @BindView(R.id.lly_dialog_che_huo_tc)
    LinearLayout llyDialogCheHuoTC;
    @OnClick(R.id.lly_dialog_che_huo_tc)
    public void rlyDialogCheHuoTCOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        Intent intent;
        if((loginId == null)||(loginId.isEmpty())){
            Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();
            intent = new Intent(view.getContext(), LoginActivity.class);
            view.getContext().startActivity(intent);
            return;
        }
        if(rbDialogReleaseChe.isChecked()){
            intent = new Intent(view.getContext(), NewFaBuCheYuanActivity.class);
            intent.putExtra("type_name","3");
            view.getContext().startActivity(intent);
        }
        if(rbDialogReleaseHuo.isChecked()){
            /*intent = new Intent(view.getContext(), NewFaBuHuoYuanActivity.class);*/
            intent = new Intent(view.getContext(), NewFaBuHuoYuanV2Activity.class);
            intent.putExtra("type_name","3");
            view.getContext().startActivity(intent);
        }
    }

    @BindView(R.id.lly_dialog_che_huo_tz)
    LinearLayout llyDialogCheHuoTZ;
    @OnClick(R.id.lly_dialog_che_huo_tz)
    public void rlyDialogCheHuoTZOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        Intent intent;
        if((loginId == null)||(loginId.isEmpty())){
            Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();
            intent = new Intent(view.getContext(), LoginActivity.class);
            view.getContext().startActivity(intent);
            return;
        }
        if(rbDialogReleaseChe.isChecked()){
            intent = new Intent(view.getContext(), NewFaBuCheYuanActivity.class);
            intent.putExtra("type_name","4");
            view.getContext().startActivity(intent);
        }
        if(rbDialogReleaseHuo.isChecked()){
            /*intent = new Intent(view.getContext(), NewFaBuHuoYuanActivity.class);*/
            intent = new Intent(view.getContext(), NewFaBuHuoYuanV2Activity.class);
            intent.putExtra("type_name","4");
            view.getContext().startActivity(intent);
        }
    }



    @BindView(R.id.rb_dialog_release_che)
    RadioButton rbDialogReleaseChe;

    @OnClick(R.id.rb_dialog_release_che)
    public void rbDialogReleaseCheOnclick(){

        initTabBar(0);
    }
    @BindView(R.id.rb_dialog_release_huo)
    RadioButton rbDialogReleaseHuo;
    @OnClick(R.id.rb_dialog_release_huo)
    public void rbDialogReleaseHuoOnclick(){

        initTabBar(1);
    }
    public ReleaseDialogController(View view1){
        view = view1;
        init();
    }

    @Override
    protected void init(){
        ButterKnife.bind(this,view);
        initImageView();
    }
    /**
     * 初始化动画
     */
    private void initImageView() {

        SystemUtils systemUtils = new SystemUtils((Activity)view.getContext());
        int width = systemUtils.getWindowWidth();
        int height = systemUtils.getWindowHeight();
        int marginLeft = (((width/2)/2));
        int ivWidth = (width/2)/2;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ivWidth,((Activity)view.getContext()).getResources().getDimensionPixelSize(R.dimen.dimen_30dp));
     /*   params.setMargins(marginLeft,0,0,0);*/
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity)view.getContext()).getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 2 - bmpW) / 4;// 计算偏移量 满屏 screenW/有几个tab 就除以几 dialog 则为dialog的宽度除以tab数量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        ivDialogReleaseCheHuo.setImageMatrix(matrix);
       /* ivDialogReleaseCheHuo.setLayoutParams(params);*/
    }


    public void initTabBar(int arg0){
        double one = offset*2.8 + bmpW;// 页卡1 -> 页卡2 偏移量
       /* int two = one * 2;*/// 页卡1 -> 页卡3 偏移量
        Animation animation = null;
        switch (arg0) {
            case 0:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(0, 0, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation((int)one, 0, 0, 0);
                }
                currIndex = arg0;
                animation.setFillAfter(true);// True:图片停在动画结束位置
                animation.setDuration(200);
                ivDialogReleaseCheHuo.startAnimation(animation);
                initCheYuanText();
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(0, (int)one, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation((int)one,(int) one, 0, 0);
                }
                currIndex = arg0;
                animation.setFillAfter(true);// True:图片停在动画结束位置
                animation.setDuration(200);
                ivDialogReleaseCheHuo.startAnimation(animation);
                initHuoYuanText();
                break;

        }
    }

    private void initCheYuanText(){

        tvDialogCheCT.setTextColor(0xff000000);
        tvDialogCheTC.setTextColor(0xff000000);
        tvDialogCheTZ.setTextColor(0xff000000);
        tvDialogCheZX.setTextColor(0xff000000);
        tvDialogHuoCT.setTextColor(0xff808080);
        tvDialogHuoTC.setTextColor(0xff808080);
        tvDialogHuoTZ.setTextColor(0xff808080);
        tvDialogHuoZX.setTextColor(0xff808080);



    }
    private void initHuoYuanText(){


        tvDialogCheCT.setTextColor(0xff808080);
        tvDialogCheTC.setTextColor(0xff808080);
        tvDialogCheTZ.setTextColor(0xff808080);
        tvDialogCheZX.setTextColor(0xff808080);
        tvDialogHuoCT.setTextColor(0xff000000);
        tvDialogHuoTC.setTextColor(0xff000000);
        tvDialogHuoTZ.setTextColor(0xff000000);
        tvDialogHuoZX.setTextColor(0xff000000);
    }
}
