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

/*import com.hyphenate.easeui.EaseConstant;*/
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.Main.IndexFragment.Common.MySelfLocActivity;
import com.zhyan.myhuanxin.EaseConstant;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.CallTelDialog;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MessageFragment.Chat.ChatActivity;
import com.shandian.lu.NetWork.AboutGoodsSourceNetWork;
import com.shandian.lu.R;
import com.shandian.lu.Bean.GoodsSourceDetailBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

import static com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils.options1;

/**
 * Created by az on 2017/5/18.
 */

public class GoodsSourceXRVDetailController extends BaseController {


    private CallTelDialog callTelDialog;
    @BindView(R.id.rly_main_index_goodssource_detail_calltel)
    RelativeLayout rlyMainIndexGoodsSourceDetailCallTel;
    @OnClick(R.id.rly_main_index_goodssource_detail_calltel)
    public void rlyMainIndexGoodsSourceDetailCallTelOnclick(){
        contactTel(tvMainIndexGoodsSourceDetailTel.getText().toString());
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
    @BindView(R.id.rly_main_index_goodssource_detail_topbar_back)
    RelativeLayout rlyMainIndexGoodsSourceDetailTopBarBack;

    @OnClick(R.id.rly_main_index_goodssource_detail_topbar_back)
    public void  rlyMainIndexGoodsSourceDetailTopBarBackOnclick(){
        activity.finish();
    }

    @BindView(R.id.iv_main_index_goodssource_detail_content)
    ImageView ivMainIndexGoodsSourceDetailContent;
    @BindView(R.id.tv_main_index_goodssource_detail_content_title)
    TextView tvMainIndexGoodsSourceDetailContentTitle;
    @BindView(R.id.tv_main_index_goodssource_detail_content_release_time)
    TextView tvMainIndexGoodsSourceDetailContentReleaseTime;
    @BindView(R.id.tv_main_index_goodssource_detail_have_read_times)
    TextView tvMainIndexGoodsSourceDetailHaveReadTimes;
    @BindView(R.id.tv_main_index_goodssource_detail_name)
    TextView tvMainIndexGoodsSourceDetailName;
    @BindView(R.id.tv_main_index_goodssource_detail_renzheng)
    TextView tvMainIndexGoodsSourceDetailRenZheng;
    @BindView(R.id.lly_main_index_goodssource_detail_renzheng)
    LinearLayout llyMainIndexGoodsSourceDetailRenZheng;
    @OnClick(R.id.lly_main_index_goodssource_detail_renzheng)
    public void llyMainIndexGoodsSourceDetailRenZhengOnclick(){

    }
    @BindView(R.id.tv_main_index_goodssource_detail_huowuzhongliang)
    TextView tvMainIndexGoodsSourceDetailHuoWuZhongLiang;

    @BindView(R.id.tv_main_index_goodssource_detail_leibie)
    TextView tvMainIndexGoodsSourceDetailLeiBie;
    @BindView(R.id.tv_main_index_goodssource_detail_tel)
    TextView tvMainIndexGoodsSourceDetailTel;
    @BindView(R.id.lly_main_index_goodssource_detail_tel)
    LinearLayout llyMainIndexGoodsSourceDetailTel;

    @OnClick(R.id.lly_main_index_goodssource_detail_tel)
    public void llyMainIndexGoodsSourceDetailTelOnclick(){

    }
    @BindView(R.id.tv_main_index_goodssource_detail_lxr)
    TextView tvMainIndexGoodsSourceDetailLXR;
    @BindView(R.id.tv_main_index_goodssource_detail_addr)
    TextView tvMainIndexGoodsSourceDetailAddr;
    @BindView(R.id.lly_main_index_goodssource_detail_addr)
    LinearLayout llyMainIndexGoodsSourceDetailAddr;
    @OnClick(R.id.lly_main_index_goodssource_detail_addr)
    public void llyMainIndexGoodsSourceDetailAddrOnclick(){
        Intent intent = new Intent(activity, MySelfLocActivity.class);
        intent.putExtra("lat",lat);
        intent.putExtra("lng",lng);
        intent.putExtra("addr",addr);
        activity.startActivity(intent);
    }
    @BindView(R.id.rly_main_index_goodssource_detail_speak)
    RelativeLayout rlyMainIndexGoodsSourceDetailSpeak;
    @OnClick(R.id.rly_main_index_goodssource_detail_speak)
    public void  rlyMainIndexGoodsSourceDetailSpeakOnclick(){
        activity.startActivity(new Intent(activity,ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, id));
    }
    @BindView(R.id.tv_main_index_goodssource_detail_pingjia)
    TextView tvMainIndexGoodsSourceDetailPingJia;
    @BindView(R.id.rly_main_index_goodssource_detail_pingjia)
    RelativeLayout rlyMainIndexGoodsSourceDetailPingJia;
    @OnClick(R.id.rly_main_index_goodssource_detail_pingjia)
    public void rlyMainIndexGoodsSourceDetailPingJiaOnclick(){

    }
    @BindView(R.id.tv_main_index_goodssource_detail_remark)
    TextView tvMainIndexGoodsSourceDetailRemark;
    String id = "0";
    private String lat="",lng="",addr="";
    public GoodsSourceXRVDetailController(Activity activity1){
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

    }



    private void initDetailFromNet(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if(login_id == null){
            Toast.makeText(activity,"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        AboutGoodsSourceNetWork aboutGoodsSourceNetWork = new AboutGoodsSourceNetWork();
        aboutGoodsSourceNetWork.getGoodsSourceDetailFromNet(id, login_id, new Observer<GoodsSourceDetailBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(GoodsSourceDetailBean goodsDetailBean) {
                if(goodsDetailBean.getStatus() == 0){
                    if((goodsDetailBean.getContent().get_$0() == null)){
                        return;
                    }
                    if(goodsDetailBean.getContent().get_$0().getAuth() == 0){
                        tvMainIndexGoodsSourceDetailRenZheng.setText("(未认证)");
                    }else{
                        tvMainIndexGoodsSourceDetailRenZheng.setText("(已认证)");
                    }
                    tvMainIndexGoodsSourceDetailContentTitle.setText(goodsDetailBean.getContent().get_$0().getGood_name().toString());
                    tvMainIndexGoodsSourceDetailContentReleaseTime.setText(goodsDetailBean.getContent().get_$0().getCreate_time());
                    tvMainIndexGoodsSourceDetailHaveReadTimes.setText("已有"+goodsDetailBean.getContent().get_$0().getNum()+"人浏览");
                    tvMainIndexGoodsSourceDetailName.setText(goodsDetailBean.getContent().get_$0().getName());
                    tvMainIndexGoodsSourceDetailHuoWuZhongLiang.setText(goodsDetailBean.getContent().get_$0().getWeight().toString());
                    tvMainIndexGoodsSourceDetailLeiBie.setText(goodsDetailBean.getContent().get_$0().getType_name().toString());
                    tvMainIndexGoodsSourceDetailTel.setText(goodsDetailBean.getContent().get_$0().getIphone().toString());
                    tvMainIndexGoodsSourceDetailAddr.setText(goodsDetailBean.getContent().get_$0().getAddress().toString());
                    tvMainIndexGoodsSourceDetailLXR.setText(goodsDetailBean.getContent().get_$0().getPeople().toString());
                    tvMainIndexGoodsSourceDetailPingJia.setText("评价("+goodsDetailBean.getContent().get_$0().getCount()+")");
                    tvMainIndexGoodsSourceDetailRemark.setText(goodsDetailBean.getContent().get_$0().getContext().toString());
                   /* if((goodsDetailBean.getContent().getImg() == null)||(goodsDetailBean.getContent().getImg().size() == 0)){
                        return;
                    }*/
                    ImageLoader.getInstance().displayImage(goodsDetailBean.getContent().getImg().get(0).getImg2(),ivMainIndexGoodsSourceDetailContent,options1);
                    lat = goodsDetailBean.getContent().get_$0().getLat();
                    lng = goodsDetailBean.getContent().get_$0().getLng();
                    addr = goodsDetailBean.getContent().get_$0().getAddress();

                }
            }
        });
    }
}
