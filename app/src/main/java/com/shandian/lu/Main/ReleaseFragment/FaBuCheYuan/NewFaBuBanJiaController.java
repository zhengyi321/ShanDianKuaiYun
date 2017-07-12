package com.shandian.lu.Main.ReleaseFragment.FaBuCheYuan;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.example.mynewslayoutlib.Bean.NewCheYuanDetailBean;
import com.shandian.lu.BaseController;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/7/6.
 */

public class NewFaBuBanJiaController extends BaseController {


    @BindView(R.id.rly_new_fabubanjia_back)
    RelativeLayout rlyNewFaBuBanJiaBack;
    @OnClick(R.id.rly_new_fabubanjia_back)
    public void rlyNewFaBuBanJiaBackOnclick(){
        activity.finish();
    }

    @BindView(R.id.rv_new_fabuhuoyuan_cartype)
    RecyclerView rvNewFaBuHuoYuanCarType;
    private List<Object> carTypeList;
    public NewFaBuBanJiaCarTypeRVAdapter newFaBuBanJiaCarTypeRVAdapter;


    public NewFaBuBanJiaController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);

        initRV();
    }

    private void initRV(){
        carTypeList = new ArrayList<>();
        newFaBuBanJiaCarTypeRVAdapter = new NewFaBuBanJiaCarTypeRVAdapter(activity,carTypeList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvNewFaBuHuoYuanCarType.setAdapter(newFaBuBanJiaCarTypeRVAdapter);
        rvNewFaBuHuoYuanCarType.setLayoutManager(linearLayoutManager);
    }

}
