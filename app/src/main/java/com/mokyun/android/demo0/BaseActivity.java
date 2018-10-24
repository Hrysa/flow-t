package com.mokyun.android.demo0;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

abstract public class BaseActivity<M extends ViewModel, B extends ViewDataBinding> extends AppCompatActivity {

    private M mViewModel;
    private B mBinding;
    private LinearLayout containerLayout;

    @Override
    final protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // hide action bar
        getSupportActionBar().hide();

        // init ui theme
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        initViewDataBinding();

        containerLayout = getContainerLayout();

        addView(onCreateView());
    }

    abstract protected <T extends View> T onCreateView();


    private void initViewDataBinding() {

            Class modelClass;
            Type type = getClass().getGenericSuperclass();
            if (type instanceof ParameterizedType) {
                modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
            } else {
                // use default class if not specified.
                modelClass = ViewModel.class;
            }
        mViewModel = (M) createViewModel(this, modelClass);

        mBinding = DataBindingUtil.setContentView(this, initView());
        mBinding.setVariable(BR.viewModel, mViewModel);
    }

    // add view to container view.
    protected void addView(View view) {
        containerLayout.addView(view);
    }


    // init activity main view.
    protected int initView() {
        return R.layout.activity_main;
    }

    private <T extends View> T getContainerLayout() {
        return findViewById(R.id.container);
    }

    public <T extends ViewModel> T createViewModel(FragmentActivity activity, Class<T> cls) {
        return ViewModelProviders.of(activity).get(cls);
    }
}
