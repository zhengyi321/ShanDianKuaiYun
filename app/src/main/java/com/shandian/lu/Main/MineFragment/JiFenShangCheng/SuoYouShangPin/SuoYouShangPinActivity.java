package com.shandian.lu.Main.MineFragment.JiFenShangCheng.SuoYouShangPin;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/5.
 */

public class SuoYouShangPinActivity extends BaseActivity {

    private SuoYouShangPinController suoYouShangPinController;
    @Override
    protected void setContentView() {
     setContentView(R.layout.activity_main_mine_jifenshangcheng_suoyoushangpin_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        suoYouShangPinController = new SuoYouShangPinController(this);
    }
}
