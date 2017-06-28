package com.shandian.lu.Main.IndexFragment.NewAdsDetail;

import android.app.Activity;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.shandian.lu.BaseController;
import com.shandian.lu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/28.
 */

public class NewAdsDetailController extends BaseController{


    @BindView(R.id.rly_new_main_ads_detail_back)
    RelativeLayout rlyNewMainAdsDetailBack;
    @OnClick(R.id.rly_new_main_ads_detail_back)
    public void rlyNewMainAdsDetailBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.wv_new_ads_detail)
    WebView wvNewAdsDetail;
    public NewAdsDetailController(Activity activity1){
        activity = activity1;
        init();
    }


    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initWebView();
    }



    private void initWebView(){
        String url = activity.getIntent().getStringExtra("url");
        if(url != null) {
            /*Toast.makeText(activity,"url:"+url,Toast.LENGTH_LONG).show();*/
            wvNewAdsDetail.loadUrl(url);
            wvNewAdsDetail.setWebViewClient(new WebViewClient());
        }
    }

}
