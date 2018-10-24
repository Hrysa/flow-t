package com.eevoe.flow;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

abstract public class FlowFragment extends Fragment {

    RelativeLayout mLayout;

    int mNavBarHeight;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mLayout = (RelativeLayout) inflater.inflate(R.layout.fragment_main, container, false);
        View view = onCreateView(inflater);
        if (view != null) {
            ((LinearLayout) mLayout.findViewById(R.id.fragment_container)).addView(view);
        }
        return mLayout;
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


    abstract public View onCreateView(@NonNull LayoutInflater inflater);
}
