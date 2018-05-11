package com.example.yuhaolu.behancedisplay.view.discover;

import android.view.View;
import android.widget.TextView;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.view.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiscoverCreativeViewHolder extends BaseViewHolder {

    @BindView(R.id.creative_picture) SimpleDraweeView creativePicture;
    @BindView(R.id.creative_name) TextView creativeName;

    public DiscoverCreativeViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
