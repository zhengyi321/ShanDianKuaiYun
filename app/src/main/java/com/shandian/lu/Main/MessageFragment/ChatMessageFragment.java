package com.shandian.lu.Main.MessageFragment;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;

import com.hyphenate.EMMessageListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.chat.EMMessage;
import com.hyphenate.exceptions.HyphenateException;
import com.zhyan.myhuanxin.EaseConstant;
import com.zhyan.myhuanxin.ui.EaseConversationListFragment;
import com.shandian.lu.Main.MessageFragment.Chat.ChatActivity;
import com.shandian.lu.Main.MessageFragment.SystemInfo.SystemInfoActivity;
import com.shandian.lu.Widget.DBHuanXin.SharePrefConstant;
import com.shandian.lu.R;
import com.shandian.lu.Widget.DBHuanXin.UserInfoCacheSvc;

import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/4/26.
 */

public class ChatMessageFragment extends EaseConversationListFragment {




    @BindView(R.id.lly_main_chatmessage_content_systeminfo)
    LinearLayout llyMainChatMessageContentSystemInfo;
     @OnClick(R.id.lly_main_chatmessage_content_systeminfo)
    public void llyMainChatMessageContentSystemInfoOnclick(){
         Intent sys=new Intent(getActivity(),SystemInfoActivity.class);
         startActivity(sys);

     }
 private ChatMessageFragmentController chatMessageFragmentController;
     @Override
     protected void initView() {
         super.initView();
         //add HeaderView

         View view=View.inflate(getActivity(), R.layout.fragment_main_chatmessage_lly,null);

         conversationListView.addHeaderView(view);

         init(view);
     }



    private void init(View view){
        ButterKnife.bind(this,view);
        initController(view);
       /* addFragment(view);*/
    }
    private void initController(View view){
        chatMessageFragmentController = new ChatMessageFragmentController(view);
    }
    private void addFragment(View view){
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        /*manager = ((Activity)view.getContext()).supp();*/
      /*   创建事物*/
        transaction = manager.beginTransaction();
        /*        创建一个Fragment*/
        TestFragment rightFragment = new TestFragment();
               /* 通过事物管理器把fragment添加到右侧的容器中(注：第一个参数是id，不是layout)*/
        transaction.add(R.id.fly_main_chatmessage_content_message, rightFragment);
                /*提交事物*/
        transaction.commit();
    }





    EMMessageListener messageListener = new EMMessageListener() {

/*    *
     * 接受消息*/


    @Override
    public void onMessageReceived(List<EMMessage> messages) {
        for (EMMessage emMessage : messages) {

            try {

                refresh();
                //获取消息扩展
                String HXid = emMessage.getStringAttribute(SharePrefConstant.ChatUserId);
                String nickname = emMessage.getStringAttribute(SharePrefConstant.ChatUserNick);
                String avater  =emMessage.getStringAttribute(SharePrefConstant.ChatUserPic);
                //存入本地数据库
                UserInfoCacheSvc.createOrUpdate(HXid, nickname, avater);
            } catch (HyphenateException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void onMessageRead(List<EMMessage> arg0) {

    }

    @Override
    public void onMessageDelivered(List<EMMessage> arg0) {

    }

    @Override
    public void onMessageChanged(EMMessage arg0, Object arg1) {

    }

    @Override
    public void onCmdMessageReceived(List<EMMessage> arg0) {

    }
};


    public void onCreateContextMenu(android.view.ContextMenu menu, View v, android.view.ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.em_delete_message, menu);
    };
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        boolean deleteMessage = false;
        if (item.getItemId() == R.id.delete_message) {
            deleteMessage = true;
        } else if (item.getItemId() == R.id.delete_conversation) {
            deleteMessage = false;
        }
        EMConversation tobeDeleteCons = conversationListView.getItem(((AdapterContextMenuInfo) item.getMenuInfo()).position-1);
        if (tobeDeleteCons == null) {
            return true;
        }

        try {
            // delete conversation
            EMClient.getInstance().chatManager().deleteConversation(tobeDeleteCons.conversationId(), deleteMessage);
//	            InviteMessgeDao inviteMessgeDao = new InviteMessgeDao(getActivity());
//	            inviteMessgeDao.deleteMessage(tobeDeleteCons.conversationId());
        } catch (Exception e) {
            e.printStackTrace();
        }
        refresh();

        // update unread count
        return true;
    }
    @Override
    protected void setUpView() {
        super.setUpView();
        //item点击事件
        conversationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                EMConversation conversation = conversationListView.getItem(position-1);
                startActivity(new Intent(getActivity(),ChatActivity.class).putExtra(EaseConstant.EXTRA_USER_ID, conversation.conversationId()));
            }
        });
        registerForContextMenu(conversationListView);

    }


    @Override
    public void onResume() {
        super.onResume();
        EMClient.getInstance().chatManager().addMessageListener(messageListener);

    }
    @Override
    public void onStop() {

        super.onStop();
    }
    @Override
    public void onDestroy() {
        EMClient.getInstance().chatManager().removeMessageListener(messageListener);
        super.onDestroy();
    }
}
