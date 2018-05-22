package com.example.yuhaolu.behancedisplay.view.bucket_projects;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.model.Bucket;
import com.example.yuhaolu.behancedisplay.utils.ModelUtils;
import com.example.yuhaolu.behancedisplay.view.base.GridSpacingItemDecoration;
import com.google.gson.reflect.TypeToken;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BucketProjectFragment extends Fragment {

    @BindView(R.id.bucket_projects_recycler_view) RecyclerView recyclerView;

    public static final String KEY_BUCKET_PROJECTS = "bucket_projects";

    private BucketProjectAdapter adapter;

    public static BucketProjectFragment newInstance(@NonNull Bundle args) {
        BucketProjectFragment bucketProjectFragment = new BucketProjectFragment();
        bucketProjectFragment.setArguments(args);
        return bucketProjectFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bucket_projects, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        final Bucket bucket = ModelUtils.toObject(getArguments().getString(KEY_BUCKET_PROJECTS),
                new TypeToken<Bucket>(){});
        adapter = new BucketProjectAdapter(getContext(), bucket.bucketProjects);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        int spacing = getResources().getDimensionPixelOffset(R.dimen.spacing_medium);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, spacing, true));
    }
}
