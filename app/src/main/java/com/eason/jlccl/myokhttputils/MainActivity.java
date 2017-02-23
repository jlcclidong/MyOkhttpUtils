package com.eason.jlccl.myokhttputils;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.eason.jlccl.httputils.Ok;
import com.eason.jlccl.httputils.callback.CallBack;
import com.eason.jlccl.httputils.callback.FileCallBack;
import com.eason.jlccl.httputils.callback.JsonCallBack;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    private Button mGet;
    private Button mDownload;
    private Button mPost;
    private TextView mTv;
    private ImageView mIV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGet = (Button) findViewById(R.id.get);
        mPost = (Button) findViewById(R.id.post);
        mDownload = (Button) findViewById(R.id.download);
        mTv = (TextView) findViewById(R.id.tv);
        mIV = (ImageView) findViewById(R.id.iv);
        mGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                get();
            }
        });
        mPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                post();
            }
        });
        mDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                download();
            }
        });
    }

    private void get() {
        Ok.get().url("http://lab.zuimeia.com/wallpaper/category/1/")
                .param("page_size", 1)
                .build()
                .call(new JsonCallBack<Bean>() {  //傳入實體類
                    @Override
                    public void fail(Exception e) {

                    }

                    @Override
                    public void success(Bean bean) {
                        mTv.setText(bean.getData().getBase_url());
                    }
                });
    }

    private void post() {
        Ok.post().url("https://www.baidu.com")
                .param("test", 1)
                .file("test", new File(""))
                .build()
                .call(new CallBack() {
                    @Override
                    public void fail(Exception e) {

                    }

                    @Override
                    public void success(String response) {
                        mTv.setText(response);
                    }
                });

    }

    private void download() {
        Ok.download().url("http://static.oschina.net/uploads/space/2015/0629/170157_rxDh_1767531.png")
                .build()
                .call(new FileCallBack(getCacheDir().getAbsolutePath(), "github.png") {
                    @Override
                    public void progress(int progress) {
                        mTv.setText(progress + "");
                    }

                    @Override
                    public void success(File file) {
                        mIV.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                    }

                    @Override
                    public void fail(Exception e) {

                    }
                });
    }
}
