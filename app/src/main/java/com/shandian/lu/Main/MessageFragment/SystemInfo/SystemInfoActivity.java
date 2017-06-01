package com.shandian.lu.Main.MessageFragment.SystemInfo;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/25.
 */

public class SystemInfoActivity extends BaseActivity {

   private SystemInfoController systemInfoController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_message_systeminfo_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();;
    }

    private void initController(){
        systemInfoController = new SystemInfoController(this);
    }
}
