package com.example.yuhaolu.behancedisplay.view.discover;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.behance.Behance;
import com.example.yuhaolu.behancedisplay.model.CreativesToFollow;
import com.example.yuhaolu.behancedisplay.toolbar.SimpleToolbar;
import com.example.yuhaolu.behancedisplay.transition.FadeInTransition;
import com.example.yuhaolu.behancedisplay.transition.FadeOutTransition;
import com.example.yuhaolu.behancedisplay.transition.SimpleTransitionListener;
import com.example.yuhaolu.behancedisplay.view.base.BehanceTask;
import com.example.yuhaolu.behancedisplay.view.base.GridSpacingItemDecoration;
import com.example.yuhaolu.behancedisplay.view.search_screen.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscoverFragment extends Fragment {

    @BindView(R.id.discover_recycler_view) RecyclerView recyclerView;
    @BindView(R.id.simple_toolbar) SimpleToolbar simpleToolbar;

    private DiscoverCreativeAdapter adapter;
    private int toolbarMargin;

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
        toolbarMargin = getResources().getDimensionPixelOffset(R.dimen.toolbarMargin);
        simpleToolbar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                showKeyboard();
                transitionToSearch();
            }
        });
        adapter = new DiscoverCreativeAdapter(new ArrayList<CreativesToFollow>(), view.getContext());
        new LoadCreativeTask().execute();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 2));
        int spacing = getResources().getDimensionPixelOffset(R.dimen.spacing_medium);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, spacing, true));
    }

    public void showKeyboard() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void transitionToSearch() {
        Transition transition = FadeOutTransition.withAction(navigateToSearchWhenDone());
        TransitionManager.beginDelayedTransition(simpleToolbar, transition);
        FrameLayout.LayoutParams frameLP = (FrameLayout.LayoutParams) simpleToolbar.getLayoutParams();
        frameLP.setMargins(0, 0, 0, 0);
        simpleToolbar.setLayoutParams(frameLP);
        simpleToolbar.hideContent();
    }

    private Transition.TransitionListener navigateToSearchWhenDone() {
        return new SimpleTransitionListener() {
            @Override
            public void onTransitionEnd(Transition transition) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
            }
        };
    }

    private class LoadCreativeTask extends BehanceTask<Void, Void, List<CreativesToFollow>> {

        @Override
        protected List<CreativesToFollow> doJob(Void... voids) { return Behance.getCreative(); }

        @Override
        protected void onSuccess(List<CreativesToFollow> creativeToFollow) {
            adapter.append(creativeToFollow);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onResume() {
        super.onResume();
        fadeToolbarIn();
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void fadeToolbarIn() {
        TransitionManager.beginDelayedTransition(simpleToolbar, FadeInTransition.createTransition());
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) simpleToolbar.getLayoutParams();
        layoutParams.setMargins(toolbarMargin, toolbarMargin, toolbarMargin, toolbarMargin);
        simpleToolbar.setLayoutParams(layoutParams);
        simpleToolbar.showContent();
    }

}
