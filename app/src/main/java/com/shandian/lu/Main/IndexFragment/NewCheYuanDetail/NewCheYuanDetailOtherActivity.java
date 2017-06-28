package com.shandian.lu.Main.IndexFragment.NewCheYuanDetail;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/10.
 */

public class NewCheYuanDetailOtherActivity extends BaseActivity {


    private NewCheYuanDetailOtherV2Controller newCheYuanOtherV2Controller;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_other_cheyuanxiangqing_v2_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }


    private void initController(){
        newCheYuanOtherV2Controller = new NewCheYuanDetailOtherV2Controller(this);
    }
}
