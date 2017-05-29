package com.zhyan.shandiankuaiyun.Main.IndexFragment.Common;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.R;

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
