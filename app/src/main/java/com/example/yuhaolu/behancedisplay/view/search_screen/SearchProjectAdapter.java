package com.example.yuhaolu.behancedisplay.view.search_screen;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.model.Project;

import java.util.ArrayList;
import java.util.List;

public class SearchProjectAdapter extends RecyclerView.Adapter<SearchProjectViewHolder> {

    private Context context;
    private List<Project> projects;

    public SearchProjectAdapter(Context context, List<Project> projects) {
        this.context = context;
        this.projects = projects;
    }

    @Override
    public SearchProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_project_item, null);
        return new SearchProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchProjectViewHolder holder, int position) {
        final Project project = projects.get(position);
        holder.projectImage.setImageURI(Uri.parse(project.covers.original));
        holder.projectName.setText(String.valueOf(project.name));
        holder.authorName.setText(String.valueOf(project.owners.get(0).displayName));
    }

    @Override
    public int getItemCount() {
        return projects != null ? projects.size() : 0;
    }


    public void appendProjects(ArrayList<Project> addProjects) {
        projects.addAll(addProjects);
        notifyDataSetChanged();
    }

    public void clearProjects() {
        projects.clear();
        notifyDataSetChanged();
    }
}
