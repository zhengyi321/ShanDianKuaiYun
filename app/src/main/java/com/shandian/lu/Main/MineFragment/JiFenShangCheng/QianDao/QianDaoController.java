package com.shandian.lu.Main.MineFragment.JiFenShangCheng.QianDao;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.ResetPassBean;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.NetWork.JiFenShangChengNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyunlib.Bean.QianDaoInitBean;
import com.zhyan.shandiankuaiyunlib.Bean.QianDaoSubmitResultBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/6/20.
 */

public class QianDaoController extends BaseController {


    private List<String> signDates=new ArrayList<String>();
    ArrayList<Integer> days=new ArrayList<Integer>();

    @BindView(R.id.rly_main_mine_jfsc_qiandao_back)
    RelativeLayout rlyMainMineJFSCQianDaoBack;
    @OnClick(R.id.rly_main_mine_jfsc_qiandao_back)
    public void rlyMainMineJFSCQianDaoBackOnclick(){
        activity.finish();
    }

    @BindView(R.id.iv_image_qiandao)
    ImageView ivImageQianDao;

    @OnClick(R.id.iv_image_qiandao)
    public void ivImageQianDaoOnclick(){
        submitQianDaoToNet();
    }

    @BindView(R.id.tv_qiandao_total)
    TextView tvQianDaoTotal;
    @BindView(R.id.tvalwaysSign)
    TextView tvAlawaysSign;
    public QianDaoController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        getDataFromNet();
    }


    private void getDataFromNet(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            return;
        }
        JiFenShangChengNetWork jiFenShangChengNetWork = new JiFenShangChengNetWork();
        jiFenShangChengNetWork.qianDaoInitFromNet(loginId, new Observer<QianDaoInitBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(QianDaoInitBean qianDaoInitBean) {
                tvQianDaoTotal.setText(qianDaoInitBean.getZjifen());
                tvAlawaysSign.setText(qianDaoInitBean.getLianxu());
                signDates = qianDaoInitBean.getNr();
                sendBooadcase();
            }
        });
    }


    protected void sendBooadcase() {
        for(int i=0;i<signDates.size();i++){
            int sum=Integer.parseInt(signDates.get(i).substring(signDates.get(i).length()-2, signDates.get(i).length()));
            days.add(sum);
        }
        //获取以前的签到的那些天；
        Intent intent=new Intent("sign");
        intent.putIntegerArrayListExtra("days", days);
        activity.sendBroadcast(intent);

    }


    private void submitQianDaoToNet(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            return;
        }
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("sign","1");
        JiFenShangChengNetWork jiFenShangChengNetWork = new JiFenShangChengNetWork();
        jiFenShangChengNetWork.qianDaoToNet(loginId, paramMap, new Observer<QianDaoSubmitResultBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(QianDaoSubmitResultBean qianDaoSubmitResultBean) {
                alertDialog(qianDaoSubmitResultBean.getMsg());
                Intent intent=new Intent("sign");
                intent.putExtra("sing","s");
                activity.sendBroadcast(intent);
            }
        });
    }


    public void alertDialog(String text){
        final Dialog dialog=new AlertDialog.Builder(activity,R.style.dialog).show();
        //自定义UI
        final Window window=dialog.getWindow();
        window.setContentView(R.layout.dialog_renzheng);
        ((TextView) window.findViewById(R.id.textView2)).setText(text);
        ((TextView) window.findViewById(R.id.textView1)).setText("恭喜");
        ((Button) window.findViewById(R.id.button2)).setVisibility(View.GONE);
        ((Button) window.findViewById(R.id.button1)).setText("确定");
        ((TextView) window.findViewById(R.id.textView4)).setVisibility(View.INVISIBLE);
        window.findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }
}
