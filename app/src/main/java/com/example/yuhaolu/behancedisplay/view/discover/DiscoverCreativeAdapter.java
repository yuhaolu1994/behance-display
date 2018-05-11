package com.example.yuhaolu.behancedisplay.view.discover;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.model.CreativesToFollow;

import java.util.ArrayList;
import java.util.List;

public class DiscoverCreativeAdapter extends RecyclerView.Adapter<DiscoverCreativeViewHolder> {

    private ArrayList<CreativesToFollow> creativeToFollow;
    private Context context;

    public DiscoverCreativeAdapter(ArrayList<CreativesToFollow> creativesToFollow, Context context) {
        this.creativeToFollow = creativesToFollow;
        this.context = context;
    }

    @Override
    public DiscoverCreativeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.discover_creatives_item, null);
        return new DiscoverCreativeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DiscoverCreativeViewHolder holder, int position) {
        final CreativesToFollow creative = creativeToFollow.get(position);
        holder.creativePicture.setImageURI(Uri.parse(String.valueOf(creative.images._138)));
        holder.creativeName.setText(String.valueOf(creative.displayName));
    }

    @Override
    public int getItemCount() {
        return (creativeToFollow != null ? creativeToFollow.size() : 0);
    }

    public void append(List<CreativesToFollow> creativeToFollow) {
        this.creativeToFollow.addAll(creativeToFollow);
        notifyDataSetChanged();
    }
}
