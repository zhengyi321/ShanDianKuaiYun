package com.shandian.lu.Main.IndexFragment.CommonXRVDetail;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/4/28.
 */

public class GoodsSourceXRVDetailActivity extends BaseActivity{


    private GoodsSourceXRVDetailController goodsSourceXRVDetailController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_index_goodssource_detail_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        goodsSourceXRVDetailController = new GoodsSourceXRVDetailController(this);
    }
}
