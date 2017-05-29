package com.zhyan.shandiankuaiyun.Main.MineFragment.JiFenShangCheng.SuoYouShangPin.DuiHuanJiLu.JiFenMingXi;

import android.app.Activity;
import android.content.Intent;
import android.widget.RelativeLayout;

import com.zhyan.shandiankuaiyun.BaseController;
import com.zhyan.shandiankuaiyun.Main.MineFragment.JiFenShangCheng.SuoYouShangPin.DuiHuanJiLu.JiFenMingXi.JiFenShuoMing.JiFenShuoMingActivity;
import com.zhyan.shandiankuaiyun.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/5/5.
 */

public class JiFenMingXiController extends BaseController {
    @BindView(R.id.rly_main_mine_jifenshangcheng_duihuanjilu_jifenmingxi_jifenshuoming)
    RelativeLayout rlyMainMineJiFenShangChengDuiHuanJiLuJiFenMingXiJiFenShuoMing;
    @OnClick(R.id.rly_main_mine_jifenshangcheng_duihuanjilu_jifenmingxi_jifenshuoming)
    public void rlyMainMineJiFenShangChengDuiHuanJiLuJiFenMingXiJiFenShuoMingOnclick(){
        Intent intent = new Intent(activity, JiFenShuoMingActivity.class);
        activity.startActivity(intent);
    }
    public JiFenMingXiController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }
}
