package com.arcsoft.arcfacedemo.city;

import com.arcsoft.arcfacedemo.BR;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class CityData extends BaseObservable {

    @Bindable
    private String nowName;
    private String name;
    private String firstA;

    public String getNowName() {
        return nowName;
    }

    public void setNowName(String nowName) {
        this.nowName = nowName;
        notifyPropertyChanged(BR.nowName);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFirstA() {
        return firstA;
    }
    public void setFirstA(String firstA) {
        this.firstA = firstA;
    }

}
