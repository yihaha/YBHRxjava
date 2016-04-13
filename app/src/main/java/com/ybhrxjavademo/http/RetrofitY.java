package com.ybhrxjavademo.http;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by y on 2016/4/13.
 */
public class RetrofitY {
    private final static String URL="http://gank.io/api/";
    private static RetrofitApiService retrofitApiService;

    public RetrofitY(){

    }

    public static RetrofitApiService getMeiziApi(){
        if (null==retrofitApiService) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            retrofitApiService = retrofit.create(RetrofitApiService.class);
        }
        return retrofitApiService;
    }

}
