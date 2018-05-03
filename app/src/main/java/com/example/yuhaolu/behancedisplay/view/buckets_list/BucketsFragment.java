package com.example.yuhaolu.behancedisplay.view.buckets_list;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.MainActivity;
import com.example.yuhaolu.behancedisplay.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BucketsFragment extends Fragment {

    public static final String BUCKETS_FRAGMENT_VALUE = "Buckets";

    public BucketsFragment() {
        // Required empty public constructor
    }

    public static BucketsFragment newInstance(String fragmentType) {
        BucketsFragment bucketsFragment = new BucketsFragment();
        Bundle args = new Bundle();
        args.putString(MainActivity.FRAGMENT_KEY, fragmentType);
        bucketsFragment.setArguments(args);
        return bucketsFragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_buckets, container, false);
    }

}
