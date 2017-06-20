package com.shandian.lu.NetWork;

import com.example.mynewslayoutlib.Bean.MyInvitationBean;
import com.example.mynewslayoutlib.Bean.ResetPassBean;
import com.shandian.lu.NetWork.BaseFile.BaseNetWork;
import com.zhyan.shandiankuaiyunlib.Bean.UpdatePassBean;

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

public class MyInvitationNetWork extends BaseNetWork {

    protected  final NetService service = getRetrofit().create(NetService.class);
    private interface NetService{
        //设缓存有效期为1天
        final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
        //查询缓存的Cache-Control设置，使用缓存
        final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
        //查询网络的Cache-Control设置。不使用缓存
        final String CACHE_CONTROL_NETWORK = "max-age=0";
        /*我的邀请*/
        //POST请求

        @GET("client/my_invitation.php")
        Observable<MyInvitationBean> getMyInvitationFromNet(@Query("id") String id);
       /*我的邀请*/

       /*修改密码*/
       @FormUrlEncoded
       @POST("client/password.php")
        Observable<UpdatePassBean> updatePassToNet(@FieldMap Map<String, String> paramMap);
       /*修改密码*/
        /*找回密码 repeat_password  new_password mobile*/
        @FormUrlEncoded
        @POST("client/reset_password.php")
        Observable<ResetPassBean> resetPassToNet(@FieldMap Map<String, String> paramMap);
        /*找回密码*/
    }

    public  void getMyInvitationFromNet(String id, Observer<MyInvitationBean> observer){
        setSubscribe(service.getMyInvitationFromNet(id),observer);
    }
    public  void updatePassToNet(Map<String,String> paramMap, Observer<UpdatePassBean> observer){
        setSubscribe(service.updatePassToNet(paramMap),observer);
    }

    public  void resetPassToNet(Map<String,String> paramMap, Observer<ResetPassBean> observer){
        setSubscribe(service.resetPassToNet(paramMap),observer);
    }

}
