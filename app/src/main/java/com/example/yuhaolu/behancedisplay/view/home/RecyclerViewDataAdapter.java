package com.example.yuhaolu.behancedisplay.view.home;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.model.Project;
import com.example.yuhaolu.behancedisplay.model.SectionDataModel;
import com.example.yuhaolu.behancedisplay.utils.GallerySnapHelper;
import com.example.yuhaolu.behancedisplay.view.base.HomeItemDecoration;

import java.util.ArrayList;

public class RecyclerViewDataAdapter extends RecyclerView.Adapter<ItemRowHolder> {

    private ArrayList<SectionDataModel> dataList;
    private Context context;
    private RecyclerView.RecycledViewPool recycledViewPool;
    private GallerySnapHelper snapHelper;

    public RecyclerViewDataAdapter(ArrayList<SectionDataModel> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
        recycledViewPool = new RecyclerView.RecycledViewPool();
        snapHelper = new GallerySnapHelper();
    }

    @Override
    public ItemRowHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_field_projects, null);
        return new ItemRowHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemRowHolder holder, int position) {
        final String fieldTitle = dataList.get(position).getHeaderTitle();
        ArrayList<Project> projects = dataList.get(position).getAllItemInSection();
        holder.fieldName.setText(fieldTitle);
        SectionListDataAdapter projectAdapter = new SectionListDataAdapter(projects, context);
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(projectAdapter);
        holder.recyclerView.setRecycledViewPool(recycledViewPool);
        snapHelper.attachToRecyclerView(holder.recyclerView);
    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }


    public void appendProjects(ArrayList<Project> addProjects, String fieldName) {
        SectionDataModel dm = new SectionDataModel();
        dm.setHeaderTitle(fieldName);
        dm.setAllItemInSection(addProjects);
        dataList.add(dm);
        notifyDataSetChanged();
    }

}
