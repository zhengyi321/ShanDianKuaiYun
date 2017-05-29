package com.zhyan.shandiankuaiyun.Main.MineFragment.GeRenXinXi.XiuGaiShouJi;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.R;

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
