package com.example.yuhaolu.behancedisplay.view.project_detail;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.example.yuhaolu.behancedisplay.view.base.SingleFragmentActivity;

public class ProjectActivity extends SingleFragmentActivity {

    public static final String KEY_PROJECT_TITLE = "project_title";

    @NonNull
    @Override
    protected String getActivityTitle() { return getIntent().getStringExtra(KEY_PROJECT_TITLE); }

    @NonNull
    @Override
    protected Fragment newFragment() { return ProjectFragment.newInstance(getIntent().getExtras()); }

    @NonNull
    @Override
    protected void setBackIcon() { }
}
