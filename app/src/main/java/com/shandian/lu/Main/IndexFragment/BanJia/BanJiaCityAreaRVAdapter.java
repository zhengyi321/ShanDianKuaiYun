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
import com.zhyan.shandiankuaiyunlib.Bean.StreetListBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/4/26.
 */

public class BanJiaCityAreaRVAdapter extends RecyclerView.Adapter<BanJiaCityAreaRVAdapter.ItemViewHolder>  {

    private List<StreetListBean.ContentBean.CityBean.TreaBean> stringList;
    private Context context;
    private LayoutInflater inflater;
    TextView textView;
    PopupWindow popupWindow;

    ImageView iv;
    int status;
    public BanJiaCityAreaRVAdapter(Context context1, List<StreetListBean.ContentBean.CityBean.TreaBean> stringList1, TextView textView1, PopupWindow popupWindow1, ImageView iv1,int status1){
        stringList = stringList1;
        context = context1;
        inflater = LayoutInflater.from(context1);
        textView = textView1;
        popupWindow = popupWindow1;

        iv = iv1;
        status = status1;
    }
    public void setAdapter(List<StreetListBean.ContentBean.CityBean.TreaBean> contentBeen){
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
        return new ItemViewHolder(inflater.inflate(R.layout.popupwindow_main_index_banjia_city_area_downdrop_rv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.pos = position;
        holder.tvMainIndexBanJiaCityAreaRVItem.setText(stringList.get(position).getStreet());
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        int pos = 0;
        @BindView(R.id.tv_main_index_banjia_city_area_rv_item)
        TextView tvMainIndexBanJiaCityAreaRVItem;

        @BindView(R.id.lly_main_index_banjia_city_area_rv_item)
        LinearLayout llyMainIndexBanJiaCityAreaRVItem;
        @OnClick(R.id.lly_main_index_banjia_city_area_rv_item)
        public void llyMainIndexBanJiaCityAreaRVItemOnclick(){
            textView.setText(stringList.get(pos).getStreet().toString());
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
