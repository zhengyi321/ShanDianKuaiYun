package com.shandian.lu.Main.IndexFragment.CheYuanList;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/9.
 */

public class CheYuanListActivity extends BaseActivity {


    private CheYuanListController cheYuanListController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_cheyuanlist_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        cheYuanListController = new CheYuanListController(this);
    }
}
