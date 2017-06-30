package com.shandian.lu.Main.ReleaseFragment.FaBuHuoYuan;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.shandian.lu.BaseController;
import com.shandian.lu.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/5/17.
 */

public class NewFaBuHuoYuanV2Controller extends BaseController {
    @BindView(R.id.pb_new_fabuhuoyuan)
    ProgressBar pbNewFaBuHuoYuan;
    @BindView(R.id.rly_new_main_release_fabuhuoyuan_back)
    RelativeLayout rlyNewMainReleaseFaBuHuoWuBack;
    @OnClick(R.id.rly_new_main_release_fabuhuoyuan_back)
    public void rlyNewMainReleaseFaBuHuoWuBackOnclick(){
        activity.finish();
    }


    @BindView(R.id.lly_new_fabuhuoyuan_content_v2)
    LinearLayout llyNewFaBuHuoYuanContentV2;
    @BindView(R.id.rv_main_release_new_fabuhuoyuan_add_pic)
    RecyclerView rvMainReleaseNewFaBuHuoYuanAddPic;
    private ArrayList<String> tempList ;
    public NewFaBuHuoYuanAddPicV2RVAdapter addPicRVAdapter;
    public NewFaBuHuoYuanV2Controller(Activity activity1){
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

        addPicRVAdapter = new NewFaBuHuoYuanAddPicV2RVAdapter(activity,tempList,pbNewFaBuHuoYuan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvMainReleaseNewFaBuHuoYuanAddPic.setAdapter(addPicRVAdapter);
        rvMainReleaseNewFaBuHuoYuanAddPic.setLayoutManager(linearLayoutManager);

    }
}
