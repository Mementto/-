package com.arcsoft.arcfacedemo.films;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FilmsViewModel extends ViewModel {

    private MutableLiveData<String> cityName;

    private MutableLiveData<Boolean> gotoCity;

    public void onClickCity(View view) {
        this.getGotoCity().setValue(true);
    }

    public MutableLiveData<Boolean> getGotoCity() {
        if (gotoCity == null) {
            gotoCity = new MutableLiveData<>();
        }
        return gotoCity;
    }

    public MutableLiveData<String> getCityName() {
        if (cityName == null) {
            cityName = new MutableLiveData<>();
        }
        String name = cityName.getValue();
        return cityName;
    }
}
