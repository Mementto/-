package com.arcsoft.arcfacedemo.mine;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arcsoft.arcfacedemo.R;
import com.arcsoft.arcfacedemo.databinding.FragmentMineBinding;
import com.arcsoft.arcfacedemo.face.FaceDialog;
import com.arcsoft.arcfacedemo.face.IsFaceRegisterActivity;
import com.arcsoft.arcfacedemo.login.LoginActivity;
import com.arcsoft.arcfacedemo.utils.Data;
import com.arcsoft.arcfacedemo.utils.Storage;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class MineFragment extends Fragment {

    private FragmentMineBinding binding;
    private MineViewModel viewModel;
    private static final int NOW_FRAGMENT_ID = 4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_mine, container, false);
        viewModel = ViewModelProviders.of(requireActivity()).get(MineViewModel.class);
        binding.setViewModel(viewModel);
        isLogin();
        eventListen();
        View view = binding.getRoot();
        return view;
    }

    private void isLogin() {
        Long userId = Storage.getUserId(getContext());
        Log.e("id", Storage.getUserId(getContext()) + "");
        if (userId != null && userId > 0) {
            viewModel.getIsLogin().setValue(true);
            binding.userType.setText(Storage.getUserType(getContext()) == Data.NORMAL_USER_CODE ? Data.NORMAL_USER : Data.IMPORTANT_USER);
            binding.userName.setText(Storage.getUsername(getContext()));
        } else {
            viewModel.getIsLogin().setValue(false);
            binding.userType.setText(Data.NOT_LOGIN);
        }
    }

    private void eventListen() {
        viewModel.getGotoLogin().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("fragment_id", NOW_FRAGMENT_ID);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        viewModel.getGotoFace().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Intent intent = new Intent(getActivity(), IsFaceRegisterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("fragment_id", NOW_FRAGMENT_ID);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

}
