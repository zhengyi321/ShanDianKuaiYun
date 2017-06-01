package com.shandian.lu.Main.MineFragment.CommonFaBuCheYuan;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/6/1.
 */

public class FaBuCheYuanActivity extends BaseActivity {


    private FaBuCheYuanController faBuCheYuanController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_fabucheyuan_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        faBuCheYuanController = new FaBuCheYuanController(this);
    }
}
