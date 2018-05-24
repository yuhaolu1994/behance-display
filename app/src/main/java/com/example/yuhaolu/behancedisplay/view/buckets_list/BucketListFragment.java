package com.example.yuhaolu.behancedisplay.view.buckets_list;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.ActionMode;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yuhaolu.behancedisplay.LoginActivity;
import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.model.AppUser;
import com.example.yuhaolu.behancedisplay.model.Bucket;
import com.example.yuhaolu.behancedisplay.model.ProjectDetail;
import com.example.yuhaolu.behancedisplay.utils.DatabaseHelper;
import com.example.yuhaolu.behancedisplay.utils.ModelUtils;
import com.example.yuhaolu.behancedisplay.utils.RecyclerItemTouchHelper;
import com.example.yuhaolu.behancedisplay.view.base.SpaceItemDecoration;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BucketListFragment extends Fragment implements BucketListAdapter.BucketAdapterListener,
        RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    @BindView(R.id.bucket_fragment_layout) RelativeLayout bucketFragment;
    @BindView(R.id.fab) FloatingActionButton fab;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.toolbar_title) TextView toolbarTitle;
    @BindView(R.id.btn_logout) ImageView logOutImage;

    public static final String CREATED_BUCKET = "created_bucket";
    public static final int REQ_CODE_NEW_BUCKET = 100;
    public static final String KEY_CHOOSING_MODE = "choosing_mode";
    public static final String KEY_CHOSEN_BUCKETS_NAMES = "chosen_bucket_names";
    public static final String KEY_CHOSEN_PROJECT = "chosen_project_detail";
    public static final String KEY_APP_USER = "app_user";

    private BucketListAdapter adapter;
    private List<Bucket> buckets;
    private ActionMode actionMode;
    private ActionModeCallback actionModeCallback;
    private boolean isChoosingMode;
    private ProjectDetail projectDetail;
    private List<Bucket> savedBuckets;
    private AppUser user;
    private DatabaseHelper databaseHelper;

//    public static BucketListFragment newInstance(boolean isChoosingMode, String appUser) {
//        Bundle args = new Bundle();
//        args.putBoolean(KEY_CHOOSING_MODE, isChoosingMode);
//        args.putString(KEY_APP_USER, appUser);
//        BucketListFragment bucketsFragment = new BucketListFragment();
//        bucketsFragment.setArguments(args);
//        return bucketsFragment;
//    }

    public static BucketListFragment newInstance(boolean isChoosingMode) {
        Bundle args = new Bundle();
        args.putBoolean(KEY_CHOOSING_MODE, isChoosingMode);
        BucketListFragment bucketsFragment = new BucketListFragment();
        bucketsFragment.setArguments(args);
        return bucketsFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buckets, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        databaseHelper = new DatabaseHelper(getContext());

        actionModeCallback = new ActionModeCallback();

        toolbarTitle.setText(R.string.bucket_title);

        savedBuckets = ModelUtils.read(view.getContext(), CREATED_BUCKET,
                new TypeToken<List<Bucket>>(){});

        buckets = savedBuckets == null ? new ArrayList() : savedBuckets;

        isChoosingMode = getArguments().getBoolean(KEY_CHOOSING_MODE);

//        user = ModelUtils.toObject(getArguments().getString(KEY_APP_USER), new TypeToken<AppUser>(){});

        if (isChoosingMode) {
            toolbar.setVisibility(View.GONE);
            projectDetail = ModelUtils.toObject(getActivity().getIntent().getExtras().getString(KEY_CHOSEN_PROJECT),
                    new TypeToken<ProjectDetail>(){});

            for (int i = 0; i < buckets.size(); i++) {
                List<ProjectDetail> bucketProjects = buckets.get(i).bucketProjects;
                for (ProjectDetail project : bucketProjects) {
                    if (project.name.contentEquals(projectDetail.name)) {
                        buckets.get(i).setChoosing(true);
                    } else {
                        buckets.get(i).setChoosing(false);
                    }
                }
            }
        }

        adapter = new BucketListAdapter(view.getContext(), buckets, this, isChoosingMode);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(getResources().
                getDimensionPixelSize(R.dimen.spacing_medium)));

        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(
                0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewBucketDialogFragment dialogFragment = NewBucketDialogFragment.newInstance();
                dialogFragment.setTargetFragment(BucketListFragment.this, REQ_CODE_NEW_BUCKET);
                dialogFragment.show(getFragmentManager(), NewBucketDialogFragment.TAG);
            }
        });

//        logOutImage.setVisibility(View.VISIBLE);
//        logOutImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String bucketsValue = ModelUtils.toString(adapter.getBuckets(),
//                        new TypeToken<List<Bucket>>(){});
//                user.setBuckets(bucketsValue);
//                databaseHelper.updateUser(user);
//                getActivity().finish();
//            }
//        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        if (isChoosingMode) {
            inflater.inflate(R.menu.bucket_list_choose_mode_menu, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.save) {
            ArrayList<String> chosenBucketsNames = new ArrayList<>();

            boolean containInBucket = false;

            for (int i = 0; i < adapter.getBuckets().size(); i++) {
                if (adapter.getBuckets().get(i).isChoosing) {
                    String chosenBucketName = adapter.getBuckets().get(i).getBucketName();
                    chosenBucketsNames.add(chosenBucketName);
                    if (!projectDetail.bucketedName.contains(chosenBucketName)) {
                        adapter.getBuckets().get(i).bucketProjects.add(projectDetail);
                    } else {
                        Toast.makeText(getContext(), "Already Exist In " + adapter.getBuckets().get(i).bucketName,
                                Toast.LENGTH_LONG).show();
                        containInBucket = true;
                        break;
                    }
                    adapter.getBuckets().get(i).shotNum += 1;
                    adapter.saveData();
                    adapter.notifyDataSetChanged();
                }
            }

            if (!containInBucket) {
                Intent result = new Intent();
                result.putStringArrayListExtra(KEY_CHOSEN_BUCKETS_NAMES, chosenBucketsNames);
                getActivity().setResult(Activity.RESULT_OK, result);
                getActivity().finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQ_CODE_NEW_BUCKET && resultCode == Activity.RESULT_OK) {
            Bucket bucket = new Bucket();
            bucket.bucketName = data.getStringExtra(NewBucketDialogFragment.KEY_BUCKET_NAME);
            bucket.bucketDescription = data.getStringExtra(NewBucketDialogFragment.KEY_BUCKET_DESCRIPTION);
            bucket.shotNum = 0;
            bucket.bucketProjects = new ArrayList<>();
            adapter.add(bucket);
            adapter.saveData();
        }
    }

    @Override
    public void onRowLongClicked(int position) {
        enableActionMode(position);
    }

    private void enableActionMode(int position) {
        if (actionMode == null) {
            actionMode = ((AppCompatActivity) getActivity()).startSupportActionMode(actionModeCallback);
        }
        toggleSelection(position);
    }

    private void toggleSelection(int position) {
        adapter.toggleSelection(position);
        int count = adapter.getSelectedItemsCount();
        if (count == 0) {
            actionMode.finish();
        } else {
            actionMode.setTitle(String.valueOf(count));
            actionMode.invalidate();
        }
    }

    /**
     * callback when recycler view is swiped
     * item will be removed on swiped
     * undo option will be provided in snackbar to restore the item
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        if (viewHolder instanceof BucketListViewHolder) {
            String name = buckets.get(viewHolder.getAdapterPosition()).getBucketName();

            final Bucket deletedBucket = buckets.get(viewHolder.getAdapterPosition());
            final int deletedIndex = viewHolder.getAdapterPosition();

            adapter.removeData(deletedIndex);
            adapter.saveData();

            Snackbar snackbar = Snackbar
                    .make(bucketFragment, name + " removed from buckets!", Snackbar.LENGTH_SHORT);
            snackbar.setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    adapter.restoreData(deletedIndex, deletedBucket);
                    adapter.saveData();
                }
            });
            snackbar.getView().setBackgroundColor(Color.WHITE);
            snackbar.setActionTextColor(Color.BLACK);
            snackbar.show();
        }
    }

    // Implement ActionMode.Callback interface
    private class ActionModeCallback implements ActionMode.Callback {

        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.menu_action_mode, menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()) {
                case R.id.action_delete:
                    deleteBuckets();
                    mode.finish();
                    return true;
                default:
                    return false;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            adapter.clearSelections();
            actionMode = null;
        }

        private void deleteBuckets() {
            List<Integer> selectedItemPositions = adapter.getSelectedItems();
            for (int i = selectedItemPositions.size() - 1; i >= 0; i--) {
                adapter.removeData(selectedItemPositions.get(i));
            }
            adapter.saveData();
        }
    }


}
