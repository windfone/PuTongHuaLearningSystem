package com.hlxyedu.putonghualearningsystem.model.http.api;

import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;
import com.hlxyedu.putonghualearningsystem.model.bean.EssayDetailVO;
import com.hlxyedu.putonghualearningsystem.model.bean.OnLineLearnTitleVO;
import com.hlxyedu.putonghualearningsystem.model.bean.UserVO;
import com.hlxyedu.putonghualearningsystem.model.http.response.HttpResponse;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 作者：skyworth on 2017/9/7 09:51
 * 邮箱：dqwei@iflytek.com
 * <p>
 * http://www.jianshu.com/p/73216939806a  Retrofit2 使用说明
 */

public interface QBaseApis {

    /*@GET("json/login")
    Flowable<HttpResponse<UserVO>> getLoginBody(@Query("uname") String uname, @Query("pwd") String pwd);

    @FormUrlEncoded
    @POST("json/login")
    Flowable<HttpResponse<RegisterVO>> postRegisterBody(@Field("uname") String uname, @Field("pwd") String pwd, @Field("tname") String tname);

    //获取教材列表
    @FormUrlEncoded
    @POST("json")
    Flowable<HttpResponse<List<BookVO>>> getBookList(@Field("uid") String uid, @Field("type") String type);

    //获取会员书架
    @GET("json/user")
    Flowable<HttpResponse<List<BookShelfVO>>> getBookShelfList(@Query("uid") String uid);

    *//* 修改个人信息 *//*
    @FormUrlEncoded
    @PUT("json/login")
    Flowable<HttpResponse<UserVO>> putModifyInfo(@Field("id") String id, @Field("pwd") String pwd
            , @Field("tname") String tname, @Field("tel") String tel, @Field("addr") String addr);*/

    // 获取 登录用户信息
    @GET("user/getUserInfo")
    Flowable<HttpResponse<UserVO>> getUserInfo(@Query("userId") int userId);

    // 获取 在线学习 头部标题
    @GET("study/selectType")
    Flowable<HttpResponse<List<OnLineLearnTitleVO>>> getOnLineLearningTitle();

    // 获取 拼音学习列表
    @GET("study/getContentTile")
    Flowable<HttpResponse<List<DataVO>>> getOnLineLearningList(@Query("typeId") int typeId);

    // 获取 短文跟读列表
    @GET("Mp3List")
    Flowable<HttpResponse<List<DataVO>>> getEssayLists();

    @GET("playUrl")
    Flowable<HttpResponse<EssayDetailVO>> getEssayDetails(@Query("keys") String keys);

}


