package com.shandian.lu.Main.MineFragment.JiFenShangCheng.SuoYouShangPin.DuiHuanJiLu;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/5.
 */

public class DuiHuanJiLuActivity extends BaseActivity {


    private DuiHuanJiLuController duiHuanJiLuController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_jifenshangcheng_suoyoushangpin_duihuanjilu_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }
    private void initController(){
        duiHuanJiLuController = new DuiHuanJiLuController(this);
    }
}
