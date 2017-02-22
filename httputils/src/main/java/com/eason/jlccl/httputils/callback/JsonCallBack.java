package com.eason.jlccl.httputils.callback;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;

/**
 * 将结果转换成json 并未处理string过大oom情况如果出现可以使用流形式转换
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
