package com.zhaldak.androidexam.api;

import com.zhaldak.androidexam.data.Planet;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;


public interface PlanetsRetrofitService {
    @GET("page/{page}")
    Observable<PlanetsResponse> getPlanets(@Path("page") Integer page);

} 