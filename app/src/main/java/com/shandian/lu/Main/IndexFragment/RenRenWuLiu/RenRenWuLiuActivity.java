package com.shandian.lu.Main.IndexFragment.RenRenWuLiu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.Main.IndexFragment.CityChange.CityChangeActivity;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.CarLengthDialog;
import com.shandian.lu.Widget.Dialog.CarTypeDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/4/27.
 */

public class RenRenWuLiuActivity extends BaseActivity {


    private final int CITY_CHANGE_SELECTED = 99;
    private boolean isBegin = true;
    private String aid,cid,tid,aid1,cid1,tid1;
    CarTypeDialog carTypeDialog;
    CarLengthDialog carLengthDialog;
    @BindView(R.id.tv_main_index_renrenwuliu_content_begin)
    TextView tvMainIndexRenRenWuLiuContentBegin;
    @BindView(R.id.rly_main_index_renrenwuliu_content_begin)
    RelativeLayout rlyMainIndexRenRenWuLiuContentBegin;
    @OnClick(R.id.rly_main_index_renrenwuliu_content_begin)
    public void rlyMainIndexRenRenWuLiuContentBeginOnclick(){
        isBegin = true;
        Intent intent = new Intent(RenRenWuLiuActivity.this, CityChangeActivity.class);
        startActivityForResult(intent,CITY_CHANGE_SELECTED);
    }
    @BindView(R.id.tv_main_index_renrenwuliu_content_reach)
    TextView tvMainIndexRenRenWuLiuContentReach;
    @BindView(R.id.rly_main_index_renrenwuliu_content_reach)
    RelativeLayout rlyMainIndexRenRenWuLiuContentReach;
    @OnClick(R.id.rly_main_index_renrenwuliu_content_reach)
    public void rlyMainIndexRenRenWuLiuContentReachOnclick(){
        isBegin = false;
        Intent intent = new Intent(RenRenWuLiuActivity.this, CityChangeActivity.class);
        startActivityForResult(intent,CITY_CHANGE_SELECTED);
    }


    private RenRenWuLiuController renRenWuLiuController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_index_renrenwuliu_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }
    private void initController(){
        renRenWuLiuController = new RenRenWuLiuController(this);
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
                        tvMainIndexRenRenWuLiuContentBegin.setText(city);
                    }else {
                        aid1 = id1;
                        cid1 = id2;
                        tid1 = id3;
                        tvMainIndexRenRenWuLiuContentReach.setText(city);
                    }
                }
                renRenWuLiuController.getDataFromNet(false);
                break;
        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        renRenWuLiuController.getDataFromNet(false);
    }
}
