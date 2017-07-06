package com.shandian.lu.Main.MineFragment.SheZhi.XiaoXiSheZhi;

import android.app.Activity;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewTuiSongStatusBean;
import com.example.mynewslayoutlib.Bean.NewTuiSongStatusSubmitBean;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.NetWork.UserNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/7/5.
 */

public class XiaoXiSheZhiController extends BaseController {



    @BindView(R.id.rly_main_mine_sz_push_back)
    RelativeLayout rlyMainMineSZPushBack;
    @OnClick(R.id.rly_main_mine_sz_push_back)
    public void rlyMainMineSZPushBackOnclick(){
        activity.finish();
    }

    @BindView(R.id.cb_main_mine_sz_push)
    CheckBox cbMainMineSZPush;

    @OnClick(R.id.cb_main_mine_sz_push)
    public void cbMainMineSZPushOnclick(){
        isPushSetting();

    }

    public XiaoXiSheZhiController(Activity activity1){
        activity = activity1;
        init();
    }


    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        getStatusFromNet();
    }

    private void isPushSetting(){
        if(cbMainMineSZPush.isChecked()){
            cbMainMineSZPush.setBackgroundResource(R.mipmap.push);
        }else {
            cbMainMineSZPush.setBackgroundResource(R.mipmap.notpush);
        }
        submitStatusToNet();
    }


    private void getStatusFromNet(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            return;
        }
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("login_id",loginId);
        UserNetWork userNetWork = new UserNetWork();
        userNetWork.getTuiSongStatusFromNet(paramMap, new Observer<NewTuiSongStatusBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewTuiSongStatusBean newTuiSongStatusBean) {
                initStatus(newTuiSongStatusBean);
            }
        });

    }

    private void initStatus(NewTuiSongStatusBean newTuiSongStatusBean){
        String zt = newTuiSongStatusBean.getNr().getTszt();
        if(zt.equals("0")){
            cbMainMineSZPush.setChecked(false);
            cbMainMineSZPush.setBackgroundResource(R.mipmap.notpush);
        }else {
            cbMainMineSZPush.setChecked(true);
            cbMainMineSZPush.setBackgroundResource(R.mipmap.push);
        }
    }

    private void submitStatusToNet(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            return;
        }
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("login_id",loginId);
        paramMap.put("tj","1");
        String zt = "0";
        if(!cbMainMineSZPush.isChecked()){
            zt = "0";
        }else {
            zt = "1";
        }
        paramMap.put("tszt",zt);
        UserNetWork userNetWork = new UserNetWork();
        userNetWork.submitTuiSongStatusToNet(paramMap, new Observer<NewTuiSongStatusSubmitBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewTuiSongStatusSubmitBean newTuiSongStatusSubmitBean) {
                Toast.makeText(activity,newTuiSongStatusSubmitBean.getMsg(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
