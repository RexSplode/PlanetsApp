package com.zhaldak.androidexam.main;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.zhaldak.androidexam.R;

import com.zhaldak.androidexam.data.Planet;
import com.zhaldak.androidexam.detail.DetailActivity;

import java.util.List;

public class PlanetsAdapter extends RecyclerView.Adapter<PlanetsAdapter.ViewHolder> {
    public static final String EXTRA_PLANET = "extra_planet";
    private List<Planet> planets;
    private Context context;


    public PlanetsAdapter(List<Planet> planets, Context context) {
        this.planets = planets;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.planet_list_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Planet planet = planets.get(position);
        holder.planetTitleText.setText(planet.getTitle());
        holder.planetDetailsText.setText(planet.getDetails());
        Glide.with(context)
                .load(planet.getPictureUrl())
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .thumbnail(0.2f)
                .into(holder.planetImage);

        holder.itemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPlanetDetails(planet);
            }
        });

    }

    private void openPlanetDetails(Planet planet) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(EXTRA_PLANET, planet);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return planets.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private View itemLayout;
        private TextView planetTitleText;
        private TextView planetDetailsText;
        private ImageView planetImage;

        ViewHolder(View itemView) {
            super(itemView);
            itemLayout = itemView.findViewById(R.id.layout);
            planetTitleText = itemView.findViewById(R.id.title_text);
            planetDetailsText = itemView.findViewById(R.id.details_text);
            planetImage = itemView.findViewById(R.id.imageView);

        }
    }

    void addItems(List<Planet> items) {
        Planet another = items.get(0);
        for (Planet planet : planets) {
            if (planet.equals(another)) {
                return;
            }

            planets.addAll(items);
            notifyItemChanged(planets.size() - 1);
        }
    }
}
