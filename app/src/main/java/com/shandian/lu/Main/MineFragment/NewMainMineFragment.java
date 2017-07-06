package com.shandian.lu.Main.MineFragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shandian.lu.Main.BaseFragment;
import com.shandian.lu.Main.MainController;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/7.
 */

public class NewMainMineFragment extends BaseFragment {



    private BroadcastReceiver broadcast;
    private NewMainMineController newMainMineController;
    @Override
    public View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_main_mine_lly, container, false);

        return view;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this,view);
        initController();
        broadcast = new LoginOutBroadcast();
        view.getContext().registerReceiver(broadcast, new IntentFilter("loginOut"));
    }

    private void initController(){
        newMainMineController = new NewMainMineController(view);
    }

    @Override
    public void onResume(){
        super.onResume();
        newMainMineController.onResume();
    }




    public class LoginOutBroadcast extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            newMainMineController.onResume();
        }
    }
}
