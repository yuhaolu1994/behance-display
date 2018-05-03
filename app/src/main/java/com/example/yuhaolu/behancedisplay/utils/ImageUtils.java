package com.example.yuhaolu.behancedisplay.utils;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.yuhaolu.behancedisplay.model.Project;
import com.example.yuhaolu.behancedisplay.model.ProjectDetail;
import com.facebook.drawee.view.SimpleDraweeView;

public class ImageUtils {
    public static void loadShotImage(@NonNull Project project, @NonNull SimpleDraweeView imageView) {
        String imageUrl = project.covers.original;
        if (!TextUtils.isEmpty(imageUrl)) {
            Uri uri = Uri.parse(imageUrl);
            imageView.setImageURI(uri);
        }
    }

    public static void loadShotDetailImage(@NonNull ProjectDetail projectDetail,
                                           @NonNull SimpleDraweeView imageView) {
        String imageUrl = projectDetail.covers.original;
        if (!TextUtils.isEmpty(imageUrl)) {
            Uri uri = Uri.parse(imageUrl);
            imageView.setImageURI(uri);
        }
    }
}
