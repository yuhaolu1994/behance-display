package com.example.yuhaolu.behancedisplay.view.collection_list;

import android.view.View;
import android.widget.TextView;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.view.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;

public class CollectionViewHolder extends BaseViewHolder {
    @BindView(R.id.shot_image) public SimpleDraweeView image;
    @BindView(R.id.project_name) public TextView projectName;
    @BindView(R.id.project_field) public TextView projectField;
    @BindView(R.id.shot_like_count) public TextView likeCount;
    @BindView(R.id.shot_view_count) public TextView viewCount;
    @BindView(R.id.shot_comment_count) public TextView commentCount;
    @BindView(R.id.shot_clickable_cover) public View cover;

    public CollectionViewHolder(View itemView) {
        super(itemView);
    }
}
