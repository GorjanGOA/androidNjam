package com.example.liquidsun.njamba.service;


import android.os.Handler;

import com.example.liquidsun.njamba.singletones.UserData;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;

import java.util.concurrent.TimeUnit;

public class ServiceRequest {

    public static void get(String url, Callback callback){
        request(url, null, callback, false);
    }

    public static void post(String url, String json, Callback callback){
        request(url, json, callback, true);
    }




    private static void request(String url,
                                String json,
                                Callback callback,
                                boolean isPost){

        MediaType JSON = MediaType.parse("application/json");

        OkHttpClient client = new OkHttpClient();
        client.setConnectTimeout(10000, TimeUnit.MILLISECONDS);
        Request.Builder requestBuilder = new Request.Builder();

        if(isPost == true) {
            RequestBody requestBody = RequestBody.create(JSON,json);
            requestBuilder.post(requestBody);
        }
        else
            requestBuilder.get();

        Request request = requestBuilder
                .url(url)
                .addHeader("Authorization", UserData.getInstance().getBaseAuth())
                .addHeader("Accept", "application/json")
                .build();



        Call call = client.newCall(request);
        call.enqueue(callback);


    }



}
