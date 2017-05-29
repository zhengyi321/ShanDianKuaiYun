package com.zhyan.shandiankuaiyun.Main.MineFragment.JiFenShangCheng;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/5.
 */

public class JiFenShangChengActivity extends BaseActivity {

    private JiFenShangChengController jiFenShangChengController;
    @Override
    protected void setContentView() {
     setContentView(R.layout.activity_main_mine_jifenshangcheng_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        jiFenShangChengController = new JiFenShangChengController(this);
    }
}
