package com.shandian.lu.Main.IndexFragment.NewCheYuanDetail;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
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
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.CallTelDialog;

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
    private String bLat,bLon,eLat,eLon,cheLat,cheLon,cheTouXiang;

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

    @BindView(R.id.rly_new_other_cyxq_mapline)
    RelativeLayout rlyNewOtherCYXQMapLine;
    @OnClick(R.id.rly_new_other_cyxq_mapline)
    public void rlyNewOtherCYXQMapLineOnclick(){
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
        intent.putExtra("czlat",cheLat);
        intent.putExtra("czlon",cheLon);
        intent.putExtra("czTouXiang",cheTouXiang);
        activity.startActivity(intent);
    }
    @BindView(R.id.rly_new_other_cyxq_bottom_message)
    RelativeLayout rlyNewOtherCYXQBottomMessage;
    @OnClick(R.id.rly_new_other_cyxq_bottom_message)
    public void rlyNewOtherCYXQBottomMessageOnclick(){
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

    CallTelDialog callTelDialog;
    private  String tel;
    @BindView(R.id.rly_new_other_cyxq_bottom_tel)
    RelativeLayout rlyNewOtherCYXQBottomTel;
    @OnClick(R.id.rly_new_other_cyxq_bottom_tel)
    public void rlyNewOtherCYXQBottomTelOnclick(){

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
        bLat = newCheYuanDetailBean.getNr().getCflat();
        bLon = newCheYuanDetailBean.getNr().getCflng();
        eLat = newCheYuanDetailBean.getNr().getDalat();
        eLon = newCheYuanDetailBean.getNr().getDalng();
        tel = newCheYuanDetailBean.getNr().getIphone();
        cheLat = newCheYuanDetailBean.getNr().getCzlat();
        cheLon = newCheYuanDetailBean.getNr().getCzlng();
        cheTouXiang = newCheYuanDetailBean.getNr().getCztouxiang();
    }


}
