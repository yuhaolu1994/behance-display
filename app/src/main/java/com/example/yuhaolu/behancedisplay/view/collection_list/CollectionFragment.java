package com.example.yuhaolu.behancedisplay.view.collection_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.MainActivity;
import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.behance.Behance;
import com.example.yuhaolu.behancedisplay.model.Project;
import com.example.yuhaolu.behancedisplay.view.base.BehanceTask;
import com.example.yuhaolu.behancedisplay.view.base.InfiniteAdapter;
import com.example.yuhaolu.behancedisplay.view.base.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollectionFragment extends Fragment {
    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_container) SwipeRefreshLayout swipeRefreshLayout;

    private CollectionAdapter adapter;
    private static String city;

    public static final String COLLECTION_FRAGMENT_VALUE = "Collection";
    public static final int REQ_CODE_PROJECT = 100;

    private InfiniteAdapter.LoadMoreListener onLoadMore = new InfiniteAdapter.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            new LoadShotsTask(false).execute();
        }
    };

    public static CollectionFragment newInstance(String fragmentType, String cityName) {
        city = cityName;
        CollectionFragment collectionFragment = new CollectionFragment();
        Bundle args = new Bundle();
        args.putString(MainActivity.FRAGMENT_KEY, fragmentType);
        collectionFragment.setArguments(args);
        return collectionFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        // avoid refresh action while refreshing
        swipeRefreshLayout.setEnabled(false);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new LoadShotsTask(true).execute();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new CollectionAdapter(this, new ArrayList<Project>(), onLoadMore);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(
                getResources().getDimensionPixelSize(R.dimen.spacing_medium)));

    }

    private class LoadShotsTask extends BehanceTask<Void, Void, List<Project>> {

        private boolean refresh;

        public LoadShotsTask(boolean refresh) {
            this.refresh = refresh;
        }

        int page = refresh ? 1 : adapter.getData().size() / Behance.ITEM_COUNT_PER_PAGE + 1;

        @Override
        protected List<Project> doJob(Void... params) {
            return Behance.getShots(page, city);
        }

        @Override
        protected void onSuccess(List<Project> shots) {

            adapter.setShowLoading(shots.size() >= Behance.ITEM_COUNT_PER_PAGE);

            if (refresh) {
                // hide loading animation
                swipeRefreshLayout.setRefreshing(false);
                adapter.setData(shots);
            } else {
                swipeRefreshLayout.setEnabled(true);
                adapter.append(shots);
            }
        }
    }
}
