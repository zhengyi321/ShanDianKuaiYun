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
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.NewBanJiaListBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.CallTelDialog;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ImageView.RoundCornerImageView.RoundCornerImageView;

import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/11.
 */

public class NewBanJiaListXRVAdapter extends RecyclerView.Adapter<NewBanJiaListXRVAdapter.MyItemViewHolder> {


    public LayoutInflater inflater;
    public List<NewBanJiaListBean.NrBean.ListBean> dataList;
    private Activity activity;
    private String img,imgUrl;
    public NewBanJiaListXRVAdapter(Activity activity1,List<NewBanJiaListBean.NrBean.ListBean> dataList1){
        activity = activity1;
        dataList = dataList1;
        inflater = LayoutInflater.from(activity1);
    }

    public void setAdapterDataList(List<NewBanJiaListBean.NrBean.ListBean> dataList1){
        if(dataList1.size() != 0){
            dataList.addAll(dataList1);
        }
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
    public MyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyItemViewHolder(inflater.inflate(R.layout.activity_new_banjialist_content_xrv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(MyItemViewHolder holder, int position) {
        holder.pos = position;
        if(position == 0){
            holder.rlyNewBanJiaListXRVItemAds.setVisibility(View.GONE);
        }else {
            if (position % 6 == 0) {
                holder.rlyNewBanJiaListXRVItemAds.setVisibility(View.VISIBLE);
                ImageLoader.getInstance().displayImage(img,holder.ivNewBanJiaListXRVItemAds, ImageLoaderUtils.options1);
            } else {
                holder.rlyNewBanJiaListXRVItemAds.setVisibility(View.GONE);
            }
        }
        holder.tvNewBanJiaListXRVItemCZName.setText(dataList.get(position).getNicheng());
        holder.tvNewBanJiaListXRVItemTime.setText(dataList.get(position).getTime());
        holder.tvNewBanJiaListXRVItemBCityArea.setText(dataList.get(position).getCfshi()+dataList.get(position).getCfqu());
        holder.tvNewBanJiaListXRVItemECityArea.setText(dataList.get(position).getDashi()+dataList.get(position).getDaqu());
        if(dataList.get(position).getBjcxixni() != null) {
            holder.tvNewBanJiaListXRVItemCarType.setText(dataList.get(position).getBjcxixni().size() + "种车型");
        }else {
            holder.tvNewBanJiaListXRVItemCarType.setText("0种车型");
        }
        holder.tvNewBanJiaListXRVItemCarLength.setText(dataList.get(position).getCar_lange());
        ImageLoader.getInstance().displayImage((String) dataList.get(position).getTouxiang(),holder.rcivNewBanJiaListXRVItemTouXiang, ImageLoaderUtils.options1);
        String memberLevel = dataList.get(position).getHydengji();
        switch (memberLevel){

            case "1":
                holder.ivNewBanJiaListXRVItemLevel1.setImageResource(R.mipmap.star);
                holder.ivNewBanJiaListXRVItemLevel2.setImageResource(R.mipmap.star2);
                holder.ivNewBanJiaListXRVItemLevel3.setImageResource(R.mipmap.star2);
                holder.ivNewBanJiaListXRVItemLevel4.setImageResource(R.mipmap.star2);
                holder.ivNewBanJiaListXRVItemLevel5.setImageResource(R.mipmap.star2);
                break;
            case "2":

                holder.ivNewBanJiaListXRVItemLevel1.setImageResource(R.mipmap.star);
                holder.ivNewBanJiaListXRVItemLevel2.setImageResource(R.mipmap.star);
                holder.ivNewBanJiaListXRVItemLevel3.setImageResource(R.mipmap.star2);
                holder.ivNewBanJiaListXRVItemLevel4.setImageResource(R.mipmap.star2);
                holder.ivNewBanJiaListXRVItemLevel5.setImageResource(R.mipmap.star2);
                break;
            case "3":

                holder.ivNewBanJiaListXRVItemLevel1.setImageResource(R.mipmap.star);
                holder.ivNewBanJiaListXRVItemLevel2.setImageResource(R.mipmap.star);
                holder.ivNewBanJiaListXRVItemLevel3.setImageResource(R.mipmap.star);
                holder.ivNewBanJiaListXRVItemLevel4.setImageResource(R.mipmap.star2);
                holder.ivNewBanJiaListXRVItemLevel5.setImageResource(R.mipmap.star2);
                break;
            case "4":
                holder.ivNewBanJiaListXRVItemLevel1.setImageResource(R.mipmap.star);
                holder.ivNewBanJiaListXRVItemLevel2.setImageResource(R.mipmap.star);
                holder.ivNewBanJiaListXRVItemLevel3.setImageResource(R.mipmap.star);
                holder.ivNewBanJiaListXRVItemLevel4.setImageResource(R.mipmap.star);
                holder.ivNewBanJiaListXRVItemLevel5.setImageResource(R.mipmap.star2);

                break;
            case "5":
                holder.ivNewBanJiaListXRVItemLevel1.setImageResource(R.mipmap.star);
                holder.ivNewBanJiaListXRVItemLevel2.setImageResource(R.mipmap.star);
                holder.ivNewBanJiaListXRVItemLevel3.setImageResource(R.mipmap.star);
                holder.ivNewBanJiaListXRVItemLevel4.setImageResource(R.mipmap.star);
                holder.ivNewBanJiaListXRVItemLevel5.setImageResource(R.mipmap.star);

                break;

        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyItemViewHolder extends RecyclerView.ViewHolder{


        int pos = 0;
        @BindView(R.id.tv_new_banjialist_xrv_item_cz_name)
        TextView tvNewBanJiaListXRVItemCZName;
        @BindView(R.id.tv_new_banjialist_xrv_item_time)
        TextView tvNewBanJiaListXRVItemTime;
        @BindView(R.id.tv_new_banjialist_xrv_item_bcityarea)
        TextView tvNewBanJiaListXRVItemBCityArea;
        @BindView(R.id.tv_new_banjialist_xrv_item_ecityarea)
        TextView tvNewBanJiaListXRVItemECityArea;
        @BindView(R.id.tv_new_banjialist_xrv_item_cartype)
        TextView tvNewBanJiaListXRVItemCarType;
        @BindView(R.id.tv_new_banjialist_xrv_item_carlength)
        TextView tvNewBanJiaListXRVItemCarLength;
        @BindView(R.id.iv_new_banjialist_xrv_item_level1)
        ImageView ivNewBanJiaListXRVItemLevel1;
        @BindView(R.id.iv_new_banjialist_xrv_item_level2)
        ImageView ivNewBanJiaListXRVItemLevel2;
        @BindView(R.id.iv_new_banjialist_xrv_item_level3)
        ImageView ivNewBanJiaListXRVItemLevel3;
        @BindView(R.id.iv_new_banjialist_xrv_item_level4)
        ImageView ivNewBanJiaListXRVItemLevel4;
        @BindView(R.id.iv_new_banjialist_xrv_item_level5)
        ImageView ivNewBanJiaListXRVItemLevel5;
        @BindView(R.id.rciv_new_banjialist_xrv_item_touxiang)
        RoundCornerImageView rcivNewBanJiaListXRVItemTouXiang;
        @BindView(R.id.rly_new_banjialist_xrv_item_ads)
        RelativeLayout rlyNewBanJiaListXRVItemAds;
        @BindView(R.id.iv_new_banjialist_xrv_item_ads)
        ImageView ivNewBanJiaListXRVItemAds;

        CallTelDialog callTelDialog;
        @BindView(R.id.iv_new_banjialist_xrv_item_cal)
        ImageView ivNewBanJiaListXRVItemCal;
        @OnClick(R.id.iv_new_banjialist_xrv_item_cal)
        public void ivNewBanJiaListXRVItemCalOnclick(){
            String tel = dataList.get(pos).getIphone();
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
        public MyItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
