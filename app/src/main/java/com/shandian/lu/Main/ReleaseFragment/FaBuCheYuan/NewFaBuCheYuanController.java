package com.shandian.lu.Main.ReleaseFragment.FaBuCheYuan;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.shandian.lu.BaseController;
import com.shandian.lu.Main.ReleaseFragment.FaBuHuoYuan.NewFaBuHuoYuanAddPicRVAdapter;
import com.shandian.lu.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/5/17.
 */

public class NewFaBuCheYuanController extends BaseController {

    @BindView(R.id.rly_new_main_release_fabucheyuan_back)
    RelativeLayout rlyNewMainReleaseFaBuCheWuBack;
    @OnClick(R.id.rly_new_main_release_fabucheyuan_back)
    public void rlyNewMainReleaseFaBuCheWuBackOnclick(){
        activity.finish();
    }

    @BindView(R.id.rv_main_release_new_fabucheyuan_add_pic)
    RecyclerView rvMainReleaseNewFaBuHuoYuanAddPic;
    private List<String> tempList ;
    public NewFaBuCheYuanAddPicRVAdapter addPicRVAdapter;
    public NewFaBuCheYuanController(Activity activity1){
        activity = activity1;
        init();
    }




    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initRV();
    }
    private void initRV(){
        tempList = new ArrayList<>();
        tempList.add("");

        addPicRVAdapter = new NewFaBuCheYuanAddPicRVAdapter(activity,tempList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvMainReleaseNewFaBuHuoYuanAddPic.setAdapter(addPicRVAdapter);
        rvMainReleaseNewFaBuHuoYuanAddPic.setLayoutManager(linearLayoutManager);

    }
}
