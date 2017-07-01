package com.shandian.lu.Main.IndexFragment.NewCheYuanDetail;

import android.content.Intent;
import android.widget.RelativeLayout;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.Main.MessageFragment.Chat.ChatActivity;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.R;
import com.zhyan.myhuanxin.EaseConstant;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/10.
 */

public class NewCheYuanDetailOtherActivity extends BaseActivity {



    private NewCheYuanDetailOtherV2Controller newCheYuanOtherV2Controller;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_other_cheyuanxiangqing_v2_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }


    private void initController(){
        newCheYuanOtherV2Controller = new NewCheYuanDetailOtherV2Controller(this);
    }
}
