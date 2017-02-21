package com.eason.jlccl.httputils.request;

import java.util.Map;

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
        Request.Builder request = new Request.Builder();
        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                request.header(header.getKey(), header.getValue());
            }
        }
        RequestBody body = RequestBody.create(MEDIA_TYPE_JSON, content);
        super.request = request.post(body).build();
        return this;
    }

    public PostJsonRequest json(String json) {
        content = json;
        return this;
    }

}
