package com.shandian.lu.Main.MineFragment.AboutUs.CompanyIntroduce;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

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
