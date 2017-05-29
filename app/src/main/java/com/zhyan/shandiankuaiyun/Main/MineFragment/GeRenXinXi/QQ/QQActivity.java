package com.zhyan.shandiankuaiyun.Main.MineFragment.GeRenXinXi.QQ;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/4.
 */

public class QQActivity extends BaseActivity {


    private QQController qqController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_gerenxinxi_qq_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        qqController = new QQController(this);
    }
}
