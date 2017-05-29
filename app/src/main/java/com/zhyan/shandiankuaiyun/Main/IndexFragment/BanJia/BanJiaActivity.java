package com.zhyan.shandiankuaiyun.Main.IndexFragment.BanJia;

import android.widget.Toast;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/4/28.
 */

public class BanJiaActivity extends BaseActivity{


    private BanJiaController banJiaController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_index_banjia_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }


    private void initController(){
        banJiaController = new BanJiaController(this);
    }


    @Override
    protected void onResume(){
        super.onResume();
       /* Toast.makeText(this,"this is onResume",Toast.LENGTH_LONG).show();*/
        banJiaController.getDataFromNet(false);
    }
}
