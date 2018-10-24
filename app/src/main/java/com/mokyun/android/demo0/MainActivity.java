package com.mokyun.android.demo0;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.mokyun.android.demo0.viewmodel.MainViewModel;

import com.mokyun.android.demo0.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> {

    protected LinearLayout onCreateView() {
        return (LinearLayout) LayoutInflater.from(this).inflate(R.layout.nav_bar, null);
    }
}
