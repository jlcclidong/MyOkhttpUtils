package com.eason.jlccl.httputils.request;

import com.eason.jlccl.httputils.Ok;
import com.eason.jlccl.httputils.callback.CallBack;
import com.eason.jlccl.httputils.headerparams.Param;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cclej on 2017/2/18.
 */

public abstract class BaseRequest<T extends BaseRequest> {
    protected List<Param> params;
    protected LinkedHashMap<String, String> headers;
    protected String url;
    protected Request request;

    public BaseRequest() {
        params = new ArrayList<>();
        headers = new LinkedHashMap<>();
        if (Ok.getCommonHeaders() != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                header(header.getKey(), header.getValue());
            }
        }
    }


    public T header(String key, String value) {
        headers.put(key, value);
        return (T) this;
    }

    public T url(String url) {
        this.url = url;
        return (T) this;
    }

    public abstract T build();

    public void call(final CallBack callBack) {
        Ok.getInstance().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                Ok.getmHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.fail(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                final String content = response.body().string();
                Ok.getmHandler().post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.success(content);
                    }
                });
            }
        });
    }


}
