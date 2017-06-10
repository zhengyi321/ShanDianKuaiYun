package com.shandian.lu.Main.IndexFragment.TeZhongWuLiu;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.Main.IndexFragment.NewCheYuanList.CheYuanListActivity;
import com.shandian.lu.Main.IndexFragment.CityChange.CityChangeActivity;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.CarLengthDialog;
import com.shandian.lu.Widget.Dialog.CarTypeDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/4/28.
 */

public class TeZhongWuLiuActivity extends BaseActivity {



    private final int CITY_CHANGE_SELECTED = 99;
    private boolean isBegin = true;
    private String aid,cid,tid,aid1,cid1,tid1;
    CarTypeDialog carTypeDialog;
    CarLengthDialog carLengthDialog;
    @BindView(R.id.tv_main_index_tezhongwuliu_content_begin)
    TextView tvMainIndexTeZhongWuLiuContentBegin;
    @BindView(R.id.rly_main_index_tezhongwuliu_content_begin)
    RelativeLayout rlyMainIndexTeZhongWuLiuContentBegin;
    @OnClick(R.id.rly_main_index_tezhongwuliu_content_begin)
    public void rlyMainIndexTeZhongWuLiuContentBeginOnclick(){
        isBegin = true;
        Intent intent = new Intent(TeZhongWuLiuActivity.this, CityChangeActivity.class);
        startActivityForResult(intent,CITY_CHANGE_SELECTED);
    }
    @BindView(R.id.tv_main_index_tezhongwuliu_content_reach)
    TextView tvMainIndexTeZhongWuLiuContentReach;
    @BindView(R.id.rly_main_index_tezhongwuliu_content_reach)
    RelativeLayout rlyMainIndexTeZhongWuLiuContentReach;
    @OnClick(R.id.rly_main_index_tezhongwuliu_content_reach)
    public void rlyMainIndexTeZhongWuLiuContentReachOnclick(){
        isBegin = false;
        Intent intent = new Intent(TeZhongWuLiuActivity.this, CityChangeActivity.class);
        startActivityForResult(intent,CITY_CHANGE_SELECTED);
    }

    @BindView(R.id.rly_main_idnex_tezhongwuliu_change)
    RelativeLayout rlyMainIndexTeZhongWuLiuChange;
    @OnClick(R.id.rly_main_idnex_tezhongwuliu_change)
    public void rlyMainIndexTeZhongWuLiuChangeOnclick(){
        Intent intent = new Intent(this, CheYuanListActivity.class);
        intent.putExtra("typeName","3");
        startActivity(intent);
        finish();
    }



    private TeZhongWuLiuController teZhongWuLiuController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_index_tezhongwuliu_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        teZhongWuLiuController = new TeZhongWuLiuController(this);
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
                        tvMainIndexTeZhongWuLiuContentBegin.setText(city);
                    }else {
                        aid1 = id1;
                        cid1 = id2;
                        tid1 = id3;
                        tvMainIndexTeZhongWuLiuContentReach.setText(city);
                    }
                }
                teZhongWuLiuController.getDataFromNet(false);
                break;
        }
    }
    @Override
    protected void onResume(){
        super.onResume();
        teZhongWuLiuController.getDataFromNet(false);
    }
}
