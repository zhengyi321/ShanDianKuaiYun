package com.zhyan.shandiankuaiyun.Main.MineFragment.JiFenShangCheng.SuoYouShangPin;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.Main.MineFragment.JiFenShangCheng.JiFenShangChengController;
import com.zhyan.shandiankuaiyun.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/5.
 */

public class SuoYouShangPinActivity extends BaseActivity {

    private SuoYouShangPinController suoYouShangPinController;
    @Override
    protected void setContentView() {
     setContentView(R.layout.activity_main_mine_jifenshangcheng_suoyoushangpin_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        suoYouShangPinController = new SuoYouShangPinController(this);
    }
}
