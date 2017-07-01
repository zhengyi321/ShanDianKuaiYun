package com.shandian.lu.Main.MessageFragment.Chat;

import android.content.Intent;
import android.os.Bundle;
/*
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseBaseActivity;
import com.hyphenate.easeui.ui.EaseChatFragment;*/
import com.zhyan.myhuanxin.EaseConstant;
import com.zhyan.myhuanxin.ui.EaseBaseActivity;
import com.zhyan.myhuanxin.ui.EaseChatFragment;
import com.shandian.lu.R;

/**
 * Created by az on 2017/5/25.
 */

public class ChatActivity/* extends Activity */ extends EaseBaseActivity {

    public static ChatActivity activityInstance;
    private EaseChatFragment chatFragment;
    String toChatUsername;
    String title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_message_chat_lly);
        activityInstance = this;
        //get user id or group id
        toChatUsername = getIntent().getExtras().getString("userId");
        title=getIntent().getExtras().getString("title");
        //use EaseChatFratFragment
        chatFragment = new EaseChatFragment();
        //pass parameters to chat fragment

        Bundle args = new Bundle();
        args.putInt(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
        args.putString(EaseConstant.EXTRA_USER_ID, toChatUsername);
        chatFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().add(R.id.main_message_chat, chatFragment).commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInstance = null;
    }
    @Override
    protected void onNewIntent(Intent intent) {
        String username = intent.getStringExtra("userId");
        if (toChatUsername.equals(username))
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }
    }
    public void onBackPressed() {
        chatFragment.onBackPressed();
    }

    public String getToChatUsername(){
        return toChatUsername;
    }
}
