package com.arcsoft.arcfacedemo.mine;

import android.view.View;

import java.util.IllegalFormatCodePointException;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MineViewModel extends ViewModel {

    private MutableLiveData<Boolean> isLogin;
    private MutableLiveData<Boolean> gotoLogin;
    private MutableLiveData<Boolean> gotoFace;

    public void onClickNotice(View view) {
        if (! isLogin.getValue()) {
            isNotLogin();
        } else {

        }
    }

    public void onClickInfo(View view) {
        if (! isLogin.getValue()) {
            isNotLogin();
        } else {

        }
    }

    public void onClickOrder(View view) {
        if (! isLogin.getValue()) {
            isNotLogin();
        } else {

        }
    }

    public void onClickWallet(View view) {
        if (! isLogin.getValue()) {
            isNotLogin();
        } else {

        }
    }

    public void onClickFace(View view) {
        if (! isLogin.getValue()) {
            isNotLogin();
        } else {
            if (getGotoFace().getValue() == null) {
                getGotoFace().setValue(false);
            } else {
                getGotoFace().setValue(! getGotoFace().getValue());
            }
        }
    }

    public void onClickList(View view) {
        if (! isLogin.getValue()) {
            isNotLogin();
        } else {

        }
    }

    public void onClickImportance(View view) {
        if (! isLogin.getValue()) {
            isNotLogin();
        } else {

        }
    }

    public void onClickService(View view) {

    }

    public void onClickSet(View view) {

    }

    public void isNotLogin() {
        if (gotoLogin.getValue() == null) {
            gotoLogin.setValue(false);
        } else {
            gotoLogin.setValue(!getGotoLogin().getValue());
        }

    }

    public MutableLiveData<Boolean> getIsLogin() {
        if (isLogin == null) {
            isLogin = new MutableLiveData<>();
        }
        return isLogin;
    }

    public MutableLiveData<Boolean> getGotoLogin() {
        if (gotoLogin == null) {
            gotoLogin = new MutableLiveData<>();
        }
        return gotoLogin;
    }

    public MutableLiveData<Boolean> getGotoFace() {
        if (gotoFace == null) {
            gotoFace = new MutableLiveData<>();
        }
        return gotoFace;
    }
}
