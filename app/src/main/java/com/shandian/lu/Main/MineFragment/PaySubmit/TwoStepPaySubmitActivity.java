package com.shandian.lu.Main.MineFragment.PaySubmit;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/13.
 */

public class TwoStepPaySubmitActivity extends BaseActivity {

    private TwoStepPaySubmitController twoStepPaySubmitController;


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_huoyuanxiangqing_two_paysubmit_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        intController();
    }

    private void intController(){
        twoStepPaySubmitController = new TwoStepPaySubmitController(this);
    }
}
