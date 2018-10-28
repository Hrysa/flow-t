package com.eevoe.flow;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.eevoe.flow.vm.NavBarVModel;

import com.eevoe.flow.databinding.NavBarBinding;
import com.eevoe.flow.databinding.FragmentMainBinding;
import com.eevoe.flow.databinding.ActivityMainBinding;

abstract public class FlowFragment extends Fragment {

    RelativeLayout mLayout;

    int mNavBarHeight;

    NavBarVModel mNavBarVModel;

    FragmentMainBinding mFragmentMainBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Log.i("Fragment", "popBackStack: getSupportFragmentManager().getBackStackEntryCount() = " + getActivity().getSupportFragmentManager().getBackStackEntryCount());

        // init nav bar ViewModel.
        mNavBarVModel = new NavBarVModel(getActivity().getApplication());
        mNavBarVModel.init(getActivity());

        // inflate view.
        mFragmentMainBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        mFragmentMainBinding.navBar.setNavBarVModel(mNavBarVModel);

        mLayout = (RelativeLayout) mFragmentMainBinding.getRoot();
        View view = onCreateView(inflater);
        view.setClickable(true);
        if (view != null) {
            ((FrameLayout) mLayout.findViewById(R.id.fragment_container)).addView(view);
        }

        /**
         * if create with replace, call update function in FlowActivity.
         */
        ((FlowActivity) getActivity()).updateReplaceStatus();
        return mLayout;
    }

    protected NavBarVModel getNavBarVModel() {
        return mNavBarVModel;
    }


    public void hideNav() {
        View nav = mLayout.findViewById(R.id.nav_bar);
        ViewGroup.LayoutParams lp = nav.getLayoutParams();
        if (lp.height > 0) {
            mNavBarHeight = lp.height;
            lp.height = 0;
            nav.setLayoutParams(lp);
        }
    }


    public void showNav() {
        View nav = mLayout.findViewById(R.id.nav_bar);
        ViewGroup.LayoutParams lp = nav.getLayoutParams();
        if (mNavBarHeight > 0) {
            lp.height = mNavBarHeight;
            nav.setLayoutParams(lp);
        }
    }

    public void push(Fragment f) {
        ((FlowActivity) getActivity()).push(f);

    }

    public void replace(Fragment f) {
        ((FlowActivity) getActivity()).replace(f);
    }

    public FlowState getState() {
        return ((FlowActivity) getActivity()).getState();
    }

    abstract public View onCreateView(@NonNull LayoutInflater inflater);
}
