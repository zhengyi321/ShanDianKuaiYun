package com.shandian.lu.Main.ReleaseFragment.ZhuanXianWuLiu;

import android.app.Activity;
import android.content.ContentResolver;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.shandian.lu.BaseController;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyunlib.Bean.ImageFloderBean;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by az on 2017/5/12.
 */

public class SelectPicController extends BaseController   {


    @BindView(R.id.bt_main_release_zhuanxianwuliu_select_photo_finish)
    Button btMainReleaseZhuanXianWuLiuSelectPhotoFinish;
    @BindView(R.id.rv_main_release_zhuanxianwuliu_select_photo)
    RecyclerView rvMainReleaseZhuanXianWuLiuSelectPhoto;

    private ImageFloderBean imageAll, currentImageFolder;
    private ArrayList<ImageFloderBean> mDirPaths = new ArrayList<ImageFloderBean>();
    private Button btn_ok;
    private ArrayList<String> selectedPicture = new ArrayList<String>();
    private SelectPicXRVAdapter selectPicXRVAdapter;
    private ContentResolver mContentResolver;
    private HashMap<String, Integer> tmpDir = new HashMap<String, Integer>();
    public SelectPicController(Activity activity1){
        activity = activity1;
        init();
    }

    @Override
    protected void init() {
        ButterKnife.bind(this,activity);


    }
















}
