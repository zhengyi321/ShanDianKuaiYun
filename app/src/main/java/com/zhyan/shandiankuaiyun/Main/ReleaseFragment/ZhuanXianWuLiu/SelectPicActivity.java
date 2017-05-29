package com.zhyan.shandiankuaiyun.Main.ReleaseFragment.ZhuanXianWuLiu;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.util.DisplayUtils;
import com.yanzhenjie.album.widget.recyclerview.AlbumVerticalGirdDecoration;
import com.zhyan.shandiankuaiyun.BaseActivity;
import com.zhyan.shandiankuaiyun.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by az on 2017/5/12.
 */

public class SelectPicActivity extends BaseActivity {


    @BindView(R.id.rv_main_release_zhuanxianwuliu_select_photo)
    RecyclerView rvMainReleaseZhuanXiaWuLiuSelectPhoto;
    @BindView(R.id.rly_main_release_zhuanxianwuliu_select_photo_back)
    RelativeLayout rlyMainReleaseZhuanXianWuLiuSelectPhotoBack;
    @OnClick(R.id.rly_main_release_zhuanxianwuliu_select_photo_back)
    public void rlyMainReleaseZhuanXianWuLiuSelectPhotoBackOnclick(){
        Intent intent = new Intent();
        intent.putStringArrayListExtra("mImageList", mImageList);
        setResult(ACTIVITY_REQUEST_SELECT_PHOTO, intent);
        finish();
    }
    @BindView(R.id.rly_main_release_zhuanxianwuliu_select_photo_again)
    RelativeLayout rlyMainReleaseZhuanXianWuLiuSelectPhotoAgain;
    @OnClick(R.id.rly_main_release_zhuanxianwuliu_select_photo_again)
    public void rlyMainReleaseZhuanXianWuLiuSelectPhotoAgainOnclick(){
        fromAlbum();
    }
    @BindView(R.id.bt_main_release_zhuanxianwuliu_select_photo_finish)
    Button btMainReleaseZhuanXianWuLiuSelectPhotoFinish;
    @OnClick(R.id.bt_main_release_zhuanxianwuliu_select_photo_finish)
    public void btMainReleaseZhuanXianWuLiuSelectPhotoFinishOnclick(){
        Intent intent = new Intent();
        if(mImageList == null){
            mImageList = new ArrayList<>();
        }
        intent.putStringArrayListExtra("mImageList", mImageList);
        setResult(ACTIVITY_REQUEST_SELECT_PHOTO, intent);
        finish();
    }
    GridAdapter gridAdapter;
    private  final int ACTIVITY_REQUEST_SELECT_PHOTO = 100;
    private  final int ACTIVITY_REQUEST_TAKE_PICTURE = 101;
    private  final int ACTIVITY_REQUEST_PREVIEW_PHOTO = 102;
    private ArrayList<String> mImageList;
    private SelectPicController selectPicController;
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_main_release_zhuanxianwuliu_select_photo_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
        mImageList = getIntent().getStringArrayListExtra("mImageList");
        if(mImageList == null){
            mImageList = new ArrayList<>();
        }
        fromAlbum();
    }
    private void initController(){
        selectPicController = new SelectPicController(this);
        initSelectPicRV();
    }
    private void initSelectPicRV(){
        DisplayUtils.initScreen(this);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.decoration_white, null);
 /*       rvMainReleaseZhuanXiaWuLiuSelectPhoto.addItemDecoration(new AlbumVerticalGirdDecoration(drawable));*/

        assert drawable != null;
        int itemSize = (DisplayUtils.screenWidth - (drawable.getIntrinsicWidth() * 4)) / 3;

        gridAdapter = new GridAdapter(this,itemSize);
        rvMainReleaseZhuanXiaWuLiuSelectPhoto.setAdapter(gridAdapter);
        rvMainReleaseZhuanXiaWuLiuSelectPhoto.setLayoutManager(new GridLayoutManager(this,3));
    }

    /**
     * Select image from fromAlbum.
     */
    private void fromAlbum() {

        Album.album(this)
                .requestCode(ACTIVITY_REQUEST_SELECT_PHOTO)
                .toolBarColor(ContextCompat.getColor(this, R.color.colorPrimary)) // Toolbar color.
                .statusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)) // StatusBar color.
                .navigationBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryBlack)) // NavigationBar color.
                .selectCount(8) // select count.
                .columnCount(2) // span count.
                .camera(true) // has fromCamera function.
                .checkedList(mImageList) // The picture has been selected for anti-election.
                .start();
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
            case ACTIVITY_REQUEST_TAKE_PICTURE: {
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList.clear();
                    List<String> imageList = Album.parseResult(data); // Parse path.
                    mImageList.addAll(imageList);
                    refreshImage();
                } else if (resultCode == RESULT_CANCELED) { // User canceled.
                  /*  Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();*/
                   /* mImageList.clear();*/
                }
                break;
            }
            case ACTIVITY_REQUEST_PREVIEW_PHOTO: {
                if (resultCode == RESULT_OK) { // Successfully.
                    mImageList.clear();
                    mImageList = Album.parseResult(data); // Parse select result.
                    refreshImage();
                }
                break;
            }
        }
    }
    /**
     * Preview image.
     *
     * @param position current position.
     */
    private void previewImage(int position) {
        Album.gallery(this)
                .requestCode(ACTIVITY_REQUEST_PREVIEW_PHOTO)
                .toolBarColor(ContextCompat.getColor(this, R.color.colorPrimary)) // Toolbar color.
                .statusBarColor(ContextCompat.getColor(this, R.color.colorPrimaryDark)) // StatusBar color.
                .navigationBarColor(ActivityCompat.getColor(this, R.color.colorPrimaryBlack)) // NavigationBar color.
                .checkedList(mImageList) // Image list.
                .currentPosition(position) // Preview first to show the first few.
                .checkFunction(true) // Does the user have an anti-selection when previewing.
                .start();

    }
    protected void onResume(){
        super.onResume();
        refreshImage();
    }
    private void refreshImage(){
        gridAdapter.notifyDataSetChanged(mImageList);
    }

}
