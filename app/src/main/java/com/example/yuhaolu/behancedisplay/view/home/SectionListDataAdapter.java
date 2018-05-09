package com.example.yuhaolu.behancedisplay.view.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.behance.Behance;
import com.example.yuhaolu.behancedisplay.model.Project;
import com.example.yuhaolu.behancedisplay.model.ProjectDetail;
import com.example.yuhaolu.behancedisplay.utils.ModelUtils;
import com.example.yuhaolu.behancedisplay.view.base.BehanceTask;
import com.example.yuhaolu.behancedisplay.view.project_detail.ProjectActivity;
import com.example.yuhaolu.behancedisplay.view.project_detail.ProjectFragment;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

public class SectionListDataAdapter extends RecyclerView.Adapter<SingleItemRowHolder> {

    private ArrayList<Project> projects;
    private Context context;

    public SectionListDataAdapter(ArrayList<Project> projects, Context context) {
        this.projects = projects;
        this.context = context;
    }

    @Override
    public SingleItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_project_item, null);
        return new SingleItemRowHolder(view);
    }

    @Override
    public void onBindViewHolder(SingleItemRowHolder holder, final int position) {
        final Project project = projects.get(position);
        holder.projectImage.setImageURI(Uri.parse(String.valueOf(project.covers.original)));
        holder.projectName.setText(String.valueOf(project.name));
        holder.authorName.setText(String.valueOf(project.owners.get(0).displayName));
        holder.cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoadProjectDetailTask(project.id).execute();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != projects ? projects.size() : 0);
    }

    private class LoadProjectDetailTask extends BehanceTask<Void, Void, ProjectDetail> {
        private int id;

        public LoadProjectDetailTask(int id) {
            this.id = id;
        }

        @Override
        protected ProjectDetail doJob(Void... voids) {
            return Behance.getProjectDetails(id);
        }

        @Override
        protected void onSuccess(ProjectDetail projectDetail) {
            Intent intent = new Intent(context, ProjectActivity.class);
            intent.putExtra(ProjectFragment.KEY_PROJECT,
                    ModelUtils.toString(projectDetail, new TypeToken<ProjectDetail>(){}));
            intent.putExtra(ProjectActivity.KEY_PROJECT_TITLE, projectDetail.name);
            context.startActivity(intent);
        }
    }
}
