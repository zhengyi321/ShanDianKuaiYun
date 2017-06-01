package com.shandian.lu.Main.MineFragment.GeRenXinXi.ErWeiMa;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/4.
 */

public class ErWeiMaActivity extends BaseActivity {

    private ErWeiMaController erWeiMaController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_gerenxinxi_erweima_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        erWeiMaController = new ErWeiMaController(this);
    }
}
