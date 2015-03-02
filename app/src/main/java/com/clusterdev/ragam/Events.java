package com.clusterdev.ragam;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class Events extends ActionBarActivity {
    ListView eventsList;
    ListView categoriesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        eventsList = (ListView) findViewById(R.id.events);
        categoriesList = (ListView) findViewById(R.id.categories);

        String[] events = new String[]{"event1", "event2", "event3"};
        String[] categories = new String[]{"category1", "category2", "category3"};

        final ArrayList<String> listEvent = new ArrayList<String>();
        final ArrayList<String> listCategory = new ArrayList<String>();

        for (int i = 0; i < events.length; ++i) {
            listEvent.add(events[i]);
        }
        for (int i = 0; i < categories.length; ++i) {
            listCategory.add(categories[i]);
        }
        ArrayAdapter<String> eventsAdapter = new MySimpleArrayAdapter(this,
                R.layout.list_item_event, events,true);
        ArrayAdapter<String> categoriesAdapter = new MySimpleArrayAdapter(this,
                R.layout.list_item_category, categories,false);

        eventsList.setAdapter(eventsAdapter);
        categoriesList.setAdapter(categoriesAdapter);

    }

    public class MySimpleArrayAdapter extends ArrayAdapter<String> {
        private final Context context;
        private final String[] values;
        private boolean isEvent;


        public MySimpleArrayAdapter(Context context, int id, String[] values, boolean isEvent) {
            super(context, id, values);
            this.isEvent = isEvent;
            this.context = context;
            this.values = values;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView = null;
            TextView textView=null;
            if (isEvent) {
                rowView = inflater.inflate(R.layout.list_item_event, parent, false);
                textView = (TextView) rowView.findViewById(R.id.list_item_event);

            } else {
                rowView = inflater.inflate(R.layout.list_item_category, parent, false);
                textView = (TextView) rowView.findViewById(R.id.list_item_category);

            }

            Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/HelveticaNeue-Thin.otf");
            textView.setTypeface(tf);
            textView.setText(values[position]);
            // change the icon for Windows and iPhone

            return rowView;
        }
    }

}

