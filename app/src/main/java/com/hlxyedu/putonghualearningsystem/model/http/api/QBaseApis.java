package com.hlxyedu.putonghualearningsystem.model.http.api;

import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;
import com.hlxyedu.putonghualearningsystem.model.bean.DetailVO;
import com.hlxyedu.putonghualearningsystem.model.bean.TopTitleVO;
import com.hlxyedu.putonghualearningsystem.model.bean.UserVO;
import com.hlxyedu.putonghualearningsystem.model.bean.VideoVO;
import com.hlxyedu.putonghualearningsystem.model.http.response.HttpResponse;

import java.util.List;

import io.reactivex.Flowable;
import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
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

    // 用户登录
    @GET("user/login")
    Flowable<HttpResponse<UserVO>> getLoginBody(@Query("username") String username,@Query("password") String password);

    // 获取 登录用户信息
    @GET("user/getUserInfo")
    Flowable<HttpResponse<UserVO>> getUserInfo(@Query("userId") int userId);

    // 获取 在线学习 头部标题
    @GET("study/selectType")
    Flowable<HttpResponse<List<TopTitleVO>>> getOnLineLearningTitle();

    // 获取 拼音学习  单词跟读 短文跟读列表
    @GET("study/getContentTile")
    Flowable<HttpResponse<List<DataVO>>> getOnLineLearningList(@Query("typeId") int typeId ,@Query("pageSize") int pageSize,@Query("currentPage") int currentPage);

    // 获取 汉字学习 轻声字 儿化音列表
    @GET("study/selectPinYin")
    Flowable<HttpResponse<List<DataVO>>> getWordLists(@Query("typeId") int typeId,@Query("pinYin") String pinYin);

    // 获取 拼音学习详情
    @GET("study/selectPinYinStudy")
    Flowable<HttpResponse<DetailVO>> getPinYinLearningDetails(@Query("conId") int conId,@Query("pinYinOrder") String pinYinOrder);

    // 获取 单词跟读 详情
    @GET("study/selectWordsRead")
    Flowable<HttpResponse<List<DetailVO>>> getWordFollowDetails(@Query("pinYinOrder") String pinYinOrder);

    // 获取 短文跟读 详情
    @GET("study/selectPassageDetail")
    Flowable<HttpResponse<DetailVO>> getEssayDetails(@Query("conId") int conId,@Query("pinYinOrder") String pinYinOrder);

    // 获取 汉字学习 轻声字 儿化音 详情
    @GET("study/selectDetail")
    Flowable<HttpResponse<DetailVO>> getHanZiDetails(@Query("conId") int conId,@Query("pinYin") String pinYin,@Query("pinYinOrder") String pinYinOrder);

    // 获取 名师课堂 头部标题
    @GET("browse/getTeacherType")
    Flowable<HttpResponse<List<TopTitleVO>>> getTeacherClassTitle();

    // 获取 名师课堂 列表
    @GET("browse/geTeacherTitle")
    Flowable<HttpResponse<List<VideoVO>>> getTeacherClassList(@Query("typeId") int typeId,@Query("orderBy") int orderBy,
                                                                @Query("currentPage") int currentPage,@Query("pageSize") int pageSize);
}


