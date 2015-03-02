package com.clusterdev.ragam;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;


public class Events extends ActionBarActivity {
    ListView eventsList;
    ListView categoriesList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        eventsList = (ListView) findViewById(R.id.events);
        categoriesList = (ListView) findViewById(R.id.categories);

        String[] events = new String[]{"Event1","Event2","Event3"};
        String[] categories= new String[]{"Category1","Category2","Category3"};

        final ArrayList<String> listEvent = new ArrayList<String>();
        final ArrayList<String> listCategory = new ArrayList<String>();

        for (int i = 0; i < events.length; ++i) {
            listEvent.add(events[i]);
        }
        for (int i = 0; i < categories.length; ++i) {
            listCategory.add(categories[i]);
        }
        ArrayAdapter<String> eventsAdapter = new ArrayAdapter<String>(this,
                R.layout.list_item,listEvent);
        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(this,
                R.layout.list_item,listCategory);

        eventsList.setAdapter(eventsAdapter);
        categoriesList.setAdapter(categoriesAdapter);

    }


}
