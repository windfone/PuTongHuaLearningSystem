package com.hlxyedu.putonghualearningsystem.model.http;

import com.hlxyedu.putonghualearningsystem.model.bean.DataVO;
import com.hlxyedu.putonghualearningsystem.model.bean.EssayDetailVO;
import com.hlxyedu.putonghualearningsystem.model.http.response.HttpResponse;

import java.util.List;

import io.reactivex.Flowable;

/**
 * 作者：skyworth on 2017/7/11 09:56
 * 邮箱：dqwei@iflytek.com
 */

public interface HttpHelper {

//    Flowable<HttpResponse<UserVO>> getLoginBody(String uname, String pwd);

    Flowable<HttpResponse<List<DataVO>>> getEssayLists();

    Flowable<HttpResponse<EssayDetailVO>> getEssayDetails(String keys);
}
