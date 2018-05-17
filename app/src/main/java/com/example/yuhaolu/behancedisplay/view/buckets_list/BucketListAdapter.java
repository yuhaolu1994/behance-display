package com.example.yuhaolu.behancedisplay.view.buckets_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.model.Bucket;
import com.example.yuhaolu.behancedisplay.utils.ModelUtils;

import java.util.ArrayList;
import java.util.List;

public class BucketListAdapter extends RecyclerView.Adapter<BucketListViewHolder> {

    private Context context;
    private List<Bucket> buckets;
    private BucketAdapterListener listener;
    private SparseBooleanArray selectedItems;

    public BucketListAdapter(Context context, List<Bucket> buckets, BucketAdapterListener listener) {
        this.context = context;
        this.buckets = buckets;
        this.listener = listener;
        selectedItems = new SparseBooleanArray();
    }

    @Override
    public BucketListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bucket_item_shot, parent, false);
        return new BucketListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(BucketListViewHolder holder, final int position) {
        final Bucket bucket = buckets.get(position);
        holder.bucketName.setText(String.valueOf(bucket.bucketName));
        holder.shotCount.setText(bucket.shotNum + " shots");
        holder.shotChosen.setVisibility(View.GONE);

        holder.bucketLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onRowLongClicked(position);
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return buckets != null ? buckets.size() : 0;
    }

    public interface BucketAdapterListener {
        void onRowLongClicked(int position);
    }

    public void saveData() {
        ModelUtils.save(context, BucketListFragment.CREATED_BUCKET, buckets);
    }

    public void add(Bucket bucket) {
        buckets.add(bucket);
        notifyDataSetChanged();
    }

    public void toggleSelection(int pos) {
        selectedItems.put(pos, true);
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selectedItems.size());
        for (int i = 0; i < selectedItems.size(); i++) {
            items.add(selectedItems.keyAt(i));
        }
        return items;
    }

    public void removeData(int position) {
        buckets.remove(position);
    }

    public void clearSelections() {
        selectedItems.clear();
    }

}
