package com.shandian.lu.Main.IndexFragment.Common;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/23.
 */

public class MySelfLocActivity extends BaseActivity {

    private MySelfLocController mySelfLocController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_index_common_myself_loc_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        mySelfLocController = new MySelfLocController(this);

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        mySelfLocController.onDestroy();
    }
}
