package com.eason.jlccl.httputils.callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by cclej on 2017/2/22.
 */

public abstract class BaseCallBack {
    public abstract void fail(Exception e);
    public abstract void success(Response response) throws IOException;
}
