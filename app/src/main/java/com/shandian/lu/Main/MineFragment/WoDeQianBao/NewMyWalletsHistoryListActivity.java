package com.shandian.lu.Main.MineFragment.WoDeQianBao;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/16.
 */

public class NewMyWalletsHistoryListActivity extends BaseActivity {


    private NewMyWalletsHistoryListController newMyWalletsHistoryListController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_mywallet_historylist_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        newMyWalletsHistoryListController = new NewMyWalletsHistoryListController(this);
    }
    @Override
    protected void onResume(){
        super.onResume();
        newMyWalletsHistoryListController.onResume();
    }
}
