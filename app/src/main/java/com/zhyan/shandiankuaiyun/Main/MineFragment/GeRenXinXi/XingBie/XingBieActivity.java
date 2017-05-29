package com.zhyan.shandiankuaiyun.Main.MineFragment.GeRenXinXi.XingBie;

import android.widget.Button;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/4.
 */

public class XingBieActivity extends BaseActivity {
    private XingBieController xingBieController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_gerenxinxi_xingbie_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        iniController();

    }

    private void iniController(){
        xingBieController = new XingBieController(this);
    }
}
