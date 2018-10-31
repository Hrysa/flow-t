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

import com.eevoe.flow.annotation.FlowBindView;
import com.eevoe.flow.vm.NavBarVModel;

import com.eevoe.flow.databinding.FragmentMainBinding;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

abstract public class FlowFragment extends Fragment {
    private static final String TAG = FlowFragment.class.getSimpleName();

    private RelativeLayout mLayout;

    private NavBarVModel mNavBarVModel;

    private FragmentMainBinding mFragmentMainBinding;

    private int mContentViewId = 0;

    private View mContentView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (mNavBarVModel == null) {

            // init nav bar ViewModel.
            initNavBarVModel();

            // inflate view and data binding.
            initDataBinding(inflater, container);

            // init annotation filed.
            initAnnotation(this);

            // create and init main content view.
            if (mContentViewId != 0) {
                createContentView(inflater);
                initView(mContentView);
            }

        }

        /**
         * TODO not working right now.
         * if create with replace, call update function in FlowActivity.
         */
        ((FlowActivity) getActivity()).updateReplaceStatus();
        return mLayout;
    }

    private void createContentView(LayoutInflater inflater) {
        mContentView = inflater.inflate(mContentViewId, mLayout, false);
        mContentView.setClickable(true);
        ((FrameLayout) mLayout.findViewById(R.id.fragment_container)).addView(mContentView);
    }

    private void initNavBarVModel() {
        mNavBarVModel = new NavBarVModel(getActivity().getApplication());
        mNavBarVModel.init(this);
    }

    private void initDataBinding(LayoutInflater inflater, @Nullable ViewGroup container) {
        mFragmentMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        mFragmentMainBinding.navBar.setNavBarVModel(mNavBarVModel);
        mLayout = (RelativeLayout) mFragmentMainBinding.getRoot();
    }

    protected NavBarVModel getNavBarVModel() {
        return mNavBarVModel;
    }

    protected View getContentView() {
        return mContentView;
    }

    public View getLayout() {
        return mLayout;
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
        Annotation[] annotations = clazz.getAnnotations();
        for (Annotation anno : annotations) {
            if (anno.annotationType() == FlowBindView.class) {
                FlowBindView anno2 = (FlowBindView) anno;
                mContentViewId = anno2.view();
                if (anno2.hideNav()) {
                    mNavBarVModel.setVisibility(View.GONE);
                }
                mNavBarVModel.title.set(anno2.navTitle());
            }

        }
    }

    public void replace(Fragment f) {
        ((FlowActivity) getActivity()).replace(f);
    }

    public FlowState getState() {
        return ((FlowActivity) getActivity()).getState();
    }

    abstract public void initView(View view);
}
