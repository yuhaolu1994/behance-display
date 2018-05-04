package com.example.yuhaolu.behancedisplay.view.project_detail;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.view.base.SingleFragmentActivity;

public class ProjectActivity extends SingleFragmentActivity {

    public static final String KEY_PROJECT_TITLE = "project_title";

    private ProjectFragment projectFragment;

    @NonNull
    @Override
    protected String getActivityTitle() { return getIntent().getStringExtra(KEY_PROJECT_TITLE); }

    @NonNull
    @Override
    protected Fragment newFragment() {
        projectFragment = ProjectFragment.newInstance(getIntent().getExtras());
        return projectFragment;
    }

    @NonNull
    @Override
    protected void setBackIcon() { }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.project_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.project_action_share) {
            projectFragment.share(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
