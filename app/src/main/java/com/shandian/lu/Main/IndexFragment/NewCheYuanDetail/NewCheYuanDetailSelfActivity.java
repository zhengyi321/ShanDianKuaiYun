package com.shandian.lu.Main.IndexFragment.NewCheYuanDetail;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/9.
 */

public class NewCheYuanDetailSelfActivity extends BaseActivity {


    private NewCheYuanDetailSelfV2Controller newCheYuanDetailSelfV2Controller;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_self_cheyuanxiangqing_v2_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        newCheYuanDetailSelfV2Controller = new NewCheYuanDetailSelfV2Controller(this);
    }
}
