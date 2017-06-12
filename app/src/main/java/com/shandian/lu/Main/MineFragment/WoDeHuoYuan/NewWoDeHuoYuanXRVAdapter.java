package com.shandian.lu.Main.MineFragment.WoDeHuoYuan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.map.Text;
import com.example.mynewslayoutlib.Bean.NewWoDeHuoYuanBean;
import com.example.mynewslayoutlib.Bean.NewWoDeHuoYuanDeleteBean;
import com.j256.ormlite.stmt.query.In;
import com.shandian.lu.Main.IndexFragment.NewHuoYuanDetail.NewHuoYuanDetailSelfActivity;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.Main.ReleaseFragment.FaBuHuoYuan.NewFaBuHuoYuanActivity;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/6/12.
 */

public class NewWoDeHuoYuanXRVAdapter extends RecyclerView.Adapter<NewWoDeHuoYuanXRVAdapter.MyItemViewHolder> {

    private Activity activity;
    private List<NewWoDeHuoYuanBean.NrBean.ListBean> dataList;
    private LayoutInflater inflater;
    interface DeleteListener{
        public void onDeleteOnclick(boolean isSuccess);
    }
    DeleteListener onDeleteListener;
    public void deleteOnClickCallBack(DeleteListener onDeleteListener1){
        onDeleteListener = onDeleteListener1;
    }
    public NewWoDeHuoYuanXRVAdapter(Activity activity1, List<NewWoDeHuoYuanBean.NrBean.ListBean> dataList1){
        activity = activity1;
        dataList = dataList1;
        inflater = LayoutInflater.from(activity1);
    }
    public void setAdapter(List<NewWoDeHuoYuanBean.NrBean.ListBean> dataList1){
        dataList.clear();
        dataList.addAll(dataList1);
        notifyDataSetChanged();
    }

    @Override
    public MyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyItemViewHolder(inflater.inflate(R.layout.activity_new_wodehuoyuan_content_xrv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(MyItemViewHolder holder, int position) {
        holder.pos = position;
        String isNew = dataList.get(position).getSfck();
        if((isNew == null)||(isNew.isEmpty())){
            isNew = "0";
        }
        if(isNew.equals("0")) {
            holder.tvNewWoDeHuoYuanXRVItemNewBaoJia.setVisibility(View.VISIBLE);
        }else{
            holder.tvNewWoDeHuoYuanXRVItemNewBaoJia.setVisibility(View.GONE);
        }
        holder.tvNewWoDeHuoYuanXRVItemBCity.setText(dataList.get(position).getCfshi());
        holder.tvNewWoDeHuoYuanXRVItemBArea.setText(dataList.get(position).getCfqu());
        holder.tvNewWoDeHuoYuanXRVItemECity.setText(dataList.get(position).getDashi());
        holder.tvNewWoDeHUoYuanXRVITemEArea.setText(dataList.get(position).getDaqu());

        String typeName = dataList.get(position).getType_name();
        if(typeName == null){
            typeName = "";
        }
        switch (typeName){
            case "1":
                holder.tvNewWoDeHuoYuanXRVItemGoodsType.setText("同城货源");
                break;
            case "2":
                holder.tvNewWoDeHuoYuanXRVItemGoodsType.setText("长途货源");
                break;
            case "3":
                holder.tvNewWoDeHuoYuanXRVItemGoodsType.setText("特种货源");
                break;
            case "4":
                holder.tvNewWoDeHuoYuanXRVItemGoodsType.setText("专线货源");
                break;
        }
      holder.tvNewWoDeHuoYuanXRVItemDis.setText(dataList.get(position).getJuli());
        String ddzt = dataList.get(position).getDingdanzt();
        if((ddzt == null)||(ddzt.isEmpty())){
            ddzt = "";
        }
        switch (ddzt){
            case "0":
                    holder.deleteUpdateState(true);
                break;
            case "1":
                holder.deleteUpdateState(true);
                break;
            case "2":
                holder.deleteUpdateState(true);
                break;
            case "3":
                holder.deleteUpdateState(false);
                break;
            case "4":
                holder.deleteUpdateState(false);
                break;
            case "5":
                holder.deleteUpdateState(false);
                break;
        }

        holder.tvNewWoDeHuoYuanXRVItemCount.setText("共"+dataList.get(position).getDdcount()+"条");
        /*if()*/
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyItemViewHolder extends RecyclerView.ViewHolder{
        int pos = 0;

        @BindView(R.id.lly_new_wodehuoyuan_xrv_item)
        LinearLayout llyNewWoDeHuoYuanXRVItem;
        @OnClick(R.id.lly_new_wodehuoyuan_xrv_item)
        public void llyNewWoDeHuoYuanXRVItemOnclick(){
            Intent intent = new Intent(activity, NewHuoYuanDetailSelfActivity.class);
            intent.putExtra("hyid",dataList.get(pos).getId());
            activity.startActivity(intent);
        }

        @BindView(R.id.tv_new_wodehuoyuan_xrv_item_count)
        TextView tvNewWoDeHuoYuanXRVItemCount;
        @BindView(R.id.tv_new_wodehuoyuan_xrv_item_newbaojia)
        TextView tvNewWoDeHuoYuanXRVItemNewBaoJia;
        @BindView(R.id.tv_new_wodehuoyuan_xrv_item_bcity)
        TextView tvNewWoDeHuoYuanXRVItemBCity;
        @BindView(R.id.tv_new_wodehuoyuan_xrv_item_barea)
        TextView tvNewWoDeHuoYuanXRVItemBArea;
        @BindView(R.id.tv_new_wodehuoyuan_xrv_item_ecity)
        TextView tvNewWoDeHuoYuanXRVItemECity;
        @BindView(R.id.tv_new_wodehuoyuan_xrv_item_earea)
        TextView tvNewWoDeHUoYuanXRVITemEArea;
        @BindView(R.id.tv_new_wodehuoyuan_xrv_item_dis)
        TextView tvNewWoDeHuoYuanXRVItemDis;
        @BindView(R.id.tv_new_wodehuoyuan_xrv_item_goodstype)
        TextView tvNewWoDeHuoYuanXRVItemGoodsType;
        @BindView(R.id.lly_new_wodehuoyuan_xrv_item_delete)
        LinearLayout llyNewWoDeHuoYuanXRVItemDelete;
        @OnClick(R.id.lly_new_wodehuoyuan_xrv_item_delete)
        public void llyNewWoDeHuoYuanXRVItemDeleteOnclick(){
            deleteWoDeHuoYuan();
        }
        @BindView(R.id.tv_new_wodehuoyuan_xrv_item_delete)
        TextView tvNewWoDeHuoYuanXRVItemDelete;
        @BindView(R.id.lly_new_wodehuoyuan_xrv_item_update)
        LinearLayout llyNewWoDeHuoYuanXRVItemUpdate;
        @OnClick(R.id.lly_new_wodehuoyuan_xrv_item_update)
        public void llyNewWoDeHuoYuanXRVItemUpdateOnclick(){
            Intent intent = new Intent(activity, NewFaBuHuoYuanActivity.class);
            String typeName = dataList.get(pos).getType_name();
            if(typeName == null){
                typeName = "";
            }
            String id = dataList.get(pos).getId();
            if(typeName == null){
                typeName = "";
            }
            intent.putExtra("type_name",typeName);
            intent.putExtra("id",id);
            activity.startActivity(intent);
        }
        @BindView(R.id.tv_new_wodehuoyuan_xrv_item_update)
        TextView tvNewWoDeHuoYuanXRVItemUpdate;
        public void deleteUpdateState(boolean canUpdateDelete){
            if(canUpdateDelete){
                Drawable drawableDelete= activity.getResources().getDrawable(R.mipmap.delete_black);
                Drawable drawableUpdate= activity.getResources().getDrawable(R.mipmap.update_black);
                tvNewWoDeHuoYuanXRVItemDelete.setCompoundDrawables(drawableDelete,null,null,null);
                tvNewWoDeHuoYuanXRVItemUpdate.setCompoundDrawables(drawableUpdate,null,null,null);
                tvNewWoDeHuoYuanXRVItemDelete.setTextColor(activity.getResources().getColor(R.color.black));
                tvNewWoDeHuoYuanXRVItemUpdate.setTextColor(activity.getResources().getColor(R.color.black));
                llyNewWoDeHuoYuanXRVItemDelete.setClickable(true);
                llyNewWoDeHuoYuanXRVItemUpdate.setClickable(true);
            }else {
                Drawable drawableDelete= activity.getResources().getDrawable(R.mipmap.delete_gray);
                Drawable drawableUpdate= activity.getResources().getDrawable(R.mipmap.update_gray);
                tvNewWoDeHuoYuanXRVItemDelete.setCompoundDrawables(drawableDelete,null,null,null);
                tvNewWoDeHuoYuanXRVItemUpdate.setCompoundDrawables(drawableUpdate,null,null,null);
                tvNewWoDeHuoYuanXRVItemDelete.setTextColor(activity.getResources().getColor(R.color.gray));
                tvNewWoDeHuoYuanXRVItemUpdate.setTextColor(activity.getResources().getColor(R.color.gray));
                llyNewWoDeHuoYuanXRVItemDelete.setClickable(false);
                llyNewWoDeHuoYuanXRVItemUpdate.setClickable(false);
            }
        }

        private void deleteWoDeHuoYuan(){
            XCCacheSaveName xcCacheSaveName= new XCCacheSaveName();
            XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
            String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
            if((loginId == null)||(loginId.isEmpty())){
                Intent intent = new Intent(activity, LoginActivity.class);
                activity.startActivity(intent);
                return;
            }
            String id = dataList.get(pos).getId();
            if(id == null){
                id = "";
            }
            Map<String,String> paramMap = new HashMap<>();
            paramMap.put("login_id",loginId);
            paramMap.put("id",id);
            NewCheHuoListNetWork newCheHuoListNetWork = new NewCheHuoListNetWork();
            newCheHuoListNetWork.deleteWoDeHuoYuanToNet(paramMap, new Observer<NewWoDeHuoYuanDeleteBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(NewWoDeHuoYuanDeleteBean newWoDeHuoYuanDeleteBean) {
                    Toast.makeText(activity,newWoDeHuoYuanDeleteBean.getMsg(),Toast.LENGTH_LONG).show();
                    onDeleteListener.onDeleteOnclick(true);
                }
            });

        }
        public MyItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
