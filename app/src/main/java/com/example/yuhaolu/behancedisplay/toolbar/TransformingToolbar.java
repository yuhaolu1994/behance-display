package com.example.yuhaolu.behancedisplay.toolbar;

import android.content.Context;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;

public class TransformingToolbar extends Toolbar {

    public TransformingToolbar(Context context, AttributeSet attrs) { super(context, attrs); }

    public void hideContent() {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(GONE);
        }
    }

    public void showContent() {
        for (int i = 0; i < getChildCount(); i++) {
            getChildAt(i).setVisibility(VISIBLE);
        }
    }

}
