package com.shandian.lu.Main.MineFragment.JiFenShangCheng.ShangPinXiangQing;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/5.
 */

public class ShangPinXiangQingActivity extends BaseActivity {


    private ShangPinXiangQingController shangPinXiangQingController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_jifenshangcheng_shangpinxiangqing_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        shangPinXiangQingController = new ShangPinXiangQingController(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        shangPinXiangQingController.getGoodsDetailFromNet();
    }
}
