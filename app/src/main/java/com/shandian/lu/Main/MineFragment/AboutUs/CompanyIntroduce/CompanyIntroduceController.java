package com.shandian.lu.Main.MineFragment.AboutUs.CompanyIntroduce;

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

public class CompanyIntroduceController extends BaseController {

    @BindView(R.id.rly_main_mine_aboutus_companyintroduce_back)
    RelativeLayout rlyMainMineAboutCompanyIntroduceBack;
    @OnClick(R.id.rly_main_mine_aboutus_companyintroduce_back)
    public void rlyMainMineAboutCompanyIntroduceBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.tv_main_mine_aboutus_companyintroduce_content)
    TextView tvMainMineAboutUsCompanyIntroduceContent;
    @BindView(R.id.tv_main_mine_aboutus_companyintroduce_tel)
    TextView tvMainMineAboutUsCompanyIntroduceTel;
    @BindView(R.id.tv_main_mine_aboutus_companyintroduce_wx)
    TextView tvMainMineAboutUsCompanyIntroduceWX;
    @BindView(R.id.tv_main_mine_aboutus_companyintroduce_qq)
    TextView tvMainMineAboutUsCompanyIntroduceQQ;
    public CompanyIntroduceController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initCompanyFromNet();
    }
    private void initCompanyFromNet(){
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
                tvMainMineAboutUsCompanyIntroduceContent.setText(companyIntroduceBean.getContent().getContent());
                tvMainMineAboutUsCompanyIntroduceTel.setText("手机: "+companyIntroduceBean.getContent().getIphone());
                tvMainMineAboutUsCompanyIntroduceWX.setText("微信: "+companyIntroduceBean.getContent().getWecat());
                tvMainMineAboutUsCompanyIntroduceQQ.setText("QQ: "+companyIntroduceBean.getContent().getQq());
            }
        });
    }
}
