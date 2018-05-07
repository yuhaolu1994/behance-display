package com.example.yuhaolu.behancedisplay;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.yuhaolu.behancedisplay.utils.BottomNavigationViewHelper;
import com.example.yuhaolu.behancedisplay.view.discover.DiscoverFragment;
import com.example.yuhaolu.behancedisplay.utils.DoubleClickBackToTop;
import com.example.yuhaolu.behancedisplay.view.buckets_list.BucketListFragment;
import com.example.yuhaolu.behancedisplay.view.collection_list.CollectionFragment;
import com.example.yuhaolu.behancedisplay.view.home.HomeFragment;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements DoubleClickBackToTop.BackToContentTopView {

    @BindView(R.id.bottom_navigation) BottomNavigationView bottomNavigationView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.toolbar_title) TextView toolbarTitle;

    private Fragment fragment;
    private String fragmentValue;
    public static final String FRAGMENT_KEY = "Fragment";
    private String cityName;

    private boolean locationFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        setupBottomView();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // 也可以直接 new DoubleClickBackToTop.BackToContentTopView代替this
        toolbar.setOnClickListener(new DoubleClickBackToTop(this));


        if (savedInstanceState == null) {
//            fragment = CollectionFragment.newInstance(CollectionFragment.COLLECTION_FRAGMENT_VALUE,
//                    getCurrentCity());
            fragment = HomeFragment.newInstance();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            toolbarTitle.setText(R.string.home_title);
        }
        checkLocationPermission();
    }

    private void setupBottomView() {
        BottomNavigationViewHelper.removeShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                fragment = HomeFragment.newInstance();
                                toolbarTitle.setText(R.string.home_title);
                                break;
                            case R.id.action_discover:
                                fragment = DiscoverFragment.newInstance();
                                toolbarTitle.setText(R.string.discover_title);
                                break;
                            case R.id.action_collections:
                                if (locationFlag) {
                                    getCurrentCity();
                                }
                                fragment = CollectionFragment
                                        .newInstance(CollectionFragment.COLLECTION_FRAGMENT_VALUE,
                                                cityName);
                                toolbarTitle.setText(R.string.nearby_title);
                                break;
                            case R.id.action_buckets:
                                fragment = BucketListFragment.newInstance(BucketListFragment.BUCKETS_FRAGMENT_VALUE);
                                toolbarTitle.setText(R.string.bucket_title);
                                break;
                        }

                        if (fragment != null) {
                            getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.fragment_container, fragment)
                                    .commit();
                            return true;
                        }
                        return false;
                    }
                });
    }

    @Override
    public void backToContentTop() {
        fragmentValue = fragment.getArguments().getString(FRAGMENT_KEY);
        if (fragmentValue.equals(CollectionFragment.COLLECTION_FRAGMENT_VALUE)) {
            RecyclerView recyclerView = fragment.getView().findViewById(R.id.recycler_view);
            recyclerView.scrollToPosition(0);
        }
    }

    private void checkLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION}, 1);
        } else {
            locationFlag = true;
        }
    }


    private void getCurrentCity() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, true);

        Location location = locationManager.getLastKnownLocation(provider);
        // get address from latitude and longitude by Geocoder
        Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
        List<Address> addresses;
        try {
            addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            if (addresses.size() > 0) {
                cityName = addresses.get(0).getLocality().toString();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == 1) {
            locationFlag = grantResults[0] == PackageManager.PERMISSION_GRANTED
                    && grantResults[1] == PackageManager.PERMISSION_GRANTED;
        }
    }
}
