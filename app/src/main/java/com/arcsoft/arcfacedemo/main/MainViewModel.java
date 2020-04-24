package com.arcsoft.arcfacedemo.main;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {

    private MainModel model;

    public MainViewModel() {
        model = new MainModel(this);
    }
}
