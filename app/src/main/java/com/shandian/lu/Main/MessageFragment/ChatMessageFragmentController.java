package com.shandian.lu.Main.MessageFragment;

import android.view.View;

import com.shandian.lu.BaseController;

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
