package com.example.yuhaolu.behancedisplay.utils;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.model.Project;
import com.example.yuhaolu.behancedisplay.model.ProjectDetail;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

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

    public static void loadLogo(SimpleDraweeView view) {
        // "https://cdn.dribbble.com/users/997070/screenshots/3083876/be.gif"
        ImageRequest imageRequest = ImageRequestBuilder.newBuilderWithResourceId(R.drawable.behance_logo).build();
        Uri imageUri = Uri.parse("res:/" + R.drawable.behance_logo);
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                                            .setImageRequest(ImageRequest.fromUri("https://cdn.dribbble.com/users/997070/screenshots/3083876/be.gif"))
                                            .setAutoPlayAnimations(true)
                                            .build();
        view.setController(controller);
    }
}
