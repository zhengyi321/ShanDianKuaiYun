package com.shandian.lu.Main.MineFragment.WoDeHuoYuan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Matrix;
import android.os.Handler;
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

import com.example.mynewslayoutlib.Bean.NewWoDeHuoYuanBean;
import com.example.mynewslayoutlib.Utils.SystemUtils;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyunlib.Widget.RecyclerView.XRecycleView.ProgressStyle;
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

public class NewWoDeHuoYuanController extends BaseController {
    private int offset = 0;// 动画图片偏移量
    private int bmpW = 70;// 动画图片宽度
    private int currIndex = 0;// 当前页卡编号
    int type = 0;
    double marginLeft = 0;
    double ivWidth = 0;
    private int refreshTime = 0;
    private int times = 0;
    private int page = 1;
    private List<NewWoDeHuoYuanBean.NrBean.ListBean> dataList;
    private NewWoDeHuoYuanXRVAdapter adapter;
    @BindView(R.id.rly_new_wodehuoyuan_back)
    RelativeLayout rlyNewWoDeHuoYuanBack;
    @OnClick(R.id.rly_new_wodehuoyuan_back)
    public void rlyNewWoDeHuoYuanBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.rb_new_wodehuoyuan_finish)
    RadioButton rbNewWoDeHuoYuanFinish;

    @OnClick(R.id.rb_new_wodehuoyuan_finish)
    public void rbNewWoDeHuoYuanFinishOnclick(){
        initTabBar(0);
        getDataFromNet("5","1");
        type = 5;
    }

    @BindView(R.id.rb_new_wodehuoyuan_releaseing)
    RadioButton rbNewWoDeHuoYuanReleaseing;
    @OnClick(R.id.rb_new_wodehuoyuan_releaseing)
    public void rbNewWoDeHuoYuanReleaseingOnclick(){
        initTabBar(1);
        getDataFromNet("0","1");
        type = 0;
    }

    @BindView(R.id.rb_new_wodehuoyuan_all)
    RadioButton rbNewWoDeHuoYuanAll;
    @OnClick(R.id.rb_new_wodehuoyuan_all)
    public void rbNewWoDeHuoYuanAllOnclick(){
        initTabBar(2);
        getDataFromNet("-1","1");
        type = -1;
    }
    @BindView(R.id.iv_new_wodehuoyuan_tab_bottom)
    ImageView ivNewWoDeHuoYuanTabBottom;

    @BindView(R.id.xrv_new_wodehuoyuan)
    XRecyclerView xrvNewWoDeHuoYuan;
    @BindView(R.id.pb_new_wodehuoyuan)
    ProgressBar pbNewWoDeHuoYuan;

    private String loginId;
    public NewWoDeHuoYuanController(Activity activity1){
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
         marginLeft = (((width/6)/2.5));
         ivWidth = (width/4.5);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams((int)ivWidth,activity.getResources().getDimensionPixelSize(R.dimen.dimen_30dp));
        params.setMargins((int)marginLeft,0,0,0);
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 3 - bmpW)/3 ;// 计算偏移量 满屏 screenW/有几个tab 就除以几 dialog 则为dialog的宽度除以tab数量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        ivNewWoDeHuoYuanTabBottom.setImageMatrix(matrix);
        ivNewWoDeHuoYuanTabBottom.setLayoutParams(params);
        initTabBar(1);
        getDataFromNet("2","1");

    }


    private void initXRV(){
        dataList = new ArrayList<>();
        adapter = new NewWoDeHuoYuanXRVAdapter(activity,dataList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrvNewWoDeHuoYuan.setAdapter(adapter);
        xrvNewWoDeHuoYuan.setLayoutManager(linearLayoutManager);
        adapter.deleteOnClickCallBack(new NewWoDeHuoYuanXRVAdapter.DeleteListener() {
            @Override
            public void onDeleteOnclick(boolean isSuccess) {
                getDataFromNet(""+type,"1");
            }
        });

        xrvNewWoDeHuoYuan.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        xrvNewWoDeHuoYuan.setLoadingMoreProgressStyle(ProgressStyle.BallRotate);
        xrvNewWoDeHuoYuan.setArrowImageView(R.drawable.iconfont_downgrey);

        /*View header = LayoutInflater.from(activity).inflate(R.layout.recyclerview_header, (ViewGroup)activity.findViewById(android.R.id.content),false);
        xrvNewHuoYuanList.addHeaderView(header);*/

        xrvNewWoDeHuoYuan.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                refreshTime ++;
                times = 0;
                new Handler().postDelayed(new Runnable(){
                    public void run() {

                        page=1;
                        getDataFromNet(""+type,page+"");

                        xrvNewWoDeHuoYuan.refreshComplete();
                    }

                }, 1000);            //refresh data here
            }

            @Override
            public void onLoadMore() {
                if(times < 2){
                    new Handler().postDelayed(new Runnable(){
                        public void run() {
                            page++;
                            getDataFromNet(""+type,page+"");
                            xrvNewWoDeHuoYuan.loadMoreComplete();

                        }
                    }, 1000);
                } else {
                    new Handler().postDelayed(new Runnable() {
                        public void run() {
                            page++;
                            getDataFromNet(""+type,page+"");
                            xrvNewWoDeHuoYuan.setNoMore(true);

                        }
                    }, 1000);
                }
                times ++;
            }
        });
        xrvNewWoDeHuoYuan.refresh();
    }


    public void initTabBar(int arg0){
      /*  int one = offset*3  + bmpW;// 页卡1 -> 页卡2 偏移量*/
        double one = marginLeft*1.7  + ivWidth;// 页卡1 -> 页卡2 偏移量
        double two = one * 2;// 页卡1 -> 页卡3 偏移量
       /* int three = one * 3;// 页卡1 -> 页卡3 偏移量*/
        Animation animation = null;
        switch (arg0) {
            case 0:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(0, 0, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation((int) one, 0, 0, 0);
                }else if (currIndex == 2) {
                    animation = new TranslateAnimation((int) two, 0, 0, 0);
                }
                currIndex = arg0;
                animation.setFillAfter(true);// True:图片停在动画结束位置
                animation.setDuration(200);
                ivNewWoDeHuoYuanTabBottom.startAnimation(animation);
                /*initCheYuanText();*/
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(0, (int) one, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation((int) one,(int)  one, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation((int) two,(int)  one, 0, 0);
                }
                currIndex = arg0;
                animation.setFillAfter(true);// True:图片停在动画结束位置
                animation.setDuration(200);
                ivNewWoDeHuoYuanTabBottom.startAnimation(animation);
               /* initHuoYuanText();*/
                break;
            case 2:
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
                ivNewWoDeHuoYuanTabBottom.startAnimation(animation);
               /* initHuoYuanText();*/
                break;

        }
    }

    private void getDataFromNet(String lx,final String p){
        pbNewWoDeHuoYuan.setVisibility(View.VISIBLE);
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("login_id",loginId);
        paramMap.put("lx",lx);
        paramMap.put("p",p);

        NewCheHuoListNetWork newCheHuoListNetWork = new NewCheHuoListNetWork();
        newCheHuoListNetWork.getWoDeHuoYuanFromNet(paramMap, new Observer<NewWoDeHuoYuanBean>() {
            @Override
            public void onCompleted() {
                pbNewWoDeHuoYuan.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                pbNewWoDeHuoYuan.setVisibility(View.GONE);
            }

            @Override
            public void onNext(NewWoDeHuoYuanBean newWoDeHuoYuanBean) {
                if(newWoDeHuoYuanBean.getStatus().equals("0")){
                    if(p.equals("1")){
                        dataList.clear();
                    }
                    adapter.setAdapter(newWoDeHuoYuanBean.getNr().getList());
                }
                pbNewWoDeHuoYuan.setVisibility(View.GONE);
            }
        });
    }
}
