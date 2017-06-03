package com.shandian.lu.Main.IndexFragment.WebView;

import android.webkit.WebView;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/3.
 */

public class WebViewActivity extends BaseActivity {

    @BindView(R.id.wv_main_index)
    WebView wvMainIndex;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_index_wv_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initWebView();
    }
    private void initWebView(){
        String url = getIntent().getStringExtra("url");
        if(url != null) {
            wvMainIndex.loadUrl(url);
        }
    }
}
