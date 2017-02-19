package com.eason.jlccl.httputils.request;

import okhttp3.Response;

/**
 * Created by jlccl on 2017/2/19.
 */

public interface CallBack {
    void fail();
    void success(Response s);
}
