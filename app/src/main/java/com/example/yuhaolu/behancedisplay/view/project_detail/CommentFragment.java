package com.example.yuhaolu.behancedisplay.view.project_detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.behance.Behance;
import com.example.yuhaolu.behancedisplay.model.Comment_;
import com.example.yuhaolu.behancedisplay.view.base.BehanceTask;
import com.example.yuhaolu.behancedisplay.view.base.InfiniteAdapter;
import com.example.yuhaolu.behancedisplay.view.base.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentFragment extends Fragment {

    @BindView(R.id.recycler_view) RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh_container) SwipeRefreshLayout swipeRefreshLayout;

    public static final String KEY_PROJECT_ID = "comments";

    private CommentAdapter adapter;
    private String projectId;

    public static CommentFragment newInstance(@NonNull Bundle args) {
        CommentFragment commentFragment = new CommentFragment();
        commentFragment.setArguments(args);
        return commentFragment;
    }

    private InfiniteAdapter.LoadMoreListener loadMoreListener = new InfiniteAdapter.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            new loadCommentsTask(false).execute();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_comment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        projectId = String.valueOf(getArguments().get(KEY_PROJECT_ID));

        swipeRefreshLayout.setEnabled(false);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new loadCommentsTask(true).execute();
            }
        });
        adapter = new CommentAdapter(this, new ArrayList<Comment_>(), loadMoreListener);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(
                getResources().getDimensionPixelSize(R.dimen.spacing_medium)));
    }

    private class loadCommentsTask extends BehanceTask<Void, Void, List<Comment_>> {

        private boolean refresh;

        public loadCommentsTask(boolean refresh) {
            this.refresh = refresh;
        }

        int page = refresh ? 1 : adapter.getData().size() / Behance.COMMENT_COUNT_PER_PAGE + 1;

        @Override
        protected List<Comment_> doJob(Void... voids) { return Behance.getComments(projectId, page); }

        @Override
        protected void onSuccess(List<Comment_> comments) {

            adapter.setShowLoading(comments.size() >= Behance.COMMENT_COUNT_PER_PAGE);

            if (refresh) {
                swipeRefreshLayout.setRefreshing(false);
                adapter.setData(comments);
            } else {
                swipeRefreshLayout.setEnabled(true);
                adapter.append(comments);
            }
        }
    }

}
