package com.shandian.lu.Main.ReleaseFragment.ZuCheHuoYun;

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
 * Created by az on 2017/5/16.
 */

public class ZuCheHuoYunController extends BaseController {



    CarTypeDialog carTypeDialog;
    CarLengthDialog carLengthDialog;
    @BindView(R.id.rly_main_release_zuchehuoyun_back)
    RelativeLayout rlyMainReleaseZuCheHuoYunBack;
    @OnClick(R.id.rly_main_release_zuchehuoyun_back)
    public void rlyMainReleaseZuCheHuoYunBackOnclick(){
        activity.finish();
    }




    @BindView(R.id.tv_main_release_zuchehuoyun_content_cartype)
    TextView tvMainReleaseZuCheHuoYunContentCarType;
    @BindView(R.id.lly_main_release_zuchehuoyun_content_cartype)
    LinearLayout llyMainReleaseZuCheHuoYunContentCarType;
    @OnClick(R.id.lly_main_release_zuchehuoyun_content_cartype)
    public void llyMainReleaseZuCheHuoYunContentCarTypeOnclick(){
        carTypeDialog = new CarTypeDialog(activity,tvMainReleaseZuCheHuoYunContentCarType).Build.build(activity);
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
    @BindView(R.id.tv_main_release_zuchehuoyun_content_carlength)
    TextView tvMainReleaseZuCheHuoYunContentCarLength;
    @BindView(R.id.lly_main_release_zuchehuoyun_content_carlength)
    LinearLayout llyMainReleaseZuCheHuoYunContentCarLength;
    @OnClick(R.id.lly_main_release_zuchehuoyun_content_carlength)
    public void llyMainReleaseZuCheHuoYunContentCarLengthOnclick(){
        carLengthDialog = new CarLengthDialog(activity,tvMainReleaseZuCheHuoYunContentCarLength).Build.build(activity);
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









    public ZuCheHuoYunController(Activity activity1){
        activity = activity1;
        init();
    }


    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }
}
