package com.shandian.lu.Main;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shandian.lu.R;

/**
 * Created by az on 2017/5/25.
 */

public abstract class BaseFragment extends Fragment {

    protected View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = setView(inflater,container,savedInstanceState);
        initView();

        return view;
    }

    public abstract View setView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);
    public  abstract void initView();

}
