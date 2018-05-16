package com.example.yuhaolu.behancedisplay.view.search_screen;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.model.Collection_;

import java.util.ArrayList;
import java.util.List;

public class SearchCollectionAdapter extends RecyclerView.Adapter<SearchCollectionViewHolder> {

    private Context context;
    private List<Collection_> collections;

    public SearchCollectionAdapter(Context context, List<Collection_> collections) {
        this.context = context;
        this.collections = collections;
    }

    @Override
    public SearchCollectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discover_collection_item, null);
        return new SearchCollectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchCollectionViewHolder holder, int position) {
        final Collection_ collection = collections.get(position);
        if (collection.projectCovers != null) {
            holder.collectionPicture.setImageURI(Uri.parse(collection.projectCovers.get(0).url));
        }
        holder.collectionName.setText(String.valueOf(collection.title));
        holder.collectionCount.setText(String.valueOf(collection.stats.items) + " Items");
    }

    @Override
    public int getItemCount() {
        return collections != null ? collections.size() : 0;
    }

    public void appendCollections(ArrayList<Collection_> addCollections) {
        collections.addAll(addCollections);
        notifyDataSetChanged();
    }

    public void clearCollection() {
        collections.clear();
        notifyDataSetChanged();
    }
}
