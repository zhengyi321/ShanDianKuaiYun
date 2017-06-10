package com.shandian.lu.Main.IndexFragment.NewCheYuanDetail;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.NewCheYuanDetailBean;
import com.shandian.lu.BaseActivity;
import com.shandian.lu.BaseController;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/6/10.
 */

public class NewCheYuanDetailOtherController extends BaseController {

    @BindView(R.id.rly_new_other_cyxq_back)
    RelativeLayout rlyNewOtherCYXQBack;
    @OnClick(R.id.rly_new_other_cyxq_back)
    public void rlyNewOtherCYXQBackOnclick(){
        activity.finish();
    }


    @BindView(R.id.tv_new_other_cyxq_prov_city_area_b)
    TextView tvNewOtherCYXQProvCityAreaB;
    @BindView(R.id.tv_new_other_cyxq_addr_b)
    TextView tvNewOtherCYXQAddrB;

    @BindView(R.id.tv_new_other_cyxq_prov_city_area_e)
    TextView tvNewOtherCYXQProvCityAreaE;
    @BindView(R.id.tv_new_other_cyxq_addr_e)
    TextView tvNewOtherCYXQAddrE;

    @BindView(R.id.tv_new_other_cyxq_cartype)
    TextView tvNewOtherCYXQCarType;
    @BindView(R.id.tv_new_other_cyxq_carlength)
    TextView tvNewOtherCYXQCarLength;
    @BindView(R.id.tv_new_other_cyxq_remark)
    TextView tvNewOtherCYXQRemark;
    @BindView(R.id.rv_new_other_cyxq_img)
    RecyclerView rvNewOtherCYXQImg;
    @BindView(R.id.tv_new_other_cyxq_updatetime)
    TextView tvNewOtherCYXQUpdateTime;
    private String cyId ;
    private NewCheYuanDetailImgRVAdapter adapter;
    private List<String> imgList;


    public NewCheYuanDetailOtherController(Activity activity1){
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
        rvNewOtherCYXQImg.setAdapter(adapter);
        rvNewOtherCYXQImg.setLayoutManager(linearLayoutManager);
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
        tvNewOtherCYXQProvCityAreaB.setText(newCheYuanDetailBean.getNr().getCfsheng()+"-"+newCheYuanDetailBean.getNr().getCfshi()+"-"+newCheYuanDetailBean.getNr().getCfqu());
        tvNewOtherCYXQProvCityAreaE.setText(newCheYuanDetailBean.getNr().getDasheng()+"-"+newCheYuanDetailBean.getNr().getDashi()+"-"+newCheYuanDetailBean.getNr().getDaqu());
        tvNewOtherCYXQAddrB.setText(newCheYuanDetailBean.getNr().getCfdizhi());
        tvNewOtherCYXQAddrE.setText(newCheYuanDetailBean.getNr().getDadizhi());
        tvNewOtherCYXQCarType.setText(newCheYuanDetailBean.getNr().getCar_type());
        tvNewOtherCYXQCarLength.setText(newCheYuanDetailBean.getNr().getCar_lange()+"米");
        tvNewOtherCYXQRemark.setText(newCheYuanDetailBean.getNr().getContent());
        tvNewOtherCYXQUpdateTime.setText(newCheYuanDetailBean.getNr().getTime());
        adapter.setAdapter(newCheYuanDetailBean.getNr().getImgtu());

    }


}
