package com.zhyan.shandiankuaiyun.Main.MineFragment.GeRenXinXi.Email;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/4.
 */

public class EmailActivity extends BaseActivity {



    private EmailController emailController ;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_gerenxinxi_email_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void  initController(){
        emailController = new EmailController(this);
    }
}
