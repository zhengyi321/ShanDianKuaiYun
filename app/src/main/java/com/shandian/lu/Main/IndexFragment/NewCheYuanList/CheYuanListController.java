package com.shandian.lu.Main.IndexFragment.NewCheYuanList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewAdsBean;
import com.example.mynewslayoutlib.Bean.NewCheYuanListBean;
import com.example.mynewslayoutlib.Bean.NewHuoYuanListBean;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.IndexFragment.NearByDriver.NearByDriverActivity;
import com.shandian.lu.Main.IndexFragment.PeiHuoZhongXin.PeiHuoZhongXinActivity;
import com.shandian.lu.Main.IndexFragment.TeZhongWuLiu.TeZhongWuLiuActivity;
import com.shandian.lu.Main.IndexFragment.ZhuanXianWuLiu.ZhuanXianWuLiuActivity;
import com.shandian.lu.NetWork.AdsNetWork;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.CarLengthDialog;
import com.shandian.lu.Widget.Dialog.CarTypeDialog;
import com.shandian.lu.Widget.Dialog.CarTypeDialogXRVAdapter;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyunlib.Widget.RecyclerView.XRecycleView.ProgressStyle;
import com.zhyan.shandiankuaiyunlib.Widget.RecyclerView.XRecycleView.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import zhyan.likeiosselectpopuplib.Bean.JsonBean;
import zhyan.likeiosselectpopuplib.OptionsPickerView;
import zhyan.likeiosselectpopuplib.ProvCityAreaOptionsPickerView;

/**
 * Created by Administrator on 2017/6/9.
 */

public class CheYuanListController extends BaseController {

    int page = 1;
    private int refreshTime = 0;
    private int times = 0;
    private String bProv="",bCity="",bArea="",eProv="",eCity="",eArea="";

    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    @BindView(R.id.xrv_new_cheyuanlist)
    XRecyclerView xrvNewCheYuanList;
    @BindView(R.id.tv_new_cheyuanlist_title)
    TextView tvNewCheYuanListTitle;
    @BindView(R.id.rly_new_cheyuanlist_back)
    RelativeLayout rlyNewCheYuanListBack;
    @OnClick(R.id.rly_new_cheyuanlist_back)
    public void rlyNewCheYuanListBackOnclick(){
        activity.finish();
    }

    @BindView(R.id.rly_new_cheyuanlist_change)
    RelativeLayout rlyNewCheYuanListChange;
    @OnClick(R.id.rly_new_cheyuanlist_change)
    public void rlyNewCheYuanListChangeOnclick(){
        changeListActivity();
    }

    @BindView(R.id.tv_new_cheyuanlist_baddr)
    TextView tvNewCheYuanListBAddr;
    @BindView(R.id.rly_new_cheyuanlist_baddr)
    RelativeLayout rlyNewCheYuanListBAddr;
    @OnClick(R.id.rly_new_cheyuanlist_baddr)
    public void rlyNewCheYuanListBAddrOnclick(){
        ShowPickerView();
    }
    @BindView(R.id.tv_new_cheyuanlist_eaddr)
    TextView tvNewCheYuanListEAddr;
    @BindView(R.id.rly_new_cheyuanlist_eaddr)
    RelativeLayout rlyNewCheYuanListEAddr;
    @OnClick(R.id.rly_new_cheyuanlist_eaddr)
    public void rlyNewCheYuanListEAddrOnclick(){
        ShowPickerView();
    }



    private void ShowPickerView() {// 弹出选择器

        ProvCityAreaOptionsPickerView pvOptions = new ProvCityAreaOptionsPickerView.Builder(activity, new ProvCityAreaOptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()+
                        options2Items.get(options1).get(options2)+
                        options3Items.get(options1).get(options2).get(options3);

                Toast.makeText(activity,tx,Toast.LENGTH_SHORT).show();
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(20)
                .setOutSideCancelable(false)// default is true
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器
        pvOptions.show();
    }
    CarTypeDialog carTypeDialog;
    @BindView(R.id.tv_new_cheyuanlist_cartype)
    TextView tvNewCheYuanListCarType;
    @BindView(R.id.rly_new_cheyuanlist_cartype)
    RelativeLayout rlyNewCheYuanListCarType;
    @OnClick(R.id.rly_new_cheyuanlist_cartype)
    public void rlyNewCheYuanListCarTypeOnClick(){
        carTypeDialog = new CarTypeDialog(activity,tvNewCheYuanListCarType).Build.setCallBackListener(new CarTypeDialog.OnAdapterListener() {
            @Override
            public void isOnclick(boolean isOnclick) {
                if(isOnclick){
                    getData2FromNet("1");
                }
            }
        }).build(activity);

        showCarTypeDialog();
    }
    public void showCarTypeDialog() {
        if (carTypeDialog != null && !carTypeDialog.isShowing())
            carTypeDialog.show();
    }

    public void dissmissCarTypeDialog() {
        if (carTypeDialog != null && carTypeDialog.isShowing())
            carTypeDialog.dismiss();
    }

    CarLengthDialog carLengthDialog;
    @BindView(R.id.tv_new_cheyuanlist_carlangth)
    TextView tvNewCheYuanListCarLangth;
    @BindView(R.id.rly_new_cheyuanlist_carlangth)
    RelativeLayout rlyNewCheYuanListCarLangth;
    @OnClick(R.id.rly_new_cheyuanlist_carlangth)
    public void rlyNewCheYuanListCarLangthOnClick(){
        carLengthDialog = new CarLengthDialog(activity,tvNewCheYuanListCarLangth).Build.setCallBackListener(new CarLengthDialog.OnAdapterClickListener() {
            @Override
            public void isClicked(boolean isClicked) {
                if(isClicked){
                    getData2FromNet("1");
                }
            }
        }).build(activity);
        showCarLengthDialog();
    }
    public void showCarLengthDialog() {
        if (carLengthDialog != null && !carLengthDialog.isShowing())
            carLengthDialog.show();
    }

    public void dissmissCarLengthDialog() {
        if (carLengthDialog != null && carLengthDialog.isShowing())
            carLengthDialog.dismiss();
    }




    CheYuanListV2XRVAdapter cheYuanListXRVAdapter;

    private List<NewCheYuanListBean.NrBean.ListBean> cheYuanList,tempBeanList,adsBeanList,noAdsBeanList;

    private String typeName;
    public CheYuanListController(Activity activity1){
        activity = activity1;
        init();
    }


    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initXRV();
        getTypeName();
        rlyNewCheYuanListChange.setVisibility(View.INVISIBLE);
        getDataFromNet(""+page);
        getAdsFromNet();
    }

    private void changeListActivity(){
        Intent intent;
        switch (typeName){
            case "1":
                tvNewCheYuanListTitle.setText("同城货运");
                 intent = new Intent(activity, PeiHuoZhongXinActivity.class);
                activity.startActivity(intent);
                activity.finish();
                break;
            case "2":
                tvNewCheYuanListTitle.setText("长途物流");
                intent = new Intent(activity, NearByDriverActivity.class);
                activity.startActivity(intent);
                activity.finish();
                break;
            case "3":
                tvNewCheYuanListTitle.setText("特种物流");
                 intent = new Intent(activity, TeZhongWuLiuActivity.class);
                activity.startActivity(intent);
                activity.finish();

                break;
            case "4":
                tvNewCheYuanListTitle.setText("专线物流");
                intent = new Intent(activity, ZhuanXianWuLiuActivity.class);
                activity.startActivity(intent);
                activity.finish();
                break;
        }


    }

    private void getTypeName(){
        typeName = activity.getIntent().getStringExtra("typeName");
        if(typeName == null){
            return;
        }
        switch (typeName){
            case "1":
                tvNewCheYuanListTitle.setText("同城货运");
                break;
            case "2":
                tvNewCheYuanListTitle.setText("长途物流");
                break;
            case "3":
                tvNewCheYuanListTitle.setText("特种物流");
                break;
            case "4":
                tvNewCheYuanListTitle.setText("专线物流");
                break;
        }
    }

    private void initXRV(){
        cheYuanList = new ArrayList<>();
        tempBeanList = new ArrayList<>();
        adsBeanList = new ArrayList<>();
        noAdsBeanList = new ArrayList<>();
        cheYuanListXRVAdapter = new CheYuanListV2XRVAdapter(activity,cheYuanList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrvNewCheYuanList.setLayoutManager(linearLayoutManager);
        xrvNewCheYuanList.setAdapter(cheYuanListXRVAdapter);

        xrvNewCheYuanList.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xrvNewCheYuanList.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xrvNewCheYuanList.setArrowImageView(R.drawable.iconfont_downgrey);

        xrvNewCheYuanList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime ++;
                times = 0;
                new Handler().postDelayed(new Runnable(){
                    public void run() {

                        page=1;
                        getData2FromNet(page+"");

                        xrvNewCheYuanList.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                if(times < 2){
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            page++;
                            getData2FromNet(page+"");
                            xrvNewCheYuanList.loadMoreComplete();

                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            page++;
                            getData2FromNet(page+"");
                            xrvNewCheYuanList.setNoMore(true);

                        }
                    }, 1000);
                }
                times ++;
            }
        });
        xrvNewCheYuanList.refresh();
    }

    private void getDataFromNet(final String pages){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String currentLat = xcCacheManager.readCache(xcCacheSaveName.currentLat);
        if(currentLat == null){
            currentLat = "";
        }

        String currentLon = xcCacheManager.readCache(xcCacheSaveName.currentlon);
        if(currentLon == null){
            currentLon = "";
        }
        NewCheHuoListNetWork newCheHuoListNetWork = new NewCheHuoListNetWork();
        newCheHuoListNetWork.getCheListFromNet(typeName, currentLat, currentLon, pages, new Observer<NewCheYuanListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewCheYuanListBean newCheYuanListBean) {
                if(newCheYuanListBean.getStatus().equals("0")){
                   /* int size = newCheYuanListBean.getNr().getList().size();
                    for(int i=0;i < size;i++){
                        if(newCheYuanListBean.getNr().getList().get(i).getGg().equals("0")){
                            noAdsBeanList.add(newCheYuanListBean.getNr().getList().get(i));
                        }
                        else if(newCheYuanListBean.getNr().getList().get(i).getGg().equals("1")){
                            adsBeanList.add(newCheYuanListBean.getNr().getList().get(i));
                        }


                        continue;
                    }
                    tempBeanList.clear();
                    tempBeanList.addAll(adsBeanList);
                    tempBeanList.addAll(noAdsBeanList);*/
                   if(pages.equals("1")){
                       cheYuanList.clear();
                   }
                    cheYuanListXRVAdapter.setAdapter(newCheYuanListBean.getNr().getList());
                }
            }
        });
    }


/*    public void getData2FromNet(final String page,  String bP, String bC, String bA, String eP, String eC, String eA,String carType,String carLangth){*/
    public void getData2FromNet(final String page  ){

        String carType = tvNewCheYuanListCarType.getText().toString();
        String carLangth = tvNewCheYuanListCarLangth.getText().toString();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String currentLat = xcCacheManager.readCache(xcCacheSaveName.currentLat);
        if(currentLat == null){
            currentLat = "";
        }

        String currentLon = xcCacheManager.readCache(xcCacheSaveName.currentlon);
        if(currentLon == null){
            currentLon = "";
        }
        NewCheHuoListNetWork newCheHuoListNetWork = new NewCheHuoListNetWork();
        newCheHuoListNetWork.getCheListV2FromNet(typeName, currentLat, currentLon, page, bProv, bCity, bArea, eProv, eCity, eArea, carType, carLangth, new Observer<NewCheYuanListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewCheYuanListBean newCheYuanListBean) {
                if(page.equals("1")){
                    cheYuanListXRVAdapter.cheYuanList.clear();
                }
                cheYuanListXRVAdapter.setAdapter(newCheYuanListBean.getNr().getList());
            }
        });



    }


    public void onResume(){
        getData2FromNet("1");
    }

    private void getAdsFromNet(){
        AdsNetWork adsNetWork = new AdsNetWork();
        adsNetWork.getAdsFromNet("1", new Observer<NewAdsBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewAdsBean newAdsBean) {
                cheYuanListXRVAdapter.setImgAndUrl(newAdsBean.getNr().getImg(),newAdsBean.getNr().getUrl());
            }
        });
    }
}
