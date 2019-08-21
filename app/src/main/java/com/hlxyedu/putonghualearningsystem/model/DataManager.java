package com.hlxyedu.putonghualearningsystem.model;

import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;
import com.hlxyedu.putonghualearningsystem.model.bean.EssayDetailVO;
import com.hlxyedu.putonghualearningsystem.model.http.HttpHelper;
import com.hlxyedu.putonghualearningsystem.model.http.response.HttpResponse;
import com.hlxyedu.putonghualearningsystem.model.prefs.PreferencesHelper;

import java.util.List;

import io.reactivex.Flowable;

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
    public String getUserInfo() {
        return mPreferencesHelper.getUserInfo();
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
    public Flowable<HttpResponse<List<DataVO>>> getEssayLists() {
        return mHttpHelper.getEssayLists();
    }

    @Override
    public Flowable<HttpResponse<EssayDetailVO>> getEssayDetails(String keys) {
        return mHttpHelper.getEssayDetails(keys);
    }
}
