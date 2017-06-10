package com.shandian.lu.Main.IndexFragment.NewHuoYuanDetail;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by zhyan on 2017/6/10.
 */

public class NewHuoYuanDetailSelfActivity extends BaseActivity {

    private NewHuoYuanDetaiSelflController newHuoYuanDetaiSelflController;

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
        newHuoYuanDetaiSelflController = new NewHuoYuanDetaiSelflController(this);
    }
}
