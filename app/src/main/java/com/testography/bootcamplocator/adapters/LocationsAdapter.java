package com.testography.bootcamplocator.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.testography.bootcamplocator.R;
import com.testography.bootcamplocator.holders.LocationsViewHolder;
import com.testography.bootcamplocator.model.Devslopes;

import java.util.ArrayList;

public class LocationsAdapter extends RecyclerView.Adapter<LocationsViewHolder> {

    private ArrayList<Devslopes> mLocations;

    public LocationsAdapter(ArrayList<Devslopes> locations) {
        mLocations = locations;
    }

    @Override
    public LocationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).inflate(R.layout
                .card_location, parent, false);
        return new LocationsViewHolder(card);
    }

    @Override
    public void onBindViewHolder(LocationsViewHolder holder, int position) {
        final Devslopes location = mLocations.get(position);
        holder.updateUI(location);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Load details page
            }
        });
    }

    @Override
    public int getItemCount() {
        return mLocations.size();
    }
}
