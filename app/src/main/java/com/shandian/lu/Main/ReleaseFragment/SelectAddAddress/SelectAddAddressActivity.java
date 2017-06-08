package com.shandian.lu.Main.ReleaseFragment.SelectAddAddress;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/8.
 */

public class SelectAddAddressActivity extends BaseActivity {

    private SelectAddAddressController selectAddAddressController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_selectaddress_addaddress_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
    }

    private void initController(){
        selectAddAddressController = new SelectAddAddressController(this);
    }
}
