package com.example.yuhaolu.behancedisplay.view.home;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.model.Project;
import com.example.yuhaolu.behancedisplay.model.SectionDataModel;
import com.example.yuhaolu.behancedisplay.utils.GallerySnapHelper;

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
        final String sectionName = dataList.get(position).getHeaderTitle();
        ArrayList projects = dataList.get(position).getAllItemInSection();
        holder.fieldName.setText(sectionName);
        SectionListDataAdapter adapter = new SectionListDataAdapter(projects, context);
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        holder.recyclerView.setAdapter(adapter);
        holder.recyclerView.setRecycledViewPool(recycledViewPool);
        snapHelper.attachToRecyclerView(holder.recyclerView);
    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public void append(ArrayList<Project> addProjects, String fieldName) {
        SectionDataModel dm = new SectionDataModel();
        dm.setHeaderTitle(fieldName);
        dm.setAllItemInSection(addProjects);
        dataList.add(dm);
        notifyDataSetChanged();
    }

}
