package com.shandian.lu.Main.MineFragment.WoDeCheYuan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewLaHuoBean;
import com.example.mynewslayoutlib.Bean.NewOrderCancelBean;
import com.example.mynewslayoutlib.Bean.NewYiBaoJiaBean;
import com.example.mynewslayoutlib.Utils.SystemUtils;
import com.shandian.lu.Main.IndexFragment.NewHuoYuanDetail.NewHuoYuanDetailOtherActivity;
import com.shandian.lu.Main.ReleaseFragment.FaBuCheYuan.NewFaBuCheYuanActivity;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.NewQueryDeleteDialog;
import com.shandian.lu.Widget.Dialog.NewQueryDialog;

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

        dataList.addAll(dataList1);
        notifyDataSetChanged();
    }

    @Override
    public MyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyItemViewHolder(inflater.inflate(R.layout.activity_new_wodecheyuan_content_yibaojia_xrv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(MyItemViewHolder holder, int position) {
        holder.pos = position;

    /*    String isNew = dataList.get(position).getSfck();*/
       /* if((isNew == null)||(isNew.isEmpty())){
            isNew = "0";
        }
        if(isNew.equals("0")) {
            holder.tvNewWoDeCheYuanYiBaoJiaXRVItemNewBaoJia.setVisibility(View.VISIBLE);
        }else{
            holder.tvNewWoDeCheYuanYiBaoJiaXRVItemNewBaoJia.setVisibility(View.GONE);
        }*/
        String ztName = dataList.get(position).getZtname();
        if((ztName == null)||(ztName.isEmpty())){
            holder.llyNewWoDeCheYuanYiBaoJiaXRVItemNews.setVisibility(View.GONE);
        }else{
            holder.llyNewWoDeCheYuanYiBaoJiaXRVItemNews.setVisibility(View.VISIBLE);
            holder.tvNewWoDeCheYuanYiBaoJiaXRVItemNewMessage.setText(ztName);
        }
        holder.tvNewWoDeCheYuanYiBaoJiaXRVItemBCity.setText(dataList.get(position).getCfshi());
        holder.tvNewWoDeCheYuanYiBaoJiaXRVItemBArea.setText(dataList.get(position).getCfqu());
        holder.tvNewWoDeCheYuanYiBaoJiaXRVItemECity.setText(dataList.get(position).getDashi());
        holder.tvNewWoDeCheYuanYiBaoJiaXRVITemEArea.setText(dataList.get(position).getDaqu());
        holder.tvNewWoDeCheYuanYiBaoJiaXRVItemDelete.setText("取消订单");
        holder.tvNewWoDeCheYuanYiBaoJiaXRVItemUpdate.setText("修改报价");
        holder.tvNewWoDeCheYuanYiBaoJiaXRVItemUpdate.setVisibility(View.GONE);
        String typeName = dataList.get(position).getType_name();
        if(typeName == null){
            typeName = "";
        }
        switch (typeName){
            case "1":
                holder.tvNewWoDeCheYuanYiBaoJiaXRVItemGoodsType.setText("同城货源");
                break;
            case "2":
                holder.tvNewWoDeCheYuanYiBaoJiaXRVItemGoodsType.setText("长途货源");
                break;
            case "3":
                holder.tvNewWoDeCheYuanYiBaoJiaXRVItemGoodsType.setText("特种货源");
                break;
            case "4":
                holder.tvNewWoDeCheYuanYiBaoJiaXRVItemGoodsType.setText("专线货源");
                break;
        }
        holder.tvNewWoDeCheYuanYiBaoJiaXRVItemDis.setText(dataList.get(position).getJuli());
        holder.tvNewWoDeCheYuanYiBaoJiaXRVItemCount.setText("共"+dataList.get(position).getNum()+"条");
        String zt = dataList.get(position).getZt();
        if((zt == null)||(zt.isEmpty())){
            zt = "";
        }
       /* Toast.makeText(activity,"ddzt:"+zt,Toast.LENGTH_LONG).show();*/
        switch (zt){
            case "-1":
                holder.deleteUpdateState(true);
                holder.tvNewWoDeCheYuanYiBaoJiaXRVItemCount.setText("报价失败");
                holder.rlyNewWoDeCheYuanYiBaoJiaXRVItemCount.setBackgroundResource(R.mipmap.mine_cyhy_gray);
                break;
            case "0":
                    holder.deleteUpdateState(true);
                holder.rlyNewWoDeCheYuanYiBaoJiaXRVItemCount.setBackgroundResource(R.mipmap.mine_cyhy_blue_bg);
                break;
            case "1":
                holder.deleteUpdateState(false);
                holder.tvNewWoDeCheYuanYiBaoJiaXRVItemCount.setText("待货主\n支付定金");
                holder.rlyNewWoDeCheYuanYiBaoJiaXRVItemCount.setBackgroundResource(R.mipmap.mine_cyhy_blue_bg);
                break;
            case "2":
                holder.deleteUpdateState(false);
                holder.tvNewWoDeCheYuanYiBaoJiaXRVItemCount.setText("已装车");
                holder.rlyNewWoDeCheYuanYiBaoJiaXRVItemCount.setBackgroundResource(R.mipmap.mine_cyhy_orange_bg);
                break;
            case "3":
                holder.deleteUpdateState(false);
                holder.tvNewWoDeCheYuanYiBaoJiaXRVItemCount.setText("运输中");
                holder.rlyNewWoDeCheYuanYiBaoJiaXRVItemCount.setBackgroundResource(R.mipmap.mine_cyhy_orange_bg);
                break;
            case "4":
                holder.deleteUpdateState(false);
                holder.tvNewWoDeCheYuanYiBaoJiaXRVItemCount.setText("待货主\n支付尾款");
                holder.rlyNewWoDeCheYuanYiBaoJiaXRVItemCount.setBackgroundResource(R.mipmap.mine_cyhy_blue_bg);
                break;
            case "5":
                holder.deleteUpdateState(false);
                holder.tvNewWoDeCheYuanYiBaoJiaXRVItemCount.setText("交易成功");
                holder.rlyNewWoDeCheYuanYiBaoJiaXRVItemCount.setBackgroundResource(R.mipmap.mine_cyhy_gray);
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

        @BindView(R.id.lly_new_wodecheyuan_yibaojia_xrv_item)
        LinearLayout llyNewWoDeCheYuanYiBaoJiaXRVItem;
        @OnClick(R.id.lly_new_wodecheyuan_yibaojia_xrv_item)
        public void llyNewWoDeCheYuanYiBaoJiaXRVItemOnclick(){
            Intent intent = new Intent(activity, NewHuoYuanDetailOtherActivity.class);
            intent.putExtra("hyid",dataList.get(pos).getId());
            String status = dataList.get(pos).getDingdanzt();
            if(status == null){
                status = "";
            }
            intent.putExtra("status",status);
            activity.startActivity(intent);
            System.out.print("\nhyId:"+dataList.get(pos).getId()+" bjid:"+dataList.get(pos).getBjid()+" loginId:"+dataList.get(pos).getLogin_id());
            System.out.print("\nhyId:"+dataList.get(pos).getId()+" bjid:"+dataList.get(pos).getBjid()+" loginId:"+dataList.get(pos).getLogin_id());
            System.out.print("\nhyId:"+dataList.get(pos).getId()+" bjid:"+dataList.get(pos).getBjid()+" loginId:"+dataList.get(pos).getLogin_id());
            System.out.print("\nhyId:"+dataList.get(pos).getId()+" bjid:"+dataList.get(pos).getBjid()+" loginId:"+dataList.get(pos).getLogin_id());
        }
        NewQueryDialog newQueryDialog;
        @BindView(R.id.tv_new_wodecheyuan_yibaojia_xrv_item_count)
        TextView tvNewWoDeCheYuanYiBaoJiaXRVItemCount;
        @BindView(R.id.rly_new_wodecheyuan_yibaojia_xrv_item_count)
        RelativeLayout rlyNewWoDeCheYuanYiBaoJiaXRVItemCount;
        @OnClick(R.id.rly_new_wodecheyuan_yibaojia_xrv_item_count)
        public void rlyNewWoDeCheYuanYiBaoJiaXRVItemCountOnclick(){

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
                    newQueryDialog = new NewQueryDialog(activity,"已装车").Build.setCallBackListener(new NewQueryDialog.DialogCallBackListener() {
                        @Override
                        public void callBack(boolean isQuery) {
                            huoWuJieZouFinishToNet();
                            dissmissQueryDialog();
                        }
                    }).build(activity);
                    showQueryDialog();
                    break;
                case "3":
                    newQueryDialog = new NewQueryDialog(activity,"已送达").Build.setCallBackListener(new NewQueryDialog.DialogCallBackListener() {
                        @Override
                        public void callBack(boolean isQuery) {
                            huoWuSongDaToNet();
                            dissmissQueryDialog();
                        }
                    }).build(activity);
                    showQueryDialog();

                    break;
                case "4":

                    break;
                case "5":

                    break;
            }
        }


        public void showQueryDialog() {
            if (newQueryDialog != null && !newQueryDialog.isShowing())
                newQueryDialog.show();
        }

        public void dissmissQueryDialog() {
            if (newQueryDialog != null && newQueryDialog.isShowing())
                newQueryDialog.dismiss();
        }
        NewQueryDeleteDialog newQueryDeleteDialog;
        @BindView(R.id.tv_new_wodecheyuan_yibaojia_xrv_item_newmessage)
        TextView tvNewWoDeCheYuanYiBaoJiaXRVItemNewMessage;
        @BindView(R.id.iv_new_wodecheyuan_yibaojia_xrv_item_news)
        ImageView ivNewWoDeCheYuanYiBaoJiaXRVItemNews;
        @BindView(R.id.lly_new_wodecheyuan_yibaojia_xrv_item_news)
        LinearLayout llyNewWoDeCheYuanYiBaoJiaXRVItemNews;
        @BindView(R.id.tv_new_wodecheyuan_yibaojia_xrv_item_bcity)
        TextView tvNewWoDeCheYuanYiBaoJiaXRVItemBCity;
        @BindView(R.id.tv_new_wodecheyuan_yibaojia_xrv_item_barea)
        TextView tvNewWoDeCheYuanYiBaoJiaXRVItemBArea;
        @BindView(R.id.tv_new_wodecheyuan_yibaojia_xrv_item_ecity)
        TextView tvNewWoDeCheYuanYiBaoJiaXRVItemECity;
        @BindView(R.id.tv_new_wodecheyuan_yibaojia_xrv_item_earea)
        TextView tvNewWoDeCheYuanYiBaoJiaXRVITemEArea;
        @BindView(R.id.tv_new_wodecheyuan_yibaojia_xrv_item_dis)
        TextView tvNewWoDeCheYuanYiBaoJiaXRVItemDis;
        @BindView(R.id.tv_new_wodecheyuan_yibaojia_xrv_item_goodstype)
        TextView tvNewWoDeCheYuanYiBaoJiaXRVItemGoodsType;
        @BindView(R.id.lly_new_wodecheyuan_yibaojia_xrv_item_delete)
        LinearLayout llyNewWoDeCheYuanYiBaoJiaXRVItemDelete;
        @OnClick(R.id.lly_new_wodecheyuan_yibaojia_xrv_item_delete)
        public void llyNewWoDeCheYuanYiBaoJiaXRVItemDeleteOnclick(){



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
        @BindView(R.id.tv_new_wodecheyuan_yibaojia_xrv_item_delete)
        TextView tvNewWoDeCheYuanYiBaoJiaXRVItemDelete;
        @BindView(R.id.lly_new_wodecheyuan_yibaojia_xrv_item_update)
        LinearLayout llyNewWoDeCheYuanYiBaoJiaXRVItemUpdate;
        @OnClick(R.id.lly_new_wodecheyuan_yibaojia_xrv_item_update)
        public void llyNewWoDeCheYuanYiBaoJiaXRVItemUpdateOnclick(){
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
        @BindView(R.id.tv_new_wodecheyuan_yibaojia_xrv_item_update)
        TextView tvNewWoDeCheYuanYiBaoJiaXRVItemUpdate;
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
                tvNewWoDeCheYuanYiBaoJiaXRVItemDelete.setCompoundDrawables(drawableDelete,null,null,null);
                tvNewWoDeCheYuanYiBaoJiaXRVItemUpdate.setCompoundDrawables(drawableUpdate,null,null,null);
                tvNewWoDeCheYuanYiBaoJiaXRVItemDelete.setTextColor(activity.getResources().getColor(R.color.black));
                tvNewWoDeCheYuanYiBaoJiaXRVItemUpdate.setTextColor(activity.getResources().getColor(R.color.black));
                llyNewWoDeCheYuanYiBaoJiaXRVItemDelete.setClickable(true);
                llyNewWoDeCheYuanYiBaoJiaXRVItemUpdate.setClickable(true);
            }else {
                Drawable drawableDelete= activity.getResources().getDrawable(R.mipmap.delete_gray);
                Drawable drawableUpdate= activity.getResources().getDrawable(R.mipmap.update_gray);
                drawableDelete.setBounds(0, 0, wid, hig);
                drawableUpdate.setBounds(0, 0, wid, hig);
                tvNewWoDeCheYuanYiBaoJiaXRVItemDelete.setCompoundDrawables(drawableDelete,null,null,null);
                tvNewWoDeCheYuanYiBaoJiaXRVItemUpdate.setCompoundDrawables(drawableUpdate,null,null,null);
                tvNewWoDeCheYuanYiBaoJiaXRVItemDelete.setTextColor(activity.getResources().getColor(R.color.gray));
                tvNewWoDeCheYuanYiBaoJiaXRVItemUpdate.setTextColor(activity.getResources().getColor(R.color.gray));
                llyNewWoDeCheYuanYiBaoJiaXRVItemDelete.setClickable(false);
                llyNewWoDeCheYuanYiBaoJiaXRVItemUpdate.setClickable(false);
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
