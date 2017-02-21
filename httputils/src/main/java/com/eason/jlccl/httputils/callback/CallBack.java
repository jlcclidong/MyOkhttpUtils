package com.eason.jlccl.httputils.callback;

import okhttp3.Response;

/**
 * Created by jlccl on 2017/2/19.
 */

public abstract class CallBack {
    public abstract void fail(Response e);
    public abstract void success(String content);
}
