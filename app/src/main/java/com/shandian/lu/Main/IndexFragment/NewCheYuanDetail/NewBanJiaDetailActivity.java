package com.shandian.lu.Main.IndexFragment.NewCheYuanDetail;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/7/12.
 */

public class NewBanJiaDetailActivity extends BaseActivity {

    private NewBanJiaDetailController newBanJiaDetailController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_banjiaxiangqing_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        newBanJiaDetailController = new NewBanJiaDetailController(this);
    }
}
