package com.shandian.lu.Main.MessageFragment.SystemInfo;

import android.app.Activity;
import android.widget.RelativeLayout;

import com.shandian.lu.BaseController;
import com.shandian.lu.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/5/25.
 */

public class SystemInfoController extends BaseController {


    @BindView(R.id.rly_main_message_sysinfo_back)
    RelativeLayout rlyMainMessageSysInfoBack;
    @OnClick(R.id.rly_main_message_sysinfo_back)
    public void rlyMainMessageSysInfoBackOnclick(){
        activity.finish();
    }
    public SystemInfoController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
    }
}
