package com.arcsoft.arcfacedemo.city;

import android.view.View;

import com.arcsoft.arcfacedemo.databinding.ItemCityHeadBinding;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CityHeadViewHolder extends RecyclerView.ViewHolder {

    private ItemCityHeadBinding binding;

    public CityHeadViewHolder(@NonNull ItemCityHeadBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public ItemCityHeadBinding getBinding() {
        return binding;
    }

}
