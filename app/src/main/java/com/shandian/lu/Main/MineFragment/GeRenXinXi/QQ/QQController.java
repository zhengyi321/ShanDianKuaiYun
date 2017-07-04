package com.shandian.lu.Main.MineFragment.GeRenXinXi.QQ;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewGeRenXinXiSubmitBean;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.shandian.lu.BaseController;
import com.shandian.lu.NetWork.UserNetWork;
import com.shandian.lu.R;
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

public class QQController extends BaseController {

    @BindView(R.id.rly_main_mine_gerenxinxi_qq_back)
    RelativeLayout rlyMainMineGeRenXinXiQQBack;
    @OnClick(R.id.rly_main_mine_gerenxinxi_qq_back)
    public void rlyMainMineGeRenXinXiQQBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.pb_new_main_mine_gerenxinxi_qq)
    ProgressBar pbNewMainMineGeRenXinXiQQ;
    @BindView(R.id.et_main_mine_gerenxinxi_qq)
    EditText etMainMineGeRenXinXiQQ;
    @BindView(R.id.bt_main_mine_gerenxinxi_qq_submit)
    Button btMainMineGeRenXinXiQQSubmit;
    @OnClick(R.id.bt_main_mine_gerenxinxi_qq_submit)
    public void btMainMineGeRenXinXiQQSubmitOnclick(){
        /*updateQQToNet();*/
        newUpdateToNet();
    }
    public QQController(Activity activity1){
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

        paramMap.put("address","");


        paramMap.put("wecat","");




        paramMap.put("nickename","");


        paramMap.put("image","");

        paramMap.put("email","");
        String qq = etMainMineGeRenXinXiQQ.getText().toString().trim();
        if(qq == null){
            qq = "";
        }else{
            qq = qq.replaceAll(" ","");
        }
        paramMap.put("qq",qq);
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
    public void updateQQToNet(){
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




    private void newUpdateToNet(){
        pbNewMainMineGeRenXinXiQQ.setVisibility(View.VISIBLE);
        UserNetWork userNetWork = new UserNetWork();
        userNetWork.submitNewGeRenXinXiToNet(getNewParamMap(), new Observer<NewGeRenXinXiSubmitBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewGeRenXinXiSubmitBean newGeRenXinXiSubmitBean) {
                Toast.makeText(activity,newGeRenXinXiSubmitBean.getMsg(),3000).show();
                pbNewMainMineGeRenXinXiQQ.setVisibility(View.GONE);
            }
        });

    }
    private Map<String,Object>  getNewParamMap(){
        Map<String,Object> paramMap = new HashMap<>();
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        String loginId= xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            Toast.makeText(activity,"请登录",3000).show();
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            return paramMap;
        }

        paramMap.put("login_id",loginId);
        String qq = etMainMineGeRenXinXiQQ.getText().toString().trim();
        qq = qq.replaceAll(" ","");

        paramMap.put("qq_code",qq);
        return paramMap;
    }
}
