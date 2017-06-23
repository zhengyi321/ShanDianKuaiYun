package com.shandian.lu.Widget.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewEditBaoJiaResultBean;
import com.shandian.lu.Main.IndexFragment.NewHuoYuanDetail.NewHuoYuanDetailOtherActivity;
import com.shandian.lu.Main.IndexFragment.NewHuoYuanDetail.NewHuoYuanDetailSelfActivity;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.NetWork.NewCheHuoListNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Observer;

/**
 * Created by az on 2017/5/6.
 */

public class NewHuoZhuEditBaoJiaDialog extends Dialog {
    Context context;

    List<String> listBeen;

    RelativeLayout dialogRly;
    private String hyId;
    private String status;
    public interface DialogCallBackListener{//通过该接口回调Dialog需要传递的值
        public void callBack(String tel);//具体方法
    }
    public NewHuoZhuEditBaoJiaDialog(Context context1, String hyId1,String status1 ) {
        super(context1);
        this.context = context1;
        hyId = hyId1;
        status = status1;
    }
    public NewHuoZhuEditBaoJiaDialog(Context context1, int themeResId) {
        super(context1, themeResId);
        this.context = context1;
    }

    protected NewHuoZhuEditBaoJiaDialog(Context context1, boolean cancelable, OnCancelListener cancelListener) {
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
        public NewHuoZhuEditBaoJiaDialog build(final Context context) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final NewHuoZhuEditBaoJiaDialog releaseDialog = new NewHuoZhuEditBaoJiaDialog(context, R.style.MyDialogStyle);//默认调用带style的构造
            releaseDialog.setCanceledOnTouchOutside(true);//默认点击布局外不能取消dialog
           /* releaseDialog.setCancelable(true);*/
            View view = mInflater.inflate(R.layout.dialog_other_huoyuanxiangqing_querenbaojia_rly, null);
            releaseDialog.addContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            releaseDialog.setContentView(view);
            final EditText inputMoney = (EditText) view.findViewById(R.id.et_dialog_other_hyxq_qrbj);
            RelativeLayout submit = (RelativeLayout)view.findViewById(R.id.rly_dialog_other_hyxq_qrbj_submit);
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Map<String,String> paramMap = new HashMap<String, String>();
                    String money = inputMoney.getText().toString();
                    XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
                    XCCacheManager xcCacheManager = XCCacheManager.getInstance(context);
                    String loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
                    if((loginId == null)||(loginId.isEmpty())){
                        Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                        return;
                    }
                    if((hyId == null)||(hyId.isEmpty())){
                        return;
                    }
                    paramMap.put("login_id",loginId);
                    paramMap.put("hyid",hyId);
                    if(money.isEmpty()){
                        Toast.makeText(context,"请输入金额",Toast.LENGTH_LONG).show();
                        return;
                    }
                    paramMap.put("jiage",money);
                    NewCheHuoListNetWork newCheHuoListNetWork = new NewCheHuoListNetWork();
                    newCheHuoListNetWork.editHuoZhuBaoJiaToNet(paramMap, new Observer<NewEditBaoJiaResultBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(NewEditBaoJiaResultBean newEditBaoJiaResultBean) {
                            Toast.makeText(context, newEditBaoJiaResultBean.getMsg(),Toast.LENGTH_LONG).show();
                            releaseDialog.dismiss();
                            Intent intent = new Intent(context, NewHuoYuanDetailSelfActivity.class);
                            intent.putExtra("hyid",hyId);
                            intent.putExtra("status",status);
                            context.startActivity(intent);
                            ((Activity)context).finish();
                        }
                    });

                }
            });
            return releaseDialog;


        }

    }


}
