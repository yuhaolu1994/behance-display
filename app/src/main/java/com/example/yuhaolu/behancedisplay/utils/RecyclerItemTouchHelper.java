package com.example.yuhaolu.behancedisplay.utils;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import com.example.yuhaolu.behancedisplay.R;
import com.example.yuhaolu.behancedisplay.view.buckets_list.BucketListViewHolder;

public class RecyclerItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    RecyclerItemTouchHelperListener listener;

    public RecyclerItemTouchHelper(int dragDirs, int swipeDirs, RecyclerItemTouchHelperListener listener) {
        super(dragDirs, swipeDirs);
        this.listener = listener;
    }

    // getDefaultUIUtil() will be used by ItemTouchHelper to detect whenever there is UI change on the view.
    // We use this function to keep the background view in a static position and move the foreground view.
    // Called when the ViewHolder swiped or dragged by the ItemTouchHelper is changed.
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        if (viewHolder != null) {
            final View foregroundView = ((BucketListViewHolder) viewHolder)
                    .itemView.findViewById(R.id.view_foreground);
            getDefaultUIUtil().onSelected(foregroundView);
        }
    }

    // Customize how your View's respond to user interactions
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView,
                            RecyclerView.ViewHolder viewHolder, float dX, float dY,
                            int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((BucketListViewHolder) viewHolder)
                .itemView.findViewById(R.id.view_foreground);
        getDefaultUIUtil().onDraw(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }

    // Customize how your View's respond to user interactions
    @Override
    public void onChildDrawOver(Canvas c, RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                int actionState, boolean isCurrentlyActive) {
        final View foregroundView = ((BucketListViewHolder) viewHolder)
                .itemView.findViewById(R.id.view_foreground);
        getDefaultUIUtil().onDrawOver(c, recyclerView, foregroundView, dX, dY,
                actionState, isCurrentlyActive);
    }

    // When the user interaction with an element is over and it also completed its animation.
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        final View foregroundView = ((BucketListViewHolder) viewHolder)
                .itemView.findViewById(R.id.view_foreground);
        getDefaultUIUtil().clearView(foregroundView);
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                          RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        listener.onSwiped(viewHolder, direction);
    }


    public interface RecyclerItemTouchHelperListener {
        void onSwiped(RecyclerView.ViewHolder viewHolder, int direction);
    }

}
