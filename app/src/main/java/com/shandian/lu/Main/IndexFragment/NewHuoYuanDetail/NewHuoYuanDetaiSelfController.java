package com.shandian.lu.Main.IndexFragment.NewHuoYuanDetail;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.NewHuoYuanDetailBean;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.IndexFragment.BaiDuRoutePlan.NewBaiDuRoutePlanActivity;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.LookBaoJiaDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/6/9.
 */

public class NewHuoYuanDetaiSelfController extends BaseController {


    private String bLat,bLon,eLat,eLon;

    @BindView(R.id.rly_new_self_hyxq_back)
    RelativeLayout rlyNewSelfHYXQBack;
    @OnClick(R.id.rly_new_self_hyxq_back)
    public void rlyNewSelfHYXQBackOnclick(){
        activity.finish();
    }


    @BindView(R.id.tv_new_self_hyxq_prov_city_area_b)
    TextView tvNewSelfHYXQProvCityAreaB;
    @BindView(R.id.tv_new_self_hyxq_addr_b)
    TextView tvNewSelfHYXQAddrB;

    @BindView(R.id.tv_new_self_hyxq_prov_city_area_e)
    TextView tvNewSelfHYXQProvCityAreaE;
    @BindView(R.id.tv_new_self_hyxq_addr_e)
    TextView tvNewSelfHYXQAddrE;

    @BindView(R.id.tv_new_self_hyxq_goodscontent)
    TextView tvNewSelfHYXQGoodsContent;
    @BindView(R.id.tv_new_self_hyxq_zxs)
    TextView tvNewSelfHYXQZXS;
    @BindView(R.id.tv_new_self_hyxq_remark)
    TextView tvNewSelfHYXQRemark;
    @BindView(R.id.rv_new_self_hyxq_img)
    RecyclerView rvNewSelfHYXQImg;
    @BindView(R.id.tv_new_self_hyxq_updatetime)
    TextView tvNewSelfHYXQUpdateTime;
    LookBaoJiaDialog lookBaoJiaDialog;
    @BindView(R.id.ib_new_self_hyxq_ckbj)
    ImageButton ibNewSelfHYXQCKBJ;
    @OnClick(R.id.ib_new_self_hyxq_ckbj)
    public void ibNewSelfHYXQCKBJOnclick(){
        lookBaoJiaDialog = new LookBaoJiaDialog(activity).Build.build(activity);

        showDialog();
    }

    @BindView(R.id.rly_new_self_hyxq_mapline)
    RelativeLayout rlyNewSelfHYXQMapLine;
    @OnClick(R.id.rly_new_self_hyxq_mapline)
    public void rlyNewSelfHYXQMapLineOnclick(){
        Intent intent = new Intent(activity, NewBaiDuRoutePlanActivity.class);
        intent.putExtra("blat",bLat);
        intent.putExtra("blon",bLon);
        intent.putExtra("elat",eLat);
        intent.putExtra("elon",eLon);
        activity.startActivity(intent);

    }

      /*  Intent intent = new Intent(this, NewFaBuHuoYuanActivity.class);*/
       /* Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);*/

    public void showDialog() {
        if (lookBaoJiaDialog != null && !lookBaoJiaDialog.isShowing())
            lookBaoJiaDialog.show();
    }

    public void dissmissDialog() {
        if (lookBaoJiaDialog != null && lookBaoJiaDialog.isShowing())
            lookBaoJiaDialog.dismiss();
    }


    private String hyId ;
    private NewHuoYuanDetailImgRVAdapter adapter;
    private List<String> imgList;
    public NewHuoYuanDetaiSelfController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        getCyId();
        initRV();
        getNewCheYuanDetailFromNet();
    }

    private void getCyId(){
        hyId = activity.getIntent().getStringExtra("hyid");
        if(hyId == null){
            hyId = "";
        }
    }
    private void initRV(){
        imgList = new ArrayList<>();
        imgList.add("");
        imgList.add("");
        imgList.add("");
        adapter = new NewHuoYuanDetailImgRVAdapter(activity,imgList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvNewSelfHYXQImg.setAdapter(adapter);
        rvNewSelfHYXQImg.setLayoutManager(linearLayoutManager);
    }
    private void getNewCheYuanDetailFromNet(){
        NewCheHuoListNetWork cheHuoListNetWork = new NewCheHuoListNetWork();
        cheHuoListNetWork.getHuoYuanDetailFromNet(hyId, new Observer<NewHuoYuanDetailBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewHuoYuanDetailBean newHuoYuanDetailBean) {
                initDetail(newHuoYuanDetailBean);
            }
        });
    }


    private void initDetail(NewHuoYuanDetailBean newHuoYuanDetailBean){
        tvNewSelfHYXQProvCityAreaB.setText(newHuoYuanDetailBean.getNr().getCfsheng()+"-"+newHuoYuanDetailBean.getNr().getCfshi()+"-"+newHuoYuanDetailBean.getNr().getCfqu());
        tvNewSelfHYXQProvCityAreaE.setText(newHuoYuanDetailBean.getNr().getDasheng()+"-"+newHuoYuanDetailBean.getNr().getDashi()+"-"+newHuoYuanDetailBean.getNr().getDaqu());
        tvNewSelfHYXQAddrB.setText(newHuoYuanDetailBean.getNr().getCfdizhi());
        tvNewSelfHYXQAddrE.setText(newHuoYuanDetailBean.getNr().getDadizhi());
        tvNewSelfHYXQGoodsContent.setText(newHuoYuanDetailBean.getNr().getGood_name());
        tvNewSelfHYXQZXS.setText(newHuoYuanDetailBean.getNr().getNum()+"箱");
        tvNewSelfHYXQRemark.setText(newHuoYuanDetailBean.getNr().getContext());
        tvNewSelfHYXQUpdateTime.setText(newHuoYuanDetailBean.getNr().getTime());
        adapter.setAdapter(newHuoYuanDetailBean.getNr().getImgtu());
        bLat = newHuoYuanDetailBean.getNr().getCflat();
        bLon = newHuoYuanDetailBean.getNr().getCflng();
        eLat = newHuoYuanDetailBean.getNr().getDalat();
        eLon = newHuoYuanDetailBean.getNr().getDalng();
    }

}
