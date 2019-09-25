package com.hlxyedu.putonghualearningsystem.model;

import com.hlxyedu.putonghualearningsystem.model.bean.CommentVO;
import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;
import com.hlxyedu.putonghualearningsystem.model.bean.DetailVO;
import com.hlxyedu.putonghualearningsystem.model.bean.TopTitleVO;
import com.hlxyedu.putonghualearningsystem.model.bean.UserVO;
import com.hlxyedu.putonghualearningsystem.model.bean.VideoVO;
import com.hlxyedu.putonghualearningsystem.model.http.HttpHelper;
import com.hlxyedu.putonghualearningsystem.model.http.response.HttpResponse;
import com.hlxyedu.putonghualearningsystem.model.prefs.PreferencesHelper;

import java.util.List;

import io.reactivex.Flowable;
import okhttp3.RequestBody;

/**
 * 作者：skyworth on 2017/7/11 09:55
 * 邮箱：dqwei@iflytek.com
 */

public class DataManager implements HttpHelper, PreferencesHelper {

    HttpHelper mHttpHelper;

    PreferencesHelper mPreferencesHelper;

    public DataManager(HttpHelper httpHelper, PreferencesHelper mPreferencesHelper) {
        mHttpHelper = httpHelper;
        this.mPreferencesHelper = mPreferencesHelper;
    }

    @Override
    public boolean getLoginStatus() {
        return mPreferencesHelper.getLoginStatus();
    }

    @Override
    public void setLoginStatus(boolean isLogin) {
        mPreferencesHelper.setLoginStatus(isLogin);
    }

    @Override
    public boolean getNightModeState() {
        return false;
    }

    @Override
    public void setNightModeState(boolean state) {

    }

    @Override
    public boolean getIsFrist() {
        return mPreferencesHelper.getIsFrist();
    }

    @Override
    public void setIsFrist(boolean isFrist) {
        mPreferencesHelper.setIsFrist(isFrist);
    }

    @Override
    public void setUid(String uid) {
        mPreferencesHelper.setUid(uid);
    }

    @Override
    public String getSpUserInfo() {
        return mPreferencesHelper.getSpUserInfo();
    }

    @Override
    public void setUserInfo(String userInfo) {
        mPreferencesHelper.setUserInfo(userInfo);
    }

    @Override
    public void clearLoginInfo() {
        mPreferencesHelper.clearLoginInfo();
    }

    @Override
    public Flowable<HttpResponse<UserVO>> getLoginBody(String username, String password) {
        return mHttpHelper.getLoginBody(username,password);
    }

    @Override
    public Flowable<HttpResponse<DetailVO>> getEssayDetails(int conId,String pinYinOrder) {
        return mHttpHelper.getEssayDetails(conId,pinYinOrder);
    }

    @Override
    public Flowable<HttpResponse<UserVO>> getUserInfo(int userId) {
        return mHttpHelper.getUserInfo(userId);
    }

    @Override
    public Flowable<HttpResponse<List<TopTitleVO>>> getOnLineLearningTitle() {
        return mHttpHelper.getOnLineLearningTitle();
    }

    @Override
    public Flowable<HttpResponse<List<DataVO>>> getOnLineLearningList(int typeId,int pageSize,int currentPage) {
        return mHttpHelper.getOnLineLearningList(typeId,pageSize,currentPage);
    }

    @Override
    public Flowable<HttpResponse<List<TopTitleVO>>> getTeacherClassTitle() {
        return mHttpHelper.getTeacherClassTitle();
    }

    @Override
    public Flowable<HttpResponse<List<VideoVO>>> getTeacherClassList(int typeId, int orderBy, int currentPage, int pageSize) {
        return mHttpHelper.getTeacherClassList(typeId,orderBy,currentPage,pageSize);
    }

    @Override
    public Flowable<HttpResponse<List<DataVO>>> getWordLists(int typeId,String pinYin) {
        return mHttpHelper.getWordLists(typeId,pinYin);
    }

    @Override
    public Flowable<HttpResponse<DetailVO>> getHanZiDetails(int conId, String pinYin,String pinYinOrder) {
        return mHttpHelper.getHanZiDetails(conId,pinYin,pinYinOrder);
    }

    @Override
    public Flowable<HttpResponse<DetailVO>> getPinYinLearningDetails(int conId, String pinYinOrder) {
        return mHttpHelper.getPinYinLearningDetails(conId,pinYinOrder);
    }

    @Override
    public Flowable<HttpResponse<List<DetailVO>>> getWordFollowDetails(int conId,String pinYinOrder) {
        return mHttpHelper.getWordFollowDetails(conId,pinYinOrder);
    }

    @Override
    public Flowable<HttpResponse<VideoVO>> getTeacherClassTopDetails(int browseNum, int teaId) {
        return mHttpHelper.getTeacherClassTopDetails(browseNum,teaId);
    }

    @Override
    public Flowable<HttpResponse<List<CommentVO>>> getCommentList(int browseNum, int currentPage, int pageSize) {
        return mHttpHelper.getCommentList(browseNum,currentPage,pageSize);
    }

}
