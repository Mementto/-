package com.arcsoft.arcfacedemo.films;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.arcsoft.arcfacedemo.R;
import com.arcsoft.arcfacedemo.city.CityActivity;
import com.arcsoft.arcfacedemo.databinding.FragmentFilmsBinding;
import com.arcsoft.arcfacedemo.location.NowLocationListener;
import com.arcsoft.arcfacedemo.utils.StorageCity;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;

public class FilmsFragment extends Fragment {

    private FragmentFilmsBinding binding;
    private FilmsViewModel viewModel;

    private Fragment fragment;
    private FilmsFilmFragment filmFragment;
    private FilmsCinemaFragment cinemaFragment;
    private FragmentTransaction transaction;

    private LocationClient locationClient;
    private NowLocationListener nowLocationListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_films, container, false);
        viewModel = new FilmsViewModel();
        binding.setViewModel(viewModel);
        eventListen();
        setFragment();
        initRadios();
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        setCityName();
    }

    private void setCityName() {
        if (StorageCity.getCityName(getContext()) != null) {
            binding.filmsCityName.setText(StorageCity.getCityName(getContext()));
        } else {
            getCityName();
        }
    }

    private void eventListen() {
        viewModel.getCityName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.filmsCityName.setText(s);
                StorageCity.setCityName(getContext(), s);
                locationClient.stop();
            }
        });


        viewModel.getGotoCity().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Intent intent = new Intent(getActivity(), CityActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("city_name", binding.filmsCityName.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void setFragment() {
        filmFragment = new FilmsFilmFragment();
        cinemaFragment = new FilmsCinemaFragment();
        transaction = getChildFragmentManager().beginTransaction();

        transaction.add(R.id.film_and_cinema, filmFragment).commit();
        fragment = filmFragment;
    }

    private void initRadios() {
        binding.filmRadiosGroup.check(R.id.film_radio_left);
        binding.filmRadioLeft.setTextColor(getResources().getColor(R.color.color_white));
        binding.filmRadioRight.setTextColor(getResources().getColor(R.color.color_main));

        binding.filmRadiosGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.film_radio_left:
                        binding.filmRadioLeft.setTextColor(getResources().getColor(R.color.color_white));
                        binding.filmRadioRight.setTextColor(getResources().getColor(R.color.color_main));
                        switchFragment(filmFragment);
                        break;
                    case R.id.film_radio_right:
                        binding.filmRadioLeft.setTextColor(getResources().getColor(R.color.color_main));
                        binding.filmRadioRight.setTextColor(getResources().getColor(R.color.color_white));
                        switchFragment(cinemaFragment);
                }
            }
        });
    }

    private void switchFragment(Fragment fragment) {
        transaction = getChildFragmentManager().beginTransaction();
        if (this.fragment != fragment) {
            if (! fragment.isAdded()) {
                transaction.hide(this.fragment).add(R.id.film_and_cinema, fragment).commit();
            } else {
                transaction.hide(this.fragment).show(fragment).commit();
            }
            this.fragment = fragment;
        }
    }

    private void getCityName() {
        locationClient = new LocationClient(getContext());
        nowLocationListener = new NowLocationListener(viewModel, getContext());
        locationClient.registerLocationListener(nowLocationListener);
        LocationClientOption option = new LocationClientOption();
        option.setIsNeedAddress(true);
        option.setNeedNewVersionRgc(true);
        option.setOpenGps(true);
        locationClient.setLocOption(option);
        locationClient.start();
    }

}
