package com.shandian.lu.Main.MineFragment.RenZheng;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/3.
 */

public class RenZhengActivity extends BaseActivity {

    private RenZhengController renZhengController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_renzheng_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }
    private void initController(){
        renZhengController = new RenZhengController(this);
    }

}
