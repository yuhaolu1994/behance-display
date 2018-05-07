package com.example.yuhaolu.behancedisplay.view.buckets_list;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.MainActivity;
import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.model.Bucket;
import com.example.yuhaolu.behancedisplay.utils.ModelUtils;
import com.example.yuhaolu.behancedisplay.view.base.SpaceItemDecoration;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BucketListFragment extends Fragment {

    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    public static final String BUCKETS_FRAGMENT_VALUE = "Buckets";
    public static final String CREATED_BUCKET = "created_bucket";


    public static final int REQ_CODE_NEW_BUCKET = 100;

    private BucketListAdapter adapter;
    private List<Bucket> buckets;

    public static BucketListFragment newInstance(String fragmentType) {
        BucketListFragment bucketsFragment = new BucketListFragment();
        Bundle args = new Bundle();
        args.putString(MainActivity.FRAGMENT_KEY, fragmentType);
        bucketsFragment.setArguments(args);
        return bucketsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buckets, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        List<Bucket> savedBuckets = ModelUtils.read(view.getContext(), CREATED_BUCKET,
                new TypeToken<List<Bucket>>(){});

        buckets = savedBuckets == null ? new ArrayList() : savedBuckets;

        adapter = new BucketListAdapter(view.getContext(), buckets);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(getResources().
                getDimensionPixelSize(R.dimen.spacing_medium)));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewBucketDialogFragment dialogFragment = NewBucketDialogFragment.newInstance();
                dialogFragment.setTargetFragment(BucketListFragment.this, REQ_CODE_NEW_BUCKET);
                dialogFragment.show(getFragmentManager(), NewBucketDialogFragment.TAG);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE_NEW_BUCKET && resultCode == Activity.RESULT_OK) {
            Bucket bucket = new Bucket();
            bucket.bucketName = data.getStringExtra(NewBucketDialogFragment.KEY_BUCKET_NAME);
            bucket.bucketDescription = data.getStringExtra(NewBucketDialogFragment.KEY_BUCKET_DESCRIPTION);
            bucket.shotNum = 0;
            adapter.add(bucket);
            adapter.saveData();
        }
    }
}
