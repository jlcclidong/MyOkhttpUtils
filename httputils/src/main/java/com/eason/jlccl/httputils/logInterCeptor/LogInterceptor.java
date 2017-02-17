package com.eason.jlccl.httputils.logInterCeptor;

import com.eason.jlccl.httputils.utils.JsonFormat;
import com.eason.jlccl.httputils.utils.Log;

import java.io.IOException;

import okhttp3.Interceptor;
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
        Log.e("============request=start===============");
        Request request = chain.request();
        Log.e("url:"+ request.url());
        Log.e("method:"+request.method());
        Log.e("headers:"+request.headers().toString());
        Log.e("============request=end=================");
        Response response = chain.proceed(request);
        Log.e("============response=start==============");
        Log.e("Request:"+ response.code());
        Log.e("message:"+response.message());
        Log.e("headers:"+response.headers().toString());
        Log.e("============response=end================");
        Log.e("============response=body===============");
        String content = response.body().string();
        Log.e(JsonFormat.formatJson(content));
        Log.e("============response=body===============");
        //request中body()只能获取一次只能重新编写一个response给结果
        return response.newBuilder()
                .body(ResponseBody.create(response.body().contentType(),content))
                .build();
    }
}
