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

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.behance.Behance;
import com.example.yuhaolu.behancedisplay.model.Project;
import com.example.yuhaolu.behancedisplay.model.SectionDataModel;
import com.example.yuhaolu.behancedisplay.view.base.BehanceTask;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {

    @BindView(R.id.home_recycler_view) RecyclerView recyclerView;

    private ArrayList<SectionDataModel> allSampleData;

    private RecyclerViewDataAdapter adapter;

    public static HomeFragment newInstance() {return new HomeFragment();}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = getLayoutInflater().inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        allSampleData = new ArrayList<>();
        adapter = new RecyclerViewDataAdapter(allSampleData, view.getContext());
        if (allSampleData.size() == 0) {
            new LoadHomeProjectsTask("Advertising").execute();
            new LoadHomeProjectsTask("Architecture").execute();
            new LoadHomeProjectsTask("Branding").execute();
        }
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(adapter);
    }

    public class LoadHomeProjectsTask extends BehanceTask<Void, Void, List<Project>> {

        private String fieldName;

        public LoadHomeProjectsTask(String fieldName) {
            this.fieldName = fieldName;
        }

        @Override
        protected List<Project> doJob(Void... voids) {
            return Behance.getHomeProjects(fieldName);
        }

        @Override
        protected void onSuccess(List<Project> projects) {
            ArrayList<Project> addProjects = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                addProjects.add(projects.get(i));
            }
            adapter.append(addProjects, fieldName);
        }
    }
}
