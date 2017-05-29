package com.zhyan.shandiankuaiyun.Main.MineFragment.Login.Register.UserData;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/4/27.
 */

public class UserDataActivity extends BaseActivity {

    private UserDataController userDataController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_login_reg_userdata_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }
    private void initController(){
        userDataController = new UserDataController(this);
    }



    protected void onDestroy(){
        super.onDestroy();
        userDataController.onDestroy();
    }
}
