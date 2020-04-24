package com.arcsoft.arcfacedemo.login;

import android.util.Log;

import com.arcsoft.arcfacedemo.api.CallApi;
import com.arcsoft.arcfacedemo.repository.UserBean;
import com.arcsoft.arcfacedemo.utils.Data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginModel {

    private LoginViewModel viewModel;

    public LoginModel(LoginViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void submitInfo(String username, String password) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Data.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CallApi api = retrofit.create(CallApi.class);
        Call<UserBean> call = api.login(username, password);
        call.enqueue(new Callback<UserBean>() {
            @Override
            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                viewModel.loginResult(response.body());
            }

            @Override
            public void onFailure(Call<UserBean> call, Throwable t) {
                viewModel.loginResultFail(Data.INTERNET_ERROR_INFO);
            }
        });
    }

}
