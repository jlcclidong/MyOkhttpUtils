package com.eason.jlccl.httputils;

import android.support.annotation.NonNull;

import com.eason.jlccl.httputils.logInterCeptor.LogInterceptor;
import com.eason.jlccl.httputils.utils.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.CookieJar;
import okhttp3.OkHttpClient;

/**
 * Created by jlccl on 2017/2/17.
 */

public class Ok {
    private static OkHttpClient.Builder builder;
    private static OkHttpClient okHttpClient;

    public Ok(Builder builder) {
        this.builder = builder.builder;
    }

    public static OkHttpClient getInstance() {
        if (okHttpClient == null)
            return builder.build();
        else return okHttpClient;
    }

    public static Builder init() {
        return new Ok.Builder();
    }

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

        public Builder logInterceptor(String tag, boolean isShow) {
            Log.TAG = tag;
            if (isShow) builder.addInterceptor(new LogInterceptor());
            return this;
        }

        public Builder CookieJar(@NonNull CookieJar cookieJar) {
            builder.cookieJar(cookieJar);
            return this;
        }

        public void build() {
            new Ok(this);
        }
    }
}
