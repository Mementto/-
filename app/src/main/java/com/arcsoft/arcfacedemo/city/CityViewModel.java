package com.arcsoft.arcfacedemo.city;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CityViewModel extends ViewModel {

    private MutableLiveData<String> cityName;

    public MutableLiveData<String> getCityName() {
        if (cityName == null) {
            cityName = new MutableLiveData<>();
        }
        return cityName;
    }
}
