package com.arcsoft.arcfacedemo.city;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arcsoft.arcfacedemo.R;
import com.arcsoft.arcfacedemo.databinding.ItemCityHeadBinding;
import com.arcsoft.arcfacedemo.databinding.ItemCityNameBinding;
import com.arcsoft.arcfacedemo.databinding.ItemCityPBinding;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

public class BeanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int ITEM_VIEW_TYPE_HEAD = 0;
    private static final int ITEM_VIEW_TYPE_NAME = 1;
    private static final int ITEM_VIEW_TYPE_A = 2;

    private final LayoutInflater layoutInflater;
    private List<CityData> cityData;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onCityName(View view, String name);
        void refreshName(View view);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_VIEW_TYPE_A) {
            ItemCityPBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_city_p, parent, false);
            return new CityAViewHolder(binding);
        } else if (viewType == ITEM_VIEW_TYPE_NAME){
            ItemCityNameBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_city_name, parent, false);
            return new CityNameViewHolder(binding);
        } else {
            ItemCityHeadBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.item_city_head, parent, false);
            return new CityHeadViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {
        final CityData data = cityData.get(position);
        if (holder instanceof CityHeadViewHolder) {
            ((CityHeadViewHolder) holder).getBinding().setCityBean(data);
            hotCityClick(((CityHeadViewHolder) holder).getBinding().beijing
                    , ((CityHeadViewHolder) holder).getBinding().beijing.getText().toString());
            hotCityClick(((CityHeadViewHolder) holder).getBinding().shanghai
                    , ((CityHeadViewHolder) holder).getBinding().shanghai.getText().toString());
            hotCityClick(((CityHeadViewHolder) holder).getBinding().guangzhou
                    , ((CityHeadViewHolder) holder).getBinding().guangzhou.getText().toString());
            hotCityClick(((CityHeadViewHolder) holder).getBinding().shenzhen
                    , ((CityHeadViewHolder) holder).getBinding().shenzhen.getText().toString());
            ((CityHeadViewHolder) holder).getBinding().refreshCityName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.refreshName(v);
                }
            });
            ((CityHeadViewHolder) holder).getBinding().chooseNowCityName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onCityName(v, ((CityHeadViewHolder) holder).getBinding().cityNowName.getText().toString());
                }
            });
        }
        if (holder instanceof  CityNameViewHolder) {
            ((CityNameViewHolder) holder).getBinding().setCityBean(data);
            ((CityNameViewHolder) holder).getBinding().cityNameLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onCityName(v, data.getName());
                }
            });
        }
        if (holder instanceof CityAViewHolder){
            ((CityAViewHolder) holder).getBinding().setCityBean(data);
            ((CityAViewHolder) holder).getBinding().cityPLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onCityName(v, data.getName());
                }
            });
        }
    }

    private void hotCityClick(TextView textView, final String name) {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickListener.onCityName(v, name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityData.size();
    }
}
