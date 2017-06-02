package com.shandian.lu.Main.MineFragment.GeRenXinXi.XiuGaiShouJi;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/4.
 */

public class XiuGaiShouJiActivity extends BaseActivity{


    private XiuGaiShouJiController xiuGaiShouJiController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_gerenxinxi_xiugaishouji_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }


    private void initController(){
        xiuGaiShouJiController = new XiuGaiShouJiController(this);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        xiuGaiShouJiController.onDestroy();
    }
}
