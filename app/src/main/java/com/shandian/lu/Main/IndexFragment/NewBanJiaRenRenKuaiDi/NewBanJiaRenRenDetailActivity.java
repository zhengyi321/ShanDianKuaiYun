package com.shandian.lu.Main.IndexFragment.NewBanJiaRenRenKuaiDi;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/28.
 */

public class NewBanJiaRenRenDetailActivity extends BaseActivity {



    private NewBanJiaRenRenDetailController newBanJiaRenRenDetailController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_banjiarenrenxiangqing_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        newBanJiaRenRenDetailController = new NewBanJiaRenRenDetailController(this);
    }
}
