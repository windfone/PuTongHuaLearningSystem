package com.hlxyedu.putonghualearningsystem.model.http;

import com.hlxyedu.putonghualearningsystem.model.bean.CommentVO;
import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;
import com.hlxyedu.putonghualearningsystem.model.bean.DetailVO;
import com.hlxyedu.putonghualearningsystem.model.bean.TopTitleVO;
import com.hlxyedu.putonghualearningsystem.model.bean.UserVO;
import com.hlxyedu.putonghualearningsystem.model.bean.VideoVO;
import com.hlxyedu.putonghualearningsystem.model.http.response.HttpResponse;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.Query;

/**
 * 作者：skyworth on 2017/7/11 09:56
 * 邮箱：dqwei@iflytek.com
 */

public interface HttpHelper {

//    Flowable<HttpResponse<UserVO>> getLoginBody(String uname, String pwd);

    Flowable<HttpResponse<UserVO>> getLoginBody(String username,String password);

    Flowable<HttpResponse<DetailVO>> getEssayDetails(int conId,String pinYinOrder);

    Flowable<HttpResponse<UserVO>> getUserInfo(int userId);

    Flowable<HttpResponse<List<TopTitleVO>>> getOnLineLearningTitle();

    Flowable<HttpResponse<List<DataVO>>> getOnLineLearningList(int typeId,int pageSize,int currentPage);

    Flowable<HttpResponse<List<TopTitleVO>>> getTeacherClassTitle();

    Flowable<HttpResponse<List<VideoVO>>> getTeacherClassList(int typeId, int orderBy,
                                                              int currentPage, int pageSize);

    Flowable<HttpResponse<List<DataVO>>> getWordLists(int typeId,String pinYin);

    Flowable<HttpResponse<DetailVO>> getHanZiDetails(int conId,String pinYin,String pinYinOrder);

    Flowable<HttpResponse<DetailVO>> getPinYinLearningDetails(int conId,String pinYinOrder);

    Flowable<HttpResponse<List<DetailVO>>> getWordFollowDetails(int conId,String pinYinOrder);

    Flowable<HttpResponse<VideoVO>> getTeacherClassTopDetails(int browseNum,int teaId);

    Flowable<HttpResponse<List<CommentVO>>> getCommentList(int browseNum, int currentPage, int pageSize);

}
