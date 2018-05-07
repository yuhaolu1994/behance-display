package com.example.yuhaolu.behancedisplay.view.buckets_list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.model.Bucket;
import com.example.yuhaolu.behancedisplay.view.base.BaseViewHolder;
import com.example.yuhaolu.behancedisplay.view.base.InfiniteAdapter;

import java.util.List;

public class BucketListAdapter extends InfiniteAdapter<Bucket> {


    public BucketListAdapter(@NonNull Context context,
                             @NonNull List<Bucket> data) {
        super(context, data);
    }

    @Override
    public BaseViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.bucket_item_shot, parent, false);
        return new BucketListViewHolder(view);
    }

    @Override
    protected void onBindItemViewHolder(BaseViewHolder holder, int position) {
        BucketListViewHolder bucketListViewHolder = (BucketListViewHolder) holder;
        final Bucket bucket = getData().get(position);
        bucketListViewHolder.bucketName.setText(String.valueOf(bucket.bucketName));
        bucketListViewHolder.shotCount.setText(bucket.shotNum + " shots");
        bucketListViewHolder.shotChosen.setVisibility(View.GONE);
    }
}
