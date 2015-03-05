package com.clusterdev.ragam;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SlidingDrawer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jazeem on 03/03/15.
 */
public class Workshop extends Activity {
    private TextView heading,description;
    private ListView list;
    private ArrayList<String> words;
    private SlidingDrawer slidingDrawer;
    private Typeface tf;
    private boolean openingForFirstTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workshop_layout);
        openingForFirstTime=true;
        heading= (TextView) findViewById(R.id.workshop_heading);
        description= (TextView) findViewById(R.id.description_textview);
        tf=Typeface.createFromAsset(getAssets(),"fonts/HelveticaNeue-Thin.otf");
        heading.setTypeface(tf);
        list = (ListView) findViewById(R.id.listview);
        slidingDrawer= (SlidingDrawer) findViewById(R.id.slidingDrawer);
        words=new ArrayList<String>();
        words.add("Coderz");
        words.add("Google");
        words.add("Android");
        words.add("iPhone");
        words.add("Apple");
        list.setBackgroundColor(R.color.event_color);
        list.setAdapter(new WorkshopAdapter(Workshop.this,words));
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int arg2,long arg3)
            {
                //args2 is the listViews Selected index
                TranslateAnimation SliderAnimationDown = new TranslateAnimation(0, 0, Animation.RELATIVE_TO_SELF, slidingDrawer.getHeight());
                SliderAnimationDown.setDuration(1000);


                SliderAnimationDown.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        slidingDrawer.close();
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                slidingDrawer.startAnimation(SliderAnimationDown);
                final Animation out = new AlphaAnimation(1.0f, 0.0f);
                out.setDuration(500);
                final Animation in = new AlphaAnimation(0.0f,1.0f);
                in.setDuration(500);
                out.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        heading.setText(words.get(arg2));
                        description.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis vel lectus nec nisi sodales congue et at justo. In pharetra ligula eget nisi convallis efficitur. Aliquam purus erat, fringilla vulputate eros faucibus, pharetra egestas nisl. Pellentesque at hendrerit nibh. Cras arcu sapien, iaculis quis tellus ac, maximus scelerisque libero. Suspendisse bibendum purus nisl, id faucibus justo sollicitudin in. Donec id lectus sed lacus vestibulum condimentum nec non felis.");
                        heading.startAnimation(in);
                        description.startAnimation(in);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                heading.startAnimation(out);
                description.startAnimation(out);
                description.setTypeface(tf);
            }
        });
        slidingDrawer.setOnDrawerOpenListener(new SlidingDrawer.OnDrawerOpenListener() {
            @Override
            public void onDrawerOpened() {
                final Animation out = new AlphaAnimation(1.0f, 0.0f);
                out.setDuration(500);
                final Animation in = new AlphaAnimation(0.0f,1.0f);
                in.setDuration(500);
                out.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        heading.setText("workshops");
                        description.setText("");
                        heading.startAnimation(in);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                if (openingForFirstTime)
                    openingForFirstTime=false;
                else {
                    heading.startAnimation(out);
                    description.startAnimation(out);
                }

            }
        });
        slidingDrawer.open();
    }
}
