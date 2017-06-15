package com.shandian.lu.Main.IndexFragment.NewCheYuanDetail;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.NewCheYuanDetailBean;
import com.shandian.lu.BaseActivity;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.IndexFragment.BaiDuRoutePlan.NewBaiDuRoutePlanActivity;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/6/9.
 */

public class NewCheYuanDetailSelflController extends BaseController {

    private String bLat,bLon,eLat,eLon;
    @BindView(R.id.rly_new_self_cyxq_back)
    RelativeLayout rlyNewSelfCYXQBack;
    @OnClick(R.id.rly_new_self_cyxq_back)
    public void rlyNewSelfCYXQBackOnclick(){
        activity.finish();
    }


    @BindView(R.id.tv_new_self_cyxq_prov_city_area_b)
    TextView tvNewSelfCYXQProvCityAreaB;
    @BindView(R.id.tv_new_self_cyxq_addr_b)
    TextView tvNewSelfCYXQAddrB;

    @BindView(R.id.tv_new_self_cyxq_prov_city_area_e)
    TextView tvNewSelfCYXQProvCityAreaE;
    @BindView(R.id.tv_new_self_cyxq_addr_e)
    TextView tvNewSelfCYXQAddrE;

    @BindView(R.id.tv_new_self_cyxq_cartype)
    TextView tvNewSelfCYXQCarType;
    @BindView(R.id.tv_new_self_cyxq_carlength)
    TextView tvNewSelfCYXQCarLength;
    @BindView(R.id.tv_new_self_cyxq_remark)
    TextView tvNewSelfCYXQRemark;
    @BindView(R.id.rv_new_self_cyxq_img)
    RecyclerView rvNewSelfCYXQImg;
    @BindView(R.id.tv_new_self_cyxq_updatetime)
    TextView tvNewSelfCYXQUpdateTime;

    @BindView(R.id.rly_new_self_cyxq_mapline)
    RelativeLayout rlyNewOtherCYXQMapLine;
    @OnClick(R.id.rly_new_self_cyxq_mapline)
    public void rlyNewOtherCYXQMapLineOnclick(){
        Intent intent = new Intent(activity, NewBaiDuRoutePlanActivity.class);
        if(bLat == null){
            bLat = "0.0";
        }
        if(bLon == null){
            bLon = "0.0";
        }
        if(eLat == null){
            eLat = "0.0";
        }
        if(eLon == null){
            eLon = "0.0";
        }
        intent.putExtra("blat",bLat);
        intent.putExtra("blon",bLon);
        intent.putExtra("elat",eLat);
        intent.putExtra("elon",eLon);
        activity.startActivity(intent);
    }
    private String cyId ;
    private NewCheYuanDetailImgRVAdapter adapter;
    private List<String> imgList;
    public NewCheYuanDetailSelflController(Activity activity1){
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
        cyId = activity.getIntent().getStringExtra("cyid");
        if(cyId == null){
            cyId = "";
        }
    }
    private void initRV(){
        imgList = new ArrayList<>();
        imgList.add("");
        imgList.add("");
        imgList.add("");
        adapter = new NewCheYuanDetailImgRVAdapter(activity,imgList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvNewSelfCYXQImg.setAdapter(adapter);
        rvNewSelfCYXQImg.setLayoutManager(linearLayoutManager);
    }
    private void getNewCheYuanDetailFromNet(){
        NewCheHuoListNetWork cheHuoListNetWork = new NewCheHuoListNetWork();
        cheHuoListNetWork.getCheYuanDetailFromNet(cyId, new Observer<NewCheYuanDetailBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewCheYuanDetailBean newCheYuanDetailBean) {
                initDetail(newCheYuanDetailBean);
            }
        });
    }


    private void initDetail(NewCheYuanDetailBean newCheYuanDetailBean){
        tvNewSelfCYXQProvCityAreaB.setText(newCheYuanDetailBean.getNr().getCfsheng()+"-"+newCheYuanDetailBean.getNr().getCfshi()+"-"+newCheYuanDetailBean.getNr().getCfqu());
        tvNewSelfCYXQProvCityAreaE.setText(newCheYuanDetailBean.getNr().getDasheng()+"-"+newCheYuanDetailBean.getNr().getDashi()+"-"+newCheYuanDetailBean.getNr().getDaqu());
        tvNewSelfCYXQAddrB.setText(newCheYuanDetailBean.getNr().getCfdizhi());
        tvNewSelfCYXQAddrE.setText(newCheYuanDetailBean.getNr().getDadizhi());
        tvNewSelfCYXQCarType.setText(newCheYuanDetailBean.getNr().getCar_type());
        tvNewSelfCYXQCarLength.setText(newCheYuanDetailBean.getNr().getCar_lange()+"米");
        tvNewSelfCYXQRemark.setText(newCheYuanDetailBean.getNr().getContent());
        tvNewSelfCYXQUpdateTime.setText(newCheYuanDetailBean.getNr().getTime());
        adapter.setAdapter(newCheYuanDetailBean.getNr().getImgtu());
        bLat = newCheYuanDetailBean.getNr().getCflat();
        bLon = newCheYuanDetailBean.getNr().getCflng();
        eLat = newCheYuanDetailBean.getNr().getDalat();
        eLon = newCheYuanDetailBean.getNr().getDalng();
    }

}
