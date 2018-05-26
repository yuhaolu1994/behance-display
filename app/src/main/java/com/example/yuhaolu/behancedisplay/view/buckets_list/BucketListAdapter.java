package com.example.yuhaolu.behancedisplay.view.buckets_list;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.model.Bucket;
import com.example.yuhaolu.behancedisplay.utils.ModelUtils;
import com.example.yuhaolu.behancedisplay.view.bucket_projects.BucketProjectActivity;
import com.example.yuhaolu.behancedisplay.view.bucket_projects.BucketProjectFragment;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class BucketListAdapter extends RecyclerView.Adapter<BucketListViewHolder> {

    private Context context;
    private List<Bucket> buckets;
    private BucketAdapterListener listener;
    private SparseBooleanArray selectedItems;
    private boolean isChoosingMode;

    public BucketListAdapter(Context context,
                             List<Bucket> buckets,
                             BucketAdapterListener listener,
                             boolean isChoosingMode) {
        this.context = context;
        this.buckets = buckets;
        this.listener = listener;
        this.isChoosingMode = isChoosingMode;
        selectedItems = new SparseBooleanArray();
    }

    @Override
    public BucketListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bucket_item_shot, parent, false);
        return new BucketListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BucketListViewHolder holder, final int position) {
        final Bucket bucket = buckets.get(position);
        if (bucket.bucketProjects.size() != 0) {
            holder.bucketImage.setImageURI(Uri.parse(bucket.bucketProjects.get(bucket.bucketProjects.size() - 1).covers._230));
        } else {
            holder.bucketImage.setImageURI(Uri.parse("res:/" + R.drawable.bucket_picture));
        }
        holder.bucketName.setText(String.valueOf(bucket.bucketName));
        holder.shotCount.setText(bucket.shotNum + " shots");

        if (isChoosingMode) {
            holder.bucketChosen.setVisibility(View.VISIBLE);
            holder.bucketChosen.setImageDrawable(
                    bucket.isChoosing
                            ? ContextCompat.getDrawable(context, R.drawable.ic_check_box_black_24dp)
                            : ContextCompat.getDrawable(context, R.drawable.ic_check_box_outline_blank_black_24dp));
        } else {
            holder.bucketChosen.setVisibility(View.GONE);
        }

        holder.bucketLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onRowLongClicked(position);
                view.performHapticFeedback(HapticFeedbackConstants.LONG_PRESS);
                holder.bucketChosen.setVisibility(View.VISIBLE);
                holder.bucketChosen.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_check_box_black_24dp));
                return true;
            }
        });

        holder.bucketChosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bucket.isChoosing = !bucket.isChoosing;
                notifyItemChanged(position);
            }
        });

        holder.bucketFrameLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, BucketProjectActivity.class);
                intent.putExtra(BucketProjectActivity.KEY_BUCKET_TITLE, bucket.bucketName);
                intent.putExtra(BucketProjectFragment.KEY_BUCKET_PROJECTS,
                        ModelUtils.toString(buckets.get(position), new TypeToken<Bucket>(){}));
                context.startActivity(intent);
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

    public List<Bucket> getBuckets() {
        return buckets;
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

    public int getSelectedItemsCount() {
        return selectedItems.size();
    }

    public void removeData(int position) {
        buckets.remove(position);
        // notify the item removed by position
        // to perform recycler view delete animations
        // NOTE: don't call notifyDataSetChanged()
        notifyItemRemoved(position);
    }

    public void restoreData(int position, Bucket bucket){
        buckets.add(position, bucket);
        notifyItemInserted(position);
    }

    public void clearSelections() {
        selectedItems.clear();
        notifyDataSetChanged();
    }

    public void setBucketChosen(int bucketID) {
        buckets.get(bucketID).setChoosing(true);
        notifyDataSetChanged();
    }

    public void setBucketUnChosen(int bucketID) {
        buckets.get(bucketID).setChoosing(false);
        notifyDataSetChanged();
    }
}
