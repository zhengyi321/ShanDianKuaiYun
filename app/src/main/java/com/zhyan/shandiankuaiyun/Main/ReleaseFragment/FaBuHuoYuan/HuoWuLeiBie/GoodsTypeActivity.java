package com.zhyan.shandiankuaiyun.Main.ReleaseFragment.FaBuHuoYuan.HuoWuLeiBie;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.Main.ReleaseFragment.BanJia.CarType.CarTypeController;
import com.zhyan.shandiankuaiyun.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/16.
 */

public class GoodsTypeActivity extends BaseActivity{


    private GoodsTypeController GoodsTypeController;



    @Override
    protected void setContentView() {

        setContentView(R.layout.activity_main_release_fabuhuowu_huowuleibie_lly);

    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }


    private void initController(){
        GoodsTypeController = new GoodsTypeController(this);
    }
}
