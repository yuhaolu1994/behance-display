package com.example.yuhaolu.behancedisplay.view.project_detail;

import android.view.View;
import android.widget.TextView;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.view.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProjectDetailViewHolder extends BaseViewHolder {
    @BindView(R.id.project_view_count) TextView viewCount;
    @BindView(R.id.project_like_count) TextView likeCount;
    @BindView(R.id.project_comment_count) TextView commentCount;
    @BindView(R.id.project_share) TextView share;
    @BindView(R.id.shot_author_picture) SimpleDraweeView authorPicture;
    @BindView(R.id.shot_title) TextView title;
    @BindView(R.id.shot_author_name) TextView authorName;
    @BindView(R.id.shot_description) TextView description;

    public ProjectDetailViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
