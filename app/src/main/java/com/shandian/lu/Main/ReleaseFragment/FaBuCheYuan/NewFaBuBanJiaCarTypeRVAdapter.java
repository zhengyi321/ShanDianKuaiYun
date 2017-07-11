package com.shandian.lu.Main.ReleaseFragment.FaBuCheYuan;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynewslayoutlib.SwipeLayout;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.Main.AdviceFragment.MainAdviceXRVAdapter;
import com.shandian.lu.R;
import com.shandian.lu.Widget.Dialog.NewFaBuBanJiaAddCarTypeDialog;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.BitmapUtils;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ImageView.RoundCornerImageView.RoundCornerImageView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTouch;

import static java.lang.Math.abs;

/**
 * Created by Administrator on 2017/7/6.
 */

public class NewFaBuBanJiaCarTypeRVAdapter extends RecyclerView.Adapter<NewFaBuBanJiaCarTypeRVAdapter.MyItemViewHolder> {


    private LayoutInflater inflater;
    private Activity activity;
    private List<Object> carTypeList;
    private List<String> netImgList;
    private List<Object> carTypeFaBuList;

    public interface AdapterUpdateImageViewListener{
        public void updateRcivCallBack(RoundCornerImageView rcivImageView,ProgressBar rcivIvPB,int pos);//具体方法
    }
    public interface AdapterMsgCallBackListener{
        public void msgCallBack(String name,String tj,String zz);
    }
    private AdapterUpdateImageViewListener adapterUpdateImageViewListener;


    public void setAdapterUpdateImageViewCallBack(AdapterUpdateImageViewListener adapterUpdateImageViewListener1){
        adapterUpdateImageViewListener = adapterUpdateImageViewListener1;
    }

    public NewFaBuBanJiaCarTypeRVAdapter(Activity activity1, List<Object> carTypeList1){
        activity = activity1;
        carTypeList = carTypeList1;
        inflater = LayoutInflater.from(activity1);
        carTypeFaBuList = new ArrayList<>();
    }


    public void setAdapter(List<Object> carTypeList1, ArrayList<String> netImgList1){
        if((carTypeList1.size() == 0)&&(netImgList1.size() == 0)){
            return;
        }
        carTypeList.clear();
        carTypeList.addAll(carTypeList1);
        netImgList = netImgList1;

        notifyDataSetChanged();
    }

    public List<Object> getFaBuList(){
        return carTypeFaBuList;
    }

    @Override
    public MyItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyItemViewHolder(inflater.inflate(R.layout.activity_new_fabubanjia_content_rv_item_lly,parent,false));
    }

    @Override
    public void onBindViewHolder(MyItemViewHolder holder, int position) {
        Map<String,Object> paramMap = (Map<String,Object>)carTypeList.get(position);
        Bitmap img = (Bitmap) paramMap.get("img");
        if(img != null){
            holder.ivNewFaBuBanJiaRVItemHeadImg.setImageBitmap(img);
        }else {
            String imgUrl = netImgList.get(position);
            if ((imgUrl != null) && (!imgUrl.isEmpty())) {
                ImageLoader.getInstance().displayImage(imgUrl,holder.ivNewFaBuBanJiaRVItemHeadImg, ImageLoaderUtils.options1);
            }
        }
        String name = (String) paramMap.get("name");
        if((name == null)||(name.isEmpty())){
            return;
        }
        String tj = (String) paramMap.get("tj");
        if((tj == null)||(tj.isEmpty())){
            return;
        }
        String zz = (String) paramMap.get("zz");
        if((zz == null)||(zz.isEmpty())){
            return;
        }


        holder.tvNewFaBuBanJiaRVItemCarType.setText(name+"/"+tj+"m³/"+zz+"kg");
        holder.pos = position;
        setFaBuParam(position,name,tj,zz);
    }

    private void setFaBuParam(int pos,String name,String tj,String zz){
        String img = netImgList.get(pos);
        if((img == null)||(img.isEmpty())){
            return;
        }
        /*Map<String,Object> paramMap = new HashMap<>();*/
        JSONObject jsonObject = new JSONObject();
        try {
         /*   jsonObject.put("img","\""+img+"\"");*/
            jsonObject.put("img",img);
         /*   jsonObject.put("name","\""+name+"\"");*/
            jsonObject.put("name",name);
       /*     jsonObject.put("tj","\""+tj+"\"");*/
            jsonObject.put("tj",tj);
        /*    jsonObject.put("zz","\""+zz+"\"");*/
            jsonObject.put("zz",zz);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        carTypeFaBuList.add(jsonObject);
    }

    @Override
    public int getItemCount() {
        return carTypeList.size();
    }




    class MyItemViewHolder extends RecyclerView.ViewHolder{

        int pos = 0;
        NewFaBuBanJiaAddCarTypeDialog newFaBuBanJiaAddCarTypeDialog;
        @BindView(R.id.rly_new_fabubanjia_rv_item_edit)
        RelativeLayout rlyNewFaBuBanJiaRVItemEdit;
        @OnClick(R.id.rly_new_fabubanjia_rv_item_edit)
        public void rlyNewFaBuBanJiaRVItemEditOnclick(){
            Map<String,Object> paramMap = (Map<String,Object>)carTypeList.get(pos);
            String picPath = (String) paramMap.get("picPath");
            ArrayList<String> imgList = new ArrayList<>();
            if((picPath != null)&&(!picPath.isEmpty())){
                imgList.add(picPath);
            }
            Bitmap bitmap = (Bitmap) paramMap.get("img");
            String name = (String) paramMap.get("name");
            String tj = (String) paramMap.get("tj");
            String zz = (String) paramMap.get("zz");
            String imgUrl = netImgList.get(pos);
            newFaBuBanJiaAddCarTypeDialog = new NewFaBuBanJiaAddCarTypeDialog(activity,imgList,bitmap,imgUrl,name,tj,zz,pos,true).Build.setUpdateRoundCornerImageViewCallBackListener(new NewFaBuBanJiaAddCarTypeDialog.UpdateRoundCornerImageViewCallBackListener() {
                @Override
                public void updateRoundCornerImageViewCallBack(RoundCornerImageView rcivImageView, ProgressBar rcivIvPB, int pos) {
                    if(adapterUpdateImageViewListener != null){
                        adapterUpdateImageViewListener.updateRcivCallBack(rcivImageView,rcivIvPB,pos);
                    }
                }
            }).setMsgCallBackListener(new NewFaBuBanJiaAddCarTypeDialog.MsgCallBackListener() {
                @Override
                public void msgCallBack(String name, String tj, String zz) {
                    Map<String,Object> paramMap = (Map<String,Object>)carTypeList.get(pos);
                    paramMap.put("name",name);
                    paramMap.put("tj",tj);
                    paramMap.put("zz",zz);
                    notifyDataSetChanged();
                }
            }).build(activity);
            showAddCarTypeDialog();
         /*   newFaBuBanJiaAddCarTypeDialog = new NewFaBuBanJiaAddCarTypeDialog(activity,)*/
      /*      System.out.print("\nnetImg:"+netImgList.get(pos));
            System.out.print("\nnetImg:"+netImgList.get(pos));
            System.out.print("\nnetImg:"+netImgList.get(pos));
            System.out.print("\nnetImg:"+netImgList.get(pos));*/
        }

        public void showAddCarTypeDialog() {
            if (newFaBuBanJiaAddCarTypeDialog != null && !newFaBuBanJiaAddCarTypeDialog.isShowing())
                newFaBuBanJiaAddCarTypeDialog.show();
        }

        public void dimssAddCarTypeDialog() {
            if (newFaBuBanJiaAddCarTypeDialog != null && newFaBuBanJiaAddCarTypeDialog.isShowing())
                newFaBuBanJiaAddCarTypeDialog.dismiss();
        }
        @BindView(R.id.iv_new_fabubanjia_rv_item_headimg)
        ImageView ivNewFaBuBanJiaRVItemHeadImg;
        @BindView(R.id.tv_new_fabubanjia_rv_item_cartype)
        TextView tvNewFaBuBanJiaRVItemCarType;
        @BindView(R.id.rly_new_fabubanjia_rv_item_delete)
        RelativeLayout rlyNewFaBuBanJiaRVItemDelete;
        @OnClick(R.id.rly_new_fabubanjia_rv_item_delete)
        public void rlyNewFaBuBanJiaRVItemDeleteOnclick(){
            netImgList.remove(pos);
            carTypeList.remove(pos);
            notifyDataSetChanged();
        }


        boolean isEditVisible = true;
        float beginX = 0;
        float nowX = 0;
        float tempX = 0;
        float endX = 0;
        @BindView(R.id.sly_new_fabubanjia_rv_item)
        SwipeLayout slyNewFaBuBanJiaRVItem;
        @OnTouch(R.id.sly_new_fabubanjia_rv_item)
        public boolean slyNewFaBuBanJiaRVItem(View v, MotionEvent event){
           /* Toast.makeText(activity,"dis1:",Toast.LENGTH_LONG).show();*/
       /*     float beginY = 0;*/
            switch (event.getAction()){
                case MotionEvent.ACTION_DOWN:
      /*              Toast.makeText(activity,"dis2:",Toast.LENGTH_LONG).show();*/
                    beginX = event.getRawX();
                    break;
                case MotionEvent.ACTION_MOVE:
                    nowX = event.getRawX();
                    float xMovDis = nowX - beginX;
                    if(abs(xMovDis) < 15){
                        return false;
                    }

                    float movDis = nowX - tempX;//从右向左为负        从左向右为正
                    tempX = nowX;
                    if((movDis < 0)&&(abs(movDis) >15)){
                        rlyNewFaBuBanJiaRVItemEdit.setVisibility(View.INVISIBLE);
                        isEditVisible = false;
                    }else if((movDis > 0)&&(abs(movDis) >15)){
                        rlyNewFaBuBanJiaRVItemEdit.setVisibility(View.VISIBLE);
                        isEditVisible = true;
                    }


                    float width = activity.getWindowManager().getDefaultDisplay().getWidth();
                /*    System.out.print("\nwidth:"+width);*/
              /*      System.out.print("\nwidth:"+width);
                    System.out.print("\nwidth:"+width);
                    System.out.print("\nwidth:"+width);
                    System.out.print("\nwidth:"+width);
                    System.out.print("\nwidth:"+width);*/
                    ViewGroup.LayoutParams layoutParams = slyNewFaBuBanJiaRVItem.getLayoutParams();
                    float slyWidth = layoutParams.width;
             /*       System.out.print("\nslyWidth:"+slyWidth);*/
                /*    System.out.print("\nslyWidth:"+slyWidth);
                    System.out.print("\nslyWidth:"+slyWidth);
                    System.out.print("\nslyWidth:"+slyWidth);
                    System.out.print("\nslyWidth:"+slyWidth);
                    System.out.print("\nslyWidth:"+slyWidth);*/
                    /*System.out.print("\nmovDis:"+movDis);*/
            /*        System.out.print("\nmovDis:"+movDis);
                    System.out.print("\nmovDis:"+movDis);
                    System.out.print("\nmovDis:"+movDis);
                    System.out.print("\nmovDis:"+movDis);
                    System.out.print("\nmovDis:"+movDis);
                    System.out.print("\nmovDis:"+movDis);
                    System.out.print("\nmovDis:"+movDis);
                    System.out.print("\nmovDis:"+movDis);*/
                    break;
                case MotionEvent.ACTION_UP:
                    endX = event.getRawX();
                    float dis = endX - beginX;//正数为从右向左      负数为从左向右
                    if(abs(dis) < 30){
                        if(isEditVisible){
                            rlyNewFaBuBanJiaRVItemEdit.setVisibility(View.VISIBLE);
                        }else {
                            rlyNewFaBuBanJiaRVItemEdit.setVisibility(View.INVISIBLE);
                        }
                    }
                   /* if(abs(dis) < 20){
                        rlyNewFaBuBanJiaRVItemEdit.setVisibility(View.VISIBLE);
                    }*/
      /*              System.out.print("\ndis:"+dis);
                    System.out.print("\ndis:"+dis);
                    System.out.print("\ndis:"+dis);
                    System.out.print("\ndis:"+dis);*/
                    break;

            }
            return false;
        }


        public MyItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }


    }
}
