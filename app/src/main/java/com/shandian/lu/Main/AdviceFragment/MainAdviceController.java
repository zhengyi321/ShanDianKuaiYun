package com.shandian.lu.Main.AdviceFragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.shandian.lu.BaseController;
import com.shandian.lu.NetWork.AdviceNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.AdviceInfoListBean;
import com.zhyan.shandiankuaiyunlib.Widget.RecyclerView.XRecycleView.DividerLine;
import com.zhyan.shandiankuaiyunlib.Widget.RecyclerView.XRecycleView.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observer;

/**
 * Created by az on 2017/4/26.
 */

public class MainAdviceController extends BaseController {


    @BindView(R.id.xrv_main_advice)
    XRecyclerView xrvMainAdvice;
    MainAdviceXRVAdapter adviceXRVAdapter;
    public MainAdviceController(View view1){
        view = view1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,view);
        initXRV();
        getAdviceInfoListFromNet();
    }

    private void initXRV(){
        List<AdviceInfoListBean.ContentBean> testList = new ArrayList<>();

        adviceXRVAdapter = new MainAdviceXRVAdapter(view.getContext(),testList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        xrvMainAdvice.setAdapter(adviceXRVAdapter);
        xrvMainAdvice.setLayoutManager(layoutManager);
        //这句就是添加我们自定义的分隔线
        DividerLine dividerLine = new DividerLine(DividerLine.VERTICAL);
        dividerLine.setSize(1);
        dividerLine.setColor(view.getContext().getResources().getColor(R.color.color_main_advice_xrv_item_split_line));
        /*xrvMainAdvice.addItemDecoration(dividerLine);*/
        xrvMainAdvice.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                xrvMainAdvice.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                xrvMainAdvice.loadMoreComplete();
            }
        });

    }

    private void getAdviceInfoListFromNet(){
        AdviceNetWork adviceNetWork = new AdviceNetWork();
        adviceNetWork.getAdviceListFromNet(new Observer<AdviceInfoListBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(AdviceInfoListBean adviceInfoListBean) {
                if((adviceInfoListBean.getStatus() == 0)&&(adviceInfoListBean.getContent() != null)){
                    adviceXRVAdapter.setAdapter(adviceInfoListBean.getContent());
                }
            }
        });
    }

}
