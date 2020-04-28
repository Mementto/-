package com.arcsoft.arcfacedemo.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.arcsoft.arcfacedemo.R;
import com.arcsoft.arcfacedemo.databinding.ActivityRegisterBinding;
import com.arcsoft.arcfacedemo.main.MainActivity;
import com.arcsoft.arcfacedemo.repository.UserBean;
import com.arcsoft.arcfacedemo.utils.Data;
import com.arcsoft.arcfacedemo.utils.LayoutUtil;
import com.arcsoft.arcfacedemo.utils.StorageUser;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

/**
 * 注册Activity
 *
 * @author mementto
 * @date 2020/4/23
 */
public class RegisterActivity extends AppCompatActivity {

    private ActivityRegisterBinding binding;
    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutUtil.setWhiteStatusBar(getWindow());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        binding.setViewModel(viewModel);
        eventListen();
    }

    private void eventListen() {
        binding.registerInputUsername.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    viewModel.checkUsername();
                }
            }
        });
        binding.registerInputPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (viewModel.checkPassword() == Data.PASSWORD_DATA_ERROR_FORMAT) {
                        binding.doublePasswordError.setVisibility(View.INVISIBLE);
                        binding.passwordFormatError.setVisibility(View.VISIBLE);
                    } else {
                        binding.doublePasswordError.setVisibility(View.INVISIBLE);
                        binding.passwordFormatError.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        binding.registerInputPasswordAgain.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    if (viewModel.checkPassword() == Data.DOUBLE_PASSWORD_DATA_ERROR) {
                        binding.passwordFormatError.setVisibility(View.INVISIBLE);
                        binding.doublePasswordError.setVisibility(View.VISIBLE);
                    } else {
                        binding.doublePasswordError.setVisibility(View.INVISIBLE);
                        binding.passwordFormatError.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
        binding.getViewModel().getUsernameResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer) {
                    case Data.USERNAME_DATA_ERROR_EXIT:
                        binding.usernameFormatError.setVisibility(View.INVISIBLE);
                        binding.usernameHasBeenExited.setVisibility(View.VISIBLE);
                        break;
                    case Data.USERNAME_DATA_ERROR_FORMAT:
                        binding.usernameHasBeenExited.setVisibility(View.INVISIBLE);
                        binding.usernameFormatError.setVisibility(View.VISIBLE);
                        break;
                    case Data.INTERNET_ERROR_CODE:
                        setToast(Data.INTERNET_ERROR_INFO);
                    default:
                        binding.usernameFormatError.setVisibility(View.INVISIBLE);
                        binding.usernameHasBeenExited.setVisibility(View.INVISIBLE);
                }
            }
        });
        binding.getViewModel().getPasswordResult().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                switch (integer) {
                    case Data.PASSWORD_DATA_ERROR_FORMAT:
                        binding.doublePasswordError.setVisibility(View.GONE);
                        binding.passwordFormatError.setVisibility(View.VISIBLE);
                        break;
                    case Data.DOUBLE_PASSWORD_DATA_ERROR:
                        binding.passwordFormatError.setVisibility(View.GONE);
                        binding.doublePasswordError.setVisibility(View.VISIBLE);
                        break;
                    case Data.INTERNET_ERROR_CODE:
                        setToast(Data.INTERNET_ERROR_INFO);
                    default:
                        binding.passwordFormatError.setVisibility(View.GONE);
                        binding.doublePasswordError.setVisibility(View.GONE);
                }
            }
        });
        binding.getViewModel().getRegisterResult().observe(this, new Observer<UserBean>() {
            @Override
            public void onChanged(UserBean userBean) {
                registerSuccess(userBean);
            }
        });

        viewModel.getReturnTo().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                finish();
            }
        });
    }

    public void setToast(String info) {
        Toast.makeText(this, info, Toast.LENGTH_SHORT).show();
    }

    public void registerSuccess(UserBean userBean) {
        StorageUser.setUserInfo(this, userBean);
        Bundle bundle = this.getIntent().getExtras();
        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

}
