package com.eason.jlccl.httputils.request;

import com.eason.jlccl.httputils.headerparams.Param;

import java.util.Map;

import okhttp3.Request;

/**
 * Created by jlccl on 2017/2/18.
 */

public class GetRequest extends BaseParamRequest<GetRequest> {

    @Override
    public GetRequest build() {
        Request.Builder request = new Request.Builder().get();
        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                request.header(header.getKey(), header.getValue());
            }
        }
        if (params != null && params.size() > 0) {
            for (int i = 0; i < params.size(); i++) {
                Param param = params.get(i);
                if (i == 0) {
                    url += "?";
                } else {
                    url += "&";
                }
                url += param.getKey() + "=" + param.getValue();
            }
        }
        request.url(url);
        super.request = request.build();
        return this;
    }
}
