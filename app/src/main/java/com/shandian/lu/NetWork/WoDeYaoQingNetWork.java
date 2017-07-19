package com.shandian.lu.NetWork;

import com.example.mynewslayoutlib.Bean.NewGeRenXinXiBean;
import com.example.mynewslayoutlib.Bean.NewGeRenXinXiSubmitBean;
import com.example.mynewslayoutlib.Bean.NewMyTuiJianRenBean;
import com.example.mynewslayoutlib.Bean.NewQrCodeSubmitBean;
import com.example.mynewslayoutlib.Bean.NewSiJiLocBean;
import com.example.mynewslayoutlib.Bean.NewYaoQingBean;
import com.shandian.lu.NetWork.BaseFile.BaseNetWork;
import com.zhyan.shandiankuaiyunlib.Bean.IsRegisterBean;
import com.zhyan.shandiankuaiyunlib.Bean.LoginBean;
import com.zhyan.shandiankuaiyunlib.Bean.MyCarSourceBean;
import com.zhyan.shandiankuaiyunlib.Bean.MyHuoSourceBean;
import com.zhyan.shandiankuaiyunlib.Bean.MyMessageBean;
import com.zhyan.shandiankuaiyunlib.Bean.RegisterBean;
import com.zhyan.shandiankuaiyunlib.Bean.UpdateResultBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;
import rx.Observer;

/**
 * Created by az on 2017/4/26.
 */

public class WoDeYaoQingNetWork extends BaseNetWork {

    protected  final NetService service = getRetrofit().create(NetService.class);
    private interface NetService{
        //设缓存有效期为1天
        final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
        //查询缓存的Cache-Control设置，使用缓存
        final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
        //查询网络的Cache-Control设置。不使用缓存
        final String CACHE_CONTROL_NETWORK = "max-age=0";

        /*二维码提交*/
        @FormUrlEncoded
        @POST("index.php/app/chyuan/ewmsm")
        Observable<NewQrCodeSubmitBean> submitNewQrCodeToNet(@FieldMap Map<String, Object> paramMap);
        /*二维码提交*/
        /*我的推荐人*/
        @GET("index.php/app/index/mytj")
        Observable<NewMyTuiJianRenBean> getMyTuiJianRenFromNet(@Query("id") String id);
        /*我的推荐人*/
        /*邀请人信息*/
        @GET("index.php/app/index/yaoqingren")
        Observable<NewYaoQingBean> getYaoQingRenFromNet(@Query("id") String id,@Query("dj") String dj);
        /*邀请人信息*/



    }


    public  void submitNewQrCodeToNet(Map<String,Object> paramMap,Observer<NewQrCodeSubmitBean> observer){
        setSubscribe(service.submitNewQrCodeToNet(paramMap),observer);
    }
    public  void getMyTuiJianRenFromNet(String id,Observer<NewMyTuiJianRenBean> observer){
        setSubscribe(service.getMyTuiJianRenFromNet(id),observer);
    }
    public  void getYaoQingRenFromNet(String id,String dj,Observer<NewYaoQingBean> observer){
        setSubscribe(service.getYaoQingRenFromNet(id,dj),observer);
    }
}
