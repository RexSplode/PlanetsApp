package com.zhaldak.androidexam.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.zhaldak.androidexam.R;
import com.zhaldak.androidexam.app.PlanetsApp;
import com.zhaldak.androidexam.data.Planet;
import com.zhaldak.androidexam.data.PlanetsStore;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;


public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PlanetsAdapter planetsAdapter;
    private LinearLayoutManager layoutManager;
    private boolean isLoading;
    private boolean haventThrown = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.my_recycler_view);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItem = layoutManager.findLastVisibleItemPosition();
                if (!isLoading && totalItemCount == (lastVisibleItem + 1) && haventThrown) {
                    loadMore();
                    isLoading = true;
                }
            }
        });
    }

    @Override
    protected void onStart() {
        loadMore();

        super.onStart();
    }

    private void loadMore() {
        PlanetsApp app = (PlanetsApp) getApplication();
        PlanetsStore planetsStore = app.getPlanetsStore();
        planetsStore.getPlanets()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::fillAdapter, this::onError);
    }

    private void fillAdapter(List<Planet> planets) {
        isLoading = false;
        if (planetsAdapter == null) {
            planetsAdapter = new PlanetsAdapter(planets, this);
            recyclerView.setAdapter(planetsAdapter);
        } else {
            planetsAdapter.addItems(planets);
        }
    }

    private void onError(Throwable error) {
        Log.e("MainActivity", error.getMessage());
        haventThrown = false;

    }


}
