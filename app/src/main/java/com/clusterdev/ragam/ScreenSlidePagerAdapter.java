package com.clusterdev.ragam;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.clusterdev.ragam.fragments.EventsFragment;


public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        private int NUM_PAGES=3;
        public ScreenSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

    public Fragment getItem(int position) {

            return EventsFragment.newInstance();
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }