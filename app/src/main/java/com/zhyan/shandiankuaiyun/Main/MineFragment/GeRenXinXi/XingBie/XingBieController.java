package com.zhyan.shandiankuaiyun.Main.MineFragment.GeRenXinXi.XingBie;

import android.app.Activity;
import android.content.Intent;
import android.widget.Button;
import android.widget.CheckBox;
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
import rx.Observer;

/**
 * Created by az on 2017/5/10.
 */

public class XingBieController extends BaseController {


    @BindView(R.id.rly_main_mine_gerenxinxi_xingbie_back)
    RelativeLayout rlyMainMineGeRenXinXiXingBieBack;
    @OnClick(R.id.rly_main_mine_gerenxinxi_xingbie_back)
    public void rlyMainMineGeRenXinXiXingBieBackOnclick(){
        activity.finish();
    }

    @BindView(R.id.cb_main_mine_gerenxinxi_xingbie_nan)
    CheckBox cbMainMineGeRenXingXiXingBieNan;

    @BindView(R.id.rly_main_mine_gerenxinxi_xingbie_nan)
    RelativeLayout rlyMainMineGeRenXinXiXingBieNan;
    @OnClick(R.id.rly_main_mine_gerenxinxi_xingbie_nan)
    public void rlyMainMineGeRenXingXiXingBieNanOnclick(){
        /*Toast.makeText(activity,"1",Toast.LENGTH_LONG).show();*/
        selectNan();
    }
    @BindView(R.id.cb_main_mine_gerenxinxi_xingbie_nv)
    CheckBox cbMainMineGeRenXingXiXingBieNv;

    @BindView(R.id.rly_main_mine_gerenxinxi_xingbie_nv)
    RelativeLayout rlyMainMineGeRenXinXiXingBieNV;
    @OnClick(R.id.rly_main_mine_gerenxinxi_xingbie_nv)
    public void rlyMainMineGeRenXingXiXingBieNvOnclick(){
        /*Toast.makeText(activity,"2",Toast.LENGTH_LONG).show();*/
        selectNv();
    }
    @BindView(R.id.bt_main_mine_gerenxinxi_xingbie_save)
    Button btMainMineGeRenXinXiXingBieSave;
    @OnClick(R.id.bt_main_mine_gerenxinxi_xingbie_save)
    public void btMainMineGeRenXinXiXingBieSaveOnclick(){
        save();
    }

    public XingBieController(Activity activity1){
        activity = activity1;
        init();
    }


    @Override
    protected void init() {
        ButterKnife.bind(this,activity);

        initSex();
     /*   initCheckBox();*/
    }

    private void initSex(){
        Intent intent = activity.getIntent();
        String sex = intent.getStringExtra("sex");
        if(sex.equals("男")){
            selectNan();
        }else{
            selectNv();
        }
    }
/*    private void initCheckBox(){
        if(cbMainMineGeRenXingXiXingBieNan.isChecked()){
            cbMainMineGeRenXingXiXingBieNv.setChecked(false);
        }else{
            cbMainMineGeRenXingXiXingBieNv.setChecked(true);
        }
    }*/
    private void selectNan(){
        cbMainMineGeRenXingXiXingBieNan.setChecked(true);
        cbMainMineGeRenXingXiXingBieNv.setChecked(false);
        cbMainMineGeRenXingXiXingBieNan.setBackgroundResource(R.mipmap.agree);
        cbMainMineGeRenXingXiXingBieNv.setBackgroundResource(R.mipmap.notagree);
    }
    private void selectNv(){
        cbMainMineGeRenXingXiXingBieNan.setChecked(false);
        cbMainMineGeRenXingXiXingBieNv.setChecked(true);
        cbMainMineGeRenXingXiXingBieNan.setBackgroundResource(R.mipmap.notagree);
        cbMainMineGeRenXingXiXingBieNv.setBackgroundResource(R.mipmap.agree);
    }

    private void save(){

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
                if(updateResultBean.getStatus() == 0) {
                    Toast.makeText(activity, updateResultBean.getMsg(), Toast.LENGTH_LONG).show();
                }
                activity.finish();
            }
        });
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

        paramMap.put("qq","");
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId).trim();
        if(login_id == null){
            login_id = "";
        }else{
            login_id = login_id.replaceAll(" ","");
        }
        paramMap.put("login_id",login_id);



        String sex = "1";
        if(cbMainMineGeRenXingXiXingBieNan.isChecked()){
            sex = "1";
        }else{
            sex = "2";
        }

        paramMap.put("sex",sex);
        return paramMap;
    }

}
