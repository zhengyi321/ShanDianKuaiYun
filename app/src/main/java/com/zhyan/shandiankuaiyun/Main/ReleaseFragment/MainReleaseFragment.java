package com.zhyan.shandiankuaiyun.Main.ReleaseFragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhyan.shandiankuaiyun.Main.BaseFragment;
import com.zhyan.shandiankuaiyun.R;

import butterknife.ButterKnife;

/**
 * Created by az on 2017/4/25.
 */

public class MainReleaseFragment extends BaseFragment {


    private MainReleaseController mainReleaseController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_release_lly, container, false);
        init(view);
        return view;
    }



    private void init(View view){
        ButterKnife.bind(this,view);
        initController(view);
    }
    private void initController(View view){
        mainReleaseController = new MainReleaseController(view);
    }
}
