package com.shandian.lu.Main.MineFragment.AboutUs;

import android.app.Activity;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.shandian.lu.Main.MineFragment.AboutUs.CompanyIntroduce.CompanyIntroduceActivity;
import com.shandian.lu.Main.MineFragment.AboutUs.YiJianFanKui.YiJianFanKuiActivity;
import com.shandian.lu.Main.MineFragment.AboutUs.YongHuXieYi.YongHuXieYiActivity;
import com.shandian.lu.BaseController;
import com.shandian.lu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/5/3.
 */

public class AboutUsController extends BaseController {

    @BindView(R.id.rly_main_mine_aboutus_back)
    RelativeLayout rlyMainMineAboutUsBack;
    @OnClick(R.id.rly_main_mine_aboutus_back)
    public void rlyMainMineAboutUsBackOnclickOnclick(){
        activity.finish();
    }

    @BindView(R.id.lly_main_mine_aboutus_companyintroduce)
    LinearLayout llyMainMineAboutUsCompanyIntroduce;

    @OnClick(R.id.lly_main_mine_aboutus_companyintroduce)
    public void llyMainMineAboutUsCompanyIntroduceOnclick(){
        Intent intent = new Intent(activity, CompanyIntroduceActivity.class);
        activity.startActivity(intent);
    }
    @BindView(R.id.lly_main_mine_aboutus_yijianfankui)
    LinearLayout llyMainMineAboutUsYiJianFanKui;

    @OnClick(R.id.lly_main_mine_aboutus_yijianfankui)
    public void llyMainMineAboutUsYiJianFanKuiOnclick(){
        Intent intent = new Intent(activity, YiJianFanKuiActivity.class);
        activity.startActivity(intent);
    }
    @BindView(R.id.lly_main_mine_aboutus_yonghuxieyi)
    LinearLayout llyMainMineAboutUsYongHuXieYi;

    @OnClick(R.id.lly_main_mine_aboutus_yonghuxieyi)
    public void llyMainMineAboutUsYongHuXieYiOnclick(){
        Intent intent = new Intent(activity, YongHuXieYiActivity.class);
        activity.startActivity(intent);
    }


    public AboutUsController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }
}
