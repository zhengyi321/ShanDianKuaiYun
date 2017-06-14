package com.shandian.lu.Main.MineFragment.WoDeCheYuan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Matrix;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.example.mynewslayoutlib.Bean.NewYiBaoJiaBean;
import com.example.mynewslayoutlib.Bean.NewWoDeCheYuanBean;
import com.example.mynewslayoutlib.Utils.SystemUtils;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyunlib.Widget.RecyclerView.XRecycleView.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/6/12.
 */

public class NewWoDeCheYuanController extends BaseController {
    private int offset = 0;// 动画图片偏移量
    private int bmpW = 70;// 动画图片宽度
    private int currIndex = 0;// 当前页卡编号
    private int page = 1;

    double marginLeft = 0;
    double ivWidth = 0;
    private List<NewWoDeCheYuanBean.NrBean.ListBean> dataList;
    private List<NewYiBaoJiaBean.NrBean.ListBean> dataList1;
    private NewWoDeCheYuanCheYuanListXRVAdapter adapter;
    private NewWoDeCheYuanYiBaoJiaXRVAdapter adapter1;
    @BindView(R.id.rly_new_wodecheyuan_back)
    RelativeLayout rlyNewWoDeCheYuanBack;
    @OnClick(R.id.rly_new_wodecheyuan_back)
    public void rlyNewWoDeCheYuanBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.rb_new_wodecheyuan_finish)
    RadioButton rbNewWoDeCheYuanFinish;

    @OnClick(R.id.rb_new_wodecheyuan_finish)
    public void rbNewWoDeCheYuanFinishOnclick(){
        initTabBar(0);
        getCheYuanListDataFromNet();

    }

    @BindView(R.id.rb_new_wodecheyuan_releaseing)
    RadioButton rbNewWoDeCheYuanReleaseing;
    @OnClick(R.id.rb_new_wodecheyuan_releaseing)
    public void rbNewWoDeCheYuanReleaseingOnclick(){
        initTabBar(1);
        getYiBaoJiaDataFromNet();


    }

    @BindView(R.id.rb_new_wodecheyuan_all)
    RadioButton rbNewWoDeCheYuanAll;
    @OnClick(R.id.rb_new_wodecheyuan_all)
    public void rbNewWoDeCheYuanAllOnclick(){
        initTabBar(2);
        getCheYuanListDataFromNet();

    }
    @BindView(R.id.iv_new_wodecheyuan_tab_bottom)
    ImageView ivNewWoDeCheYuanTabBottom;

    @BindView(R.id.xrv_new_wodecheyuan_cheyuanlist)
    XRecyclerView xrvNewWoDeCheYuanCheYuanList;
    @BindView(R.id.xrv_new_wodecheyuan_yibaojia)
    XRecyclerView xrvNewWoDeCheYuanYiBaoJia;
    @BindView(R.id.pb_new_wodecheyuan)
    ProgressBar pbNewWoDeCheYuan;

    private String loginId;
    public NewWoDeCheYuanController(Activity activity1){
        activity = activity1;
        init();
    }


    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initLoginId();
        initImageView();
        initXRV();
    }
    private void initLoginId(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            return;
        }

    }
    /**
     * 初始化动画
     */
    private void initImageView() {

        SystemUtils systemUtils = new SystemUtils(activity);
        int width = systemUtils.getWindowWidth();
        int height = systemUtils.getWindowHeight();
       /*  marginLeft = (((width/6)/2.5));
         ivWidth = (width/4.5);*/
         marginLeft = (((width/8)));
         ivWidth = (width/4.5);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)ivWidth,activity.getResources().getDimensionPixelSize(R.dimen.dimen_30dp));
        params.setMargins((int)marginLeft,0,0,0);
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 2 - bmpW)/2 ;// 计算偏移量 满屏 screenW/有几个tab 就除以几 dialog 则为dialog的宽度除以tab数量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        ivNewWoDeCheYuanTabBottom.setImageMatrix(matrix);
        ivNewWoDeCheYuanTabBottom.setLayoutParams(params);
        initTabBar(0);
        getCheYuanListDataFromNet();

    }


    private void initXRV(){
        dataList = new ArrayList<>();
        dataList1 = new ArrayList<>();
        adapter = new NewWoDeCheYuanCheYuanListXRVAdapter(activity,dataList);
        adapter1 = new NewWoDeCheYuanYiBaoJiaXRVAdapter(activity,dataList1);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(activity);
        linearLayoutManager1.setOrientation(LinearLayoutManager.VERTICAL);
        xrvNewWoDeCheYuanCheYuanList.setLayoutManager(linearLayoutManager);
        xrvNewWoDeCheYuanYiBaoJia.setLayoutManager(linearLayoutManager1);
        xrvNewWoDeCheYuanCheYuanList.setAdapter(adapter);
        xrvNewWoDeCheYuanYiBaoJia.setAdapter(adapter1);
        xrvNewWoDeCheYuanCheYuanList.setVisibility(View.VISIBLE);
        xrvNewWoDeCheYuanYiBaoJia.setVisibility(View.GONE);
        adapter.deleteOnClickCallBack(new NewWoDeCheYuanCheYuanListXRVAdapter.DeleteListener() {
            @Override
            public void onDeleteOnclick(boolean isSuccess) {
                getCheYuanListDataFromNet();
            }
        });
        adapter1.deleteOnClickCallBack(new NewWoDeCheYuanYiBaoJiaXRVAdapter.DeleteListener() {
            @Override
            public void onDeleteOnclick(boolean isSuccess) {
                getYiBaoJiaDataFromNet();
            }
        });
    }


    public void initTabBar(int arg0){
      /*  int one = offset*3  + bmpW;// 页卡1 -> 页卡2 偏移量*/
       /* double one = marginLeft*1.7  + ivWidth;*/// 页卡1 -> 页卡2 偏移量
        double one = marginLeft*2.5  + ivWidth;// 页卡1 -> 页卡2 偏移量
     /*   double two = one * 2;// 页卡1 -> 页卡3 偏移量*/
       /* int three = one * 3;// 页卡1 -> 页卡3 偏移量*/
        Animation animation = null;
        switch (arg0) {
            case 0:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(0, 0, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation((int) one, 0, 0, 0);
                }/*else if (currIndex == 2) {
                    animation = new TranslateAnimation((int) two, 0, 0, 0);
                }*/
                currIndex = arg0;
                animation.setFillAfter(true);// True:图片停在动画结束位置
                animation.setDuration(200);
                ivNewWoDeCheYuanTabBottom.startAnimation(animation);
                /*initCheYuanText();*/
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(0, (int) one, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation((int) one,(int)  one, 0, 0);
                } /*else if (currIndex == 2) {
                    animation = new TranslateAnimation((int) two,(int)  one, 0, 0);
                }*/
                currIndex = arg0;
                animation.setFillAfter(true);// True:图片停在动画结束位置
                animation.setDuration(200);
                ivNewWoDeCheYuanTabBottom.startAnimation(animation);
               /* initHuoYuanText();*/
                break;
           /* case 2:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(0, (int) two, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation((int) one, (int) two, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation((int) two, (int) two, 0, 0);
                }
                currIndex = arg0;
                animation.setFillAfter(true);// True:图片停在动画结束位置
                animation.setDuration(200);
                ivNewWoDeCheYuanTabBottom.startAnimation(animation);
               *//* initHuoYuanText();*//*
                break;*/

        }
    }

    private void getCheYuanListDataFromNet(){
        pbNewWoDeCheYuan.setVisibility(View.VISIBLE);
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("login_id",loginId);
       /* paramMap.put("lx",lx);*/
        NewCheHuoListNetWork newCheHuoListNetWork = new NewCheHuoListNetWork();
        newCheHuoListNetWork.getWoDeCheYuanCheYuanLieBiaoFromNet(paramMap, new Observer<NewWoDeCheYuanBean>() {
            @Override
            public void onCompleted() {
                pbNewWoDeCheYuan.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                pbNewWoDeCheYuan.setVisibility(View.GONE);
            }

            @Override
            public void onNext(NewWoDeCheYuanBean newWoDeCheYuanBean) {
                if(newWoDeCheYuanBean.getStatus().equals("0")){
                    xrvNewWoDeCheYuanCheYuanList.setVisibility(View.VISIBLE);
                    xrvNewWoDeCheYuanYiBaoJia.setVisibility(View.GONE);
                    adapter.setAdapter(newWoDeCheYuanBean.getNr().getList());

                }
                pbNewWoDeCheYuan.setVisibility(View.GONE);
            }
        });
    }

    private void getYiBaoJiaDataFromNet(){
        pbNewWoDeCheYuan.setVisibility(View.VISIBLE);
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("login_id",loginId);
        paramMap.put("p",""+page);
        paramMap.put("zt","2");
       /* paramMap.put("lx",lx);*/
        NewCheHuoListNetWork newCheHuoListNetWork = new NewCheHuoListNetWork();
        newCheHuoListNetWork.getWoDeCheYuanYiBaoJiaFromNet(paramMap, new Observer<NewYiBaoJiaBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewYiBaoJiaBean newYiBaoJiaBean) {
                if(newYiBaoJiaBean.getStatus().equals("0")){
                    xrvNewWoDeCheYuanCheYuanList.setVisibility(View.GONE);
                    xrvNewWoDeCheYuanYiBaoJia.setVisibility(View.VISIBLE);
                    adapter1.setAdapter(newYiBaoJiaBean.getNr().getList());


                }
                pbNewWoDeCheYuan.setVisibility(View.GONE);
            }
        });
    }
}
