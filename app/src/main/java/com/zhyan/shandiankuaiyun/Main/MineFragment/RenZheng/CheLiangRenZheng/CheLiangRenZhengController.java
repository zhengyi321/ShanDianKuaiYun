package com.zhyan.shandiankuaiyun.Main.MineFragment.RenZheng.CheLiangRenZheng;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yanzhenjie.album.Album;
import com.zhyan.shandiankuaiyun.BaseController;
import com.zhyan.shandiankuaiyun.R;
import com.zhyan.shandiankuaiyun.Widget.Dialog.CarLengthDialog;
import com.zhyan.shandiankuaiyun.Widget.Dialog.CarTypeDialog;
import com.zhyan.shandiankuaiyun.Widget.Dialog.ShengFenDaiHaoDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/5/19.
 */

public class CheLiangRenZhengController extends BaseController{

    CarTypeDialog carTypeDialog;
    CarLengthDialog carLengthDialog;
    ShengFenDaiHaoDialog shengFenDaiHaoDialog;
    @BindView(R.id.rly_main_mine_renzheng_cheliangrenzheng_back)
    RelativeLayout rlyMainMineRenZhengCheLiangRenZhengBack;
    @OnClick(R.id.rly_main_mine_renzheng_cheliangrenzheng_back)
    public void rlyMainMineRenZhengCheLiangRenZhengBackOnclick(){
        activity.finish();
    }


    @BindView(R.id.et_main_mine_renzheng_cheliangrenzheng_name)
    EditText etMainMineRenZhengCheLiangRenZhengName;
    @BindView(R.id.et_main_mine_renzheng_cheliangrenzheng_shenfenzheng)
    EditText etMainMineRenZhengCheLiangRenZhengShenFenZheng;




    @BindView(R.id.et_main_mine_renzheng_cheliangrenzheng_cph)
    EditText etMainMineRenZhengCheLiangRenZhengCPH;

    @BindView(R.id.et_main_mine_renzheng_cheliangrenzheng_zaizhong)
    EditText etMainMineRenZhengCheLiangRenZhengZaiZhong;

    @BindView(R.id.tv_main_mine_renzheng_cheliangrenzheng_cartype)
    TextView tvMainMineRenZhengCheLiangRenZhengCarType;
    @BindView(R.id.rly_main_mine_renzheng_cheliangrenzheng_cartype)
    RelativeLayout rlyMainMineRenZhengCheLiangRenZhengCarType;
    @OnClick(R.id.rly_main_mine_renzheng_cheliangrenzheng_cartype)
    public void rlyMainMineRenZhengCheLiangRenZhengCarTypeOnclick(){
        carTypeDialog = new CarTypeDialog(activity,tvMainMineRenZhengCheLiangRenZhengCarType).Build.build(activity);
        showDialog();
    }
    public void showDialog() {
        if (carTypeDialog != null && !carTypeDialog.isShowing())
            carTypeDialog.show();
    }

    public void dissmissDialog() {
        if (carTypeDialog != null && carTypeDialog.isShowing())
            carTypeDialog.dismiss();
    }

    @BindView(R.id.tv_main_mine_renzheng_cheliangrenzheng_carlength)
    TextView tvMainMineRenZhengCheLiangRenZhengCarLength;
    @BindView(R.id.rly_main_mine_renzheng_cheliangrenzheng_carlength)
    RelativeLayout rlyMainMineRenZhengCheLiangRenZhengCarLength;
    @OnClick(R.id.rly_main_mine_renzheng_cheliangrenzheng_carlength)
    public void rlyMainMineRenZhengCheLiangRenZhengCarLengthOnclick(){
        carLengthDialog = new CarLengthDialog(activity,tvMainMineRenZhengCheLiangRenZhengCarLength).Build.build(activity);
        showDialog2();
    }
    public void showDialog2() {
        if (carLengthDialog != null && !carLengthDialog.isShowing())
            carLengthDialog.show();
    }

    public void dissmissDialog2() {
        if (carLengthDialog != null && carLengthDialog.isShowing())
            carLengthDialog.dismiss();
    }
    @BindView(R.id.tv_main_mine_renzheng_cheliangrenzheng_shengfendaihao)
    TextView tvMainMineRenZhengCheLiangRenZhengShengFenDaiHao;
    @BindView(R.id.rly_main_mine_renzheng_cheliangrenzheng_shengfendaihao)
    RelativeLayout rlyMainMineRenZhengCheLiangRenZhengShengFenDaiHao;
    @OnClick(R.id.rly_main_mine_renzheng_cheliangrenzheng_shengfendaihao)
    public void rlyMainMineRenZhengCheLiangRenZhengShengFenDaiHaoOnclick(){
        shengFenDaiHaoDialog = new ShengFenDaiHaoDialog(activity,tvMainMineRenZhengCheLiangRenZhengShengFenDaiHao).Build.build(activity);
        showDialog3();
    }
    public void showDialog3() {
        if (shengFenDaiHaoDialog != null && !shengFenDaiHaoDialog.isShowing())
            shengFenDaiHaoDialog.show();
    }

    public void dissmissDialog3() {
        if (shengFenDaiHaoDialog != null && shengFenDaiHaoDialog.isShowing())
            shengFenDaiHaoDialog.dismiss();
    }



    public CheLiangRenZhengController(Activity activity1){
        activity = activity1;
        init();
    }


    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }




}
