package com.example.wxj.my_okhttp;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnGet, btnPost;
    private TextView tvShowMsg;
    private Handler mhandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initView() {
        btnGet = (Button) findViewById(R.id.btn_Get);
        btnPost = (Button) findViewById(R.id.btn_Post);
        tvShowMsg = (TextView) findViewById(R.id.tv_show_msg);
    }

    private void initData() {
        btnGet.setOnClickListener(this);
        btnPost.setOnClickListener(this);
        mhandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                tvShowMsg.setText((String) msg.obj);
            }
        };

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_Get:
                getData();
                break;
            case R.id.btn_Post:
                postData();
                break;
        }
    }

    private void postData() {
        HttpUtil.requestPost(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = mhandler.obtainMessage();
                msg.obj="Post方法获取数据"+response.body().string();
                mhandler.sendMessage(msg);
            }
        });
    }


    private void getData() {
        HttpUtil.requestGet(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Message msg = mhandler.obtainMessage();
                msg.obj="Get方法获取数据"+response.body().string();
                mhandler.sendMessage(msg);
            }
        });
    }
}
