package com.eevoe.flow;

import android.content.res.Resources;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

    private boolean mCalled;

    private int mEnterAnimationStatus;

    public static final int ANIMATION_ENTER_STATUS_NOT_START = -1;
    public static final int ANIMATION_ENTER_STATUS_STARTED = 0;
    public static final int ANIMATION_ENTER_STATUS_END = 1;

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

        onResumeFlow();

        /**
         * TODO not working right now.
         * if create with replace, call update function in FlowActivity.
         */
        ((FlowActivity) getActivity()).updateReplaceStatus();
        return mLayout;
    }

    public void onResumeFlow(){}

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

    public void push(FlowFragment f) {
        ((FlowActivity) getActivity()).push(f);

    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
//        return super.onCreateAnimation(transit, enter, nextAnim);
        if (!enter && getParentFragment() != null && getParentFragment().isRemoving()) {
            // This is a workaround for the bug where child fragments disappear when
            // the parent is removed (as all children are first removed from the parent)
            // See https://code.google.com/p/android/issues/detail?id=55228
            Animation doNothingAnim = new AlphaAnimation(1, 1);
            doNothingAnim.setDuration(300);
            return doNothingAnim;
        }
        Animation animation = null;
        if (enter) {
            try {
                animation = AnimationUtils.loadAnimation(getContext(), nextAnim);

            } catch (Resources.NotFoundException ignored) {

            } catch (RuntimeException ignored) {

            }
            if (animation != null) {
                animation.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {
                        onEnterAnimationStart(animation);
                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        checkAndCallOnEnterAnimationEnd(animation);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            } else {
                onEnterAnimationStart(null);
                checkAndCallOnEnterAnimationEnd(null);
            }
        }
        return animation;

    }

    private void checkAndCallOnEnterAnimationEnd(@Nullable Animation animation) {
        mCalled = false;
        onEnterAnimationEnd(animation);
        if (!mCalled) {
            throw new RuntimeException("FlowFragment " + this + " did not call through to super.onEnterAnimationEnd(Animation)");
        }
    }

    protected void onEnterAnimationStart(@Nullable Animation animation) {
        mEnterAnimationStatus = ANIMATION_ENTER_STATUS_STARTED;
    }

    protected void onEnterAnimationEnd(@Nullable Animation animation) {
        if (mCalled) {
            throw new IllegalAccessError("don't call #onEnterAnimationEnd() directly");
        }
        mCalled = true;
        mEnterAnimationStatus = ANIMATION_ENTER_STATUS_END;
//        if (mDelayRenderRunnableList.size() > 0) {
//            for (int i = 0; i < mDelayRenderRunnableList.size(); i++) {
//                mDelayRenderRunnableList.get(i).run();
//            }
//            mDelayRenderRunnableList.clear();
//        }
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
                if (anno2.titleId() != 0) {
                    mNavBarVModel.title.set(getString(anno2.titleId()));
                } else {
                    mNavBarVModel.title.set(anno2.title());
                }
            }

        }
    }

    public void back() {
        if (mEnterAnimationStatus != ANIMATION_ENTER_STATUS_END) {
            return;
        }
        ((FlowActivity) getActivity()).back();
    }

    public void replace(FlowFragment f) {
        ((FlowActivity) getActivity()).replace(f);
    }

    public FlowState getState() {
        return ((FlowActivity) getActivity()).getState();
    }

    abstract public void initView(View view);
}
