package com.nearsoft.sofe.map.view;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MedPagerAdapter extends FragmentPagerAdapter {

    public MedPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return ServicesFragment.newInstance();
            case 1:
                return MapFragment.newInstance();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Servicios";
            case 1:
                return "Ubicación";
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
