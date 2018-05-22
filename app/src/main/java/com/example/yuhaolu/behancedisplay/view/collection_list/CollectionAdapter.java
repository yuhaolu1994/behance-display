package com.example.yuhaolu.behancedisplay.view.collection_list;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.behance.Behance;
import com.example.yuhaolu.behancedisplay.model.Project;
import com.example.yuhaolu.behancedisplay.model.ProjectDetail;
import com.example.yuhaolu.behancedisplay.utils.ImageUtils;
import com.example.yuhaolu.behancedisplay.utils.ModelUtils;
import com.example.yuhaolu.behancedisplay.view.base.BaseViewHolder;
import com.example.yuhaolu.behancedisplay.view.base.BehanceTask;
import com.example.yuhaolu.behancedisplay.view.base.InfiniteAdapter;
import com.example.yuhaolu.behancedisplay.view.project_detail.CommentActivity;
import com.example.yuhaolu.behancedisplay.view.project_detail.CommentFragment;
import com.example.yuhaolu.behancedisplay.view.project_detail.ProjectActivity;
import com.example.yuhaolu.behancedisplay.view.project_detail.ProjectFragment;
import com.google.gson.reflect.TypeToken;

import java.util.List;

public class CollectionAdapter extends InfiniteAdapter<Project> {

    private final CollectionFragment collectionFragment;

    public CollectionAdapter(@NonNull CollectionFragment collectionFragment,
                             @NonNull List<Project> data,
                             @NonNull LoadMoreListener loadMoreListener) {
        super(collectionFragment.getContext(), data, loadMoreListener);
        this.collectionFragment = collectionFragment;
    }

    @Override
    public BaseViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.collection_item_shot, parent, false);
        return new CollectionViewHolder(view);
    }

    @Override
    protected void onBindItemViewHolder(BaseViewHolder holder, int position) {
        CollectionViewHolder collectionViewHolder = (CollectionViewHolder) holder;
        final Project project = getData().get(position);
        collectionViewHolder.projectName.setText(String.valueOf(project.name));
        String projectField = TextUtils.join(" & ", project.fields);
        collectionViewHolder.projectField.setText(String.valueOf(projectField));
        collectionViewHolder.viewCount.setText(String.valueOf(project.stats.views));
        collectionViewHolder.likeCount.setText(String.valueOf(project.stats.appreciations));
        collectionViewHolder.commentCount.setText(String.valueOf(project.stats.comments));
        collectionViewHolder.commentCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CommentActivity.class);
                intent.putExtra(CommentFragment.KEY_PROJECT_ID, String.valueOf(project.id));
                collectionFragment.startActivity(intent);
            }
        });
        ImageUtils.loadShotImage(project, collectionViewHolder.image);

        collectionViewHolder.cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoadProjectDetailTask(project.id).execute();
            }
        });
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
            projectDetail.setBucketed(false);
            Intent intent = new Intent(getContext(), ProjectActivity.class);
            intent.putExtra(ProjectFragment.KEY_PROJECT,
                    ModelUtils.toString(projectDetail, new TypeToken<ProjectDetail>(){}));
            intent.putExtra(ProjectActivity.KEY_PROJECT_TITLE, projectDetail.name);
            collectionFragment.startActivity(intent);
        }
    }

}
