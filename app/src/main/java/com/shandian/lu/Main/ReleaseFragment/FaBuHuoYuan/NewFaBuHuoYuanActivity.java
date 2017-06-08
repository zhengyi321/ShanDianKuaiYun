package com.shandian.lu.Main.ReleaseFragment.FaBuHuoYuan;

import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewFaBuPicBean;
import com.j256.ormlite.stmt.query.In;
import com.shandian.lu.BaseActivity;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.Main.ReleaseFragment.SelectAddAddress.SelectAddAddressActivity;
import com.shandian.lu.NetWork.NewFaBuNetWork;
import com.shandian.lu.R;
import com.yanzhenjie.album.Album;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyuanwidgetlib.Utils.BitmapUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/6/7.
 */

public class NewFaBuHuoYuanActivity extends BaseActivity {


    private NewFaBuHuoYuanController newFaBuHuoYuanController;
    private  final int ACTIVITY_REQUEST_SELECT_PHOTO = 100;

    private final int ACTIVITY_SELECT_ADDRESS_BEGIN = 105;
    private final int ACTIVITY_SELECT_ADDRESS_END = 106;
    private ArrayList<String> mImageList;
    private ArrayList<String> netImageList;
    int picSize = 0;
    int i = 0;


    @BindView(R.id.lly_new_main_release_tzys_content_begin)
    LinearLayout llyNewMainReleaseTZYSContentBegin;
    @OnClick(R.id.lly_new_main_release_tzys_content_begin)
    public void llyNewMainReleaseTZYSContentBeginOnclick(){
        Intent intent = new Intent(this, SelectAddAddressActivity.class);
        startActivityForResult(intent,ACTIVITY_SELECT_ADDRESS_BEGIN);
    }
    @BindView(R.id.lly_new_main_release_tzys_content_end)
    LinearLayout llyNewMainReleaseTZYSContentEnd;
    @OnClick(R.id.lly_new_main_release_tzys_content_end)
    public void llyNewMainReleaseTZYSContentEndOnclick(){
        Intent intent = new Intent(this, SelectAddAddressActivity.class);
        startActivityForResult(intent,ACTIVITY_SELECT_ADDRESS_END);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_fabuhuoyuan_lly);
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);
        mImageList = new ArrayList<>();
        netImageList = new ArrayList<>();
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
        picSize = mImageList.size();
        if(picSize <= 0){
            return;
        }
        i = 0;
        sendPicToNet();
    }


    private void sendPicToNet(){


        if(i < picSize) {

            NewFaBuNetWork newFaBuNetWork = new NewFaBuNetWork();
            newFaBuNetWork.upPicToNet(getParamMap(), new Observer<NewFaBuPicBean>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(NewFaBuPicBean newFaBuPicBean) {
                    if(newFaBuPicBean.getStatus().equals("0")){
                        netImageList.add(newFaBuPicBean.getImgurl());
                        i++;
                        sendPicToNet();
                    }
                }
            });
        }else{
            newFaBuHuoYuanController.addPicRVAdapter.setNetImageList(netImageList);
        }
/*        System.out.print("\nbase64:"+base64_00);*/
        /*Log.i("base64:",base64_00);*/

    }
    private Map<String,String> getParamMap(){
        Map<String,String> paramMap = new HashMap<>();
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(this);
        String loginId= xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            Toast.makeText(this,"请登录",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            return paramMap;
        }
        paramMap.put("login_id",loginId);

        BitmapUtils bitmapUtils = new BitmapUtils();
        Bitmap bm = compressImageFromFile(mImageList.get(i));
        //将图片显示到ImageView中
        String base64_00 = bitmapUtils.bitmapConvertBase64(bm);
        paramMap.put("tu",base64_00);
        return paramMap;

    }
    //图片压缩
    private Bitmap compressImageFromFile(String srcPath) {

        BitmapUtils bitmapUtils = new BitmapUtils();
        Bitmap bitmap = bitmapUtils.getimage(srcPath);
        bitmap = bitmapUtils.compressImage(bitmap);
        bitmap = bitmapUtils.comp(bitmap);

        return bitmap;
    }
}
