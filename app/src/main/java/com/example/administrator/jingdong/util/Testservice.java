package com.example.administrator.jingdong.util;


import com.example.administrator.jingdong.bean.Shop_xiangqing;
import com.example.administrator.jingdong.bean.Sousuobean;
import com.example.administrator.jingdong.faxian.bean.User;
import com.example.administrator.jingdong.fenlei.bean.Addgood;
import com.example.administrator.jingdong.fenlei.bean.Feileirightbean;
import com.example.administrator.jingdong.gouwuche.bean.Gouwuche;
import com.example.administrator.jingdong.shouye.bean.Feileileftbean;
import com.example.administrator.jingdong.shouye.bean.Shouyebean;
import com.example.administrator.jingdong.shouye.bean.Sousuoxiangqingbean;
import com.example.administrator.jingdong.wode.bean.Denglubean;
import com.example.administrator.jingdong.wode.bean.Tuijianbean;
import com.example.administrator.jingdong.wode.bean.Upnamebean;
import com.example.administrator.jingdong.wode.bean.Zhucebean;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/2/4.
 */

public interface Testservice {
    //首页的接口
    @GET("ad/getAd")
    Observable<Shouyebean> getShouye();

    //分类左
    @GET("product/getCatagory")
    Observable<Feileileftbean> getfeileileft();

    //分类右
    @POST("product/getProductCatagory")
    @FormUrlEncoded
    Observable<Feileirightbean> getfenleiright(@FieldMap Map<String,String> map);

    //当前子分类下的商品列表
    @POST("product/getProducts")
    @FormUrlEncoded
    Observable<Sousuobean> postsousuo(@FieldMap Map<String,String> map);

    //商品详情
    @POST("product/getProductDetail?source=android")
    @FormUrlEncoded
    Observable<Shop_xiangqing> postgetshop(@FieldMap Map<String,String> map);

    //添加购物车
    @POST("product/addCart?source=android&uid=71")
    @FormUrlEncoded
    Observable<Addgood> addshop(@FieldMap Map<String,String> map);

    //发现
    @POST("a2a/impressApi/news/mergeList?pageSize=10")
    @FormUrlEncoded
    Observable<User> faxianjson(@FieldMap Map<String,String> map);


    //搜索详情
    @POST("product/searchProducts?source=android")
    @FormUrlEncoded
    Observable<Sousuoxiangqingbean> getsouxaing(@FieldMap Map<String,String> map);


  //购物车
    @GET("product/getCarts?source=android&uid=72")
    Observable<Gouwuche> getUser();


    @POST("user/login")@FormUrlEncoded
    Observable<Denglubean> denglu(@FieldMap Map<String,String> map);

    @POST("user/getUserInfo")@FormUrlEncoded
    Observable<Denglubean> getuser(@FieldMap Map<String,String> map);

    @GET("ad/getAd")
    Observable<Tuijianbean> gettuijian();

    @POST("user/reg")@FormUrlEncoded
    Observable<Zhucebean> zhuce(@FieldMap Map<String,String> map);

    //修改昵称
    @POST("user/updateNickName")@FormUrlEncoded
    Observable<Upnamebean> myupname(@FieldMap Map<String,String> map);

    //第二种方式上传头像
    @POST("file/upload")
    @Multipart
    Observable<Upnamebean> upload2(@Query("uid") String uid, @Part MultipartBody.Part file);
}
