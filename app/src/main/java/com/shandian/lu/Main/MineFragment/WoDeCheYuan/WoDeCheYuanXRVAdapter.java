package com.shandian.lu.Main.MineFragment.WoDeCheYuan;

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
import com.shandian.lu.Main.IndexFragment.CommonXRVDetail.CarSourceXRVDetailActivity;
import com.shandian.lu.NetWork.AboutCarSourceNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.MyCarSourceBean;
import com.zhyan.shandiankuaiyunlib.Bean.MyCarSourcesDeleteResultBean;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by az on 2017/4/26.
 */

public class WoDeCheYuanXRVAdapter extends RecyclerView.Adapter<WoDeCheYuanXRVAdapter.ItemViewHolder>  {

    private MyCarSourceBean stringList;
    private Context context;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;
    public WoDeCheYuanXRVAdapter(Context context1, MyCarSourceBean stringList1){
        stringList = stringList1;
        if (stringList.getContent() == null){
            stringList.setContent(new ArrayList<MyCarSourceBean.ContentBean>());
        }
        imageLoader = ImageLoader.getInstance();
        context = context1;
        inflater = LayoutInflater.from(context1);
    }
    public void setAdapter(MyCarSourceBean myCarSourceBean){

        stringList = myCarSourceBean;
        notifyDataSetChanged();

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflater.inflate(R.layout.activity_main_mine_wodecheyuan_content_xrv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        imageLoader.displayImage(stringList.getContent().get(position).getImg1(),holder.ivMainMineWoDeHuoYuan, ImageLoaderUtils.options1);
        holder.tvMainMineWoDeCheYuanName.setText(stringList.getContent().get(position).getAddress_set()+" - "+stringList.getContent().get(position).getAddress_out());
        holder.tvMainMineWoDeCheYuanTime.setText(stringList.getContent().get(position).getCreate_time());
        holder.tvMainMineWoDeCheYuanTimes.setText(stringList.getContent().get(position).getNum());
        holder.pos = position;
    }

    @Override
    public int getItemCount() {
        if(stringList.getContent() != null) {
            return stringList.getContent().size();
        }else {
            return 0;
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{

        int pos = 0;


        @BindView(R.id.lly_main_mine_wodecheyuan_xrv_item)
        LinearLayout llyMainMineWoDeCheYuanXRVItem;
        @OnClick(R.id.lly_main_mine_wodecheyuan_xrv_item)
        public void llyMainMineWoDeCheYuanXRVItemOnclick(){
            Intent intent = new Intent(context, CarSourceXRVDetailActivity.class);
            intent.putExtra("id",""+stringList.getContent().get(pos).getId());
            context.startActivity(intent);
        }
        @BindView(R.id.iv_main_mine_wodecheyuan)
        ImageView ivMainMineWoDeHuoYuan;
        @BindView(R.id.tv_main_mine_wodecheyuan_name)
        TextView tvMainMineWoDeCheYuanName;
        @BindView(R.id.tv_main_mine_wodecheyuan_time)
        TextView tvMainMineWoDeCheYuanTime;
        @BindView(R.id.tv_main_mine_wodecheyuan_times)
        TextView tvMainMineWoDeCheYuanTimes;

        @BindView(R.id.bt_main_mine_wodecheyuan_select)
        Button btMainMineWoDeCheYuanSelect;
        @OnClick(R.id.bt_main_mine_wodecheyuan_select)
        public void btMainMineWoDeCheYuanSelectOnclick(){
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
            AboutCarSourceNetWork aboutCarSourceNetWork = new AboutCarSourceNetWork();
            aboutCarSourceNetWork.deleteCarResourceToNet(id, new Observer<MyCarSourcesDeleteResultBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(MyCarSourcesDeleteResultBean myCarSourcesDeleteResultBean) {
                    Toast.makeText(context,myCarSourcesDeleteResultBean.getMsg(),Toast.LENGTH_LONG).show();
                    if(myCarSourcesDeleteResultBean.getStatus() == 0) {
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
