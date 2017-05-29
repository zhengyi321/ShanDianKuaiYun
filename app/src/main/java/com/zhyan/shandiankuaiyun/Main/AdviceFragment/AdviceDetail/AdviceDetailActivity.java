package com.zhyan.shandiankuaiyun.Main.AdviceFragment.AdviceDetail;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/20.
 */

public class AdviceDetailActivity extends BaseActivity {


    private AdviceDetailController adviceDetailController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_advice_detail_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        adviceDetailController = new AdviceDetailController(this);
    }
}
