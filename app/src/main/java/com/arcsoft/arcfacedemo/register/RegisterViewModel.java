package com.arcsoft.arcfacedemo.register;

import android.view.View;

import com.arcsoft.arcfacedemo.utils.Data;
import com.arcsoft.arcfacedemo.utils.Storage;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

/**
 * 注册Activity
 *
 * @author mementto
 * @date 2020/4/23
 */
public class RegisterViewModel extends ViewModel {

    private String usernameData;
    private String passwordData;
    private String passwordDataAgain;
    private MutableLiveData<Integer> usernameResult;
    private MutableLiveData<Integer> passwordResult;
    private MutableLiveData<Long> registerResult;
    private RegisterModel model;


    public RegisterViewModel() {
        model = new RegisterModel(this);
    }

    public void checkUsername() {
        model.submitUsername(usernameData);
    }
    public void submitUsernameResult(int result) {
        this.usernameResult.setValue(result);
    }

    public int checkPassword() {
        if (passwordData == null || (passwordData.length() > 16 || passwordData.length() < 6)) {
            return Data.PASSWORD_DATA_ERROR_FORMAT;
        } else if (! passwordData.equals(passwordDataAgain)) {
            return Data.DOUBLE_PASSWORD_DATA_ERROR;
        } else {
            return Data.INFO_DATA_TRUE;
        }
    }

    public void submitInfo(View view) {
        model.submitRegisterInfo(usernameData, passwordData, passwordDataAgain);
    }

    public void submitInfoPasswordError(int result) {
        this.passwordResult.setValue(result);
    }

    public void registerSuccess(Long result) {
        this.registerResult.setValue(result);
    }

    public MutableLiveData<Long> getRegisterResult() {
        if (registerResult == null) {
            registerResult = new MutableLiveData<>();
        }
        return registerResult;
    }

    public MutableLiveData<Integer> getUsernameResult() {
        if (usernameResult == null) {
            usernameResult = new MutableLiveData<>();
        }
        return usernameResult;
    }

    public MutableLiveData<Integer> getPasswordResult() {
        if (passwordResult == null) {
            passwordResult = new MutableLiveData<>();
        }
        return passwordResult;
    }

    public String getUsernameData() {
        return usernameData;
    }

    public void setUsernameData(String usernameData) {
        this.usernameData = usernameData;
    }

    public String getPasswordData() {
        return passwordData;
    }

    public void setPasswordData(String passwordData) {
        this.passwordData = passwordData;
    }

    public String getPasswordDataAgain() {
        return passwordDataAgain;
    }

    public void setPasswordDataAgain(String passwordDataAgain) {
        this.passwordDataAgain = passwordDataAgain;
    }
}
