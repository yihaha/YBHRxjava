package com.ybhrxjavademo.http;

import com.ybhrxjavademo.bean.MeiziResult;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by y on 2016/4/13.
 */
public interface RetrofitApiService {
    @GET("data/福利/10/{page}")
    Observable<MeiziResult> getMeiziList(@Path("page") int page);

//    @GET("data/福利/{number}/{page}")
//    Observable<MeiziResult> getBeauties(@Path("number") int number, @Path("page") int page);

}
