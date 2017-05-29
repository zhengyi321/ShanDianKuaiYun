package com.zhyan.shandiankuaiyun.Main.MineFragment.JiFenShangCheng.ShangPinXiangQing.TianXieShouHuoDiZhi;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyun.BaseController;
import com.zhyan.shandiankuaiyun.NetWork.CompanyNetWork;
import com.zhyan.shandiankuaiyun.R;
import com.zhyan.shandiankuaiyunlib.Bean.ShangPinXiangQingAddAddressBean;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by az on 2017/5/23.
 */

public class TianXieShouHuoDiZhiController extends BaseController {

    @BindView(R.id.rly_main_mine_jifenshangcheng_shangpinxiangqing_tianxieshouhuodizhi_back)
    RelativeLayout rlyMainMineJiFenShangChengShangPinXiangQingTianXieShouHuoDiZhiBack;
    @OnClick(R.id.rly_main_mine_jifenshangcheng_shangpinxiangqing_tianxieshouhuodizhi_back)
    public void rlyMainMineJiFenShangChengShangPinXiangQingTianXieShouHuoDiZhiBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.et_main_mine_jifenshangcheng_shangpinxiangqing_tianxieshouhuodizhi_get_name)
    EditText etMainMineJiFenShangChengShangPinXiangQingTianXieShouHuoDiZhiGetName;
    @BindView(R.id.et_main_mine_jifenshangcheng_shangpinxiangqing_tianxieshouhuodizhi_addr)
    EditText etMainMineJiFenShangChengShangPinXiangQingTianXieShouHuoDiZhiAddr;
    @BindView(R.id.et_main_mine_jifenshangcheng_shangpinxiangqing_tianxieshouhuodizhi_tel)
    EditText etMainMineJiFenShangChengShangPinXiangQingTianXieShouHuoDiZhiGetTel;
    @BindView(R.id.bt_main_mine_jifenshangcheng_shangpinxiangqing_tianxieshouhuodizhi_save)
    Button btMainMineJiFenShangChengShangPinXiangQingTianXieShouHuoDiZhiSave;
    @OnClick(R.id.bt_main_mine_jifenshangcheng_shangpinxiangqing_tianxieshouhuodizhi_save)
    public void btMainMineJiFenShangChengShangPinXiangQingTianXieShouHuoDiZhiSaveOnclick(){
        submitDetailToNet();
    }

    public TianXieShouHuoDiZhiController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initAddrData();
    }

    private void initAddrData(){
        String name = activity.getIntent().getStringExtra("name");
        String tel = activity.getIntent().getStringExtra("tel");
        String addr = activity.getIntent().getStringExtra("addr");
        etMainMineJiFenShangChengShangPinXiangQingTianXieShouHuoDiZhiAddr.setText(addr);
        etMainMineJiFenShangChengShangPinXiangQingTianXieShouHuoDiZhiGetTel.setText(tel);
        etMainMineJiFenShangChengShangPinXiangQingTianXieShouHuoDiZhiGetName.setText(name);
    }
    private Map<String,String> getParamMap(){
        Map<String,String> paramMap = new HashMap<>();
        String name = etMainMineJiFenShangChengShangPinXiangQingTianXieShouHuoDiZhiGetName.getText().toString().trim();
        if(name == null){
            name = "";
        }else{
            name = name.replaceAll(" ","");
        }
        paramMap.put("name",name);
        String tel = etMainMineJiFenShangChengShangPinXiangQingTianXieShouHuoDiZhiGetTel.getText().toString().trim();
        if(tel == null){
            tel = "";
        }else{
            tel = tel.replaceAll(" ","");
        }
        paramMap.put("tel",tel);
        String addr = etMainMineJiFenShangChengShangPinXiangQingTianXieShouHuoDiZhiGetName.getText().toString().trim();
        if(addr == null){
            addr = "";
        }else{
            addr = addr.replaceAll(" ","");
        }
        paramMap.put("addr",addr);


        return paramMap;
    }

    private void submitDetailToNet(){
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if(login_id == null){
            login_id = "";
        }else{
            login_id = login_id.replaceAll(" ","");
        }
        CompanyNetWork companyNetWork = new CompanyNetWork();
        companyNetWork.addAddressToNet(login_id,getParamMap(), new Observer<ShangPinXiangQingAddAddressBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ShangPinXiangQingAddAddressBean shangPinXiangQingAddAddressBean) {
                Toast.makeText(activity,shangPinXiangQingAddAddressBean.getMsg(),Toast.LENGTH_LONG).show();
                activity.finish();
            }
        });
    }
}
