package com.mokyun.android.demo0.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

public class MainViewModel extends AndroidViewModel {
    public ObservableField<String> userName = new ObservableField<>("edit");

    public ObservableField<String> backgroundColor = new ObservableField<>("#ffffff");

    public MainViewModel(@NonNull Application application) {
        super(application);
    }

    public void setUserName(String s) {
        userName.set(s);
    }
}
