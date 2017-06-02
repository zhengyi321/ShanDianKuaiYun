package com.shandian.lu.Main.IndexFragment.BanJia;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.shandian.lu.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/4/26.
 */

public class BanJiaLeiXingRVAdapter extends RecyclerView.Adapter<BanJiaLeiXingRVAdapter.ItemViewHolder>  {

    private List<String> stringList;
    private Context context;
    private LayoutInflater inflater;
    TextView textView;

    ImageView iv;
    PopupWindow popupWindow;
    int status = 0;
    public BanJiaLeiXingRVAdapter(Context context1, List<String> stringList1, TextView textView1, PopupWindow popupWindow1,  ImageView iv1,int status1){
        stringList = stringList1;
        context = context1;
        inflater = LayoutInflater.from(context1);
        textView = textView1;
        popupWindow = popupWindow1;
        iv = iv1;
        status = status1;
    }
    public void setAdapter(List<String> contentBeen){
        stringList.clear();
        stringList.addAll(contentBeen);
        notifyDataSetChanged();
    }
    public void clean(){
        stringList.clear();
        notifyDataSetChanged();
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflater.inflate(R.layout.popupwindow_main_index_banjia_leixing_downdrop_rv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.pos = position;
        holder.tvMainIndexBanJiaLeiXingRVItem.setText(stringList.get(position).toString());
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        int pos = 0;
        @BindView(R.id.tv_main_index_banjia_leixing_rv_item)
        TextView tvMainIndexBanJiaLeiXingRVItem;

        @BindView(R.id.lly_main_index_banjia_leixing_rv_item)
        LinearLayout llyMainIndexBanJiaLeiXingRVItem;
        @OnClick(R.id.lly_main_index_banjia_leixing_rv_item)
        public void llyMainIndexBanJiaLeiXingRVItemOnclick(){
            textView.setText(stringList.get(pos).toString());
            textView.setTextColor(context.getResources().getColor(R.color.color_main_index_banjia_bottom_black_word_textcolor));
            iv.setImageResource(R.mipmap.black_down);
            status = 0;
            popupWindow.dismiss();
        }

        public ItemViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
