package com.shandian.lu.Main.MineFragment.JiFenShangCheng.SuoYouShangPin.DuiHuanJiLu;

import android.app.Activity;
import android.content.Intent;
import android.widget.RelativeLayout;

import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MineFragment.JiFenShangCheng.SuoYouShangPin.DuiHuanJiLu.JiFenMingXi.JiFenMingXiActivity;
import com.shandian.lu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/5/5.
 */

public class DuiHuanJiLuController extends BaseController {

    @BindView(R.id.rly_main_mine_jifenshangcheng_suoyoushangpin_duihuanjilu_jifenmingxi)
    RelativeLayout rlyMainMineJiFenShangChengSuoYouShangPinDuiHuanJiLuJiFenMingXi;
    @OnClick(R.id.rly_main_mine_jifenshangcheng_suoyoushangpin_duihuanjilu_jifenmingxi)
    public void rlyMainMineJiFenShangChengSuoYouShangPinDuiHuanJiLuJiFenMingXiOnclick(){
        Intent intent = new Intent(activity, JiFenMingXiActivity.class);
        activity.startActivity(intent);
    }
    public DuiHuanJiLuController(Activity activity1){
        activity = activity1;
        init();

    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }
}
