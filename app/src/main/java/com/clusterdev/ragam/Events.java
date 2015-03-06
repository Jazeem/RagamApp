package com.clusterdev.ragam;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Events extends ActionBarActivity {
    ListView eventsList;
    ListView categoriesList;
    DataBaseHelper db;
    Cursor eventsCursor;
    Cursor categoryCursor;
    CustomCursorAdapter eventsAdapter;
    CustomCursorAdapter categoriesAdapter;
    AlphaAnimation customFadeIn;
    AlphaAnimation customFadeOut;
    String category = null;
    TextView heading;
    View rightForeground;
    AdapterView.OnItemClickListener categoryClickListner;
    AdapterView.OnItemClickListener eventClickListner;
    View events_layout;
    TextView description;
    String selectedEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_events);


        eventsList = (ListView) findViewById(R.id.events);
        categoriesList = (ListView) findViewById(R.id.categories);
        rightForeground = findViewById(R.id.right_foreground);
        events_layout= findViewById(R.id.events_layout);

        description= (TextView) findViewById(R.id.description);
        eventClickListner = new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
// previously invisible view
                TextView textView= (TextView) view;

                for(int i=0;i<parent.getChildCount();i++)
                    setSelectedDesign((TextView) parent.getChildAt(i),true);
                setSelectedDesign(textView, false);

// get the center for the clipping circle
                selectedEvent=textView.getText().toString();
                heading.setText(selectedEvent);
                AlphaAnimation fadeOut = new AlphaAnimation(1.0f, 0.0f);
                fadeOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {


                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                fadeOut.setDuration(500);
                fadeOut.setFillAfter(true);

                Animation scaleAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.scale);
                categoriesList.setOnItemClickListener(null);
                eventsList.setOnItemClickListener(null);
                scaleAnim.setDuration(1200);
                scaleAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Log.d("Scale","Stopped");
                        AlphaAnimation newfadeIn;
                        newfadeIn = new AlphaAnimation(0.0f, 1.0f);
                        newfadeIn.setDuration(500);
                        newfadeIn.setFillAfter(true);

                        description.setVisibility(View.VISIBLE);
                        description.startAnimation(newfadeIn);
                        Log.d("Fade In description","Started");

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });

                rightForeground.startAnimation(scaleAnim);
                events_layout.startAnimation(fadeOut);
// make the view visible and start the animation
                rightForeground.setVisibility(View.VISIBLE);

                //anim.start();


            }
        };

        categoryClickListner = new AdapterView.OnItemClickListener() {



            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;

                if(category!=null && category.equals(textView.getText().toString())){
                    return;
                }



                for (int j = 0; j < parent.getChildCount(); j++)
                    setSelected((TextView) parent.getChildAt(j), false);

                setSelected(textView, true);
            }

        };
        eventsList.setOnItemClickListener(eventClickListner);
        categoriesList.setOnItemClickListener(categoryClickListner);


        db = new DataBaseHelper(this);

        try {
            db.createDataBase();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        db.openDataBase();
        categoryCursor = db.getGenres("COMPETITIONS");

        eventsAdapter = new CustomCursorAdapter(this, null, true);
        categoriesAdapter = new CustomCursorAdapter(this, categoryCursor, false);


        eventsList.setAdapter(eventsAdapter);
        categoriesList.setAdapter(categoriesAdapter);

        heading = (TextView) findViewById(R.id.events_heading);
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeue-Thin.otf");
        heading.setTypeface(tf);
        description.setTypeface(tf);


//        heading.startAnimation(fadeIn);
//        heading.setVisibility(View.VISIBLE);


    }

    public void backPressed(View view) {
        final Animation shrinkAnim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shrink);
        categoriesList.setOnItemClickListener(null);
        shrinkAnim.setDuration(1200);
        final AlphaAnimation fadeIn;
        fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(500);
        fadeIn.setFillAfter(true);
        AlphaAnimation fadeOut;
        fadeOut = new AlphaAnimation(1.0f,0.0f);
        fadeOut.setDuration(500);
        heading.setText("events");
       // fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                description.setVisibility(View.GONE);
                events_layout.startAnimation(fadeIn);


                rightForeground.startAnimation(shrinkAnim);
                shrinkAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        eventsList.setOnItemClickListener(eventClickListner);
                        categoriesList.setOnItemClickListener(categoryClickListner);
                        rightForeground.setVisibility(View.INVISIBLE);

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                rightForeground.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
     description.startAnimation(fadeOut);



    }

    private void setSelected(TextView view, boolean selected) {
        if (selected) {

            String selectedCategory = view.getText().toString();
            Log.d("Selected ",selectedCategory);
            if (category == null) {
                category = selectedCategory;
                setSelectedDesign(view, selected);
                fadeIn();
                return;
            } else if (selectedCategory.equals(category)) {
                return;
            } else {
                category = selectedCategory;
                setSelectedDesign(view, selected);

                customFadeOut = new AlphaAnimation(1.0f, 0.0f);
                customFadeOut.setDuration(500);
                customFadeOut.setFillAfter(true);
                customFadeOut.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        events_layout.setVisibility(View.GONE);

                        fadeIn();

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
                events_layout.startAnimation(customFadeOut);
                return;
            }
        } else {

            setSelectedDesign(view, selected);

            return;
        }


    }

    private void setSelectedDesign(TextView view, boolean selected) {

        if (selected) {
            view.setBackgroundColor(getResources().getColor(R.color.events_color));
            view.setTextColor(getResources().getColor(R.color.white));
        } else {
            view.setBackgroundColor(getResources().getColor(R.color.white));
            view.setTextColor(getResources().getColor(R.color.events_color));
        }
    }


    private void fadeIn() {

        eventsCursor = db.getEventsByGenre(category);
        eventsAdapter=new CustomCursorAdapter(this, eventsCursor,true);
        eventsList.setAdapter(eventsAdapter);
        customFadeIn = new AlphaAnimation(0.0f, 1.0f);
        customFadeIn.setDuration(500);
        customFadeIn.setFillAfter(true);
        events_layout.setVisibility(View.VISIBLE);
        events_layout.startAnimation(customFadeIn);

    }

    public class CustomCursorAdapter extends CursorAdapter {
        private final Context context;

        private boolean isEvent;


        public CustomCursorAdapter(Context context, Cursor cursor, boolean isEvent) {
            super(context, cursor);
            this.isEvent = isEvent;
            this.context = context;

        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = null;
            TextView textView = null;
            if (isEvent) {
                rowView = inflater.inflate(R.layout.list_item_event, parent, false);
                textView = (TextView) rowView.findViewById(R.id.list_item_event);

            } else {
                rowView = inflater.inflate(R.layout.list_item_category, parent, false);
                textView = (TextView) rowView.findViewById(R.id.list_item_category);

            }

            Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeue-Thin.otf");
            textView.setTypeface(tf);
            return rowView;
        }

        @Override
        public void bindView(View view, Context context, Cursor cursor) {


            TextView textView = (TextView) view;
            String name = "";

            if (isEvent)
                name = cursor.getString(cursor.getColumnIndex("name"));
            else
                name = cursor.getString(cursor.getColumnIndex("genre"));
            textView.setText(name);

        }
    }

}

