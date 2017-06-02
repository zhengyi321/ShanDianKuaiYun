package com.shandian.lu.Main.ReleaseFragment.RenRenKuaiDi;

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

public class RenRenKuaiDiController extends BaseController {



    CarTypeDialog carTypeDialog;
    CarLengthDialog carLengthDialog;
    @BindView(R.id.rly_main_release_renrenkuaidi_back)
    RelativeLayout rlyMainReleaseRenRenKuaiDiBack;
    @OnClick(R.id.rly_main_release_renrenkuaidi_back)
    public void rlyMainReleaseRenRenKuaiDiBackOnclick(){
        activity.finish();
    }




    @BindView(R.id.tv_main_release_renrenkuaidi_content_cartype)
    TextView tvMainReleaseRenRenKuaiDiContentCarType;
    @BindView(R.id.lly_main_release_renrenkuaidi_content_cartype)
    LinearLayout llyMainReleaseRenRenKuaiDiContentCarType;
    @OnClick(R.id.lly_main_release_renrenkuaidi_content_cartype)
    public void llyMainReleaseRenRenKuaiDiContentCarTypeOnclick(){
        carTypeDialog = new CarTypeDialog(activity,tvMainReleaseRenRenKuaiDiContentCarType).Build.build(activity);
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
    @BindView(R.id.tv_main_release_renrenkuaidi_content_carlength)
    TextView tvMainReleaseRenRenKuaiDiContentCarLength;
    @BindView(R.id.lly_main_release_renrenkuaidi_content_carlength)
    LinearLayout llyMainReleaseRenRenKuaiDiContentCarLength;
    @OnClick(R.id.lly_main_release_renrenkuaidi_content_carlength)
    public void llyMainReleaseRenRenKuaiDiContentCarLengthOnclick(){
        carLengthDialog = new CarLengthDialog(activity,tvMainReleaseRenRenKuaiDiContentCarLength).Build.build(activity);
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






    public RenRenKuaiDiController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }
}
