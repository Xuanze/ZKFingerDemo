package com.zhongruan.android.zkfingerdemo.view;

import android.graphics.Canvas;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;

public class SimpleItemTouchHelperCallback extends Callback {
    public static final float ALPHA_FULL = 1.0f;
    private final ItemTouchHelperAdapter mAdapter;

    public SimpleItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {
        this.mAdapter = adapter;
    }

    public boolean isLongPressDragEnabled() {
        return true;
    }

    public boolean isItemViewSwipeEnabled() {
        return true;
    }

    public int getMovementFlags(RecyclerView recyclerView, ViewHolder viewHolder) {
        if (recyclerView.getLayoutManager() instanceof GridLayoutManager) {
            return Callback.makeMovementFlags(15, 0);
        }
        return Callback.makeMovementFlags(3, 48);
    }

    public boolean onMove(RecyclerView recyclerView, ViewHolder source, ViewHolder target) {
        if (source.getItemViewType() != target.getItemViewType()) {
            return false;
        }
        this.mAdapter.onItemMove(source.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    public void onSwiped(ViewHolder viewHolder, int i) {
        this.mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    public void onChildDraw(Canvas c, RecyclerView recyclerView, ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == 1) {
            viewHolder.itemView.setAlpha(ALPHA_FULL - (Math.abs(dX) / ((float) viewHolder.itemView.getWidth())));
            viewHolder.itemView.setTranslationX(dX);
            return;
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }

    public void onSelectedChanged(ViewHolder viewHolder, int actionState) {
        if (actionState != 0 && (viewHolder instanceof ItemTouchHelperViewHolder)) {
            ((ItemTouchHelperViewHolder) viewHolder).onItemSelected();
        }
        super.onSelectedChanged(viewHolder, actionState);
    }

    public void clearView(RecyclerView recyclerView, ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setAlpha(ALPHA_FULL);
        if (viewHolder instanceof ItemTouchHelperViewHolder) {
            ((ItemTouchHelperViewHolder) viewHolder).onItemClear();
        }
    }
}
