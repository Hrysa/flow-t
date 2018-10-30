package com.eevoe.flow.vm;

import android.app.Activity;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.content.res.ColorStateList;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.opengl.Visibility;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eevoe.flow.BR;
import com.eevoe.flow.FlowActivity;
import com.eevoe.flow.R;

public class NavBarVModel extends AndroidViewModel {
    final public ObservableField<Drawable> leftIcon = new ObservableField<>();
    final public ObservableField<Integer> leftIconVisibility = new ObservableField<>();
    final public ObservableField<Integer> rightIconVisibility = new ObservableField<>();
    final public ObservableField<Drawable> rightIcon = new ObservableField<>();
    final public ObservableField<String> title = new ObservableField<>();
    final public ObservableField<Integer> titleGravity = new ObservableField<>(Gravity.LEFT);


    public View.OnClickListener leftOnClick;
    public View.OnClickListener rightOnClick;

    Fragment mFragment;

    View mView;

    private int mNavBarHeight;
    private int mLeftInconWidth;
    private int mRightInconWidth;

    public void init(final Fragment fragment) {
        mFragment = fragment;
        leftIcon.set(mFragment.getResources().getDrawable(R.drawable.back));
        rightIcon.set(mFragment.getResources().getDrawable(R.drawable.more));
        leftOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFragment.getActivity().onBackPressed();
            }
        };
    }

    public NavBarVModel(@NonNull Application application) {
        super(application);
    }

    public void initView(final View view) {
        mView = view;
    }

    public void hideLeftIcon() {
        View view = mView.findViewById(R.id.nav_bar_left_icon);
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp.width > 0) {
            mLeftInconWidth = lp.width;
            lp.width = 0;
            view.setLayoutParams(lp);
        }
    }

    public void showLeftIcon() {
        View view = mView.findViewById(R.id.nav_bar_left_icon);
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (mLeftInconWidth > 0) {
            lp.height = mLeftInconWidth;
            view.setLayoutParams(lp);
        }
    }

    public void hideRightIcon() {
        View view = mView.findViewById(R.id.nav_bar_right_icon);
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp.width > 0) {
            mRightInconWidth = lp.width;
            lp.width = 0;
            view.setLayoutParams(lp);
        }
    }

    public void showRightIcon() {
        View view = mView.findViewById(R.id.nav_bar_right_icon);
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (mRightInconWidth > 0) {
            lp.height = mRightInconWidth;
            view.setLayoutParams(lp);
        }
    }
}