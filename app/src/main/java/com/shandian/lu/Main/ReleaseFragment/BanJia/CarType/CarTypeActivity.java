package com.shandian.lu.Main.ReleaseFragment.BanJia.CarType;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/16.
 */

public class CarTypeActivity extends BaseActivity{


    private CarTypeController carTypeController;



    @Override
    protected void setContentView() {

        setContentView(R.layout.activity_main_release_banjia_leibie_lly);

    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }


    private void initController(){
        carTypeController = new CarTypeController(this);
    }
}
