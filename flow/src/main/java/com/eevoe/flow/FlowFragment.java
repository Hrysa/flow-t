package com.eevoe.flow;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.eevoe.flow.vm.NavBarVModel;

import com.eevoe.flow.databinding.FragmentMainBinding;

import java.lang.reflect.Field;

abstract public class FlowFragment extends Fragment {
    private static final String TAG = FlowFragment.class.getSimpleName();

    RelativeLayout mLayout;

    NavBarVModel mNavBarVModel;

    FragmentMainBinding mFragmentMainBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mNavBarVModel == null) {

            // init annotation filed.
            initAnnotation(this);

            // init nav bar ViewModel.
            mNavBarVModel = new NavBarVModel(getActivity().getApplication());
            mNavBarVModel.init(this);

            // inflate view.
            mFragmentMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
            mFragmentMainBinding.navBar.setNavBarVModel(mNavBarVModel);

            mLayout = (RelativeLayout) mFragmentMainBinding.getRoot();
            mNavBarVModel.initView(mLayout);

            View view = onCreateView(inflater);
            view.setClickable(true);
            if (view != null) {
                ((FrameLayout) mLayout.findViewById(R.id.fragment_container)).addView(view);
            }
        }

        /**
         * TODO not working right now.
         * if create with replace, call update function in FlowActivity.
         */
        ((FlowActivity) getActivity()).updateReplaceStatus();
        return mLayout;
    }

    protected NavBarVModel getNavBarVModel() {
        return mNavBarVModel;
    }

    public void push(Fragment f) {
        ((FlowActivity) getActivity()).push(f);

    }

    private void initAnnotation(Object target) {
        Class<?> clazz = target.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                com.eevoe.flow.annotation.FlowState annotation = field.getAnnotation(com.eevoe.flow.annotation.FlowState.class);
                if (annotation != null) {
                    field.set(this, getState().get((Class)field.getType()));
                }
            } catch (IllegalAccessException ex) {

            }
        }
    }

    public void replace(Fragment f) {
        ((FlowActivity) getActivity()).replace(f);
    }

    public FlowState getState() {
        return ((FlowActivity) getActivity()).getState();
    }

    abstract public View onCreateView(@NonNull LayoutInflater inflater);
}
