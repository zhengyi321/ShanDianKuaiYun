package com.shandian.lu.Main.ReleaseFragment.BanJia;

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
 * Created by az on 2017/5/15.
 */

public class ReleaseBanJiaController extends BaseController {

    CarTypeDialog carTypeDialog;
    CarLengthDialog carLengthDialog;
    @BindView(R.id.rly_main_release_banjia_back)
    RelativeLayout rlyMainReleaseBanJiaBack;
    @OnClick(R.id.rly_main_release_banjia_back)
    public void rlyMainReleaseBanJiaBackOnclick(){
        activity.finish();
    }




    @BindView(R.id.tv_main_release_banjia_content_cartype)
    TextView tvMainReleaseBanJiaContentCarType;
    @BindView(R.id.lly_main_release_banjia_content_cartype)
    LinearLayout llyMainReleaseBanJiaContentCarType;
    @OnClick(R.id.lly_main_release_banjia_content_cartype)
    public void llyMainReleaseBanJiaContentCarTypeOnclick(){
        carTypeDialog = new CarTypeDialog(activity,tvMainReleaseBanJiaContentCarType).Build.build(activity);
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
    @BindView(R.id.tv_main_release_banjia_content_carlength)
    TextView tvMainReleaseBanJiaContentCarLength;
    @BindView(R.id.lly_main_release_banjia_content_carlength)
    LinearLayout llyMainReleaseBanJiaContentCarLength;
    @OnClick(R.id.lly_main_release_banjia_content_carlength)
    public void llyMainReleaseBanJiaContentCarLengthOnclick(){
        carLengthDialog = new CarLengthDialog(activity,tvMainReleaseBanJiaContentCarLength).Build.build(activity);
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


    public ReleaseBanJiaController(Activity activity1){
        activity = activity1;
        init();
    }
    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }
}
