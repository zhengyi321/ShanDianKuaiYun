package com.shandian.lu.Main.IndexFragment.BaiDuRoutePlan;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by zhyan on 2017/6/11.
 */

public class NewBaiDuRoutePlanActivity extends BaseActivity{


    private NewBaiDuRoutePlanController newBaiDuRoutePlanController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_baidu_route_plan_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }


    private void initController(){
        newBaiDuRoutePlanController = new NewBaiDuRoutePlanController(this);
    }


    @Override
    public void onPause() {
        super.onPause();
        newBaiDuRoutePlanController.onPause();

    }

    @Override
    public void onResume() {
        super.onResume();
        newBaiDuRoutePlanController.onResume();

    }

    @Override
    public void onDestroy() {
       super.onDestroy();
        newBaiDuRoutePlanController.onDestroy();
    }
}
