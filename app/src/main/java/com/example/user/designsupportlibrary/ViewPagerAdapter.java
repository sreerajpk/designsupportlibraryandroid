package com.example.user.designsupportlibrary;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * Created by user on 7/7/15.
 */
public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private int numberOfTabs;
    private List<String> titles;

    public ViewPagerAdapter(FragmentManager fragmentManager, List<String> titles,
                            int numberOfTabs)

    {
        super(fragmentManager);

        this.titles = titles;
        this.numberOfTabs = numberOfTabs;
    }

    @Override
    public int getCount() {
        return numberOfTabs;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return TabsFragment.newInstance(position);
    }
}
