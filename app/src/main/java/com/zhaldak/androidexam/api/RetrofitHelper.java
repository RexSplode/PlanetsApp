package com.zhaldak.androidexam.api;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class RetrofitHelper {
    private Retrofit retrofit;
    private PlanetsRetrofitService planetsService;

    public RetrofitHelper() {
        retrofit = createRetrofit();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }
    public PlanetsRetrofitService getPlanetsService(){
        return retrofit.create(PlanetsRetrofitService.class);
    }

    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("http://54.202.34.250:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }
}
