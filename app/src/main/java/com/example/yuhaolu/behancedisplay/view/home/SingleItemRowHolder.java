package com.example.yuhaolu.behancedisplay.view.home;

import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.TextView;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.view.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SingleItemRowHolder extends BaseViewHolder {
    @BindView(R.id.home_project_image) SimpleDraweeView projectImage;
    @BindView(R.id.home_project_name) TextView projectName;
    @BindView(R.id.home_author_name) TextView authorName;
    @BindView(R.id.home_clickable_project) CardView cover;

    public SingleItemRowHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
