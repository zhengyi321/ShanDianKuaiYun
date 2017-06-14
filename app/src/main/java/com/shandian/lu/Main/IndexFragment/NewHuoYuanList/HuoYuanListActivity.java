package com.shandian.lu.Main.IndexFragment.NewHuoYuanList;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.NewHuoYuanListBean;
import com.shandian.lu.BaseActivity;
import com.shandian.lu.Main.ReleaseFragment.SelectAddAddress.SelectAddAddressActivity;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.NewHuoYuanListTypeDialog;
import com.yanzhenjie.album.Album;
import com.zhyan.shandiankuaiyunlib.Widget.RecyclerView.XRecycleView.ProgressStyle;
import com.zhyan.shandiankuaiyunlib.Widget.RecyclerView.XRecycleView.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/10.
 */

public class HuoYuanListActivity extends BaseActivity {

    @BindView(R.id.xrv_new_huoyuanlist)
    XRecyclerView xrvNewHuoYuanList;
    private final int ACTIVITY_SELECT_ADDRESS_BEGIN = 105;
    private final int ACTIVITY_SELECT_ADDRESS_END = 106;
    private String bProvince,eProvince,bCity,eCity,bArea,eArea,beginAddr,endAddr;
    int page = 1;
    private int refreshTime = 0;
    private int times = 0;
    public List<NewHuoYuanListBean.NrBean.ListBean> huoYuanList,tempBeanList,adsBeanList,noAdsBeanList;
    HuoYuanListXRVAdapter huoYuanListXRVAdapter;
    private String blat,blon,elat,elon;
    @BindView(R.id.rly_new_huoyuanlist_baddr)
    RelativeLayout rlyNewHuoYuanListBAddr;
    @BindView(R.id.tv_new_huoyuanlist_baddr)
    TextView tvNewHuoYuanListBAddr;
    @OnClick(R.id.rly_new_huoyuanlist_baddr)
    public void rlyNewHuoYuanListBAddrOnclick(){
        Intent intent = new Intent(this, SelectAddAddressActivity.class);
        intent.putExtra("type","begin");
        startActivityForResult(intent,ACTIVITY_SELECT_ADDRESS_BEGIN);
    }
    @BindView(R.id.rly_new_huoyuanlist_eaddr)
    RelativeLayout rlyNewHuoYuanListEAddr;
    @BindView(R.id.tv_new_huoyuanlist_eaddr)
    TextView tvNewHuoYuanListEAddr;
    @OnClick(R.id.rly_new_huoyuanlist_eaddr)
    public void rlyNewHuoYuanListEAddrOnclick(){
        Intent intent = new Intent(this, SelectAddAddressActivity.class);
        intent.putExtra("type","end");
        startActivityForResult(intent,ACTIVITY_SELECT_ADDRESS_END);
    }
    NewHuoYuanListTypeDialog newHuoYuanListTypeDialog;
    private String type1 = "0";
    @BindView(R.id.tv_new_huoyuanlist_goodstype)
    TextView tvNewHuoYuanListGoodsType;
    @BindView(R.id.rly_new_huoyuanlist_goodstype)
    RelativeLayout rlyNewHuoYuanListGoodsType;
    @OnClick(R.id.rly_new_huoyuanlist_goodstype)
    public void rlyNewHuoYuanListGoodsTypeOnclick(){
        newHuoYuanListTypeDialog = new NewHuoYuanListTypeDialog(this).Build.setCallBackListener(new NewHuoYuanListTypeDialog.DialogCallBackListener() {
            @Override
            public void callBack(String type) {

                reFreshData("1",type);
                type1 = type;
                setType(type);
                dissmissDialog();
            }
        }).build(this);
        showDialog();
    }

    private void setType(String type){
        switch (type){
            case "1":
                tvNewHuoYuanListGoodsType.setText("同城货源");
                break;
            case "2":
                tvNewHuoYuanListGoodsType.setText("长途货源");
                break;
            case "3":
                tvNewHuoYuanListGoodsType.setText("特种货源");
                break;
            case "4":
                tvNewHuoYuanListGoodsType.setText("专线货源");
                break;
        }
    }
    public void showDialog() {
        if (newHuoYuanListTypeDialog != null && !newHuoYuanListTypeDialog.isShowing())
            newHuoYuanListTypeDialog.show();
    }

    public void dissmissDialog() {
        if (newHuoYuanListTypeDialog != null && newHuoYuanListTypeDialog.isShowing())
            newHuoYuanListTypeDialog.dismiss();
    }
    @BindView(R.id.rly_new_huoyuanlist_all)
    RelativeLayout rlyNewHuoYuanListAll;
    @OnClick(R.id.rly_new_huoyuanlist_all)
    public void rlyNewHuoYuanListAllOnclick(){
        reFreshData("1","0");
        tvNewHuoYuanListGoodsType.setText("全部");
    }


    private HuoYuanListController huoYuanListController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_huoyuanlist_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initXRV();
        initController();
        reFreshData("1","0");
    }


    private void initController(){
        huoYuanListController = new HuoYuanListController(this,huoYuanListXRVAdapter);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data == null){
            return;
        }
        switch (requestCode) {

            case ACTIVITY_SELECT_ADDRESS_BEGIN:{

                Bundle begin = data.getExtras();

                bProvince = begin.getString("province");
                bCity = begin.getString("city");
                bArea = begin.getString("area");
                blat = begin.getString("lat");
                blon = begin.getString("lon");
                beginAddr = begin.getString("addr");
                tvNewHuoYuanListBAddr.setText(bCity);

                reFreshData("1",type1);
                break;
            }
            case ACTIVITY_SELECT_ADDRESS_END:{

                Bundle begin = data.getExtras();

                eProvince = begin.getString("province");
                eCity = begin.getString("city");
                eArea = begin.getString("area");
                elat = begin.getString("lat");
                elon = begin.getString("lon");
                endAddr = begin.getString("addr");
                tvNewHuoYuanListEAddr.setText(eCity);

                reFreshData("1",type1);
                break;
            }

            default:

                break;
        }
    }

    private void reFreshData(String page,String type){

        if(bProvince == null){
            bProvince = "";
        }
        if(bCity == null){
            bCity = "";
        }
        if(bArea == null){
            bArea = "";
        }
        if(eProvince == null){
            eProvince = "";
        }
        if(eCity == null){
            eCity = "";
        }
        if(eArea == null){
            eArea = "";
        }
        huoYuanListController.getData2FromNet(page,type,bProvince,bCity,bArea,eProvince,eCity,eArea);
      /*  if((blat != null)&&(blon != null)&&(elat!=null)&&(elon != null)){

        }else{
            huoYuanListController.getDataFromNet(page,type);
        }*/
    }



    private void initXRV(){
        huoYuanList = new ArrayList<>();

        huoYuanListXRVAdapter = new HuoYuanListXRVAdapter(this,huoYuanList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrvNewHuoYuanList.setLayoutManager(linearLayoutManager);
        xrvNewHuoYuanList.setAdapter(huoYuanListXRVAdapter);
        xrvNewHuoYuanList.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xrvNewHuoYuanList.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xrvNewHuoYuanList.setArrowImageView(R.drawable.iconfont_downgrey);

        /*View header = LayoutInflater.from(activity).inflate(R.layout.recyclerview_header, (ViewGroup)activity.findViewById(android.R.id.content),false);
        xrvNewHuoYuanList.addHeaderView(header);*/

        xrvNewHuoYuanList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime ++;
                times = 0;
                new Handler().postDelayed(new Runnable(){
                    public void run() {

                        page=1;
                        reFreshData(page+"","0");

                        xrvNewHuoYuanList.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                if(times < 2){
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            page++;
                            reFreshData(page+"","0");
                            xrvNewHuoYuanList.loadMoreComplete();

                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            page++;
                            reFreshData(page+"","0");
                            xrvNewHuoYuanList.setNoMore(true);

                        }
                    }, 1000);
                }
                times ++;
            }
        });

    }
}
