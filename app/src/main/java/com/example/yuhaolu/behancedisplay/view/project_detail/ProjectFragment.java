package com.example.yuhaolu.behancedisplay.view.project_detail;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.model.ProjectDetail;
import com.example.yuhaolu.behancedisplay.utils.ModelUtils;
import com.example.yuhaolu.behancedisplay.view.buckets_list.BucketListActivity;
import com.example.yuhaolu.behancedisplay.view.buckets_list.BucketListFragment;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectFragment extends Fragment {

    public static final String KEY_PROJECT = "project";
    public static final int REQ_CODE_BUCKET = 100;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    private ProjectDetail projectDetail;
    private ProjectAdapter adapter;

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
        adapter = new ProjectAdapter(this, projectDetail);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
    }

    // bucket function
    public void bucket() {
        Intent intent = new Intent(getContext(), BucketListActivity.class);
        String projectValue = ModelUtils.toString(projectDetail, new TypeToken<ProjectDetail>(){});
        intent.putExtra(BucketListFragment.KEY_CHOSEN_PROJECT, projectValue);
        startActivityForResult(intent, REQ_CODE_BUCKET);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE_BUCKET && resultCode == Activity.RESULT_OK) {
            // 更改收藏按钮图标
            List<String> chosenBucketNames = data.getStringArrayListExtra(
                    BucketListFragment.KEY_CHOSEN_BUCKETS_NAMES);
            if (chosenBucketNames.size() != 0) {
                adapter.setProjectBucketed();
            } else {
                adapter.setProjectUnBucketed();
            }

            // 处理更新的projectDetail中保存的bucketID
            String removeBucketID = data.getStringExtra(BucketListFragment.KEY_REMOVE_BUCKET_ID);
            if (removeBucketID != null) {
                projectDetail.bucketedName.remove(Integer.parseInt(removeBucketID));
                adapter.notifyDataSetChanged();
            }
        }
    }

    public void share(Context context) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, projectDetail.name + " " + projectDetail.url);
        shareIntent.setType("text/plain");
        context.startActivity(Intent.createChooser(shareIntent, getString(R.string.share_project)));
    }
}
