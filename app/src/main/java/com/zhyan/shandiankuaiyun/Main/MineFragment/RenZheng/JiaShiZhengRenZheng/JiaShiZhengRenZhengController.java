package com.zhyan.shandiankuaiyun.Main.MineFragment.RenZheng.JiaShiZhengRenZheng;

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

public class JiaShiZhengRenZhengController extends BaseController {
    @BindView(R.id.rly_main_mine_renzheng_jiashizhengrenzheng_back)
    RelativeLayout rlyMainMineRenZhengJiaShiZhengRenZhengBack;
    @OnClick(R.id.rly_main_mine_renzheng_jiashizhengrenzheng_back)
    public void rlyMainMineRenZhengJiaShiZhengRenZhengBackOnclick(){
        activity.finish();
    }






    public JiaShiZhengRenZhengController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }
}
