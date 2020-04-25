package com.arcsoft.arcfacedemo.main;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;

import com.arcsoft.arcfacedemo.R;
import com.arcsoft.arcfacedemo.activities.ActivityFragment;
import com.arcsoft.arcfacedemo.databinding.ActivityMainBinding;
import com.arcsoft.arcfacedemo.face.FaceDialog;
import com.arcsoft.arcfacedemo.films.FilmsFragment;
import com.arcsoft.arcfacedemo.mine.MineFragment;
import com.arcsoft.arcfacedemo.store.StoreFragment;
import com.arcsoft.arcfacedemo.utils.Data;
import com.arcsoft.arcfacedemo.utils.LayoutUtil;
import com.arcsoft.arcfacedemo.utils.Storage;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProviders;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    private Fragment fragment;
    private FilmsFragment filmsFragment;
    private ActivityFragment activityFragment;
    private MineFragment mineFragment;
    private StoreFragment storeFragment;
    private FragmentTransaction fragmentTransaction;
    private boolean isLogin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutUtil.setWhiteStatusBar(getWindow());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        binding.setViewModel(viewModel);
        setFragment();
        initNavigationBar();
        submitUserId();
        eventListen();
        alert();
        Log.e("第一", Storage.getUserId(this) + "");
    }

    private void submitUserId() {

    }

    private void alert() {

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            if (Data.FACE_ENTER_FAIL == bundle.getInt("upload_face_result")) {
                final FaceDialog dialog = new FaceDialog(this, R.mipmap.icon_face_fail, Data.FACE_ENTER_FAIL_INFO);
                dialog.show();
                dialog.setOnClickListener(new FaceDialog.OnClickListener() {
                    @Override
                    public void onIKnowClick() {
                        dialog.dismiss();
                    }
                });
            }
            if (0 < bundle.getInt("upload_face_result")) {
                final FaceDialog dialog = new FaceDialog(this, R.mipmap.icon_face_success, Data.FACE_ENTER_SUCCESS_INFO);
                dialog.show();
                dialog.setOnClickListener(new FaceDialog.OnClickListener() {
                    @Override
                    public void onIKnowClick() {
                        dialog.dismiss();
                    }
                });
            }
        }

    }

    private void setFragment() {
        int id = 0;
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getInt("fragment_id");
            if (id != 0) {
                id --;
            }
        }
        filmsFragment = new FilmsFragment();
        activityFragment = new ActivityFragment();
        mineFragment = new MineFragment();
        storeFragment = new StoreFragment();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch(id) {
            case 0:
                fragmentTransaction.add(R.id.main_pages, filmsFragment).commit();
                fragment = filmsFragment;
                break;
            case 1:
                fragmentTransaction.add(R.id.main_pages, activityFragment).commit();
                fragment = activityFragment;
                break;
            case 2:
                fragmentTransaction.add(R.id.main_pages, storeFragment).commit();
                fragment = storeFragment;
                break;
            case 3:
                fragmentTransaction.add(R.id.main_pages, mineFragment).commit();
                fragment = mineFragment;
        }
    }

    private void eventListen() {
        binding.mainNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                switch (position) {
                    case 0:
                        switchFragment(filmsFragment);
                        break;
                    case 1:
                        switchFragment(activityFragment);
                        break;
                    case 2:
                        switchFragment(storeFragment);
                        break;
                    case 3:
                        switchFragment(mineFragment);
                        break;
                }
            }

            @Override
            public void onTabUnselected(int position) {}

            @Override
            public void onTabReselected(int position) {}
        });
    }

    private void switchFragment(Fragment fragment) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (this.fragment != fragment) {
            if (! fragment.isAdded()) {
                fragmentTransaction.hide(this.fragment).add(R.id.main_pages, fragment).commit();
            } else {
                fragmentTransaction.hide(this.fragment).show(fragment).commit();
            }
            this.fragment = fragment;
        }
    }

    private void initNavigationBar() {
        int id = 0;
        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null) {
            id = bundle.getInt("fragment_id");
            if (id != 0) {
                id --;
            }
        }


        binding.mainNavigationBar
                .setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC);

        binding.mainNavigationBar
                .setActiveColor(R.color.color_main)
                .setInActiveColor(R.color.color_light_gray)
                .setBarBackgroundColor(R.color.color_white);

        binding.mainNavigationBar
                .addItem(new BottomNavigationItem(R.mipmap.icon_film_select, R.string.main_films)
                        .setInactiveIcon(ContextCompat.getDrawable(MainActivity.this, R.mipmap.icon_film_unselect)))
                .addItem(new BottomNavigationItem(R.mipmap.icon_activity_select, R.string.main_activity)
                        .setInactiveIcon(ContextCompat.getDrawable(MainActivity.this, R.mipmap.icon_activity_unselect)))
                .addItem(new BottomNavigationItem(R.mipmap.icon_store_select, R.string.main_store)
                        .setInactiveIcon(ContextCompat.getDrawable(MainActivity.this, R.mipmap.icon_store_unselect)))
                .addItem(new BottomNavigationItem(R.mipmap.icon_mine_select, R.string.main_mine)
                        .setInactiveIcon(ContextCompat.getDrawable(MainActivity.this, R.mipmap.icon_mine_unselect)))
                .setFirstSelectedPosition(id)
                .initialise();


    }


}
