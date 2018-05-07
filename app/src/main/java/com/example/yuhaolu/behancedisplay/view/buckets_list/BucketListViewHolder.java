package com.example.yuhaolu.behancedisplay.view.buckets_list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.view.base.BaseViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BucketListViewHolder extends BaseViewHolder {

    @BindView(R.id.bucket_name) TextView bucketName;
    @BindView(R.id.bucket_shot_count) TextView shotCount;
    @BindView(R.id.bucket_shot_chosen) ImageView shotChosen;
    @BindView(R.id.bucket_layout) View bucketLayout;

    public BucketListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
