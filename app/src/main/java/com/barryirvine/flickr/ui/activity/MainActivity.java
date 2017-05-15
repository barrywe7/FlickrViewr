package com.barryirvine.flickr.ui.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.barryirvine.flickr.R;
import com.barryirvine.flickr.databinding.ActivityMainBinding;
import com.barryirvine.flickr.ui.fragment.PhotoFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);
        if (savedInstanceState == null) {
            addFragment(R.id.fragment_layout, PhotoFragment.newInstance());
        }
    }

    public void addFragment(@IdRes final int containerViewId, final Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(containerViewId, fragment, fragment.getClass().getSimpleName()).commitNow();
    }

}