package com.example.yuhaolu.behancedisplay.view.search_screen;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.behance.Behance;
import com.example.yuhaolu.behancedisplay.model.Collection_;
import com.example.yuhaolu.behancedisplay.model.Project;
import com.example.yuhaolu.behancedisplay.model.User_;
import com.example.yuhaolu.behancedisplay.toolbar.Searchbar;
import com.example.yuhaolu.behancedisplay.transition.FadeInTransition;
import com.example.yuhaolu.behancedisplay.transition.FadeOutTransition;
import com.example.yuhaolu.behancedisplay.transition.SimpleTransitionListener;
import com.example.yuhaolu.behancedisplay.utils.GallerySnapHelper;
import com.example.yuhaolu.behancedisplay.view.base.BehanceTask;
import com.example.yuhaolu.behancedisplay.view.base.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchActivity extends AppCompatActivity {

    @BindView(R.id.search_toolbar) Searchbar searchbar;
    @BindView(R.id.discover_project_title) TextView projectTitle;
    @BindView(R.id.recycler_view_discover) RecyclerView recyclerView;
    @BindView(R.id.discover_collection_title) TextView collectionTitle;
    @BindView(R.id.discover_collection_view) RecyclerView collectionView;
    @BindView(R.id.show_more_collection) TextView showMoreCollection;
    @BindView(R.id.discover_user_title) TextView userTitle;
    @BindView(R.id.discover_user_view) RecyclerView userView;
    @BindView(R.id.show_more_user) TextView showMoreUser;

    private SearchProjectAdapter adapter;
    private SearchCollectionAdapter collectionAdapter;
    private SearchUserAdapter userAdapter;
    private GallerySnapHelper snapHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        setSupportActionBar(searchbar);

        if (isFirstTimeRunning(savedInstanceState)) {
            searchbar.hideContent();
            ViewTreeObserver viewTreeObserver = searchbar.getViewTreeObserver();
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @TargetApi(Build.VERSION_CODES.KITKAT)
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                @Override
                public void onGlobalLayout() {
                    searchbar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    showSearch();
                }

                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                public void showSearch() {
                    TransitionManager.beginDelayedTransition(searchbar, FadeInTransition.createTransition());
                    searchbar.showContent();
                }
            });
        }
        setupProjectsView();
        setupCollectionsView();
        setupUsersView();
        setupSearch();
    }

    private void setupProjectsView() {
        snapHelper = new GallerySnapHelper();
        adapter = new SearchProjectAdapter(this, new ArrayList<Project>());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        snapHelper.attachToRecyclerView(recyclerView);
    }

    private void setupCollectionsView() {
        collectionAdapter = new SearchCollectionAdapter(this, new ArrayList<Collection_>());
        collectionView.setAdapter(collectionAdapter);
        collectionView.setLayoutManager(new LinearLayoutManager(this));
        collectionView.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.spacing_medium)));
    }

    private void setupUsersView() {
        userAdapter = new SearchUserAdapter(this, new ArrayList<User_>());
        userView.setAdapter(userAdapter);
        userView.setLayoutManager(new LinearLayoutManager(this));
        userView.addItemDecoration(new SpaceItemDecoration(getResources().getDimensionPixelSize(R.dimen.spacing_medium)));
    }

    private void setupSearch() {
        searchbar.editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (adapter.getItemCount() != 0 || collectionAdapter.getItemCount() != 0
                            || userAdapter.getItemCount() != 0) {
                        adapter.clearProjects();
                        collectionAdapter.clearCollection();
                        userAdapter.clearUser();
                        projectTitle.setVisibility(View.INVISIBLE);
                        collectionTitle.setVisibility(View.INVISIBLE);
                        showMoreCollection.setVisibility(View.INVISIBLE);
                        userTitle.setVisibility(View.INVISIBLE);
                        showMoreUser.setVisibility(View.INVISIBLE);
                    }
                    new LoadDiscoveredProjects(searchbar.editText.getText().toString()).execute();
                    new LoadDiscoveredCollections(searchbar.editText.getText().toString()).execute();
                    new LoadDiscoveredUsers(searchbar.editText.getText().toString()).execute();
                    hideKeyboard();
                    return true;
                }
                return false;
            }
        });
    }

    private class LoadDiscoveredProjects extends BehanceTask<Void, Void, List<Project>> {
        private String query;

        public LoadDiscoveredProjects(String query) {
            this.query = query;
        }

        @Override
        protected List<Project> doJob(Void... voids) {
            return Behance.getDiscoveredProjects(query);
        }

        @Override
        protected void onSuccess(List<Project> projects) {
            projectTitle.setVisibility(View.VISIBLE);
            ArrayList<Project> addProjects = new ArrayList<>();
            for (int i = 0; i < 10; i++) {
                addProjects.add(projects.get(i));
            }
            adapter.appendProjects(addProjects);
        }
    }

    private class LoadDiscoveredCollections extends BehanceTask<Void, Void, List<Collection_>> {

        private String query;

        public LoadDiscoveredCollections(String query) {
            this.query = query;
        }

        @Override
        protected List<Collection_> doJob(Void... voids) {
            return Behance.getDiscoveredCollections(query);
        }

        @Override
        protected void onSuccess(List<Collection_> collection_s) {
            collectionTitle.setVisibility(View.VISIBLE);
            ArrayList<Collection_> addCollections = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                addCollections.add(collection_s.get(i));
            }
            collectionAdapter.appendCollections(addCollections);
            showMoreCollection.setVisibility(View.VISIBLE);
        }
    }

    private class LoadDiscoveredUsers extends BehanceTask<Void, Void, List<User_>> {

        private String query;

        public LoadDiscoveredUsers(String query) {
            this.query = query;
        }

        @Override
        protected List<User_> doJob(Void... voids) {
            return Behance.getDiscoveredUsers(query);
        }

        @Override
        protected void onSuccess(List<User_> user_s) {
            userTitle.setVisibility(View.VISIBLE);
            ArrayList<User_> addUsers = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                addUsers.add(user_s.get(i));
            }
            userAdapter.appendUser(addUsers);
            showMoreUser.setVisibility(View.VISIBLE);
        }
    }

    private boolean isFirstTimeRunning(Bundle savedInstanceState) {
        return savedInstanceState == null;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void finish() {
        super.finish();
        hideKeyboard();
        exitTransitionWithAction(new Runnable() {
            @Override
            public void run() {
                SearchActivity.super.finish();
                overridePendingTransition(0, 0);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        } else if (item.getItemId() == R.id.action_clear) {
            searchbar.clearText();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void exitTransitionWithAction(final Runnable endingAction) {
        Transition transition = FadeOutTransition.withAction(new SimpleTransitionListener() {
            @Override
            public void onTransitionEnd(Transition transition) {
                endingAction.run();
            }
        });
        TransitionManager.beginDelayedTransition(searchbar, transition);
        searchbar.hideContent();
    }
}
