package com.shandian.lu.Main.IndexFragment.NewHuoYuanList;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.NewHuoYuanListBean;
import com.google.gson.Gson;
import com.shandian.lu.BaseActivity;
import com.shandian.lu.Main.ReleaseFragment.SelectAddAddress.SelectAddAddressActivity;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.NewHuoYuanListTypeDialog;
import com.shandian.lu.Widget.Utils.GetJsonDataUtil;
import com.yanzhenjie.album.Album;
import com.zhyan.shandiankuaiyunlib.Widget.RecyclerView.XRecycleView.ProgressStyle;
import com.zhyan.shandiankuaiyunlib.Widget.RecyclerView.XRecycleView.XRecyclerView;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zhyan.likeiosselectpopuplib.Bean.JsonBean;
import zhyan.likeiosselectpopuplib.ProvCityAreaOptionsPickerView;

/**
 * Created by Administrator on 2017/6/10.
 */

public class HuoYuanListActivity extends BaseActivity {
    private Thread thread;
    private  final int MSG_LOAD_DATA = 0x0001;
    private  final int MSG_LOAD_SUCCESS = 0x0002;
    private  final int MSG_LOAD_FAILED = 0x0003;
    private boolean isLoaded = false;
    @BindView(R.id.xrv_new_huoyuanlist)
    XRecyclerView xrvNewHuoYuanList;
    private final int ACTIVITY_SELECT_ADDRESS_BEGIN = 105;
    private final int ACTIVITY_SELECT_ADDRESS_END = 106;
    private String bProvince,eProvince,bCity,eCity,bArea,eArea,beginAddr,endAddr;
    private ArrayList<JsonBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
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
       /* Intent intent = new Intent(this, SelectAddAddressActivity.class);
        intent.putExtra("type","begin");
        startActivityForResult(intent,ACTIVITY_SELECT_ADDRESS_BEGIN);*/
        ShowPickerView(true);
    }
    @BindView(R.id.rly_new_huoyuanlist_eaddr)
    RelativeLayout rlyNewHuoYuanListEAddr;
    @BindView(R.id.tv_new_huoyuanlist_eaddr)
    TextView tvNewHuoYuanListEAddr;
    @OnClick(R.id.rly_new_huoyuanlist_eaddr)
    public void rlyNewHuoYuanListEAddrOnclick(){
       /* Intent intent = new Intent(this, SelectAddAddressActivity.class);
        intent.putExtra("type","end");
        startActivityForResult(intent,ACTIVITY_SELECT_ADDRESS_END);*/
        ShowPickerView(false);
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
       /* reFreshData("1","0");*/
       xrvNewHuoYuanList.refresh();
        tvNewHuoYuanListGoodsType.setText("全部");
    }


    private HuoYuanListController huoYuanListController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_huoyuanlist_lly);
    }



    private void ShowPickerView(final boolean isBeginShengShiXian) {// 弹出选择器

        ProvCityAreaOptionsPickerView pvOptions = new ProvCityAreaOptionsPickerView.Builder(this, new ProvCityAreaOptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int options2, int options3, View v) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()+
                        options2Items.get(options1).get(options2)+
                        options3Items.get(options1).get(options2).get(options3);
                if(isBeginShengShiXian) {
                    bProvince =  options1Items.get(options1).getPickerViewText();
                    bCity = options2Items.get(options1).get(options2);
                    bArea =  options3Items.get(options1).get(options2).get(options3);
                    int indexOfQuan = bArea.indexOf("全");
                    if(indexOfQuan >= 0){
                        bArea = "";
                        tvNewHuoYuanListBAddr.setText(bCity);
                    }else {
                        tvNewHuoYuanListBAddr.setText(bArea);
                    }
             /*       page = 1;
                    getData2FromNet();*/
                    xrvNewHuoYuanList.refresh();
                }else{

                    eProvince =  options1Items.get(options1).getPickerViewText();
                    eCity = options2Items.get(options1).get(options2);
                    eArea =  options3Items.get(options1).get(options2).get(options3);
                    int indexOfQuan = eArea.indexOf("全");
                    if(indexOfQuan >= 0){
                        eArea = "";
                        tvNewHuoYuanListEAddr.setText(eCity);
                    }else {
                        tvNewHuoYuanListEAddr.setText(eArea);
                    }
                    /*page = 1;
                    getData2FromNet();*/
                    xrvNewHuoYuanList.refresh();
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

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initXRV();
        initJsonData();
        initController();

       /* reFreshData("1","0");*/
    }


    private void initController(){
        huoYuanListController = new HuoYuanListController(this,huoYuanListXRVAdapter,xrvNewHuoYuanList);
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

    private void reFreshData(String page1,String type){

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
/*        System.out.print("\npage:"+page+"type:"+type+"bProvince:"+bProvince+"bCity:"+bCity+"bArea:"+bArea+"eProvince:"+eProvince+"eCity:"+eCity+"eArea:"+eArea);
        System.out.print("\npage:"+page+"type:"+type+"bProvince:"+bProvince+"bCity:"+bCity+"bArea:"+bArea+"eProvince:"+eProvince+"eCity:"+eCity+"eArea:"+eArea);
        System.out.print("\npage:"+page+"type:"+type+"bProvince:"+bProvince+"bCity:"+bCity+"bArea:"+bArea+"eProvince:"+eProvince+"eCity:"+eCity+"eArea:"+eArea);
        System.out.print("\npage:"+page+"type:"+type+"bProvince:"+bProvince+"bCity:"+bCity+"bArea:"+bArea+"eProvince:"+eProvince+"eCity:"+eCity+"eArea:"+eArea);
        System.out.print("\npage:"+page+"type:"+type+"bProvince:"+bProvince+"bCity:"+bCity+"bArea:"+bArea+"eProvince:"+eProvince+"eCity:"+eCity+"eArea:"+eArea);
        System.out.print("\npage:"+page+"type:"+type+"bProvince:"+bProvince+"bCity:"+bCity+"bArea:"+bArea+"eProvince:"+eProvince+"eCity:"+eCity+"eArea:"+eArea);
 */       huoYuanListController.getData2FromNet(page1,type,bProvince,bCity,bArea,eProvince,eCity,eArea);
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
                        reFreshData(page+"",type1);
                        /*reFreshData(page+"","0");*/


                    }

                }, 0);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                if(times < 2){
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            page++;
                            reFreshData(page+"",type1);
                        /*    xrvNewHuoYuanList.loadMoreComplete();*/

                        }
                    }, 0);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            page++;
                            reFreshData(page+"",type1);
                     /*       xrvNewHuoYuanList.setNoMore(true);*/

                        }
                    }, 0);
                }
                times ++;
            }
        });
       /* reFreshData(page+"",type1);*/
        xrvNewHuoYuanList.refresh();

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
    private void initJsonData() {//解析数据

        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         *
         * */
        String JsonData = new GetJsonDataUtil().getJson(this,"province.json");//获取assets目录下的json文件数据

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
}
