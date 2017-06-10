package com.shandian.lu.Main.IndexFragment.NewCheYuanDetail;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/10.
 */

public class NewCheYuanDetailOtherActivity extends BaseActivity {


    private NewCheYuanDetailOtherController newCheYuanOtherController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_other_cheyuanxiangqing_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }


    private void initController(){
        newCheYuanOtherController = new NewCheYuanDetailOtherController(this);
    }
}
