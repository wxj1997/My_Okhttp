package com.example.wxj.my_okhttp;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by wxj on 2018/4/25.
 */

public class HttpUtil {

    public static void requestGet(Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        //添加Request请求
        Request request = new Request.Builder().url("http://10.0.2.2/get.php?key=get").get()
                .build();
        //创建call对象
        Call call = okHttpClient.newCall(request);
        call.enqueue(callback);
    }

    public static void requestPost(Callback callback) {
        OkHttpClient okHttpClient = new OkHttpClient();
        FormBody formBody = new FormBody.Builder().add("key", "post").build();
        Request request = new Request.Builder().url("http://10.0.2.2/post.php").post(formBody)
                .build();
        Call call=okHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
