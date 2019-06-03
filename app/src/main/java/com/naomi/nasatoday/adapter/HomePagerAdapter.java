package com.naomi.nasatoday.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import com.naomi.nasatoday.activity.Space;
import com.naomi.nasatoday.Fragment.HomeDetailFragment;

public class HomePagerAdapter extends FragmentPagerAdapter {private ArrayList<Space> mspace;

    public HomePagerAdapter(FragmentManager fm, ArrayList<Space> Space) {
        super(fm);
        mspace = Space;
    }

    @Override
    public Fragment getItem(int position) {
        return HomeDetailFragment.newInstance(mspace.get(position));
    }

    @Override
    public int getCount() {
        return mspace.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mspace.get(position).getmTitle();
    }
}
