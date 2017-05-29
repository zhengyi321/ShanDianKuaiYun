package com.zhyan.shandiankuaiyun.Main.ReleaseFragment.ZhuanXianWuLiu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.yanzhenjie.album.Album;
import com.zhyan.shandiankuaiyun.Main.ReleaseFragment.ZhuanXianWuLiu.SelectPicXRVAdapter.OnSelectImageListener;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.PermissionUtil;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.ToolUtils;
import com.zhyan.shandiankuaiyun.BaseController;
import com.zhyan.shandiankuaiyun.R;
import com.zhyan.shandiankuaiyunlib.Bean.ImageFloderBean;
import android.Manifest.permission;
import android.database.Cursor;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.zhyan.shandiankuaiyuanwidgetlib.Utils.PermissionUtil.AfterPermissionGranted;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.PermissionUtil.PermissionCallback;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.PermissionUtil.RequestCode;
import com.zhyan.shandiankuaiyunlib.Bean.ImageItemBean;
import android.provider.MediaStore.MediaColumns;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import android.provider.MediaStore;
import android.provider.MediaStore.MediaColumns;
import android.widget.Toast;

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
