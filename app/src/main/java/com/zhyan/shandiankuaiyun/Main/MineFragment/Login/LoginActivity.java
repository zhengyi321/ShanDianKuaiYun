package com.zhyan.shandiankuaiyun.Main.MineFragment.Login;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.R;

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
