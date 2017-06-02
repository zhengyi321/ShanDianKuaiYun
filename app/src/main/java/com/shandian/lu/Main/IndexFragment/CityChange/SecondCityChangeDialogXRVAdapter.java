package com.shandian.lu.Main.IndexFragment.CityChange;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.ThirdCityChangeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/4/26.
 */

public class SecondCityChangeDialogXRVAdapter extends RecyclerView.Adapter<SecondCityChangeDialogXRVAdapter.ItemViewHolder>  {

    private List<ThirdCityChangeBean.ContentBean.ListBean> stringList;
    private Context context;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;
    private final int CITY_CHANGE_SELECTED = 99;
    private String aid = "";
    String cid = "";
    public SecondCityChangeDialogXRVAdapter(Context context1, List<ThirdCityChangeBean.ContentBean.ListBean>   stringList1, String aid1,String cid1){

        stringList = stringList1;
        context = context1;
        inflater = LayoutInflater.from(context1);
        imageLoader = ImageLoader.getInstance();
        aid = aid1;
        cid = cid1;
    }
    public void setAdapter(List<ThirdCityChangeBean.ContentBean.ListBean>  stringList1){
        stringList.clear();
        stringList.addAll(stringList1);
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflater.inflate(R.layout.dialog_main_index_changecity_xrv_item_xrv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.tvMainIndexCityChangeXRVItemCity.setText(stringList.get(position).getArea());
        System.out.print("\n cid:"+stringList.get(position).getAid());
        holder.pos = position;
      /*      holder.tvMainIndexCityChangeCity.setText(stringList.get(position).getProvince());*/
       /* imageLoader.displayImage(holder.ivMainMineWoDeHuoYuan,stringList.getContent().get(position).getImg1());*/
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        int pos = 0;
        @BindView(R.id.tv_main_index_citychange_xrv_item_city)
        TextView tvMainIndexCityChangeXRVItemCity;
        @BindView(R.id.rly_main_index_citychange_xrv_item_city)
        RelativeLayout rlyMainIndexCityChangeXRVItemCity;
        @OnClick(R.id.rly_main_index_citychange_xrv_item_city)
        public void rlyMainIndexCityChangeXRVItemCityOnclick(){
            String city =stringList.get(pos).getArea();
            Intent intent = new Intent();
            Bundle bundle=new Bundle();
            bundle.putString("city",city);
            bundle.putString("aid",aid);
            bundle.putString("cid",cid);
            bundle.putString("tid",stringList.get(pos).getAid()+"");
            XCCacheManager xcCacheManager = XCCacheManager.getInstance(context);
            XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
            String model = xcCacheManager.readCache(xcCacheSaveName.modlestatus);
            int indexCity = city.indexOf("市");
                    /*Toast.makeText(view1.getContext(),"indexcity:"+indexCity,Toast.LENGTH_LONG).show();*/
            if(indexCity >= 0){
                city = city.substring(0,indexCity);
            }
            int indexCity2 = city.indexOf("全");
            if(indexCity2 >= 0){
                city = city.substring(indexCity2+1,city.length());
            }
            if((model != null)&&(model.equals("index"))) {
                xcCacheManager.writeCache(xcCacheSaveName.currentCity, city);
                xcCacheManager.writeCache(xcCacheSaveName.currentAid, aid);
                xcCacheManager.writeCache(xcCacheSaveName.currentCid, cid);
                xcCacheManager.writeCache(xcCacheSaveName.currentTid, stringList.get(pos).getAid() + "");
            }
            intent.putExtras(bundle);
            ((Activity) context).setResult(CITY_CHANGE_SELECTED, intent);
            ((Activity) context).finish();



        }

        public ItemViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
