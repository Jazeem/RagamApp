package com.clusterdev.ragam;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import com.clusterdev.ragam.fragments.AboutFragment;
import com.clusterdev.ragam.fragments.CelebrityTalkFragment;
import com.clusterdev.ragam.fragments.EventsFragment;
import com.clusterdev.ragam.fragments.ExhibitionFragment;
import com.clusterdev.ragam.fragments.GalleryFragment;
import com.clusterdev.ragam.fragments.ProShowFragment;
import com.clusterdev.ragam.fragments.WorkshopFragment;
import com.jfeinstein.jazzyviewpager.JazzyViewPager;


public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    private int NUM_PAGES = 7;
    private JazzyViewPager mJazzy;

    public ScreenSlidePagerAdapter(FragmentManager fm,JazzyViewPager mJazzy) {
        super(fm);
        this.mJazzy=mJazzy;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return EventsFragment.newInstance();

            case 1:
                return WorkshopFragment.newInstance();

            case 2:
                return ExhibitionFragment.newInstance();

            case 3:
                return CelebrityTalkFragment.newInstance();

            case 4:
                return ProShowFragment.newInstance();

            case 5:
                return GalleryFragment.newInstance();
            case 6:
                return AboutFragment.newInstance();
            default:
                return null;

        }


    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Object obj = super.instantiateItem(container, position);
        mJazzy.setObjectForPosition(obj, position);
        return obj;
    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}