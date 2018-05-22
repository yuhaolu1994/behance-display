package com.example.yuhaolu.behancedisplay.view.buckets_list;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.view.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BucketListViewHolder extends BaseViewHolder {

    @BindView(R.id.bucket_fragment_layout) FrameLayout bucketFrameLayout;
    @BindView(R.id.bucket_image) SimpleDraweeView bucketImage;
    @BindView(R.id.bucket_linear_layout) LinearLayout bucketLayout;
    @BindView(R.id.bucket_name) TextView bucketName;
    @BindView(R.id.bucket_shot_count) TextView shotCount;
    @BindView(R.id.bucket_shot_chosen) ImageView bucketChosen;
    @BindView(R.id.view_background) RelativeLayout viewBackground;
    @BindView(R.id.view_foreground) RelativeLayout viewForeground;

    public BucketListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
