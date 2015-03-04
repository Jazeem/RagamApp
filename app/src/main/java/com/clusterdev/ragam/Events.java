package com.clusterdev.ragam;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

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
    AlphaAnimation fadeIn;
    AlphaAnimation fadeOut;
    String category = null;
    TextView heading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        eventsList = (ListView) findViewById(R.id.events);
        categoriesList = (ListView) findViewById(R.id.categories);

        categoriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                String selected = ((TextView) view).getText().toString();
                if (category == null) {
                    category = selected;
                    fadeIn();

                    return;
                } else if (selected.equals(category)) {
                    return;
                }

                category = selected;

                for (int j = 0; j < parent.getChildCount(); j++)
                    setSelected((TextView) parent.getChildAt(j), false);

                setSelected(textView, true);
            }
        });


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

        fadeIn = new AlphaAnimation(0.0f, 1.0f);
        fadeIn.setDuration(500);
        fadeIn.setFillAfter(true);

        fadeOut = new AlphaAnimation(1.0f, 0.0f);
        fadeOut.setDuration(500);
        fadeOut.setFillAfter(true);
        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Log.d("Animation", "Fade Out stopped");
                fadeIn();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

//        heading.startAnimation(fadeIn);
//        heading.setVisibility(View.VISIBLE);


    }

    @Override
    protected void onResume() {
        super.onResume();

        categoriesList.getAdapter().getView(1, null, null).performClick();

    }

    private void setSelected(TextView view, boolean selected) {


        if (selected) {
            eventsList.startAnimation(fadeOut);
            view.setBackgroundColor(getResources().getColor(R.color.events_color));
            view.setTextColor(getResources().getColor(R.color.white));
        } else {
            view.setBackgroundColor(getResources().getColor(R.color.white));
            view.setTextColor(getResources().getColor(R.color.events_color));
        }
    }

    private void fadeIn() {

        eventsCursor = db.getEventsByGenre(category);
        eventsAdapter.swapCursor(eventsCursor);
        eventsList.startAnimation(fadeIn);
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

