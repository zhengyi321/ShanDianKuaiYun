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

public class BanJiaCityRVAdapter extends RecyclerView.Adapter<BanJiaCityRVAdapter.ItemViewHolder>  {

    private List<StreetListBean.ContentBean.CityBean> stringList;
    private Context context;
    private LayoutInflater inflater;
    TextView textView;
    BanJiaCityAreaRVAdapter banJiaCityAreaRVAdapter;
    private int previsNum = 0;
    private ImageView iv ;
    private PopupWindow popupWindow;
    private int status=0;
    public BanJiaCityRVAdapter(Context context1, List<StreetListBean.ContentBean.CityBean> stringList1, TextView textView1, BanJiaCityAreaRVAdapter banJiaCityAreaRVAdapter1, ImageView iv1, PopupWindow pop1,int status1){
        stringList = stringList1;
        context = context1;
        inflater = LayoutInflater.from(context1);
        textView = textView1;
        banJiaCityAreaRVAdapter = banJiaCityAreaRVAdapter1;
        iv = iv1;
        popupWindow = pop1;
        status = status;
    }
    public void setAdapter(List<StreetListBean.ContentBean.CityBean> contentBeen){
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
        return new ItemViewHolder(inflater.inflate(R.layout.popupwindow_main_index_banjia_city_downdrop_rv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.pos = position;
        holder.tvMainIndexBanJiaCityRVItem.setText(stringList.get(position).getTown());
        holder.llyMainIndexBanJiaCityRVItem.setBackgroundColor(context.getResources().getColor(R.color.white));

    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        int pos = 0;
        @BindView(R.id.tv_main_index_banjia_city_rv_item)
        TextView tvMainIndexBanJiaCityRVItem;

        @BindView(R.id.lly_main_index_banjia_city_rv_item)
        LinearLayout llyMainIndexBanJiaCityRVItem;
        @OnClick(R.id.lly_main_index_banjia_city_rv_item)
        public void llyMainIndexBanJiaCityRVItemOnclick(){
            /*textView.setText(stringList.get(pos).toString());*/

            if((banJiaCityAreaRVAdapter != null)&& (stringList.get(pos).getTrea() != null)){
                banJiaCityAreaRVAdapter.setAdapter(stringList.get(pos).getTrea());
                notifyItemChanged(previsNum);
                previsNum = pos;
                llyMainIndexBanJiaCityRVItem.setBackgroundColor(context.getResources().getColor(R.color.lightgray));

            }else{
                textView.setText(stringList.get(pos).getTown());
                textView.setTextColor(context.getResources().getColor(R.color.color_main_index_banjia_bottom_black_word_textcolor));
                iv.setImageResource(R.mipmap.black_down);
                status = 0;
                popupWindow.dismiss();
            }

        }

        public ItemViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
