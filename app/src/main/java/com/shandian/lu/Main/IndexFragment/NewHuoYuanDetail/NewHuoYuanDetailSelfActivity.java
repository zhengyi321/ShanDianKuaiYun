package com.shandian.lu.Main.IndexFragment.NewHuoYuanDetail;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by zhyan on 2017/6/10.
 */

public class NewHuoYuanDetailSelfActivity extends BaseActivity {

    private NewHuoYuanDetailSelfV2Controller newHuoYuanDetailSelfV2Controller;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_self_huoyuanxiangqing_v2_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        newHuoYuanDetailSelfV2Controller = new NewHuoYuanDetailSelfV2Controller(this);
    }
}
