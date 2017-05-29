package com.zhyan.shandiankuaiyun.Main.MineFragment.Login.FindPass;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.Main.MineFragment.Login.FindPass.FindPassController;
import com.zhyan.shandiankuaiyun.R;

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
