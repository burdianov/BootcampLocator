package com.testography.bootcamplocator.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.testography.bootcamplocator.R;
import com.testography.bootcamplocator.fragments.MainFragment;

public class MapsActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        MainFragment mainFragment = (MainFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.container_main, mainFragment)
                    .commit();
        }
    }
}
