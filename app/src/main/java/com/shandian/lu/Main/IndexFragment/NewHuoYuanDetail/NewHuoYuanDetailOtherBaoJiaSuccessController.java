package com.shandian.lu.Main.IndexFragment.NewHuoYuanDetail;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewHuoYuanDetailOtherBean;
import com.example.mynewslayoutlib.Bean.NewLaHuoBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.IndexFragment.BaiDuRoutePlan.NewBaiDuRoutePlanActivity;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.NewEditBaoJiaDialog;
import com.shandian.lu.Widget.Dialog.NewQueryDialog;
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.CallTelDialog;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ImageView.RoundImageView;

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

public class NewHuoYuanDetailOtherBaoJiaSuccessController extends BaseController {




    private String status;
    private  String loginId,baoJiaId;
 /*   @BindView(R.id.rly_new_other_hyxq_success_bottom_tel)
    RelativeLayout rlyNewOtherHYXQSuccessBottomTel;*/
    @BindView(R.id.rly_new_other_hyxq_success_bottom_tgbj_submit)
    RelativeLayout rlyNewOtherHYXQSuccessBottomTGBJSubmit;
    @OnClick(R.id.rly_new_other_hyxq_success_bottom_tgbj_submit)
    public void rlyNewOtherHYXQSuccessBottomTGBJSubmitOnclick(){
        clickOperation();
    }
    @BindView(R.id.tv_new_other_hyxq_success_bottom_tgbj_submit)
    TextView tvNewOtherHYXQSuccessBottomTGBJSubmit;
    @BindView(R.id.lly_new_other_hyxq_success_bottom_item)
    LinearLayout llyNewOtherHYXQSuccessBottomItem;
 /*   @BindView(R.id.rly_new_other_hyxq_success_bottom_message)
    RelativeLayout rlyNewOtherHYXQSuccessBottomMessage;*/

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

    NewEditBaoJiaDialog newEditBaoJiaDialog;
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
        if (newEditBaoJiaDialog != null && !newEditBaoJiaDialog.isShowing())
            newEditBaoJiaDialog.show();
    }

    public void dissmissDialog() {
        if (newEditBaoJiaDialog != null && newEditBaoJiaDialog.isShowing())
            newEditBaoJiaDialog.dismiss();
    }

    private  String tel;
    @BindView(R.id.rly_new_other_hyxq_success_bottom_message)
    RelativeLayout rlyNewOtherHYXQSuccessBottomMessage;
    @OnClick(R.id.rly_new_other_hyxq_success_bottom_message)
    public void rlyNewOtherHYXQSuccessBottomMessageOnclick(){
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

    @BindView(R.id.rly_new_other_hyxq_success_bottom_tel)
    RelativeLayout rlyNewOtherHYXQSuccessBottomTel;
    @OnClick(R.id.rly_new_other_hyxq_success_bottom_tel)
    public void rlyNewOtherHYXQSuccessBottomTelOnclick(){

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
        status = activity.getIntent().getStringExtra("status");
        initstatus(status);
    }

    private void initstatus(String status){
        if(status == null){
            status = "";
        }

        switch (status){
            case "-1":
                llyNewOtherHYXQSuccessBottomItem.setVisibility(View.VISIBLE);
                rlyNewOtherHYXQSuccessBottomTGBJSubmit.setBackgroundResource(R.color.gray);
                tvNewOtherHYXQSuccessBottomTGBJSubmit.setText("报价失败");
                break;
            case "0":
                llyNewOtherHYXQSuccessBottomItem.setVisibility(View.GONE);
                break;
            case "1":
                llyNewOtherHYXQSuccessBottomItem.setVisibility(View.VISIBLE);
                rlyNewOtherHYXQSuccessBottomTGBJSubmit.setBackgroundResource(R.color.gray);
                tvNewOtherHYXQSuccessBottomTGBJSubmit.setText("待货主支付定金");
                rlyNewOtherHYXQSuccessBottomTGBJSubmit.setClickable(false);
                break;
            case "2":
                llyNewOtherHYXQSuccessBottomItem.setVisibility(View.VISIBLE);
                rlyNewOtherHYXQSuccessBottomTGBJSubmit.setBackgroundResource(R.mipmap.weibaojia_submit_orange_bg);
                tvNewOtherHYXQSuccessBottomTGBJSubmit.setText("已装车");
                rlyNewOtherHYXQSuccessBottomTGBJSubmit.setClickable(true);
                break;
            case "3":
                llyNewOtherHYXQSuccessBottomItem.setVisibility(View.VISIBLE);
                rlyNewOtherHYXQSuccessBottomTGBJSubmit.setBackgroundResource(R.mipmap.weibaojia_submit_orange_bg);
                tvNewOtherHYXQSuccessBottomTGBJSubmit.setText("已送达");
                rlyNewOtherHYXQSuccessBottomTGBJSubmit.setClickable(true);
                break;
            case "4":
                llyNewOtherHYXQSuccessBottomItem.setVisibility(View.VISIBLE);
                rlyNewOtherHYXQSuccessBottomTGBJSubmit.setBackgroundResource(R.color.gray);
                tvNewOtherHYXQSuccessBottomTGBJSubmit.setText("待货主支付尾款");
                rlyNewOtherHYXQSuccessBottomTGBJSubmit.setClickable(false);
                break;
            case "5":
                llyNewOtherHYXQSuccessBottomItem.setVisibility(View.VISIBLE);
                rlyNewOtherHYXQSuccessBottomTGBJSubmit.setBackgroundResource(R.color.gray);
                tvNewOtherHYXQSuccessBottomTGBJSubmit.setText("交易成功");
                rlyNewOtherHYXQSuccessBottomTGBJSubmit.setClickable(false);
                break;

            default:
                llyNewOtherHYXQSuccessBottomItem.setVisibility(View.GONE);
                break;
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

        loginId = newHuoYuanDetailBean.getNr().getLogin_id();
        baoJiaId = newHuoYuanDetailBean.getNr().getBaojiaid();
        tel = newHuoYuanDetailBean.getNr().getIphone();
    }
    NewQueryDialog newQueryDialog;
    private void clickOperation(){
        switch (status){
            case "-1":

                break;
            case "0":

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
                    tvNewOtherHYXQSuccessBottomTGBJSubmit.setText("已送达");
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
                    rlyNewOtherHYXQSuccessBottomTGBJSubmit.setBackgroundResource(R.color.gray);
                    tvNewOtherHYXQSuccessBottomTGBJSubmit.setText("待货主支付尾款");
                    rlyNewOtherHYXQSuccessBottomTGBJSubmit.setClickable(false);
                }
            }
        });
    }


}
