package com.example.yuhaolu.behancedisplay.view.project_detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.model.ProjectDetail;
import com.example.yuhaolu.behancedisplay.utils.ModelUtils;
import com.google.gson.reflect.TypeToken;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectFragment extends Fragment {

    public static final String KEY_PROJECT = "project";

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private ProjectDetail projectDetail;

    public static ProjectFragment newInstance(@NonNull Bundle args) {
        ProjectFragment fragment = new ProjectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_project, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        projectDetail = ModelUtils.toObject(getArguments().getString(KEY_PROJECT),
                new TypeToken<ProjectDetail>(){});
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(new ProjectAdapter(this, projectDetail));
    }

    public void share(Context context) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, projectDetail.name + " " + projectDetail.url);
        shareIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(shareIntent, getString(R.string.share_project)));
    }
}
