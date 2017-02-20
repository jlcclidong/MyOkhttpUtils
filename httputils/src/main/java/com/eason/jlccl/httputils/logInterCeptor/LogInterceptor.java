package com.eason.jlccl.httputils.logInterCeptor;


import android.text.TextUtils;

import com.eason.jlccl.httputils.utils.JsonFormat;
import com.eason.jlccl.httputils.utils.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 日志打印类了request response body
 * Created by jlccl on 2017/2/17.
 */

public class LogInterceptor implements Interceptor {
    private String tag = "eason";
    private boolean showResponse = true;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        logForRequest(request);
        Response response = chain.proceed(request);
//
        return logForResponse(response);
    }

    private Response logForResponse(Response response) {
        try {
            Log.e("============response start==============");
            Response copy = response.newBuilder().build();

            Log.e("responseurl:" + copy.request().url());
            Log.e("response code:" + copy.code());
            if (!TextUtils.isEmpty(copy.message()))
                Log.e("message:" + copy.message());
            if (copy.headers() != null && copy.headers().size() > 0)
                Log.e("headers:" + copy.headers().toString());
            Log.e("============response end================");
            ResponseBody body = copy.body();
            if (body != null) {
                MediaType mediaType = body.contentType();
                if (mediaType != null) {
                    Log.e("Content-type:" + mediaType.toString());
                    if (isText(mediaType)) {
                        Log.e("============response body===============");
                        String content = body.string();
                        Log.e(JsonFormat.formatJson(content));
                        Log.e("============response body===============");
                        return response.newBuilder().body(ResponseBody.create(mediaType, content)).build();
                    } else {
                        Log.e(" maybe response content too large too print , ignored!");
                    }
                }
            } else {
                Log.e(" body is null , ignored!");
            }
        } catch (Exception e) {

        }
        return response;
    }

    private void logForRequest(Request request) {
        try {
            Log.e("============request start===============");
            Log.e("url:" + request.url());
            Log.e("method:" + request.method());
            if (request.headers() != null && request.headers().size() > 0)
                Log.e("headers:" + request.headers().toString());
            Log.e("============request end=================");
        } catch (Exception e) {
            Log.e("log request has something worng!!");
        }
    }

    private boolean isText(MediaType mediaType) {
        if (mediaType.type() != null && mediaType.type().equals("text")) {
            return true;
        }
        if (mediaType.subtype() != null) {
            if (mediaType.subtype().equals("json") ||
                    mediaType.subtype().equals("xml") ||
                    mediaType.subtype().equals("html") ||
                    mediaType.subtype().equals("webviewhtml")
                    )
                return true;
        }
        return false;
    }
}
