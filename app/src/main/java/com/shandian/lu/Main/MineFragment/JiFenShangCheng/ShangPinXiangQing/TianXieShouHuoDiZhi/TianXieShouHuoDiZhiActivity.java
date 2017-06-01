package com.shandian.lu.Main.MineFragment.JiFenShangCheng.ShangPinXiangQing.TianXieShouHuoDiZhi;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/5.
 */

public class TianXieShouHuoDiZhiActivity extends BaseActivity {

    private TianXieShouHuoDiZhiController tianXieShouHuoDiZhiController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_jifenshangcheng_shangpinxiangqing_tianxieshouhuodizhi_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        tianXieShouHuoDiZhiController = new TianXieShouHuoDiZhiController(this);
    }
}
