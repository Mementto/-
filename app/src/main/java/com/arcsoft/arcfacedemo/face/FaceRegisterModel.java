package com.arcsoft.arcfacedemo.face;

import android.net.Uri;
import android.util.Log;

import com.arcsoft.arcfacedemo.api.CallApi;
import com.arcsoft.arcfacedemo.utils.Data;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FaceRegisterModel {

    private FaceRegisterViewModel viewModel;

    public FaceRegisterModel(FaceRegisterViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void submitFace(String path, Long userId) {
        File file = new File(path);
        RequestBody imgBody = RequestBody.create(MediaType.parse("image/jpg"), file);
        MultipartBody.Part imgPart = MultipartBody.Part.createFormData("img", file.getName(), imgBody);
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl("http://192.168.1.103:80/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CallApi api = retrofit.create(CallApi.class);
        Call<Integer> call = api.uploadImage(imgPart, userId);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                viewModel.submitResult(response.body());
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {
                viewModel.submitResult(Data.INTERNET_ERROR_CODE);
            }
        });
    }
}
