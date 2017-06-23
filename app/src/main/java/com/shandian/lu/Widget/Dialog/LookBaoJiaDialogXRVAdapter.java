package com.shandian.lu.Widget.Dialog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.NewBaoJiaListBean;
import com.shandian.lu.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/11.
 */

public class LookBaoJiaDialogXRVAdapter extends RecyclerView.Adapter<LookBaoJiaDialogXRVAdapter.MyItemViewHolder> {


    private List<NewBaoJiaListBean.NrBean.ListBean> dataList;
    private Context context;
    private String hyId;
    private LayoutInflater inflater;
   String baojiaId;
    public LookBaoJiaDialogXRVAdapter(Context context1, List<NewBaoJiaListBean.NrBean.ListBean> dataList1){
        context = context1;
        dataList = dataList1;
        inflater = LayoutInflater.from(context1);
    }

    public void setAdapter(List<NewBaoJiaListBean.NrBean.ListBean> dataList1,String hyId1,String baojiaId1){
        dataList.clear();
        dataList.addAll(dataList1);
        notifyDataSetChanged();
        hyId = hyId1;
        baojiaId = baojiaId1;
    }

    @Override
    public MyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyItemViewHolder(inflater.inflate(R.layout.dialog_new_huoyuanxiangqing_self_baojia_xrv_item_rly,parent,false));
    }

    @Override
    public void onBindViewHolder(MyItemViewHolder holder, int position) {
        holder.pos = position;
        int data = position % 2;
        if(data == 0){
            holder.llyNewHYXQBJXRVItem.setBackgroundColor(context.getResources().getColor(R.color.white));

        }else{
            holder.llyNewHYXQBJXRVItem.setBackgroundResource(R.drawable.pink_half_radius_corner_shape);
        }
        int count = position + 1;
        holder.tvNewHYXQSelfBaoJiaXRVItemName.setText(count+"."+dataList.get(position).getName());
        holder.tvNewHYXQSelfBaoJiaXRVItemPrice.setText(dataList.get(position).getJiage()+"元");

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public class MyItemViewHolder extends RecyclerView.ViewHolder{
        int pos = 0;
        AgreeBaoJiaDialog agreeBaoJiaDialog;
        @BindView(R.id.tv_new_hyxq_self_baojia_xrv_item_name)
        TextView tvNewHYXQSelfBaoJiaXRVItemName;
        @BindView(R.id.tv_new_hyxq_self_baojia_xrv_item_price)
        TextView tvNewHYXQSelfBaoJiaXRVItemPrice;
        @BindView(R.id.tv_new_hyxq_self_baojia_xrv_item_dis)
        TextView tvNewHYXQSelfBaoJiaXRVItemDis;
        @BindView(R.id.lly_new_hyxq_bj_xrv_item)
        LinearLayout llyNewHYXQBJXRVItem;
        @OnClick(R.id.lly_new_hyxq_bj_xrv_item)
        public void llyNewHYXQBJXRVItemOnclick(){

            agreeBaoJiaDialog = new AgreeBaoJiaDialog(context,hyId,dataList.get(pos),baojiaId).Build.build(context);

            /* Toast.makeText(activity,"hyid:"+hyId,Toast.LENGTH_LONG).show();*/
            showDialog();
        }
    public void showDialog() {
        if (agreeBaoJiaDialog != null && !agreeBaoJiaDialog.isShowing())
            agreeBaoJiaDialog.show();
    }

    public void dissmissDialog() {
        if (agreeBaoJiaDialog != null && agreeBaoJiaDialog.isShowing())
            agreeBaoJiaDialog.dismiss();
    }
        public MyItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
