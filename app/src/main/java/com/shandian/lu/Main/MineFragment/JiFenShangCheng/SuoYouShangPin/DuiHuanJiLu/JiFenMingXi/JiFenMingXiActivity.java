package com.shandian.lu.Main.MineFragment.JiFenShangCheng.SuoYouShangPin.DuiHuanJiLu.JiFenMingXi;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/5.
 */

public class JiFenMingXiActivity extends BaseActivity {


    private JiFenMingXiController jiFenMingXiController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_jifenshangcheng_suoyoushangpin_duihuanjilu_jifenmingxi_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }
    private void initController(){
        jiFenMingXiController = new JiFenMingXiController(this);
    }
}
