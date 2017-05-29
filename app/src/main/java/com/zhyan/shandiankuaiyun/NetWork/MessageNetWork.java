package com.zhyan.shandiankuaiyun.NetWork;

import com.zhyan.shandiankuaiyun.NetWork.BaseFile.BaseNetWork;
import com.zhyan.shandiankuaiyunlib.Bean.IsRegisterBean;
import com.zhyan.shandiankuaiyunlib.Bean.LoginBean;
import com.zhyan.shandiankuaiyunlib.Bean.MyCarSourceBean;
import com.zhyan.shandiankuaiyunlib.Bean.MyHuoSourceBean;
import com.zhyan.shandiankuaiyunlib.Bean.MyMessageBean;
import com.zhyan.shandiankuaiyunlib.Bean.RegisterBean;
import com.zhyan.shandiankuaiyunlib.Bean.SystemInfoBean;
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

public class MessageNetWork extends BaseNetWork {

    protected  final NetService service = getRetrofit().create(NetService.class);
    private interface NetService{
        //设缓存有效期为1天
        final long CACHE_STALE_SEC = 60 * 60 * 24 * 1;
        //查询缓存的Cache-Control设置，使用缓存
        final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
        //查询网络的Cache-Control设置。不使用缓存
        final String CACHE_CONTROL_NETWORK = "max-age=0";
        /*系统信息*/
        //GET请求
        @GET("client/message.php")
        Observable<SystemInfoBean> getSystemInfoFromNet(@Query("login_id") String login_id);
       /*系统信息*/

    }

    public  void getSystemInfoFromNet(String login_id, Observer<SystemInfoBean> observer){
        setSubscribe(service.getSystemInfoFromNet(login_id),observer);
    }

}
