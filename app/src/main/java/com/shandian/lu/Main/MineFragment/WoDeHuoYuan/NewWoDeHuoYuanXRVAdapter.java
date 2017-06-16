package com.shandian.lu.Main.MineFragment.WoDeHuoYuan;

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

import com.baidu.mapapi.map.Text;
import com.example.mynewslayoutlib.Bean.NewWoDeHuoYuanBean;
import com.example.mynewslayoutlib.Bean.NewWoDeHuoYuanDeleteBean;
import com.example.mynewslayoutlib.Utils.SystemUtils;
import com.j256.ormlite.stmt.query.In;
import com.shandian.lu.Main.IndexFragment.NewHuoYuanDetail.NewHuoYuanDetailSelfActivity;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.Main.MineFragment.PaySubmit.TwoStepPaySubmitActivity;
import com.shandian.lu.Main.ReleaseFragment.FaBuHuoYuan.NewFaBuHuoYuanActivity;
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
        String ztName = dataList.get(position).getZtname();
        if((isNew == null)||(isNew.isEmpty())){
            isNew = "0";
        }
        if((ztName == null)||(isNew.isEmpty())){
            ztName = "";
        }
       /* if(isNew.equals("1")) {
            holder.llyNewWoDeHuoYuanXRVItemNews.setVisibility(View.VISIBLE);
        }else{
            holder.llyNewWoDeHuoYuanXRVItemNews.setVisibility(View.GONE);
        }*/
       holder.ivNewWoDeHuoYuanXRVItemNews.setVisibility(View.GONE);
        holder.tvNewWoDeHuoYuanXRVItemNews.setText(ztName);
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
/*        Toast.makeText(activity,"ddzt:"+ddzt,Toast.LENGTH_LONG).show();*/
        switch (ddzt){
            case "0":
                    holder.deleteUpdateState(true);
                holder.rlyNewWoDeHuoYuanXRVItemCount.setBackgroundResource(R.mipmap.mine_cyhy_blue_bg);
                holder.tvNewWoDeHuoYuanXRVItemCount.setText("共"+dataList.get(position).getDdcount()+"条");
                break;
            case "1":
                holder.deleteUpdateState(true);
                holder.tvNewWoDeHuoYuanXRVItemCount.setText("支付定金");
                holder.rlyNewWoDeHuoYuanXRVItemCount.setBackgroundResource(R.mipmap.mine_cyhy_orange_bg);
                break;
            case "2":
                holder.deleteUpdateState(true);
                holder.tvNewWoDeHuoYuanXRVItemCount.setText("待接货");
                holder.rlyNewWoDeHuoYuanXRVItemCount.setBackgroundResource(R.mipmap.mine_cyhy_blue_bg);
                break;
            case "3":
                holder.deleteUpdateState(false);
                holder.tvNewWoDeHuoYuanXRVItemCount.setText("运输中");
                holder.rlyNewWoDeHuoYuanXRVItemCount.setBackgroundResource(R.mipmap.mine_cyhy_blue_bg);
                break;
            case "4":
                holder.deleteUpdateState(false);
                holder.tvNewWoDeHuoYuanXRVItemCount.setText("支付尾款");
                holder.rlyNewWoDeHuoYuanXRVItemCount.setBackgroundResource(R.mipmap.mine_cyhy_orange_bg);
                break;
            case "5":
                holder.deleteUpdateState(false);
                holder.tvNewWoDeHuoYuanXRVItemCount.setText("交易成功");
                holder.rlyNewWoDeHuoYuanXRVItemCount.setBackgroundResource(R.mipmap.mine_cyhy_gray);
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

        @BindView(R.id.lly_new_wodehuoyuan_xrv_item)
        LinearLayout llyNewWoDeHuoYuanXRVItem;
        @OnClick(R.id.lly_new_wodehuoyuan_xrv_item)
        public void llyNewWoDeHuoYuanXRVItemOnclick(){
            String ddzt = dataList.get(pos).getDingdanzt();

            if((ddzt == null)||(ddzt.isEmpty())){
                ddzt = "";
            }
            Intent intent = new Intent(activity, NewHuoYuanDetailSelfActivity.class);
            intent.putExtra("status",ddzt);
            intent.putExtra("hyid",dataList.get(pos).getId());
            activity.startActivity(intent);
        }

        @BindView(R.id.tv_new_wodehuoyuan_xrv_item_count)
        TextView tvNewWoDeHuoYuanXRVItemCount;
        @BindView(R.id.rly_new_wodehuoyuan_xrv_item_count)
        RelativeLayout rlyNewWoDeHuoYuanXRVItemCount;
        @OnClick(R.id.rly_new_wodehuoyuan_xrv_item_count)
        public void rlyNewWoDeHuoYuanXRVItemCountOnclick(){
            String ddzt = dataList.get(pos).getDingdanzt();
            Intent intent;
            if((ddzt == null)||(ddzt.isEmpty())){
                ddzt = "";
            }
           /* Toast.makeText(activity,"ddzt:"+ddzt,Toast.LENGTH_LONG).show();*/
            switch (ddzt){
                case "0":

                    break;
                case "1":
                        intent = new Intent(activity, TwoStepPaySubmitActivity.class);
                        System.out.print("\nhyId"+dataList.get(pos).getId()+" baojiaId:"+dataList.get(pos).getBaojiaid());
                        System.out.print("\nhyId"+dataList.get(pos).getId()+" baojiaId:"+dataList.get(pos).getBaojiaid());
                        System.out.print("\nhyId"+dataList.get(pos).getId()+" baojiaId:"+dataList.get(pos).getBaojiaid());
                        System.out.print("\nhyId"+dataList.get(pos).getId()+" baojiaId:"+dataList.get(pos).getBaojiaid());
                        System.out.print("\nhyId"+dataList.get(pos).getId()+" baojiaId:"+dataList.get(pos).getBaojiaid());
                        System.out.print("\nhyId"+dataList.get(pos).getId()+" baojiaId:"+dataList.get(pos).getBaojiaid());
                        intent.putExtra("hyId",dataList.get(pos).getId());
                        intent.putExtra("baojiaId",dataList.get(pos).getBaojiaid());
                        intent.putExtra("status","dingjin");

                        activity.startActivity(intent);
                    break;
                case "2":

                    break;
                case "3":

                    break;
                case "4":
                     intent = new Intent(activity, TwoStepPaySubmitActivity.class);
                    intent.putExtra("hyId",dataList.get(pos).getId());
                    intent.putExtra("baojiaId",dataList.get(pos).getBaojiaid());
                    intent.putExtra("status","weikuan");
                    activity.startActivity(intent);
                    break;
                case "5":

                    break;
            }
        }
        NewQueryDeleteDialog newQueryDeleteDialog;
        @BindView(R.id.tv_new_wodehuoyuan_xrv_item_news)
        TextView tvNewWoDeHuoYuanXRVItemNews;
        @BindView(R.id.iv_new_wodehuoyuan_xrv_item_news)
        ImageView ivNewWoDeHuoYuanXRVItemNews;
        @BindView(R.id.lly_new_wodehuoyuan_xrv_item_news)
        LinearLayout llyNewWoDeHuoYuanXRVItemNews;
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



            newQueryDeleteDialog = new NewQueryDeleteDialog(activity).Build.setCallBackListener(new NewQueryDeleteDialog.DialogCallBackListener() {
                @Override
                public void callBack(boolean isDelete) {
                    if(isDelete){
                        deleteWoDeHuoYuan();
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
        @BindView(R.id.tv_new_wodehuoyuan_xrv_item_update)
        TextView tvNewWoDeHuoYuanXRVItemUpdate;
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
                tvNewWoDeHuoYuanXRVItemDelete.setCompoundDrawables(drawableDelete,null,null,null);
                tvNewWoDeHuoYuanXRVItemUpdate.setCompoundDrawables(drawableUpdate,null,null,null);
                tvNewWoDeHuoYuanXRVItemDelete.setTextColor(activity.getResources().getColor(R.color.black));
                tvNewWoDeHuoYuanXRVItemUpdate.setTextColor(activity.getResources().getColor(R.color.black));
                llyNewWoDeHuoYuanXRVItemDelete.setClickable(true);
                llyNewWoDeHuoYuanXRVItemUpdate.setClickable(true);
            }else {
                Drawable drawableDelete= activity.getResources().getDrawable(R.mipmap.delete_gray);
                Drawable drawableUpdate= activity.getResources().getDrawable(R.mipmap.update_gray);
                drawableDelete.setBounds(0, 0, wid, hig);
                drawableUpdate.setBounds(0, 0, wid, hig);
                tvNewWoDeHuoYuanXRVItemDelete.setCompoundDrawables(drawableDelete,null,null,null);
                tvNewWoDeHuoYuanXRVItemUpdate.setCompoundDrawables(drawableUpdate,null,null,null);
                tvNewWoDeHuoYuanXRVItemDelete.setTextColor(activity.getResources().getColor(R.color.gray));
                tvNewWoDeHuoYuanXRVItemUpdate.setTextColor(activity.getResources().getColor(R.color.gray));
                llyNewWoDeHuoYuanXRVItemDelete.setClickable(false);
                llyNewWoDeHuoYuanXRVItemUpdate.setClickable(false);
            }
        }

        private void deleteWoDeHuoYuan(){

            String loginId = dataList.get(pos).getLogin_id();
            if((loginId == null)||(loginId.isEmpty())){
                loginId = "";
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
