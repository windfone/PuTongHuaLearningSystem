package com.hlxyedu.putonghualearningsystem.utils;

import android.util.Log;

import com.blankj.utilcode.util.StringUtils;
import com.hlxyedu.putonghualearningsystem.model.http.exception.ApiException;
import com.hlxyedu.putonghualearningsystem.model.http.response.HttpResponse;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.FlowableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by codeest on 2016/8/3.
 */
public class RxUtil {

    /**
     * 统一线程处理
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<T, T> rxSchedulerHelper() {    //compose简化线程
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    public static <T> FlowableTransformer<T,T> handleTestResultT(){
        return new FlowableTransformer<T, T>() {
            @Override
            public Flowable<T> apply(Flowable<T> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<T, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(T t) throws Exception {
                        return createData(t);
                    }
                });
            }
        };
    }

    /**
     * 统一返回结果处理
     * @param <T>
     * @return
     */
    public static <T> FlowableTransformer<HttpResponse<T>, T> handleTestResult() {   //compose判断结果
        return new FlowableTransformer<HttpResponse<T>, T>() {
            @Override
            public Flowable<T> apply(Flowable<HttpResponse<T>> httpResponseFlowable) {
                return httpResponseFlowable.flatMap(new Function<HttpResponse<T>, Flowable<T>>() {
                    @Override
                    public Flowable<T> apply(HttpResponse<T> httpResponse) {

                        if(httpResponse.isSuccess()){
                            return createData(httpResponse.getData());
                        }else {
                            return Flowable.error(new ApiException( httpResponse.getMsg(),httpResponse.getStatus()));
                        }

//                        if(httpResponse.getErrorCode() == 0) {
////                            if(EmptyUtils.isEmpty(httpResponse.getData()))
//                            if(httpResponse.getData() == null)
//                            {
//                                 return createData((T)"success");
//                            }
//                            return createData(httpResponse.getData());
//                        }
////                        else if(httpResponse.getStatus() == 2){
////                            //需要发送事件
////                            RxBus.getDefault().post(new LogoutEvent(LogoutEvent.LOGOUT));
////                            return createData((T)"token error");
////                        }
//                        else {
//                            return Flowable.error(new ApiException(httpResponse.getErrorMsg(), httpResponse.getErrorCode()));
//                        }

//                        switch (httpResponse.getStatus()){
//                            case 1:
//                                if(httpResponse.getData() == null)
//                                {
//                                    return Flowable.error(new ApiException(httpResponse.getMessage(), httpResponse.getStatus()));
//                                }
//                                return createData(httpResponse.getData());
////                            case 2:
////                                RxBus.getDefault().post(new LogoutEvent(LogoutEvent.LOGOUT));
////                                return createData((T)"token error");
//                            default:
//                                return Flowable.error(new ApiException(httpResponse.getMessage(), httpResponse.getStatus()));
//                        }

                    }
                });
            }
        };
    }


//
//    /**
//     * 统一返回结果处理
//     * @param <T>
//     * @return
//     */
//    public static <T> FlowableTransformer<WXHotHttpResponse<T>, T> handleHotResult() {   //compose判断结果
//        return new FlowableTransformer<WXHotHttpResponse<T>, T>() {
//            @Override
//            public Flowable<T> apply(Flowable<WXHotHttpResponse<T>> httpResponseFlowable) {
//                return httpResponseFlowable.flatMap(new Function<WXHotHttpResponse<T>, Flowable<T>>() {
//                    @Override
//                    public Flowable<T> apply(WXHotHttpResponse<T> wxHotHttpResponse) {
//                        if(wxHotHttpResponse.getError_code() == 0) {
//                            return createData(wxHotHttpResponse.getResult());
//                        } else {
//                            return Flowable.error(new ApiException(wxHotHttpResponse.getReason(), wxHotHttpResponse.getError_code()));
//                        }
//                    }
//                });
//            }
//        };
//    }
//
//    /**
//     * 统一返回结果处理
//     * @param <T>
//     * @return
//     */
//    public static <T> FlowableTransformer<ShowHttpResponse<T>, T> handleShowResult() {   //compose判断结果
//        return new FlowableTransformer<ShowHttpResponse<T>, T>() {
//            @Override
//            public Flowable<T> apply(Flowable<ShowHttpResponse<T>> httpResponseFlowable) {
//                return httpResponseFlowable.flatMap(new Function<ShowHttpResponse<T>, Flowable<T>>() {
//                    @Override
//                    public Flowable<T> apply(ShowHttpResponse<T> wxHotHttpResponse) {
//                        if(wxHotHttpResponse.getShowapi_res_code() == 0) {
//                            return createData(wxHotHttpResponse.getShowapi_res_body());
//                        } else {
//                            return Flowable.error(new ApiException(wxHotHttpResponse.getShowapi_res_error(), wxHotHttpResponse.getShowapi_res_code()));
//                        }
//                    }
//                });
//            }
//        };
//    }

    /**
     * 生成Flowable
     * @param <T>
     * @return
     */
    public static <T> Flowable<T> createData(final T t) {
        return Flowable.create(new FlowableOnSubscribe<T>() {
            @Override
            public void subscribe(FlowableEmitter<T> emitter) throws Exception {
                try {
                    emitter.onNext(t);
                    emitter.onComplete();
                } catch (Exception e) {
                    emitter.onError(e);
                }
            }
        }, BackpressureStrategy.BUFFER);
    }

}
