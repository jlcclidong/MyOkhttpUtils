package com.eason.jlccl.httputils.request;

import com.eason.jlccl.httputils.headerparams.Param;

import okhttp3.Request;

/**
 * Created by jlccl on 2017/2/18.
 */

public class GetRequest extends BaseParamRequest<GetRequest> {

    @Override
    protected void buildBody(Request.Builder requestbuilder) {
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
        requestbuilder.url(url);
        super.request = requestbuilder.build();
    }
}
