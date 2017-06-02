package com.shandian.lu.Main.MineFragment.Login;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/4/26.
 */

public class LoginActivity extends BaseActivity{


    private LoginController loginController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_login_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();

    }
    private void initController(){
        loginController = new LoginController(this);
    }
}
