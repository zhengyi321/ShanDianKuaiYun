package com.zhyan.shandiankuaiyun.Main.MineFragment.RenZheng.ShiMingRenZheng;

import android.app.Activity;
import android.widget.RelativeLayout;

import com.zhyan.shandiankuaiyun.BaseController;
import com.zhyan.shandiankuaiyun.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/5/20.
 */

public class ShiMingRenZhengController extends BaseController {

    @BindView(R.id.rly_main_mine_renzheng_shimingrenzheng_back)
    RelativeLayout rlyMainMineRenZhengShiMingRenZhengBack;
    @OnClick(R.id.rly_main_mine_renzheng_shimingrenzheng_back)
    public void rlyMainMineRenZhengShiMingRenZhengBackOnclick(){
        activity.finish();
    }

    public ShiMingRenZhengController(Activity activity1){
        activity = activity1;
        init();
    }


    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }
}
