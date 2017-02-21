package com.eason.jlccl.httputils.callback;

/**
 * Created by jlccl on 2017/2/19.
 */

public abstract class CallBack {
    public abstract void fail(Exception e);
    public abstract void success(String content);
}
