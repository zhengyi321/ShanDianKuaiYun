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
import com.example.mynewslayoutlib.Utils.SystemUtils;
import com.shandian.lu.Main.IndexFragment.NewCheYuanDetail.NewCheYuanDetailSelflActivity;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.Main.ReleaseFragment.FaBuCheYuan.NewFaBuCheYuanV2Activity;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.NewQueryDeleteDialog;
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

public class NewWoDeCheYuanCheYuanListXRVAdapter extends RecyclerView.Adapter<NewWoDeCheYuanCheYuanListXRVAdapter.MyItemViewHolder> {

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
    public NewWoDeCheYuanCheYuanListXRVAdapter(Activity activity1, List<NewWoDeCheYuanBean.NrBean.ListBean> dataList1){
        activity = activity1;
        dataList = dataList1;
        inflater = LayoutInflater.from(activity1);
    }
    public void setAdapter(List<NewWoDeCheYuanBean.NrBean.ListBean> dataList1){

        dataList.addAll(dataList1);
        notifyDataSetChanged();
    }

    @Override
    public MyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyItemViewHolder(inflater.inflate(R.layout.activity_new_wodecheyuan_content_cheyuanlist_xrv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(MyItemViewHolder holder, int position) {
        holder.pos = position;
        holder.llyNewWoDeCheYuanListXRVItemNews.setVisibility(View.GONE);
    /*    String isNew = dataList.get(position).getSfck();*/
       /* if((isNew == null)||(isNew.isEmpty())){
            isNew = "0";
        }
        if(isNew.equals("0")) {
            holder.tvNewWoDeCheYuanListXRVItemNewBaoJia.setVisibility(View.VISIBLE);
        }else{
            holder.tvNewWoDeCheYuanListXRVItemNewBaoJia.setVisibility(View.GONE);
        }*/
        holder.tvNewWoDeCheYuanListXRVItemBCity.setText(dataList.get(position).getCfshi());
        holder.tvNewWoDeCheYuanListXRVItemBArea.setText(dataList.get(position).getCfqu());
        holder.tvNewWoDeCheYuanListXRVItemECity.setText(dataList.get(position).getDashi());
        holder.tvNewWoDeCheYuanListXRVITemEArea.setText(dataList.get(position).getDaqu());

        String typeName = dataList.get(position).getType_name();
        if(typeName == null){
            typeName = "";
        }
        switch (typeName){
            case "1":
                holder.tvNewWoDeCheYuanListXRVItemGoodsType.setText("同城物流");
                break;
            case "2":
                holder.tvNewWoDeCheYuanListXRVItemGoodsType.setText("长途物流");
                break;
            case "3":
                holder.tvNewWoDeCheYuanListXRVItemGoodsType.setText("特种物流");
                break;
            case "4":
                holder.tvNewWoDeCheYuanListXRVItemGoodsType.setText("专线物流");
                break;
        }
        holder.tvNewWoDeCheYuanListXRVItemDis.setText(dataList.get(position).getJuli());
        holder.rlyNewWoDeCheYuanListXRVItemCount.setVisibility(View.GONE);/*
        holder.tvNewWoDeCheYuanListXRVItemCount.setText("共"+dataList.get(position).getNum()+"条");
        String zt = dataList.get(position).getZt();
        if((zt == null)||(zt.isEmpty())){
            zt = "";
        }
       *//* Toast.makeText(activity,"ddzt:"+zt,Toast.LENGTH_LONG).show();*//*
        switch (zt){
            case "-1":
                    holder.deleteUpdateState(true);
                holder.tvNewWoDeCheYuanListXRVItemCount.setText("报价失败");
                break;
            case "0":
                    holder.deleteUpdateState(true);

                break;
            case "1":
                holder.deleteUpdateState(true);
                holder.tvNewWoDeCheYuanListXRVItemCount.setText("待货主\n支付定金");
                break;
            case "2":
                holder.deleteUpdateState(true);
                holder.tvNewWoDeCheYuanListXRVItemCount.setText("拉货");
                break;
            case "3":
                holder.deleteUpdateState(false);
                holder.tvNewWoDeCheYuanListXRVItemCount.setText("运输中");
                break;
            case "4":
                holder.deleteUpdateState(false);
                holder.tvNewWoDeCheYuanListXRVItemCount.setText("待货主\n支付尾款");
                break;
            case "5":
                holder.deleteUpdateState(false);
                holder.tvNewWoDeCheYuanListXRVItemCount.setText("交易成功");
                break;
        }*/


        /*if()*/
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyItemViewHolder extends RecyclerView.ViewHolder{
        int pos = 0;

        @BindView(R.id.lly_new_wodecheyuan_cheyuanlist_xrv_item)
        LinearLayout llyNewWoDeCheYuanListXRVItem;
        @OnClick(R.id.lly_new_wodecheyuan_cheyuanlist_xrv_item)
        public void llyNewWoDeCheYuanListXRVItemOnclick(){
            Intent intent = new Intent(activity, NewCheYuanDetailSelflActivity.class);
            intent.putExtra("cyid",dataList.get(pos).getId());
            activity.startActivity(intent);
        }

        @BindView(R.id.tv_new_wodecheyuan_cheyuanlist_xrv_item_count)
        TextView tvNewWoDeCheYuanListXRVItemCount;
        @BindView(R.id.rly_new_wodecheyuan_cheyuanlist_xrv_item_count)
        RelativeLayout rlyNewWoDeCheYuanListXRVItemCount;
        @OnClick(R.id.rly_new_wodecheyuan_cheyuanlist_xrv_item_count)
        public void rlyNewWoDeCheYuanListXRVItemCountOnclick(){

            Intent intent = new Intent(activity, NewCheYuanDetailSelflActivity.class);
            intent.putExtra("cyid",dataList.get(pos).getId());
            activity.startActivity(intent);
            String zt = dataList.get(pos).getZt();
            if((zt == null)||(zt.isEmpty())){
                zt = "";
            }
            /*Toast.makeText(activity,"ddzt:"+zt,Toast.LENGTH_LONG).show();*/
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
        NewQueryDeleteDialog newQueryDeleteDialog;
        @BindView(R.id.tv_new_wodecheyuan_cheyuanlist_xrv_item_newmessage)
        TextView tvNewWoDeCheYuanListXRVItemNewMessage;
        @BindView(R.id.lly_new_wodecheyuan_cheyuanlist_xrv_item_news)
        LinearLayout llyNewWoDeCheYuanListXRVItemNews;
        @BindView(R.id.tv_new_wodecheyuan_cheyuanlist_xrv_item_bcity)
        TextView tvNewWoDeCheYuanListXRVItemBCity;
        @BindView(R.id.tv_new_wodecheyuan_cheyuanlist_xrv_item_barea)
        TextView tvNewWoDeCheYuanListXRVItemBArea;
        @BindView(R.id.tv_new_wodecheyuan_cheyuanlist_xrv_item_ecity)
        TextView tvNewWoDeCheYuanListXRVItemECity;
        @BindView(R.id.tv_new_wodecheyuan_cheyuanlist_xrv_item_earea)
        TextView tvNewWoDeCheYuanListXRVITemEArea;
        @BindView(R.id.tv_new_wodecheyuan_cheyuanlist_xrv_item_dis)
        TextView tvNewWoDeCheYuanListXRVItemDis;
        @BindView(R.id.tv_new_wodecheyuan_cheyuanlist_xrv_item_goodstype)
        TextView tvNewWoDeCheYuanListXRVItemGoodsType;
        @BindView(R.id.lly_new_wodecheyuan_cheyuanlist_xrv_item_delete)
        LinearLayout llyNewWoDeCheYuanListXRVItemDelete;
        @OnClick(R.id.lly_new_wodecheyuan_cheyuanlist_xrv_item_delete)
        public void llyNewWoDeCheYuanListXRVItemDeleteOnclick(){



            newQueryDeleteDialog = new NewQueryDeleteDialog(activity).Build.setCallBackListener(new NewQueryDeleteDialog.DialogCallBackListener() {
                @Override
                public void callBack(boolean isDelete) {
                    if(isDelete){
                        deleteWoDeCheYuan();
                    }
                    dissmissDialog();
                }
            }).build(activity);

        /* Toast.makeText(activity,"hyid:"+hyId,Toast.LENGTH_LONG).show();*/
            showDialog();

        }
        public void showDialog() {
            if (newQueryDeleteDialog != null && !newQueryDeleteDialog.isShowing())
                newQueryDeleteDialog.show();
        }

        public void dissmissDialog() {
            if (newQueryDeleteDialog != null && newQueryDeleteDialog.isShowing())
                newQueryDeleteDialog.dismiss();
        }
        @BindView(R.id.tv_new_wodecheyuan_cheyuanlist_xrv_item_delete)
        TextView tvNewWoDeCheYuanListXRVItemDelete;
        @BindView(R.id.lly_new_wodecheyuan_cheyuanlist_xrv_item_update)
        LinearLayout llyNewWoDeCheYuanListXRVItemUpdate;
        @OnClick(R.id.lly_new_wodecheyuan_cheyuanlist_xrv_item_update)
        public void llyNewWoDeCheYuanListXRVItemUpdateOnclick(){
            Intent intent = new Intent(activity, NewFaBuCheYuanV2Activity.class);
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
        @BindView(R.id.tv_new_wodecheyuan_cheyuanlist_xrv_item_update)
        TextView tvNewWoDeCheYuanListXRVItemUpdate;
        public void deleteUpdateState(boolean canUpdateDelete){
            SystemUtils systemUtils = new SystemUtils(activity);
            double width = systemUtils.getWindowWidth();
            double height = systemUtils.getWindowHeight();
            int wid = (int) (width/30);
            int hig = (int) (height/54);
            if(canUpdateDelete){
                Drawable drawableDelete= activity.getResources().getDrawable(R.mipmap.delete_black);
                Drawable drawableUpdate= activity.getResources().getDrawable(R.mipmap.update_black);
                drawableDelete.setBounds(0, 0, wid, hig);
                drawableUpdate.setBounds(0, 0, wid, hig);
                tvNewWoDeCheYuanListXRVItemDelete.setCompoundDrawables(drawableDelete,null,null,null);
                tvNewWoDeCheYuanListXRVItemUpdate.setCompoundDrawables(drawableUpdate,null,null,null);
                tvNewWoDeCheYuanListXRVItemDelete.setTextColor(activity.getResources().getColor(R.color.black));
                tvNewWoDeCheYuanListXRVItemUpdate.setTextColor(activity.getResources().getColor(R.color.black));
                llyNewWoDeCheYuanListXRVItemDelete.setClickable(true);
                llyNewWoDeCheYuanListXRVItemUpdate.setClickable(true);
            }else {
                Drawable drawableDelete= activity.getResources().getDrawable(R.mipmap.delete_gray);
                Drawable drawableUpdate= activity.getResources().getDrawable(R.mipmap.update_gray);
                drawableDelete.setBounds(0, 0, wid, hig);
                drawableUpdate.setBounds(0, 0, wid, hig);
                tvNewWoDeCheYuanListXRVItemDelete.setCompoundDrawables(drawableDelete,null,null,null);
                tvNewWoDeCheYuanListXRVItemUpdate.setCompoundDrawables(drawableUpdate,null,null,null);
                tvNewWoDeCheYuanListXRVItemDelete.setTextColor(activity.getResources().getColor(R.color.gray));
                tvNewWoDeCheYuanListXRVItemUpdate.setTextColor(activity.getResources().getColor(R.color.gray));
                llyNewWoDeCheYuanListXRVItemDelete.setClickable(false);
                llyNewWoDeCheYuanListXRVItemUpdate.setClickable(false);
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
