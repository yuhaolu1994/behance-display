package com.example.yuhaolu.behancedisplay.view.bucket_projects;

import android.support.v4.app.Fragment;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.view.base.SingleFragmentActivity;

public class BucketProjectActivity extends SingleFragmentActivity {

    public static final String KEY_BUCKET_TITLE = "bucket_title";

    @Override
    protected String getActivityTitle() { return getIntent().getStringExtra(KEY_BUCKET_TITLE); }

    @Override
    protected Fragment newFragment() { return BucketProjectFragment.newInstance(getIntent().getExtras()); }

    @Override
    protected void setBackIcon() {
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_clear_black_24dp);
    }
}
