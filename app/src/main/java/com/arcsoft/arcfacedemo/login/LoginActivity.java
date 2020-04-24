package com.arcsoft.arcfacedemo.login;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.arcsoft.arcfacedemo.R;
import com.arcsoft.arcfacedemo.databinding.ActivityLoginBinding;
import com.arcsoft.arcfacedemo.utils.Data;
import com.arcsoft.arcfacedemo.utils.Storage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding.setViewModel(viewModel);
        eventListen();
    }

    private void eventListen() {
        viewModel.getResult().observe(this, new Observer<Long>() {
            @Override
            public void onChanged(Long aLong) {
                if (aLong.intValue() == Data.USERNAME_OR_PASSWORD_ERROR) {
                    setToast(Data.USERNAME_OR_PASSWORD_ERROR_INFO);
                } else if (aLong.intValue() == Data.INTERNET_ERROR_CODE) {
                    setToast(Data.INTERNET_ERROR_INFO);
                } else{
                    loginSuccess(aLong);
                }
            }
        });
    }

    private void loginSuccess(Long aLong) {
        Storage.setUserId(this, aLong);
        Log.e("结果", aLong + "");
    }

    private void setToast(String info) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
    }

}
