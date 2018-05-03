package com.example.yuhaolu.behancedisplay.view.project_detail;

import android.view.View;

import com.example.yuhaolu.behancedisplay.view.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.ButterKnife;

public class ProjectImageViewHolder extends BaseViewHolder {
    SimpleDraweeView image;
    public ProjectImageViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        image = (SimpleDraweeView) itemView;
    }
}
