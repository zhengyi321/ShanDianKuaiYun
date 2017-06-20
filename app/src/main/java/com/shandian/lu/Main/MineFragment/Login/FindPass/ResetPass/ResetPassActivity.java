package com.shandian.lu.Main.MineFragment.Login.FindPass.ResetPass;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/20.
 */

public class ResetPassActivity extends BaseActivity {

    private ResetPassController resetPassController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_login_resetpass_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }
    private void initController(){
        resetPassController = new ResetPassController(this);
    }
}
