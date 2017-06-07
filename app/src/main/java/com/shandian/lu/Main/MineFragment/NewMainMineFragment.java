package com.shandian.lu.Main.MineFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shandian.lu.Main.BaseFragment;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/7.
 */

public class NewMainMineFragment extends BaseFragment {





    @Override
    public View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_main_mine_lly, container, false);

        return view;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this,view);
    }
}
