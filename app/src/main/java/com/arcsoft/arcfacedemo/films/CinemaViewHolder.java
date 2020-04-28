package com.arcsoft.arcfacedemo.films;

import android.view.View;

import com.arcsoft.arcfacedemo.databinding.ItemCinemaDetailBinding;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class CinemaViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private T binding;

    public CinemaViewHolder(@NonNull T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public T getBinding() {
        return binding;
    }
}
