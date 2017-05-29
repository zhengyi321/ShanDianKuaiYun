package com.zhyan.shandiankuaiyun.Main.MineFragment.AboutUs.CompanyIntroduce;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.R;

/**
 * Created by az on 2017/5/3.
 */

public class CompanyIntroduceActivity extends BaseActivity {



    private CompanyIntroduceController companyIntroduceController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_aboutus_companyintroduce_lly);
    }

    @Override
    protected void init() {
        initController();
    }

    private void initController(){
        companyIntroduceController = new CompanyIntroduceController(this);
    }
}
