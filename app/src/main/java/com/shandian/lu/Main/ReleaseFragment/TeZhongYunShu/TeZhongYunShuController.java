package com.shandian.lu.Main.ReleaseFragment.TeZhongYunShu;

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

public class TeZhongYunShuController extends BaseController {


    CarTypeDialog carTypeDialog;
    CarLengthDialog carLengthDialog;
    @BindView(R.id.rly_main_release_tezhongyunshu_back)
    RelativeLayout rlyMainReleaseTeZhongYunShuBack;
    @OnClick(R.id.rly_main_release_tezhongyunshu_back)
    public void rlyMainReleaseTeZhongYunShuBackOnclick(){
        activity.finish();
    }




    @BindView(R.id.tv_main_release_tezhongyunshu_content_cartype)
    TextView tvMainReleaseTeZhongYunShuContentCarType;
    @BindView(R.id.lly_main_release_tezhongyunshu_content_cartype)
    LinearLayout llyMainReleaseTeZhongYunShuContentCarType;
    @OnClick(R.id.lly_main_release_tezhongyunshu_content_cartype)
    public void llyMainReleaseTeZhongYunShuContentCarTypeOnclick(){
        carTypeDialog = new CarTypeDialog(activity,tvMainReleaseTeZhongYunShuContentCarType).Build.build(activity);
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
    @BindView(R.id.tv_main_release_tezhongyunshu_content_carlength)
    TextView tvMainReleaseTeZhongYunShuContentCarLength;
    @BindView(R.id.lly_main_release_tezhongyunshu_content_carlength)
    LinearLayout llyMainReleaseTeZhongYunShuContentCarLength;
    @OnClick(R.id.lly_main_release_tezhongyunshu_content_carlength)
    public void llyMainReleaseTeZhongYunShuContentCarLengthOnclick(){
        carLengthDialog = new CarLengthDialog(activity,tvMainReleaseTeZhongYunShuContentCarLength).Build.build(activity);
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




    public TeZhongYunShuController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }
}
