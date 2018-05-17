package com.example.yuhaolu.behancedisplay.view.project_detail;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.view.base.SingleFragmentActivity;

public class CommentActivity extends SingleFragmentActivity {

    @NonNull
    @Override
    protected String getActivityTitle() { return getString(R.string.comment_activity_title); }

    @NonNull
    @Override
    protected Fragment newFragment() {
        return CommentFragment.newInstance(getIntent().getExtras());
    }

    @NonNull
    @Override
    protected void setBackIcon() {
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_clear_black_24dp);
    }
}
