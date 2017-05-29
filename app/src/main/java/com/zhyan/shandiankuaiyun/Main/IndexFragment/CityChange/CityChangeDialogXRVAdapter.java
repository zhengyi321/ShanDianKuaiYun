package com.zhyan.shandiankuaiyun.Main.IndexFragment.CityChange;

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
import com.zhyan.shandiankuaiyun.Bean.SecondCityChangeBean;
import com.zhyan.shandiankuaiyun.NetWork.MainIndexNetWork;
import com.zhyan.shandiankuaiyun.R;
import com.zhyan.shandiankuaiyunlib.Bean.ThirdCityChangeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by az on 2017/4/26.
 */

public class CityChangeDialogXRVAdapter extends RecyclerView.Adapter<CityChangeDialogXRVAdapter.ItemViewHolder>  {

    private List<SecondCityChangeBean.ContentBean.ListBean> stringList;
    private Context context;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;
    private final int CITY_CHANGE_SELECTED = 99;
    private String aid = "";
    public CityChangeDialogXRVAdapter(Context context1, List<SecondCityChangeBean.ContentBean.ListBean>  stringList1,String aid1){
        stringList = stringList1;
        context = context1;
        inflater = LayoutInflater.from(context1);
        imageLoader = ImageLoader.getInstance();
        aid = aid1;
    }
    public void setAdapter(List<SecondCityChangeBean.ContentBean.ListBean> stringList1){
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
        SecondCityChangeDialog secondCityChangeDialog;
        @BindView(R.id.tv_main_index_citychange_xrv_item_city)
        TextView tvMainIndexCityChangeXRVItemCity;
        @BindView(R.id.rly_main_index_citychange_xrv_item_city)
        RelativeLayout rlyMainIndexCityChangeXRVItemCity;
        @OnClick(R.id.rly_main_index_citychange_xrv_item_city)
        public void rlyMainIndexCityChangeXRVItemCityOnclick(){
            String cid = stringList.get(pos).getAid();
            MainIndexNetWork mainIndexNetWork = new MainIndexNetWork();
            mainIndexNetWork.getThirdCityFromNet(cid, new Observer<ThirdCityChangeBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(ThirdCityChangeBean thirdCityChangeBean) {
                    if(thirdCityChangeBean.getContent() == null){
                        String city =stringList.get(pos).getArea();
                        Intent intent = new Intent();
                        Bundle bundle = new Bundle();
                        bundle.putString("city", city);
                        bundle.putString("aid", aid);
                        bundle.putString("cid", stringList.get(pos).getAid());
                        intent.putExtras(bundle);

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
                            xcCacheManager.writeCache(xcCacheSaveName.currentCid, stringList.get(pos).getAid());
                            xcCacheManager.writeCache(xcCacheSaveName.currentTid, "");
                        }
 /*           intent.putExtra("city",city);*/
                        ((Activity) context).setResult(CITY_CHANGE_SELECTED, intent);
                        ((Activity) context).finish();
                    }else{
                        secondCityChangeDialog = new SecondCityChangeDialog(context,thirdCityChangeBean.getContent().getList(),aid,stringList.get(pos).getAid()).Build.build(context);
                        showDialog();
                    }
                }
            });


    }
    public void showDialog() {
        if (secondCityChangeDialog != null && !secondCityChangeDialog.isShowing())
            secondCityChangeDialog.show();
    }

    public void dissmissDialog() {
        if (secondCityChangeDialog != null && secondCityChangeDialog.isShowing())
            secondCityChangeDialog.dismiss();
    }

        public ItemViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
