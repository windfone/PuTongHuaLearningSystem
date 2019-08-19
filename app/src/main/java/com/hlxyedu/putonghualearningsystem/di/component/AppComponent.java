package com.hlxyedu.putonghualearningsystem.di.component;


import com.hlxyedu.putonghualearningsystem.app.AppContext;
import com.hlxyedu.putonghualearningsystem.di.module.AppModule;
import com.hlxyedu.putonghualearningsystem.di.module.HttpModule;
import com.hlxyedu.putonghualearningsystem.model.DataManager;
import com.hlxyedu.putonghualearningsystem.model.http.RetrofitHelper;

import javax.inject.Singleton;

import dagger.Component;

/**
 * 作者：skyworth on 2017/7/10 14:40
 * 邮箱：dqwei@iflytek.com
 */
@Singleton
@Component(modules = {AppModule.class, HttpModule.class})
public interface AppComponent {

    AppContext getContext();  // 提供App的Context

    DataManager getDataManager(); //数据中心

    RetrofitHelper retrofitHelper();  //提供http的帮助类
}
