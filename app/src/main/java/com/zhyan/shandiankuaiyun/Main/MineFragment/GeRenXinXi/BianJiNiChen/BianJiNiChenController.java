package com.zhyan.shandiankuaiyun.Main.MineFragment.GeRenXinXi.BianJiNiChen;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
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
import okhttp3.ResponseBody;
import rx.Observer;

/**
 * Created by az on 2017/5/10.
 */

public class BianJiNiChenController extends BaseController {

    @BindView(R.id.rly_main_mine_gerenxinxi_bianjinichen_topbar_back)
    RelativeLayout rlyMainMineGeRenXinXiBianJiNiChenTopBarBack;
    @OnClick(R.id.rly_main_mine_gerenxinxi_bianjinichen_topbar_back)
    public void rlyMainMineGeRenXinXiBianJiNiChenTopBarBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.et_main_mine_gerenxinxi_bianjinichen_nick)
    EditText etMainMineGeRenXinXiBianJiNiChenNick;
    @BindView(R.id.bt_main_mine_gerenxinxi_bianjinichen_save)
    Button btMainMineGeRenXinXiBianJiNiChenSave;
    @OnClick(R.id.bt_main_mine_gerenxinxi_bianjinichen_save)
    public void btMainMineGeRenXinXiBianJiNiChenSaveOnclick(){
        saveNickToNet();
    }
    private String login_id = "0";
    public BianJiNiChenController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initLoginId();
    }
    private void initLoginId(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
    }
    private void saveNickToNet(){
        String nick = etMainMineGeRenXinXiBianJiNiChenNick.getText().toString();
        if(nick != null){


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









    private Map<String,String> getParamMap(){
        Map<String,String> paramMap = new HashMap<>();
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);

        paramMap.put("address","");


        paramMap.put("wecat","");



        String nickename= etMainMineGeRenXinXiBianJiNiChenNick.getText().toString().trim();
        if(nickename == null){
            nickename = "";
        }else{
            nickename = nickename.replaceAll(" ","");
        }
        paramMap.put("nickename",nickename);


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
}
