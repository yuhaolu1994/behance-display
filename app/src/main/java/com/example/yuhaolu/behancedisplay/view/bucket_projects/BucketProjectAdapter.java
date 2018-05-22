package com.example.yuhaolu.behancedisplay.view.bucket_projects;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.model.ProjectDetail;

import java.util.List;

public class BucketProjectAdapter extends RecyclerView.Adapter<BucketProjectViewHolder> {

    private Context context;
    private List<ProjectDetail> projects;

    public BucketProjectAdapter(Context context, List<ProjectDetail> projects) {
        this.context = context;
        this.projects = projects;
    }

    @Override
    public BucketProjectViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bucket_project_item, null);
        return new BucketProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BucketProjectViewHolder holder, int position) {
        final ProjectDetail project = projects.get(position);
        holder.bucketProjectPicture.setImageURI(Uri.parse(project.covers._404));
        holder.bucketProjectName.setText(String.valueOf(project.name));
        holder.bucketProjectAuthor.setText(String.valueOf(project.owners.get(0).displayName));
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }
}
