package adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import Activity.Space;
import Fragment.MainDetailFragment;

public class MainPagerAdapter extends FragmentPagerAdapter {private ArrayList<Space> mspace;

    public MainPagerAdapter(FragmentManager fm, ArrayList<Space> Space) {
        super(fm);
        mspace = Space;
    }

    @Override
    public Fragment getItem(int position) {
        return MainDetailFragment.newInstance(mspace.get(position));
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
