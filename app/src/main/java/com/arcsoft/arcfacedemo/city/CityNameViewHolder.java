package com.arcsoft.arcfacedemo.city;

import android.view.View;

import com.arcsoft.arcfacedemo.databinding.ItemCityNameBinding;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CityNameViewHolder extends RecyclerView.ViewHolder {

    private ItemCityNameBinding binding;

    public CityNameViewHolder(@NonNull ItemCityNameBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public ItemCityNameBinding getBinding() {
        return binding;
    }
}
