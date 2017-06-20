package com.shandian.lu.Main.MineFragment.JiFenShangCheng.QianDao;

import com.shandian.lu.BaseFragmentActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/5.
 */

public class QianDaoActivity extends BaseFragmentActivity {

    private QianDaoController qianDaoController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_mine_jifenshangcheng_qiandao_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        qianDaoController = new QianDaoController(this);
    }
}
