package com.clusterdev.ragam;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.clusterdev.ragam.fragments.CelebrityTalkFragment;
import com.clusterdev.ragam.fragments.EventsFragment;
import com.clusterdev.ragam.fragments.ExhibitionFragment;
import com.clusterdev.ragam.fragments.GalleryFragment;
import com.clusterdev.ragam.fragments.WorkshopFragment;


public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    private int NUM_PAGES = 5;

    public ScreenSlidePagerAdapter(FragmentManager fm) {
        super(fm);
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

            /*case 4:
                return ProShow.newInstance();*/

            case 4:
                return GalleryFragment.newInstance();

            default:
                return null;

        }


    }

    @Override
    public int getCount() {
        return NUM_PAGES;
    }
}