package com.shandian.lu.Main.MineFragment.CommonFaBuCheYuan;

import android.app.Activity;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.shandian.lu.Main.ReleaseFragment.FaBuHuoYuan.FaBuHuoYuanActivity;
import com.shandian.lu.Main.ReleaseFragment.HuiTouChe.HuiTouCheActivity;
import com.shandian.lu.Main.ReleaseFragment.RenRenKuaiDi.RenRenKuaiDiActivity;
import com.shandian.lu.Main.ReleaseFragment.TeZhongYunShu.TeZhongYunShuActivity;
import com.shandian.lu.Main.ReleaseFragment.ZuCheHuoYun.ZuCheHuoYunActivity;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.ReleaseFragment.BanJia.ReleaseBanJiaActivity;
import com.shandian.lu.Main.ReleaseFragment.ZhuanXianWuLiu.ZhuanXianWuLiuReleaseActivity;
import com.shandian.lu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/6/1.
 */

public class FaBuCheYuanController extends BaseController {

    @BindView(R.id.rly_main_mine_fabucheyuan_back)
    RelativeLayout rlyMainMineFaBuCheYuanBack;
    @OnClick(R.id.rly_main_mine_fabucheyuan_back)
    public  void  rlyMainMineFaBuCheYuanBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.lly_main_mine_fabucheyuan_zxwl)
    LinearLayout llyMainMineFaBuCheYuanZXWL;
    @OnClick(R.id.lly_main_mine_fabucheyuan_zxwl)
    public void llyMainMineFaBuCheYuanZXWLOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(activity,"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(activity, ZhuanXianWuLiuReleaseActivity.class);
        activity.startActivity(intent);
    }
    @BindView(R.id.lly_main_mine_fabucheyuan_rrkd)
    LinearLayout llyMainMineFaBuCheYuanRRKD;
    @OnClick(R.id.lly_main_mine_fabucheyuan_rrkd)
    public void llyMainMineFaBuCheYuanRRKDOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(activity,"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(activity, RenRenKuaiDiActivity.class);
        activity.startActivity(intent);
    }
    @BindView(R.id.lly_main_mine_fabucheyuan_zchy)
    LinearLayout llyMainMineFaBuCheYuanZCHY;
    @OnClick(R.id.lly_main_mine_fabucheyuan_zchy)
    public void llyMainMineFaBuCheYuanZCHYOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(activity,"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(activity, ZuCheHuoYunActivity.class);
        activity.startActivity(intent);
    }
    @BindView(R.id.lly_main_mine_fabucheyuan_tzys)
    LinearLayout llyMainMineFaBuCheYuanTZYS;
    @OnClick(R.id.lly_main_mine_fabucheyuan_tzys)
    public void llyMainMineFaBuCheYuanTZYSOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(activity,"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(activity, TeZhongYunShuActivity.class);
        activity.startActivity(intent);
    }
    @BindView(R.id.lly_main_mine_fabucheyuan_htc)
    LinearLayout llyMainMineFaBuCheYuanHTC;
    @OnClick(R.id.lly_main_mine_fabucheyuan_htc)
    public void llyMainMineFaBuCheYuanHTCOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(activity,"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(activity, HuiTouCheActivity.class);
        activity.startActivity(intent);
    }
    @BindView(R.id.lly_main_mine_fabucheyuan_bj)
    LinearLayout llyMainMineFaBuCheYuanBJ;
    @OnClick(R.id.lly_main_mine_fabucheyuan_bj)
    public void llyMainMineFaBuCheYuanBJOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(activity,"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(activity, ReleaseBanJiaActivity.class);
        activity.startActivity(intent);
    }

    @BindView(R.id.rly_main_mine_fabucheyuan_topbar_fabuhuoyuan)
    RelativeLayout rlyMainMineFaBuCheYuanTopBarFaBuHuoYuan;
    @OnClick(R.id.rly_main_mine_fabucheyuan_topbar_fabuhuoyuan)
    public void rlyMainMineFaBuCheYuanTopBarFaBuHuoYuanOnclick(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(activity,"请登录",Toast.LENGTH_LONG).show();
            return;
        }
        Intent intent = new Intent(activity, FaBuHuoYuanActivity.class);
        activity.startActivity(intent);
    }


    public FaBuCheYuanController(Activity activity1){
        activity = activity1;
        init();
    }


    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }
}
