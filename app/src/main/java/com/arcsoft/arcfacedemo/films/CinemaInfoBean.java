package com.arcsoft.arcfacedemo.films;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

public class CinemaInfoBean extends BaseObservable {

    private String name;
    private String length;
    private String address;
    private String tag1;
    private String tag2;

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(BR.cinema_info);
    }

    @Bindable
    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
        notifyPropertyChanged(BR.cinema_info);
    }

    @Bindable
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
        notifyPropertyChanged(BR.cinema_info);
    }

    @Bindable
    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
        notifyPropertyChanged(BR.cinema_info);
    }

    @Bindable
    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
        notifyPropertyChanged(BR.cinema_info);
    }
}
