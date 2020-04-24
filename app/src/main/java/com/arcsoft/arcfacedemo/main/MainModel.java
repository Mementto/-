package com.arcsoft.arcfacedemo.main;

import com.arcsoft.arcfacedemo.api.CallApi;
import com.arcsoft.arcfacedemo.utils.Data;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModel {

    private MainViewModel viewModel;

    public MainModel(MainViewModel viewModel) {
        this.viewModel = viewModel;
    }


}
