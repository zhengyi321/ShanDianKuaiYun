package com.shandian.lu.Main.AdviceFragment.AdviceDetail;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynewslayoutlib.TextView.HTMLView;
import com.shandian.lu.BaseController;
import com.shandian.lu.NetWork.AdviceNetWork;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Utils.MyTagHandler;
import com.zhyan.shandiankuaiyunlib.Bean.AdviceDetailBean;


import java.net.URL;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by az on 2017/5/20.
 */

public class AdviceDetailController extends BaseController {

    @BindView(R.id.rly_main_advice_detail_back)
    RelativeLayout rlyMainAdviceDetailBack;
    private String nextId = "0",previousId = "0";
    @OnClick(R.id.rly_main_advice_detail_back)
    public void rlyMainAdviceDetailBackOnclick(){
        activity.finish();
    }

    @BindView(R.id.tv_main_advice_detail_content_title)
    TextView tvMainAdviceDetailContentTitle;
    @BindView(R.id.tv_main_advice_detail_content_time)
    TextView tvMainAdviceDetailContentTime;
    @BindView(R.id.wv_main_advice_detail_content_news)
    WebView wvMainAdviceDetailContentNews;
    @BindView(R.id.tv_main_advice_detail_content_news)
    TextView tvMainAdviceDetailContentNews;
    @BindView(R.id.tv_main_advice_detail_content_previous)
    TextView tvMainAdviceDetailContentPrevious;
    @BindView(R.id.tv_main_advice_detail_content_next)
    TextView tvMainAdviceDetailContentNext;
    @BindView(R.id.lly_main_advice_detail_content_shoucang)
    LinearLayout llyMainAdviceDetailContentShouCang;
    @OnClick(R.id.lly_main_advice_detail_content_shoucang)
    public void llyMainAdviceDetailContentShouCangOnclick(){

    }
    @BindView(R.id.lly_main_advice_detail_content_previus)
    LinearLayout llyMainAdviceDetailContentPrevius;
    @OnClick(R.id.lly_main_advice_detail_content_previus)
    public void llyMainAdviceDetailContentPreviusOnclick(){
        getDetailFromNet(previousId);
    }
    @BindView(R.id.lly_main_advice_detail_content_next)
    LinearLayout llyMainAdviceDetailContentNext;
    @OnClick(R.id.lly_main_advice_detail_content_next)
    public void llyMainAdviceDetailContentNextOnclick(){
        getDetailFromNet(nextId);
    }

    public AdviceDetailController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initAdviceDetail();
    }
    private void initAdviceDetail(){
        String id = activity.getIntent().getStringExtra("id");
        /*Toast.makeText(activity,"id:"+id,Toast.LENGTH_LONG).show();*/
        if(id == null){
            return;
        }
        getDetailFromNet(id);
    }

    private void getDetailFromNet(String id){
        AdviceNetWork adviceNetWork = new AdviceNetWork();
        adviceNetWork.getAdviceDetailFromNet(id, new Observer<AdviceDetailBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(AdviceDetailBean adviceDetailBean) {
                if(adviceDetailBean.getStatus() == 0) {
                    initAdviceDetailByNet(adviceDetailBean);
                }
            }
        });
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void initAdviceDetailByNet(AdviceDetailBean adviceDetailBean){
        if(adviceDetailBean.getContent().getLast_one() == null){
            llyMainAdviceDetailContentPrevius.setVisibility(View.GONE);
            nextId = adviceDetailBean.getContent().getNext_one().getId();
        }else{
            llyMainAdviceDetailContentPrevius.setVisibility(View.VISIBLE);
            previousId = adviceDetailBean.getContent().getLast_one().getId();
        }
        if(adviceDetailBean.getContent().getNext_one() == null){
            llyMainAdviceDetailContentNext.setVisibility(View.GONE);
            previousId = adviceDetailBean.getContent().getLast_one().getId();
        }else{
            llyMainAdviceDetailContentNext.setVisibility(View.VISIBLE);
            nextId = adviceDetailBean.getContent().getNext_one().getId();
        }
        tvMainAdviceDetailContentTitle.setText(adviceDetailBean.getContent().getList().getTitle().toString());
        tvMainAdviceDetailContentTime.setText(adviceDetailBean.getContent().getList().getCreate_time());
        String content = adviceDetailBean.getContent().getList().getContent();
        System.out.print("\nhtml:"+content);
        System.out.print("\nhtml:"+content);
        System.out.print("\nhtml:"+content);
        int indexOfImg = content.indexOf("img");
        if(indexOfImg < 0){
            content = content.replaceAll(" ","");
        }

       /* content = content.replaceAll("<imgsrc","<img src");*/
        int indexOfAnd = content.indexOf("&");
        if(indexOfAnd >= 0){
           /* Toast.makeText(activity,"yes",3000).show();*/
            Spanned spCon = Html.fromHtml(content);
            String html = "";
            if(indexOfImg >= 0){
                html = "<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>" +
                        "</head><body>"+spCon+"</body></html>";
                wvMainAdviceDetailContentNews.loadDataWithBaseURL(null,html, "text/html", "utf-8", null);
                WebSettings settings = wvMainAdviceDetailContentNews.getSettings();
                settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
                settings.setUseWideViewPort(true);
                settings.setLoadWithOverviewMode(true);
            }else {
                html = "<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/>" +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\" /></head><body>"+spCon+"</body></html>";
                wvMainAdviceDetailContentNews.loadDataWithBaseURL(null,html, "text/html", "utf-8", null);

            }
   /*     System.out.print("\nhtml:"+content);  <meta name="viewport" content="width=device-width, initial-scale=0.2" />
        System.out.print("\nhtml:"+content);
        System.out.print("\nhtml:"+content);
        System.out.print("\nhtml:"+content);*/


            tvMainAdviceDetailContentNews.setVisibility(View.GONE);
            wvMainAdviceDetailContentNews.setVisibility(View.VISIBLE);
        }else {
            /*Toast.makeText(activity,"no",3000).show();*/
            tvMainAdviceDetailContentNews.setVisibility(View.VISIBLE);
            wvMainAdviceDetailContentNews.setVisibility(View.GONE);
            tvMainAdviceDetailContentNews.setText(content);
        }


      /*  DownLoadUtil.setHtmlText(htvMainAdviceDetailContentNews,content,480,320);*/
       /* htvMainAdviceDetailContentNews.setHtml(content,
                new HtmlResImageGetter(htvMainAdviceDetailContentNews));*/
        /*Spanned charSequence =  Html.fromHtml(content);
      *//*  String strCont = charSequence.toString();
        strCont = strCont.replaceAll("<p>","\n");
        strCont = strCont.replaceAll("</p>","");
        strCont = strCont.replaceAll("<br/>","");*//*
        tvMainAdviceDetailContentNews.setText(charSequence);*/

        tvMainAdviceDetailContentNext.setText(adviceDetailBean.getContent().getNext_one().getTitle());
        tvMainAdviceDetailContentPrevious.setText(adviceDetailBean.getContent().getLast_one().getTitle());
        /*previousId = adviceDetailBean.getContent().getLast_one().getId();*/
       /* nextId = adviceDetailBean.getContent().getNext_one().getId();*/

    }



    Html.ImageGetter imgGetter = new Html.ImageGetter() {
        public Drawable getDrawable(String source) {
            Drawable drawable = null;
            Log.d("Image Path", source);
            URL url;
            try {
                url = new URL(source);
                drawable = Drawable.createFromStream(url.openStream(), "");
            } catch (Exception e) {
                return null;
            }
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable
                    .getIntrinsicHeight());
            return drawable;
        }
    };
}
