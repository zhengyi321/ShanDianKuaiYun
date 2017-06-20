package com.shandian.lu.Main.MineFragment.SheZhi;

import android.app.Activity;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MineFragment.Login.FindPass.FindPassActivity;
import com.shandian.lu.Main.MineFragment.SheZhi.XiaoXiSheZhi.XiaoXiSheZhiActivity;
import com.shandian.lu.Main.MineFragment.SheZhi.XiuGaiMiMa.XiuGaiMiMaActivity;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.DiskCache;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.DiskLruCache;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/5/4.
 */

public class SheZhiController extends BaseController {


    @BindView(R.id.tv_main_mine_shezhi_disksize)
    TextView tvMainMineSheZhiDiskSize;
    @BindView(R.id.rly_main_mine_shezhi_back)
    RelativeLayout rlyMainMineSheZhiBack;
    @OnClick(R.id.rly_main_mine_shezhi_back)
    public void rlyMainMineSheZhiBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.lly_main_mine_shezhi_findpass)
    LinearLayout llyMainMineSheZhiFindPass;
    @OnClick(R.id.lly_main_mine_shezhi_findpass)
    public void llyMainMineSheZhiFindPassOnclick(){
        Intent intent = new Intent(activity, FindPassActivity.class);
        activity.startActivity(intent);
    }
    @BindView(R.id.lly_main_mine_shezhi_xiugaimima)
    LinearLayout llyMainMineSheZhiXiuGaiMiMa;
    @OnClick(R.id.lly_main_mine_shezhi_xiugaimima)
    public void llyMainMineSheZhiXiuGaiMiMaOnclick(){
        Intent intent = new Intent(activity, XiuGaiMiMaActivity.class);
        activity.startActivity(intent);
    }
    @BindView(R.id.lly_main_mine_shezhi_xiaoxishezhi)
    LinearLayout llyMainMineSheZhiXiaoXiSheZhi;
    @OnClick(R.id.lly_main_mine_shezhi_xiaoxishezhi)
    public void llyMainMineSheZhiXiaoXiSheZhiOnclick(){
        Intent intent = new Intent(activity, XiaoXiSheZhiActivity.class);
        activity.startActivity(intent);
    }


    public SheZhiController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initDiskCacheSize();
    }

    private void initDiskCacheSize(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        String size = xcCacheManager.getDiskSize()+"M";
        tvMainMineSheZhiDiskSize.setText(size);
    }
}
