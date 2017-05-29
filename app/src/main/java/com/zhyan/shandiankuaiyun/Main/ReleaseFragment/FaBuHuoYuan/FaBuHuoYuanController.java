package com.zhyan.shandiankuaiyun.Main.ReleaseFragment.FaBuHuoYuan;

import android.app.Activity;
import android.widget.RelativeLayout;

import com.zhyan.shandiankuaiyun.BaseController;
import com.zhyan.shandiankuaiyun.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/5/17.
 */

public class FaBuHuoYuanController extends BaseController {

    @BindView(R.id.rly_main_release_fabuhuoyuan_back)
    RelativeLayout rlyMainReleaseFaBuHuoWuBack;
    @OnClick(R.id.rly_main_release_fabuhuoyuan_back)
    public void rlyMainReleaseFaBuHuoWuBackOnclick(){
        activity.finish();
    }

    public FaBuHuoYuanController(Activity activity1){
        activity = activity1;
        init();
    }




    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }
}
