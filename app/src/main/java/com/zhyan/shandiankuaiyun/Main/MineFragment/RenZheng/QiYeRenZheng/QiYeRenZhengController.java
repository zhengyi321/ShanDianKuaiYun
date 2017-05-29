package com.zhyan.shandiankuaiyun.Main.MineFragment.RenZheng.QiYeRenZheng;

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

public class QiYeRenZhengController extends BaseController {

    @BindView(R.id.rly_main_mine_renzheng_qiyerenzheng_back)
    RelativeLayout rlyMainMineRenZhengQiYeRenZhengBack;
    @OnClick(R.id.rly_main_mine_renzheng_qiyerenzheng_back)
    public void rlyMainMineRenZhengQiYeRenZhengBackOnClick(){
        activity.finish();
    }

    public QiYeRenZhengController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }
}
