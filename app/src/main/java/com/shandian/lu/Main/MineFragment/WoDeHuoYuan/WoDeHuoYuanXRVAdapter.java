package com.shandian.lu.Main.MineFragment.WoDeHuoYuan;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.zhyan.shandiankuaiyuanwidgetlib.AlertView.AlertView;
import com.zhyan.shandiankuaiyuanwidgetlib.AlertView.OnItemClickListener;
import com.shandian.lu.Main.IndexFragment.CommonXRVDetail.GoodsSourceXRVDetailActivity;
import com.shandian.lu.NetWork.AboutGoodsSourceNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.MyGoodsSourcesDeleteResultBean;
import com.zhyan.shandiankuaiyunlib.Bean.MyHuoSourceBean;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by az on 2017/4/26.
 */

public class WoDeHuoYuanXRVAdapter extends RecyclerView.Adapter<WoDeHuoYuanXRVAdapter.ItemViewHolder>  {

    private MyHuoSourceBean stringList;
    private Context context;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;
    public WoDeHuoYuanXRVAdapter(Context context1, MyHuoSourceBean stringList1){
        stringList = stringList1;
        if(stringList.getContent() == null){
            stringList.setContent(new ArrayList<MyHuoSourceBean.ContentBean>());
        }
        context = context1;
        inflater = LayoutInflater.from(context1);
        imageLoader = ImageLoader.getInstance();
    }
    public void setAdapter(MyHuoSourceBean myHuoSourceBeen){
        stringList = myHuoSourceBeen;
        notifyDataSetChanged();
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflater.inflate(R.layout.activity_main_mine_wodehuoyuan_content_xrv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        imageLoader.displayImage(stringList.getContent().get(position).getImg1(),holder.ivMainMineWoDeHuoYuan, ImageLoaderUtils.options1);
        holder.tvMainMineWoDeHuoYuanName.setText(stringList.getContent().get(position).getAddress_set()+" - "+stringList.getContent().get(position).getAddress_out());
        holder.tvMainMineWoDeHuoYuanTime.setText(stringList.getContent().get(position).getCreate_time());
        holder.tvMainMineWoDeHuoYuanTimes.setText(stringList.getContent().get(position).getNum());
        holder.pos = position;
       /* imageLoader.displayImage(holder.ivMainMineWoDeHuoYuan,stringList.getContent().get(position).getImg1());*/
    }

    @Override
    public int getItemCount() {
        if(stringList.getContent() != null) {
            return stringList.getContent().size();
        }else {
            return 0;
        }
       /* return stringList.getContent().size();*/
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        int pos = 0;
        @BindView(R.id.lly_main_mine_wodehuoyuan_xrv_item)
        LinearLayout llyMainMineWoDeHuoYunXRVItem;
        @OnClick(R.id.lly_main_mine_wodehuoyuan_xrv_item)
        public void llyMainMineWoDeHuoYunXRVItemOnclick(){
            Intent intent = new Intent(context, GoodsSourceXRVDetailActivity.class);
            intent.putExtra("id",""+stringList.getContent().get(pos).getId());
            context.startActivity(intent);
        }
        @BindView(R.id.iv_main_mine_wodehuoyuan)
        ImageView ivMainMineWoDeHuoYuan;
        @BindView(R.id.tv_main_mine_wodehuoyuan_name)
        TextView tvMainMineWoDeHuoYuanName;

        @BindView(R.id.tv_main_mine_wodehuoyuan_time)
        TextView tvMainMineWoDeHuoYuanTime;
        @BindView(R.id.tv_main_mine_wodehuoyuan_times)
        TextView tvMainMineWoDeHuoYuanTimes;
        @BindView(R.id.bt_main_mine_wodehuoyuan_select)
        Button btMainMineWoDeHuoYuanSelect;
        @OnClick(R.id.bt_main_mine_wodehuoyuan_select)
        public void btMainMineWoDeHuoYuanSelectOnclick(){
            //从相册选择头像代码
            new AlertView.Builder().setContext(context)
                    .setStyle(AlertView.Style.ActionSheet)
                 /*   .setTitle("选择头像")*/
                    .setMessage(null)
                    .setCancelText("取消")
                    .setDestructive("删除信息")
                    .setDestructive1("编辑信息")
                    .setOthers(null)
                    .setOnItemClickListener(new OnItemClickListener() {
                        @Override
                        public void onItemClick(Object o, int position) {
                        /*Toast.makeText(getBaseContext(),"pos"+position,Toast.LENGTH_SHORT).show();*/
                            switch (position){
                                case 0:
                                    deleteData();
                                    break;
                                case 1:

                                    break;
                                case -1:
                                    break;
                            }
                        }
                    }).build()
                    .show();
        }
        private void deleteData(){
            String id = stringList.getContent().get(pos).getId();
            AboutGoodsSourceNetWork aboutGoodsSourceNetWork = new AboutGoodsSourceNetWork();
            aboutGoodsSourceNetWork.deleteGoodsResourceToNet(id, new Observer<MyGoodsSourcesDeleteResultBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(MyGoodsSourcesDeleteResultBean myGoodsSourcesDeleteResultBean) {
                    Toast.makeText(context,myGoodsSourcesDeleteResultBean.getMsg(),Toast.LENGTH_LONG).show();
                    if(myGoodsSourcesDeleteResultBean.getStatus() == 0) {
                        stringList.getContent().remove(stringList.getContent().get(pos));
                        notifyDataSetChanged();
                    }
                }
            });
        }
        public ItemViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
