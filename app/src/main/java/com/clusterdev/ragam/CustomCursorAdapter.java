package com.clusterdev.ragam;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

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

        Typeface tf = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeue-Thin.otf");
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
