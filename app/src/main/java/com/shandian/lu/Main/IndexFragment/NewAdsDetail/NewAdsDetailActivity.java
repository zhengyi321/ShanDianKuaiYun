package com.shandian.lu.Main.IndexFragment.NewAdsDetail;

import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/28.
 */

public class NewAdsDetailActivity extends BaseActivity {


    @BindView(R.id.wv_new_ads_detail)
    WebView wvNewAdsDetail;
    private NewAdsDetailController newAdsDetailController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_ads_detail_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }
    private void initController(){
        newAdsDetailController = new NewAdsDetailController(this);
    }



}
