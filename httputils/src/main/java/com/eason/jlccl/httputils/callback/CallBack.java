package com.eason.jlccl.httputils.callback;

import java.io.IOException;

import okhttp3.Response;

/**
 * String callback
 * Created by jlccl on 2017/2/19.
 */

public abstract class CallBack extends BaseCallBack {
    public abstract void fail(Exception e);

    @Override
    public void success(Response response) throws IOException {
        this.success(response.body().string());
    }

    public abstract void success(String response);

}
