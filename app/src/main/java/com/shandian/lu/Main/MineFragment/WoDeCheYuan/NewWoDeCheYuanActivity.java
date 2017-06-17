package com.shandian.lu.Main.MineFragment.WoDeCheYuan;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.Main.MineFragment.WoDeHuoYuan.NewWoDeHuoYuanController;
import com.shandian.lu.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/12.
 */

public class NewWoDeCheYuanActivity extends BaseActivity {


    private NewWoDeCheYuanController newWoDeCheYuanController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_wodecheyuan_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        newWoDeCheYuanController = new NewWoDeCheYuanController(this);
    }


    @Override
    protected void onResume(){
        super.onResume();
        newWoDeCheYuanController.onResume();

    }
}
