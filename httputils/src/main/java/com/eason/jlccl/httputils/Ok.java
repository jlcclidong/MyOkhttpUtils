package com.eason.jlccl.httputils;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import com.eason.jlccl.httputils.headerparams.Param;
import com.eason.jlccl.httputils.request.GetRequest;
import com.eason.jlccl.httputils.request.PostRequest;
import com.eason.jlccl.httputils.utils.Log;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.CookieJar;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Created by jlccl on 2017/2/17.
 */

public class Ok {
    private static OkHttpClient.Builder builder;
    private static OkHttpClient okHttpClient;
    private static Map<String, String> headers;
    private static List<Param> params;
    private static Handler mHandler;

    private Ok(Builder builder) {
        this.builder = builder.builder;
    }

    public static Builder init(Context context) {
        mHandler = new Handler(Looper.getMainLooper());
        return new Ok.Builder();
    }

    public static Handler getmHandler() {
        return mHandler;
    }

    public static OkHttpClient getInstance() {
        if (okHttpClient == null)
            return builder.build();
        else return okHttpClient;
    }

    /**
     * ok构建
     */
    public static class Builder {

        private OkHttpClient.Builder builder;

        public Builder() {
            builder = new OkHttpClient.Builder();
            builder.connectTimeout(10000L, TimeUnit.MILLISECONDS);
            builder.readTimeout(10000L, TimeUnit.MILLISECONDS);
        }

        public Builder connectTimeout(long timeout, TimeUnit unit) {
            builder.connectTimeout(timeout, unit);
            return this;
        }

        public Builder readTimeout(long timeout, TimeUnit unit) {
            builder.readTimeout(timeout, unit);
            return this;
        }

        public Builder NetWorkInterceptor(String tag, Interceptor interceptor) {
            Log.TAG = tag;
            builder.addNetworkInterceptor(interceptor);
            return this;
        }

        public Builder AppInterceptor(String tag, Interceptor interceptor) {
            Log.TAG = tag;
            builder.addInterceptor(interceptor);
            return this;
        }

        public Builder CookieJar(@NonNull CookieJar cookieJar) {
            builder.cookieJar(cookieJar);
            return this;
        }

        public Builder commonHeader(String key, String value) {
            if (headers == null) headers = new LinkedHashMap<>();
            headers.put(key, value);
            return this;
        }

        public Builder commonParams(String key, String value) {
            if (params == null) params = new ArrayList<>();
            params.add(new Param(key, value));
            return this;
        }

        public void build() {
            new Ok(this);
        }
    }

    /**
     * 获得公共请求头
     */
    public static Map<String, String> getCommonHeaders() {
        return headers;
    }

    /**
     * 获得公共请求参数
     */
    public static List<Param> getCommonParams() {
        return params;
    }


    /**
     * get
     */
    public static GetRequest get() {
        return new GetRequest();
    }

    /**
     * post
     */
    public static PostRequest post() {
        return new PostRequest();
    }
}
