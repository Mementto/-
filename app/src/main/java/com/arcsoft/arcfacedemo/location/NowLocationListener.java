package com.arcsoft.arcfacedemo.location;

import android.content.Context;

import com.arcsoft.arcfacedemo.city.CityData;
import com.arcsoft.arcfacedemo.city.CityViewModel;
import com.arcsoft.arcfacedemo.films.FilmsViewModel;
import com.arcsoft.arcfacedemo.utils.StorageCity;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;

import java.util.List;

public class NowLocationListener extends BDAbstractLocationListener {

    private FilmsViewModel viewModel;
    private CityViewModel cityViewModel;
    private Context context;

    public NowLocationListener(FilmsViewModel viewModel, Context context) {
        this.viewModel = viewModel;
        this.context = context;
    }

    public NowLocationListener(CityViewModel cityViewModel, Context context) {
        this.cityViewModel = cityViewModel;
        this.context = context;
    }

    public NowLocationListener() {}

    @Override
    public void onReceiveLocation(BDLocation location) {
        String addr = location.getAddrStr();    //获取详细地址信息
        String country = location.getCountry();    //获取国家
        String province = location.getProvince();    //获取省份
        String city = location.getCity();    //获取城市
        String district = location.getDistrict();    //获取区县
        String street = location.getStreet();    //获取街道信息
        String adcode = location.getAdCode();    //获取adcode
        String town = location.getTown();    //获取乡镇信息
        int errorCode = location.getLocType();
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();

        StorageCity.setLatAndLong(context, latitude, longitude);

        cityName2Films(city);
    }

    private void cityName2Films(String city) {
        int i = city.indexOf("市");
        city = city.substring(0, i);
        if (viewModel != null) {
            viewModel.getCityName().setValue(city);
        } else {
            cityViewModel.getCityName().setValue(city);
        }
    }
}
