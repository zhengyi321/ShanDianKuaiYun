package com.shandian.lu.Main.MineFragment.Login.FindPass.ResetPass;

import android.app.Activity;
import android.text.InputType;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.ResetPassBean;
import com.shandian.lu.BaseController;
import com.shandian.lu.NetWork.AccountNetWork;
import com.shandian.lu.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/6/20.
 */

public class ResetPassController extends BaseController {

    String mobile;
    @BindView(R.id.rly_main_mine_login_resetpass_topbar_cancel)
    RelativeLayout rlyMainMineLoginResetPassTopBarCancel;

    @OnClick(R.id.rly_main_mine_login_resetpass_topbar_cancel)
    public void rlyMainMineLoginResetPassTopBarCancelOnclick(){
        activity.finish();
    }
    @BindView(R.id.et_main_mine_login_resetpass_content_pass)
    EditText etMainMineLoginResetPassContentPass;
    @BindView(R.id.et_main_mine_login_resetpass_content_pass_again)
    EditText etMainMineLoginResetPassContentPassAgain;
    @BindView(R.id.cb_main_mine_login_resetpass_content_see)
    CheckBox cbMainMineLoginResetPassContentSee;
    @OnClick(R.id.cb_main_mine_login_resetpass_content_see)
    public void cbMainMineLoginResetPassContentSeeOnclick(){
        onCheckedChange();
    }
    @BindView(R.id.rly_main_mine_login_resetpass_content_submit)
    RelativeLayout rlyMainMineLoginResetPassContentSubmit;

    @OnClick(R.id.rly_main_mine_login_resetpass_content_submit)
    public void rlyMainMineLoginResetPassContentSubmitOnclick(){
        resetPassSubmitToNet();
    }

    public ResetPassController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        getMobile();
    }

    private void getMobile(){
        mobile = activity.getIntent().getStringExtra("mobile");
        if((mobile == null)||(mobile.isEmpty())){
            mobile = "";
        }
    }
    private void onCheckedChange(){
        if(cbMainMineLoginResetPassContentSee.isChecked()){
//选择状态 显示明文--设置为可见的密码
            etMainMineLoginResetPassContentPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            etMainMineLoginResetPassContentPassAgain.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        }else {
//默认状态显示密码--设置文本 要一起写才能起作用 InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD
            etMainMineLoginResetPassContentPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            etMainMineLoginResetPassContentPassAgain.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
    }

    private void resetPassSubmitToNet(){
        String pass = etMainMineLoginResetPassContentPass.getText().toString();
        String pass_again= etMainMineLoginResetPassContentPassAgain.getText().toString();
        if(!pass.equals(pass_again)){
            Toast.makeText(activity,"请输入相同的密码",Toast.LENGTH_LONG).show();
            return;
        }
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("new_password",pass);
        paramMap.put("repeat_password",pass_again);
        paramMap.put("mobile",mobile);
        AccountNetWork accountNetWork = new AccountNetWork();
        accountNetWork.resetPassToNet(paramMap, new Observer<ResetPassBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(ResetPassBean resetPassBean) {
                Toast.makeText(activity,resetPassBean.getMsg(),Toast.LENGTH_LONG).show();
                if(resetPassBean.getStatus() == 0){
                    activity.finish();
                }
            }
        });
    }



}
