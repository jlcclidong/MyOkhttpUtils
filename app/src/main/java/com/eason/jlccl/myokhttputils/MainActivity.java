package com.eason.jlccl.myokhttputils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.eason.jlccl.httputils.Ok;
import com.eason.jlccl.httputils.request.CallBack;

import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private Button mGet;
    private Button mPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mGet = (Button) findViewById(R.id.get);
        mGet.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                Ok.get()
                        .url("https://wish.yuntigao.net/app/get-user-info")
                        .build()
                        .call(new CallBack() {
                            @Override
                            public void fail() {

                            }

                            @Override
                            public void success(Response s) {
                                mGet.setText("success");
                            }
                        });
            }
        });
        mPost = (Button) findViewById(R.id.post);
        mPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Ok.post()
                        .url("https://wish.yuntigao.net/app/SystemLogin")
                        .param("j_username", "dxf")
                        .param("j_password", "1")
                        .build()
                        .call(new CallBack() {
                            @Override
                            public void fail() {

                            }

                            @Override
                            public void success(Response s) {

                            }
                        });
            }

        });
    }
}


