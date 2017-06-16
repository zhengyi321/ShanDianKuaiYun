package com.shandian.lu.Main.MineFragment.WoDeQianBao.NewTiXian.NewTiXianSuccess;

import android.widget.Button;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/16.
 */

public class NewTiXianSuccessActivity extends BaseActivity {

    @BindView(R.id.bt_new_mywallet_tixian_success_to_mywallet)
    Button btNewMyWalletTiXianSuccessToMyWallet;

    @OnClick(R.id.bt_new_mywallet_tixian_success_to_mywallet)
    public void btNewMyWalletTiXianSuccessToMyWalletOnclick(){
        finish();
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_mywallet_tixian_success_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
    }
}
