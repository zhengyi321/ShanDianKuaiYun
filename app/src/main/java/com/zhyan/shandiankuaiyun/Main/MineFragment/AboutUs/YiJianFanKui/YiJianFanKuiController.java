package com.zhyan.shandiankuaiyun.Main.MineFragment.AboutUs.YiJianFanKui;

import android.app.Activity;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyun.BaseController;
import com.zhyan.shandiankuaiyun.NetWork.CompanyNetWork;
import com.zhyan.shandiankuaiyun.R;
import com.zhyan.shandiankuaiyunlib.Bean.YiJianFanKuiBean;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by az on 2017/5/22.
 */

public class YiJianFanKuiController extends BaseController {
    @BindView(R.id.rly_main_mine_aboutus_yijianfankui_back)
    RelativeLayout rlyMainMineAboutUsYiJianFanKuiBack;
    @OnClick(R.id.rly_main_mine_aboutus_yijianfankui_back)
    public void rlyMainMineAboutUsYiJianFanKuiBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.et_main_mine_aboutus_yijianfankui_content)
    EditText etMainMineAboutUsYiJianFanKuiContent;
    @BindView(R.id.rly_main_mine_aboutus_yijianfankui_content_submit)
    RelativeLayout rlyMainMineAboutYiJianFanKuiContentSubmit;
    @OnClick(R.id.rly_main_mine_aboutus_yijianfankui_content_submit)
    public void rlyMainMineAboutYiJianFanKuiContentSubmitOnclick(){
        submitFeedBackToNet();
    }
    public YiJianFanKuiController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }


    private void submitFeedBackToNet(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String login_id = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((login_id == null)||(login_id.isEmpty())){
            Toast.makeText(activity,"请登入",Toast.LENGTH_LONG).show();
            return;
        }
        String feedContent = etMainMineAboutUsYiJianFanKuiContent.getText().toString().trim();
        if(feedContent == null){
            feedContent = "";
        }else{
            feedContent = feedContent.replaceAll(" ","");
        }
        if(feedContent.length() > 300){
            Toast.makeText(activity,"请输入300字以内",Toast.LENGTH_LONG).show();
            return;
        }
        CompanyNetWork companyNetWork = new CompanyNetWork();
        companyNetWork.submitYiJianToNet(login_id, feedContent, new Observer<YiJianFanKuiBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(YiJianFanKuiBean yiJianFanKuiBean) {
                Toast.makeText(activity,yiJianFanKuiBean.getMessage(),Toast.LENGTH_LONG).show();
                activity.finish();
            }
        });

    }
}
