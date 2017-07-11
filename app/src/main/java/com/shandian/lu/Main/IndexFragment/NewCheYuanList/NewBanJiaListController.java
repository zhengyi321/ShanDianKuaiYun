package com.shandian.lu.Main.IndexFragment.NewCheYuanList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.NewAdsBean;
import com.example.mynewslayoutlib.Bean.NewBanJiaListBean;
import com.example.mynewslayoutlib.Bean.NewCheYuanListBean;
import com.google.gson.Gson;
import com.shandian.lu.BaseController;
import com.shandian.lu.NetWork.AdsNetWork;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.CarLengthDialog;
import com.shandian.lu.Widget.Dialog.CarTypeDialog;
import com.shandian.lu.Widget.Utils.GetJsonDataUtil;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyunlib.Widget.RecyclerView.XRecycleView.ProgressStyle;
import com.zhyan.shandiankuaiyunlib.Widget.RecyclerView.XRecycleView.XRecyclerView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;
import zhyan.likeiosselectpopuplib.Bean.JsonBean;
import zhyan.likeiosselectpopuplib.ProvCityAreaOptionsPickerView;

/**
 * Created by Administrator on 2017/7/11.
 */

public class NewBanJiaListController extends BaseController {
    private Thread thread;
    private  final int MSG_LOAD_DATA = 0x0001;
    private  final int MSG_LOAD_SUCCESS = 0x0002;
    private  final int MSG_LOAD_FAILED = 0x0003;
    private boolean isLoaded = false;
    private String bProv="",bCity="",bArea="",eProv="",eCity="",eArea="";
    int page = 1;
    private int refreshTime = 0;
    private int times = 0;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    @BindView(R.id.rly_new_banjialist_back)
    RelativeLayout rlyNewBanJiaListBack;

    @OnClick(R.id.rly_new_banjialist_back)
    public void rlyNewBanJiaListBackOnclick(){
        activity.finish();
    }

    @BindView(R.id.xrv_new_banjialist)
    XRecyclerView xrvNewBanJiaList;
    @BindView(R.id.rly_new_banjialist_baddr)
    RelativeLayout rlyNewBanJiaListBAddr;
    @OnClick(R.id.rly_new_banjialist_baddr)
    public void rlyNewBanJiaListBAddrOnclick(){
        ShowPickerView(true);
    }
    @BindView(R.id.tv_new_banjialist_baddr)
    TextView tvNewBanJiaListBAddr;
    @BindView(R.id.rly_new_banjialist_eaddr)
    RelativeLayout rlyNewBanJiaListEAddr;
    @OnClick(R.id.rly_new_banjialist_eaddr)
    public void rlyNewBanJiaListEAddrOnclick(){
        ShowPickerView(false);
    }
    @BindView(R.id.tv_new_banjialist_eaddr)
    TextView tvNewBanJiaListEAddr;
    CarTypeDialog carTypeDialog;
    @BindView(R.id.rly_new_banjialist_cartype)
    RelativeLayout rlyNewBanJiaListCarType;
    @OnClick(R.id.rly_new_banjialist_cartype)
    public void rlyNewBanJiaListCarTypeOnclick(){
        carTypeDialog = new CarTypeDialog(activity,tvNewBanJiaListCarType).Build.setCallBackListener(new CarTypeDialog.OnAdapterListener() {
            @Override
            public void isOnclick(boolean isOnclick) {
                if(isOnclick){
                    /*page = 1;
                    getData2FromNet();*/
                    xrvNewBanJiaList.refresh();
                }
            }
        }).build(activity);

        showCarTypeDialog();
    }
    @BindView(R.id.tv_new_banjialist_cartype)
    TextView tvNewBanJiaListCarType;



    public void showCarTypeDialog() {
        if (carTypeDialog != null && !carTypeDialog.isShowing())
            carTypeDialog.show();
    }

    public void dissmissCarTypeDialog() {
        if (carTypeDialog != null && carTypeDialog.isShowing())
            carTypeDialog.dismiss();
    }

    CarLengthDialog carLengthDialog;
    @BindView(R.id.rly_new_banjialist_carlangth)
    RelativeLayout rlyNewBanJiaListCarLangth;
    @OnClick(R.id.rly_new_banjialist_carlangth)
    public void rlyNewBanJiaListCarLangthOnclick(){
        carLengthDialog = new CarLengthDialog(activity,tvNewBanJiaListCarLangth).Build.setCallBackListener(new CarLengthDialog.OnAdapterClickListener() {
            @Override
            public void isClicked(boolean isClicked) {
                if(isClicked){
                    /*page = 1;
                    getData2FromNet();*/
                    xrvNewBanJiaList.refresh();
                }
            }
        }).build(activity);
        showCarLengthDialog();
    }
    @BindView(R.id.tv_new_banjialist_carlangth)
    TextView tvNewBanJiaListCarLangth;






    public void showCarLengthDialog() {
        if (carLengthDialog != null && !carLengthDialog.isShowing()) {
            carLengthDialog.show();
        }
    }

    public void dissmissCarLengthDialog() {
        if (carLengthDialog != null && carLengthDialog.isShowing()) {
            carLengthDialog.dismiss();
        }
    }


    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_LOAD_DATA:
                    if (thread==null){//如果已创建就不再重新创建子线程了

                       /* Toast.makeText(JsonDataActivity.this,"开始解析数据",Toast.LENGTH_SHORT).show();*/
                        thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                // 写子线程中的操作,解析省市区数据
                                initJsonData();
                            }
                        });
                        thread.start();
                    }
                    break;

                case MSG_LOAD_SUCCESS:
                   /* Toast.makeText(JsonDataActivity.this,"解析数据成功",Toast.LENGTH_SHORT).show();*/
                    isLoaded = true;
                    break;

                case MSG_LOAD_FAILED:
                 /*   Toast.makeText(JsonDataActivity.this,"解析数据失败",Toast.LENGTH_SHORT).show();*/
                    break;

            }
        }
    };
    NewBanJiaListXRVAdapter newBanJiaListXRVAdapter;
    private List<NewBanJiaListBean.NrBean.ListBean> dataList;
    private String typeName;
    public NewBanJiaListController(Activity activity1){
        activity = activity1;
        init();
    }


    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        getTypeName();
        initJsonData();
        initXRV();
        getAdsFromNet();

    }

    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(activity,"province.json");//获取assets目录下的json文件数据

        ArrayList<JsonBean> jsonBean = parseData(JsonData);//用Gson 转成实体

        /**
         * 添加省份数据
         *
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;

        for (int i=0;i<jsonBean.size();i++){//遍历省份
            ArrayList<String> CityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> Province_AreaList = new ArrayList<>();//该省的所有地区列表（第三极）

            for (int c=0; c<jsonBean.get(i).getCityList().size(); c++){//遍历该省份的所有城市
                String CityName = jsonBean.get(i).getCityList().get(c).getName();
                CityList.add(CityName);//添加城市

                ArrayList<String> City_AreaList = new ArrayList<>();//该城市的所有地区列表

                //如果无地区数据，建议添加空字符串，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCityList().get(c).getArea() == null
                        ||jsonBean.get(i).getCityList().get(c).getArea().size()==0) {
                    City_AreaList.add("");
                }else {

                    for (int d=0; d < jsonBean.get(i).getCityList().get(c).getArea().size(); d++) {//该城市对应地区所有数据
                        String AreaName = jsonBean.get(i).getCityList().get(c).getArea().get(d);

                        City_AreaList.add(AreaName);//添加该城市所有地区数据
                    }
                }
                Province_AreaList.add(City_AreaList);//添加该省所有地区数据
            }

            /**
             * 添加城市数据
             */
            options2Items.add(CityList);

            /**
             * 添加地区数据
             */
            options3Items.add(Province_AreaList);
        }

        mHandler.sendEmptyMessage(MSG_LOAD_SUCCESS);

    }

    private void ShowPickerView(final boolean isBeginShengShiXian) {// 弹出选择器

        ProvCityAreaOptionsPickerView pvOptions = new ProvCityAreaOptionsPickerView.Builder(activity, new ProvCityAreaOptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()+
                        options2Items.get(options1).get(options2)+
                        options3Items.get(options1).get(options2).get(options3);
                if(isBeginShengShiXian) {
                    bProv =  options1Items.get(options1).getPickerViewText();
                    bCity = options2Items.get(options1).get(options2);
                    bArea =  options3Items.get(options1).get(options2).get(options3);
                    int indexOfQuan = bArea.indexOf("全");
                    if(indexOfQuan >= 0){
                        bArea = "";
                        tvNewBanJiaListBAddr.setText(bCity);
                    }else {
                        tvNewBanJiaListBAddr.setText(bArea);
                    }
             /*       page = 1;
                    getData2FromNet();*/
                    xrvNewBanJiaList.refresh();
                }else{

                    eProv =  options1Items.get(options1).getPickerViewText();
                    eCity = options2Items.get(options1).get(options2);
                    eArea =  options3Items.get(options1).get(options2).get(options3);
                    int indexOfQuan = eArea.indexOf("全");
                    if(indexOfQuan >= 0){
                        eArea = "";
                        tvNewBanJiaListEAddr.setText(eCity);
                    }else {
                        tvNewBanJiaListEAddr.setText(eArea);
                    }
                    /*page = 1;
                    getData2FromNet();*/
                    xrvNewBanJiaList.refresh();
                }
             /*   Toast.makeText(activity,tx,Toast.LENGTH_SHORT).show();*/
            }
        })

                .setTitleText("城市选择")
                .setDividerColor(Color.BLACK)
                .setTextColorCenter(Color.BLACK) //设置选中项文字颜色
                .setContentTextSize(15)
                .setOutSideCancelable(false)// default is true
                .build();

        /*pvOptions.setPicker(options1Items);//一级选择器
        pvOptions.setPicker(options1Items, options2Items);//二级选择器*/
        pvOptions.setPicker(options1Items, options2Items,options3Items);//三级选择器
        pvOptions.show();
    }

    private void initXRV(){
        dataList = new ArrayList<>();
        newBanJiaListXRVAdapter = new NewBanJiaListXRVAdapter(activity,dataList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrvNewBanJiaList.setLayoutManager(linearLayoutManager);
        xrvNewBanJiaList.setAdapter(newBanJiaListXRVAdapter);

        xrvNewBanJiaList.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xrvNewBanJiaList.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xrvNewBanJiaList.setArrowImageView(R.drawable.iconfont_downgrey);
        xrvNewBanJiaList.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime ++;
                times = 0;
                new Handler().postDelayed(new Runnable(){
                    public void run() {

                        page=1;
                        getData2FromNet();


                    }

                }, 0);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                if(times < 2){
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            page++;
                            getData2FromNet();
                         /*   xrvNewCheYuanList.loadMoreComplete();*/

                        }
                    }, 0);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            page++;
                            getData2FromNet();
                           /* xrvNewCheYuanList.setNoMore(true);*/

                        }
                    }, 0);
                }
                times ++;
            }
        });
        xrvNewBanJiaList.refresh();
    }

    private void getTypeName() {
        typeName = activity.getIntent().getStringExtra("typeName");
        if (typeName == null) {
            return;
        }
    }
    public ArrayList<JsonBean> parseData(String result) {//Gson 解析
        ArrayList<JsonBean> detail = new ArrayList<>();
        try {
            JSONArray data = new JSONArray(result);
            Gson gson = new Gson();
            for (int i = 0; i < data.length(); i++) {
                JsonBean entity = gson.fromJson(data.optJSONObject(i).toString(), JsonBean.class);
                detail.add(entity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            mHandler.sendEmptyMessage(MSG_LOAD_FAILED);
        }
        return detail;
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
                newBanJiaListXRVAdapter.setImgAndUrl(newAdsBean.getNr().getImg(),newAdsBean.getNr().getUrl());
            }
        });
    }


    public void getData2FromNet(){

        String carType = tvNewBanJiaListCarType.getText().toString();
        String carLangth = tvNewBanJiaListCarLangth.getText().toString();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String currentLat = xcCacheManager.readCache(xcCacheSaveName.currentLat);
        if(currentLat == null){
            currentLat = "";
        }

        String currentLon = xcCacheManager.readCache(xcCacheSaveName.currentLon);
        if(currentLon == null){
            currentLon = "";
        }
      /*  Toast.makeText(activity,""+typeName,Toast.LENGTH_LONG).show();*/
        NewCheHuoListNetWork newCheHuoListNetWork = new NewCheHuoListNetWork();
        newCheHuoListNetWork.getBanJiaListFromNet(typeName, currentLat, currentLon, page + "", bProv, bCity, bArea, eProv, eCity, eArea, carType, carLangth, new Observer<NewBanJiaListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewBanJiaListBean newBanJiaListBean) {
                if(page == 1 ){
                    newBanJiaListXRVAdapter.dataList.clear();
                    xrvNewBanJiaList.refreshComplete();
                }else {
                    xrvNewBanJiaList.loadMoreComplete();
                }
                if(newBanJiaListBean.getNr().getList().size() == 0){
                    xrvNewBanJiaList.setNoMore(true);
                }
                newBanJiaListXRVAdapter.setAdapterDataList(newBanJiaListBean.getNr().getList());
            }
        });
    }









}
