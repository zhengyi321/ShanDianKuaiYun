package com.shandian.lu.Main.IndexFragment.NewHuoYuanDetail;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewHuoYuanDetailBean;
import com.example.mynewslayoutlib.Bean.NewHuoYuanDetailOtherV2Bean;
import com.example.mynewslayoutlib.Bean.NewLaHuoBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.IndexFragment.BaiDuRoutePlan.NewBaiDuRoutePlanActivity;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.NewEditBaoJiaDialog;
import com.shandian.lu.Widget.Dialog.NewQueryDialog;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.CallTelDialog;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/6/9.
 */

public class NewHuoYuanDetailOtherBaoJiaV2Controller extends BaseController {

    private String hyId ,tel;
    @BindView(R.id.rly_new_other_hyxq_back)
    RelativeLayout rlyNewOtherHYXQBack;
    @OnClick(R.id.rly_new_other_hyxq_back)
    public void rlyNewOtherHYXQBackOnclick(){
        activity.finish();
    }


    @BindView(R.id.ib_new_other_hyxq_ads)
    ImageButton ibNewOtherHYXQAds;
    @OnClick(R.id.ib_new_other_hyxq_ads)
    public void ibNewOtherHYXQAdsOnclick(){

    }
    @BindView(R.id.tv_new_other_hyxq_prov_city_b)
    TextView tvNewOtherHYXQProvCityB;

    @BindView(R.id.tv_new_other_hyxq_prov_city_e)
    TextView tvNewOtherHYXQProvCityE;

    @BindView(R.id.tv_new_other_hyxq_fbtime)
    TextView tvNewOtherHYXQFBTime;

    @BindView(R.id.rly_new_other_hyxq_sjbj)
    RelativeLayout rlyNewOtherHYXQSJBJ;
    @BindView(R.id.lly_new_other_hyxq_sjbj)
    LinearLayout llyNewOtherHYXQSJBJ;
    @BindView(R.id.tv_new_other_hyxq_sjbj)
    TextView tvNewOtherHYXQSJBJ;
    @BindView(R.id.tv_new_other_hyxq_sjbj_status)
    TextView tvNewOtherHYXQSJBJStatus;
    @BindView(R.id.tv_new_other_hyxq_hzjg)
    TextView tvNewOtherHYXQHZJG;
    @BindView(R.id.tv_new_other_hyxq_hyckj)
    TextView tvNewOtherHYXQHYCKJ;


    @BindView(R.id.tv_new_other_hyxq_car_useing_time)
    TextView tvNewOtherHYXQCarUseingTime;
    @BindView(R.id.tv_new_other_hyxq_name)
    TextView tvNewOtherHYXQName;
    @BindView(R.id.tv_new_other_hyxq_tiji)
    TextView tvNewOtherHYXQTiJi;
    @BindView(R.id.tv_new_other_hyxq_goodsnums)
    TextView tvNewOtherHYXQGoodsNums;
    @BindView(R.id.tv_new_other_hyxq_weight)
    TextView tvNewOtherHYXQWeight;
    @BindView(R.id.tv_new_other_hyxq_mile)
    TextView tvNewOtherHYXQMile;
    /*
    @BindView(R.id.tv_new_other_hyxq_goodscontent)
    TextView tvNewOtherHYXQGoodsContent;
    @BindView(R.id.tv_new_other_hyxq_zxs)
    TextView tvNewOtherHYXQZXS;*/
    @BindView(R.id.tv_new_other_hyxq_remark)
    TextView tvNewOtherHYXQRemark;
    @BindView(R.id.rv_new_other_hyxq_img)
    RecyclerView rvNewOtherHYXQImg;
   /* @BindView(R.id.tv_new_other_hyxq_updatetime)
    TextView tvNewOtherHYXQUpdateTime;*/

    NewEditBaoJiaDialog neweditBaoJiaDialog;
    @BindView(R.id.tv_new_other_hyxq_bottom_tgbj_submit)
    TextView tvNewOtherHYXQBottomTGBJSubmit;
    @BindView(R.id.rly_new_other_hyxq_bottom_tgbj_submit)
    RelativeLayout rlyNewOtherHYXQBottomTGBJSubmit;
    @OnClick(R.id.rly_new_other_hyxq_bottom_tgbj_submit)
    public void rlyNewOtherHYXQBottomTGBJSubmitOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            return;
        }
        clickOperation();


    }



    private String bLat,bLon,eLat,eLon;
    @BindView(R.id.rly_new_other_hyxq_mapline)
    RelativeLayout rlyNewOtherHYXQMapLine;
    @OnClick(R.id.rly_new_other_hyxq_mapline)
    public void rlyNewOtherHYXQMapLineOnclick(){
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
    public void showDialog() {
        if (neweditBaoJiaDialog != null && !neweditBaoJiaDialog.isShowing())
            neweditBaoJiaDialog.show();
    }

    public void dissmissDialog() {
        if (neweditBaoJiaDialog != null && neweditBaoJiaDialog.isShowing())
            neweditBaoJiaDialog.dismiss();
    }







    @BindView(R.id.rly_new_other_hyxq_bottom_message)
    RelativeLayout rlyNewOtherHYXQBottomMessage;
    @OnClick(R.id.rly_new_other_hyxq_bottom_message)
    public void rlyNewOtherHYXQBottomMessageOnclick(){
        if((tel == null)||(tel.isEmpty())){
            return;
        }
        doSendSMSTo(tel,"" );
    }

    /**
     * 调起系统发短信功能
     * @param phoneNumber
     * @param message
     */
    public void doSendSMSTo(String phoneNumber,String message){
       /* if(PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)){*/
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneNumber));
        intent.putExtra("sms_body", message);
        activity.startActivity(intent);
      /*  }*/
    }
    private  String loginId,baoJiaId;
    private String status;
    CallTelDialog callTelDialog;
    @BindView(R.id.rly_new_other_hyxq_bottom_tel)
    RelativeLayout rlyNewOtherHYXQBottomTel;
    @OnClick(R.id.rly_new_other_hyxq_bottom_tel)
    public void rlyNewOtherHYXQBottomTelOnclick(){

        if((tel == null)||(tel.isEmpty())){
            return;
        }
        callTelDialog = new CallTelDialog(activity,tel).Build.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dimssTelDialog();
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dimssTelDialog();
            }
        }).setCallBackListener(new CallTelDialog.DialogCallBackListener() {
            @Override
            public void callBack(String tel) {
                startCallTel(tel);
            }
        }).build(activity);
        showTelDialog();
    }


      /*  Intent intent = new Intent(this, NewFaBuHuoYuanActivity.class);*/
       /* Intent intent = new Intent(this, TestActivity.class);
        startActivity(intent);*/




    public void showTelDialog() {
        if (callTelDialog != null && !callTelDialog.isShowing())
            callTelDialog.show();
    }

    public void dimssTelDialog() {
        if (callTelDialog != null && callTelDialog.isShowing())
            callTelDialog.dismiss();
    }
    private void startCallTel(String number) {
        /*PhoneFormatCheckUtils phoneFormatCheckUtils = new PhoneFormatCheckUtils();
        if((number != null)&&(phoneFormatCheckUtils.IsNumber(number))) {*/
        //用intent启动拨打电话
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));

        activity.startActivity(intent);
       /* }*/
    }

    private NewHuoYuanDetailImgRVAdapter adapter;
    private List<String> imgList;
    public NewHuoYuanDetailOtherBaoJiaV2Controller(Activity activity1){
        activity = activity1;
        init();
    }


    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        getHyId();
        initRV();
        getNewCheYuanDetailFromNet();



    }
    private void getHyId(){
        hyId = activity.getIntent().getStringExtra("hyid");
        if(hyId == null){
            hyId = "";
        }


    }
/*    private void getHyId(){
        hyId = activity.getIntent().getStringExtra("hyid");
        if(hyId == null){
            hyId = "";
        }
    }*/
    private void initRV(){
        imgList = new ArrayList<>();
        imgList.add("");
        imgList.add("");
        imgList.add("");
        adapter = new NewHuoYuanDetailImgRVAdapter(activity,imgList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvNewOtherHYXQImg.setAdapter(adapter);
        rvNewOtherHYXQImg.setLayoutManager(linearLayoutManager);
    }
    private void getNewCheYuanDetailFromNet(){

        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)){
            loginId="";
        }
      /*  Toast.makeText(activity,"hyid:"+hyId,Toast.LENGTH_LONG).show();*/
        NewCheHuoListNetWork cheHuoListNetWork = new NewCheHuoListNetWork();
        cheHuoListNetWork.getHuoYuanDetailOtherV2FromNet(hyId, loginId, new Observer<NewHuoYuanDetailOtherV2Bean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewHuoYuanDetailOtherV2Bean newHuoYuanDetailOtherV2Bean) {
                initDetail(newHuoYuanDetailOtherV2Bean);
            }
        });
    }




















    private void initstatus(){

        if(status == null){
            status = "";
        }

        switch (status){
            case "-1":
             /*   llyNewOtherHYXQSuccessBottomItem.setVisibility(View.VISIBLE);
                rlyNewOtherHYXQSuccessBottomTGBJSubmit.setBackgroundResource(R.color.gray);*/
                tvNewOtherHYXQBottomTGBJSubmit.setText("报价失败");
                tvNewOtherHYXQBottomTGBJSubmit.setClickable(false);
                break;
            case "0":
         /*       llyNewOtherHYXQSuccessBottomItem.setVisibility(View.GONE);*/

                break;
            case "1":
       /*         llyNewOtherHYXQSuccessBottomItem.setVisibility(View.VISIBLE);
                rlyNewOtherHYXQSuccessBottomTGBJSubmit.setBackgroundResource(R.color.gray);*/
                tvNewOtherHYXQBottomTGBJSubmit.setText("待货主支付定金");
                tvNewOtherHYXQBottomTGBJSubmit.setClickable(false);
                break;
            case "2":
/*                llyNewOtherHYXQSuccessBottomItem.setVisibility(View.VISIBLE);
                rlyNewOtherHYXQSuccessBottomTGBJSubmit.setBackgroundResource(R.mipmap.weibaojia_submit_orange_bg);*/
                tvNewOtherHYXQBottomTGBJSubmit.setText("已装车");
                tvNewOtherHYXQBottomTGBJSubmit.setClickable(true);
                break;
            case "3":
                /*llyNewOtherHYXQSuccessBottomItem.setVisibility(View.VISIBLE);
                rlyNewOtherHYXQSuccessBottomTGBJSubmit.setBackgroundResource(R.mipmap.weibaojia_submit_orange_bg);*/
                tvNewOtherHYXQBottomTGBJSubmit.setText("已送达");
                tvNewOtherHYXQBottomTGBJSubmit.setClickable(true);
                break;
            case "4":
              /*  llyNewOtherHYXQSuccessBottomItem.setVisibility(View.VISIBLE);
                rlyNewOtherHYXQSuccessBottomTGBJSubmit.setBackgroundResource(R.color.gray);*/
                tvNewOtherHYXQBottomTGBJSubmit.setText("待货主支付尾款");
                tvNewOtherHYXQBottomTGBJSubmit.setClickable(false);
                break;
            case "5":
             /*   llyNewOtherHYXQSuccessBottomItem.setVisibility(View.VISIBLE);
                rlyNewOtherHYXQSuccessBottomTGBJSubmit.setBackgroundResource(R.color.gray);*/
                tvNewOtherHYXQBottomTGBJSubmit.setText("交易成功");
                tvNewOtherHYXQBottomTGBJSubmit.setClickable(false);
                break;

            default:
             /*   llyNewOtherHYXQSuccessBottomItem.setVisibility(View.GONE);*/
                break;
        }
    }






    NewQueryDialog newQueryDialog;
    private void clickOperation(){
        switch (status){
            case "-1":

                break;
            case "0":
                neweditBaoJiaDialog = new NewEditBaoJiaDialog(activity,hyId).Build.build(activity);

                showDialog();
                break;
            case "1":

                break;
            case "2":
                newQueryDialog = new NewQueryDialog(activity,"已装车").Build.setCallBackListener(new NewQueryDialog.DialogCallBackListener() {
                    @Override
                    public void callBack(boolean isQuery) {
                        huoWuJieZouFinishToNet();
                        dissmissQueryDialog();
                    }
                }).build(activity);
                showQueryDialog();

                break;
            case "3":

                newQueryDialog = new NewQueryDialog(activity,"已送达").Build.setCallBackListener(new NewQueryDialog.DialogCallBackListener() {
                    @Override
                    public void callBack(boolean isQuery) {
                        huoWuSongDaToNet();
                        dissmissQueryDialog();
                    }
                }).build(activity);
                showQueryDialog();

                break;
            case "4":

                break;
            case "5":

                break;
        }
    }



    public void showQueryDialog() {
        if (newQueryDialog != null && !newQueryDialog.isShowing())
            newQueryDialog.show();
    }

    public void dissmissQueryDialog() {
        if (newQueryDialog != null && newQueryDialog.isShowing())
            newQueryDialog.dismiss();
    }

    private void huoWuJieZouFinishToNet(){
        Map<String,String> paramMap = new HashMap<>();


        paramMap.put("login_id",loginId);
        paramMap.put("hyid",hyId);
        paramMap.put("baojiaid",baoJiaId);
        NewCheHuoListNetWork newCheHuoListNetWork = new NewCheHuoListNetWork();
        newCheHuoListNetWork.huoWuJieZouFinishToNet(paramMap, new Observer<NewLaHuoBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewLaHuoBean newLaHuoBean) {
                Toast.makeText(activity,newLaHuoBean.getMsg(),Toast.LENGTH_LONG).show();
                if(newLaHuoBean.getStatus().equals("0")){
                    status="3";
                    tvNewOtherHYXQBottomTGBJSubmit.setText("已送达");
                }
            }
        });
    }
    private void huoWuSongDaToNet(){
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("login_id",loginId);
        paramMap.put("hyid",hyId);
        paramMap.put("baojiaid",baoJiaId);
        NewCheHuoListNetWork newCheHuoListNetWork = new NewCheHuoListNetWork();
        newCheHuoListNetWork.huoWuSongDaToNet(paramMap, new Observer<NewLaHuoBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewLaHuoBean newLaHuoBean) {
                Toast.makeText(activity,newLaHuoBean.getMsg(),Toast.LENGTH_LONG).show();
                if(newLaHuoBean.getStatus().equals("0")){
                    status="4";

                    tvNewOtherHYXQBottomTGBJSubmit.setText("待货主支付尾款");
                    tvNewOtherHYXQBottomTGBJSubmit.setClickable(false);
                }
            }
        });
    }

    private void initDetail(NewHuoYuanDetailOtherV2Bean newHuoYuanDetailBean){
        tvNewOtherHYXQProvCityB.setText(newHuoYuanDetailBean.getNr().getCfshi()+newHuoYuanDetailBean.getNr().getCfqu());
        tvNewOtherHYXQProvCityE.setText(newHuoYuanDetailBean.getNr().getDashi()+newHuoYuanDetailBean.getNr().getDaqu());
        tvNewOtherHYXQFBTime.setText(newHuoYuanDetailBean.getNr().getTime());
        String sfbj = newHuoYuanDetailBean.getNr().getSfbj();
        status = activity.getIntent().getStringExtra("status");
        if(sfbj.equals("0")){
            llyNewOtherHYXQSJBJ.setVisibility(View.GONE);
            tvNewOtherHYXQSJBJStatus.setVisibility(View.VISIBLE);
            String czbj = newHuoYuanDetailBean.getNr().getCzbj();

            tvNewOtherHYXQSJBJStatus.setText(czbj);
            tvNewOtherHYXQBottomTGBJSubmit.setText("提供报价");
            rlyNewOtherHYXQBottomTGBJSubmit.setClickable(true);
            status = "0";
        }else if(sfbj.equals("1")){
            llyNewOtherHYXQSJBJ.setVisibility(View.VISIBLE);
            tvNewOtherHYXQSJBJStatus.setVisibility(View.GONE);
            String czbj = newHuoYuanDetailBean.getNr().getCzbj();
            if(czbj.isEmpty()){
                czbj = "0元";
            }
            tvNewOtherHYXQSJBJ.setText(czbj);
            tvNewOtherHYXQBottomTGBJSubmit.setText("修改报价");
            tvNewOtherHYXQBottomTGBJSubmit.setClickable(true);
            status = "0";
        }else if(sfbj.equals("2")){
            llyNewOtherHYXQSJBJ.setVisibility(View.VISIBLE);
            tvNewOtherHYXQSJBJStatus.setVisibility(View.GONE);
            String czbj = newHuoYuanDetailBean.getNr().getCzbj();
            if(czbj.isEmpty()){
                czbj = "0元";
            }
            tvNewOtherHYXQSJBJ.setText(czbj);
            tvNewOtherHYXQBottomTGBJSubmit.setText("报价成功");
            tvNewOtherHYXQBottomTGBJSubmit.setClickable(false);
        }else if(sfbj.equals("3")){
            llyNewOtherHYXQSJBJ.setVisibility(View.VISIBLE);
            tvNewOtherHYXQSJBJStatus.setVisibility(View.GONE);
            String czbj = newHuoYuanDetailBean.getNr().getCzbj();
            if(czbj.isEmpty()){
                czbj = "0元";
            }
            tvNewOtherHYXQSJBJ.setText(czbj);
            tvNewOtherHYXQBottomTGBJSubmit.setText("报价失败");
            tvNewOtherHYXQBottomTGBJSubmit.setClickable(false);
        }
        String hyjg = newHuoYuanDetailBean.getNr().getHyjiage();
        if(hyjg.isEmpty()){
            hyjg = "0元";
        }
        tvNewOtherHYXQHZJG.setText(hyjg);
        String tjjg = newHuoYuanDetailBean.getNr().getTjjiage();
        if(tjjg.isEmpty()){
            tjjg = "0元";
        }

        ImageLoader.getInstance().displayImage(newHuoYuanDetailBean.getNr().getGg().getImg(),ibNewOtherHYXQAds, ImageLoaderUtils.options1);
        tvNewOtherHYXQHYCKJ.setText(tjjg);
        tvNewOtherHYXQCarUseingTime.setText(newHuoYuanDetailBean.getNr().getYcsj());
        String hwlx = newHuoYuanDetailBean.getNr().getHuowulx();
        tvNewOtherHYXQName.setText(hwlx);
        String tiji = newHuoYuanDetailBean.getNr().getTiji();
       /* Toast.makeText(activity,"tiji:"+tiji,Toast.LENGTH_LONG).show();*/
        tvNewOtherHYXQTiJi.setText(tiji);
        tvNewOtherHYXQGoodsNums.setText(newHuoYuanDetailBean.getNr().getXiangshu());
        tvNewOtherHYXQWeight.setText(newHuoYuanDetailBean.getNr().getWeight());
        tvNewOtherHYXQMile.setText(newHuoYuanDetailBean.getNr().getJuli());

    /*    tvNewOtherHYXQGoodsContent.setText(newHuoYuanDetailBean.getNr().getGood_name());
        tvNewOtherHYXQZXS.setText(newHuoYuanDetailBean.getNr().getNum()+"箱");*/
        tvNewOtherHYXQRemark.setText(newHuoYuanDetailBean.getNr().getContext());
        /*tvNewOtherHYXQUpdateTime.setText(newHuoYuanDetailBean.getNr().getTime());*/
        bLat = newHuoYuanDetailBean.getNr().getCflat();
        bLon = newHuoYuanDetailBean.getNr().getCflng();
        eLat = newHuoYuanDetailBean.getNr().getDalat();
        eLon = newHuoYuanDetailBean.getNr().getDalng();
        adapter.setAdapter(newHuoYuanDetailBean.getNr().getImgtu());
        tel = newHuoYuanDetailBean.getNr().getIphone();
        loginId = newHuoYuanDetailBean.getNr().getLogin_id();
        baoJiaId = newHuoYuanDetailBean.getNr().getBaojiaid();
        initstatus();
    }

}
