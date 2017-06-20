package com.shandian.lu.Main.MineFragment.JiFenShangCheng.JiFenTiXian;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.JiFenBean;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.NetWork.JiFenShangChengNetWork;
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
 * Created by Administrator on 2017/6/20.
 */

public class JiFenTiXianController extends BaseController{


    @BindView(R.id.rly_main_mine_jfsc_jftx_back)
    RelativeLayout rlyMainMineJFSCJFTXBack;
    @OnClick(R.id.rly_main_mine_jfsc_jftx_back)
    public void rlyMainMineJFSCJFTXBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.et_main_mine_jfsc_jftx_account)
    EditText etMainMineJFSCJFTXAccount;
    @BindView(R.id.et_main_mine_jfsc_jftx_name)
    EditText etMainMineJFSCJFTXName;
    @BindView(R.id.et_main_mine_jfsc_jftx_jine)
    EditText etMainMineJFSCJFTXJine;
    @BindView(R.id.tv_main_mine_jfsc_jftx_jf)
    TextView tvMainMineJFSCJFTXJF;
    @BindView(R.id.tv_main_mine_jfsc_jftx_ktx)
    TextView tvMainMineJFSCJFTXKTX;
    @BindView(R.id.bt_main_mine_jfsc_jftx_submit)
    Button btMainMineJFSCJFTXSubmit;
    @OnClick(R.id.bt_main_mine_jfsc_jftx_submit)
    public void btMainMineJFSCJFTXSubmitOnclick(){
        submitTiXianDataToNet();
    }
    public JiFenTiXianController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        getJiFenFromNet();
        getJiFenFromNet();
    }



    private void getJiFenFromNet(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            return;
        }
        Map<String,String> paramMap = new HashMap<>();

        paramMap.put("id",loginId);
        paramMap.put("poslx","0");
        paramMap.put("jine","");
        paramMap.put("zhanghao","");
        paramMap.put("name","");
        JiFenShangChengNetWork jiFenShangChengNetWork = new JiFenShangChengNetWork();
        jiFenShangChengNetWork.getJiFenFromNet(paramMap, new Observer<JiFenBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(JiFenBean jiFenBean) {
                if(jiFenBean.getStatus().equals("0")){
                    tvMainMineJFSCJFTXJF.setText(jiFenBean.getNr().getJifen());
                    tvMainMineJFSCJFTXKTX.setText(jiFenBean.getNr().getJine()+"");
                }
            }
        });

    }


    private void submitTiXianDataToNet(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            return;
        }

        Map<String,String> paramMap = new HashMap<>();

        paramMap.put("id",loginId);
        paramMap.put("poslx","1");
        String jine = etMainMineJFSCJFTXJine.getText().toString();

        paramMap.put("jine",jine);
        String zhanghao = etMainMineJFSCJFTXAccount.getText().toString();
        String name = etMainMineJFSCJFTXName.getText().toString();
        paramMap.put("zhanghao",zhanghao);
        paramMap.put("name",name);
        JiFenShangChengNetWork jiFenShangChengNetWork = new JiFenShangChengNetWork();
        jiFenShangChengNetWork.getJiFenFromNet(paramMap, new Observer<JiFenBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(JiFenBean jiFenBean) {
                Toast.makeText(activity,jiFenBean.getMsg(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
