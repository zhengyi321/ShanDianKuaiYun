package com.shandian.lu.Main.AdviceFragment.AdviceDetail;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shandian.lu.BaseController;
import com.shandian.lu.NetWork.AdviceNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.AdviceDetailBean;

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
        tvMainAdviceDetailContentNews.setText(adviceDetailBean.getContent().getList().getContent());
        tvMainAdviceDetailContentNext.setText(adviceDetailBean.getContent().getNext_one().getTitle());
        tvMainAdviceDetailContentPrevious.setText(adviceDetailBean.getContent().getLast_one().getTitle());
        /*previousId = adviceDetailBean.getContent().getLast_one().getId();*/
       /* nextId = adviceDetailBean.getContent().getNext_one().getId();*/

    }
}
