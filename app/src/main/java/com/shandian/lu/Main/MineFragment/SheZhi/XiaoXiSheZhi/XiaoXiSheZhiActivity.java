package com.shandian.lu.Main.MineFragment.SheZhi.XiaoXiSheZhi;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/4.
 */

public class XiaoXiSheZhiActivity extends BaseActivity {


    private XiaoXiSheZhiController xiaoXiSheZhiController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_shezhi_xiaoxishezhi_lly);
    }

    @Override
    protected void init() {

        ButterKnife.bind(this);
        initController();
    }


    private void initController(){
        xiaoXiSheZhiController = new XiaoXiSheZhiController(this);
    }
}
