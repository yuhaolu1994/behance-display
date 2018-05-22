package com.example.yuhaolu.behancedisplay.view.buckets_list;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.view.base.SingleFragmentActivity;

public class BucketListActivity extends SingleFragmentActivity {

    @NonNull
    @Override
    protected String getActivityTitle() { return getString(R.string.bucket_activity_title); }

    @NonNull
    @Override
    protected Fragment newFragment() {
        return BucketListFragment.newInstance(true);
    }

    @NonNull
    @Override
    protected void setBackIcon() { }

}
