package com.shandian.lu.Main.MineFragment.WoDeHuoYuan;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/12.
 */

public class NewWoDeHuoYuanActivity extends BaseActivity {


    private NewWoDeHuoYuanController newWoDeHuoYuanController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_wodehuoyuan_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        newWoDeHuoYuanController = new NewWoDeHuoYuanController(this);
    }

}
