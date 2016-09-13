package com.testography.bootcamplocator.fragments;


import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.testography.bootcamplocator.R;
import com.testography.bootcamplocator.model.Devslopes;
import com.testography.bootcamplocator.services.DataService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MarkerOptions mUserMarker;
    private LocationsListFragment mListFragment;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment)
                getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        mListFragment = (LocationsListFragment) getActivity()
                .getSupportFragmentManager().findFragmentById(R.id.container_locations_list);
        if (mListFragment == null) {
            mListFragment = LocationsListFragment.newInstance();
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_locations_list, mListFragment)
                    .commit();
        }

        final EditText zipText = (EditText) view.findViewById(R.id.zip_text);
        zipText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && i == KeyEvent
                        .KEYCODE_ENTER) {
                    // We have to make sure this is a valid zip code - check
                    // total count and characters
                    String text = zipText.getText().toString();
                    int zip = Integer.parseInt(text);

                    InputMethodManager imm = (InputMethodManager) getActivity()
                            .getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(zipText.getWindowToken(), 0);

                    showList();

                    updateMapForZip(zip);
                    return true;
                }
                return false;
            }
        });
        hideList();
        return view;
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    public void setUserMarker(LatLng latLng) {
        if (mUserMarker == null) {
            mUserMarker = new MarkerOptions().position(latLng).title("Current " +
                    "Location");
            mMap.addMarker(mUserMarker);
            Log.v("---MAPS---", "Lat: " + latLng.latitude + " - Long: " +
                    latLng.longitude);
        }
        try {
            Geocoder geocoder = new Geocoder(getContext(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(latLng.latitude,
                    latLng.longitude, 1);
            int zip = Integer.parseInt(addresses.get(0).getPostalCode());
            updateMapForZip(zip);
        } catch (IOException e) {
            e.printStackTrace();
        }

        updateMapForZip(92284);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
    }

    private void updateMapForZip(int zipcode) {
        ArrayList<Devslopes> locations = DataService.getInstance()
                .getBootcampLocationsWithin10MilesOfZip(zipcode);

        for (int i = 0; i < locations.size(); i++) {
            Devslopes loc = locations.get(i);
            MarkerOptions marker = new MarkerOptions().position(new LatLng(loc
                    .getLatitude(), loc.getLongitude()));
            marker.title(loc.getLocationTitle());
            marker.snippet(loc.getLocationAddress());
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin));
            mMap.addMarker(marker);
        }
    }

    private void hideList() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .hide(mListFragment)
                .commit();
    }

    private void showList() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .show(mListFragment)
                .commit();
    }
}
