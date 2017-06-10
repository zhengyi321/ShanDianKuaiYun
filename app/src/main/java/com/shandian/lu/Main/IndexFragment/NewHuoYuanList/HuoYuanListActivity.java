package com.shandian.lu.Main.IndexFragment.NewHuoYuanList;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/10.
 */

public class HuoYuanListActivity extends BaseActivity {


    private HuoYuanListController huoYuanListController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_huoyuanlist_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();

    }


    private void initController(){
        huoYuanListController = new HuoYuanListController(this);
    }
}
