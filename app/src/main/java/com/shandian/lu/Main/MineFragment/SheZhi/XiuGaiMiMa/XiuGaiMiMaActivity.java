package com.shandian.lu.Main.MineFragment.SheZhi.XiuGaiMiMa;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/4.
 */

public class XiuGaiMiMaActivity extends BaseActivity {

    private XiuGaiMiMaController xiuGaiMiMaController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_shezhi_xiugaimima_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        xiuGaiMiMaController = new XiuGaiMiMaController(this);
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        xiuGaiMiMaController.onDestroy();


    }
}
