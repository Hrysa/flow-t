package com.eevoe.flow.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;


abstract public class FlowFragmentPagerAdapter extends FragmentPagerAdapter {

    FragmentManager mFm;

    public FlowFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        mFm = fm;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        Fragment f = (Fragment) super.instantiateItem(container, position);
        String fragmentTag = f.getTag();

        if (f != getItem(position)) {
            FragmentTransaction ft = mFm.beginTransaction();
            ft.remove(f);
            f =getItem(position);
            ft.add(container.getId(), f, fragmentTag);
            ft.attach(f);
            ft.commitAllowingStateLoss();
        }
        return f;
    }
}
