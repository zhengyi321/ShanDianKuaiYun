package com.shandian.lu.Main.MineFragment.WoDeRenZheng;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynewslayoutlib.Bean.NewMyRenZhengDetailBean;
import com.example.mynewslayoutlib.Bean.NewRenZhengSubmitResultBean;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.shandian.lu.BaseController;
import com.shandian.lu.Main.MineFragment.Login.LoginActivity;
import com.shandian.lu.NetWork.RenZhengNetWork;
import com.shandian.lu.R;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheManager.XCCacheManager;
import com.zhyan.shandiankuaiyuanwidgetlib.DBCache.XCCacheSaveName.XCCacheSaveName;
import com.zhyan.shandiankuaiyunlib.Utils.ImageLoaderUtils;
import com.zhyan.shandiankuaiyunlib.Widget.ImageView.RoundCornerImageView.RoundCornerImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observer;

/**
 * Created by Administrator on 2017/7/3.
 */

public class NewWoDeRenZhengController extends BaseController {


    @BindView(R.id.rly_new_rz_back)
    RelativeLayout rlyNewRZBack;
    @OnClick(R.id.rly_new_rz_back)
    public void rlyNewRZBackOnclick(){
        activity.finish();
    }
    @BindView(R.id.iv_new_rz_logo)
    ImageView ivNewRZLogo;
    @BindView(R.id.pb_new_rz)
    ProgressBar pbNewRZ;
    @BindView(R.id.tv_new_rz_status1)
    TextView tvNewRZStatus1;
/*    @BindView(R.id.tv_new_rz_status2)
    TextView tvNewRZStatus2;*/
    @BindView(R.id.rciv_new_rz_sm_p1)
    RoundCornerImageView rcivNewRZSMP1;
    @BindView(R.id.rciv_new_rz_sm_p2)
    RoundCornerImageView rcivNewRZSMP2;
    @BindView(R.id.rciv_new_rz_sm_p3)
    RoundCornerImageView rcivNewRZSMP3;
    @BindView(R.id.rciv_new_rz_qy_p1)
    RoundCornerImageView rcivNewRZQYP1;
    @BindView(R.id.rciv_new_rz_qy_p2)
    RoundCornerImageView rcivNewRZQYP2;
    @BindView(R.id.rciv_new_rz_qy_p3)
    RoundCornerImageView rcivNewRZQYP3;
    @BindView(R.id.rciv_new_rz_jsz)
    RoundCornerImageView rcivNewRZJSZ;
    @BindView(R.id.rciv_new_rz_xsz)
    RoundCornerImageView rcivNewRZXSZ;
    @BindView(R.id.bt_new_rz_submit)
    Button btNewRZSubmit;
    public String loginId = "";
    public List<String> imgList_SMRZ_PHOTO1 ;
    public List<String> imgList_SMRZ_PHOTO2 ;
    public List<String> imgList_SMRZ_PHOTO3 ;
    public List<String> imgList_QYRZ_PHOTO ;
    public List<String> imgList_JSZ_PHOTO ;
    public List<String> imgList_XSZ_PHOTO ;
    private List<String> imgList1,imgList2,imgList3,imgList4,imgList5,imgList6;
    public NewWoDeRenZhengController(Activity activity1,List<String> imgListSM1,List<String> imgListSM2,List<String> imgListSM3,List<String> imgListQY,List<String> imgListJSZ,List<String> imgListXSZ){
        activity = activity1;
        imgList1 = imgListSM1;
        imgList2 = imgListSM2;
        imgList3 = imgListSM3;
        imgList4 = imgListQY;
        imgList5 = imgListJSZ;
        imgList6 = imgListXSZ;
        init();
    }


    @Override
    protected void init() {
        ButterKnife.bind(this,activity);
        getRenZhengDetailFromNet();
        imgList_SMRZ_PHOTO1 = new ArrayList<>();
        imgList_SMRZ_PHOTO2 = new ArrayList<>();
        imgList_SMRZ_PHOTO3 = new ArrayList<>();
        imgList_QYRZ_PHOTO = new ArrayList<>();
        imgList_JSZ_PHOTO = new ArrayList<>();
        imgList_XSZ_PHOTO = new ArrayList<>();

    }

    private void getRenZhengDetailFromNet(){
        XCCacheManager xcCacheManager = XCCacheManager.getInstance(activity);
        XCCacheSaveName xcCacheSaveName = new XCCacheSaveName();
        loginId = xcCacheManager.readCache(xcCacheSaveName.logId);
        if((loginId == null)||(loginId.isEmpty())){
            Intent intent = new Intent(activity, LoginActivity.class);
            activity.startActivity(intent);
            return;
        }
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("login_id",loginId);
        pbNewRZ.setVisibility(View.VISIBLE);
        RenZhengNetWork renZhengNetWork = new RenZhengNetWork();
        renZhengNetWork.getRenZhengDetailFromNet(paramMap, new Observer<NewMyRenZhengDetailBean>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(NewMyRenZhengDetailBean newMyRenZhengDetailBean) {
                pbNewRZ.setVisibility(View.GONE);
                initDetail(newMyRenZhengDetailBean);
            }
        });

    }

    public void initDetail(NewMyRenZhengDetailBean newMyRenZhengDetailBean){
        String loginId2 = newMyRenZhengDetailBean.getNr().getLogin_id();
        if(!loginId.equals(loginId2)){
            return;
        }else{
            String auth = newMyRenZhengDetailBean.getNr().getIs_auth();

            String sfz1 = newMyRenZhengDetailBean.getNr().getSfzzm();
            imgList_SMRZ_PHOTO1.clear();
            imgList_SMRZ_PHOTO1.add(sfz1);
            imgList1.clear();
            imgList1.add(sfz1);

            /*Toast.makeText(activity,"sfz111"+imgList_SMRZ_PHOTO1,3000).show();*/
            String sfz2 = newMyRenZhengDetailBean.getNr().getSfzfm();
            imgList_SMRZ_PHOTO2.clear();
            imgList_SMRZ_PHOTO2.add(sfz2);
            imgList2.clear();
            imgList2.add(sfz2);

            String sfz3 = newMyRenZhengDetailBean.getNr().getScsfz();
            imgList_SMRZ_PHOTO3.clear();
            imgList_SMRZ_PHOTO3.add(sfz3);
            imgList3.clear();
            imgList3.add(sfz3);

            String yyzz = newMyRenZhengDetailBean.getNr().getYyzz();
            imgList_QYRZ_PHOTO.clear();
            imgList_QYRZ_PHOTO.add(yyzz);
            imgList4.clear();
            imgList4.add(yyzz);
            String jsz = newMyRenZhengDetailBean.getNr().getJsz();
            imgList_JSZ_PHOTO.clear();
            imgList_JSZ_PHOTO.add(jsz);
            imgList5.clear();
            imgList5.add(jsz);
            String xsz = newMyRenZhengDetailBean.getNr().getXsz();
            imgList_XSZ_PHOTO.clear();
            imgList_XSZ_PHOTO.add(xsz);
            imgList6.clear();
            imgList6.add(xsz);
            if(auth.equals("0")){
                tvNewRZStatus1.setText("审核中...");
            }
            else if(auth.equals("1")){
                tvNewRZStatus1.setText("恭喜，审核通过！");
                ivNewRZLogo.setImageResource(R.mipmap.rz_success_logo);
                tvNewRZStatus1.setTextColor(activity.getResources().getColor(R.color.colorRenZhenSuccess));
            /*    tvNewRZStatus2.setVisibility(View.GONE);*/
            }
            else if(auth.equals("2")){
                tvNewRZStatus1.setText("认证失败！");
            }

            ImageLoader.getInstance().displayImage(sfz1,rcivNewRZSMP1, ImageLoaderUtils.options1);
            ImageLoader.getInstance().displayImage(sfz2,rcivNewRZSMP2, ImageLoaderUtils.options1);
            ImageLoader.getInstance().displayImage(sfz3,rcivNewRZSMP3, ImageLoaderUtils.options1);
            ImageLoader.getInstance().displayImage(yyzz,rcivNewRZQYP2, ImageLoaderUtils.options1);
            ImageLoader.getInstance().displayImage(jsz,rcivNewRZJSZ, ImageLoaderUtils.options1);
            ImageLoader.getInstance().displayImage(xsz,rcivNewRZXSZ, ImageLoaderUtils.options1);

        }

    }
    public void initDetailAfterSubmit(NewRenZhengSubmitResultBean newMyRenZhengDetailBean){
        String loginId2 = newMyRenZhengDetailBean.getNr().getLogin_id();
        if(!loginId.equals(loginId2)){
            return;
        }else{
            String auth = newMyRenZhengDetailBean.getNr().getIs_auth();

            String sfz1 = newMyRenZhengDetailBean.getNr().getSfzzm();


            imgList_SMRZ_PHOTO1.add(sfz1);
            Toast.makeText(activity,"sfz1"+imgList_SMRZ_PHOTO1,3000).show();
            String sfz2 = newMyRenZhengDetailBean.getNr().getSfzfm();
            imgList_SMRZ_PHOTO2.add(sfz2);
            String sfz3 = newMyRenZhengDetailBean.getNr().getScsfz();
            imgList_SMRZ_PHOTO3.add(sfz3);
            String yyzz = newMyRenZhengDetailBean.getNr().getYyzz();
            imgList_QYRZ_PHOTO.add(yyzz);
            String jsz = newMyRenZhengDetailBean.getNr().getJsz();
            imgList_JSZ_PHOTO.add(jsz);
            String xsz = newMyRenZhengDetailBean.getNr().getXsz();
            imgList_XSZ_PHOTO.add(xsz);
            if(auth.equals("0")){
                tvNewRZStatus1.setText("审核中...");
                ivNewRZLogo.setImageResource(R.mipmap.rz_fail_gray_logo);
                tvNewRZStatus1.setTextColor(activity.getResources().getColor(R.color.black));

            }
            else if(auth.equals("1")){
                tvNewRZStatus1.setText("恭喜，审核通过！");
                ivNewRZLogo.setImageResource(R.mipmap.rz_success_logo);
                tvNewRZStatus1.setTextColor(activity.getResources().getColor(R.color.colorRenZhenSuccess));
   /*             tvNewRZStatus2.setVisibility(View.GONE);*/
            }
            else if(auth.equals("2")){
                tvNewRZStatus1.setText("认证失败！");
                ivNewRZLogo.setImageResource(R.mipmap.rz_fail_gray_logo);
                tvNewRZStatus1.setTextColor(activity.getResources().getColor(R.color.black));
            }

            ImageLoader.getInstance().displayImage(sfz1,rcivNewRZSMP1, ImageLoaderUtils.options1);
            ImageLoader.getInstance().displayImage(sfz2,rcivNewRZSMP2, ImageLoaderUtils.options1);
            ImageLoader.getInstance().displayImage(sfz3,rcivNewRZSMP3, ImageLoaderUtils.options1);
            ImageLoader.getInstance().displayImage(yyzz,rcivNewRZQYP2, ImageLoaderUtils.options1);
            ImageLoader.getInstance().displayImage(jsz,rcivNewRZJSZ, ImageLoaderUtils.options1);
            ImageLoader.getInstance().displayImage(xsz,rcivNewRZXSZ, ImageLoaderUtils.options1);
            activity.finish();
        }

    }
}
