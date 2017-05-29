package com.zhyan.shandiankuaiyun.Main.MineFragment.WoDeCheYuan;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.R;

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
