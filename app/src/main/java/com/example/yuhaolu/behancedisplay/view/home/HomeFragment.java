package com.example.yuhaolu.behancedisplay.view.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

public class HomeFragment extends Fragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.home_field_name)
    TextView fieldTitle;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    private HomeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_field_projects, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        fieldTitle.setText("Advertising");
        adapter = new HomeAdapter(view.getContext(), new ArrayList<Project>());
        new LoadHomeProjectsTask().execute();
        LinearLayoutManager manager = new LinearLayoutManager(view.getContext());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(getResources()
                .getDimensionPixelSize(R.dimen.spacing_medium)));
    }

    private class LoadHomeProjectsTask extends BehanceTask<Void, Void, List<Project>> {

        @Override
        protected List<Project> doJob(Void... voids) {
            return Behance.getHomeProjects("Advertising");
        }

        @Override
        protected void onSuccess(List<Project> projects) {
            List<Project> addProjects = new ArrayList<>();
            for (int i = 0; i < 10; ++i) {
                addProjects.add(projects.get(i));
            }
            adapter.append(addProjects);
        }
    }
}
