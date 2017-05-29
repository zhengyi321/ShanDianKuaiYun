package com.zhyan.shandiankuaiyun.Main.ReleaseFragment.ZhuanXianWuLiu.MiaoShu;

import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/14.
 */

public class MiaoShuActivity extends BaseActivity {



    private MiaoShuController miaoShuController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_release_zhuanxianwuliu_miaoshu_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        miaoShuController = new MiaoShuController(this);
    }
}
