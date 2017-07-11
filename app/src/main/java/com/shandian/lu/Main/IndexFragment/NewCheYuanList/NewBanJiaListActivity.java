package com.shandian.lu.Main.IndexFragment.NewCheYuanList;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

/**
 * Created by Administrator on 2017/7/11.
 */

public class NewBanJiaListActivity extends BaseActivity {


    private NewBanJiaListController newBanJiaListController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_banjialist_lly);
    }

    @Override
    protected void init() {
        initController();
    }

    private void initController(){
        newBanJiaListController = new NewBanJiaListController(this);
    }
}
