package com.shandian.lu.Main.IndexFragment.NewHuoYuanDetail;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.NewHuoYuanDetailOtherBean;
import com.example.mynewslayoutlib.Bean.NewHuoYuanDetailOtherV2Bean;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.IndexFragment.BaiDuRoutePlan.NewBaiDuRoutePlanActivity;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.NewEditBaoJiaDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/9.
 */

public class NewHuoYuanDetailOtherBaoJiaWaitController extends BaseController {


    @BindView(R.id.rly_new_other_hyxq_wait_back)
    RelativeLayout rlyNewOtherHYXQWaitBack;
    @OnClick(R.id.rly_new_other_hyxq_wait_back)
    public void rlyNewOtherHYXQWaitBackOnclick(){
        activity.finish();
    }


    @BindView(R.id.tv_new_other_hyxq_wait_prov_city_area_b)
    TextView tvNewOtherHYXQWaitProvCityAreaB;
    @BindView(R.id.tv_new_other_hyxq_wait_addr_b)
    TextView tvNewOtherHYXQWaitAddrB;

    @BindView(R.id.tv_new_other_hyxq_wait_prov_city_area_e)
    TextView tvNewOtherHYXQWaitProvCityAreaE;
    @BindView(R.id.tv_new_other_hyxq_wait_addr_e)
    TextView tvNewOtherHYXQWaitAddrE;

    @BindView(R.id.tv_new_other_hyxq_wait_goodscontent)
    TextView tvNewOtherHYXQWaitGoodsContent;
    @BindView(R.id.tv_new_other_hyxq_wait_zxs)
    TextView tvNewOtherHYXQWaitZXS;
    @BindView(R.id.tv_new_other_hyxq_wait_remark)
    TextView tvNewOtherHYXQWaitRemark;
    @BindView(R.id.rv_new_other_hyxq_wait_img)
    RecyclerView rvNewOtherHYXQWaitImg;
    @BindView(R.id.tv_new_other_hyxq_wait_updatetime)
    TextView tvNewOtherHYXQWaitUpdateTime;

    NewEditBaoJiaDialog neweditBaoJiaDialog;
    private String hyId;
    @BindView(R.id.rly_new_other_hyxq_wait_bottom_submit)
    RelativeLayout rlyNewOtherHYXQWaitBottomSubmit;
    @OnClick(R.id.rly_new_other_hyxq_wait_bottom_submit)
    public void rlyNewOtherHYXQWaitBottomSubmitOnclick(){
        neweditBaoJiaDialog = new NewEditBaoJiaDialog(activity,hyId).Build.build(activity);

        showDialog();
    }

    @BindView(R.id.tv_new_other_hyxq_wait_money)
    TextView tvNewOtherHYXQWaitMoney;
    private String bLat,bLon,eLat,eLon;
    @BindView(R.id.rly_new_other_hyxq_wait_mapline)
    RelativeLayout rlyNewOtherHYXQWaitMapLine;
    @OnClick(R.id.rly_new_other_hyxq_wait_mapline)
    public void rlyNewOtherHYXQWaitMapLineOnclick(){
        Intent intent = new Intent(activity, NewBaiDuRoutePlanActivity.class);
        if(bLat == null){
            bLat = "";
        }
        if(bLon == null){
            bLon = "";
        }
        if(eLat == null){
            eLat = "";
        }
        if(eLon == null){
            eLon = "";
        }
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
        if (neweditBaoJiaDialog != null && !neweditBaoJiaDialog.isShowing())
            neweditBaoJiaDialog.show();
    }

    public void dissmissDialog() {
        if (neweditBaoJiaDialog != null && neweditBaoJiaDialog.isShowing())
            neweditBaoJiaDialog.dismiss();
    }



    private NewHuoYuanDetailImgRVAdapter adapter;
    private ArrayList<String> imgList;
    public NewHuoYuanDetailOtherBaoJiaWaitController(Activity activity1){
        activity = activity1;
        init();
    }


    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        getHyId();
        initRV();

    }
    private void getHyId(){
        hyId = activity.getIntent().getStringExtra("hyid");
        if(hyId == null){
            hyId = "";
        }
    }
    private void initRV(){
        imgList = new ArrayList<>();

        adapter = new NewHuoYuanDetailImgRVAdapter(activity,imgList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvNewOtherHYXQWaitImg.setAdapter(adapter);
        rvNewOtherHYXQWaitImg.setLayoutManager(linearLayoutManager);
    }


    public void initDetail(NewHuoYuanDetailOtherV2Bean newHuoYuanDetailBean){
        tvNewOtherHYXQWaitProvCityAreaB.setText(newHuoYuanDetailBean.getNr().getCfsheng()+"-"+newHuoYuanDetailBean.getNr().getCfshi()+"-"+newHuoYuanDetailBean.getNr().getCfqu());
        tvNewOtherHYXQWaitProvCityAreaE.setText(newHuoYuanDetailBean.getNr().getDasheng()+"-"+newHuoYuanDetailBean.getNr().getDashi()+"-"+newHuoYuanDetailBean.getNr().getDaqu());
        tvNewOtherHYXQWaitAddrB.setText(newHuoYuanDetailBean.getNr().getCfdizhi());
        tvNewOtherHYXQWaitAddrE.setText(newHuoYuanDetailBean.getNr().getDadizhi());
        tvNewOtherHYXQWaitGoodsContent.setText(newHuoYuanDetailBean.getNr().getGood_name());
        tvNewOtherHYXQWaitZXS.setText(newHuoYuanDetailBean.getNr().getNum()+"箱");
        tvNewOtherHYXQWaitRemark.setText(newHuoYuanDetailBean.getNr().getContext());
        tvNewOtherHYXQWaitUpdateTime.setText(newHuoYuanDetailBean.getNr().getTime());
        tvNewOtherHYXQWaitMoney.setText(newHuoYuanDetailBean.getNr().getBjnr());
        bLat = newHuoYuanDetailBean.getNr().getCflat();
        bLon = newHuoYuanDetailBean.getNr().getCflng();
        eLat = newHuoYuanDetailBean.getNr().getDalat();
        eLon = newHuoYuanDetailBean.getNr().getDalng();
        adapter.setAdapter(newHuoYuanDetailBean.getNr().getImgtu());

    }

}
