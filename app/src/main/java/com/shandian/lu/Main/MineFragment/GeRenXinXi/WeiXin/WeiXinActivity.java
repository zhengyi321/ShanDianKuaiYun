package com.shandian.lu.Main.MineFragment.GeRenXinXi.WeiXin;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/4.
 */

public class WeiXinActivity extends BaseActivity {

    private WeiXinController weiXinController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_gerenxinxi_weixin_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }
    private void initController(){
        weiXinController = new WeiXinController(this);
    }
}
