package com.zhyan.shandiankuaiyun.Main.MessageFragment;

import android.app.Activity;
import android.view.View;

import com.zhyan.shandiankuaiyun.BaseController;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/25.
 */

public class ChatMessageFragmentController extends BaseController {

    public ChatMessageFragmentController(View view1){
        view = view1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,view);

    }
}
