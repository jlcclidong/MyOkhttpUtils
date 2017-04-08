package com.eason.jlccl.httputils.logInterCeptor;


import android.text.TextUtils;

import com.eason.jlccl.httputils.utils.JsonFormat;
import com.eason.jlccl.httputils.utils.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
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


    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        long requestTime = System.nanoTime();
        logForRequest(request);
        Response response = chain.proceed(request);
        long responseTime = System.nanoTime();
        long time = TimeUnit.NANOSECONDS.toMillis(responseTime - requestTime);
        return logForResponse(response, time);
    }


    private void logForRequest(Request request) {
        try {
            Log.e("============request start===============");
            Log.e("url:" + request.url());
            Log.e("method:" + request.method());
            if (request.headers() != null && request.headers().size() > 0){
                Headers headers = request.headers();
                for (int i = 0; i < headers.size(); i++) {
                    Log.e("headers"+headers.name(i)+"---"+headers.value(i));
                }
            }
            Log.e("============request end=================");
        } catch (Exception e) {
            Log.e("log request has something worng!!");
        }
    }

    private Response logForResponse(Response response, long time) {
        try {
            Log.e("============response start==============");
            //response.body().string()只能调用一次 body()就会关掉
            //每次使用前都clone一份使用保证原来的body没有被关掉
            Response copy = response.newBuilder().build();
            Log.e("responseurl:" + copy.request().url());
            Log.e("response code:" + copy.code());
            Log.e("total time:" + time);
            if (!TextUtils.isEmpty(copy.message()))
                Log.e("message:" + copy.message());
            if (copy.headers() != null && copy.headers().size() > 0) {
                Headers headers = copy.headers();
                for (int i = 0; i < headers.size(); i++) {
                    Log.e("\t" + headers.name(i) + ": " + headers.value(i));
                }
            }
            Log.e("============response end================");
            ResponseBody body = copy.body();
            if (body != null) {
                MediaType mediaType = body.contentType();
                if (mediaType != null) {
                    Log.e("============response body===============");
                    Log.e("Content-type:" + mediaType.toString());
                    if (isText(mediaType)) {
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
            Log.e("log response has something worng!!");
        }
        return response;
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
