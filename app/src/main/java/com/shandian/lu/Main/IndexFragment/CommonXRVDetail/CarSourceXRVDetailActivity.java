package com.shandian.lu.Main.IndexFragment.CommonXRVDetail;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/4/28.
 */

public class CarSourceXRVDetailActivity extends BaseActivity{


    private CarSourcesXRVDetailController carSourcesXRVDetailController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_index_carsource_detail_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        carSourcesXRVDetailController = new CarSourcesXRVDetailController(this);
    }
}
