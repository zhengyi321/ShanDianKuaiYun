package com.shandian.lu.Main.MineFragment;

import android.view.View;

import com.shandian.lu.BaseController;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/7.
 */

public class NewMainMineController extends BaseController {


    public NewMainMineController(View view1){
        view  = view1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,view);
    }
}
