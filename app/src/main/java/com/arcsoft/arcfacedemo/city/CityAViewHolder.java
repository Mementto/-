package com.arcsoft.arcfacedemo.city;

import android.view.View;

import com.arcsoft.arcfacedemo.databinding.ItemCityPBinding;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CityAViewHolder extends RecyclerView.ViewHolder {

    private ItemCityPBinding binding;

    public CityAViewHolder(@NonNull ItemCityPBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public ItemCityPBinding getBinding() {
        return binding;
    }

}
