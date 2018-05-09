package com.example.yuhaolu.behancedisplay.view.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.yuhaolu.behancedisplay.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ItemRowHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.home_field_name) TextView fieldName;
    @BindView(R.id.recycler_view_list) RecyclerView recyclerView;

    public ItemRowHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
