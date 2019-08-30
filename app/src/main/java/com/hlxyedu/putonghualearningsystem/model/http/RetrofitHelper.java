package com.hlxyedu.putonghualearningsystem.model.http;


import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;
import com.hlxyedu.putonghualearningsystem.model.bean.DetailVO;
import com.hlxyedu.putonghualearningsystem.model.bean.TopTitleVO;
import com.hlxyedu.putonghualearningsystem.model.bean.UserVO;
import com.hlxyedu.putonghualearningsystem.model.bean.VideoVO;
import com.hlxyedu.putonghualearningsystem.model.http.api.ManageApis;
import com.hlxyedu.putonghualearningsystem.model.http.api.QBaseApis;
import com.hlxyedu.putonghualearningsystem.model.http.response.HttpResponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Flowable;
import okhttp3.RequestBody;

/**
 *
 */
public class RetrofitHelper implements HttpHelper {

    private QBaseApis qBaseApis;

    private ManageApis manageApis;

    @Inject
    public RetrofitHelper(QBaseApis qBaseApis, ManageApis manageApis) {
        this.qBaseApis = qBaseApis;
        this.manageApis = manageApis;
    }

    @Override
    public Flowable<HttpResponse<UserVO>> getLoginBody(String username, String password) {
        return qBaseApis.getLoginBody(username,password);
    }

    @Override
    public Flowable<HttpResponse<DetailVO>> getEssayDetails(int conId,String pinYinOrder) {
        return qBaseApis.getEssayDetails(conId,pinYinOrder);
    }

    @Override
    public Flowable<HttpResponse<UserVO>> getUserInfo(int userId) {
        return qBaseApis.getUserInfo(userId);
    }

    @Override
    public Flowable<HttpResponse<List<TopTitleVO>>> getOnLineLearningTitle() {
        return qBaseApis.getOnLineLearningTitle();
    }

    @Override
    public Flowable<HttpResponse<List<DataVO>>> getOnLineLearningList(int typeId,int pageSize,int currentPage) {
        return qBaseApis.getOnLineLearningList(typeId,pageSize,currentPage);
    }

    @Override
    public Flowable<HttpResponse<List<TopTitleVO>>> getTeacherClassTitle() {
        return qBaseApis.getTeacherClassTitle();
    }

    @Override
    public Flowable<HttpResponse<List<VideoVO>>> getTeacherClassList(int typeId, int orderBy, int currentPage, int pageSize) {
        return qBaseApis.getTeacherClassList(typeId,orderBy,currentPage,pageSize);
    }

    @Override
    public Flowable<HttpResponse<List<DataVO>>> getWordLists(int typeId,String pinYin) {
        return qBaseApis.getWordLists(typeId,pinYin);
    }

    @Override
    public Flowable<HttpResponse<DetailVO>> getHanZiDetails(int conId, String pinYin,String pinYinOrder) {
        return qBaseApis.getHanZiDetails(conId,pinYin,pinYinOrder);
    }

    @Override
    public Flowable<HttpResponse<DetailVO>> getPinYinLearningDetails(int conId, String pinYinOrder) {
        return qBaseApis.getPinYinLearningDetails(conId,pinYinOrder);
    }

    @Override
    public Flowable<HttpResponse<List<DetailVO>>> getWordFollowDetails(String pinYinOrder) {
        return qBaseApis.getWordFollowDetails(pinYinOrder);
    }

}
