package com.shandian.lu.Main.ReleaseFragment.FaBuCheYuan;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shandian.lu.BaseController;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.CarTypeDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/5/17.
 */

public class NewFaBuCheYuanV2Controller extends BaseController {
    @BindView(R.id.pb_new_fabucheyuan)
    ProgressBar pbNewFaBuCheYuan;
    @BindView(R.id.rly_new_main_release_fabucheyuan_back)
    RelativeLayout rlyNewMainReleaseFaBuCheWuBack;
    @OnClick(R.id.rly_new_main_release_fabucheyuan_back)
    public void rlyNewMainReleaseFaBuCheWuBackOnclick(){
        activity.finish();
    }

    CarTypeDialog carTypeDialog;
    @BindView(R.id.tv_new_fabucheyuan_cartype)
    TextView tvNewFaBuCheYuanCarType;
    @BindView(R.id.rly_new_fabucheyuan_select_cartype)
    RelativeLayout rlyNewFaBuCheYuanSelectCarType;
    @OnClick(R.id.rly_new_fabucheyuan_select_cartype)
    public void  rlyNewFaBuCheYuanSelectCarTypeOnclick(){
        carTypeDialog = new CarTypeDialog(activity,tvNewFaBuCheYuanCarType).Build.build(activity);
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
    @BindView(R.id.rv_main_release_new_fabucheyuan_add_pic)
    RecyclerView rvMainReleaseNewFaBuHuoYuanAddPic;




    private List<String> tempList ;
    public NewFaBuCheYuanAddPicRVAdapter addPicRVAdapter;
    public NewFaBuCheYuanV2Controller(Activity activity1){
        activity = activity1;
        init();
    }




    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        initRV();
    }
    private void initRV(){
        tempList = new ArrayList<>();
        tempList.add("");

        addPicRVAdapter = new NewFaBuCheYuanAddPicRVAdapter(activity,tempList,pbNewFaBuCheYuan);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvMainReleaseNewFaBuHuoYuanAddPic.setAdapter(addPicRVAdapter);
        rvMainReleaseNewFaBuHuoYuanAddPic.setLayoutManager(linearLayoutManager);

    }
}
