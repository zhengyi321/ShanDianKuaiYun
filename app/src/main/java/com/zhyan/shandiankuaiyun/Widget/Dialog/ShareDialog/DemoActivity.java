package com.zhyan.shandiankuaiyun.Widget.Dialog.ShareDialog;

import android.app.Activity;
import android.app.Dialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zhyan.shandiankuaiyuanwidgetlib.ShareDialog.PageIndicatorView;
import com.zhyan.shandiankuaiyuanwidgetlib.ShareDialog.PageRecyclerView;
import com.zhyan.shandiankuaiyun.Main.MainActivity;
import com.zhyan.shandiankuaiyun.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by az on 2017/5/27.
 */

public class DemoActivity {

    Activity activity;
    private Dialog shareDialog;
    PageRecyclerView mRecyclerView;
    //分享的平台集合
    private List<String> dataList = null;
    //分享的分页PageRecyclerView的Adapter
    private PageRecyclerView.PageAdapter myAdapter = null;
    public DemoActivity(Activity activity1){
        activity = activity1;
        init();
    }


    private void init(){
        shareDialog = new Dialog(activity, R.style.dialog);
        View contentView = LayoutInflater.from(activity).inflate(R.layout.pop_share, null);
        shareDialog.setContentView(contentView);
        shareDialog.setCanceledOnTouchOutside(true);
        mRecyclerView = (PageRecyclerView) contentView.findViewById(R.id.myRecyclerView);
        // 设置指示器
        shareDialog.show();
        mRecyclerView.setIndicator((PageIndicatorView) contentView.findViewById(R.id.indicator));
        // 设置行数和列数
        mRecyclerView.setPageSize(3, 3);
        // 设置页间距
        mRecyclerView.setPageMargin(30);
        initData();
        // 设置数据
        mRecyclerView.setAdapter(myAdapter = mRecyclerView.new PageAdapter(dataList, new PageRecyclerView.CallBack() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(activity).inflate(R.layout.item, parent, false);
                return new MyHolder(view);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                ((MyHolder) holder).tv.setText(dataList.get(position));
            }

/*            @Override
            public void onItemClickListener(View view, int position) {
                Toast.makeText(activity, "点击："
                        + dataList.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClickListener(View view, int position) {
                Toast.makeText(activity, "删除："
                        + dataList.get(position), Toast.LENGTH_SHORT).show();
                myAdapter.remove(position);
            }*/
        }));

    }

    private void initData() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 47; i++) {
            dataList.add(String.valueOf(i));
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public TextView tv = null;

        public MyHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.text);
        }
    }

}
