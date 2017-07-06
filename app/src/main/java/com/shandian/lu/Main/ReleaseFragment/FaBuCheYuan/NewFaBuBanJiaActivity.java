package com.shandian.lu.Main.ReleaseFragment.FaBuCheYuan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.Widget.Dialog.NewFaBuBanJiaAddCarTypeDialog;
import com.shandian.lu.BaseActivity;
import com.shandian.lu.R;
import com.yanzhenjie.album.Album;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.BitmapUtils;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ImageView.RoundCornerImageView.RoundCornerImageView;
import com.shandian.lu.Widget.Dialog.NewFaBuBanJiaAddCarTypeDialog.RoundCornerImageViewCallBackListener;
import com.shandian.lu.Widget.Dialog.NewFaBuBanJiaAddCarTypeDialog.MsgCallBackListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/7/6.
 */

public class NewFaBuBanJiaActivity extends BaseActivity {


    private final int PICK_PHOTO_FROM_PHONE_CARTYPE_HEADIMG = 100;
    NewFaBuBanJiaAddCarTypeDialog newFaBuBanJiaAddCarTypeDialog;
    RoundCornerImageView rcivHeadImageView;
    ProgressBar rcivProgressBar;
    Map<String,String> carTypeMap;
    List<Object> carTypeList;
    private ArrayList<String> imgList;
    @BindView(R.id.lly_new_fabubanjia_cartype)
    LinearLayout llyNewFaBuBanJiaCarType;
    @OnClick(R.id.lly_new_fabubanjia_cartype)
    public void llyNewFaBuBanJiaCarTypeOnclick(){
        newFaBuBanJiaAddCarTypeDialog = new NewFaBuBanJiaAddCarTypeDialog(this,imgList).Build.setRoundCornerImageViewCallBackListener(new RoundCornerImageViewCallBackListener() {
            @Override
            public void rcivCallBack(RoundCornerImageView rcivImageView, ProgressBar rcivIvPB) {
                rcivHeadImageView = rcivImageView;
                rcivProgressBar = rcivIvPB;
            }
        }).setMsgCallBackListener(new MsgCallBackListener() {
            @Override
            public void msgCallBack(String name, String tj, String zz) {
                if(imgList.size() == 0){
                    Toast.makeText(NewFaBuBanJiaActivity.this,"头像不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                if(name.isEmpty()){
                    Toast.makeText(NewFaBuBanJiaActivity.this,"名称不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                if(tj.isEmpty()){
                    Toast.makeText(NewFaBuBanJiaActivity.this,"体积不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                if(zz.isEmpty()){
                    Toast.makeText(NewFaBuBanJiaActivity.this,"载重不能为空",Toast.LENGTH_LONG).show();
                    return;
                }
                carTypeMap = new HashMap<String, String>();
                carTypeMap.put("img",imgList.get(0));
                carTypeMap.put("name",name);
                carTypeMap.put("tj",tj);
                carTypeMap.put("zz",zz);
                carTypeList.add(carTypeMap);
                if(newFaBuBanJiaController != null){
                    newFaBuBanJiaController.newFaBuBanJiaCarTypeRVAdapter.setAdapter(carTypeList);
                }
            }
        }).build(this);
        showAddCarTypeDialog();
    }



    public void showAddCarTypeDialog() {
        if (newFaBuBanJiaAddCarTypeDialog != null && !newFaBuBanJiaAddCarTypeDialog.isShowing())
            newFaBuBanJiaAddCarTypeDialog.show();
    }

    public void dimssAddCarTypeDialog() {
        if (newFaBuBanJiaAddCarTypeDialog != null && newFaBuBanJiaAddCarTypeDialog.isShowing())
            newFaBuBanJiaAddCarTypeDialog.dismiss();
    }

    private NewFaBuBanJiaController newFaBuBanJiaController;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_fabubanjia_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        initController();
        carTypeList = new ArrayList<>();
        imgList = new ArrayList<>();
    }
    private void initController(){
        newFaBuBanJiaController = new NewFaBuBanJiaController(this);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {
            return;
        }
        switch (requestCode){
            case PICK_PHOTO_FROM_PHONE_CARTYPE_HEADIMG:
            if (resultCode == RESULT_OK) { // Successfully.
                imgList.clear();
                imgList = Album.parseResult(data); // Parse select result.
                   /* Toast.makeText(this,"ACTIVITY_REQUEST_SELECT_PHOTO:"+mImageList.get(0),Toast.LENGTH_LONG).show();*/
                if(imgList.size() != 0){
                    refreshImg();
                }
            } else if (resultCode == RESULT_CANCELED) { // User canceled.
                   /* Snackbar.make(noneView, R.string.cancel_select_photo_hint, Snackbar.LENGTH_LONG).show();*/
                   /* mImageList.clear();*/
            }
            break;

        }

    }

    private void refreshImg(){
        Bitmap bm = compressImageFromFile(imgList.get(0));
        rcivHeadImageView.setImageBitmap(bm);
    }

    //图片压缩
    private Bitmap compressImageFromFile(String srcPath) {

        BitmapUtils bitmapUtils = new BitmapUtils();
        Bitmap bitmap = bitmapUtils.getimage(srcPath);
/*        bitmap = bitmapUtils.compressImage(bitmap);
        bitmap = bitmapUtils.comp(bitmap);*/

        return bitmap;
    }
}
