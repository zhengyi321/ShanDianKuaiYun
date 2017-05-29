package com.zhyan.shandiankuaiyun.Main.MineFragment.MengYou;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhyan.shandiankuaiyun.R;

import java.util.List;

/**
 * Created by az on 2017/4/26.
 */

public class MengYouXRVAdapter extends RecyclerView.Adapter<MengYouXRVAdapter.ItemViewHolder>  {

    private List<String> stringList;
    private Context context;
    private LayoutInflater inflater;
    public MengYouXRVAdapter(Context context1, List<String> stringList1){
        stringList = stringList1;
        context = context1;
        inflater = LayoutInflater.from(context1);
    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflater.inflate(R.layout.activity_main_mine_mengyou_xrv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        public ItemViewHolder(View view){
            super(view);
        }
    }
}
