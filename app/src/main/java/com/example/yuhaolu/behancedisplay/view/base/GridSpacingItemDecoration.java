package com.example.yuhaolu.behancedisplay.view.base;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

    private int spanCount;
    private int spacing;
    private boolean includeEdge;

    public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
        this.spanCount = spanCount;
        this.spacing = spacing;
        this.includeEdge = includeEdge;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        // get item position
        int position = parent.getChildAdapterPosition(view);
        // item column
        int column = position % spanCount;

        if (includeEdge) {
            outRect.left = spacing - column / spanCount * spacing;
            outRect.right = (column + 1) / spanCount * spacing;
            if (position < spanCount) {
                outRect.top = spacing;
            }
            outRect.bottom = spacing;
        } else {
            outRect.left = column / spanCount * spacing;
            outRect.right = spacing - (column + 1) / spanCount * spacing;
            if (position >= spanCount) {
                outRect.top = spacing;
            }
        }
    }
}
