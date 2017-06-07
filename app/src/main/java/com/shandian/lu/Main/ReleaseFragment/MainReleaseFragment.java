package com.shandian.lu.Main.ReleaseFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.shandian.lu.Main.BaseFragment;
import com.shandian.lu.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/4/25.
 */

public class MainReleaseFragment extends BaseFragment {


    private MainReleaseController mainReleaseController;


    @Override
    public View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_release_lly, container, false);

        return view;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this,view);
        initController(view);
    }



    private void initController(View view){
        mainReleaseController = new MainReleaseController(view);
    }
}
