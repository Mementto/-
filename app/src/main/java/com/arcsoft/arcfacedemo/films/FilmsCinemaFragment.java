package com.arcsoft.arcfacedemo.films;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arcsoft.arcfacedemo.R;
import com.arcsoft.arcfacedemo.databinding.FragmentFilmsCinemaBinding;
import com.arcsoft.arcfacedemo.utils.StorageCity;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FilmsCinemaFragment extends Fragment {

    private FragmentFilmsCinemaBinding binding;
    private RecyclerView recyclerView;
    private CinemaAdapter adapter;
    private List<CinemaInfoBean> beans;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_films_cinema, container, false);
        binding.cinemaInfoRecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        setAdapter();
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        beans.clear();
        poi();
    }

    private void setAdapter() {
        beans = new ArrayList<>();
        adapter = new CinemaAdapter(getContext(), beans);
        binding.cinemaInfoRecyclerview.setAdapter(adapter);

        DividerItemDecoration divider = new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(getContext(), R.drawable.cinema_info_divider));
        binding.cinemaInfoRecyclerview.addItemDecoration(divider);
    }

    public void poi() {
        final PoiSearch poiSearch = PoiSearch.newInstance();

        OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                if (poiResult != null && poiResult.getAllPoi() != null) {
                    int n = poiResult.getAllPoi().size();
                    for (int i = 0; i < n; i ++) {
                        double x = setLength(poiResult.getAllPoi().get(i).getLocation().longitude,
                            poiResult.getAllPoi().get(i).getLocation().latitude,
                            StorageCity.getLong(getContext()),
                            StorageCity.getLat(getContext()));
                        x /= 1000;
                        BigDecimal bigDecimal = new BigDecimal(String.valueOf(x)).setScale(2, RoundingMode.HALF_UP);

                        String[] tag = poiResult.getAllPoi().get(i).getPoiDetailInfo().tag.split(";");

                        CinemaInfoBean bean = new CinemaInfoBean();
                        bean.setName(poiResult.getAllPoi().get(i).getName());
                        bean.setAddress(poiResult.getAllPoi().get(i).getAddress());
                        bean.setTag1(tag[0].trim());
                        bean.setTag2(tag[1].trim());
                        if (x > 100) {
                            bean.setLength(">100km");
                        } else {
                            bean.setLength(bigDecimal.toString() + "km");
                        }
                        beans.add(bean);
                    }
                }
                adapter.notifyDataSetChanged();
                poiSearch.destroy();
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }

            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }
        };
        poiSearch.setOnGetPoiSearchResultListener(listener);
        poiSearch.searchInCity(
                new PoiCitySearchOption()
                        .city(StorageCity.getCityName(getContext()))
                        .keyword("万达影城")
                        .scope(2));
    }

    private double setLength(double long1, double lat1, double long2, double lat2) {
        double radiansAX = Math.toRadians(long1); // A经弧度
        double radiansAY = Math.toRadians(lat1); // A纬弧度
        double radiansBX = Math.toRadians(long2); // B经弧度
        double radiansBY = Math.toRadians(lat2); // B纬弧度

        // 公式中“cosβ1cosβ2cos（α1-α2）+sinβ1sinβ2”的部分，得到∠AOB的cos值
        double cos = Math.cos(radiansAY) * Math.cos(radiansBY) * Math.cos(radiansAX - radiansBX)
                + Math.sin(radiansAY) * Math.sin(radiansBY);
//        System.out.println("cos = " + cos); // 值域[-1,1]
        double acos = Math.acos(cos); // 反余弦值
//        System.out.println("acos = " + acos); // 值域[0,π]
//        System.out.println("∠AOB = " + Math.toDegrees(acos)); // 球心角 值域[0,180]
        return 6371393 * acos;

    }

}
