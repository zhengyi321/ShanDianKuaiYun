package com.shandian.lu.Main.IndexFragment.HongBao;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewQiangHongBaoBean;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.NetWork.MainIndexNetWork;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.NewHongBaoGetDialog;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/6/7.
 */

public class HongBaoController extends BaseController {

    @BindView(R.id.pb_new_index_hongbao)
    ProgressBar pbNewIndexHongBao;
    @BindView(R.id.rly_new_main_index_hongbao_back)
    RelativeLayout rlyNewMainIndexHongBaoBack;
    @OnClick(R.id.rly_new_main_index_hongbao_back)
    public void rlyNewMainIndexHongBaoBackOnclick(){
        activity.finish();
    }

    NewHongBaoGetDialog newHongBaoGetDialog;
    @BindView(R.id.rly_new_index_hongbao_get)
    RelativeLayout rlyNewIndexHongBaoGet;
    @OnClick(R.id.rly_new_index_hongbao_get)
    public void rlyNewIndexHongBaoGetOnclick() {
        getHongBaoFromNet();
    }
    public HongBaoController(Activity activity1){
        activity = activity1;
        init();
    }
    private boolean isFirst = true;


    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }


    private void getHongBaoFromNet(){
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            return;
        }
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("id",loginId);
        MainIndexNetWork mainIndexNetWork = new MainIndexNetWork();
        pbNewIndexHongBao.setVisibility(View.VISIBLE);
        mainIndexNetWork.getHongBaoFromNet(paramMap, new Observer<NewQiangHongBaoBean>() {
            @Override
            public void onCompleted() {
                pbNewIndexHongBao.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                pbNewIndexHongBao.setVisibility(View.GONE);
            }

            @Override
            public void onNext(NewQiangHongBaoBean newQiangHongBaoBean) {
             /*   if(isFirst) {*/
                    Toast.makeText(activity, newQiangHongBaoBean.getMsg(), 3000).show();
           /*     }*/
                newHongBaoGetDialog = new NewHongBaoGetDialog(activity,newQiangHongBaoBean.getNr().getJine()).Build.build(activity);
                showDialog();
                pbNewIndexHongBao.setVisibility(View.GONE);
            }
        });
    }




    public void showDialog() {
        if (newHongBaoGetDialog != null && !newHongBaoGetDialog.isShowing())
            newHongBaoGetDialog.show();
    }

    public void dissmissDialog() {
        if (newHongBaoGetDialog != null && newHongBaoGetDialog.isShowing())
            newHongBaoGetDialog.dismiss();
    }
}
