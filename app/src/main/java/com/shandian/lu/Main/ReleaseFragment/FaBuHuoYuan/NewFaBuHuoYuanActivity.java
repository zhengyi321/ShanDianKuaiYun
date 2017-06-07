package com.shandian.lu.Main.ReleaseFragment.FaBuHuoYuan;

import android.content.Intent;

import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;
import com.yanzhenjie.album.Album;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/6/7.
 */

public class NewFaBuHuoYuanActivity extends BaseActivity {


    private NewFaBuHuoYuanController newFaBuHuoYuanController;
    private  final int ACTIVITY_REQUEST_SELECT_PHOTO = 100;
    private ArrayList<String> mImageList;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_fabuhuoyuan_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        mImageList = new ArrayList<>();
        initController();

    }


    private void initController(){
        newFaBuHuoYuanController = new NewFaBuHuoYuanController(this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ACTIVITY_REQUEST_SELECT_PHOTO: {
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList.clear();
                    mImageList = Album.parseResult(data); // Parse select result.
                   /* Toast.makeText(this,"ACTIVITY_REQUEST_SELECT_PHOTO:"+mImageList.get(0),Toast.LENGTH_LONG).show();*/
                    refreshImage();
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                   /* Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();*/
                   /* mImageList.clear();*/
                }
                break;
            }
        }
    }


    private void refreshImage(){
        newFaBuHuoYuanController.addPicRVAdapter.setAdapterImage(mImageList);
        newFaBuHuoYuanController.addPicRVAdapter.setmImageList(mImageList);
    }
}
