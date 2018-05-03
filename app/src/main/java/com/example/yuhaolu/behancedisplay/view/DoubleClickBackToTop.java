package com.example.yuhaolu.behancedisplay.view;

import android.view.View;

public class DoubleClickBackToTop implements View.OnClickListener {

    private long lastClickTime = 0;

    // 300ms点击延时
    private static final long delayTime = 300;

    //final保证属性会出现在构造函数中
    private final BackToContentTopView backToContentTopView;

    public DoubleClickBackToTop(BackToContentTopView backToContentTopView) {
        this.backToContentTopView = backToContentTopView;
    }

    @Override
    public void onClick(View view) {
        long currentClickTime = System.currentTimeMillis();
        if (currentClickTime - lastClickTime > delayTime) {
            lastClickTime = currentClickTime;
        } else {
            onDoubleClick();
        }
    }

    public void onDoubleClick() {
        backToContentTopView.backToContentTop();
    }

    public interface BackToContentTopView {
        void backToContentTop();
    }
}
