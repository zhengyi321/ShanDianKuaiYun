package com.shandian.lu.Main.MineFragment.WoDeCheYuan;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/3.
 */

public class WoDeCheYuanActivity extends BaseActivity {

    private WoDeCheYuanController woDeCheYuanController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_wodecheyuan_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        woDeCheYuanController = new WoDeCheYuanController(this);
    }
}
