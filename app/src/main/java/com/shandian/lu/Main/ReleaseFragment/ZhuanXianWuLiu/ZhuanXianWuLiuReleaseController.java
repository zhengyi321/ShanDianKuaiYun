package com.shandian.lu.Main.ReleaseFragment.ZhuanXianWuLiu;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shandian.lu.BaseController;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.CarLengthDialog;
import com.shandian.lu.Widget.Dialog.CarTypeDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/5/12.
 */

public class ZhuanXianWuLiuReleaseController extends BaseController{

    public   final int ACTIVITY_REQUEST_SELECT_PHOTO = 100;
    CarTypeDialog carTypeDialog;
    CarLengthDialog carLengthDialog;
    @BindView(R.id.rly_main_release_zhuanxianwuliu_back)
    RelativeLayout rlyMainReleaseZhuanXianWuLiuBack;
    @OnClick(R.id.rly_main_release_zhuanxianwuliu_back)
    public void rlyMainReleaseZhuanXianWuLiuBackOnclick(){
        activity.finish();
    }

    @BindView(R.id.tv_main_release_zhuanxianwuliu_content_cartype)
    TextView tvMainReleaseZhuanXianWuLiuContentCarType;
    @BindView(R.id.lly_main_release_zhuanxianwuliu_content_cartype)
    LinearLayout llyMainReleaseZhuanXianWuLiuContentCarType;
    @OnClick(R.id.lly_main_release_zhuanxianwuliu_content_cartype)
    public void llyMainReleaseZhuanXianWuLiuContentCarTypeOnclick(){
        carTypeDialog = new CarTypeDialog(activity,tvMainReleaseZhuanXianWuLiuContentCarType).Build.build(activity);
        showDialog();


    }
    public void showDialog() {
        if (carTypeDialog != null && !carTypeDialog.isShowing())
            carTypeDialog.show();
        }

    public void dissmissDialog() {
        if (carTypeDialog != null && carTypeDialog.isShowing())
            carTypeDialog.dismiss();
    }
    @BindView(R.id.tv_main_release_zhuanxianwuliu_content_carlength)
    TextView tvMainReleaseZhuanXianWuLiuContentCarLength;
    @BindView(R.id.lly_main_release_zhuanxianwuliu_content_carlength)
    LinearLayout llyMainReleaseZhuanXianWuLiuContentCarLength;
    @OnClick(R.id.lly_main_release_zhuanxianwuliu_content_carlength)
    public void llyMainReleaseZhuanXianWuLiuContentCarLengthOnclick(){
        carLengthDialog = new CarLengthDialog(activity,tvMainReleaseZhuanXianWuLiuContentCarLength).Build.build(activity);
        showDialog2();




    }
    public void showDialog2() {
        if (carLengthDialog != null && !carLengthDialog.isShowing())
            carLengthDialog.show();
        }

    public void dissmissDialog2() {
        if (carLengthDialog != null && carLengthDialog.isShowing())
            carLengthDialog.dismiss();
    }



    public ZhuanXianWuLiuReleaseController(Activity activity1){
        activity = activity1;
        init();
    }
    @Override
    protected void init() {
        ButterKnife.bind(this,activity);

    }



}
