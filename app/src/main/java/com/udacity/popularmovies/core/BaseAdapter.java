package com.udacity.popularmovies.core;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    @NonNull
    private final AsyncListDiffer<T> differ;


    protected BaseAdapter(@NonNull final DiffUtil.ItemCallback<T> diffCallback) {
        differ = new AsyncListDiffer<>(this, diffCallback);
    }

    @NonNull
    public AsyncListDiffer<T> getDiffer() {
        return differ;
    }

    public void submitList(@NonNull List<T> list) {
        differ.submitList(list);
    }

    @NonNull
    protected T getItem(int position) {
        return differ.getCurrentList().get(position);
    }

    @Override
    public int getItemCount() {
        return differ.getCurrentList().size();
    }

    public int getPosition(@Nullable final T item) {
        return differ.getCurrentList().indexOf(item);
    }
}
