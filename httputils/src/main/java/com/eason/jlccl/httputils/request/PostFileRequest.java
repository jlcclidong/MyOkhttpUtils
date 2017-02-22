package com.eason.jlccl.httputils.request;

import com.eason.jlccl.httputils.utils.Log;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.net.URLEncoder;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by jlccl on 2017/2/19.
 */

public class PostFileRequest extends BaseParamRequest<PostFileRequest> {

    private File content;

    @Override
    protected void buildBody(Request.Builder requestbuilder) {
        if (content == null) {
            Log.e("File is NULL");
            super.request = requestbuilder.url(url).get().build();
        }
        RequestBody body = RequestBody.create(MediaType.parse(getContentType(content.getAbsolutePath())), content);
        super.request = requestbuilder.url(url).post(body).build();
    }


    /**
     * 获取文件contentType
     *
     * @return
     */

    private String getContentType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = null;
        try {
            contentTypeFor = fileNameMap.getContentTypeFor(URLEncoder.encode(path, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }


    public PostFileRequest file(File file) {
        content = file;
        return this;
    }

}
