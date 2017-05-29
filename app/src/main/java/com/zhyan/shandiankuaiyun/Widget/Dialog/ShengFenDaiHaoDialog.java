package com.zhyan.shandiankuaiyun.Widget.Dialog;

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

public class ShengFenDaiHaoDialog extends Dialog {
    Context context;
    ShengFenDaiHaoDialogXRVAdapter shengFenDaiHaoDialogXRVAdapter;
    List<String> listBeen;
    TextView textView;
    RelativeLayout dialogRly;

    public interface DialogCallBackListener{//通过该接口回调Dialog需要传递的值
        public void callBack(String tel);//具体方法
    }
    public ShengFenDaiHaoDialog(Context context1, TextView textView1 ) {
        super(context1);
        this.context = context1;
        textView = textView1;

    }
    public ShengFenDaiHaoDialog(Context context1, int themeResId) {
        super(context1, themeResId);
        this.context = context1;
    }

    protected ShengFenDaiHaoDialog(Context context1, boolean cancelable, OnCancelListener cancelListener) {
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
        public ShengFenDaiHaoDialog build(Context context) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            final ShengFenDaiHaoDialog shengFenDaiHaoDialog = new ShengFenDaiHaoDialog(context, R.style.MyDialogStyle);//默认调用带style的构造
            shengFenDaiHaoDialog.setCanceledOnTouchOutside(true);//默认点击布局外不能取消dialog
            View view = mInflater.inflate(R.layout.dialog_shengfendaihao, null);
            shengFenDaiHaoDialog.addContentView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            final RecyclerView rvCityChange= (RecyclerView) view.findViewById(R.id.rv_main_mine_renzheng_shengfendaihao);


            initXRV(rvCityChange,shengFenDaiHaoDialog);



            shengFenDaiHaoDialog.setContentView(view);
            return shengFenDaiHaoDialog;
        }

    }
    private void initXRV(RecyclerView recyclerView,ShengFenDaiHaoDialog shengFenDaiHaoDialog ){
        List<String> stringList = new ArrayList<>();
        stringList.add("");
        stringList.add("");
        stringList.add("");
        String[]carLongs=new String[]{"京","津","沪","渝","冀","晋","蒙","辽","吉","黑","苏","浙","皖","闽","赣","鲁","豫","鄂","湘","粤","桂","琼","川","贵","云","藏","陕","甘","青","宁","新",};

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context,3);
        shengFenDaiHaoDialogXRVAdapter = new ShengFenDaiHaoDialogXRVAdapter(context,carLongs,textView,shengFenDaiHaoDialog);
        recyclerView.setAdapter(shengFenDaiHaoDialogXRVAdapter);
        recyclerView.setLayoutManager(gridLayoutManager);
    }

}
