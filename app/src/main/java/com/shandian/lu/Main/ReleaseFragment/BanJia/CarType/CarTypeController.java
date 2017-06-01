package com.shandian.lu.Main.ReleaseFragment.BanJia.CarType;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shandian.lu.BaseController;
import com.shandian.lu.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/16.
 */

public class CarTypeController extends BaseController {


    @BindView(R.id.rv_main_release_banjia_leibie_content)
    RecyclerView rvMainReleaseBanJiaLeiBieContent;
    CarTypeRVAdapter carTypeRVAdapter;
    private String[] strings = {"居民搬家","公司搬家","小型搬家","长途搬家货运","空调移机","设备拆迁","起重吊装","搬家搬场","钢琴搬运","家具拆装","国际搬家"};
    public CarTypeController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initRV();
    }

    private void initRV(){
        carTypeRVAdapter = new CarTypeRVAdapter(activity,strings);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMainReleaseBanJiaLeiBieContent.setLayoutManager(layoutManager);
        rvMainReleaseBanJiaLeiBieContent.setAdapter(carTypeRVAdapter);
    }
}
