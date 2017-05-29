package com.zhyan.shandiankuaiyun.Main.MineFragment.SheZhi;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.R;

/**
 * Created by az on 2017/5/4.
 */

public class SheZhiActivity extends BaseActivity {

    private SheZhiController sheZhiController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_shezhi_lly);
    }

    @Override
    protected void init() {
        initController();
    }

    private void initController(){
        sheZhiController = new SheZhiController(this);
    }
}
