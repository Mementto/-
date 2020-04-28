package com.arcsoft.arcfacedemo.city;

import android.os.Bundle;
import android.view.View;

import com.arcsoft.arcfacedemo.R;
import com.arcsoft.arcfacedemo.databinding.ActivityCitiesBinding;
import com.arcsoft.arcfacedemo.location.NowLocationListener;
import com.arcsoft.arcfacedemo.utils.LayoutUtil;
import com.arcsoft.arcfacedemo.utils.StorageCity;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.util.Collections;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

public class CityActivity extends AppCompatActivity {

    private ActivityCitiesBinding binding;

    private List<CityData> cityData;
    private CharacterParser characterParser;
    private PinyinComparator pinyinComparator;
    private BeanAdapter adapter;

    private LocationClient locationClient;
    private NowLocationListener myLocationListener;
    private CityViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutUtil.setWhiteStatusBar(getWindow());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cities);
        viewModel = ViewModelProviders.of(this).get(CityViewModel.class);
        binding.setViewModel(viewModel);
        binding.cityRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        initData();
        initRecyclerView();
        initListener();
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

        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(ContextCompat.getDrawable(this, R.drawable.cinema_info_divider));
        binding.cityRecyclerView.addItemDecoration(divider);
    }

    private void initListener() {
        adapter.setOnItemClickListener(new BeanAdapter.OnItemClickListener() {
            @Override
            public void onCityName(View view, String name) {
                StorageCity.setCityName(getApplicationContext(), name);
                finish();
            }

            @Override
            public void refreshName(View view) {
                getCityName();
            }
        });

        binding.cityReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StorageCity.setCityName(getApplicationContext(), cityData.get(0).getNowName());
                finish();
            }
        });

        binding.citySearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        viewModel.getCityName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                cityData.get(0).setNowName(s);
                StorageCity.setCityName(getApplicationContext(), cityData.get(0).getNowName());
                locationClient.stop();
            }
        });
    }

    private void getCityName() {
        locationClient = new LocationClient(getApplicationContext());
        myLocationListener = new NowLocationListener(viewModel, getApplicationContext());
        locationClient.registerLocationListener(myLocationListener);
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        option.setNeedNewVersionRgc(true);
        option.setOpenGps(true);
        locationClient.setLocOption(option);
        locationClient.start();
    }

}
