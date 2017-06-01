package com.shandian.lu.Main.MineFragment.Login.FindPass;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

/**
 * Created by az on 2017/4/27.
 */

public class FindPassActivity extends BaseActivity {

    private FindPassController findPassController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_login_findpass_lly);
    }

    @Override
    protected void init() {
        initController();
    }
    private void initController(){
        findPassController = new FindPassController(this);
    }
}
