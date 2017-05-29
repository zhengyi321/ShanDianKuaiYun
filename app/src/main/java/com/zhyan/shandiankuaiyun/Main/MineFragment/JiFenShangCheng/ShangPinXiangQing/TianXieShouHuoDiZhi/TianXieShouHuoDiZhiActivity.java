package com.zhyan.shandiankuaiyun.Main.MineFragment.JiFenShangCheng.ShangPinXiangQing.TianXieShouHuoDiZhi;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.R;

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
