package com.example.yuhaolu.behancedisplay;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

public class BehanceApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
    }
}
