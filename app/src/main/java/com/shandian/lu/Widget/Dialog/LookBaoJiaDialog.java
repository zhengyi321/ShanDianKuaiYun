package com.shandian.lu.Widget.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mynewslayoutlib.Bean.NewBaoJiaListBean;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;

import java.util.List;

import rx.Observer;

/**
 * Created by az on 2017/5/6.
 */

public class LookBaoJiaDialog extends Dialog {
    Context context;

    List<String> listBeen;
    String hyId;
    String baojiaId;
    RelativeLayout dialogRly;

    public interface DialogCallBackListener{//通过该接口回调Dialog需要传递的值
        public void callBack(String tel);//具体方法
    }
    public LookBaoJiaDialog(Context context1 ,String hyId1,String baojiaId1) {
        super(context1);
        this.context = context1;
        hyId = hyId1;
        baojiaId = baojiaId1;
    }
    public LookBaoJiaDialog(Context context1, int themeResId) {
        super(context1, themeResId);
        this.context = context1;
    }

    protected LookBaoJiaDialog(Context context1, boolean cancelable, OnCancelListener cancelListener) {
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
        public LookBaoJiaDialog build(Context context) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            LookBaoJiaDialog releaseDialog = new LookBaoJiaDialog(context, R.style.MyDialogStyle);//默认调用带style的构造
            releaseDialog.setCanceledOnTouchOutside(true);//默认点击布局外不能取消dialog
           /* releaseDialog.setCancelable(true);*/
            View view = mInflater.inflate(R.layout.dialog_new_huoyuanxiangqing_self_baojia_rly, null);
            releaseDialog.addContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            final TextView textView = (TextView)view.findViewById(R.id.tv_new_hyxq_self_baojia_message_size);

            releaseDialog.setContentView(view);

            final LookBaoJiaDialogController baoJiaDialogController = new LookBaoJiaDialogController(view);
            NewCheHuoListNetWork newCheHuoListNetWork = new NewCheHuoListNetWork();
            newCheHuoListNetWork.getBaoJiaListFromNet(hyId, new Observer<NewBaoJiaListBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(NewBaoJiaListBean newBaoJiaListBean) {
                    if(newBaoJiaListBean.getStatus().equals("0")){
                        baoJiaDialogController.baoJiaDialogXRVAdapter.setAdapter(newBaoJiaListBean.getNr().getList(),hyId,baojiaId);
                        textView.setText(newBaoJiaListBean.getNr().getList().size()+"条");
                    }
                }
            });
            return releaseDialog;
        }

    }


}
