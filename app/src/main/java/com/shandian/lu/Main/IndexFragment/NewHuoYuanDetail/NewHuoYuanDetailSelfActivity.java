package com.shandian.lu.Main.IndexFragment.NewHuoYuanDetail;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by zhyan on 2017/6/10.
 */

public class NewHuoYuanDetailSelfActivity extends BaseActivity {

    private NewHuoYuanDetailSelfController newHuoYuanDetailSelflController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_self_huoyuanxiangqing_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        newHuoYuanDetailSelflController = new NewHuoYuanDetailSelfController(this);
    }
}
