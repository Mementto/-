package com.arcsoft.arcfacedemo.city;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class CityViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private T binding;

    public CityViewHolder(@NonNull T binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public T getBinding() {
        return binding;
    }
}
