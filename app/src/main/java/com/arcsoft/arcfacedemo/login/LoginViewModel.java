package com.arcsoft.arcfacedemo.login;

import android.Manifest;
import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private String username;
    private String password;
    private LoginModel model;
    private MutableLiveData<Long> result;

    public LoginViewModel() {
        model = new LoginModel(this);
    }

    public void submitInfo(View view) {
        model.submitInfo(username, password);
    }

    public void loginResult(Long result) {
        this.getResult().setValue(result);
    }

    public MutableLiveData<Long> getResult() {
        if (result == null) {
            result = new MutableLiveData<>();
        }
        return result;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
