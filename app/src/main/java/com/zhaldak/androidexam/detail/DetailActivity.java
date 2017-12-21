package com.zhaldak.androidexam.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhaldak.androidexam.R;
import com.zhaldak.androidexam.data.Planet;
import com.zhaldak.androidexam.main.PlanetsAdapter;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        Planet planet = intent.getParcelableExtra(PlanetsAdapter.EXTRA_PLANET);

        TextView planetTitleText = findViewById(R.id.planet_title);
        TextView planetDetailsText = findViewById(R.id.planet_detail);
        ImageView planetImage = findViewById(R.id.planet_img);

        planetTitleText.setText(planet.getTitle());
        planetDetailsText.setText(planet.getDetails());
        Glide.with(this)
                .load(planet.getPictureUrl())
                .thumbnail(0.2f)
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .into(planetImage);

    }
}
