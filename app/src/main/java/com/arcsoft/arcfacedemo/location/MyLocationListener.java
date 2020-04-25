package com.arcsoft.arcfacedemo.location;

import com.arcsoft.arcfacedemo.films.FilmsViewModel;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;

public class MyLocationListener extends BDAbstractLocationListener {

    private FilmsViewModel viewModel;

    public MyLocationListener(FilmsViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public MyLocationListener() {}

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
        cityName2Films(city);
    }

    private void cityName2Films(String city) {
        viewModel.getCityName().setValue(city);
    }
}
