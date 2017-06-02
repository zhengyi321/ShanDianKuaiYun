package com.shandian.lu.Main.IndexFragment.RenRenWuLiu;

import android.content.Context;
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

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.CallTelDialog;
import com.shandian.lu.Main.IndexFragment.CommonXRVDetail.CarSourceXRVDetailActivity;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.ZhuanXianWuliuCarSourceBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils.options;

/**
 * Created by az on 2017/4/26.
 */

public class RenRenWuLiuXRVAdapter extends RecyclerView.Adapter<RenRenWuLiuXRVAdapter.ItemViewHolder>  {

    private CallTelDialog callTelDialog;
    private List<ZhuanXianWuliuCarSourceBean.ContentBean> stringList;
    private Context context;
    private LayoutInflater inflater;
    public RenRenWuLiuXRVAdapter(Context context1, List<ZhuanXianWuliuCarSourceBean.ContentBean> stringList1){
        stringList = stringList1;
        context = context1;
        inflater = LayoutInflater.from(context1);
    }
    public void setAdapter(List<ZhuanXianWuliuCarSourceBean.ContentBean> contentBeen){
        stringList.clear();
        stringList.addAll(contentBeen);
        notifyDataSetChanged();
    }
    public void clean(){
        stringList.clear();
        notifyDataSetChanged();
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflater.inflate(R.layout.activity_main_index_renrenwuliu_content_xrv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.pos = position;
        holder.tvMainIndexRenRenWuLiuContentXRVProvince.setText(stringList.get(position).getCar_title());
        holder.tvMainIndexRenRenWuLiuContentXRVRoadPlane.setText(stringList.get(position).getAddress_set()+"-"+stringList.get(position).getAddress_out());
        holder.tvMainIndexRenRenWuLiuContentXRVTime.setText(stringList.get(position).getCreate_time());
        ImageLoader.getInstance().displayImage(stringList.get(position).getImg1(),holder.ivMainIndexRenRenWuLiuContentXRVItem,options);
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    private void contactTel(String tel1) {
        if((tel1 == null)||(tel1.isEmpty())){
            return;
        }
        callTelDialog = new CallTelDialog(context,tel1).Build.setNegativeButton("取消", new DialogInterface.OnClickListener() {
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
        }).build(context);
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

        context.startActivity(intent);
       /* }*/
    }
    public class ItemViewHolder extends RecyclerView.ViewHolder{
        int pos = 0;
        @BindView(R.id.rly_main_index_renrenwuliu_content_xrv_call)
        RelativeLayout rlyMainIndexRenRenWuLiuContentXRVCall;
        @OnClick(R.id.rly_main_index_renrenwuliu_content_xrv_call)
        public void rlyMainIndexRenRenWuLiuContentXRVCallOnclick(){
            contactTel(stringList.get(pos).getIphone());
        }
        @BindView(R.id.tv_main_index_renrenwuliu_content_xrv_province)
        TextView tvMainIndexRenRenWuLiuContentXRVProvince;
        @BindView(R.id.tv_main_index_renrenwuliu_content_xrv_roadplane)
        TextView tvMainIndexRenRenWuLiuContentXRVRoadPlane;
        @BindView(R.id.tv_main_index_renrenwuliu_content_xrv_time)
        TextView tvMainIndexRenRenWuLiuContentXRVTime;
        @BindView(R.id.iv_main_index_renrenwuliu_content_xrv_item)
        ImageView ivMainIndexRenRenWuLiuContentXRVItem;
        @BindView(R.id.lly_main_index_renrenwuliu_xrv_item)
        LinearLayout llyMainIndexRenRenWuLiuXRVItem;
        @OnClick(R.id.lly_main_index_renrenwuliu_xrv_item)
        public void llyMainIndexRenRenWuLiuXRVItemOnclick(){
            Intent intent = new Intent(context, CarSourceXRVDetailActivity.class);
            intent.putExtra("id",""+stringList.get(pos).getId());
            context.startActivity(intent);
        }

        public ItemViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
