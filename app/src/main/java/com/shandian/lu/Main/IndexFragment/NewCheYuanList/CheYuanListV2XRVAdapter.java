package com.shandian.lu.Main.IndexFragment.NewCheYuanList;

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

import com.example.mynewslayoutlib.Bean.NewCheYuanListBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.Main.IndexFragment.NewAdsDetail.NewAdsDetailActivity;
import com.shandian.lu.Main.IndexFragment.NewBanJiaRenRenKuaiDi.NewBanJiaRenRenDetailActivity;
import com.shandian.lu.Main.IndexFragment.NewCheYuanDetail.NewCheYuanDetailOtherActivity;
import com.shandian.lu.Main.IndexFragment.NewCheYuanDetail.NewCheYuanDetailSelfActivity;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.CallTelDialog;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ImageView.RoundCornerImageView.RoundCornerImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static cz.msebera.android.httpclient.client.methods.RequestBuilder.put;

/**
 * Created by Administrator on 2017/6/9.
 */

public class CheYuanListV2XRVAdapter extends RecyclerView.Adapter<CheYuanListV2XRVAdapter.MyItemHolder> {

    private Activity activity;
    public List<NewCheYuanListBean.NrBean.ListBean> cheYuanList;
    private LayoutInflater inflater;
    private String img,imgUrl;
    public CheYuanListV2XRVAdapter(Activity activity1, List<NewCheYuanListBean.NrBean.ListBean> cheYuanList1){
        activity = activity1;
        cheYuanList = cheYuanList1;
        inflater = LayoutInflater.from(activity1);
    }

    @Override
    public MyItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyItemHolder(inflater.inflate(R.layout.activity_new_cheyuanlist_content_xrv_item_v2_lly,parent,false));
    }

    public void setAdapter(List<NewCheYuanListBean.NrBean.ListBean> cheYuanList1){

        cheYuanList.addAll(cheYuanList1);
        notifyDataSetChanged();
    }
    public void setImgAndUrl(String img1,String imgUrl1){
        img = img1;
        imgUrl = imgUrl1;
        if(img == null){
            img = "";
        }
        if(imgUrl == null){
            imgUrl = "";
        }
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(MyItemHolder holder, int position) {
        holder.pos = position;
        if(position == 0){


            holder.rlyNewCheYuanListXRVItemAds.setVisibility(View.GONE);
        }else {
            if (position % 6 == 0) {

                holder.rlyNewCheYuanListXRVItemAds.setVisibility(View.VISIBLE);
                ImageLoader.getInstance().displayImage(img,holder.ivNewCheYuanListXRVItemAds,ImageLoaderUtils.options1);
            } else {

                holder.rlyNewCheYuanListXRVItemAds.setVisibility(View.GONE);
            }
        }
        holder.tvNewCheYuanListXRVItemCZName.setText(cheYuanList.get(position).getNicheng());
        holder.tvNewCheYuanListXRVItemTime.setText(cheYuanList.get(position).getTime());
        holder.tvNewCheYuanListXRVItemBCityArea.setText(cheYuanList.get(position).getCfshi()+cheYuanList.get(position).getCfqu());

        holder.tvNewCheYuanListXRVItemECityArea.setText(cheYuanList.get(position).getDashi()+cheYuanList.get(position).getDaqu());
        holder.tvNewCheYuanListXRVItemCarType.setText(cheYuanList.get(position).getCar_type());
        holder.tvNewCheYuanListXRVItemCarLength.setText(cheYuanList.get(position).getCar_lange());
        String typeName = cheYuanList.get(position).getType_name();
        if(typeName.equals("5")||typeName.equals("6")){
            holder.ivNewCheYuanListArrowSplite.setVisibility(View.GONE);
        }
        ImageLoader.getInstance().displayImage(cheYuanList.get(position).getTouxiang(),holder.rcivNewCheYuanListXRVItemTouXiang, ImageLoaderUtils.options1);
        if(cheYuanList.get(position).getGg() == null){
            return;
        }
        String memberLevel = cheYuanList.get(position).getHydengji();
        switch (memberLevel){

            case "1":
                holder.ivNewCheYuanListXRVItemLevel1.setImageResource(R.mipmap.star);
                holder.ivNewCheYuanListXRVItemLevel2.setImageResource(R.mipmap.star2);
                holder.ivNewCheYuanListXRVItemLevel3.setImageResource(R.mipmap.star2);
                holder.ivNewCheYuanListXRVItemLevel4.setImageResource(R.mipmap.star2);
                holder.ivNewCheYuanListXRVItemLevel5.setImageResource(R.mipmap.star2);
                break;
            case "2":

                holder.ivNewCheYuanListXRVItemLevel1.setImageResource(R.mipmap.star);
                holder.ivNewCheYuanListXRVItemLevel2.setImageResource(R.mipmap.star);
                holder.ivNewCheYuanListXRVItemLevel3.setImageResource(R.mipmap.star2);
                holder.ivNewCheYuanListXRVItemLevel4.setImageResource(R.mipmap.star2);
                holder.ivNewCheYuanListXRVItemLevel5.setImageResource(R.mipmap.star2);
                break;
            case "3":

                holder.ivNewCheYuanListXRVItemLevel1.setImageResource(R.mipmap.star);
                holder.ivNewCheYuanListXRVItemLevel2.setImageResource(R.mipmap.star);
                holder.ivNewCheYuanListXRVItemLevel3.setImageResource(R.mipmap.star);
                holder.ivNewCheYuanListXRVItemLevel4.setImageResource(R.mipmap.star2);
                holder.ivNewCheYuanListXRVItemLevel5.setImageResource(R.mipmap.star2);
                break;
            case "4":
                holder.ivNewCheYuanListXRVItemLevel1.setImageResource(R.mipmap.star);
                holder.ivNewCheYuanListXRVItemLevel2.setImageResource(R.mipmap.star);
                holder.ivNewCheYuanListXRVItemLevel3.setImageResource(R.mipmap.star);
                holder.ivNewCheYuanListXRVItemLevel4.setImageResource(R.mipmap.star);
                holder.ivNewCheYuanListXRVItemLevel5.setImageResource(R.mipmap.star2);

                break;
            case "5":
                holder.ivNewCheYuanListXRVItemLevel1.setImageResource(R.mipmap.star);
                holder.ivNewCheYuanListXRVItemLevel2.setImageResource(R.mipmap.star);
                holder.ivNewCheYuanListXRVItemLevel3.setImageResource(R.mipmap.star);
                holder.ivNewCheYuanListXRVItemLevel4.setImageResource(R.mipmap.star);
                holder.ivNewCheYuanListXRVItemLevel5.setImageResource(R.mipmap.star);

                break;

        }


    }

    @Override
    public int getItemCount() {
        return cheYuanList.size();
    }


    public class MyItemHolder extends RecyclerView.ViewHolder {
        int pos = 0;
        CallTelDialog callTelDialog;

        @BindView(R.id.rly_new_cheyuanlist_xrv_item_ads)
        RelativeLayout rlyNewCheYuanListXRVItemAds;
        @BindView(R.id.tv_new_cheyuanlist_xrv_item_cz_name)
        TextView tvNewCheYuanListXRVItemCZName;
        @BindView(R.id.tv_new_cheyuanlist_xrv_item_time)
        TextView tvNewCheYuanListXRVItemTime;
        @BindView(R.id.tv_new_cheyuanlist_xrv_item_bcityarea)
        TextView tvNewCheYuanListXRVItemBCityArea;

        @BindView(R.id.tv_new_cheyuanlist_xrv_item_ecityarea)
        TextView tvNewCheYuanListXRVItemECityArea;


        @BindView(R.id.tv_new_cheyuanlist_xrv_item_cartype)
        TextView tvNewCheYuanListXRVItemCarType;
        @BindView(R.id.tv_new_cheyuanlist_xrv_item_carlength)
        TextView tvNewCheYuanListXRVItemCarLength;
        @BindView(R.id.iv_new_cheyuanlist_xrv_item_level1)
        ImageView ivNewCheYuanListXRVItemLevel1;
        @BindView(R.id.iv_new_cheyuanlist_xrv_item_level2)
        ImageView ivNewCheYuanListXRVItemLevel2;
        @BindView(R.id.iv_new_cheyuanlist_xrv_item_level3)
        ImageView ivNewCheYuanListXRVItemLevel3;
        @BindView(R.id.iv_new_cheyuanlist_xrv_item_level4)
        ImageView ivNewCheYuanListXRVItemLevel4;
        @BindView(R.id.iv_new_cheyuanlist_xrv_item_level5)
        ImageView ivNewCheYuanListXRVItemLevel5;

        @BindView(R.id.iv_new_cheyuanlist_xrv_item_ads)
        ImageView ivNewCheYuanListXRVItemAds;
        @OnClick(R.id.iv_new_cheyuanlist_xrv_item_ads)
        public void ivNewCheYuanListXRVItemAdsOnclick(){
            Intent intent = new Intent(activity, NewAdsDetailActivity.class);
            intent.putExtra("url",imgUrl);
            activity.startActivity(intent);
        }


        @BindView(R.id.rciv_new_cheyuanlist_xrv_item_touxiang)
        RoundCornerImageView rcivNewCheYuanListXRVItemTouXiang;

        @BindView(R.id.iv_new_cheyuanlist_arrow_splite)
        ImageView ivNewCheYuanListArrowSplite;
        @BindView(R.id.iv_new_cheyuanlist_xrv_item_cal)
        ImageView ivNewCheYuanListXRVItemCal;

        @OnClick(R.id.iv_new_cheyuanlist_xrv_item_cal)
        public void ivNewCheYuanListXRVItemCalOnclick(){
            String tel = cheYuanList.get(pos).getIphone();
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

        @BindView(R.id.lly_new_cheyuanlist_xrv_item_item)
        LinearLayout llyNewCheYuanListXRVItemItem;
        @OnClick(R.id.lly_new_cheyuanlist_xrv_item_item)
        public void llyNewCheYuanListXRVItemItemOnclick(){
            String typeName = cheYuanList.get(pos).getType_name();
            XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
            XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
            String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
            if(typeName.equals("5")||typeName.equals("6")){
                Intent intent = new Intent(activity, NewBanJiaRenRenDetailActivity.class);
                intent.putExtra("type_name",typeName);
                intent.putExtra("cyid",cheYuanList.get(pos).getId());
                intent.putExtra("czid",cheYuanList.get(pos).getLogin_id());
                /*System.out.print("\ncyid"+cheYuanList.get(pos).getId());
                System.out.print("\ncyid"+cheYuanList.get(pos).getId());
                System.out.print("\ncyid"+cheYuanList.get(pos).getId());
                System.out.print("\ncyid"+cheYuanList.get(pos).getId());*/
                activity.startActivity(intent);
                return;
            }
            if((loginId == null)||(loginId.isEmpty())){

                Intent intent = new Intent(activity, NewCheYuanDetailOtherActivity.class);
                intent.putExtra("type_name",typeName);
                intent.putExtra("cyid",cheYuanList.get(pos).getId());
                intent.putExtra("czid",cheYuanList.get(pos).getLogin_id());
                activity.startActivity(intent);
                return;
            }


            if(loginId.equals(cheYuanList.get(pos).getLogin_id())){
                Intent intent = new Intent(activity, NewCheYuanDetailSelfActivity.class);
                intent.putExtra("type_name",typeName);
                intent.putExtra("cyid",cheYuanList.get(pos).getId());
                intent.putExtra("czid",cheYuanList.get(pos).getLogin_id());
                activity.startActivity(intent);
                return;
            }else{
                Intent intent = new Intent(activity, NewCheYuanDetailOtherActivity.class);
                intent.putExtra("type_name",typeName);
                intent.putExtra("cyid",cheYuanList.get(pos).getId());
                intent.putExtra("czid",cheYuanList.get(pos).getLogin_id());
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
