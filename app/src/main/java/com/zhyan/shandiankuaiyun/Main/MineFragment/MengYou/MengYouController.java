package com.zhyan.shandiankuaiyun.Main.MineFragment.MengYou;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;

import com.zhyan.shandiankuaiyun.BaseController;
import com.zhyan.shandiankuaiyun.R;
import com.zhyan.shandiankuaiyunlib.Widget.RecyclerView.XRecycleView.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/4.
 */

public class MengYouController extends BaseController {

    @BindView(R.id.xrv_main_mine_mengyou_content)
    XRecyclerView xrvMainMineMengYouContent;

    MengYouXRVAdapter adapter ;

    public MengYouController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initXRV();
    }

    private void initXRV(){
        List<String> stringList = new ArrayList<>();
        stringList.add("");
        stringList.add("");
        stringList.add("");
        stringList.add("");
        adapter = new MengYouXRVAdapter(activity,stringList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrvMainMineMengYouContent.setAdapter(adapter);
        xrvMainMineMengYouContent.setLayoutManager(layoutManager);

    }
}
