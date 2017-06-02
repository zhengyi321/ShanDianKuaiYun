package com.shandian.lu.Main.IndexFragment.CityChange;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.shandian.lu.Bean.SecondCityChangeBean;
import com.shandian.lu.NetWork.MainIndexNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.CityBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by az on 2017/4/26.
 */

public class CountryXRVAdapter extends RecyclerView.Adapter<CountryXRVAdapter.ItemViewHolder>  {

    private List<CityBean.ContentBean.OrdinaryProvinceBean> stringList;
    private Activity activity;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;
    String[] city = {"北京","上海","天津","重庆"};
    private final int CITY_CHANGE_SELECTED = 99;
    public CountryXRVAdapter(Activity activity1, List<CityBean.ContentBean.OrdinaryProvinceBean>  stringList1){
        stringList = stringList1;

        activity = activity1;
        inflater = LayoutInflater.from(activity1);
        imageLoader = ImageLoader.getInstance();
    }
    public void setAdapter(List<CityBean.ContentBean.OrdinaryProvinceBean> stringList1){
        stringList.clear();
        stringList.addAll(stringList1);
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflater.inflate(R.layout.main_index_changecity_xrv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.pos = position;
        holder.tvMainIndexCityChangeCity.setText(stringList.get(position).getProvince());
       /* imageLoader.displayImage(holder.ivMainMineWoDeHuoYuan,stringList.getContent().get(position).getImg1());*/
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        int pos = 0;
        CityChangeDialog cityChangeDialog;
        @BindView(R.id.tv_main_index_citychange_city)
        TextView tvMainIndexCityChangeCity;
        @BindView(R.id.rly_main_index_citychange_city)
        RelativeLayout rlyMainIndexCityChangeCity;
        @OnClick(R.id.rly_main_index_citychange_city)
        public void rlyMainIndexCityChangeCityOnclick(){
            MainIndexNetWork mainIndexNetWork = new MainIndexNetWork();
            String id = stringList.get(pos).getId();
       /*     Toast.makeText(context,"rlyMainIndexCityChangeCityOnclick:",Toast.LENGTH_LONG).show();*/
            mainIndexNetWork.getSecondCityFromNet(id, new Observer<SecondCityChangeBean>() {
                @Override
                public void onCompleted() {
                    /*Toast.makeText(context,"onCompleted:",Toast.LENGTH_LONG).show();*/
                }

                @Override
                public void onError(Throwable e) {
                   /* Toast.makeText(context,"onError:"+e,Toast.LENGTH_LONG).show();*/
                }

                @Override
                public void onNext(SecondCityChangeBean secondCityChangeBean) {
                    /*Toast.makeText(context,"finish:"+secondCityChangeBean.getContent().getList().get(pos).getArea(),Toast.LENGTH_LONG).show();
*/                  XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
                    XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
                    int size = city.length;
                    /*for(int i = 0;i < size;i++){
                        if(stringList.get(pos).getProvince().equals(city[i])){
                            Intent intent = new Intent();
                            Bundle bundle=new Bundle();
                            bundle.putString("city",city[i]);
                            bundle.putString("aid",stringList.get(pos).getId());
                            intent.putExtras(bundle);
                            String model = xcCacheManager.readCache(xcCacheSaveName.modlestatus);
                            if((model != null)||(model.equals("index"))) {
                                xcCacheManager.writeCache(xcCacheSaveName.currentCity, stringList.get(pos).getProvince());
                                xcCacheManager.writeCache(xcCacheSaveName.currentAid, stringList.get(pos).getId());
                                xcCacheManager.writeCache(xcCacheSaveName.currentCid, "");
                                xcCacheManager.writeCache(xcCacheSaveName.currentTid, "");
                            }
                            activity.setResult(CITY_CHANGE_SELECTED, intent);
                            activity.finish();
                            return;
                        }
                    }*/

                    cityChangeDialog = new CityChangeDialog(activity,secondCityChangeBean.getContent().getList(),stringList.get(pos).getId()).Build.build(activity);
                    showDialog();


                }
            });
        }
        public void showDialog() {
            if (cityChangeDialog != null && !cityChangeDialog.isShowing())
                cityChangeDialog.show();
        }

        public void dissmissDialog() {
            if (cityChangeDialog != null && cityChangeDialog.isShowing())
                cityChangeDialog.dismiss();
        }
        public ItemViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
