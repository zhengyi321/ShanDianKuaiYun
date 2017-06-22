package com.shandian.lu.Main.IndexFragment.NewHuoYuanList;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.NewHuoYuanListBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.Main.IndexFragment.BaiDuRoutePlan.NewBaiDuRoutePlanActivity;
import com.shandian.lu.Main.IndexFragment.NewHuoYuanDetail.NewHuoYuanDetailOtherActivity;
import com.shandian.lu.Main.IndexFragment.NewHuoYuanDetail.NewHuoYuanDetailSelfActivity;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.LookBaoJiaDialog;
import com.shandian.lu.Widget.Dialog.NewEditBaoJiaDialog;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.CallTelDialog;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ImageView.RoundCornerImageView.RoundCornerImageView;
import com.zhyan.shandiankuaiyunlib.Widget.ImageView.RoundImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/9.
 */

public class HuoYuanListXRVAdapter extends RecyclerView.Adapter<HuoYuanListXRVAdapter.MyItemHolder> {

    private Activity activity;
    public List<NewHuoYuanListBean.NrBean.ListBean> huoYuanList;
    private LayoutInflater inflater;
    public HuoYuanListXRVAdapter(Activity activity1, List<NewHuoYuanListBean.NrBean.ListBean> huoYuanList1){
        activity = activity1;
        huoYuanList = huoYuanList1;
        inflater = LayoutInflater.from(activity1);
    }

    @Override
    public MyItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyItemHolder(inflater.inflate(R.layout.activity_new_huoyuanlist_content_xrv_item_lly,parent,false));
    }

    public void setAdapter(List<NewHuoYuanListBean.NrBean.ListBean> huoYuanList1){

        huoYuanList.addAll(huoYuanList1);
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(MyItemHolder holder, int position) {
        holder.pos = position;
        holder.tvNewHuoYuanListTime.setText(huoYuanList.get(position).getTime());
        holder.tvNewHuoYuanListBCity.setText(huoYuanList.get(position).getCfsheng()+huoYuanList.get(position).getCfshi());
      /*  holder.tvNewHuoYuanListBArea.setText(huoYuanList.get(position).getCfqu());*/
        holder.tvNewHuoYuanListECity.setText(huoYuanList.get(position).getDasheng()+huoYuanList.get(position).getDashi());
        holder.tvNewHuoYuanListHZName.setText(huoYuanList.get(position).getNicheng());
        holder.tvNewHuoYuanListProduct.setText(huoYuanList.get(position).getHuowulx());
        String tiji = huoYuanList.get(position).getTiji();
        if(tiji.isEmpty()){

        }else{
            holder.tvNewHuoYuanListTiJi.setText(tiji);
        }
        String weight = huoYuanList.get(position).getWeight();
        if(weight.isEmpty()){

        }else{
            holder.tvNewHuoYuanListTiJi.setText(weight);
        }

       /* holder.tvNewHuoYuanListEArea.setText(huoYuanList.get(position).getDaqu());*/
        /*String type = huoYuanList.get(position).getType_name();
        if(type == null){
            type = "";
        }*/
       /* switch (type){
            case "1":
                holder.tvNewHuoYuanListHuoType.setText("同城货源");
                break;
            case "2":
                holder.tvNewHuoYuanListHuoType.setText("长途货源");
                break;
            case "3":
                holder.tvNewHuoYuanListHuoType.setText("特种货源");
                break;
            case "4":
                holder.tvNewHuoYuanListHuoType.setText("专线货源");
                break;
        }

        holder.tvNewHuoYuanListDis.setText(huoYuanList.get(position).getJuli());
        holder.tvNewHuoYuanListName.setText(huoYuanList.get(position).getNicheng());*/
        ImageLoader.getInstance().displayImage(huoYuanList.get(position).getTouxiang(),holder.rcivNewHuoYuanListTouXiang, ImageLoaderUtils.options1);
        if(position == 0){

            holder.llyNewHuoYuanListItem.setVisibility(View.VISIBLE);
            holder.rlyNewHuoYuanListAds.setVisibility(View.GONE);
        }else {
            if (position % 6 == 0) {
                holder.llyNewHuoYuanListItem.setVisibility(View.GONE);
                holder.rlyNewHuoYuanListAds.setVisibility(View.VISIBLE);
            } else {
                holder.llyNewHuoYuanListItem.setVisibility(View.VISIBLE);
                holder.rlyNewHuoYuanListAds.setVisibility(View.GONE);
            }
        }

       /* if(huoYuanList.get(position).getGg() == null){
            return;
        }*/
    /*    if(huoYuanList.get(position).getGg().equals("0")) {
            holder.tvNewHuoYuanListAds.setVisibility(View.GONE);
        }else{
            holder.tvNewHuoYuanListAds.setVisibility(View.VISIBLE);
        }*/
   /*     Toast.makeText(activity,""+huoYuanList.get(position).getId(),Toast.LENGTH_LONG).show();
*/
    }

    @Override
    public int getItemCount() {
        return huoYuanList.size();
    }


    public class MyItemHolder extends RecyclerView.ViewHolder {
        int pos = 0;
        CallTelDialog callTelDialog;/*
        @BindView(R.id.tv_new_huoyuanlist_time)
        TextView tvNewHuoYuanListTime;
        @BindView(R.id.tv_new_huoyuanlist_bcity)
        TextView tvNewHuoYuanListBCity;
        @BindView(R.id.tv_new_huoyuanlist_barea)
        TextView tvNewHuoYuanListBArea;
        @BindView(R.id.tv_new_huoyuanlist_ecity)
        TextView tvNewHuoYuanListECity;
        @BindView(R.id.tv_new_huoyuanlist_earea)
        TextView tvNewHuoYuanListEArea;
        @BindView(R.id.tv_new_huoyuanlist_data)
        TextView tvNewHuoYuanListData;
        @BindView(R.id.tv_new_huoyuanlist_huotype)
        TextView tvNewHuoYuanListHuoType;
        @BindView(R.id.tv_new_huoyuanlist_dis)
        TextView tvNewHuoYuanListDis;
        @BindView(R.id.tv_new_huoyuanlist_ads)
        TextView tvNewHuoYuanListAds;
        @BindView(R.id.riv_new_huoyuanlist_touxiang)
        RoundImageView rivNewHuoYuanListTouXiang;
        @BindView(R.id.tv_new_huoyuanlist_name)
        TextView tvNewHuoYuanListName;*/

        @BindView(R.id.rly_new_huoyuanlist_ads)
        RelativeLayout rlyNewHuoYuanListAds;
        @BindView(R.id.tv_new_huoyuanlist_hz_name)
        TextView tvNewHuoYuanListHZName;
        @BindView(R.id.tv_new_huoyuanlist_time)
        TextView tvNewHuoYuanListTime;
        @BindView(R.id.tv_new_huoyuanlist_bcity)
        TextView tvNewHuoYuanListBCity;
        @BindView(R.id.tv_new_huoyuanlist_ecity)
        TextView tvNewHuoYuanListECity;

        @BindView(R.id.tv_new_huoyuanlist_account)
        TextView tvNewHuoYuanListAccount;
        @BindView(R.id.tv_new_huoyuanlist_product)
        TextView tvNewHuoYuanListProduct;
        @BindView(R.id.tv_new_huoyuanlist_tiji)
        TextView tvNewHuoYuanListTiJi;
        @BindView(R.id.tv_new_huoyuanlist_weight)
        TextView tvNewHuoYuanListWeight;
        @BindView(R.id.tv_new_huoyuanlist_route)
        TextView tvNewHuoYuanListRoute;
        @OnClick(R.id.tv_new_huoyuanlist_route)
        public void tvNewHuoYuanListRouteOnclick(){
            Intent intent = new Intent(activity, NewBaiDuRoutePlanActivity.class);
            String cfzuobiao = huoYuanList.get(pos).getCfzuobiao();
            int indexOfBegin = cfzuobiao.indexOf(",");
            String bLat ="";
            String bLon = "";
            if( indexOfBegin > 0){
                bLat = cfzuobiao.substring(0,indexOfBegin);
                bLon = cfzuobiao.substring(indexOfBegin+1,cfzuobiao.length());
            }
            String dazuobiao = huoYuanList.get(pos).getDazuobiao();
            String eLat = "";
            String eLon = "";
            int indexOfEnd = dazuobiao.indexOf(",");
            if(indexOfEnd > 0){
                eLat = dazuobiao.substring(0,indexOfEnd);
                eLon = dazuobiao.substring(indexOfEnd+1,dazuobiao.length());
            }
            if(bLat == null){
                bLat = "";
            }
            if(bLon == null){
                bLon = "";
            }
            if(eLat == null){
                eLat = "";
            }
            if(eLon == null){
                eLon = "";
            }
            String cheLat= huoYuanList.get(pos).getLat();
            String cheLon = huoYuanList.get(pos).getLng();
            String cheTouXiang = huoYuanList.get(pos).getTouxiang();
            intent.putExtra("blat",bLat);
            intent.putExtra("blon",bLon);
            intent.putExtra("elat",eLat);
            intent.putExtra("elon",eLon);
            intent.putExtra("czlat",cheLat);
            intent.putExtra("czlon",cheLon);
            intent.putExtra("czTouXiang",cheTouXiang);
            activity.startActivity(intent);
        }
        NewEditBaoJiaDialog neweditBaoJiaDialog;
        @BindView(R.id.tv_new_huoyuanlist_bj)
        TextView tvNewHuoYuanListBJ;
        @OnClick(R.id.tv_new_huoyuanlist_bj)
        public void tvNewHuoYuanListBJOnclick(){
            String hyId = huoYuanList.get(pos).getId();
            neweditBaoJiaDialog = new NewEditBaoJiaDialog(activity,hyId).Build.build(activity);

       /* Toast.makeText(activity,"hyid:"+hyId,Toast.LENGTH_LONG).show();*/
            showNewEditBaoJiaDialog();
        }
        public void showNewEditBaoJiaDialog() {
            if (neweditBaoJiaDialog != null && !neweditBaoJiaDialog.isShowing())
                neweditBaoJiaDialog.show();
        }

        public void dissmissNewEditBaoJiaDialog() {
            if (neweditBaoJiaDialog != null && neweditBaoJiaDialog.isShowing())
                neweditBaoJiaDialog.dismiss();
        }
        @BindView(R.id.rciv_new_huoyuanlist_touxiang)
        RoundCornerImageView rcivNewHuoYuanListTouXiang;

        @BindView(R.id.iv_new_huoyuanlist_cal)
        ImageView ivNewHuoYuanListCal;
        @OnClick(R.id.iv_new_huoyuanlist_cal)
        public void ivNewHuoYuanListCalOnclick(){
            String tel = huoYuanList.get(pos).getIphone();
            if((tel == null)||(tel.isEmpty())){
                return;
            }
            callTelDialog = new CallTelDialog(activity,tel).Build.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dissmissDialog();
                }
            }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dissmissDialog();
                }
            }).setCallBackListener(new CallTelDialog.DialogCallBackListener() {
                @Override
                public void callBack(String tel) {
                    startCallTel(tel);
                }
            }).build(activity);
            showDialog();
        }
        public void showDialog() {
            if (callTelDialog != null && !callTelDialog.isShowing())
                callTelDialog.show();
        }

        public void dissmissDialog() {
            if (callTelDialog != null && callTelDialog.isShowing())
                callTelDialog.dismiss();
        }
        private void startCallTel(String number) {
        /*PhoneFormatCheckUtils phoneFormatCheckUtils = new PhoneFormatCheckUtils();
        if((number != null)&&(phoneFormatCheckUtils.IsNumber(number))) {*/
            //用intent启动拨打电话
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));

            activity.startActivity(intent);
       /* }*/
        }

        @BindView(R.id.lly_new_huoyuanlist_item)
        LinearLayout llyNewHuoYuanListItem;
        @OnClick(R.id.lly_new_huoyuanlist_item)
        public void llyNewHuoYuanListItemOnclick(){
            XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
            XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
            String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
            if((loginId == null)||(loginId.isEmpty())){

                Intent intent = new Intent(activity, NewHuoYuanDetailOtherActivity.class);
                intent.putExtra("hyid",huoYuanList.get(pos).getId());
                activity.startActivity(intent);
                return;
            }
            String ddzt = huoYuanList.get(pos).getDingdanzt();
            if(ddzt == null){
                ddzt = "";
            }
            if(loginId.equals(huoYuanList.get(pos).getLogin_id())){

                Intent intent = new Intent(activity, NewHuoYuanDetailSelfActivity.class);
                intent.putExtra("hyid",huoYuanList.get(pos).getId());
                intent.putExtra("status",ddzt);
                activity.startActivity(intent);
                return;
            }else{
                Intent intent = new Intent(activity, NewHuoYuanDetailOtherActivity.class);
                intent.putExtra("hyid",huoYuanList.get(pos).getId());
                intent.putExtra("status",ddzt);
                activity.startActivity(intent);
                return;
            }
        }

        public MyItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
