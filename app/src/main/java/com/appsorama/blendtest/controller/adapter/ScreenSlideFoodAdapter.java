package com.appsorama.blendtest.controller.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jose Torres in ${$COMPANY} on 4/20/16.
 */
public class ScreenSlideFoodAdapter extends FragmentPagerAdapter {
    private final List<Fragment> aCategoryFragment = new ArrayList<>();
    private final List<String> aCategoryTitle = new ArrayList<>();

    public ScreenSlideFoodAdapter(FragmentManager fm) {
        super(fm);
    }

    public void addFragment(Fragment fragment, String title) {
        aCategoryFragment.add(fragment);
        aCategoryTitle.add(title);
    }

    @Override
    public Fragment getItem(int position) {
        return aCategoryFragment.get(position);
    }

    @Override
    public int getCount() {
        return aCategoryFragment.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return aCategoryTitle.get(position);
    }
}