package com.testography.bootcamplocator.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.testography.bootcamplocator.R;
import com.testography.bootcamplocator.model.Devslopes;

public class LocationsViewHolder extends RecyclerView.ViewHolder {

    private ImageView locationImage;
    private TextView locationTitle;
    private TextView locationAddress;

    public LocationsViewHolder(View itemView) {
        super(itemView);

        locationImage = (ImageView) itemView.findViewById(R.id.location_image);
        locationTitle = (TextView) itemView.findViewById(R.id.location_title);
        locationAddress = (TextView) itemView.findViewById(R.id.location_address);
    }

    public void updateUI(Devslopes location) {
        String uri = location.getImageUrl();
        int resource = locationImage.getResources().getIdentifier(uri, null,
                locationImage.getContext().getPackageName());
        locationImage.setImageResource(resource);
        locationTitle.setText(location.getLocationTitle());
        locationAddress.setText(location.getLocationAddress());
    }
}
