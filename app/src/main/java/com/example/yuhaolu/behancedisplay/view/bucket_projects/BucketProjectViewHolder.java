package com.example.yuhaolu.behancedisplay.view.bucket_projects;

import android.view.View;
import android.widget.TextView;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.view.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BucketProjectViewHolder extends BaseViewHolder {
    @BindView(R.id.bucket_project_picture) SimpleDraweeView bucketProjectPicture;
    @BindView(R.id.bucket_project_name) TextView bucketProjectName;
    @BindView(R.id.bucket_project_author) TextView bucketProjectAuthor;

    public BucketProjectViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
