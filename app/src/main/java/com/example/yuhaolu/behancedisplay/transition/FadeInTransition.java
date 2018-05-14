package com.example.yuhaolu.behancedisplay.transition;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.transition.AutoTransition;
import android.transition.Transition;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class FadeInTransition extends AutoTransition {

    private static final int FADE_IN_DURATION = 200;

    public static Transition createTransition() {
        AutoTransition transition = new AutoTransition();
        transition.setDuration(FADE_IN_DURATION);
        return transition;
    }
}
