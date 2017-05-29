package com.zhyan.shandiankuaiyun.Main.ReleaseFragment.HuiTouChe;

import android.app.Activity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhyan.shandiankuaiyun.BaseController;
import com.zhyan.shandiankuaiyun.R;
import com.zhyan.shandiankuaiyun.Widget.Dialog.CarLengthDialog;
import com.zhyan.shandiankuaiyun.Widget.Dialog.CarTypeDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/5/15.
 */

public class HuiTouController extends BaseController {



    CarTypeDialog carTypeDialog;
    CarLengthDialog carLengthDialog;
    @BindView(R.id.rly_main_release_huitouche_back)
    RelativeLayout rlyMainReleaseHuiTouCheBack;
    @OnClick(R.id.rly_main_release_huitouche_back)
    public void rlyMainReleaseHuiTouCheBackOnclick(){
        activity.finish();
    }




    @BindView(R.id.tv_main_release_huitouche_content_cartype)
    TextView tvMainReleaseHuiTouCheContentCarType;
    @BindView(R.id.lly_main_release_huitouche_content_cartype)
    LinearLayout llyMainReleaseHuiTouCheContentCarType;
    @OnClick(R.id.lly_main_release_huitouche_content_cartype)
    public void llyMainReleaseHuiTouCheContentCarTypeOnclick(){
        carTypeDialog = new CarTypeDialog(activity,tvMainReleaseHuiTouCheContentCarType).Build.build(activity);
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
    @BindView(R.id.tv_main_release_huitouche_content_carlength)
    TextView tvMainReleaseHuiTouCheContentCarLength;
    @BindView(R.id.lly_main_release_huitouche_content_carlength)
    LinearLayout llyMainReleaseHuiTouCheContentCarLength;
    @OnClick(R.id.lly_main_release_huitouche_content_carlength)
    public void llyMainReleaseHuiTouCheContentCarLengthOnclick(){
        carLengthDialog = new CarLengthDialog(activity,tvMainReleaseHuiTouCheContentCarLength).Build.build(activity);
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




    public HuiTouController(Activity activity1){
        activity = activity1;
        init();
    }
    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }
}
