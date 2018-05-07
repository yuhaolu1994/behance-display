package com.example.yuhaolu.behancedisplay.view.home;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.model.Project;
import com.example.yuhaolu.behancedisplay.view.base.BaseViewHolder;
import com.example.yuhaolu.behancedisplay.view.base.InfiniteAdapter;

import java.util.List;

public class HomeAdapter extends InfiniteAdapter<Project> {


    public HomeAdapter(@NonNull Context context, @NonNull List<Project> data) {
        super(context, data);
    }

    @Override
    public BaseViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_project_item, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    protected void onBindItemViewHolder(BaseViewHolder holder, int position) {
        HomeViewHolder homeViewHolder = (HomeViewHolder) holder;
        Project project = getData().get(position);
        homeViewHolder.projectImage.setImageURI(Uri.parse(project.covers.original));
        homeViewHolder.projectName.setText(String.valueOf(project.name));
        homeViewHolder.authorName.setText(String.valueOf(project.owners.get(0).displayName));
    }
}
