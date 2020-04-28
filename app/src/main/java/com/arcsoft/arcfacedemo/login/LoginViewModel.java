package com.arcsoft.arcfacedemo.login;

import android.view.View;

import com.arcsoft.arcfacedemo.repository.UserBean;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {

    private String username;
    private String password;
    private LoginModel model;
    private MutableLiveData<String> fail;
    private MutableLiveData<UserBean> result;
    private MutableLiveData<Boolean> register;
    private MutableLiveData<Boolean> returnTo;

    public LoginViewModel() {
        model = new LoginModel(this);
    }

    public void submitInfo(View view) {
        model.submitInfo(username, password);
    }

    public void loginResult(UserBean userBean) {
        this.getResult().setValue(userBean);
    }

    public void loginResultFail(String fail) {
        this.fail.setValue(fail);
    }

    public void gotoRegister(View view) {
        if (getRegister().getValue() == null) {
            getRegister().setValue(false);
        } else {
            getRegister().setValue(! getRegister().getValue());
        }
    }

    public void returnTo(View view) {
        if (getReturnTo().getValue() == null) {
            getReturnTo().setValue(false);
        } else {
            getReturnTo().setValue(! getReturnTo().getValue());
        }
    }

    public MutableLiveData<Boolean> getReturnTo() {
        if (returnTo == null) {
            returnTo = new MutableLiveData<>();
        }
        return returnTo;
    }

    public MutableLiveData<String> getFail() {
        if (fail == null) {
            fail = new MutableLiveData<>();
        }
        return fail;
    }

    public MutableLiveData<UserBean> getResult() {
        if (result == null) {
            result = new MutableLiveData<>();
        }
        return result;
    }

    public MutableLiveData<Boolean> getRegister() {
        if (register == null) {
            register = new MutableLiveData<>();
        }
        return register;
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
