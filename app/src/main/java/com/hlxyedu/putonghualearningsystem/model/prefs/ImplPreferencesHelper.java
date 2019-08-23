package com.hlxyedu.putonghualearningsystem.model.prefs;

import android.content.Context;
import android.content.SharedPreferences;


import com.hlxyedu.putonghualearningsystem.api.Constants;
import com.hlxyedu.putonghualearningsystem.app.AppContext;

import javax.inject.Inject;

/**
 * @author: skyworth
 * @date: 2017/4/21
 * @description:
 */

public class ImplPreferencesHelper implements PreferencesHelper {

    private static final boolean DEFAULT_NIGHT_MODE = false;
    private static final boolean DEFAULT_NO_IMAGE = false;
    private static final boolean DEFAULT_AUTO_SAVE = true;

    private static final boolean DEFAULT_LIKE_POINT = false;
    private static final boolean DEFAULT_VERSION_POINT = false;
    private static final boolean DEFAULT_MANAGER_POINT = false;

//    private static final int DEFAULT_CURRENT_ITEM = Constants.TYPE_ZHIHU;


    private final SharedPreferences mSPrefs;

    @Inject
    public ImplPreferencesHelper() {
        mSPrefs = AppContext.getInstance().getSharedPreferences(Constants.BOSS, Context.MODE_PRIVATE);
    }

    @Override
    public boolean getNightModeState() {
//        return mSPrefs.getBoolean(Constants.SP_NIGHT_MODE, DEFAULT_NIGHT_MODE);
        return true;
    }

    @Override
    public void setNightModeState(boolean state) {
//        mSPrefs.edit().putBoolean(Constants.SP_NIGHT_MODE, state).apply();
    }

    @Override
    public void setLoginStatus(boolean isLogin) {
        mSPrefs.edit().putBoolean(Constants.LOGIN_STATUS, isLogin).apply();
    }

    @Override
    public boolean getLoginStatus() {
        return mSPrefs.getBoolean(Constants.LOGIN_STATUS, false);
    }

    @Override
    public void setIsFrist(boolean isFrist) {
        mSPrefs.edit().putBoolean(Constants.IS_FRIST, isFrist).apply();
    }

    @Override
    public boolean getIsFrist() {
        return mSPrefs.getBoolean(Constants.IS_FRIST, true);
    }

    @Override
    public void setUserInfo(String userInfo) {
        mSPrefs.edit().putString(Constants.USER_INFO, userInfo).apply();
    }

    @Override
    public String getSpUserInfo() {
        return mSPrefs.getString(Constants.USER_INFO, "");
    }

    @Override
    public void clearLoginInfo() {
        mSPrefs.edit().remove(Constants.UID).apply();
        mSPrefs.edit().remove(Constants.USER_INFO).apply();
        mSPrefs.edit().remove(Constants.LOGIN_STATUS).apply();
    }

    @Override
    public void setUid(String uid) {
        mSPrefs.edit().putString(Constants.UID, uid).apply();
    }
}
