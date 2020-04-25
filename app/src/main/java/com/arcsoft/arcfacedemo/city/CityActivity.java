package com.arcsoft.arcfacedemo.city;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.arcsoft.arcfacedemo.R;
import com.arcsoft.arcfacedemo.databinding.ActivityCitiesBinding;
import com.arcsoft.arcfacedemo.location.MyLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.util.Collections;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

public class CityActivity extends AppCompatActivity {

    private ActivityCitiesBinding binding;

    private List<CityData> cityData;
    private CharacterParser characterParser;
    private PinyinComparator pinyinComparator;
    private BeanAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cities);
        binding.cityRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        initData();
        initRecyclerView();
    }

    private void initData() {
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        getCity();
    }

    private void getCity() {
        Bundle bundle = getIntent().getExtras();
        String cityName = bundle.getString("city_name");

        GetCityList getCityList = new GetCityList(characterParser);
        cityData = getCityList.filledData(getResources().getStringArray(R.array.date));
        Collections.sort(cityData, pinyinComparator);
        cityData.get(0).setNowName(cityName);
    }

    private void initRecyclerView() {
        adapter = new BeanAdapter(this, cityData);
        binding.cityRecyclerView.setAdapter(adapter);
    }
}
