package com.eason.jlccl.httputils.callback;

import android.text.TextUtils;

import com.eason.jlccl.httputils.Ok;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Response;

/**
 * Created by cclej on 2017/2/22.
 */

public abstract class FileCallBack extends BaseCallBack {


    private final String targetDir;
    private final String filename;

    public FileCallBack(String targetDir, String fileName) {
        this.targetDir = targetDir;
        this.filename = fileName;
        if (TextUtils.isEmpty(targetDir) || TextUtils.isEmpty(fileName)) {
            this.fail(new Exception());
        }
    }

    @Override
    public void success(Response response) throws IOException {
        saveFile(response);
    }

    public abstract void progress(int progress);

    public abstract void success(File file);

    private void saveFile(final Response response) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream is = null;
                byte[] buffer = new byte[2048];
                int len = 0;
                FileOutputStream fos = null;
                try {
                    File dirFile = new File(targetDir);
                    if (!dirFile.exists()) {
                        dirFile.mkdirs();
                    }
                    final File file = new File(dirFile, filename);
                    if (file.exists()) {
                        file.delete();
                    }
                    final long total = response.body().contentLength();
                    is = response.body().byteStream();
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buffer)) != -1) {
                        sum += len;
                        fos.write(buffer, 0, len);
                        final long finalSum = sum;
                        Ok.getmHandler().post(new Runnable() {
                            @Override
                            public void run() {
                                progress((int) (finalSum * 1f / total * 100));
                            }
                        });
                    }
                    fos.flush();
                    Ok.getmHandler().post(new Runnable() {
                        @Override
                        public void run() {
                            success(file);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        response.body().close();
                        if (is != null) is.close();
                    } catch (Exception e) {
                    }
                    try {
                        if (fos != null) {
                            fos.close();
                        }
                    } catch (IOException e) {
                    }
                }
            }
        }).start();
    }
}
