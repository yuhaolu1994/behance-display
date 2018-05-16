package com.example.yuhaolu.behancedisplay.view.search_screen;

import android.view.View;
import android.widget.TextView;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.view.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchUserViewHolder extends BaseViewHolder {

    @BindView(R.id.discover_user_picture) SimpleDraweeView userPicture;
    @BindView(R.id.discover_user_name) TextView userName;
    @BindView(R.id.discover_user_field) TextView userField;

    public SearchUserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
