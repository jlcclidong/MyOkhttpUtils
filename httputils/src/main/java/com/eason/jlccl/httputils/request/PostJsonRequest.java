package com.eason.jlccl.httputils.request;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by cclej on 2017/2/21.
 */

public class PostJsonRequest extends BaseRequest<PostJsonRequest> {

    public static final MediaType MEDIA_TYPE_JSON
            = MediaType.parse("application/json; charset=utf-8");
    private String content;

    @Override
    public PostJsonRequest build() {
        if (content == null) {
            content = "";
        }
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, content);
        Request builder = new Request.Builder().post(body).build();
        super.request = builder;
        return this;
    }

    public PostJsonRequest json(String json) {
        content = json;
        return this;
    }

}
