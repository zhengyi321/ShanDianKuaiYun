package com.shandian.lu.Main.MineFragment.WoDeQianBao.NewTiXian;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewTiXianBean;
import com.j256.ormlite.stmt.query.In;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.Main.MineFragment.WoDeQianBao.NewTiXian.NewTiXianSuccess.NewTiXianSuccessActivity;
import com.shandian.lu.NetWork.MyWalletNetWork;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.NewQueryDialog;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/6/16.
 */

public class NewMyWalletTiXianController extends BaseController {
    String total;
    NewQueryDialog newQueryDialog;
    @BindView(R.id.pb_new_mywallet_tixian)
    ProgressBar pbNewMyWalletTiXian;
    @BindView(R.id.rly_new_mywallet_tixian_back)
    RelativeLayout rlyNewMyWalletTiXianBack;
    @OnClick(R.id.rly_new_mywallet_tixian_back)
    public void rlyNewMyWalletTiXianBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.et_new_mywallet_tixian_zhanghao)
    EditText etNewMyWalletTiXianZhangHao;
    @BindView(R.id.et_new_mywallet_tixian_name)
    EditText etNewMyWalletTiXianName;
    @BindView(R.id.et_new_mywallet_tixian_jiner)
    EditText etNewMyWalletTiXianJinEr;
    @BindView(R.id.tv_new_mywallet_tixian_jiner_all)
    TextView tvNewMyWalletTiXianJinErAll;
    @BindView(R.id.tv_new_mywallet_tixian_tixian_all)
    TextView tvNewMyWalletTiXianTiXianAll;
    @OnClick(R.id.tv_new_mywallet_tixian_tixian_all)
    public void tvNewMyWalletTiXianTiXianAllOnclick(){
        newQueryDialog = new NewQueryDialog(activity,"全部提现").Build.setCallBackListener(new NewQueryDialog.DialogCallBackListener() {
            @Override
            public void callBack(boolean isQuery) {
                if(isQuery){

                    submitDataToNet(total);
                }
                dissmissQueryDialog();
            }
        }).build(activity);
        showQueryDialog();
    }
    @BindView(R.id.bt_new_mywallet_tixian_submit)
    Button btNewMyWalletTiXianSubmit;
    @OnClick(R.id.bt_new_mywallet_tixian_submit)
    public void btNewMyWalletTiXianSubmitOnclick(){
        newQueryDialog = new NewQueryDialog(activity,"提现").Build.setCallBackListener(new NewQueryDialog.DialogCallBackListener() {
            @Override
            public void callBack(boolean isQuery) {
                if(isQuery){
                    String jine =  etNewMyWalletTiXianJinEr.getText().toString();
                    submitDataToNet(jine);
                }
                dissmissQueryDialog();
            }
        }).build(activity);
        showQueryDialog();
    }
    public void showQueryDialog() {
        if (newQueryDialog != null && !newQueryDialog.isShowing())
            newQueryDialog.show();
    }

    public void dissmissQueryDialog() {
        if (newQueryDialog != null && newQueryDialog.isShowing())
            newQueryDialog.dismiss();
    }

    public NewMyWalletTiXianController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initAll();
    }
    private void initAll(){
        total = activity.getIntent().getStringExtra("total");
        if(total == null){
            total = "";
        }
        tvNewMyWalletTiXianJinErAll.setText(total);
    }

    private void submitDataToNet(String jine){
        pbNewMyWalletTiXian.setVisibility(View.VISIBLE);
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
        String zhanghao = etNewMyWalletTiXianZhangHao.getText().toString();
        if(zhanghao.isEmpty()){
            Toast.makeText(activity,"请输入账号",Toast.LENGTH_LONG).show();
            return;
        }
        paramMap.put("zhanghao",zhanghao);
        String name = etNewMyWalletTiXianName.getText().toString();
        if(name.isEmpty()){
            Toast.makeText(activity,"请输入姓名",Toast.LENGTH_LONG).show();
            return;
        }
        paramMap.put("name",name);
        if(jine.isEmpty()){
            Toast.makeText(activity,"请输入金额,且确保你的账号有钱",Toast.LENGTH_LONG).show();
            return;
        }
        paramMap.put("jine",jine);


        MyWalletNetWork myWalletNetWork = new MyWalletNetWork();
        myWalletNetWork.tiXianFromNet(paramMap, new Observer<NewTiXianBean>() {
            @Override
            public void onCompleted() {
                pbNewMyWalletTiXian.setVisibility(View.GONE);
            }

            @Override
            public void onError(Throwable e) {
                pbNewMyWalletTiXian.setVisibility(View.GONE);
            }

            @Override
            public void onNext(NewTiXianBean newTiXianBean) {
                Toast.makeText(activity,newTiXianBean.getMsg(),Toast.LENGTH_LONG).show();
                if(newTiXianBean.getStatus().equals("0")){
                    Intent intent = new Intent(activity, NewTiXianSuccessActivity.class);
                    activity.startActivity(intent);
                    activity.finish();
                }
                pbNewMyWalletTiXian.setVisibility(View.GONE);
            }
        });
    }


}
