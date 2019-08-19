package com.skyworth.rxqwelibrary.api;

import android.text.TextUtils;

import com.blankj.utilcode.util.NetworkUtils;
import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：skyworth on 2017/7/10 14:57
 * 邮箱：dqwei@iflytek.com
 */

public class Api {
    private static final String TAG = Api.class.getSimpleName();

    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 7676;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 7676;

    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    private static final String CACHE_CONTROL_AGE = "max-age=0";

    /**
     * 云端响应头拦截器，用来配置缓存策略
     * Dangerous interceptor that rewrites the server's cache-control header.
     */
    public static final Interceptor mRewriteCacheControlInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String cacheControl = request.cacheControl().toString();
            if (!NetworkUtils.isConnected()) {
                request = request.newBuilder()
                        .cacheControl(TextUtils.isEmpty(cacheControl)? CacheControl.FORCE_NETWORK: CacheControl.FORCE_CACHE)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetworkUtils.isConnected()) {
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置

                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            } else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };

    /**
     * token 拦截器
     */
    /**
     * 全局自动刷新Token的拦截器
     * <p>
     * 作者：余天然 on 16/9/5 下午3:31
     * http://www.jianshu.com/p/8d1ee61bc2d2
     * http://www.jianshu.com/p/62ab11ddacc8  方法二
     */
    public static Interceptor TokenInterceptor =new Interceptor() {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Response response = chain.proceed(request);
            Logger.d(TAG,"response.code=" + response.code());



//            if()

//            if (isTokenExpired(response)) {//根据和服务端的约定判断token过期
//                Logger.d(TAG,"静默自动刷新Token,然后重新请求数据");
//                //同步请求方式，获取最新的Token
//                String newSession = getNewToken();
//                //使用新的Token，创建新的请求
//                Request newRequest = chain.request()
//                        .newBuilder()
//                        .header("Cookie", "JSESSIONID=" + newSession)
//                        .build();
//                //重新请求
//                return chain.proceed(newRequest);
//            }
            return response;
        }

        /**
         * 根据Response，判断Token是否失效
         *
         * @param response
         * @return
         */
        private boolean isTokenExpired(Response response) {
            if (response.code() == 404) {
                return true;
            }
            return false;
        }

        /**
         * 同步请求方式，获取最新的Token
         *
         * @return
         */
        private String getNewToken() throws IOException {
            // 通过一个特定的接口获取新的token，此处要用到同步的retrofit请求
//            User user = AppContext.getInstance().getUser();
//            String username = user.getName();
//            String password = user.getPasswd();

//            LogUtil.debug(TAG,"loginInfo=" + user.toString());
//            Call<Response_Login> call = WebHelper.getSyncInterface().synclogin(new Request_Login(username, password));
//            loginInfo = call.execute().body();
//            LogUtil.print("loginInfo=" + loginInfo.toString());
//
//            loginInfo.setPassword(password);
//            CacheManager.saveLoginInfo(loginInfo);
            return "da";
        }
    };
}
