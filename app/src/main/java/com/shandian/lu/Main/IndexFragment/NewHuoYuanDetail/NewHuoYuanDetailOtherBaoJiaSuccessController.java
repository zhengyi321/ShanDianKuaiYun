package com.shandian.lu.Main.IndexFragment.NewHuoYuanDetail;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.NewHuoYuanDetailOtherBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.IndexFragment.BaiDuRoutePlan.NewBaiDuRoutePlanActivity;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.EditBaoJiaDialog;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ImageView.RoundImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/9.
 */

public class NewHuoYuanDetailOtherBaoJiaSuccessController extends BaseController {


    @BindView(R.id.rly_new_other_hyxq_success_back)
    RelativeLayout rlyNewOtherHYXQSuccessBack;
    @OnClick(R.id.rly_new_other_hyxq_success_back)
    public void rlyNewOtherHYXQSuccessBackOnclick(){
        activity.finish();
    }

    @BindView(R.id.tv_new_other_hyxq_success_name)
    TextView tvNewOtherHYXQSuccessName;
    @BindView(R.id.tv_new_other_hyxq_success_price)
    TextView tvNewOtherHYXQSuccessPrice;
    @BindView(R.id.riv_new_other_hyxq_success_touxiang)
    RoundImageView rivNewOtherHYXQSuccessTouXiang;

    @BindView(R.id.tv_new_other_hyxq_success_prov_city_area_b)
    TextView tvNewOtherHYXQSuccessProvCityAreaB;
    @BindView(R.id.tv_new_other_hyxq_success_addr_b)
    TextView tvNewOtherHYXQSuccessAddrB;

    @BindView(R.id.tv_new_other_hyxq_success_prov_city_area_e)
    TextView tvNewOtherHYXQSuccessProvCityAreaE;
    @BindView(R.id.tv_new_other_hyxq_success_addr_e)
    TextView tvNewOtherHYXQSuccessAddrE;

    @BindView(R.id.tv_new_other_hyxq_success_goodscontent)
    TextView tvNewOtherHYXQSuccessGoodsContent;
    @BindView(R.id.tv_new_other_hyxq_success_zxs)
    TextView tvNewOtherHYXQSuccessZXS;
    @BindView(R.id.tv_new_other_hyxq_success_remark)
    TextView tvNewOtherHYXQSuccessRemark;
    @BindView(R.id.rv_new_other_hyxq_success_img)
    RecyclerView rvNewOtherHYXQSuccessImg;
    @BindView(R.id.tv_new_other_hyxq_success_updatetime)
    TextView tvNewOtherHYXQSuccessUpdateTime;

    EditBaoJiaDialog editBaoJiaDialog;
    private String hyId;
/*    @BindView(R.id.rly_new_other_hyxq_success_bottom_submit)
    RelativeLayout rlyNewOtherHYXQSuccessBottomSubmit;
    @OnClick(R.id.rly_new_other_hyxq_success_bottom_submit)
    public void rlyNewOtherHYXQSuccessBottomSubmitOnclick(){
        editBaoJiaDialog = new EditBaoJiaDialog(activity,hyId).Build.build(activity);

        showDialog();
    }

    @BindView(R.id.tv_new_other_hyxq_success_money)
    TextView tvNewOtherHYXQSuccessMoney;*/
    private String bLat,bLon,eLat,eLon;
    @BindView(R.id.rly_new_other_hyxq_success_mapline)
    RelativeLayout rlyNewOtherHYXQSuccessMapLine;
    @OnClick(R.id.rly_new_other_hyxq_success_mapline)
    public void rlyNewOtherHYXQSuccessMapLineOnclick(){
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
        if (editBaoJiaDialog != null && !editBaoJiaDialog.isShowing())
            editBaoJiaDialog.show();
    }

    public void dissmissDialog() {
        if (editBaoJiaDialog != null && editBaoJiaDialog.isShowing())
            editBaoJiaDialog.dismiss();
    }



    private NewHuoYuanDetailImgRVAdapter adapter;
    private List<String> imgList;
    public NewHuoYuanDetailOtherBaoJiaSuccessController(Activity activity1){
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
        rvNewOtherHYXQSuccessImg.setAdapter(adapter);
        rvNewOtherHYXQSuccessImg.setLayoutManager(linearLayoutManager);
    }


    public void initDetail(NewHuoYuanDetailOtherBean newHuoYuanDetailBean){
        tvNewOtherHYXQSuccessProvCityAreaB.setText(newHuoYuanDetailBean.getNr().getCfsheng()+"-"+newHuoYuanDetailBean.getNr().getCfshi()+"-"+newHuoYuanDetailBean.getNr().getCfqu());
        tvNewOtherHYXQSuccessProvCityAreaE.setText(newHuoYuanDetailBean.getNr().getDasheng()+"-"+newHuoYuanDetailBean.getNr().getDashi()+"-"+newHuoYuanDetailBean.getNr().getDaqu());
        tvNewOtherHYXQSuccessAddrB.setText(newHuoYuanDetailBean.getNr().getCfdizhi());
        tvNewOtherHYXQSuccessAddrE.setText(newHuoYuanDetailBean.getNr().getDadizhi());
        tvNewOtherHYXQSuccessGoodsContent.setText(newHuoYuanDetailBean.getNr().getGood_name());
        tvNewOtherHYXQSuccessZXS.setText(newHuoYuanDetailBean.getNr().getNum()+"箱");
        tvNewOtherHYXQSuccessRemark.setText(newHuoYuanDetailBean.getNr().getContext());
        tvNewOtherHYXQSuccessUpdateTime.setText(newHuoYuanDetailBean.getNr().getTime());
        String name =(String )newHuoYuanDetailBean.getNr().getName();
        if(name == null){
            name = "";
        }
        tvNewOtherHYXQSuccessName.setText(name);
        tvNewOtherHYXQSuccessPrice.setText(newHuoYuanDetailBean.getNr().getBjnr()+"元");
        ImageLoader.getInstance().displayImage(newHuoYuanDetailBean.getNr().getTouxiang(),rivNewOtherHYXQSuccessTouXiang, ImageLoaderUtils.options1);
       /* tvNewOtherHYXQSuccessMoney.setText(newHuoYuanDetailBean.getNr().getBjnr());*/
        bLat = newHuoYuanDetailBean.getNr().getCflat();
        bLon = newHuoYuanDetailBean.getNr().getCflng();
        eLat = newHuoYuanDetailBean.getNr().getDalat();
        eLon = newHuoYuanDetailBean.getNr().getDalng();
        adapter.setAdapter(newHuoYuanDetailBean.getNr().getImgtu());

    }

}
