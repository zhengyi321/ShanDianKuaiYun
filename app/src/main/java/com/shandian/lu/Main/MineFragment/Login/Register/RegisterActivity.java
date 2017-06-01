package com.shandian.lu.Main.MineFragment.Login.Register;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

/**
 * Created by az on 2017/4/27.
 */

public class RegisterActivity extends BaseActivity{
    private RegisterController registerController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_login_reg_lly);
    }

    @Override
    protected void init() {
        initController();
    }
    private void initController(){
        registerController = new RegisterController(this);
    }
}
