package com.shandian.lu.Main.AdviceFragment;

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

public class MainAdviceFragment extends BaseFragment {


    private MainAdviceController mainAdviceController;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_advice_lly, container, false);
        init(view);
        return view;
    }



    private void init(View view){
        ButterKnife.bind(this,view);
        mainAdviceController = new MainAdviceController(view);
    }
}
