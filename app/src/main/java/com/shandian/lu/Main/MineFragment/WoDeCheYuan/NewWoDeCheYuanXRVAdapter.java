package com.shandian.lu.Main.MineFragment.WoDeCheYuan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewWoDeCheYuanBean;
import com.example.mynewslayoutlib.Bean.NewWoDeCheYuanDeleteBean;
import com.example.mynewslayoutlib.Bean.NewWoDeHuoYuanBean;
import com.example.mynewslayoutlib.Bean.NewWoDeHuoYuanDeleteBean;
import com.shandian.lu.Main.IndexFragment.NewCheYuanDetail.NewCheYuanDetaiSelflActivity;
import com.shandian.lu.Main.IndexFragment.NewHuoYuanDetail.NewHuoYuanDetailSelfActivity;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.Main.MineFragment.PaySubmit.TwoStepPaySubmitActivity;
import com.shandian.lu.Main.ReleaseFragment.FaBuCheYuan.NewFaBuCheYuanActivity;
import com.shandian.lu.Main.ReleaseFragment.FaBuHuoYuan.NewFaBuHuoYuanActivity;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;

import java.util.ArrayList;
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

public class NewWoDeCheYuanXRVAdapter extends RecyclerView.Adapter<NewWoDeCheYuanXRVAdapter.MyItemViewHolder> {

    private Activity activity;
    private List<NewWoDeCheYuanBean.NrBean.ListBean> dataList;
    private LayoutInflater inflater;
    interface DeleteListener{
        public void onDeleteOnclick(boolean isSuccess);
    }
    DeleteListener onDeleteListener;
    public void deleteOnClickCallBack(DeleteListener onDeleteListener1){
        onDeleteListener = onDeleteListener1;
    }
    public NewWoDeCheYuanXRVAdapter(Activity activity1, List<NewWoDeCheYuanBean.NrBean.ListBean> dataList1){
        activity = activity1;
        dataList = dataList1;
        inflater = LayoutInflater.from(activity1);
    }
    public void setAdapter(List<NewWoDeCheYuanBean.NrBean.ListBean> dataList1){
        dataList.clear();
        dataList.addAll(dataList1);
        notifyDataSetChanged();
    }

    @Override
    public MyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyItemViewHolder(inflater.inflate(R.layout.activity_new_wodecheyuan_content_xrv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(MyItemViewHolder holder, int position) {
        holder.pos = position;
        holder.tvNewWoDeCheYuanXRVItemNewBaoJia.setVisibility(View.GONE);
    /*    String isNew = dataList.get(position).getSfck();*/
       /* if((isNew == null)||(isNew.isEmpty())){
            isNew = "0";
        }
        if(isNew.equals("0")) {
            holder.tvNewWoDeCheYuanXRVItemNewBaoJia.setVisibility(View.VISIBLE);
        }else{
            holder.tvNewWoDeCheYuanXRVItemNewBaoJia.setVisibility(View.GONE);
        }*/
        holder.tvNewWoDeCheYuanXRVItemBCity.setText(dataList.get(position).getCfshi());
        holder.tvNewWoDeCheYuanXRVItemBArea.setText(dataList.get(position).getCfqu());
        holder.tvNewWoDeCheYuanXRVItemECity.setText(dataList.get(position).getDashi());
        holder.tvNewWoDeCheYuanXRVITemEArea.setText(dataList.get(position).getDaqu());

        String typeName = dataList.get(position).getType_name();
        if(typeName == null){
            typeName = "";
        }
        switch (typeName){
            case "1":
                holder.tvNewWoDeCheYuanXRVItemGoodsType.setText("同城物流");
                break;
            case "2":
                holder.tvNewWoDeCheYuanXRVItemGoodsType.setText("长途物流");
                break;
            case "3":
                holder.tvNewWoDeCheYuanXRVItemGoodsType.setText("特种物流");
                break;
            case "4":
                holder.tvNewWoDeCheYuanXRVItemGoodsType.setText("专线物流");
                break;
        }
        holder.tvNewWoDeCheYuanXRVItemDis.setText(dataList.get(position).getJuli());
        holder.tvNewWoDeCheYuanXRVItemCount.setText("共"+dataList.get(position).getNum()+"条");
        String zt = dataList.get(position).getZt();
        if((zt == null)||(zt.isEmpty())){
            zt = "";
        }
       /* Toast.makeText(activity,"ddzt:"+zt,Toast.LENGTH_LONG).show();*/
        switch (zt){
            case "-1":
                    holder.deleteUpdateState(true);
                holder.tvNewWoDeCheYuanXRVItemCount.setText("报价失败");
                break;
            case "0":
                    holder.deleteUpdateState(true);

                break;
            case "1":
                holder.deleteUpdateState(true);
                holder.tvNewWoDeCheYuanXRVItemCount.setText("待货主\n支付定金");
                break;
            case "2":
                holder.deleteUpdateState(true);
                holder.tvNewWoDeCheYuanXRVItemCount.setText("拉货");
                break;
            case "3":
                holder.deleteUpdateState(false);
                holder.tvNewWoDeCheYuanXRVItemCount.setText("运输中");
                break;
            case "4":
                holder.deleteUpdateState(false);
                holder.tvNewWoDeCheYuanXRVItemCount.setText("待货主\n支付尾款");
                break;
            case "5":
                holder.deleteUpdateState(false);
                holder.tvNewWoDeCheYuanXRVItemCount.setText("交易成功");
                break;
        }


        /*if()*/
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyItemViewHolder extends RecyclerView.ViewHolder{
        int pos = 0;

        @BindView(R.id.lly_new_wodecheyuan_xrv_item)
        LinearLayout llyNewWoDeCheYuanXRVItem;
        @OnClick(R.id.lly_new_wodecheyuan_xrv_item)
        public void llyNewWoDeCheYuanXRVItemOnclick(){
            Intent intent = new Intent(activity, NewHuoYuanDetailSelfActivity.class);
            intent.putExtra("hyid",dataList.get(pos).getId());
            activity.startActivity(intent);
        }

        @BindView(R.id.tv_new_wodecheyuan_xrv_item_count)
        TextView tvNewWoDeCheYuanXRVItemCount;
        @BindView(R.id.rly_new_wodecheyuan_xrv_item_count)
        RelativeLayout rlyNewWoDeCheYuanXRVItemCount;
        @OnClick(R.id.rly_new_wodecheyuan_xrv_item_count)
        public void rlyNewWoDeCheYuanXRVItemCountOnclick(){

            Intent intent = new Intent(activity, NewCheYuanDetaiSelflActivity.class);
            intent.putExtra("cyid",dataList.get(pos).getId());
            activity.startActivity(intent);
            String zt = dataList.get(pos).getZt();
            if((zt == null)||(zt.isEmpty())){
                zt = "";
            }
            Toast.makeText(activity,"ddzt:"+zt,Toast.LENGTH_LONG).show();
            switch (zt){
                case "-1":

                    break;
                case "0":

                    break;
                case "1":

                    break;
                case "2":

                    break;
                case "3":

                    break;
                case "4":

                    break;
                case "5":

                    break;
            }
        }

        @BindView(R.id.tv_new_wodecheyuan_xrv_item_newbaojia)
        TextView tvNewWoDeCheYuanXRVItemNewBaoJia;
        @BindView(R.id.tv_new_wodecheyuan_xrv_item_bcity)
        TextView tvNewWoDeCheYuanXRVItemBCity;
        @BindView(R.id.tv_new_wodecheyuan_xrv_item_barea)
        TextView tvNewWoDeCheYuanXRVItemBArea;
        @BindView(R.id.tv_new_wodecheyuan_xrv_item_ecity)
        TextView tvNewWoDeCheYuanXRVItemECity;
        @BindView(R.id.tv_new_wodecheyuan_xrv_item_earea)
        TextView tvNewWoDeCheYuanXRVITemEArea;
        @BindView(R.id.tv_new_wodecheyuan_xrv_item_dis)
        TextView tvNewWoDeCheYuanXRVItemDis;
        @BindView(R.id.tv_new_wodecheyuan_xrv_item_goodstype)
        TextView tvNewWoDeCheYuanXRVItemGoodsType;
        @BindView(R.id.lly_new_wodecheyuan_xrv_item_delete)
        LinearLayout llyNewWoDeCheYuanXRVItemDelete;
        @OnClick(R.id.lly_new_wodecheyuan_xrv_item_delete)
        public void llyNewWoDeCheYuanXRVItemDeleteOnclick(){
            deleteWoDeCheYuan();
        }
        @BindView(R.id.tv_new_wodecheyuan_xrv_item_delete)
        TextView tvNewWoDeCheYuanXRVItemDelete;
        @BindView(R.id.lly_new_wodecheyuan_xrv_item_update)
        LinearLayout llyNewWoDeCheYuanXRVItemUpdate;
        @OnClick(R.id.lly_new_wodecheyuan_xrv_item_update)
        public void llyNewWoDeCheYuanXRVItemUpdateOnclick(){
            Intent intent = new Intent(activity, NewFaBuCheYuanActivity.class);
            String typeName = dataList.get(pos).getType_name();
            if(typeName == null){
                typeName = "";
            }
            String id = dataList.get(pos).getId();
            if(typeName == null){
                typeName = "";
            }
            intent.putExtra("type_name",typeName);
            ArrayList imgList = (ArrayList<String>)dataList.get(pos).getImgtu();

            if(imgList == null){
                imgList = new ArrayList<>();
            }
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("imgTu",imgList);
            intent.putExtras(bundle);
            intent.putExtra("id",id);
            activity.startActivity(intent);
        }
        @BindView(R.id.tv_new_wodecheyuan_xrv_item_update)
        TextView tvNewWoDeCheYuanXRVItemUpdate;
        public void deleteUpdateState(boolean canUpdateDelete){
            if(canUpdateDelete){
                Drawable drawableDelete= activity.getResources().getDrawable(R.mipmap.delete_black);
                Drawable drawableUpdate= activity.getResources().getDrawable(R.mipmap.update_black);
                tvNewWoDeCheYuanXRVItemDelete.setCompoundDrawables(drawableDelete,null,null,null);
                tvNewWoDeCheYuanXRVItemUpdate.setCompoundDrawables(drawableUpdate,null,null,null);
                tvNewWoDeCheYuanXRVItemDelete.setTextColor(activity.getResources().getColor(R.color.black));
                tvNewWoDeCheYuanXRVItemUpdate.setTextColor(activity.getResources().getColor(R.color.black));
                llyNewWoDeCheYuanXRVItemDelete.setClickable(true);
                llyNewWoDeCheYuanXRVItemUpdate.setClickable(true);
            }else {
                Drawable drawableDelete= activity.getResources().getDrawable(R.mipmap.delete_gray);
                Drawable drawableUpdate= activity.getResources().getDrawable(R.mipmap.update_gray);
                tvNewWoDeCheYuanXRVItemDelete.setCompoundDrawables(drawableDelete,null,null,null);
                tvNewWoDeCheYuanXRVItemUpdate.setCompoundDrawables(drawableUpdate,null,null,null);
                tvNewWoDeCheYuanXRVItemDelete.setTextColor(activity.getResources().getColor(R.color.gray));
                tvNewWoDeCheYuanXRVItemUpdate.setTextColor(activity.getResources().getColor(R.color.gray));
                llyNewWoDeCheYuanXRVItemDelete.setClickable(false);
                llyNewWoDeCheYuanXRVItemUpdate.setClickable(false);
            }
        }

        private void deleteWoDeCheYuan(){
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
            newCheHuoListNetWork.deleteWoDeCheYuanToNet(paramMap, new Observer<NewWoDeCheYuanDeleteBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(NewWoDeCheYuanDeleteBean newWoDeCheYuanDeleteBean) {
                    Toast.makeText(activity,newWoDeCheYuanDeleteBean.getMsg(),Toast.LENGTH_LONG).show();
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