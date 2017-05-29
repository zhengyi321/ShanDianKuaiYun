package com.zhyan.shandiankuaiyun.Main.MineFragment.WoDeHuoYuan;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.Main.MineFragment.WoDeCheYuan.WoDeCheYuanController;
import com.zhyan.shandiankuaiyun.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/3.
 */

public class WoDeHuoYuanActivity extends BaseActivity {

    private WoDeHuoYuanController woDeHuoYuanController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_wodehuoyuan_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        woDeHuoYuanController = new WoDeHuoYuanController(this);
    }
}
