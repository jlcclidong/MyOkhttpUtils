package com.eason.jlccl.httputils.request;

import com.eason.jlccl.httputils.Ok;
import com.eason.jlccl.httputils.callback.BaseCallBack;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by cclej on 2017/2/18.
 */

public abstract class BaseRequest<T extends BaseRequest> {

    protected LinkedHashMap<String, String> headers;
    protected String url;
    protected Request request;
    protected Object tag;

    public BaseRequest() {
        headers = new LinkedHashMap<>();
        if (Ok.getCommonHeaders() != null) {
            try {
                for (Map.Entry<String, String> header : headers.entrySet()) {
                    header(header.getKey(), header.getValue());
                }
            } catch (Exception e) {
                e.printStackTrace();
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

    public T tag(Object tag) {
        this.tag = tag;
        return (T) this;
    }

    public T build() {
        Request.Builder requestbuilder = new Request.Builder();
        if (tag != null) {
            requestbuilder.tag(tag);
        }
        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                requestbuilder.header(header.getKey(), header.getValue());
            }
        }
        buildBody(requestbuilder);
        return (T) this;
    }

    /**
     * 各子类实现body体的封装
     *
     * @param requestbuilder
     */
    protected abstract void buildBody(Request.Builder requestbuilder);

    /**
     * 请求回调
     *
     * @param callBack callback返回String结果 jsonCallBack返回Gson解析对象
     */
    public void call(final BaseCallBack callBack) {
        Ok.getInstance().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(final Call call, final Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        Ok.getmHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    callBack.success(response);
                                } catch (IOException e) {

                                }

                            }
                        });
                    } else {
                        callBack.fail(new Exception(""));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    callBack.fail(new Exception(""));
                }
            }
        });
    }


}
