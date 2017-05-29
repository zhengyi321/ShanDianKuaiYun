package com.zhyan.shandiankuaiyun.Main.MineFragment.GeRenXinXi.BianJiNiChen;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/4.
 */

public class BianJiNiChenActivity extends BaseActivity{


    private BianJiNiChenController bianJiNiChenController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_gerenxinxi_bianjinichen_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        bianJiNiChenController = new BianJiNiChenController(this);
    }
}
