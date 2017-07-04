package com.shandian.lu.Main.MineFragment.WoDeYaoQing.WoDeTuiJianRen;

import android.app.Activity;
import android.content.Intent;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.NewMyTuiJianRenBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.NetWork.WoDeYaoQingNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ImageView.RoundImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/7/3.
 */

public class WoDeTuiJianRenController extends BaseController {

    @BindView(R.id.rly_new_main_mine_tjr_back)
    RelativeLayout rlyNewMainMineTJRBack;
    @OnClick(R.id.rly_new_main_mine_tjr_back)
    public void rlyNewMainMineTJRBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.riv_new_main_mine_tjr_head)
    RoundImageView rivNewMainMineTJRHead;
    @BindView(R.id.tv_new_main_mine_tjr_nick)
    TextView tvNewMainMineTJRNick;
    @BindView(R.id.tv_new_main_mine_tjr_sex)
    TextView tvNewMainMineTJRSex;
    @BindView(R.id.tv_new_main_mine_tjr_tel)
    TextView tvNewMainMineTJRTel;
    @BindView(R.id.tv_new_main_mine_tjr_addr)
    TextView tvNewMainMineTJRAddr;
    @BindView(R.id.tv_new_main_mine_tjr_qq)
    TextView tvNewMainMineTJRQQ;
    @BindView(R.id.tv_new_main_mine_tjr_wx)
    TextView tvNewMainMineTJRWX;

    public WoDeTuiJianRenController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        getDetailFromNet();
    }
    private void getDetailFromNet(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            return;
        }

        WoDeYaoQingNetWork woDeYaoQingNetWork = new WoDeYaoQingNetWork();
        woDeYaoQingNetWork.getMyTuiJianRenFromNet(loginId, new Observer<NewMyTuiJianRenBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewMyTuiJianRenBean newMyTuiJianRenBean) {
                initDetail(newMyTuiJianRenBean);
            }
        });
    }
    private void initDetail(NewMyTuiJianRenBean newMyTuiJianRenBean){
        ImageLoader.getInstance().displayImage(newMyTuiJianRenBean.getImage(),rivNewMainMineTJRHead, ImageLoaderUtils.options1);
        tvNewMainMineTJRNick.setText(newMyTuiJianRenBean.getNickename());
        tvNewMainMineTJRSex.setText(newMyTuiJianRenBean.getSex());
        tvNewMainMineTJRTel.setText(newMyTuiJianRenBean.getMobile());
        tvNewMainMineTJRAddr.setText(newMyTuiJianRenBean.getCity().toString());
        tvNewMainMineTJRQQ.setText(newMyTuiJianRenBean.getQq_code().toString());
        tvNewMainMineTJRWX.setText(newMyTuiJianRenBean.getWei_code());

    }

}
