package com.shandian.lu.Main.MineFragment.JiFenShangCheng.JiFenTiXian;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/5.
 */

public class JiFenTiXianActivity extends BaseActivity {




    private JiFenTiXianController jiFenTiXianController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_jifenshangcheng_jifentixian_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        jiFenTiXianController = new JiFenTiXianController(this);
    }
}
