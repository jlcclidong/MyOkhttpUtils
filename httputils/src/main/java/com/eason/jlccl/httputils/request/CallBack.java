package com.eason.jlccl.httputils.request;

/**
 * Created by jlccl on 2017/2/19.
 */

public interface CallBack<T> {
    void fail();

    void success(T s);
}
