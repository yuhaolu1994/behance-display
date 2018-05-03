package com.example.yuhaolu.behancedisplay.view.project_detail;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.model.Comment_;
import com.example.yuhaolu.behancedisplay.view.base.BaseViewHolder;
import com.example.yuhaolu.behancedisplay.view.base.InfiniteAdapter;

import java.util.List;

public class CommentAdapter extends InfiniteAdapter<Comment_> {

    private final CommentFragment commentFragment;

    public CommentAdapter(@NonNull CommentFragment commentFragment,
                          @NonNull List<Comment_> data,
                          @NonNull LoadMoreListener loadMoreListener) {
        super(commentFragment.getContext(), data, loadMoreListener);
        this.commentFragment = commentFragment;
    }

    @Override
    public BaseViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext())
                .inflate(R.layout.project_comment_item, parent, false);
        return new CommentViewHolder(view);
    }

    @Override
    protected void onBindItemViewHolder(BaseViewHolder holder, int position) {
        CommentViewHolder commentViewHolder = (CommentViewHolder) holder;
        final Comment_ comment = getData().get(position);
        commentViewHolder.authorPicture.setImageURI(Uri.parse(comment.user.images._50));
        commentViewHolder.authorName.setText(String.valueOf(comment.user.displayName));
        commentViewHolder.comment.setText(String.valueOf(comment.comment));
    }
}
