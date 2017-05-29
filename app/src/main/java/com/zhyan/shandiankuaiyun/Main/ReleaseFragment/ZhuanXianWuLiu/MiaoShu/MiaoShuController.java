package com.zhyan.shandiankuaiyun.Main.ReleaseFragment.ZhuanXianWuLiu.MiaoShu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhyan.shandiankuaiyun.BaseController;
import com.zhyan.shandiankuaiyun.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/5/14.
 */

public class MiaoShuController extends BaseController {
    private final int OTHER_DESC = 98;
    @BindView(R.id.rly_main_release_zhuanxianwuliu_miaoshu_back)
    RelativeLayout rlyMainReleaseZhuanXianWuLiuMiaoShuBack;
    @OnClick(R.id.rly_main_release_zhuanxianwuliu_miaoshu_back)
    public void rlyMainReleaseZhuanXianWuLiuMiaoShuBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.rly_main_release_zhuanxianwuliu_miaoshu_finish)
    RelativeLayout rlyMainReleaseZhuanXianWuLiuMiaoShuFinish;
    @OnClick(R.id.rly_main_release_zhuanxianwuliu_miaoshu_finish)
    public void rlyMainReleaseZhuanXianWuLiuMiaoShuFinishOnclick(){
        Bundle bundle = new Bundle();
        String desc = etMainReleaseZhuanXianWuLiuMiaoShuDesc.getText().toString();
        bundle.putString("desc",desc);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        activity.setResult(OTHER_DESC,intent);
        activity.finish();
    }
    @BindView(R.id.et_main_release_zhuanxianwuliu_miaoshu_desc)
    EditText etMainReleaseZhuanXianWuLiuMiaoShuDesc;
    @BindView(R.id.tv_main_release_zhuanxianwuliu_miaoshu_clear)
    TextView tvMainReleaseZhuanXianWuLiuMiaoShuClear;
    @OnClick(R.id.tv_main_release_zhuanxianwuliu_miaoshu_clear)
    public void tvMainReleaseZhuanXianWuLiuMiaoShuClearOnclick(){
        etMainReleaseZhuanXianWuLiuMiaoShuDesc.setText("");
    }
    public MiaoShuController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }
}
