package com.shandian.lu.Main.IndexFragment.HongBao;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/7.
 */

public class HongBaoActivity extends BaseActivity {


    private HongBaoController hongBaoController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_index_hongbao_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        hongBaoController = new HongBaoController(this);
    }
}
