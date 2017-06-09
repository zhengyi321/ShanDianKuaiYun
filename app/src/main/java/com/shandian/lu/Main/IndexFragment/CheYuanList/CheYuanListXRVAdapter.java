package com.shandian.lu.Main.IndexFragment.CheYuanList;

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
import android.widget.TextView;

import com.baidu.mapapi.map.Text;
import com.example.mynewslayoutlib.Bean.NewCheYuanListBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.Main.IndexFragment.NewCheYuanDetail.NewCheYuanDetaiSelflActivity;
import com.shandian.lu.Main.IndexFragment.ZhengCheHuoYun.ZhengCheHuoYunXRVAdapter;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.CallTelDialog;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ImageView.RoundImageView;

import java.util.List;
import java.util.zip.Inflater;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/9.
 */

public class CheYuanListXRVAdapter extends RecyclerView.Adapter<CheYuanListXRVAdapter.MyItemHolder> {

    private Activity activity;
    private List<NewCheYuanListBean.NrBean.ListBean>cheYuanList;
    private LayoutInflater inflater;
    public CheYuanListXRVAdapter(Activity activity1, List<NewCheYuanListBean.NrBean.ListBean> cheYuanList1){
        activity = activity1;
        cheYuanList = cheYuanList1;
        inflater = LayoutInflater.from(activity1);
    }

    @Override
    public MyItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyItemHolder(inflater.inflate(R.layout.activity_new_cheyuanlist_content_xrv_item_lly,parent,false));
    }

    public void setAdapter(List<NewCheYuanListBean.NrBean.ListBean> cheYuanList1){
        cheYuanList.clear();
        cheYuanList.addAll(cheYuanList1);
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(MyItemHolder holder, int position) {
        holder.pos = position;
        holder.tvNewCheYuanListTime.setText(cheYuanList.get(position).getTime());
        holder.tvNewCheYuanListBCity.setText(cheYuanList.get(position).getCfshi());
        holder.tvNewCheYuanListBArea.setText(cheYuanList.get(position).getCfqu());
        holder.tvNewCheyuanListECity.setText(cheYuanList.get(position).getDashi());
        holder.tvNewCheYuanListEArea.setText(cheYuanList.get(position).getDaqu());
        holder.tvNewCheYuanListCarType.setText(cheYuanList.get(position).getCar_type());
        holder.tvNewCheYuanListDis.setText(cheYuanList.get(position).getJuli()+" km");
        holder.tvNewCheYuanListName.setText(cheYuanList.get(position).getNicheng());
        ImageLoader.getInstance().displayImage(cheYuanList.get(position).getTouxiang(),holder.rivNewCheYuanListTouXiang, ImageLoaderUtils.options1);
        if(cheYuanList.get(position).getGg() == null){
            return;
        }
        if(cheYuanList.get(position).getGg().equals("0")) {
            holder.tvNewCheYuanListAds.setVisibility(View.GONE);
        }else{
            holder.tvNewCheYuanListAds.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return cheYuanList.size();
    }


    public class MyItemHolder extends RecyclerView.ViewHolder {
        int pos = 0;
        CallTelDialog callTelDialog;
        @BindView(R.id.tv_new_cheyuanlist_time)
        TextView tvNewCheYuanListTime;
        @BindView(R.id.tv_new_cheyuanlist_bcity)
        TextView tvNewCheYuanListBCity;
        @BindView(R.id.tv_new_cheyuanlist_barea)
        TextView tvNewCheYuanListBArea;
        @BindView(R.id.tv_new_cheyuanlist_ecity)
        TextView tvNewCheyuanListECity;
        @BindView(R.id.tv_new_cheyuanlist_earea)
        TextView tvNewCheYuanListEArea;
        @BindView(R.id.tv_new_cheyuanlist_data)
        TextView tvNewCheYuanListData;
        @BindView(R.id.tv_new_cheyuanlist_cartype)
        TextView tvNewCheYuanListCarType;
        @BindView(R.id.tv_new_cheyuanlist_dis)
        TextView tvNewCheYuanListDis;
        @BindView(R.id.tv_new_cheyuanlist_ads)
        TextView tvNewCheYuanListAds;
        @BindView(R.id.riv_new_cheyuanlist_touxiang)
        RoundImageView rivNewCheYuanListTouXiang;
        @BindView(R.id.tv_new_cheyuanlist_name)
        TextView tvNewCheYuanListName;
        @BindView(R.id.iv_new_cheyuanlist_cal)
        ImageView ivNewCheYuanListCal;

        @OnClick(R.id.iv_new_cheyuanlist_cal)
        public void ivNewCheYuanListCalOnclick(){
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

        @BindView(R.id.lly_new_cheyuanlist_item)
        LinearLayout llyNewCheYuanListItem;
        @OnClick(R.id.lly_new_cheyuanlist_item)
        public void llyNewCheYuanListItemOnclick(){
            XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
            XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
            String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
            if((loginId == null)||(loginId.isEmpty())){
                Intent intent = new Intent(activity, LoginActivity.class);
                activity.startActivity(intent);
                activity.finish();
            }
            if(loginId.equals(cheYuanList.get(pos).getLogin_id())){
                Intent intent = new Intent(activity, NewCheYuanDetaiSelflActivity.class);
                intent.putExtra("cyid",cheYuanList.get(pos).getId());
                activity.startActivity(intent);
                activity.finish();
            }
        }

        public MyItemHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
