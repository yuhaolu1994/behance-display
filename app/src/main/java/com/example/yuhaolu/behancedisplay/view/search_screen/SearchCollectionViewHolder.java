package com.example.yuhaolu.behancedisplay.view.search_screen;

import android.view.View;
import android.widget.TextView;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.view.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchCollectionViewHolder extends BaseViewHolder {

    @BindView(R.id.collection_picture) SimpleDraweeView collectionPicture;
    @BindView(R.id.collection_name) TextView collectionName;
    @BindView(R.id.collection_count) TextView collectionCount;

    public SearchCollectionViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
