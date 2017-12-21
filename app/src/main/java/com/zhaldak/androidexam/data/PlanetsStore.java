package com.zhaldak.androidexam.data;

import com.zhaldak.androidexam.api.PlanetsRetrofitService;
import com.zhaldak.androidexam.api.RetrofitHelper;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;



public class PlanetsStore {
    private static PlanetsStore instance;
    private RetrofitHelper retrofitHelper;
    private int currentPlanetsPage = -1;
    private List<Planet> planets;


    /**
     * Not thread safe, lack of time
     * @return instance of this class
     */
    public static PlanetsStore getInstance(){
        if(instance == null){
            instance = new PlanetsStore();

        }
        return instance;
    }

    private PlanetsStore(){
        retrofitHelper = new RetrofitHelper();
        planets = new ArrayList<>();
    }

    public Observable<List<Planet>> getPlanets() {
        PlanetsRetrofitService service = retrofitHelper.getPlanetsService();
        return service
                .getPlanets(++currentPlanetsPage)
                .flatMap(response -> Observable.just(response.getPlanets()))
                .onErrorReturn(e -> planets)
                .map(list -> {
                    planets.addAll(list);
                    return planets;
                });

    }
} 