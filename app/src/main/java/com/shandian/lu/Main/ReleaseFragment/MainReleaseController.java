package com.shandian.lu.Main.ReleaseFragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.shandian.lu.Main.ReleaseFragment.BanJia.ReleaseBanJiaActivity;
import com.shandian.lu.Main.ReleaseFragment.FaBuHuoYuan.FaBuHuoYuanActivity;
import com.shandian.lu.Main.ReleaseFragment.HuiTouChe.HuiTouCheActivity;
import com.shandian.lu.Main.ReleaseFragment.RenRenKuaiDi.RenRenKuaiDiActivity;
import com.shandian.lu.Main.ReleaseFragment.TeZhongYunShu.TeZhongYunShuActivity;
import com.shandian.lu.Main.ReleaseFragment.ZuCheHuoYun.ZuCheHuoYunActivity;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.ReleaseFragment.ZhuanXianWuLiu.ZhuanXianWuLiuReleaseActivity;
import com.shandian.lu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/4/28.
 */

public class MainReleaseController extends BaseController{


    @BindView(R.id.lly_main_release_zxwl)
    LinearLayout llyMainReleaseZXWL;
    @OnClick(R.id.lly_main_release_zxwl)
    public void llyMainReleaseZXWLOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(view.getContext(), ZhuanXianWuLiuReleaseActivity.class);
        view.getContext().startActivity(intent);
    }
    @BindView(R.id.lly_main_release_rrkd)
    LinearLayout llyMainReleaseRRKD;
    @OnClick(R.id.lly_main_release_rrkd)
    public void llyMainReleaseRRKDOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(view.getContext(), RenRenKuaiDiActivity.class);
        view.getContext().startActivity(intent);
    }
    @BindView(R.id.lly_main_release_zchy)
    LinearLayout llyMainReleaseZCHY;
    @OnClick(R.id.lly_main_release_zchy)
    public void llyMainReleaseZCHYOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(view.getContext(), ZuCheHuoYunActivity.class);
        view.getContext().startActivity(intent);
    }
    @BindView(R.id.lly_main_release_tzys)
    LinearLayout llyMainReleaseTZYS;
    @OnClick(R.id.lly_main_release_tzys)
    public void llyMainReleaseTZYSOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(view.getContext(), TeZhongYunShuActivity.class);
        view.getContext().startActivity(intent);
    }
    @BindView(R.id.lly_main_release_htc)
    LinearLayout llyMainReleaseHTC;
    @OnClick(R.id.lly_main_release_htc)
    public void llyMainReleaseHTCOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(view.getContext(), HuiTouCheActivity.class);
        view.getContext().startActivity(intent);
    }
    @BindView(R.id.lly_main_release_bj)
    LinearLayout llyMainReleaseBJ;
    @OnClick(R.id.lly_main_release_bj)
    public void llyMainReleaseBJOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(view.getContext(), ReleaseBanJiaActivity.class);
        view.getContext().startActivity(intent);
    }

    @BindView(R.id.rly_main_release_topbar_fabuhuoyuan)
    RelativeLayout rlyMainReleaseTopBarFaBuHuoYuan;
    @OnClick(R.id.rly_main_release_topbar_fabuhuoyuan)
    public void rlyMainReleaseTopBarFaBuHuoYuanOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(view.getContext());
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(view.getContext(),"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(view.getContext(), FaBuHuoYuanActivity.class);
        view.getContext().startActivity(intent);
    }




    public MainReleaseController(View view1){
        view = view1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,view);

    }

}
