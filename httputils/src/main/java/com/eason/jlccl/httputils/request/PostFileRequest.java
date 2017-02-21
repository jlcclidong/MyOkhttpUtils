package com.eason.jlccl.httputils.request;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by jlccl on 2017/2/19.
 */

public class PostFileRequest extends BaseParamRequest<PostFileRequest> {

    private File content;

    @Override
    public PostFileRequest build() {
        if (content == null) {
            throw new NullPointerException("Fill can not be null!");
        }
        Request.Builder request = new Request.Builder();
        if (headers != null) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                request.header(header.getKey(), header.getValue());
            }
        }
        RequestBody body = RequestBody.create(MediaType.parse(getContentType(content.getAbsolutePath())), content);
        super.request = request.post(body).build();
        ;
        return this;
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
