package com.arcsoft.arcfacedemo.films;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arcsoft.arcfacedemo.R;
import com.arcsoft.arcfacedemo.databinding.FragmentFilmsCinemaBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class FilmsCinemaFragment extends Fragment {

    private FragmentFilmsCinemaBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_films_cinema, container, false);
        View view = binding.getRoot();
        return view;
    }
}
