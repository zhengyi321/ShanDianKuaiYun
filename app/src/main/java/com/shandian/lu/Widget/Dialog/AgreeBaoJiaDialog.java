package com.shandian.lu.Widget.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewBaoJiaListBean;
import com.example.mynewslayoutlib.Bean.NewHuoZhuTongYiBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ImageView.RoundImageView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observer;

/**
 * Created by az on 2017/5/6.
 */

public class AgreeBaoJiaDialog extends Dialog {
    Context context;

    NewBaoJiaListBean.NrBean.ListBean been;
    String hyId;
    RelativeLayout dialogRly;

    public interface DialogCallBackListener{//通过该接口回调Dialog需要传递的值
        public void callBack(String tel);//具体方法
    }
    public AgreeBaoJiaDialog(Context context1 , String hyId1,NewBaoJiaListBean.NrBean.ListBean been1) {
        super(context1);
        this.context = context1;
        hyId = hyId1;
        been = been1;

    }
    public AgreeBaoJiaDialog(Context context1, int themeResId) {
        super(context1, themeResId);
        this.context = context1;
    }

    protected AgreeBaoJiaDialog(Context context1, boolean cancelable, OnCancelListener cancelListener) {
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
        private DialogCallBackListener mDialogCallBackListener;
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

        public Builder setCallBackListener(DialogCallBackListener mDialogCallBackListener){//设置回调
            this.mDialogCallBackListener = mDialogCallBackListener;
            return this;
        }
        /**
         * 1,加载要显示的布局
         * 2，通过dialog的addContentView将布局添加到window中
         * 3，基本逻辑处理
         * 4，显示dialog的布局
         */
        public AgreeBaoJiaDialog build(Context context) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final AgreeBaoJiaDialog releaseDialog = new AgreeBaoJiaDialog(context, R.style.MyDialogStyle);//默认调用带style的构造
            releaseDialog.setCanceledOnTouchOutside(true);//默认点击布局外不能取消dialog
           /* releaseDialog.setCancelable(true);*/
            View view = mInflater.inflate(R.layout.dialog_new_huoyuanxiangqing_agreebaojia_rly, null);
            releaseDialog.addContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            final TextView nameTV = (TextView)view.findViewById(R.id.tv_new_hyxq_self_agreebaojia_name);
            final TextView priceTV = (TextView)view.findViewById(R.id.tv_new_hyxq_self_agreebaojia_price);
            RelativeLayout rlyTel = (RelativeLayout)view.findViewById(R.id.rly_new_hyxq_self_agreebaojia_tel);
            RelativeLayout rlyMesg = (RelativeLayout)view.findViewById(R.id.rly_new_hyxq_self_agreebaojia_message);
            RelativeLayout rlyAgreeSubmit = (RelativeLayout)view.findViewById(R.id.rly_new_hyxq_self_agreebaojia_agree_submit);
            RoundImageView roundImageViewTouXiang = (RoundImageView)view.findViewById(R.id.riv_new_hyxq_self_agreebaojia_touxiang);
            releaseDialog.setContentView(view);
            nameTV.setText(been.getName());
            priceTV.setText(been.getJiage());
            ImageLoader.getInstance().displayImage(been.getTouxiang(),roundImageViewTouXiang, ImageLoaderUtils.options1);
            rlyTel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startCallTel(been.getMobile());
                }
            });
            rlyAgreeSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    agreeBaoJiaSubmitToNet();
                    releaseDialog.dismiss();
                }
            });

            return releaseDialog;
        }
        private void startCallTel(String number) {
        /*PhoneFormatCheckUtils phoneFormatCheckUtils = new PhoneFormatCheckUtils();
        if((number != null)&&(phoneFormatCheckUtils.IsNumber(number))) {*/
            //用intent启动拨打电话
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + number));

            context.startActivity(intent);
       /* }*/
        }

        private void agreeBaoJiaSubmitToNet(){
            Map<String,String> paramMap = new HashMap<>();
            String czid = been.getCzid();
            if(czid ==  null){
                czid = "";
            }
            paramMap.put("czid",czid);
            paramMap.put("hyid",hyId);

            NewCheHuoListNetWork newCheHuoListNetWork = new NewCheHuoListNetWork();
            newCheHuoListNetWork.huoZhuAgreeSubmitToNet(paramMap, new Observer<NewHuoZhuTongYiBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(NewHuoZhuTongYiBean newHuoZhuTongYiBean) {
                    Toast.makeText(context,newHuoZhuTongYiBean.getMsg(),Toast.LENGTH_LONG).show();

                }
            });
        }

    }


}
