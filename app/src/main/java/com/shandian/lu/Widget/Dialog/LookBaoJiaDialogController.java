package com.shandian.lu.Widget.Dialog;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.example.mynewslayoutlib.Bean.NewBaoJiaListBean;
import com.shandian.lu.BaseController;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Widget.RecyclerView.XRecycleView.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/11.
 */

public class LookBaoJiaDialogController extends BaseController {

    @BindView(R.id.xrv_dialog_new_hyxq_baojia)
    XRecyclerView xrvDialogNewHYXQBaoJia;
    public LookBaoJiaDialogXRVAdapter baoJiaDialogXRVAdapter;
    private List<NewBaoJiaListBean.NrBean.ListBean> dataList;
    public LookBaoJiaDialogController(View view1){
        view = view1;
        init();
    }


    @Override
    protected void init() {
        ButterKnife.bind(this,view);
        initXRV();
    }

    private void initXRV(){
        dataList = new ArrayList<>();
        baoJiaDialogXRVAdapter = new LookBaoJiaDialogXRVAdapter(view.getContext(),dataList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrvDialogNewHYXQBaoJia.setAdapter(baoJiaDialogXRVAdapter);
        xrvDialogNewHYXQBaoJia.setLayoutManager(linearLayoutManager);
    }




}
