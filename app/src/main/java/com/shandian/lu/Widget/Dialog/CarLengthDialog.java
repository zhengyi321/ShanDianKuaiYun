package com.shandian.lu.Widget.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhyan.shandiankuaiyuanwidgetlib.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by az on 2017/5/6.
 */

public class CarLengthDialog extends Dialog {
    Context context;
    CarLengthDialogXRVAdapter carLengthDialogXRVAdapter;
    List<String> listBeen;
    TextView textView;
    RelativeLayout dialogRly;

    public interface DialogCallBackListener{//通过该接口回调Dialog需要传递的值
        public void callBack(String tel);//具体方法
    }
    public interface OnAdapterClickListener{
        public void isClicked(boolean isClicked);
    }
    OnAdapterClickListener onAdapterClickListener;
    public CarLengthDialog(Context context1, TextView textView1 ) {
        super(context1);
        this.context = context1;
        textView = textView1;

    }
    public CarLengthDialog(Context context1, int themeResId) {
        super(context1, themeResId);
        this.context = context1;
    }

    protected CarLengthDialog(Context context1, boolean cancelable, OnCancelListener cancelListener) {
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

        public Builder setCallBackListener(OnAdapterClickListener onAdapterClickListener1){//设置回调
           onAdapterClickListener = onAdapterClickListener1;
            return this;
        }
        /**
         * 1,加载要显示的布局
         * 2，通过dialog的addContentView将布局添加到window中
         * 3，基本逻辑处理
         * 4，显示dialog的布局
         */
        public CarLengthDialog build(Context context) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final CarLengthDialog cityChangeDialog = new CarLengthDialog(context, R.style.MyDialogStyle);//默认调用带style的构造
            cityChangeDialog.setCanceledOnTouchOutside(true);//默认点击布局外不能取消dialog
            View view = mInflater.inflate(R.layout.dialog_carlength, null);
            cityChangeDialog.addContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            final RecyclerView rvCityChange= (RecyclerView) view.findViewById(R.id.rv_main_release_zhuanxianwuliu_carlength);


            initXRV(rvCityChange,cityChangeDialog);



            cityChangeDialog.setContentView(view);
            return cityChangeDialog;
        }

    }
    private void initXRV(RecyclerView recyclerView,CarLengthDialog cityChangeDialog ){
        List<String> stringList = new ArrayList<>();
        stringList.add("");
        stringList.add("");
        stringList.add("");
        String[]carLongs=new String[]{"4.2米","4.8米","5.2米","5.8米","6.2米","6.5米","6.8米","7.2米","8米","9.6米","12米","13米","13.5米","15米","16.5米","17.5米","18.5米","20米","22米","24米"};

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3);
        carLengthDialogXRVAdapter = new CarLengthDialogXRVAdapter(context,carLongs,textView,cityChangeDialog);
        carLengthDialogXRVAdapter.setSelectedCallBack(new CarLengthDialogXRVAdapter.OnSelectedListener() {
            @Override
            public void isSelected(boolean isSelected) {
                if(isSelected){
                    if(onAdapterClickListener != null){
                        onAdapterClickListener.isClicked(true);
                    }
                }
            }
        });
        recyclerView.setAdapter(carLengthDialogXRVAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

}
