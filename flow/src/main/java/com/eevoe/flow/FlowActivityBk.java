package com.eevoe.flow;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

abstract public class FlowActivityBk<M extends ViewModel, B extends ViewDataBinding> extends AppCompatActivity {

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

        // TODO
//        initViewDataBinding();

        containerLayout = getContainerLayout();

        addView(onCreateView());
    }

    abstract protected <T extends View> T onCreateView();


    // TODO
    private void initViewDataBinding() {
//
//        Class modelClass;
//        Type type = getClass().getGenericSuperclass();
//        if (type instanceof ParameterizedType) {
//            modelClass = (Class) ((ParameterizedType) type).getActualTypeArguments()[0];
//        } else {
//            // use default class if not specified.
//            modelClass = ViewModel.class;
//        }
//        mViewModel = (M) createViewModel(this, modelClass);
//
//        mBinding = DataBindingUtil.setContentView(this, initView());
//        mBinding.setVariable(BR.viewModel, mViewModel);
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
        return findViewById(R.id.activity_container);
    }

    public <T extends ViewModel> T createViewModel(FragmentActivity activity, Class<T> cls) {
        return ViewModelProviders.of(activity).get(cls);
    }
}

//        FlowFragment fragment;
//        fragment =

//        if (sp == null || sp.getString("login", NOT_LOGIN).equals(NOT_LOGIN)) {
//            fragment = new LoginFragment();
//        } else {
////            //如果记住密码并且勾选自动登录,则跳转home页,否则跳转login页
////            if (loginData.getString(IS_REM,"0").equals(CHECKED)
////                    && loginData.getString(IS_AUTO,"0").equals(CHECKED)){
////                fragment = new HomeFragment();
////            }else{
////                fragment = new LoginFragment();
////            }
//            fragment = new HomeFragment();
//        }
//
//        QMUIStatusBarHelper.translucent(this);
//
//        //status值未登陆状态,如果status值未1就跳转到home页,如果为0,就跳转到login页
//        if(loginData.getString("status","0").equals("1")
//                && list.size()>0){
//            fragment = new HomeFragment();
//        }
//        getSupportFragmentManager()
//                .beginTransaction()
//                .add(R.id.demo, fragment, fragment.getClass().getSimpleName())
//                .addToBackStack(fragment.getClass().getSimpleName())
//                .commit();
