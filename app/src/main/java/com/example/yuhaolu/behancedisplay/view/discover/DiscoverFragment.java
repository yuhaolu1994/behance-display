package com.example.yuhaolu.behancedisplay.view.discover;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.behance.Behance;
import com.example.yuhaolu.behancedisplay.model.CreativesToFollow;
import com.example.yuhaolu.behancedisplay.view.base.BehanceTask;
import com.example.yuhaolu.behancedisplay.view.base.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscoverFragment extends Fragment {

    @BindView(R.id.discover_recycler_view) RecyclerView recyclerView;

    private DiscoverCreativeAdapter adapter;

    public static DiscoverFragment newInstance() {
        return new DiscoverFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter = new DiscoverCreativeAdapter(new ArrayList<CreativesToFollow>(), view.getContext());
        new LoadCreativeTask().execute();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        int spacing = getResources().getDimensionPixelOffset(R.dimen.spacing_medium);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, spacing, true));
    }

    private class LoadCreativeTask extends BehanceTask<Void, Void, List<CreativesToFollow>> {

        @Override
        protected List<CreativesToFollow> doJob(Void... voids) { return Behance.getCreative(); }

        @Override
        protected void onSuccess(List<CreativesToFollow> creativeToFollow) {
            adapter.append(creativeToFollow);
        }
    }


}
