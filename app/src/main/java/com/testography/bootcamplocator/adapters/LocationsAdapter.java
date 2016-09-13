package com.testography.bootcamplocator.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

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
        return null;
    }

    @Override
    public void onBindViewHolder(LocationsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
