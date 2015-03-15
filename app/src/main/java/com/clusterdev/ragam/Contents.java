package com.clusterdev.ragam;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import com.clusterdev.ragam.R;
import com.jfeinstein.jazzyviewpager.JazzyViewPager;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.IconPageIndicator;
import com.viewpagerindicator.LinePageIndicator;
import com.viewpagerindicator.UnderlinePageIndicator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;

public class Contents extends FragmentActivity {
    /**
     * The number of pages (wizard steps) to show in this demo.
     */
    private static final int NUM_PAGES = 5;

    /**
     * The pager widget, which handles animation and allows swiping horizontally to access previous
     * and next wizard steps.
     */
    private JazzyViewPager mPager;

    /**
     * The pager adapter, which provides the pages to the view pager widget.
     */
    private PagerAdapter mPagerAdapter;
    private boolean prompterDismissed;

    private RelativeLayout prompter;
    private SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents);
        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        prompterDismissed =sharedPreferences.getBoolean("prompterDismissed",false);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent=getIntent();

        editor=sharedPreferences.edit();
        prompter= (RelativeLayout) findViewById(R.id.prompter);


        prompter.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                prompter.setVisibility(View.GONE);
                editor.putBoolean("prompterDismissed", true);
                editor.commit();
                prompterDismissed = true;
                return false;
            }
        });

        if (prompterDismissed)
            prompter.setVisibility(View.GONE);
        // Instantiate a ViewPager and a PagerAdapter.
        mPager = (JazzyViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(),mPager);
        mPager.setAdapter(mPagerAdapter);
        mPager.setTransitionEffect(JazzyViewPager.TransitionEffect.RotateDown);
        mPager.setCurrentItem(intent.getIntExtra("FRAGMENT",0));
        LinePageIndicator indicator = (LinePageIndicator) findViewById(R.id.titles);
        indicator.setViewPager(mPager);
        indicator.setCurrentItem(intent.getIntExtra("FRAGMENT",0));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}


/**
 * A simple pager adapter that represents 5 ScreenSlidePageFragment objects, in
 * sequence.
 */

