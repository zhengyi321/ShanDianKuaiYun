package com.shandian.lu.Widget.Dialog;

import android.app.Activity;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.example.mynewslayoutlib.Utils.SystemUtils;
import com.shandian.lu.BaseController;
import com.shandian.lu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by zhyan on 2017/6/7.
 */

public class ReleaseDialogController extends BaseController {
    private int offset = 0;// 动画图片偏移量
    private int bmpW;// 动画图片宽度
    private int currIndex = 0;// 当前页卡编号

    @BindView(R.id.iv_dialog_release_che_huo)
    ImageView ivDialogReleaseCheHuo;
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
        int marginLeft = (((width/2)/2)/2);
        int ivWidth = (width/2)/2;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ivWidth,activity.getResources().getDimensionPixelSize(3));
        params.setMargins(marginLeft,0,0,0);
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 2 - bmpW) / 2;// 计算偏移量  screenW/有几个tab 就除以几
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        ivDialogReleaseCheHuo.setImageMatrix(matrix);
        ivDialogReleaseCheHuo.setLayoutParams(params);
    }


    public void initTabBar(int arg0){
        int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
        int two = one * 2;// 页卡1 -> 页卡3 偏移量
        Animation animation = null;
        switch (arg0) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(one, 0, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, 0, 0, 0);
                }
                currIndex = arg0;
                animation.setFillAfter(true);// True:图片停在动画结束位置
                animation.setDuration(200);
                ivDialogReleaseCheHuo.startAnimation(animation);

                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, one, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, one, 0, 0);
                }
                currIndex = arg0;
                animation.setFillAfter(true);// True:图片停在动画结束位置
                animation.setDuration(200);
                ivDialogReleaseCheHuo.startAnimation(animation);

                break;

        }
    }
}
