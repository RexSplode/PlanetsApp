package com.zhaldak.androidexam.api;

import com.google.gson.annotations.SerializedName;
import com.zhaldak.androidexam.data.Planet;

import java.util.List;


public class PlanetsResponse {
    int page;
    @SerializedName("items")
    List<Planet> planets;

    public int getPage() {
        return page;
    }

    public List<Planet> getPlanets() {
        return planets;
    }
}
