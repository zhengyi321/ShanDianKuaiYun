package com.zhyan.shandiankuaiyun.Main.ReleaseFragment.BanJia.CarType;

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
import com.zhyan.shandiankuaiyun.Widget.Dialog.CarLengthDialog;
import com.zhyan.shandiankuaiyunlib.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/4/26.
 */

public class CarTypeRVAdapter extends RecyclerView.Adapter<CarTypeRVAdapter.ItemViewHolder>  {

    private String[] stringList;
    private Activity activity;
    private LayoutInflater inflater;

    private final int CITY_CHANGE_SELECTED = 99;


    public CarTypeRVAdapter(Activity activity1, String[] stringList1 ){
        stringList = stringList1;
        activity = activity1;
        inflater = LayoutInflater.from(activity1);


    }


    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflater.inflate(R.layout.activity_main_release_banjia_leibie_content_rv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.tvMainReleaseBanJiaLeiBieContentRVItem.setText(stringList[position]);
        holder.pos = position;
      /*      holder.tvMainIndexCityChangeCity.setText(stringList.get(position).getProvince());*/
       /* imageLoader.displayImage(holder.ivMainMineWoDeHuoYuan,stringList.getContent().get(position).getImg1());*/
    }

    @Override
    public int getItemCount() {
        return stringList.length;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        int pos = 0;
        private final int LEIBIE = 97;
        @BindView(R.id.tv_main_release_banjia_leibie_content_rv_item)
        TextView tvMainReleaseBanJiaLeiBieContentRVItem;
        @BindView(R.id.rly_main_release_banjia_leibie_content_rv_item)
        RelativeLayout rlyMainReleaseBanJiaLeiBieContentRVItem;
        @OnClick(R.id.rly_main_release_banjia_leibie_content_rv_item)
        public void rlyMainReleaseBanJiaLeiBieContentRVItemOnclick(){
            /*textView.setText(tvMainReleaseCarLength.getText().toString());
            cityChangeDialog.hide();*/
            Bundle bundle = new Bundle();
            String leibie = stringList[pos];
            bundle.putString("leibie",leibie);
            Intent intent = new Intent();
            intent.putExtras(bundle);
            activity.setResult(LEIBIE,intent);
            activity.finish();

          /*  ((Activity) context).finish();*/
        }
        public ItemViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
