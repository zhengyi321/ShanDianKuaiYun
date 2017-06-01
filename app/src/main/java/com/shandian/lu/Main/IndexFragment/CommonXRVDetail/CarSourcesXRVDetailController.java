package com.shandian.lu.Main.IndexFragment.CommonXRVDetail;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.Main.IndexFragment.Common.MySelfLocActivity;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.CallTelDialog;
import com.shandian.lu.BaseController;
import com.shandian.lu.NetWork.AboutCarSourceNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.CarSourceDetailBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

import static com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils.options1;

/**
 * Created by az on 2017/5/17.
 */

public class CarSourcesXRVDetailController extends BaseController {




    private CallTelDialog callTelDialog;
    @BindView(R.id.rly_main_index_carsource_detail_calltel)
    RelativeLayout rlyMainIndexCarSourceDetailCallTel;
    @OnClick(R.id.rly_main_index_carsource_detail_calltel)
    public void rlyMainIndexCarSourceDetailCallTelOnclick(){
        contactTel(tvMainIndexCarSourceDetailTel.getText().toString());
    }


    private void contactTel(String tel1) {
        if((tel1 == null)||(tel1.isEmpty())){
            return;
        }
        callTelDialog = new CallTelDialog(activity,tel1).Build.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dissmissDialog();
            }
        }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dissmissDialog();
            }
        }).setCallBackListener(new CallTelDialog.DialogCallBackListener() {
            @Override
            public void callBack(String tel) {
                startCallTel(tel);
            }
        }).build(activity);
        showDialog();
    }
    public void showDialog() {
        if (callTelDialog != null && !callTelDialog.isShowing())
            callTelDialog.show();
    }

    public void dissmissDialog() {
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
    @BindView(R.id.rly_main_index_carsource_detail_topbar_back)
    RelativeLayout rlyMainIndexCarSourceDetailTopBarBack;

    @OnClick(R.id.rly_main_index_carsource_detail_topbar_back)
    public void  rlyMainIndexCarSourceDetailTopBarBackOnclick(){
        activity.finish();
    }

    @BindView(R.id.iv_main_index_carsource_detail_content)
    ImageView ivMainIndexCarSourceDetailContent;
    @BindView(R.id.tv_main_index_carsource_detail_content_title)
    TextView tvMainIndexCarSourceDetailContentTitle;
    @BindView(R.id.tv_main_index_carsource_detail_content_release_time)
    TextView tvMainIndexCarSourceDetailContentReleaseTime;
    @BindView(R.id.tv_main_index_carsource_detail_have_read_times)
    TextView tvMainIndexCarSourceDetailHaveReadTimes;
    @BindView(R.id.tv_main_index_carsource_detail_name)
    TextView tvMainIndexCarSourceDetailName;
    @BindView(R.id.tv_main_index_carsource_detail_renzheng)
    TextView tvMainIndexCarSourceDetailRenZheng;
    @BindView(R.id.lly_main_index_carsource_detail_renzheng)
    LinearLayout llyMainIndexCarSourceDetailRenZheng;
    @OnClick(R.id.lly_main_index_carsource_detail_renzheng)
    public void llyMainIndexCarSourceDetailRenZhengOnclick(){

    }
    @BindView(R.id.tv_main_index_carsource_detail_cartype)
    TextView tvMainIndexCarSourceDetailCarType;

    @BindView(R.id.tv_main_index_carsource_detail_carlength)
    TextView tvMainIndexCarSourceDetailCarLength;
    @BindView(R.id.tv_main_index_carsource_detail_tel)
    TextView tvMainIndexCarSourceDetailTel;
    @BindView(R.id.lly_main_index_carsource_detail_tel)
    LinearLayout llyMainIndexCarSourceDetailTel;

    @OnClick(R.id.lly_main_index_carsource_detail_tel)
    public void llyMainIndexCarSourceDetailTelOnclick(){

    }
    @BindView(R.id.tv_main_index_carsource_detail_lxr)
    TextView tvMainIndexCarSourceDetailLXR;
    @BindView(R.id.tv_main_index_carsource_detail_addr)
    TextView tvMainIndexCarSourceDetailAddr;
    @BindView(R.id.lly_main_index_carsource_detail_addr)
    LinearLayout llyMainIndexCarSourceDetailAddr;
    @OnClick(R.id.lly_main_index_carsource_detail_addr)
    public void llyMainIndexCarSourceDetailAddrOnclick(){
        Intent intent = new Intent(activity, MySelfLocActivity.class);
        intent.putExtra("lat",lat);
        intent.putExtra("lng",lng);
        intent.putExtra("addr",addr);
        activity.startActivity(intent);
    }
    @BindView(R.id.rly_main_index_carsource_detail_speak)
    RelativeLayout rlyMainIndexCarSourceDetailSpeak;
    @OnClick(R.id.rly_main_index_carsource_detail_speak)
    public void  rlyMainIndexCarSourceDetailSpeakOnclick(){

    }
    @BindView(R.id.tv_main_index_carsource_detail_pingjia)
    TextView tvMainIndexCarSourceDetailPingJia;
    @BindView(R.id.rly_main_index_carsource_detail_pingjia)
    RelativeLayout rlyMainIndexCarSourceDetailPingJia;
    @OnClick(R.id.rly_main_index_carsource_detail_pingjia)
    public void rlyMainIndexCarSourceDetailPingJiaOnclick(){

    }
    @BindView(R.id.tv_main_index_carsource_detail_remark)
    TextView tvMainIndexCarSourceDetailRemark;
    String id = "0";
    private String lat="",lng="",addr="";
    public CarSourcesXRVDetailController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initGetId();
        initDetailFromNet();
    }

    private void initGetId(){
        id = activity.getIntent().getStringExtra("id");
        if(id == null){
            return;
        }

    }
    private void initDetailFromNet(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if(login_id == null){
            Toast.makeText(activity,"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        AboutCarSourceNetWork aboutCarSourceNetWork = new AboutCarSourceNetWork();
        aboutCarSourceNetWork.getCarSourceDetailFromNet(id, login_id, new Observer<CarSourceDetailBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(CarSourceDetailBean carSourceDetailBean) {

                if(carSourceDetailBean.getStatus() == 0){
                    if((carSourceDetailBean.getContent().getList() == null)||(carSourceDetailBean.getContent().getList().size() == 0)){
                        return;
                    }
                    if(carSourceDetailBean.getContent().getList().get(0).getAuth() == 0){
                        tvMainIndexCarSourceDetailRenZheng.setText("(未认证)");
                    }else{
                        tvMainIndexCarSourceDetailRenZheng.setText("(已认证)");
                    }
                    tvMainIndexCarSourceDetailContentTitle.setText(carSourceDetailBean.getContent().getList().get(0).getCar_title().toString());
                    tvMainIndexCarSourceDetailContentReleaseTime.setText(carSourceDetailBean.getContent().getList().get(0).getCreate_time());
                    tvMainIndexCarSourceDetailHaveReadTimes.setText("已有"+carSourceDetailBean.getContent().getList().get(0).getNum()+"人浏览");
                    tvMainIndexCarSourceDetailName.setText(carSourceDetailBean.getContent().getList().get(0).getName());
                    tvMainIndexCarSourceDetailCarType.setText(carSourceDetailBean.getContent().getList().get(0).getCar_type().toString());
                    tvMainIndexCarSourceDetailCarLength.setText(carSourceDetailBean.getContent().getList().get(0).getCar_lange().toString());
                    tvMainIndexCarSourceDetailTel.setText(carSourceDetailBean.getContent().getList().get(0).getIphone().toString());
                    tvMainIndexCarSourceDetailAddr.setText(carSourceDetailBean.getContent().getList().get(0).getAddress());
                    tvMainIndexCarSourceDetailLXR.setText(carSourceDetailBean.getContent().getList().get(0).getPeople());
                    tvMainIndexCarSourceDetailPingJia.setText("评价("+carSourceDetailBean.getContent().getList().get(0).getCount()+")");
                    tvMainIndexCarSourceDetailRemark.setText(carSourceDetailBean.getContent().getList().get(0).getContent());
                   /* if((carSourceDetailBean.getContent().getImg() == null)||(carSourceDetailBean.getContent().getImg().size() == 0)){
                        return;
                    }*/
                    ImageLoader.getInstance().displayImage(carSourceDetailBean.getContent().getImg().get(0).getImg2(),ivMainIndexCarSourceDetailContent,options1);
                    lat = carSourceDetailBean.getContent().getList().get(0).getLat();
                    lng = carSourceDetailBean.getContent().getList().get(0).getLng();
                    addr = carSourceDetailBean.getContent().getList().get(0).getAddress();
                }
            }
        });
    }


}
