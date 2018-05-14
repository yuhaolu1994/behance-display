package com.example.yuhaolu.behancedisplay.toolbar;

import android.content.Context;
import android.util.AttributeSet;

import com.example.yuhaolu.behancedisplay.R;

public class SimpleToolbar extends TransformingToolbar {

    public SimpleToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        setBackgroundColor(context.getResources().getColor(android.R.color.white));
        setNavigationIcon(R.drawable.ic_search_black_24dp);
        setTitle("Tap here to search");
    }
}
