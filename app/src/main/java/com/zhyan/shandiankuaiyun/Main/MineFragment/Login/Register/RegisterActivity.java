package com.zhyan.shandiankuaiyun.Main.MineFragment.Login.Register;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.R;

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
