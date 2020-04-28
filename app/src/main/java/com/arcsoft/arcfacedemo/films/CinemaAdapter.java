package com.arcsoft.arcfacedemo.films;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.arcsoft.arcfacedemo.BR;
import com.arcsoft.arcfacedemo.R;
import com.arcsoft.arcfacedemo.databinding.ItemCinemaDetailBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class CinemaAdapter extends RecyclerView.Adapter<CinemaViewHolder> {

    private LayoutInflater layoutInflater;
    private List<CinemaInfoBean> list;

    public CinemaAdapter(Context context, List<CinemaInfoBean> list) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.list = list;
    }

    @NonNull
    @Override
    public CinemaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCinemaDetailBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_cinema_detail, parent, false);
        return new CinemaViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CinemaViewHolder holder, int position) {
        CinemaInfoBean bean = list.get(position);
        holder.getBinding().setVariable(BR.cinema_info, bean);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
