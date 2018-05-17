package com.example.yuhaolu.behancedisplay.view.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.utils.ModelUtils;
import com.example.yuhaolu.behancedisplay.view.buckets_list.BucketListFragment;

import java.util.List;

public abstract class InfiniteAdapter<M> extends RecyclerView.Adapter<BaseViewHolder> {

    private static final int TYPE_LOADING = 0;
    private static final int TYPE_ITEM = 1;

    private final Context context;
    private List<M> data;
    private LoadMoreListener loadMoreListener;
    boolean showLoading;

    public InfiniteAdapter(@NonNull Context context,
                           @NonNull List<M> data,
                           @NonNull LoadMoreListener loadMoreListener) {
        this.context = context;
        this.data = data;
        this.loadMoreListener = loadMoreListener;
        this.showLoading = true;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_LOADING) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_show_loading, parent, false);
            return new BaseViewHolder(view);
        } else {
            return onCreateItemViewHolder(parent, viewType);
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        final int viewType = getItemViewType(position);
        if (viewType == TYPE_LOADING) {
            loadMoreListener.onLoadMore();
        } else {
            onBindItemViewHolder(holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (showLoading) {
            return position == data.size() ? TYPE_LOADING : TYPE_ITEM;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return showLoading ? data.size() + 1 : data.size();
    }

    public List<M> getData() {
        return data;
    }

    public void setData(@NonNull List<M> newData) {
        data.clear();
        data.addAll(newData);
        notifyDataSetChanged();
    }

    public void append(@NonNull List<M> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void add(@NonNull M data) {
        this.data.add(data);
        notifyDataSetChanged();
    }

    public void setShowLoading(boolean showLoading) {
        this.showLoading = showLoading;
        notifyDataSetChanged();
    }

    public Context getContext() {return context;}

    public abstract BaseViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType);

    protected abstract void onBindItemViewHolder(BaseViewHolder holder, int position);

    public interface LoadMoreListener { void onLoadMore();}
}
