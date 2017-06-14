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

import com.example.mynewslayoutlib.Bean.NewLaHuoBean;
import com.example.mynewslayoutlib.Bean.NewOrderCancelBean;
import com.example.mynewslayoutlib.Bean.NewWoDeHuoYuanDeleteBean;
import com.example.mynewslayoutlib.Bean.NewYiBaoJiaBean;
import com.example.mynewslayoutlib.Bean.NewWoDeCheYuanDeleteBean;
import com.example.mynewslayoutlib.Utils.SystemUtils;
import com.shandian.lu.Main.IndexFragment.NewHuoYuanDetail.NewHuoYuanDetailOtherActivity;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.Main.ReleaseFragment.FaBuCheYuan.NewFaBuCheYuanActivity;
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

public class NewWoDeCheYuanYiBaoJiaXRVAdapter extends RecyclerView.Adapter<NewWoDeCheYuanYiBaoJiaXRVAdapter.MyItemViewHolder> {

    private Activity activity;
    private List<NewYiBaoJiaBean.NrBean.ListBean> dataList;
    private LayoutInflater inflater;
    interface DeleteListener{
        public void onDeleteOnclick(boolean isSuccess);
    }
    DeleteListener onDeleteListener;
    public void deleteOnClickCallBack(DeleteListener onDeleteListener1){
        onDeleteListener = onDeleteListener1;
    }
    public NewWoDeCheYuanYiBaoJiaXRVAdapter(Activity activity1, List<NewYiBaoJiaBean.NrBean.ListBean> dataList1){
        activity = activity1;
        dataList = dataList1;
        inflater = LayoutInflater.from(activity1);
    }
    public void setAdapter(List<NewYiBaoJiaBean.NrBean.ListBean> dataList1){
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
        holder.tvNewWoDeCheYuanXRVItemDelete.setText("取消订单");
        holder.tvNewWoDeCheYuanXRVItemUpdate.setText("修改报价");
        String typeName = dataList.get(position).getType_name();
        if(typeName == null){
            typeName = "";
        }
        switch (typeName){
            case "1":
                holder.tvNewWoDeCheYuanXRVItemGoodsType.setText("同城货源");
                break;
            case "2":
                holder.tvNewWoDeCheYuanXRVItemGoodsType.setText("长途货源");
                break;
            case "3":
                holder.tvNewWoDeCheYuanXRVItemGoodsType.setText("特种货源");
                break;
            case "4":
                holder.tvNewWoDeCheYuanXRVItemGoodsType.setText("专线货源");
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
                holder.deleteUpdateState(false);
                holder.tvNewWoDeCheYuanXRVItemCount.setText("待货主\n支付定金");
                break;
            case "2":
                holder.deleteUpdateState(false);
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
            Intent intent = new Intent(activity, NewHuoYuanDetailOtherActivity.class);
            intent.putExtra("hyid",dataList.get(pos).getId());
            activity.startActivity(intent);
            System.out.print("\nhyId:"+dataList.get(pos).getId()+" bjid:"+dataList.get(pos).getBjid()+" loginId:"+dataList.get(pos).getLogin_id());
            System.out.print("\nhyId:"+dataList.get(pos).getId()+" bjid:"+dataList.get(pos).getBjid()+" loginId:"+dataList.get(pos).getLogin_id());
            System.out.print("\nhyId:"+dataList.get(pos).getId()+" bjid:"+dataList.get(pos).getBjid()+" loginId:"+dataList.get(pos).getLogin_id());
            System.out.print("\nhyId:"+dataList.get(pos).getId()+" bjid:"+dataList.get(pos).getBjid()+" loginId:"+dataList.get(pos).getLogin_id());
        }

        @BindView(R.id.tv_new_wodecheyuan_xrv_item_count)
        TextView tvNewWoDeCheYuanXRVItemCount;
        @BindView(R.id.rly_new_wodecheyuan_xrv_item_count)
        RelativeLayout rlyNewWoDeCheYuanXRVItemCount;
        @OnClick(R.id.rly_new_wodecheyuan_xrv_item_count)
        public void rlyNewWoDeCheYuanXRVItemCountOnclick(){

        /*    Intent intent = new Intent(activity, NewHuoYuanDetailOtherActivity.class);
            intent.putExtra("hyid",dataList.get(pos).getId());
            activity.startActivity(intent);*/
            String zt = dataList.get(pos).getZt();
            if((zt == null)||(zt.isEmpty())){
                zt = "";
            }
           /* Toast.makeText(activity,"ddzt:"+zt,Toast.LENGTH_LONG).show();*/
            switch (zt){
                case "-1":

                    break;
                case "0":

                    break;
                case "1":

                    break;
                case "2":
                    huoWuJieZouFinishToNet();
                    break;
                case "3":
                    huoWuSongDaToNet();
                    break;
                case "4":

                    break;
                case "5":

                    break;
            }
        }
        NewQueryDeleteDialog newQueryDeleteDialog;
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



            newQueryDeleteDialog = new NewQueryDeleteDialog(activity).Build.setCallBackListener(new NewQueryDeleteDialog.DialogCallBackListener() {
                @Override
                public void callBack(boolean isDelete) {
                    if(isDelete){
                        cancelMyBaoJiaOrderToNet();
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
            SystemUtils systemUtils = new SystemUtils(activity);
            double width = systemUtils.getWindowWidth();
            double height = systemUtils.getWindowHeight();
            int wid = (int) (width/20);
            int hig = (int) (height/40);
            if(canUpdateDelete){
                Drawable drawableDelete= activity.getResources().getDrawable(R.mipmap.delete_black);
                Drawable drawableUpdate= activity.getResources().getDrawable(R.mipmap.update_black);
                drawableDelete.setBounds(0, 0, wid, hig);
                drawableUpdate.setBounds(0, 0, wid, hig);
                tvNewWoDeCheYuanXRVItemDelete.setCompoundDrawables(drawableDelete,null,null,null);
                tvNewWoDeCheYuanXRVItemUpdate.setCompoundDrawables(drawableUpdate,null,null,null);
                tvNewWoDeCheYuanXRVItemDelete.setTextColor(activity.getResources().getColor(R.color.black));
                tvNewWoDeCheYuanXRVItemUpdate.setTextColor(activity.getResources().getColor(R.color.black));
                llyNewWoDeCheYuanXRVItemDelete.setClickable(true);
                llyNewWoDeCheYuanXRVItemUpdate.setClickable(true);
            }else {
                Drawable drawableDelete= activity.getResources().getDrawable(R.mipmap.delete_gray);
                Drawable drawableUpdate= activity.getResources().getDrawable(R.mipmap.update_gray);
                drawableDelete.setBounds(0, 0, wid, hig);
                drawableUpdate.setBounds(0, 0, wid, hig);
                tvNewWoDeCheYuanXRVItemDelete.setCompoundDrawables(drawableDelete,null,null,null);
                tvNewWoDeCheYuanXRVItemUpdate.setCompoundDrawables(drawableUpdate,null,null,null);
                tvNewWoDeCheYuanXRVItemDelete.setTextColor(activity.getResources().getColor(R.color.gray));
                tvNewWoDeCheYuanXRVItemUpdate.setTextColor(activity.getResources().getColor(R.color.gray));
                llyNewWoDeCheYuanXRVItemDelete.setClickable(false);
                llyNewWoDeCheYuanXRVItemUpdate.setClickable(false);
            }
        }

        private void cancelMyBaoJiaOrderToNet(){
            String hyId = dataList.get(pos).getId();
            if((hyId == null)||(hyId.isEmpty())){

            }
            String baojiaid = dataList.get(pos).getBjid();
            if(baojiaid == null){
                baojiaid = "";
            }
            Map<String,String> paramMap = new HashMap<>();
            paramMap.put("hyid",hyId);
            paramMap.put("baojiaid",baojiaid);
            NewCheHuoListNetWork newCheHuoListNetWork = new NewCheHuoListNetWork();
            newCheHuoListNetWork.cancelBaoJiaOrderToNet(paramMap, new Observer<NewOrderCancelBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(NewOrderCancelBean newOrderCancelBean) {
                    Toast.makeText(activity,newOrderCancelBean.getMsg(),Toast.LENGTH_LONG).show();
                    onDeleteListener.onDeleteOnclick(true);
                }
            });

        }

        private void huoWuJieZouFinishToNet(){
            Map<String,String> paramMap = new HashMap<>();
            String loginId = dataList.get(pos).getLogin_id();
            if(loginId == null){
                loginId = "";
            }
            String hyid = dataList.get(pos).getId();
            if(hyid == null){
                hyid = "";
            }
            String baojiaid = dataList.get(pos).getBaojiaid();
            if(baojiaid == null){
                baojiaid = "";
            }
            paramMap.put("login_id",loginId);
            paramMap.put("hyid",hyid);
            paramMap.put("baojiaid",baojiaid);
            NewCheHuoListNetWork newCheHuoListNetWork = new NewCheHuoListNetWork();
            newCheHuoListNetWork.huoWuJieZouFinishToNet(paramMap, new Observer<NewLaHuoBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(NewLaHuoBean newLaHuoBean) {
                    Toast.makeText(activity,newLaHuoBean.getMsg(),Toast.LENGTH_LONG).show();
                }
            });
        }
        private void huoWuSongDaToNet(){
            Map<String,String> paramMap = new HashMap<>();
            String loginId = dataList.get(pos).getLogin_id();
            if(loginId == null){
                loginId = "";
            }
            String hyid = dataList.get(pos).getId();
            if(hyid == null){
                hyid = "";
            }
            String baojiaid = dataList.get(pos).getBaojiaid();
            if(baojiaid == null){
                baojiaid = "";
            }
            paramMap.put("login_id",loginId);
            paramMap.put("hyid",hyid);
            paramMap.put("baojiaid",baojiaid);
            NewCheHuoListNetWork newCheHuoListNetWork = new NewCheHuoListNetWork();
            newCheHuoListNetWork.huoWuSongDaToNet(paramMap, new Observer<NewLaHuoBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(NewLaHuoBean newLaHuoBean) {
                    Toast.makeText(activity,newLaHuoBean.getMsg(),Toast.LENGTH_LONG).show();
                }
            });
        }
        public MyItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
