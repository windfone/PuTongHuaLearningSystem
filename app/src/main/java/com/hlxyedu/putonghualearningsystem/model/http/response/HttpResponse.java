package com.hlxyedu.putonghualearningsystem.model.http.response;

/**
 * 作者：skyworth on 2017/9/7 09:56
 * 邮箱：dqwei@iflytek.com
 */

public class HttpResponse<T> {

    private String msg;
    private String resultType;
    private int status;
    private int code;
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
