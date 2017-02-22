package com.eason.jlccl.httputils.request;

import okhttp3.Request;

/**
 * Created by cclej on 2017/2/22.
 */

public class FileRequest extends BaseRequest<FileRequest> {


    @Override
    protected void buildBody(Request.Builder requestbuilder) {
        super.request = requestbuilder.get().url(url).build();
    }

}
