package com.zhaldak.androidexam.app;

import android.app.Application;

import com.zhaldak.androidexam.data.PlanetsStore;


public class PlanetsApp extends Application {
    private PlanetsStore planetsStore;

    @Override
    public void onCreate() {
        planetsStore = PlanetsStore.getInstance();
        super.onCreate();
    }

    public PlanetsStore getPlanetsStore() {
        return planetsStore;
    }
}