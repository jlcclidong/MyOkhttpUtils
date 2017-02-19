package com.eason.jlccl.myokhttputils;

import android.app.Application;

import com.eason.jlccl.httputils.Ok;
import com.eason.jlccl.httputils.logInterCeptor.LogInterceptor;
import com.eason.jlccl.httputils.persistentcookiejar.PersistentCookieJar;
import com.eason.jlccl.httputils.persistentcookiejar.cache.SetCookieCache;
import com.eason.jlccl.httputils.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.util.concurrent.TimeUnit;

/**
 * Created by jlccl on 2017/2/17.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Ok.init(this)
                .connectTimeout(1000l, TimeUnit.MILLISECONDS)
                .readTimeout(1000l, TimeUnit.MILLISECONDS)
                .AppInterceptor("eason", true, new LogInterceptor())
                .CookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.this)))
                .build();
    }
}
