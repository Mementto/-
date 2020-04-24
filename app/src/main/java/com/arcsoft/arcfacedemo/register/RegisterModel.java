package com.arcsoft.arcfacedemo.register;

import com.arcsoft.arcfacedemo.api.CallApi;
import com.arcsoft.arcfacedemo.utils.Data;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 注册Activity
 *
 * @author mementto
 * @date 2020/4/23
 */
public class RegisterModel {

    private RegisterViewModel viewModel;

    public RegisterModel(RegisterViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void submitUsername(String usernameData) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Data.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CallApi api = retrofit.create(CallApi.class);
        Call<Integer> call = api.submitUsername(usernameData);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                viewModel.submitUsernameResult(response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                viewModel.submitUsernameResult(Data.INTERNET_ERROR_CODE);
            }
        });
    }

    public void submitRegisterInfo(String username, String password, String passwordAgain) {
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(Data.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CallApi api = retrofit.create(CallApi.class);
        Call<Long> call = api.register(username, password, passwordAgain);
        call.enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                if (response.body().intValue() == -3 || response.body().intValue() == -4) {
                    viewModel.submitInfoPasswordError(response.body().intValue());
                } else if (response.body() == -2 || response.body() == -1) {
                    viewModel.submitUsernameResult(response.body().intValue());
                } else {
                    viewModel.registerSuccess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {
                viewModel.submitUsernameResult(Data.INTERNET_ERROR_CODE);
            }
        });

    }
}
