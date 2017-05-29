package com.zhyan.shandiankuaiyun.Main.ReleaseFragment.FaBuHuoYuan.HuoWuLeiBie;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.RelativeLayout;

import com.zhyan.shandiankuaiyun.BaseController;
import com.zhyan.shandiankuaiyun.Main.ReleaseFragment.BanJia.CarType.CarTypeRVAdapter;
import com.zhyan.shandiankuaiyun.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/5/16.
 */

public class GoodsTypeController extends BaseController {

    @BindView(R.id.rly_main_release_fabuhuowu_huowuleibie_back)
    RelativeLayout rlyMainReleaseFaBuHuoWuHuoWuLeiBieBack;
    @OnClick(R.id.rly_main_release_fabuhuowu_huowuleibie_back)
    public void rlyMainReleaseFaBuHuoWuHuoWuLeiBieBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.rv_main_release_fabuhuowu_huowuleibie_content)
    RecyclerView rvMainReleaseFaBuHuoWuHuoWuLeiBieContent;
   GoodsTypeRVAdapter goodsTypeRVAdapter;
    private String[] strings = {"设备","矿产","建材","食品","蔬菜","生鲜","药品","化工","木材","家畜","纺织品","日用品","电子电器","农副产品","其他类别"};
    public GoodsTypeController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initRV();
    }

    private void initRV(){
       goodsTypeRVAdapter = new GoodsTypeRVAdapter(activity,strings);
        LinearLayoutManager layoutManager = new LinearLayoutManager(activity);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvMainReleaseFaBuHuoWuHuoWuLeiBieContent.setLayoutManager(layoutManager);
        rvMainReleaseFaBuHuoWuHuoWuLeiBieContent.setAdapter(goodsTypeRVAdapter);
    }
}
