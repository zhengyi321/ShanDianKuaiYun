package com.zhyan.shandiankuaiyun.Main.IndexFragment.CityChange;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhyan.shandiankuaiyuanwidgetlib.R;
import com.zhyan.shandiankuaiyun.Bean.SecondCityChangeBean;
import com.zhyan.shandiankuaiyun.NetWork.MainIndexNetWork;
import com.zhyan.shandiankuaiyunlib.Bean.ThirdCityChangeBean;

import java.util.ArrayList;
import java.util.List;

import rx.Observer;

/**
 * Created by az on 2017/5/6.
 */

public class SecondCityChangeDialog extends Dialog {
    Context context;
    SecondCityChangeDialogXRVAdapter secondCityChangeDialogXRVAdapter;
    List<ThirdCityChangeBean.ContentBean.ListBean> listBeen;
    private String aid = "";
    private String cid = "";
    public interface DialogCallBackListener{//通过该接口回调Dialog需要传递的值
        public void callBack(String tel);//具体方法
    }
    public SecondCityChangeDialog(Context context1, List<ThirdCityChangeBean.ContentBean.ListBean> listBeen1, String aid1,String cid1) {
        super(context1);
        this.context = context1;
        listBeen = listBeen1;
        aid = aid1;
        cid = cid1;
    }
    public SecondCityChangeDialog(Context context1, int themeResId) {
        super(context1, themeResId);
        this.context = context1;
    }

    protected SecondCityChangeDialog(Context context1, boolean cancelable, OnCancelListener cancelListener) {
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
        public SecondCityChangeDialog build(Context context) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final SecondCityChangeDialog cityChangeDialog = new SecondCityChangeDialog(context, R.style.MyDialogStyle);//默认调用带style的构造
            cityChangeDialog.setCanceledOnTouchOutside(false);//默认点击布局外不能取消dialog
            View view = mInflater.inflate(R.layout.dialog_citychange, null);
            cityChangeDialog.addContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            final RecyclerView rvCityChange= (RecyclerView) view.findViewById(R.id.rv_main_index_citychange_citychange);
            initXRV(rvCityChange);



            cityChangeDialog.setContentView(view);
            return cityChangeDialog;
        }

    }
    private void initXRV(RecyclerView recyclerView){
        List<String> stringList = new ArrayList<>();
        stringList.add("");
        stringList.add("");
        stringList.add("");

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3);
        secondCityChangeDialogXRVAdapter = new SecondCityChangeDialogXRVAdapter(context,listBeen,aid,cid);
        recyclerView.setAdapter(secondCityChangeDialogXRVAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);


    }
}
