package com.shandian.lu.Main.MineFragment.AboutUs.YongHuXieYi;

import android.app.Activity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shandian.lu.BaseController;
import com.shandian.lu.NetWork.CompanyNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.CompanyIntroduceBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by az on 2017/5/22.
 */

public class YongHuXieYiController extends BaseController {


    @BindView(R.id.rly_main_mine_aboutus_yonghuxieyi_back)
    RelativeLayout rlyMainMineAboutUsYongHuXieYiBack;
    @OnClick(R.id.rly_main_mine_aboutus_yonghuxieyi_back)
    public void rlyMainMineAboutUsYongHuXieYiBackOnclick(){
        activity.finish();
    }

    @BindView(R.id.tv_main_mine_aboutus_yonghuxieyi)
    TextView tvMainMineAboutUsYongHuXieYi;
    public YongHuXieYiController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initYongHuXieYi();
    }


    private void initYongHuXieYi(){
        CompanyNetWork companyNetWork = new CompanyNetWork();
        companyNetWork.companyIntroduceFromNet(new Observer<CompanyIntroduceBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CompanyIntroduceBean companyIntroduceBean) {
                tvMainMineAboutUsYongHuXieYi.setText(companyIntroduceBean.getContent().getAgree_ment());
            }
        });
    }
}
