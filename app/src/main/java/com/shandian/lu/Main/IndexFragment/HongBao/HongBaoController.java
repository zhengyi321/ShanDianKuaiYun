package com.shandian.lu.Main.IndexFragment.HongBao;

import android.app.Activity;
import android.widget.RelativeLayout;

import com.shandian.lu.BaseController;
import com.shandian.lu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/7.
 */

public class HongBaoController extends BaseController {


    public HongBaoController(Activity activity1){
        activity = activity1;
        init();
    }


    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }
    @BindView(R.id.rly_new_main_index_hongbao_back)
    RelativeLayout rlyNewMainIndexHongBaoBack;
    @OnClick(R.id.rly_new_main_index_hongbao_back)
    public void rlyNewMainIndexHongBaoBackOnclick(){
        activity.finish();
    }
}
