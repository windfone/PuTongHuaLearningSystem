package com.hlxyedu.putonghualearningsystem.di.module;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.Registry;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;
import com.skyworth.rxqwelibrary.utils.HttpsUtils;

import java.io.InputStream;

import okhttp3.OkHttpClient;

//public class OkHttpGlideModule implements GlideModule {
//    @Override
//    public void applyOptions(Context context, GlideBuilder builder) {
//
//    }
//
//    @Override
//    public void registerComponents(@NonNull Context context, @NonNull Glide glide, @NonNull Registry registry) {
//        OkHttpClient client = HttpsUtils.getUnsafeOkHttpClient();
//        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(client));
////        HttpModule httpModule = new HttpModule();
////        OkHttpClient okHttpClient = httpModule.provideClient(new OkHttpClient.Builder());
////        registry.replace(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory(okHttpClient));
//    }
//}
