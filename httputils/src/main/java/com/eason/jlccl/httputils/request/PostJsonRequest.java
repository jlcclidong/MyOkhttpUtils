package com.eason.jlccl.httputils.request;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * postjson构建
 * Created by cclej on 2017/2/21.
 */

public class PostJsonRequest extends BaseRequest<PostJsonRequest> {

    public static final MediaType MEDIA_TYPE_JSON
            = MediaType.parse("application/json; charset=utf-8");
    private String content;

    @Override
    protected void buildBody(Request.Builder requestbuilder) {
        if (content == null) {
            content = "";
        }
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, content);
        super.request = requestbuilder.url(url).post(body).build();
    }

    public PostJsonRequest json(String json) {
        content = json;
        return this;
    }

}
