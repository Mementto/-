package com.arcsoft.arcfacedemo.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.arcsoft.arcfacedemo.R;
import com.arcsoft.arcfacedemo.databinding.ActivityLoginBinding;
import com.arcsoft.arcfacedemo.main.MainActivity;
import com.arcsoft.arcfacedemo.register.RegisterActivity;
import com.arcsoft.arcfacedemo.repository.UserBean;
import com.arcsoft.arcfacedemo.utils.Data;
import com.arcsoft.arcfacedemo.utils.LayoutUtil;
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
        LayoutUtil.setWhiteStatusBar(getWindow());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        binding.setViewModel(viewModel);
        eventListen();
    }

    private void eventListen() {
        viewModel.getResult().observe(this, new Observer<UserBean>() {
            @Override
            public void onChanged(UserBean userBean) {
                if (userBean.getUserId().intValue() == Data.USERNAME_OR_PASSWORD_ERROR) {
                    setToast(Data.USERNAME_OR_PASSWORD_ERROR_INFO);
                } else{
                    loginSuccess(userBean);
                }
            }
        });

        viewModel.getFail().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                setToast(s);
            }
        });

        viewModel.getRegister().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                Bundle bundle = getIntent().getExtras();
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        viewModel.getReturnTo().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                finish();
            }
        });
    }

    private void loginSuccess(UserBean userBean) {
        Storage.setUserInfo(this, userBean);
        Log.e("结果", userBean.getUserId() + "");
        Bundle bundle = this.getIntent().getExtras();

        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    private void setToast(String info) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
    }

}
