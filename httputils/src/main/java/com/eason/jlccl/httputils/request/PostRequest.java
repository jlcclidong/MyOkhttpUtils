package com.eason.jlccl.httputils.request;

import com.eason.jlccl.httputils.headerparams.FileParam;
import com.eason.jlccl.httputils.headerparams.Param;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * post请求支持表单+文件上传
 * Created by jlccl on 2017/2/19.
 */

public class PostRequest extends BaseParamRequest<PostRequest> {
    private List<FileParam> files;

    @Override
    protected void buildBody(Request.Builder requestbuilder) {
        RequestBody requestBody;
        if (files != null && files.size() > 0) {
            MultipartBody.Builder builder = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM);
            if (params != null && params.size() > 0) {
                for (Param param : params) {
                    builder.addFormDataPart(param.getKey(), param.getValue());
                }
            }
            for (FileParam file : files) {
                if (file.getFile().exists() && file.getFile().isFile()) {
                    RequestBody body = RequestBody.create(MediaType.parse(getContentType(file.getFile().getAbsolutePath())), file.getFile());
                    builder.addFormDataPart(file.getKey(), getFileName(file.getFile()), body);
                }
            }
            requestBody = builder.build();
        } else {
            FormBody.Builder builder = new FormBody.Builder();
            if (params != null && params.size() > 0) {
                for (Param param : params) {
                    builder.add(param.getKey(), param.getValue());
                }
            }
            requestBody = builder.build();
        }
        super.request = requestbuilder.url(url).post(requestBody).build();
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

    /**
     * 获取文件名，如果文件不存在 或者 是文件夹 返回null 报异常
     *
     * @param file
     * @return
     */
    private String getFileName(File file) {
        return file.getName();

    }

    public PostRequest file(String key, File file) {
        if (files == null) files = new ArrayList<>();
        files.add(new FileParam(key, file));
        return this;
    }

    public PostRequest file(String key, List<File> file) {
        if (file != null && file.size() > 0) {
            if (files == null) files = new ArrayList<>();
            for (File file1 : file) {
                files.add(new FileParam(key, file1));
            }
        }
        return this;
    }
}
