package com.example.yuhaolu.behancedisplay.view.collection_list;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.behance.Behance;
import com.example.yuhaolu.behancedisplay.model.Project;
import com.example.yuhaolu.behancedisplay.utils.DoubleClickBackToTop;
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
    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.toolbar_title) TextView toolbarTitle;

    private CollectionAdapter adapter;
    private static String city;

    private InfiniteAdapter.LoadMoreListener onLoadMore = new InfiniteAdapter.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            new LoadShotsTask(false).execute();
        }
    };

    public static CollectionFragment newInstance(String cityName) {
        city = cityName;
        CollectionFragment collectionFragment = new CollectionFragment();
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
        toolbarTitle.setText(R.string.nearby_title);
        toolbar.setOnClickListener(new DoubleClickBackToTop(new DoubleClickBackToTop.BackToContentTopView() {
            @Override
            public void backToContentTop() {
                recyclerView.scrollToPosition(0);
            }
        }));

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
