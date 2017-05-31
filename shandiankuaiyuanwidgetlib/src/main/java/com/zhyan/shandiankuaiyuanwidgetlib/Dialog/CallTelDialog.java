package com.zhyan.shandiankuaiyuanwidgetlib.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhyan.shandiankuaiyuanwidgetlib.R;

/**
 * Created by az on 2017/5/6.
 */

public class CallTelDialog extends Dialog {
    Context context;
    private String tel;
    public interface DialogCallBackListener{//通过该接口回调Dialog需要传递的值
        public void callBack(String tel);//具体方法
    }
    public CallTelDialog(Context context1,String tel1) {
        super(context1);
        this.context = context1;
        tel = tel1;

    }
    public CallTelDialog(Context context1, int themeResId) {
        super(context1, themeResId);
        this.context = context1;
    }

    protected CallTelDialog(Context context1, boolean cancelable, OnCancelListener cancelListener) {
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
        public CallTelDialog build(Context context) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final CallTelDialog companyCustomTelDialog = new CallTelDialog(context, R.style.MyDialogStyle);//默认调用带style的构造
            companyCustomTelDialog.setCanceledOnTouchOutside(true);//默认点击布局外不能取消dialog
            View view = mInflater.inflate(R.layout.dialog_call_tel, null);
            companyCustomTelDialog.addContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            final TextView tvTel= (TextView) view.findViewById(R.id.tv_call_tel_tel);
            tvTel.setText(tel);
            if (!TextUtils.isEmpty(positiveText)) {//这是确认按钮
                RelativeLayout rly_cofirm = (RelativeLayout) view.findViewById(R.id.rly_call_tel_query);
                if (positiviOnclickListener != null) {
                    rly_cofirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            positiviOnclickListener.onClick(companyCustomTelDialog, BUTTON_POSITIVE);
                            if(mDialogCallBackListener != null )
                                mDialogCallBackListener.callBack(tvTel.getText().toString());  //触发回调
                        }
                    });
                }
            } else {
                view.findViewById(R.id.rly_call_tel_cancel).setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(negativeText)) {//这是取消按钮逻辑处理
                RelativeLayout btn_cancle = (RelativeLayout) view.findViewById(R.id.rly_call_tel_cancel);
                if (negativeOnclickListener != null) {
                    btn_cancle.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            negativeOnclickListener.onClick(companyCustomTelDialog, BUTTON_NEGATIVE);
                        }
                    });
                }
            } else {
                view.findViewById(R.id.rly_call_tel_cancel).setVisibility(View.GONE);
            }

            companyCustomTelDialog.setContentView(view);
            return companyCustomTelDialog;
        }

    }
}
