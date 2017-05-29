package com.zhyan.shandiankuaiyun.Main.IndexFragment.PeiHuoZhongXin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhyan.shandiankuaiyuanwidgetlib.Utils.BitmapUtils;
import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.Main.IndexFragment.CityChange.CityChangeActivity;
import com.zhyan.shandiankuaiyun.Main.ReleaseFragment.FaBuHuoYuan.FaBuHuoYuanActivity;
import com.zhyan.shandiankuaiyun.R;
import com.zhyan.shandiankuaiyun.Widget.Dialog.CarLengthDialog;
import com.zhyan.shandiankuaiyun.Widget.Dialog.CarTypeDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/4/28.
 */

public class PeiHuoZhongXinActivity extends BaseActivity {
    private final int CITY_CHANGE_SELECTED = 99;
    private boolean isBegin = true;
    private String aid,cid,tid,aid1,cid1,tid1;
    CarTypeDialog carTypeDialog;
    CarLengthDialog carLengthDialog;
    @BindView(R.id.tv_main_index_peihuozhongxin_content_begin)
    TextView tvMainIndexPeiHuoZhongXinContentBegin;
    @BindView(R.id.rly_main_index_peihuozhongxin_content_begin)
    RelativeLayout rlyMainIndexPeiHuoZhongXinContentBegin;
    @OnClick(R.id.rly_main_index_peihuozhongxin_content_begin)
    public void rlyMainIndexPeiHuoZhongXinContentBeginOnclick(){
        isBegin = true;
        Intent intent = new Intent(PeiHuoZhongXinActivity.this, CityChangeActivity.class);
        startActivityForResult(intent,CITY_CHANGE_SELECTED);
    }
    @BindView(R.id.tv_main_index_peihuozhongxin_content_reach)
    TextView tvMainIndexPeiHuoZhongXinContentReach;
    @BindView(R.id.rly_main_index_peihuozhongxin_content_reach)
    RelativeLayout rlyMainIndexPeiHuoZhongXinContentReach;
    @OnClick(R.id.rly_main_index_peihuozhongxin_content_reach)
    public void rlyMainIndexPeiHuoZhongXinContentReachOnclick(){
        isBegin = false;
        Intent intent = new Intent(PeiHuoZhongXinActivity.this, CityChangeActivity.class);
        startActivityForResult(intent,CITY_CHANGE_SELECTED);
    }



    private PeiHuoZhongXinController controller;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_index_peihuozhongxin_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        controller = new PeiHuoZhongXinController(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (resultCode) {
            case CITY_CHANGE_SELECTED:
                Bundle b=data.getExtras(); //data为B中回传的Intent
                String city=b.getString("city");//str即为回传的值
                String id1 = b.getString("aid");
                String id2 = b.getString("cid");
                String id3 = b.getString("tid");

                /*Toast.makeText(FaBuHuoYuanActivity.this,"city:"+city,Toast.LENGTH_LONG).show();*/
                if(city != null){
                    if(isBegin) {
                        aid = id1;
                        cid = id2;
                        tid = id3;
                        tvMainIndexPeiHuoZhongXinContentBegin.setText(city);
                    }else {
                        aid1 = id1;
                        cid1 = id2;
                        tid1 = id3;
                        tvMainIndexPeiHuoZhongXinContentReach.setText(city);
                    }
                }
                controller.getDataFromNet(false);
                break;
        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        controller.getDataFromNet(false);
    }
}
