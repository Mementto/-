package com.arcsoft.arcfacedemo.city;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arcsoft.arcfacedemo.BR;
import com.arcsoft.arcfacedemo.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class BeanAdapter extends RecyclerView.Adapter<CityViewHolder> {

    private static final int ITEM_VIEW_TYPE_HEAD = 0;
    private static final int ITEM_VIEW_TYPE_NAME = 1;
    private static final int ITEM_VIEW_TYPE_A = 2;

    private final LayoutInflater layoutInflater;
    private List<CityData> cityData;

    public BeanAdapter(Context context, List<CityData> cityData) {
        super();
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.cityData = cityData;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ITEM_VIEW_TYPE_HEAD;
        } else if (position == 1 ||
                ! cityData.get(position - 1).getFirstA().equals(cityData.get(position).getFirstA())) {
            return ITEM_VIEW_TYPE_A;
        } else {
            return ITEM_VIEW_TYPE_NAME;
        }
    }

    @NonNull
    @Override
    public CityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewDataBinding binding;
        if (viewType == ITEM_VIEW_TYPE_A) {
            binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_city_p, parent, false);
        } else if (viewType == ITEM_VIEW_TYPE_NAME){
            binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_city_name, parent, false);
        } else {
            binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_city_head, parent, false);
        }
        return new CityViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CityViewHolder holder, int position) {
        CityData data = cityData.get(position);
        holder.getBinding().setVariable(BR.city_bean, data);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return cityData.size();
    }
}
