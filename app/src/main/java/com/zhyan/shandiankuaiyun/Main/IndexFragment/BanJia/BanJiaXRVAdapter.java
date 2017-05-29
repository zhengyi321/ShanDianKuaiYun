package com.zhyan.shandiankuaiyun.Main.IndexFragment.BanJia;

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
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.CallTelDialog;
import com.zhyan.shandiankuaiyuanwidgetlib.Dialog.LianXiKeFuDialog;
import com.zhyan.shandiankuaiyun.Main.IndexFragment.CommonXRVDetail.CarSourceXRVDetailActivity;
import com.zhyan.shandiankuaiyun.R;
import com.zhyan.shandiankuaiyunlib.Bean.CarSourceSelectBean;
import com.zhyan.shandiankuaiyunlib.Bean.HomeMovingBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils.options;

/**
 * Created by az on 2017/4/26.
 */

public class BanJiaXRVAdapter extends RecyclerView.Adapter<BanJiaXRVAdapter.ItemViewHolder>  {

    private CallTelDialog callTelDialog;
    private List<HomeMovingBean.ContentBean.CarInfoBean> stringList;
    private Context context;
    private LayoutInflater inflater;
    public BanJiaXRVAdapter(Context context1, List<HomeMovingBean.ContentBean.CarInfoBean> stringList1){
        stringList = stringList1;
        context = context1;
        inflater = LayoutInflater.from(context1);
    }
    public void setAdapter(List<HomeMovingBean.ContentBean.CarInfoBean> contentBeen){
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
        return new ItemViewHolder(inflater.inflate(R.layout.activity_main_index_banjia_content_xrv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        /*Toast.makeText(context,"this is banjiaadapter pos:"+position,Toast.LENGTH_LONG).show();*/
        holder.pos = position;
        if(stringList.size() <= 0){
            return;
        }
        if(stringList.get(position) == null){
            return;
        }


        if(stringList.get(position).getType_name() != null){
            holder.tvMainIndexBanJiaContentXRVProvince.setText(stringList.get(position).getCar_title());
        }
        if((stringList.get(position).getAddress_set() != null)&&(stringList.get(position).getAddress_out() != null)) {
            holder.tvMainIndexBanJiaContentXRVRoadPlane.setText(stringList.get(position).getAddress_set() + "-" + stringList.get(position).getAddress_out());
        }
        if(stringList.get(position).getCreate_time() != null) {
            holder.tvMainIndexBanJiaContentXRVTime.setText(stringList.get(position).getCreate_time());
        }
        ImageLoader.getInstance().displayImage(stringList.get(position).getImg1(),holder.ivMainIndexBanJiaContentXRVItem,options);
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
        @BindView(R.id.rly_main_index_banjia_content_xrv_call)
        RelativeLayout rlyMainIndexBanJiaContentXRVCall;
        @OnClick(R.id.rly_main_index_banjia_content_xrv_call)
        public void rlyMainIndexBanJiaContentXRVCallOnclick(){
            contactTel(stringList.get(pos).getIphone());
        }
        @BindView(R.id.tv_main_index_banjia_content_xrv_province)
        TextView tvMainIndexBanJiaContentXRVProvince;
        @BindView(R.id.tv_main_index_banjia_content_xrv_roadplane)
        TextView tvMainIndexBanJiaContentXRVRoadPlane;
        @BindView(R.id.tv_main_index_banjia_content_xrv_time)
        TextView tvMainIndexBanJiaContentXRVTime;
        @BindView(R.id.iv_main_index_banjia_content_xrv_item)
        ImageView ivMainIndexBanJiaContentXRVItem;
        @BindView(R.id.lly_main_index_banjia_xrv_item)
        LinearLayout llyMainIndexBanJiaXRVItem;
        @OnClick(R.id.lly_main_index_banjia_xrv_item)
        public void llyMainIndexBanJiaXRVItemOnclick(){
            Intent intent = new Intent(context, CarSourceXRVDetailActivity.class);
    /*        Toast.makeText(context,"this is llyMainIndexBanJiaXRVItemOnclick:"+pos,Toast.LENGTH_LONG).show();*/
            if(stringList.get(pos) == null){
                return;
            }
            if(stringList.get(pos).getId() == null){
                return;
            }
           /* Toast.makeText(context,"id"+stringList.get(pos).getId(),Toast.LENGTH_LONG).show();*/
            intent.putExtra("id",""+stringList.get(pos).getId());
            context.startActivity(intent);
        }

        public ItemViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
