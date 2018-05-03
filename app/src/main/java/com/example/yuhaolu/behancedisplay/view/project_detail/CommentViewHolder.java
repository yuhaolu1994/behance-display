package com.example.yuhaolu.behancedisplay.view.project_detail;

import android.view.View;
import android.widget.TextView;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.view.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommentViewHolder extends BaseViewHolder {
    @BindView(R.id.comment_author_picture)SimpleDraweeView authorPicture;
    @BindView(R.id.comment_author_name) TextView authorName;
    @BindView(R.id.comment_content) TextView comment;

    public CommentViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
