package com.example.yuhaolu.behancedisplay.view.discover;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DiscoverFieldsFragment extends Fragment {

    public DiscoverFieldsFragment() {
        // Required empty public constructor
    }

    public static DiscoverFieldsFragment newInstance() {
        return new DiscoverFieldsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_discover_fields, container, false);
    }

}
