package com.hlxyedu.putonghualearningsystem.model.http.api;

public interface ManageApis {

    /**
     * manager
     * APP_ID
     * 版本升级使用
     */
    // 测试环境
    public static final String APP_ID = "00000000000000006001";


    // apk更新的配置信息
    //配置HOST
    // 开发环境
    public static String HOST = "http://www.baidu.com";


//    @POST("common/base-server/project-version/check-new-version")
//    Flowable<VersionVO> getNewVersion(@Body RequestBody requestBody);
//
//    @GET("common/cloud-file-server/file/get-file-url")
//    Flowable<FileUrlVO> getFileUrl(@Query("fid") String fid);
}
