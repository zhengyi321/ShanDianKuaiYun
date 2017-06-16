package com.shandian.lu.Main.MineFragment.WoDeQianBao.NewTiXian;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/16.
 */

public class NewMyWalletTiXianActivity extends BaseActivity {
    private NewMyWalletTiXianController newMyWalletTiXianController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_mywallet_tixian_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        newMyWalletTiXianController = new NewMyWalletTiXianController(this);
    }
}
