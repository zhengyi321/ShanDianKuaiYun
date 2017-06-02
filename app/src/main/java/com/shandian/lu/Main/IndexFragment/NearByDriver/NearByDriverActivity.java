package com.shandian.lu.Main.IndexFragment.NearByDriver;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/23.
 */

public class NearByDriverActivity extends BaseActivity{

    private NearByDriverController nearByDriverController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_index_nearby_driver_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }


    private void initController(){
        nearByDriverController = new NearByDriverController(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        nearByDriverController.onDestroy();
    }
}
