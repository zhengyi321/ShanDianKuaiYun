package com.shandian.lu.Main.ReleaseFragment.ZhuanXianWuLiu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.ImageFloderBean;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/4/26.
 */

public class SelectPicXRVAdapter extends RecyclerView.Adapter<SelectPicXRVAdapter.ItemViewHolder>  {

    private ImageFloderBean stringList;
    private Context context;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;
    private OnSelectImageListener onSelectImageListener;
    /**
     * 已选择的图片
     */
    private ArrayList<String> selectedPicture = new ArrayList<String>();
    public SelectPicXRVAdapter(Context context1, ImageFloderBean  stringList1){
        stringList = stringList1;

        context = context1;
        inflater = LayoutInflater.from(context1);
        imageLoader = ImageLoader.getInstance();
    }
    public void setAdapter(ImageFloderBean hotProvinceBeanList){
        if((stringList.images != null)&&(hotProvinceBeanList.images != null)) {
            stringList.images.clear();
            stringList.images.addAll(hotProvinceBeanList.images);
            notifyDataSetChanged();
        }
    }
    public void setSelectedPicture(ArrayList<String> selectedPicture) {
        this.selectedPicture = selectedPicture;
        notifyDataSetChanged();
    }
    public void setOnSelectImageListener(
            OnSelectImageListener onSelectImageListener) {
        this.onSelectImageListener = onSelectImageListener;
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemViewHolder(inflater.inflate(R.layout.activity_main_release_zhuanxianwuliu_select_photo_rv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {

            holder.pos = position;
       /* imageLoader.displayImage(holder.ivMainMineWoDeHuoYuan,stringList.getContent().get(position).getImg1());*/
    }

    @Override
    public int getItemCount() {
        return stringList.images.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        int pos = 0;

        public ItemViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
    public interface OnSelectImageListener {

        void onSelect(int num, ArrayList<String> selectedPicture);

    }
}
