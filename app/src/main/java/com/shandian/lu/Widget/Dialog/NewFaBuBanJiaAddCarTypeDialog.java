package com.shandian.lu.Widget.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.R;
import com.yanzhenjie.album.Album;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ImageView.RoundCornerImageView.RoundCornerImageView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/7/6.
 */

public class NewFaBuBanJiaAddCarTypeDialog extends Dialog {
    Context context;
    private ArrayList<String> imgList;
    private final int PICK_PHOTO_FROM_PHONE_CARTYPE_HEADIMG = 100;
    private final int UPDATE_PHOTO_FROM_PHONE_CARTYPE_HEADIMG = 101;
    private boolean isUpdate = false;
    private Bitmap imgBitMap1;
    private String imgUrl,name,tj,zz;
    private int postion = 0;
    public interface RoundCornerImageViewCallBackListener{//通过该接口回调Dialog需要传递的值
        public void rcivCallBack(RoundCornerImageView rcivImageView,ProgressBar rcivIvPB);//具体方法
    }

    public interface UpdateRoundCornerImageViewCallBackListener{
        public void updateRoundCornerImageViewCallBack(RoundCornerImageView rcivImageView,ProgressBar rcivIvPB,int pos);
    }

    public interface MsgCallBackListener{
        public void msgCallBack(String name,String tj,String zz);
    }

    public NewFaBuBanJiaAddCarTypeDialog(Context context1, ArrayList<String> imgList1, Bitmap imgBit,String imgUrl1,String name1,String tj1,String zz1,int pos,boolean isUpdate1) {
        super(context1);
        this.context = context1;
        imgList = imgList1;
        isUpdate = isUpdate1;
        imgBitMap1 = imgBit;
        imgUrl = imgUrl1;
        name = name1;
        tj  = tj1;
        zz = zz1;
        isUpdate = isUpdate1;
        postion = pos;
    }

    public NewFaBuBanJiaAddCarTypeDialog(Context context1, int themeResId) {
        super(context1, themeResId);
        this.context = context1;
    }

    protected NewFaBuBanJiaAddCarTypeDialog(Context context1, boolean cancelable, OnCancelListener cancelListener) {
        super(context1, cancelable, cancelListener);
        this.context = context1;
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    public Builder Build = new Builder(context);
    //用Builder模式来构造Dialog
    public  class Builder {
        private Context mContext;
        private View contentView;
        private String title;
        private String message;
        private String positiveText;
        private String negativeText;
        private OnClickListener positiviOnclickListener;
        private OnClickListener negativeOnclickListener;
        private RoundCornerImageViewCallBackListener roundCornerImageViewCallBackListener;
        private UpdateRoundCornerImageViewCallBackListener updateRoundCornerImageViewCallBackListener;
        private MsgCallBackListener msgCallBackListener;
        public Builder(Context mContext) {
            this.mContext = mContext;
        }

        public Builder setContentView(View contentView) {//设置dialog的主view
            this.contentView = contentView;
            return this;
        }

        public Builder setTitle(String title) {//设置dialog的标题
            this.title = title;
            return this;
        }

        public Builder setMessage(String msg) {//设置dialig的内容
            this.message = msg;
            return this;
        }

        public Builder setPositiveButton(String text, OnClickListener positiviOnclickListener) {//dialog的确认按钮
            this.positiveText = text;
            this.positiviOnclickListener = positiviOnclickListener;
            return this;
        }

        public Builder setNegativeButton(String text, OnClickListener negativeOnclickListener) {//dialog的取消按钮
            this.negativeText = text;
            this.negativeOnclickListener = negativeOnclickListener;
            return this;
        }

        public Builder setRoundCornerImageViewCallBackListener(RoundCornerImageViewCallBackListener roundCornerImageViewCallBackListener1){//设置回调
            this.roundCornerImageViewCallBackListener = roundCornerImageViewCallBackListener1;
            return this;
        }
        public Builder setUpdateRoundCornerImageViewCallBackListener(UpdateRoundCornerImageViewCallBackListener updateRoundCornerImageViewCallBackListener1){
            this.updateRoundCornerImageViewCallBackListener = updateRoundCornerImageViewCallBackListener1;
            return this;
        }
        public Builder setMsgCallBackListener(MsgCallBackListener msgCallBackListener1){
            this.msgCallBackListener = msgCallBackListener1;
            return this;
        }
        /**
         * 1,加载要显示的布局
         * 2，通过dialog的addContentView将布局添加到window中
         * 3，基本逻辑处理
         * 4，显示dialog的布局
         */
        public NewFaBuBanJiaAddCarTypeDialog build(Context context) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final NewFaBuBanJiaAddCarTypeDialog companyCustomTelDialog = new NewFaBuBanJiaAddCarTypeDialog(context, R.style.MyDialogStyle);//默认调用带style的构造
            companyCustomTelDialog.setCanceledOnTouchOutside(true);//默认点击布局外不能取消dialog
            View view = mInflater.inflate(R.layout.dialog_fabubanjia_add_cartype_rly, null);
            companyCustomTelDialog.addContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            final RoundCornerImageView roundCornerImageView = (RoundCornerImageView) view.findViewById(R.id.rciv_dialog_fbbj_cartype_headimg);
            if(imgBitMap1 != null){
                roundCornerImageView.setImageBitmap(imgBitMap1);
            }else {
                if((imgUrl != null)&&(imgUrl.isEmpty())){
                    ImageLoader.getInstance().displayImage(imgUrl,roundCornerImageView, ImageLoaderUtils.options1);
                }
            }

            final ProgressBar roundCornerImageViewPrograssBar = (ProgressBar) view.findViewById(R.id.pb_dialog_fbbj_cartype_headimg);
            final EditText nameET = (EditText) view.findViewById(R.id.et_dialog_fbbj_cartype_name);
            if((name != null)&&(!name.isEmpty())){
                nameET.setText(name);
            }
            final EditText tjET = (EditText) view.findViewById(R.id.et_dialog_fbbj_cartype_tj);
            if((tj != null)&&(!tj.isEmpty())){
                tjET.setText(tj);
            }
            final EditText zzET = (EditText) view.findViewById(R.id.et_dialog_fbbj_cartype_zz);
            if((zz != null)&&(!zz.isEmpty())){
                zzET.setText(zz);
            }
            final ImageView ivClose = (ImageView) view.findViewById(R.id.iv_dialog_fbbj_cartype_close);
            ivClose.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    companyCustomTelDialog.dismiss();
                }
            });
            final TextView tvSubmit = (TextView)view.findViewById(R.id.tv_dialog_fbbj_cartype_submit);
            if(isUpdate){
                tvSubmit.setText("确认修改");
            }
            final RelativeLayout rlySubmit = (RelativeLayout) view.findViewById(R.id.rly_dialog_fbbj_cartype_submit);
            roundCornerImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!isUpdate) {
                        if (roundCornerImageViewCallBackListener != null) {
                            roundCornerImageViewCallBackListener.rcivCallBack(roundCornerImageView, roundCornerImageViewPrograssBar);

                        }
                    }else {
                        if(updateRoundCornerImageViewCallBackListener != null){
                            updateRoundCornerImageViewCallBackListener.updateRoundCornerImageViewCallBack(roundCornerImageView,roundCornerImageViewPrograssBar,postion);
                        }
                    }
                    selectPhotoFromPhone();
                }
            });
            rlySubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = nameET.getText().toString();
                    String tj = tjET.getText().toString();
                    String zz = zzET.getText().toString();
                    if(msgCallBackListener != null){
                        msgCallBackListener.msgCallBack(name,tj,zz);
                        companyCustomTelDialog.dismiss();
                    }
                }
            });

            companyCustomTelDialog.setContentView(view);
            return companyCustomTelDialog;
        }



    }


    private void selectPhotoFromPhone(){
        if(!isUpdate) {
            Album.album((Activity) context)
                    .requestCode(PICK_PHOTO_FROM_PHONE_CARTYPE_HEADIMG)
                    .toolBarColor(ContextCompat.getColor(context, R.color.colorPrimary)) // Toolbar color.
                    .statusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark)) // StatusBar color.
                    .navigationBarColor(ActivityCompat.getColor(context, R.color.colorPrimaryBlack)) // NavigationBar color.
                    .selectCount(1) // select count.
                    .columnCount(2) // span count.
                    .camera(true) // has fromCamera function.
                    .checkedList(imgList) // The picture has been selected for anti-election.
                    .start();
        }else {
            Album.album((Activity) context)
                    .requestCode(UPDATE_PHOTO_FROM_PHONE_CARTYPE_HEADIMG)
                    .toolBarColor(ContextCompat.getColor(context, R.color.colorPrimary)) // Toolbar color.
                    .statusBarColor(ContextCompat.getColor(context, R.color.colorPrimaryDark)) // StatusBar color.
                    .navigationBarColor(ActivityCompat.getColor(context, R.color.colorPrimaryBlack)) // NavigationBar color.
                    .selectCount(1) // select count.
                    .columnCount(2) // span count.
                    .camera(true) // has fromCamera function.
                    .checkedList(imgList) // The picture has been selected for anti-election.
                    .start();
        }
    }
}