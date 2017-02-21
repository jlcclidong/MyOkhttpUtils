package com.eason.jlccl.httputils.callback;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;

/**
 * Created by cclej on 2017/2/21.
 */

public abstract class JsonCallBack<T> extends CallBack {
    @Override
    public void success(String content) {
        Class<T> entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        if (entityClass == String.class) {
            this.success((T) content);
        }
        T t = new Gson().fromJson(content, entityClass);
        this.success(t);
    }

    public abstract void success(T t);
}
