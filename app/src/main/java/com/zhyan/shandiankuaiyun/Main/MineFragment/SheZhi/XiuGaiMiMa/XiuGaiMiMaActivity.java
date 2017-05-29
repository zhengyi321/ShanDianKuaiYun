package com.zhyan.shandiankuaiyun.Main.MineFragment.SheZhi.XiuGaiMiMa;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.R;

import butterknife.ButterKnife;
import cn.smssdk.SMSSDK;

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
