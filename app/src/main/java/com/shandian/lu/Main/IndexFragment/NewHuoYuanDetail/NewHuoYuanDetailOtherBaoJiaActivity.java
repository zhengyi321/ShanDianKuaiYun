package com.shandian.lu.Main.IndexFragment.NewHuoYuanDetail;

import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewBaoJiaSelectBean;
import com.shandian.lu.BaseActivity;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;

import butterknife.ButterKnife;
import rx.Observer;

/**
 * Created by zhyan on 2017/6/10.
 */

public class NewHuoYuanDetailOtherBaoJiaActivity extends BaseActivity {

    private NewHuoYuanDetaiOtherBaoJiaController newHuoYuanDetaiOtherController;
    private NewHuoYuanDetaiOtherBaoJiaWaitController newHuoYuanDetaiOtherBaoJiaWaitController;
    private String hyId;
    @Override
    protected void setContentView() {
       getHyId();
    }

    @Override
    protected void init() {
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(this);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            initOtherBaoJiaController();
            return;
        }
        NewCheHuoListNetWork newCheHuoListNetWork = new NewCheHuoListNetWork();
        newCheHuoListNetWork.getHuoYuanDetail2FromNet(hyId, loginId, new Observer<NewBaoJiaSelectBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewBaoJiaSelectBean newBaoJiaSelectBean) {
                if(newBaoJiaSelectBean.getNr().getSfbj().equals("0")){
                    initOtherBaoJiaController();
                }
                else if(newBaoJiaSelectBean.getNr().getSfbj().equals("1")){
                    initOtherBaoJiaWaitController();
                    newHuoYuanDetaiOtherBaoJiaWaitController.initDetail(newBaoJiaSelectBean);
                }
                else if(newBaoJiaSelectBean.getNr().getSfbj().equals("2")){
                    initOtherBaoJiaSuccessController();

                }
                else if(newBaoJiaSelectBean.getNr().getSfbj().equals("3")){
                    initOtherBaoJiaFailController();

                }

            }
        });
       /* initOtherBaoJiaController();*/
    }
    private void getHyId(){
        hyId = getIntent().getStringExtra("hyid");
       /* Toast.makeText(this,"hyid:"+hyId,Toast.LENGTH_LONG).show();*/
        if(hyId == null){
            hyId = "";
        }
    }

    private void initOtherBaoJiaController(){
        setContentView(R.layout.activity_new_other_huoyuanxiangqing_lly);
        ButterKnife.bind(this);
        newHuoYuanDetaiOtherController = new NewHuoYuanDetaiOtherBaoJiaController(this);
    }
    private void initOtherBaoJiaWaitController(){
        setContentView(R.layout.activity_new_other_huoyuanxiangqing_wait_lly);
        ButterKnife.bind(this);
        newHuoYuanDetaiOtherBaoJiaWaitController = new NewHuoYuanDetaiOtherBaoJiaWaitController(this);
    }
    private void initOtherBaoJiaSuccessController(){
        setContentView(R.layout.activity_new_other_huoyuanxiangqing_baojiachenggong_lly);
        ButterKnife.bind(this);
        newHuoYuanDetaiOtherBaoJiaWaitController = new NewHuoYuanDetaiOtherBaoJiaWaitController(this);
    }
    private void initOtherBaoJiaFailController(){
        setContentView(R.layout.activity_new_other_huoyuanxiangqing_wait_lly);
        ButterKnife.bind(this);
        newHuoYuanDetaiOtherBaoJiaWaitController = new NewHuoYuanDetaiOtherBaoJiaWaitController(this);
    }
}
