package com.zhyan.shandiankuaiyun.Main.MineFragment.GeRenXinXi.WeiZhi;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyun.BaseController;
import com.zhyan.shandiankuaiyun.NetWork.UserNetWork;
import com.zhyan.shandiankuaiyun.R;
import com.zhyan.shandiankuaiyunlib.Bean.UpdateResultBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by az on 2017/5/22.
 */

public class WeiZhiController extends BaseController {

    @BindView(R.id.rly_main_mine_gerenxinxi_weizhi_back)
    RelativeLayout rlyMainMineGeRenXinXiWeiZhiBack;

    @OnClick(R.id.rly_main_mine_gerenxinxi_weizhi_back)
    public void rlyMainMineGeRenXinXiWeiZhiBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.tv_main_mine_gerenxinxi_weizhi_addr)
    TextView tvMainMineGeRenXinXiWeiZhiAddr;
    @BindView(R.id.et_main_mine_gerenxinxi_weizhi_addr)
    EditText etMainMineGeRenXinXiWeiZhiAddr;
    @BindView(R.id.rly_main_mine_gerenxinxi_weizhi_get_addr)
    RelativeLayout rlyMainMineGeRenXinXIWeiZHiGetAddr;
    @OnClick(R.id.rly_main_mine_gerenxinxi_weizhi_get_addr)
    public void rlyMainMineGeRenXinXIWeiZHiGetAddrOnclick(){

    }
    @BindView(R.id.lly_main_mine_gerenxinxi_weizhi_get_addr)
    LinearLayout llyMainMineGeRenXinXiWeiZhiGetAddr;
    @OnClick(R.id.lly_main_mine_gerenxinxi_weizhi_get_addr)
    public void llyMainMineGeRenXinXiWeiZhiGetAddrOnclick(){

    }
    @BindView(R.id.bt_main_mine_gerenxinxi_weizhi_submit)
    Button btMainMineGeRenXinXiWeiZhiSubmit;
    @OnClick(R.id.bt_main_mine_gerenxinxi_weizhi_submit)
    public void btMainMineGeRenXinXiWeiZhiSubmitOnclick(){
        submitAddrToNet();
    }

    public WeiZhiController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }


    private Map<String,String> getParamMap(){
        Map<String,String> paramMap = new HashMap<>();
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        String addr = tvMainMineGeRenXinXiWeiZhiAddr.getText().toString().trim() + etMainMineGeRenXinXiWeiZhiAddr.getText().toString().trim();
        if(addr == null){
            addr = "";
        }else{
            addr = addr.replaceAll(" ","");
        }
        paramMap.put("address",addr);
        paramMap.put("wecat","");




        paramMap.put("nickename","");


        paramMap.put("image","");
        paramMap.put("email","");

        paramMap.put("qq","");
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId).trim();
        if(login_id == null){
            login_id = "";
        }else{
            login_id = login_id.replaceAll(" ","");
        }
        paramMap.put("login_id",login_id);






        paramMap.put("sex","0");
        return paramMap;
    }

    private void submitAddrToNet(){
        UserNetWork userNetWork = new UserNetWork();
        userNetWork.updateMyMessageToNet(getParamMap(), new Observer<UpdateResultBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(UpdateResultBean updateResultBean) {
                Toast.makeText(activity,updateResultBean.getMsg(),Toast.LENGTH_LONG).show();
                activity.finish();
            }
        });
    }
}
